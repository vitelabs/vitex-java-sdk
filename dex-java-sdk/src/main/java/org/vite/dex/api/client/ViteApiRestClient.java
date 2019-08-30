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
 * Vite API façade, supporting synchronous/blocking access Vite's REST API.
 */
public interface ViteApiRestClient {

    // General endpoints

    /**
     * Test connectivity to the Rest API.
     */
    void ping();

    /**
     * Test connectivity to the Rest API and get the current server time.
     *
     * @return current server time.
     */
    Long getServerTime();

    /**
     * @return Current exchange trading rules and symbol information
     */
    List<Market> getAllMarkets(String operator, String quoteCurrency);

    /**
     * @return Current exchange trading rules and symbol information
     */
    MarketDetail getMarketDetail(String symbol);


    // Market Data endpoints

    /**
     * Get order book of a symbol.
     *
     * @param symbol ticker symbol (e.g. VTT-000_VITE)
     * @param limit  depth of the order book (max 100)
     */
    OrderBook getOrderBook(String symbol, Integer limit, Integer precision);

    /**
     * Get recent trades (up to last 500). Weight: 1
     *
     * @param symbol ticker symbol (e.g. VTT-000_VITE)
     * @param limit  of last trades (Default 500; max 1000.)
     */
    List<LatestTrade> getTrades(String symbol, Integer limit);


    /**
     * Kline/candlestick bars for a symbol. Klines are uniquely identified by their open time.
     *
     * @param symbol    symbol to aggregate (mandatory)
     * @param interval  candlestick interval (mandatory)
     * @param limit     Default 500; max 1000 (optional)
     * @param startTime Timestamp in ms to get candlestick bars from INCLUSIVE (optional).
     * @param endTime   Timestamp in ms to get candlestick bars until INCLUSIVE (optional).
     * @return a candlestick bar for the given symbol and interval
     */
    Candlesticks getCandlestickBars(String symbol, KlineInterval interval, Integer limit, Long startTime, Long endTime);

    /**
     * Kline/candlestick bars for a symbol. Klines are uniquely identified by their open time.
     *
     * @see #getCandlestickBars(String, KlineInterval, Integer, Long, Long)
     */
    Candlesticks getCandlestickBars(String symbol, KlineInterval interval);

    /**
     * Get 24 hour price change statistics for all symbols.
     */
    List<TickerStatistics> get24HrPriceStatistics(String symbol, String quoteCurrency);

    /**
     * Get best price/qty on the order book for a symbol.
     */
    BookTicker getBookTicker(String symbol);

    // Account endpoints

    /**
     * Send in a new order.
     *
     * @param order the new order to submit.
     * @return a response containing details about the newly placed order.
     */
    NewOrderResponse newOrder(NewOrderRequest order);

    /**
     * Test new order creation and signature/recvWindow long. Creates and validates a new order but does not send it into the matching engine.
     *
     * @param order the new TEST order to submit.
     */
    void newOrderTest(NewOrderRequest order);

    /**
     * Check an order's status.
     *
     * @param queryOrderRequest order status request options/filters
     * @return an order
     */
    Order getOrderStatus(QueryOrderRequest queryOrderRequest);

    /**
     * Cancel an active order.
     *
     * @param cancelOrderRequest order status request parameters
     */
    CancelOrderResponse cancelOrder(CancelOrderRequest cancelOrderRequest);

    /**
     * Get all orders on a symbol.
     *
     * @param orderRequest order request parameters
     * @return a list of all account open orders on a symbol.
     */
    List<Order> getAllOrders(QueryOrdersRequest orderRequest);

    /**
     * Cancel all active orders of a symbol.
     *
     * @param cancelOrdersRequest
     */
    List<CancelOrderResponse> cancelOrders(CancelOrdersRequest cancelOrdersRequest);

}
