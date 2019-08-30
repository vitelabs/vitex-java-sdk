package org.vite.dex.api.client.impl;


import org.vite.dex.api.client.ViteApiRestClient;
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

import static org.vite.dex.api.client.impl.ViteApiServiceGenerator.createService;
import static org.vite.dex.api.client.impl.ViteApiServiceGenerator.executeSync;

/**
 * Implementation of Vite's REST API using Retrofit with synchronous/blocking method calls.
 */
public class ViteApiRestClientImpl implements ViteApiRestClient {

    private final ViteApiService viteApiService;

    public ViteApiRestClientImpl(String apiKey, String secret) {
        viteApiService = createService(ViteApiService.class, apiKey, secret);
    }

    // General endpoints

    @Override
    public void ping() {
        executeSync(viteApiService.ping());
    }

    @Override
    public Long getServerTime() {
        return executeSync(viteApiService.getServerTime());
    }

    @Override
    public List<Market> getAllMarkets(String operator, String quoteCurrency) {
        return executeSync(viteApiService.getAllMarkets(operator,quoteCurrency));
    }

    @Override
    public MarketDetail getMarketDetail(String symbol) {
        return executeSync(viteApiService.getMarketDetail(symbol));
    }

    // Market Data endpoints

    @Override
    public OrderBook getOrderBook(String symbol, Integer limit, Integer precision) {
        return executeSync(viteApiService.getOrderBook(symbol,limit,precision));
    }

    @Override
    public List<LatestTrade> getTrades(String symbol, Integer limit) {
        return executeSync(viteApiService.getTrades(symbol,limit));
    }

    @Override
    public Candlesticks getCandlestickBars(String symbol, KlineInterval interval, Integer limit, Long startTime, Long endTime) {
        startTime = startTime == null ? 1564070400000L : startTime;
        endTime = endTime == null ? System.currentTimeMillis()  : endTime;
        limit = limit == null ? 10 : limit;
        InputChecker.checker()
                .checkSymbol(symbol)
                .checkRange(limit.longValue(), 1, 1500, "limit")
                .checkRange(startTime, 1564070400000L, System.currentTimeMillis() , "startTime")
                .checkRange(endTime, 1564070400000L, System.currentTimeMillis() , "startTime")
                .shouldNotNull(interval, "KlineInterval");
        return executeSync(viteApiService.getCandlestickBars(symbol, interval.toString(), limit, startTime, endTime));
    }

    @Override
    public Candlesticks getCandlestickBars(String symbol, KlineInterval interval) {
        Long startTime = 1564070400000L;
        Long endTime = System.currentTimeMillis();
        Integer limit = 10;
        InputChecker.checker()
                .checkSymbol(symbol)
                .checkRange(limit.longValue(), 1, 1500, "limit")
                .checkRange(startTime, 1564070400000L, System.currentTimeMillis() , "startTime")
                .checkRange(endTime, 1564070400000L, System.currentTimeMillis() , "startTime")
                .shouldNotNull(interval, "KlineInterval");
        return executeSync(viteApiService.getCandlestickBars(symbol, interval.toString(), limit, startTime, endTime));
    }

    @Override
    public List<TickerStatistics> get24HrPriceStatistics(String symbol, String quoteCurrency) {
        return executeSync(viteApiService.get24HrPriceStatistics(symbol, quoteCurrency));
    }

    @Override
    public BookTicker getBookTicker(String symbol) {
        return executeSync(viteApiService.getBookTicker(symbol));
    }


    // Account endpoints

    @Override
    public NewOrderResponse newOrder(NewOrderRequest order) {
        return executeSync(viteApiService.newOrder(order.getSymbol(),
                order.getPrice(),
                order.getAmount(),
                order.getOrderSide().code(),
                order.getTimestamp()));
    }

    @Override
    public void newOrderTest(NewOrderRequest order) {
        executeSync(viteApiService.newOrderTest(order.getSymbol(),
                order.getPrice(),
                order.getAmount(),
                order.getOrderSide().code(),
                order.getTimestamp()));
    }

    @Override
    public Order getOrderStatus(QueryOrderRequest queryOrderRequest) {
        return executeSync(viteApiService.getOrderStatus(queryOrderRequest.getSymbol(),
                queryOrderRequest.getOrderId(),
                queryOrderRequest.getTimestamp()));
    }

    @Override
    public CancelOrderResponse cancelOrder(CancelOrderRequest cancelOrderRequest) {
        return executeSync(viteApiService.cancelOrder(cancelOrderRequest.getSymbol(),
                cancelOrderRequest.getOrderId(),
                cancelOrderRequest.getTimestamp()));
    }

    @Override
    public List<Order> getAllOrders(QueryOrdersRequest orderRequest) {
        return executeSync(viteApiService.getAllOrders(orderRequest.getSymbol(),
                orderRequest.getStartTime(),
                orderRequest.getEndTime(),
                null == orderRequest.getSide() ? null : orderRequest.getSide().code(),
                null == orderRequest.getStatus() ? null : orderRequest.getStatus().code(),
                orderRequest.getLimit(),
                orderRequest.getTimestamp()));
    }

    @Override
    public List<CancelOrderResponse> cancelOrders(CancelOrdersRequest cancelOrdersRequest) {
        return executeSync(viteApiService.cancelOrders(cancelOrdersRequest.getSymbol(),
                cancelOrdersRequest.getTimestamp()));
    }
}
