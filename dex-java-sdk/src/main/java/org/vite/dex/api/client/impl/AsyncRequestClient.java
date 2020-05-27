package org.vite.dex.api.client.impl;


import org.vite.dex.api.client.AsyncRequest;
import org.vite.dex.api.client.domain.account.CancelOrderResponse;
import org.vite.dex.api.client.domain.account.NewOrderResponse;
import org.vite.dex.api.client.domain.account.Order;
import org.vite.dex.api.client.domain.account.OrderList;
import org.vite.dex.api.client.domain.account.request.*;
import org.vite.dex.api.client.domain.enums.KlineInterval;
import org.vite.dex.api.client.domain.general.MarketDetail;
import org.vite.dex.api.client.domain.general.MarketPrecision;
import org.vite.dex.api.client.domain.market.*;
import org.vite.dex.api.client.utils.AsyncResult;
import org.vite.dex.api.client.utils.InputChecker;
import org.vite.dex.api.client.utils.ResponseCallback;

import java.util.List;

/**
 * Implementation of Vite's REST API using Retrofit with asynchronous/non-blocking method calls.
 */
public class AsyncRequestClient implements AsyncRequest {

    private RestApiRequestParser requestImpl;

    private String apiKey;
    private String secretKey;
    private String serverUrl;

    private AsyncRequestClient() {

    }

    public static AsyncRequestClient builder() {
        return new AsyncRequestClient();
    }

    public AsyncRequestClient apiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public AsyncRequestClient secretKey(String secretKey) {
        this.secretKey = secretKey;
        return this;
    }

    public AsyncRequestClient serverUrl(String serverUrl) {
        this.serverUrl = serverUrl;
        return this;
    }

    public AsyncRequestClient build() {
        this.requestImpl = new RestApiRequestParser(apiKey, secretKey, serverUrl);
        return this;
    }

    // General endpoints

//    @Override
//    public void ping(ResponseCallback<AsyncResult<Void>> callback) {
//        RestApiInvoker.callASync(requestImpl);
//        dexApiService.ping().enqueue(new DexApiCallbackAdapter<>(callback));
//    }

    @Override
    public void getServerTime(ResponseCallback<AsyncResult<Long>> callback) {
        RestApiInvoker.callASync(requestImpl.getServerTime(), callback);
    }

    @Override
    public void getAllMarkets(Integer offset, Integer limit, ResponseCallback<AsyncResult<List<MarketPrecision>>> callback) {
        RestApiInvoker.callASync(requestImpl.getMarkets(offset, limit), callback);
    }

    @Override
    public void getMarketDetail(String symbol, ResponseCallback<AsyncResult<MarketDetail>> callback) {
        RestApiInvoker.callASync(requestImpl.getMarketDetail(symbol), callback);
    }

    // Market Data endpoints

    @Override
    public void getOrderBook(String symbol, Integer limit, Integer precision, ResponseCallback<AsyncResult<OrderBook>> callback) {
        RestApiInvoker.callASync(requestImpl.getDepth(symbol, limit), callback);
    }

    @Override
    public void getTrades(String symbol, Integer limit, ResponseCallback<AsyncResult<List<LatestTrade>>> callback) {
        RestApiInvoker.callASync(requestImpl.getHistoricalTrade(symbol, 0, limit, null, null, null, null, null), callback);
    }

    @Override
    public void getCandlestickBars(String symbol, KlineInterval interval, Integer limit, Long startTime, Long endTime, ResponseCallback<AsyncResult<Candlesticks>> callback) {
        RestApiInvoker.callASync(requestImpl.getKline(symbol, interval, startTime, endTime, limit), callback);
    }

    @Override
    public void getCandlestickBars(String symbol, KlineInterval interval, ResponseCallback<AsyncResult<Candlesticks>> callback) {
        Long startTime = 1564070400000L;
        Long endTime = System.currentTimeMillis();
        Integer limit = 10;
        InputChecker.checker()
                .checkSymbol(symbol)
                .checkRange(limit.longValue(), 1, 1500, "limit")
                .checkRange(startTime, 1564070400000L, System.currentTimeMillis() , "startTime")
                .checkRange(endTime, 1564070400000L, System.currentTimeMillis() , "startTime")
                .shouldNotNull(interval, "KlineInterval");
        RestApiInvoker.callASync(requestImpl.getKline(symbol, interval, startTime, endTime, limit), callback);
    }

    @Override
    public void get24HrPriceStatistics(String symbol, String quoteTokenSymbol, String quoteTokenCategory, ResponseCallback<AsyncResult<List<TickerStatistics>>> callback) {
        RestApiInvoker.callASync(requestImpl.get24HTickerStatistics(symbol, quoteTokenSymbol, quoteTokenCategory), callback);
    }

    @Override
    public void getBookTicker(String symbol, ResponseCallback<AsyncResult<BookTicker>> callback) {
        RestApiInvoker.callASync(requestImpl.getBestBookTicker(symbol), callback);
    }

    @Override
    public void newOrder(NewOrderRequest order, ResponseCallback<AsyncResult<NewOrderResponse>> callback) {
        RestApiInvoker.callASync(requestImpl.placeOrder(order), callback);
    }

    @Override
    public void newOrderTest(NewOrderRequest order, ResponseCallback<AsyncResult<Void>> callback) {
        RestApiInvoker.callASync(requestImpl.placeOrderTest(order), callback);
    }

    @Override
    public void getOrderStatus(QueryOrderRequest orderStatusRequest, ResponseCallback<AsyncResult<Order>> callback) {
        RestApiInvoker.callASync(requestImpl.getOrder(orderStatusRequest.getAddress(),
                orderStatusRequest.getOrderId()), callback);
    }

    @Override
    public void cancelOrder(CancelOrderRequest cancelOrderRequest, ResponseCallback<AsyncResult<CancelOrderResponse>> callback) {
        RestApiInvoker.callASync(requestImpl.cancelOrder(cancelOrderRequest.getSymbol(),
                cancelOrderRequest.getOrderId()), callback);
    }

    @Override
    public void getAllOrders(QueryOrdersRequest orderRequest, ResponseCallback<AsyncResult<OrderList>> callback) {
        RestApiInvoker.callASync(requestImpl.getAllOrders(orderRequest), callback);
    }

    @Override
    public void cancelOrders(CancelOrdersRequest cancelOrdersRequest, ResponseCallback<AsyncResult<List<CancelOrderResponse>>> callback) {
        RestApiInvoker.callASync(requestImpl.cancelOrders(cancelOrdersRequest.getSymbol()), callback);
    }
}
