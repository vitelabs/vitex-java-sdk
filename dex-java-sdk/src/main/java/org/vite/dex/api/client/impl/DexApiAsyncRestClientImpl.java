package org.vite.dex.api.client.impl;


import org.vite.dex.api.client.DexApiAsyncRestClient;
import org.vite.dex.api.client.DexApiCallback;
import org.vite.dex.api.client.domain.KlineInterval;
import org.vite.dex.api.client.domain.account.CancelOrderResponse;
import org.vite.dex.api.client.domain.account.NewOrderResponse;
import org.vite.dex.api.client.domain.account.Order;
import org.vite.dex.api.client.domain.account.request.*;
import org.vite.dex.api.client.domain.general.Market;
import org.vite.dex.api.client.domain.general.MarketDetail;
import org.vite.dex.api.client.domain.market.*;
import org.vite.dex.api.client.utils.InputChecker;

import java.util.List;

import static org.vite.dex.api.client.impl.DexApiServiceGenerator.createService;

/**
 * Implementation of Vite's REST API using Retrofit with asynchronous/non-blocking method calls.
 */
public class DexApiAsyncRestClientImpl implements DexApiAsyncRestClient {

    private final DexApiService dexApiService;

    public DexApiAsyncRestClientImpl(String apiKey, String secret) {
        dexApiService = createService(DexApiService.class, apiKey, secret);
    }

    // General endpoints

    @Override
    public void ping(DexApiCallback<Void> callback) {
        dexApiService.ping().enqueue(new DexApiCallbackAdapter<>(callback));
    }

    @Override
    public void getServerTime(DexApiCallback<Long> callback) {
        dexApiService.getServerTime().enqueue(new DexApiCallbackAdapter<>(callback));
    }

    @Override
    public void getAllMarkets(String operator, String quoteCurrency, DexApiCallback<List<Market>> callback) {
        dexApiService.getAllMarkets(operator, quoteCurrency).enqueue(new DexApiCallbackAdapter<>(callback));
    }

    @Override
    public void getMarketDetail(String symbol, DexApiCallback<MarketDetail> callback) {
        dexApiService.getMarketDetail(symbol).enqueue(new DexApiCallbackAdapter<>(callback));
    }

    // Market Data endpoints

    @Override
    public void getOrderBook(String symbol, Integer limit, Integer precision, DexApiCallback<OrderBook> callback) {
        dexApiService.getOrderBook(symbol, limit, precision).enqueue(new DexApiCallbackAdapter<>(callback));
    }

    @Override
    public void getTrades(String symbol, Integer limit, DexApiCallback<List<LatestTrade>> callback) {
        dexApiService.getTrades(symbol, limit).enqueue(new DexApiCallbackAdapter<>(callback));
    }

    @Override
    public void getCandlestickBars(String symbol, KlineInterval interval, Integer limit, Long startTime, Long endTime, DexApiCallback<Candlesticks> callback) {
        dexApiService.getCandlestickBars(symbol, interval.toString(), limit, startTime, endTime).enqueue(new DexApiCallbackAdapter<>(callback));
    }

    @Override
    public void getCandlestickBars(String symbol, KlineInterval interval, DexApiCallback<Candlesticks> callback) {
        Long startTime = 1564070400000L;
        Long endTime = System.currentTimeMillis();
        Integer limit = 10;
        InputChecker.checker()
                .checkSymbol(symbol)
                .checkRange(limit.longValue(), 1, 1500, "limit")
                .checkRange(startTime, 1564070400000L, System.currentTimeMillis() , "startTime")
                .checkRange(endTime, 1564070400000L, System.currentTimeMillis() , "startTime")
                .shouldNotNull(interval, "KlineInterval");
        dexApiService.getCandlestickBars(symbol, interval.toString(), limit, startTime, endTime).enqueue(new DexApiCallbackAdapter<>(callback));
    }

    @Override
    public void get24HrPriceStatistics(String symbol, String quoteCurrency, DexApiCallback<List<TickerStatistics>> callback) {
        dexApiService.get24HrPriceStatistics(symbol, quoteCurrency).enqueue(new DexApiCallbackAdapter<>(callback));
    }

    @Override
    public void getBookTicker(String symbol, DexApiCallback<BookTicker> callback) {
        dexApiService.getBookTicker(symbol).enqueue(new DexApiCallbackAdapter<>(callback));
    }

    // Account endpoints

    @Override
    public void newOrder(NewOrderRequest order, DexApiCallback<NewOrderResponse> callback) {
        dexApiService.newOrder(order.getSymbol(),
                order.getPrice(),
                order.getAmount(),
                order.getOrderSide().code(),
                order.getTimestamp()).enqueue(new DexApiCallbackAdapter<>(callback));
    }

    @Override
    public void newOrderTest(NewOrderRequest order, DexApiCallback<Void> callback) {
        dexApiService.newOrderTest(order.getSymbol(),
                order.getPrice(),
                order.getAmount(),
                order.getOrderSide().code(),
                order.getTimestamp()).enqueue(new DexApiCallbackAdapter<>(callback));
    }

    @Override
    public void getOrderStatus(QueryOrderRequest orderStatusRequest, DexApiCallback<Order> callback) {
        dexApiService.getOrderStatus(orderStatusRequest.getSymbol(),
                orderStatusRequest.getOrderId(),
                orderStatusRequest.getTimestamp()).enqueue(new DexApiCallbackAdapter<>(callback));
    }


    @Override
    public void cancelOrder(CancelOrderRequest cancelOrderRequest, DexApiCallback<CancelOrderResponse> callback) {
        dexApiService.cancelOrder(cancelOrderRequest.getSymbol(),
                cancelOrderRequest.getOrderId(),
                cancelOrderRequest.getTimestamp()).enqueue(new DexApiCallbackAdapter<>(callback));
    }

    @Override
    public void getAllOrders(QueryOrdersRequest orderRequest, DexApiCallback<List<Order>> callback) {
        dexApiService.getAllOrders(orderRequest.getSymbol(),
                orderRequest.getStartTime(),
                orderRequest.getEndTime(),
                null == orderRequest.getSide() ? null : orderRequest.getSide().code(),
                null == orderRequest.getStatus() ? null : orderRequest.getStatus().code(),
                orderRequest.getLimit(),
                orderRequest.getTimestamp()).enqueue(new DexApiCallbackAdapter<>(callback));
    }

    @Override
    public void cancelOrders(CancelOrdersRequest cancelOrdersRequest, DexApiCallback<List<CancelOrderResponse>> callback) {
        dexApiService.cancelOrders(cancelOrdersRequest.getSymbol(),
                cancelOrdersRequest.getTimestamp()).enqueue(new DexApiCallbackAdapter<>(callback));
    }
}
