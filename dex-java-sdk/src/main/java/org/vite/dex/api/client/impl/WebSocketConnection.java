package org.vite.dex.api.client.impl;

import com.google.common.collect.Maps;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vite.dex.api.client.domain.event.DexProto;
import org.vite.dex.api.client.exception.DexApiException;
import org.vite.dex.api.client.utils.TimeService;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

public class WebSocketConnection extends WebSocketListener {

    private static final Logger log = LoggerFactory.getLogger(WebSocketConnection.class);


    private static int connectionCounter = 0;
    private static UUID uuid = UUID.randomUUID();
    private final Request okHttpRequest;
    private final WebSocketWatchDog watchDog;
    private final String clientId;
    private final String apiKey;
    private final String secretKey;
    private WebSocket webSocket = null;
    private volatile long lastReceivedTime = 0;
    private volatile ConnectionState state = ConnectionState.IDLE;
    private int delayInSecond = 0;
    private String subscriptionServerUrl;
    private String tradingHost;
    private Map<String, WebSocketRequest> topicWebSocketRequestMap = Maps.newConcurrentMap();
    private Map<String, WebSocketRequest> topicsWebSocketRequestMap = Maps.newConcurrentMap();
    public WebSocketConnection(
            String apiKey,
            String secretKey,
            SubscriptionOptions options,
            WebSocketWatchDog watchDog) {
        this.apiKey = apiKey;
        this.secretKey = secretKey;
        this.clientId = uuid.toString() + "_" + WebSocketConnection.connectionCounter++;
        this.subscriptionServerUrl = options.getUrl();
        try {
            String host = new URL(options.getUrl().replaceAll("wss", "https")).getHost();
            this.tradingHost = host;
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.okHttpRequest = new Request.Builder().url(subscriptionServerUrl).build();
        this.watchDog = watchDog;
        log.info("[Sub] Connection [id: " + this.clientId + "] created");
    }

    void addSubscribe(WebSocketRequest request) {
        send(DexProto.DexProtocol.newBuilder().setClientId(this.clientId).setOpType("sub").setTopics(request.topics).build());
        Arrays.stream(request.topics.split(",")).forEach(t -> {
            topicWebSocketRequestMap.put(t, request);
        });
        topicsWebSocketRequestMap.put(request.topics, request);
    }

    String getClientId() {
        return this.clientId;
    }

    void connect() {
        if (state == ConnectionState.CONNECTED) {
            log.info("[Sub][" + this.clientId + "] Already connected");
            return;
        }
        log.info("[Sub][" + this.clientId + "] Connecting...");
        webSocket = RestApiInvoker.createWebSocket(okHttpRequest, this);
    }

    void reConnect(int delayInSecond) {
        log.warn("[Sub][" + this.clientId + "] Reconnecting after "
                + delayInSecond + " seconds later");
        if (webSocket != null) {
            webSocket.cancel();
            webSocket = null;
        }
        this.delayInSecond = delayInSecond;
        state = ConnectionState.DELAY_CONNECT;
    }

    void reConnect() {
        if (delayInSecond != 0) {
            delayInSecond--;
        } else {
            connect();
            //重新注册topic
            topicsWebSocketRequestMap.forEach((k,v)->{
                send(DexProto.DexProtocol.newBuilder().setClientId(this.clientId).setOpType("sub").setTopics(k).build());
            });

        }
    }

    long getLastReceivedTime() {
        return this.lastReceivedTime;
    }

    void send(DexProto.DexProtocol dexProtocol) {
        boolean result = false;
        if (webSocket != null) {
            result = webSocket.send(ByteString.of(dexProtocol.toByteArray()));
        }
        if (!result) {
            log.error("[Sub][" + this.clientId + "] Failed to send message");
            closeOnError();
        }
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        super.onMessage(webSocket, text);
        lastReceivedTime = TimeService.getCurrentTimeStamp();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
        super.onMessage(webSocket, bytes);
        try {
            lastReceivedTime = TimeService.getCurrentTimeStamp();
            DexProto.DexProtocol dexProtocol;
            try {
                dexProtocol = DexProto.DexProtocol.parseFrom(bytes.toByteArray());
            } catch (IOException e) {
                log.error("[Sub][" + this.clientId + "] Receive message error: " + e.getMessage());
                closeOnError();
                return;
            }
            if (dexProtocol.getErrorCode() != 0) {
                String errorMsg = null;
                switch (dexProtocol.getErrorCode()) {
                    //clientId illegal
                    case 1:
                        errorMsg = "illegal_client_id";
                        break;
                    //clientId illegal
                    case 2:
                        errorMsg = "illegal_topics";
                        break;
                    //clientId illegal
                    case 3:
                        errorMsg = "illegal_op_type";
                        break;
                    case 5:
                        errorMsg = "visit_limit";
                        break;
                }
                onError(dexProtocol.getTopics(), dexProtocol.getErrorCode() + ": " + errorMsg, null);
                close();
            } else if (dexProtocol.getOpType().equals("pong")) {
                //nothing to do
            } else if (dexProtocol.getOpType().equals("ping")) {
                //nothing to do
            } else if (dexProtocol.getOpType().equals("sub")) {
                //nothing to do
            } else if (dexProtocol.getOpType().equals("un_sub")) {
                //nothing to do
            } else if (dexProtocol.getOpType().equals("push")) {
                onReceive(dexProtocol);
            }
        } catch (Exception e) {
            log.error("[Sub][" + this.getClientId() + "] Unexpected error: " + e.getMessage());
            closeOnError();
        }
    }

    private void onError(String topics, String errorMessage, Throwable e) {
        log.error("[Sub][" + this.clientId + "] " + errorMessage);

        if (StringUtils.isEmpty(topics)) {
            return;
        }

        if (topicsWebSocketRequestMap.containsKey(topics) && topicsWebSocketRequestMap.get(topics).errorHandler != null) {
            DexApiException exception = new DexApiException(DexApiException.SUBSCRIPTION_ERROR, errorMessage, e);
            topicsWebSocketRequestMap.get(topics).errorHandler.onError(exception);
        }
    }

    @SuppressWarnings("unchecked")
    private void onReceive(DexProto.DexProtocol dexProtocol) {
        Object obj = null;
        try {
            obj = topicWebSocketRequestMap.get(dexProtocol.getTopics()).eventParser.parseData(dexProtocol.getClientId(), dexProtocol.getTopics(), dexProtocol.getMessage().toByteArray());
        } catch (Exception e) {
            onError(dexProtocol.getTopics(), "Failed to parse server's response: " + e.getMessage(), e);
        }
        try {
            topicWebSocketRequestMap.get(dexProtocol.getTopics()).updateCallback.onReceive(obj);
        } catch (Exception e) {
            onError(dexProtocol.getTopics(), "Process error: " + e.getMessage()
                    + " You should capture the exception in your error handler", e);
        }
    }

    public ConnectionState getState() {
        return state;
    }

    public void unsubscribeAll() {
        send(DexProto.DexProtocol.newBuilder().setClientId(clientId).setOpType("un_sub").setTopics(StringUtils.join(topicWebSocketRequestMap.keySet(), ",")).build());
    }

    public void close() {
        log.error("[Sub][" + this.getClientId() + "] Closing normally");
        webSocket.cancel();
        webSocket = null;
        watchDog.onClosedNormally(this);
    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
        super.onClosed(webSocket, code, reason);
        if (state == ConnectionState.CONNECTED) {
            state = ConnectionState.IDLE;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        super.onOpen(webSocket, response);
        this.webSocket = webSocket;
        log.info("[Sub][" + this.clientId + "] Connected to server");
        watchDog.onConnectionCreated(this);
        state = ConnectionState.CONNECTED;
        lastReceivedTime = TimeService.getCurrentTimeStamp();
        //发送心跳
        send(DexProto.DexProtocol.newBuilder().setClientId(clientId).setOpType("ping").build());
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        onError(null, "Unexpected error: " + t.getMessage(), t);
        closeOnError();
    }

    private void closeOnError() {
        if (webSocket != null) {
            this.webSocket.cancel();
            state = ConnectionState.CLOSED_ON_ERROR;
            log.error("[Sub][" + this.getClientId() + "] Connection is closing due to error");
        }
    }

    public enum ConnectionState {
        IDLE,
        DELAY_CONNECT,
        CONNECTED,
        CLOSED_ON_ERROR
    }
}
