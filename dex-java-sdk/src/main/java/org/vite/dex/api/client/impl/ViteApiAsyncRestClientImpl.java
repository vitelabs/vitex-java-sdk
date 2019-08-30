package org.vite.dex.api.client.impl;


import org.vite.dex.api.client.ViteApiAsyncRestClient;
import org.vite.dex.api.client.ViteApiCallback;
import org.vite.dex.api.client.domain.KlineInterval;
import org.vite.dex.api.client.domain.account.CancelOrderResponse;
import org.vite.dex.api.client.domain.account.NewOrderResponse;
import org.vite.dex.api.client.domain.account.Order;
import org.vite.dex.api.client.domain.account.request.*;
import org.vite.dex.api.client.domain.general.Market;
import org.vite.dex.api.client.domain.general.MarketDetail;
import org.vite.dex.api.client.domain.market.*;

import java.util.List;

import static org.vite.dex.api.client.impl.ViteApiServiceGenerator.createService;

/**
 * Implementation of Vite's REST API using Retrofit with asynchronous/non-blocking method calls.
 */
public class ViteApiAsyncRestClientImpl implements ViteApiAsyncRestClient {

    private final ViteApiService viteApiService;

    public ViteApiAsyncRestClientImpl(String apiKey, String secret) {
        viteApiService = createService(ViteApiService.class, apiKey, secret);
    }

    // General endpoints

    @Override
    public void ping(ViteApiCallback<Void> callback) {
        viteApiService.ping().enqueue(new ViteApiCallbackAdapter<>(callback));
    }

    @Override
    public void getServerTime(ViteApiCallback<Long> callback) {
        viteApiService.getServerTime().enqueue(new ViteApiCallbackAdapter<>(callback));
    }

    @Override
    public void getMarkets(String operator, String quoteCurrency, ViteApiCallback<List<Market>> callback) {
        viteApiService.getAllMarkets(operator, quoteCurrency).enqueue(new ViteApiCallbackAdapter<>(callback));
    }

    @Override
    public void getMarketDetail(String symbol, ViteApiCallback<MarketDetail> callback) {
        viteApiService.getMarketDetail(symbol).enqueue(new ViteApiCallbackAdapter<>(callback));
    }

    // Market Data endpoints

    @Override
    public void getOrderBook(String symbol, Integer limit, Integer precision, ViteApiCallback<OrderBook> callback) {
        viteApiService.getOrderBook(symbol, limit, precision).enqueue(new ViteApiCallbackAdapter<>(callback));
    }

    @Override
    public void getTrades(String symbol, Integer limit, ViteApiCallback<List<LatestTrade>> callback) {
        viteApiService.getTrades(symbol, limit).enqueue(new ViteApiCallbackAdapter<>(callback));
    }

    @Override
    public void getCandlestickBars(String symbol, KlineInterval interval, Integer limit, Long startTime, Long endTime, ViteApiCallback<Candlesticks> callback) {
        viteApiService.getCandlestickBars(symbol, interval.toString(), limit, startTime, endTime).enqueue(new ViteApiCallbackAdapter<>(callback));
    }

    @Override
    public void get24HrPriceStatistics(String symbol, String quoteCurrency, ViteApiCallback<List<TickerStatistics>> callback) {
        viteApiService.get24HrPriceStatistics(symbol, quoteCurrency).enqueue(new ViteApiCallbackAdapter<>(callback));
    }

    @Override
    public void getBookTicker(String symbol, ViteApiCallback<BookTicker> callback) {
        viteApiService.getBookTicker(symbol).enqueue(new ViteApiCallbackAdapter<>(callback));
    }

    // Account endpoints

    @Override
    public void newOrder(NewOrderRequest order, ViteApiCallback<NewOrderResponse> callback) {
        viteApiService.newOrder(order.getSymbol(),
                order.getPrice(),
                order.getAmount(),
                order.getOrderSide().code(),
                order.getTimestamp()).enqueue(new ViteApiCallbackAdapter<>(callback));
    }

    @Override
    public void newOrderTest(NewOrderRequest order, ViteApiCallback<Void> callback) {
        viteApiService.newOrderTest(order.getSymbol(),
                order.getPrice(),
                order.getAmount(),
                order.getOrderSide().code(),
                order.getTimestamp()).enqueue(new ViteApiCallbackAdapter<>(callback));
    }

    @Override
    public void getOrderStatus(QueryOrderRequest orderStatusRequest, ViteApiCallback<Order> callback) {
        viteApiService.getOrderStatus(orderStatusRequest.getSymbol(),
                orderStatusRequest.getOrderId(),
                orderStatusRequest.getTimestamp()).enqueue(new ViteApiCallbackAdapter<>(callback));
    }


    @Override
    public void cancelOrder(CancelOrderRequest cancelOrderRequest, ViteApiCallback<CancelOrderResponse> callback) {
        viteApiService.cancelOrder(cancelOrderRequest.getSymbol(),
                cancelOrderRequest.getOrderId(),
                cancelOrderRequest.getTimestamp()).enqueue(new ViteApiCallbackAdapter<>(callback));
    }

    @Override
    public void getAllOrders(QueryOrdersRequest orderRequest, ViteApiCallback<List<Order>> callback) {
        viteApiService.getAllOrders(orderRequest.getSymbol(),
                orderRequest.getStartTime(),
                orderRequest.getEndTime(),
                null == orderRequest.getSide() ? null : orderRequest.getSide().code(),
                null == orderRequest.getStatus() ? null : orderRequest.getStatus().code(),
                orderRequest.getLimit(),
                orderRequest.getTimestamp()).enqueue(new ViteApiCallbackAdapter<>(callback));
    }

    @Override
    public void cancelOrders(CancelOrdersRequest cancelOrdersRequest, ViteApiCallback<List<CancelOrderResponse>> callback) {
        viteApiService.cancelOrders(cancelOrdersRequest.getSymbol(),
                cancelOrdersRequest.getTimestamp()).enqueue(new ViteApiCallbackAdapter<>(callback));
    }
}
