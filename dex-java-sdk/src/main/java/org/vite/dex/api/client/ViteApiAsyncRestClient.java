package org.vite.dex.api.client;

import org.vite.dex.api.client.domain.KlineInterval;
import org.vite.dex.api.client.domain.account.CancelOrderResponse;
import org.vite.dex.api.client.domain.account.NewOrderResponse;
import org.vite.dex.api.client.domain.account.Order;
import org.vite.dex.api.client.domain.account.request.*;
import org.vite.dex.api.client.domain.general.Market;
import org.vite.dex.api.client.domain.general.MarketDetail;
import org.vite.dex.api.client.domain.market.*;

import java.util.List;

/**
 * Vite API façade, supporting asynchronous/non-blocking access Vite's REST API.
 */
public interface ViteApiAsyncRestClient {

    // General endpoints

    /**
     * Test connectivity to the Rest API.
     */
    void ping(ViteApiCallback<Void> callback);

    /**
     * Check server time.
     */
    void getServerTime(ViteApiCallback<Long> callback);

    /**
     * Current exchange trading rules and symbol information
     */
    void getMarkets(String operator, String quoteCurrency, ViteApiCallback<List<Market>> callback);

    /**
     * Current exchange trading rules and symbol information
     */
    void getMarketDetail(String symbol, ViteApiCallback<MarketDetail> callback);

    // Market Data endpoints

    /**
     * Get order book of a symbol (asynchronous)
     *
     * @param symbol   ticker symbol (e.g. VTT-000_VITE)
     * @param limit    depth of the order book (max 100)
     * @param callback the callback that handles the response
     */
    void getOrderBook(String symbol, Integer limit, Integer precision, ViteApiCallback<OrderBook> callback);

    /**
     * Get recent trades (up to last 500). Weight: 1
     *
     * @param symbol   ticker symbol (e.g. VTT-000_VITE)
     * @param limit    of last trades (Default 500; max 1000.)
     * @param callback the callback that handles the response
     */
    void getTrades(String symbol, Integer limit, ViteApiCallback<List<LatestTrade>> callback);

    /**
     * Kline/candlestick bars for a symbol. Klines are uniquely identified by their open time.
     *
     * @param symbol    symbol to aggregate (mandatory)
     * @param interval  candlestick interval (mandatory)
     * @param limit     Default 500; max 1000 (optional)
     * @param startTime Timestamp in ms to get candlestick bars from INCLUSIVE (optional).
     * @param endTime   Timestamp in ms to get candlestick bars until INCLUSIVE (optional).
     * @param callback  the callback that handles the response containing a candlestick bar for the given symbol and interval
     */
    void getCandlestickBars(String symbol, KlineInterval interval, Integer limit, Long startTime, Long endTime, ViteApiCallback<Candlesticks> callback);

    /**
     * Get 24 hour price change statistics (asynchronous).
     *
     * @param symbol   ticker symbol (e.g. VTT-000_VITE)
     * @param callback the callback that handles the response
     */
    void get24HrPriceStatistics(String symbol, String quoteCurrency, ViteApiCallback<List<TickerStatistics>> callback);

    /**
     * Get best price/qty on the order book for a symbol (asynchronous).
     *
     * @param symbol   ticker symbol (e.g. VTT-000_VITE)
     * @param callback the callback that handles the response
     */
    void getBookTicker(String symbol, ViteApiCallback<BookTicker> callback);

    // Account endpoints

    /**
     * Send in a new order (asynchronous)
     *
     * @param order    the new order to submit.
     * @param callback the callback that handles the response
     */
    void newOrder(NewOrderRequest order, ViteApiCallback<NewOrderResponse> callback);

    /**
     * Test new order creation and signature/recvWindow long. Creates and validates a new order but does not send it into the matching engine.
     *
     * @param order    the new TEST order to submit.
     * @param callback the callback that handles the response
     */
    void newOrderTest(NewOrderRequest order, ViteApiCallback<Void> callback);

    /**
     * Check an order's status (asynchronous).
     *
     * @param orderStatusRequest order status request parameters
     * @param callback           the callback that handles the response
     */
    void getOrderStatus(QueryOrderRequest orderStatusRequest, ViteApiCallback<Order> callback);

    /**
     * Cancel an active order (asynchronous).
     *
     * @param cancelOrderRequest order status request parameters
     * @param callback           the callback that handles the response
     */
    void cancelOrder(CancelOrderRequest cancelOrderRequest, ViteApiCallback<CancelOrderResponse> callback);

    /**
     * Get all account orders; active, canceled, or filled.
     *
     * @param orderRequest order request parameters
     * @param callback     the callback that handles the response
     */
    void getAllOrders(QueryOrdersRequest orderRequest, ViteApiCallback<List<Order>> callback);

    /**
     * Cancel all active orders of a symbol (asynchronous).
     *
     * @param cancelOrdersRequest order status request parameters
     * @param callback the callback that handles the response
     */
    void cancelOrders(CancelOrdersRequest cancelOrdersRequest, ViteApiCallback<List<CancelOrderResponse>> callback);
}