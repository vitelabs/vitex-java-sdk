package org.vite.dex.api.client;

import org.vite.dex.api.client.domain.enums.KlineInterval;
import org.vite.dex.api.client.domain.account.CancelOrderResponse;
import org.vite.dex.api.client.domain.account.NewOrderResponse;
import org.vite.dex.api.client.domain.account.Order;
import org.vite.dex.api.client.domain.account.OrderList;
import org.vite.dex.api.client.domain.account.request.*;
import org.vite.dex.api.client.domain.general.MarketDetail;
import org.vite.dex.api.client.domain.general.MarketPrecision;
import org.vite.dex.api.client.domain.market.*;
import org.vite.dex.api.client.utils.AsyncResult;
import org.vite.dex.api.client.utils.ResponseCallback;

import java.util.List;

/**
 * Vite API façade, supporting asynchronous/non-blocking access Vite's REST API.
 */
public interface AsyncRequest {

    // General endpoints

    /**
     * Test connectivity to the Rest API.
     */
//    void ping(ResponseCallback<AsyncResult<Void>> callback);

    /**
     * Check server time.
     */
    void getServerTime(ResponseCallback<AsyncResult<Long>> callback);

    /**
     * Current exchange trading rules and symbol information
     */
    void getAllMarkets(Integer offset, Integer limit, ResponseCallback<AsyncResult<List<MarketPrecision>>> callback);

    /**
     * Current exchange trading rules and symbol information
     */
    void getMarketDetail(String symbol, ResponseCallback<AsyncResult<MarketDetail>> callback);

    // Market Data endpoints

    /**
     * Get order book of a symbol (asynchronous)
     *
     * @param symbol   ticker symbol (e.g. VTT-000_VITE)
     * @param limit    depth of the order book (max 100)
     * @param callback the callback that handles the response
     */
    void getOrderBook(String symbol, Integer limit, Integer precision, ResponseCallback<AsyncResult<OrderBook>> callback);

    /**
     * Get recent trades (up to last 500). Weight: 1
     *
     * @param symbol   ticker symbol (e.g. VTT-000_VITE)
     * @param limit    of last trades (Default 500; max 1000.)
     * @param callback the callback that handles the response
     */
    void getTrades(String symbol, Integer limit, ResponseCallback<AsyncResult<List<LatestTrade>>> callback);

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
    void getCandlestickBars(String symbol, KlineInterval interval, Integer limit, Long startTime, Long endTime, ResponseCallback<AsyncResult<Candlesticks>> callback);

    /**
     * Kline/candlestick bars for a symbol. Klines are uniquely identified by their open time.
     */
    void getCandlestickBars(String symbol, KlineInterval interval, ResponseCallback<AsyncResult<Candlesticks>> callback);

    /**
     * Get 24 hour price change statistics (asynchronous).
     *
     * @param symbol   ticker symbol (e.g. VTT-000_VITE)
     * @param callback the callback that handles the response
     */
    void get24HrPriceStatistics(String symbol, String quoteTokenSymbol, String quoteTokenCategory, ResponseCallback<AsyncResult<List<TickerStatistics>>> callback);

    /**
     * Get best price/qty on the order book for a symbol (asynchronous).
     *
     * @param symbol   ticker symbol (e.g. VTT-000_VITE)
     * @param callback the callback that handles the response
     */
    void getBookTicker(String symbol, ResponseCallback<AsyncResult<BookTicker>> callback);

    // Account endpoints

    /**
     * Send in a new order (asynchronous)
     *
     * @param order    the new order to submit.
     * @param callback the callback that handles the response
     */
    void newOrder(NewOrderRequest order, ResponseCallback<AsyncResult<NewOrderResponse>> callback);

    /**
     * Test new order creation and signature/recvWindow long. Creates and validates a new order but does not send it into the matching engine.
     *
     * @param order    the new TEST order to submit.
     * @param callback the callback that handles the response
     */
    void newOrderTest(NewOrderRequest order, ResponseCallback<AsyncResult<Void>> callback);

    /**
     * Check an order's status (asynchronous).
     *
     * @param orderStatusRequest order status request parameters
     * @param callback           the callback that handles the response
     */
    void getOrderStatus(QueryOrderRequest orderStatusRequest, ResponseCallback<AsyncResult<Order>> callback);

    /**
     * Cancel an active order (asynchronous).
     *
     * @param cancelOrderRequest order status request parameters
     * @param callback           the callback that handles the response
     */
    void cancelOrder(CancelOrderRequest cancelOrderRequest, ResponseCallback<AsyncResult<CancelOrderResponse>> callback);

    /**
     * Get all account orders; active, canceled, or filled.
     *
     * @param orderRequest order request parameters
     * @param callback     the callback that handles the response
     */
    void getAllOrders(QueryOrdersRequest orderRequest, ResponseCallback<AsyncResult<OrderList>> callback);

    /**
     * Cancel all active orders of a symbol (asynchronous).
     *
     * @param cancelOrdersRequest order status request parameters
     * @param callback the callback that handles the response
     */
    void cancelOrders(CancelOrdersRequest cancelOrdersRequest, ResponseCallback<AsyncResult<List<CancelOrderResponse>>> callback);
}