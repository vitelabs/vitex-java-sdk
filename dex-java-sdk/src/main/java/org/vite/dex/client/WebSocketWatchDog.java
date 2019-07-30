package org.vite.dex.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vite.dex.client.bean.event.DexProto;
import org.vite.dex.client.i.SubscriptionOptions;

import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class WebSocketWatchDog {

    private static final Logger log = LoggerFactory.getLogger(WebSocketConnection.class);
    private final CopyOnWriteArrayList<WebSocketConnection> TIME_HELPER = new CopyOnWriteArrayList<>();
    private final SubscriptionOptions options;

    WebSocketWatchDog(SubscriptionOptions subscriptionOptions) {
        this.options = Objects.requireNonNull(subscriptionOptions);
        long t = 1_000;

        //断网重连
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
        exec.scheduleAtFixedRate(() -> {
            TIME_HELPER.forEach(connection -> {
                if (connection.getState() == WebSocketConnection.ConnectionState.CONNECTED) {
                    // Check response
                    if (options.isAutoReconnect()) {
                        long ts = System.currentTimeMillis() - connection.getLastReceivedTime();
                        if (ts > options.getReceiveLimitMs()) {
                            log.warn("[Sub][" + connection.getClientId() + "] No response from server");
                            connection.reConnect(options.getConnectionDelayOnFailure());
                        }
                    }
                } else if (connection.getState() == WebSocketConnection.ConnectionState.DELAY_CONNECT) {
                    connection.reConnect();
                } else if (connection.getState() == WebSocketConnection.ConnectionState.CLOSED_ON_ERROR) {
                    if (options.isAutoReconnect()) {
                        connection.reConnect(options.getConnectionDelayOnFailure());
                    }
                }
            });
        }, t, t, TimeUnit.MILLISECONDS);
        Runtime.getRuntime().addShutdownHook(new Thread(exec::shutdown));

        //发送ping消息
        ScheduledExecutorService pingExec = Executors.newScheduledThreadPool(1);
        exec.scheduleAtFixedRate(() -> {
            TIME_HELPER.forEach(connection -> {
                if (connection.getState() == WebSocketConnection.ConnectionState.CONNECTED) {
                    log.info("send ping heartbeat...");
                    connection.send(DexProto.DexProtocol.newBuilder().setClientId(connection.getClientId()).setOpType("ping").build());
                }
            });
        }, t, options.getPingIntervalMs(), TimeUnit.MILLISECONDS);
        Runtime.getRuntime().addShutdownHook(new Thread(pingExec::shutdown));
    }

    void onConnectionCreated(WebSocketConnection connection) {
        TIME_HELPER.addIfAbsent(connection);
    }

    void onClosedNormally(WebSocketConnection connection) {
        TIME_HELPER.remove(connection);
    }
}
