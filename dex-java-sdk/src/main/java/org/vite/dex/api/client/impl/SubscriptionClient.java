package org.vite.dex.api.client.impl;



import org.vite.dex.api.client.Subscription;
import org.vite.dex.api.client.domain.enums.KlineInterval;
import org.vite.dex.api.client.domain.enums.QuoteTokenCategory;
import org.vite.dex.api.client.domain.event.*;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class SubscriptionClient implements Subscription {

    private WebSocketRequestImpl requestImpl;
    private WebSocketConnection connection;
    private WebSocketWatchDog watchDog;
    private SubscriptionOptions options;

    private SubscriptionClient() {

    }

    public static SubscriptionClient builder() {
        return new SubscriptionClient();
    }

    public SubscriptionClient serverUrl(String serverUrl) {
        this.options = new SubscriptionOptions(serverUrl);
        return this;
    }

    public SubscriptionClient options(SubscriptionOptions options) {
        this.options = options;
        return this;
    }

    public SubscriptionClient watchDog(WebSocketWatchDog watchDog) {
        this.watchDog = watchDog;
        return this;
    }

    public SubscriptionClient build() {
        Objects.requireNonNull(this.options);
        if (watchDog == null) {
            watchDog = new WebSocketWatchDog(options);
        }
        this.requestImpl = new WebSocketRequestImpl();
        connection = new WebSocketConnection(options, watchDog);
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
    public void subscribe24HTickerStatisticsEvent( QuoteTokenCategory quoteTokenCategory, SubscriptionListener<TickerStatisticsEvent> callback) {
        subscribe24HTickerStatisticsEvent(quoteTokenCategory,callback,null);
    }

    @Override
    public void subscribe24HTickerStatisticsEvent(QuoteTokenCategory quoteTokenCategory, SubscriptionListener<TickerStatisticsEvent> callback, SubscriptionErrorHandler errorHandler) {
        subscribe(requestImpl.subscribe24HTickerStatisticsEvent(quoteTokenCategory, callback, errorHandler));
    }

    @Override
    public void subscribeKlineEvent( String symbols, KlineInterval interval, SubscriptionListener<KlineEvent> callback) {
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
