package org.vite.dex.client;

import org.vite.dex.client.bean.enums.KlineInterval;
import org.vite.dex.client.bean.event.*;
import org.vite.dex.client.i.Subscription;
import org.vite.dex.client.i.SubscriptionErrorHandler;
import org.vite.dex.client.i.SubscriptionListener;
import org.vite.dex.client.i.SubscriptionOptions;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class WebSocketStreamClient implements Subscription {

    private WebSocketRequestImpl requestImpl;
    private WebSocketConnection connection;
    private WebSocketWatchDog watchDog;
    private SubscriptionOptions options;

    private String apiKey;
    private String secretKey;
    private String serverUrl;

    private WebSocketStreamClient() {

    }

    public static WebSocketStreamClient builder() {
        return new WebSocketStreamClient();
    }

    public WebSocketStreamClient apiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public WebSocketStreamClient secretKey(String secretKey) {
        this.secretKey = secretKey;
        return this;
    }

    public WebSocketStreamClient serverUrl(String serverUrl) {
        this.serverUrl = serverUrl;
        this.options = new SubscriptionOptions(serverUrl);
        return this;
    }

    public WebSocketStreamClient options(SubscriptionOptions options) {
        this.options = options;
        return this;
    }

    public WebSocketStreamClient watchDog(WebSocketWatchDog watchDog) {
        this.watchDog = watchDog;
        return this;
    }

    public WebSocketStreamClient build() {
        Objects.requireNonNull(this.options);
        if (watchDog == null) {
            watchDog = new WebSocketWatchDog(options);
        }
        this.requestImpl = new WebSocketRequestImpl();
        connection = new WebSocketConnection(apiKey, secretKey, options, watchDog);
        connection.connect();
        return this;
    }

    private <T> void subscribe(WebSocketRequest<T> request) {
        connection.addSubscribe(request);
    }

    @Override
    public void subscribeTradeEvent(String symbols, SubscriptionListener<TradeEvent> callback) {
        subscribeTradeEvent(symbols, callback, null);
    }

    @Override
    public void subscribeTradeEvent(String symbols, SubscriptionListener<TradeEvent> callback, SubscriptionErrorHandler errorHandler) {
        subscribe(requestImpl.subscribeTradeEvent(symbols, callback, errorHandler));
    }

    @Override
    public void subscribeOrderUpdateEvent(String addresses, SubscriptionListener<OrderEvent> callback) {
        subscribeOrderUpdateEvent(addresses, callback, null);
    }

    @Override
    public void subscribeOrderUpdateEvent(String addresses, SubscriptionListener<OrderEvent> callback, SubscriptionErrorHandler errorHandler) {
        subscribe(requestImpl.subscribeOrderUpdateEvent(addresses, callback, errorHandler));
    }

    @Override
    public void subscribe24HTickerStatisticsEvent(String symbols, SubscriptionListener<TickerStatisticsEvent> callback) {
        subscribe24HTickerStatisticsEvent(symbols, callback, null);
    }

    @Override
    public void subscribe24HTickerStatisticsEvent(String symbols, SubscriptionListener<TickerStatisticsEvent> callback, SubscriptionErrorHandler errorHandler) {
        subscribe(requestImpl.subscribe24HTickerStatisticsEvent(symbols, callback, errorHandler));
    }

    @Override
    public void subscribeKlineEvent(String symbols, KlineInterval interval, SubscriptionListener<KlineEvent> callback) {
        subscribeKlineEvent(symbols, interval, callback, null);
    }

    @Override
    public void subscribeKlineEvent(String symbols, KlineInterval interval, SubscriptionListener<KlineEvent> callback, SubscriptionErrorHandler errorHandler) {
        subscribe(requestImpl.subscribeKlineEventEvent(symbols, interval, callback, errorHandler));
    }

    @Override
    public void subscribeDepthEvent(String symbols, SubscriptionListener<DepthEvent> callback) {
        subscribeDepthEvent(symbols, -1, callback, null);
    }

    @Override
    public void subscribeDepthEvent(String symbols, SubscriptionListener<DepthEvent> callback, SubscriptionErrorHandler errorHandler) {
        subscribe(requestImpl.subscribeDepthEvent(symbols, -1, callback, errorHandler));
    }

    @Override
    public void subscribeDepthEvent(String symbols, Integer steps, SubscriptionListener<DepthEvent> callback) {
        subscribeDepthEvent(symbols, steps, callback, null);
    }

    @Override
    public void subscribeDepthEvent(String symbols, Integer steps, SubscriptionListener<DepthEvent> callback, SubscriptionErrorHandler errorHandler) {
        subscribe(requestImpl.subscribeDepthEvent(symbols, steps, callback, errorHandler));
    }

    @Override
    public void unsubscribeAll() {
        try {
            connection.unsubscribeAll();
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            watchDog.onClosedNormally(connection);
            connection.close();
        }
    }

}
