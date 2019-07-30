package org.vite.dex.client.i;


import org.vite.dex.client.bean.enums.KlineInterval;
import org.vite.dex.client.bean.event.*;

/***
 * The subscription client interface, it is used for subscribing any market data update and
 * account change, it is asynchronous, so you must implement the SubscriptionListener interface.
 * The server will push any update to the client. if client get the update, the onReceive method
 * will be called.
 */
public interface Subscription {

    /**
     * Subscribe candlestick/kline event. If the candlestick/kline is updated, server will send the
     * data to client and onReceive in callback will be called.
     *
     * @param symbols  The symbols, like "ABC_VITE". Use comma to separate multi symbols, like
     *                 "ABC_VITE,ABC_VITE".
     * @param interval The candlestick/kline interval, MIN1, MIN5, DAY1 etc.
     * @param callback The implementation is required. onReceive will be called if receive server's
     *                 update.
     */
    void subscribeKlineEvent(String symbols, KlineInterval interval, SubscriptionListener<KlineEvent> callback);

    /**
     * Subscribe candlestick/kline event. If the candlestick/kline is updated, server will send the
     * data to client and onReceive in callback will be called.
     *
     * @param symbols      The symbols, like "ABC_VITE". Use comma to separate multi symbols, like
     *                     "ABC_VITE,ABC_VITE".
     * @param interval     The candlestick/kline interval, MIN1, MIN5, DAY1 etc.
     * @param callback     The implementation is required. onReceive will be called if receive server's
     *                     update.
     * @param errorHandler The error handler will be called if subscription failed or error happen
     *                     between client and Dex server.
     */
    void subscribeKlineEvent(String symbols, KlineInterval interval, SubscriptionListener<KlineEvent> callback, SubscriptionErrorHandler errorHandler);

    /**
     * Subscribe price depth event. If the price depth is updated, server will send the data to client
     * and onReceive in callback will be called.
     *
     * @param symbols  The symbols, like "ABC_VITE". Use comma to separate multi symbols, like
     *                 "ABC_VITE,ABC_VITE".
     * @param callback The implementation is required. onReceive will be called if receive server's
     *                 update.
     */
    void subscribeDepthEvent(String symbols, SubscriptionListener<DepthEvent> callback);

    /**
     * Subscribe price depth event. If the price depth is updated, server will send the data to client
     * and onReceive in callback will be called.
     *
     * @param symbols      The symbols, like "ABC_VITE". Use comma to separate multi symbols, like
     *                     "ABC_VITE,ABC_VITE".
     * @param callback     The implementation is required. onReceive will be called if receive server's
     *                     update.
     * @param errorHandler The error handler will be called if subscription failed or error happen
     *                     between client and Dex server.
     */
    void subscribeDepthEvent(String symbols, SubscriptionListener<DepthEvent> callback, SubscriptionErrorHandler errorHandler);

    /**
     * Subscribe price depth event. If the price depth is updated server will send the data to client
     * and onReceive in callback will be called.
     *
     * @param symbols  The symbols, like "ABC_VITE". Use comma to separate multi symbols, like
     *                 "ABC_VITE,ABC_VITE".
     * @param callback The implementation is required. onReceive will be called if receive server's
     *                 update.
     */

    /**
     * Subscribe price depth event. If the price depth is updated, server will send the data to client
     * and onReceive in callback will be called.
     *
     * @param symbols  The symbols, like "ABC_VITE". Use comma to separate multi symbols, like
     *                 "ABC_VITE,ABC_VITE".
     * @param steps    The price decimal, like "1" . Use comma to merge depth order.
     * @param callback The implementation is required. onReceive will be called if receive server's
     *                 update.
     */
    void subscribeDepthEvent(String symbols, Integer steps, SubscriptionListener<DepthEvent> callback);

    /**
     * Subscribe price depth event. If the price depth is updated, server will send the data to client
     * and onReceive in callback will be called.
     *
     * @param symbols      The symbols, like "ABC_VITE". Use comma to separate multi symbols, like
     *                     "ABC_VITE,ABC_VITE".
     * @param steps        The price decimal, like "1" . Use comma to merge depth order.
     * @param callback     The implementation is required. onReceive will be called if receive server's
     *                     update.
     * @param errorHandler The error handler will be called if subscription failed or error happen
     *                     between client and Dex server.
     */
    void subscribeDepthEvent(String symbols, Integer steps, SubscriptionListener<DepthEvent> callback, SubscriptionErrorHandler errorHandler);

    /**
     * Subscribe price depth event. If the price depth is updated server will send the data to client
     * and onReceive in callback will be called.
     *
     * @param symbols  The symbols, like "ABC_VITE". Use comma to separate multi symbols, like
     *                 "ABC_VITE,ABC_VITE".
     * @param callback The implementation is required. onReceive will be called if receive server's
     *                 update.
     */
    void subscribeTradeEvent(String symbols, SubscriptionListener<TradeEvent> callback);

    /**
     * Subscribe price depth event. If the price depth is updated, server will send the data to client
     * and onReceive in callback will be called.
     *
     * @param symbols      The symbols, like "ABC_VITE". Use comma to separate multi symbols, like
     *                     "ABC_VITE,ABC_VITE".
     * @param callback     The implementation is required. onReceive will be called if receive server's
     *                     update.
     * @param errorHandler The error handler will be called if subscription failed or error happen
     *                     between client and Dex server.
     */
    void subscribeTradeEvent(String symbols, SubscriptionListener<TradeEvent> callback, SubscriptionErrorHandler errorHandler);

    /**
     * Subscribe order changing event. If a order is created, canceled etc, server will send the data
     * to client and onReceive in callback will be called.
     *
     * @param addresses  The addresses, like "vite_xxxxxx". Use comma to separate multi address, like
     *                   "vite_xxxxxx,vite_xxxxxx".
     *
     * @param callback The implementation is required. onReceive will be called if receive server's
     *                 update.
     */
    void subscribeOrderUpdateEvent(String addresses, SubscriptionListener<OrderEvent> callback);

    /**
     * Subscribe order changing event. If a order is created, canceled etc, server will send the data
     * to client and onReceive in callback will be called.
     *
     * @param addresses  The addresses, like "vite_xxxxxx". Use comma to separate multi address, like
     *                   "vite_xxxxxx,vite_xxxxxx".
     *
     * @param callback     The implementation is required. onReceive will be called if receive server's
     *                     update.
     * @param errorHandler The error handler will be called if subscription failed or error happen
     *                     between client and Dex server.
     */
    void subscribeOrderUpdateEvent(String addresses, SubscriptionListener<OrderEvent> callback,
                                   SubscriptionErrorHandler errorHandler);

    /**
     * Subscribe 24 hours trade statistics event. If statistics is generated, server will send the
     * data to client and onReceive in callback will be called.
     *
     * @param symbols  The symbols, like "ABC_VITE". Use comma to separate multi symbols, like
     *                 "ABC_VITE,ABC_VITE".
     * @param callback The implementation is required. onReceive will be called if receive server's
     *                 update.
     */
    void subscribe24HTickerStatisticsEvent(String symbols, SubscriptionListener<TickerStatisticsEvent> callback);

    /**
     * Subscribe 24 hours trade statistics event. If statistics is generated, server will send the
     * data to client and onReceive in callback will be called.
     *
     * @param symbols      The symbols, like "ABC_VITE". Use comma to separate multi symbols, like
     *                     "ABC_VITE,ABC_VITE".
     * @param callback     The implementation is required. onReceive will be called if receive server's
     *                     update.
     * @param errorHandler The error handler will be called if subscription failed or error happen
     *                     between client and Dex server.
     */
    void subscribe24HTickerStatisticsEvent(String symbols, SubscriptionListener<TickerStatisticsEvent> callback, SubscriptionErrorHandler errorHandler);

    /**
     * Unsubscribe all subscription.
     */
    void unsubscribeAll();

}
