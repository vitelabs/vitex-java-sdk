package org.vite.dex.api.client.impl;

import org.vite.dex.api.client.SyncRequest;
import org.vite.dex.api.client.domain.account.CancelOrderResponse;
import org.vite.dex.api.client.domain.account.NewOrderResponse;
import org.vite.dex.api.client.domain.account.Order;
import org.vite.dex.api.client.domain.account.OrderList;
import org.vite.dex.api.client.domain.account.request.*;
import org.vite.dex.api.client.domain.enums.KlineInterval;
import org.vite.dex.api.client.domain.enums.QuoteTokenCategory;
import org.vite.dex.api.client.domain.general.MarketDetail;
import org.vite.dex.api.client.domain.general.MarketPrecision;
import org.vite.dex.api.client.domain.market.*;

import java.util.List;

public class SyncRequestClient implements SyncRequest {

    private RestApiRequestParser requestImpl;

    private String apiKey;
    private String secretKey;
    private String serverUrl;

    private SyncRequestClient() {

    }

    public static SyncRequestClient builder() {
        return new SyncRequestClient();
    }

    public SyncRequestClient apiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public SyncRequestClient secretKey(String secretKey) {
        this.secretKey = secretKey;
        return this;
    }

    public SyncRequestClient serverUrl(String serverUrl) {
        this.serverUrl = serverUrl;
        return this;
    }

    public SyncRequestClient build() {
        this.requestImpl = new RestApiRequestParser(apiKey, secretKey, serverUrl);
        return this;
    }

    @Override
    public void ping() {

    }

//    @Override
//    public Limit getLimit() {
//        return RestApiInvoker.callSync(requestImpl.getLimit());
//    }
//
//    @Override
//    public List<Token> getTokens(Integer offset, Integer limit) {
//        return getTokens(null, null, offset, limit);
//    }
//
//    @Override
//    public List<Token> getTokensByCategory(TokenCategory category, Integer offset, Integer limit) {
//        return getTokens(category, null, offset, limit);
//    }
//
//    @Override
//    public List<Token> getTokensBySymbolLike(String tokenSymbolLike, Integer offset, Integer limit) {
//        return getTokens(null, tokenSymbolLike, offset, limit);
//    }
//
//    @Override
//    public List<Token> getTokens(TokenCategory category, String tokenSymbolLike, Integer offset, Integer limit) {
//        return RestApiInvoker.callSync(requestImpl.getTokens(category, tokenSymbolLike, offset, limit));
//    }
//
//    @Override
//    public TokenDetail getTokenDetailByTokenId(String tokenId) {
//        return getTokenDetail(null, tokenId);
//    }
//
//    @Override
//    public TokenDetail getTokenDetailBySymbol(String tokenSymbol) {
//        return getTokenDetail(tokenSymbol, null);
//    }
//
//    @Override
//    public TokenDetail getTokenDetail(String tokenSymbol, String tokenId) {
//        return RestApiInvoker.callSync(requestImpl.getTokenDetail(tokenSymbol, tokenId));
//    }
//
//    @Override
//    public List<TokenMapping> getTokenMapped(String quoteTokenSymbol) {
//        return RestApiInvoker.callSync(requestImpl.getTokenMapped(quoteTokenSymbol));
//    }
//
//    @Override
//    public List<TokenMapping> getTokenUnmapped(String quoteTokenSymbol) {
//        return RestApiInvoker.callSync(requestImpl.getTokenUnmapped(quoteTokenSymbol));
//    }

    @Override
    public List<MarketPrecision> getAllMarkets( Integer offset, Integer limit) {
        return RestApiInvoker.callSync(requestImpl.getMarkets(offset, limit));
    }

    @Override
    public MarketDetail getMarketDetail( String symbol) {
        return RestApiInvoker.callSync(requestImpl.getMarketDetail(symbol));
    }

    @Override
    public BookTicker getBookTicker(String symbol) {
        return RestApiInvoker.callSync(requestImpl.getBestBookTicker(symbol));
    }
//
//    @Override
//    public List<ExchangeRate> getExchangeRateByTokenIds(String tokenIds) {
//        return getExchangeRate(tokenIds, null);
//    }
//
//    @Override
//    public List<ExchangeRate> getExchangeRate(String tokenSymbols) {
//        return getExchangeRate(null, tokenSymbols);
//    }
//
//    @Override
//    public List<ExchangeRate> getExchangeRate(String tokenIds, String tokenSymbols) {
//        return RestApiInvoker.callSync(requestImpl.getExchangeRate(tokenIds, tokenSymbols));
//    }

//    @Override
//    public Double getUsd2Cny() {
//        return RestApiInvoker.callSync(requestImpl.getUsd2Cny());
//    }

    @Override
    public Long getServerTime() {
        return RestApiInvoker.callSync(requestImpl.getServerTime());
    }
//
//    @Override
//    public List<MarketStatus> getClosedMarkets() {
//        return RestApiInvoker.callSync(requestImpl.getClosedMarkets());
//    }
//
//    @Override
//    public MarketStatus getMarketStatus(String symbol) {
//        return RestApiInvoker.callSync(requestImpl.getMarketStatus(symbol));
//    }

    @Override
    public Candlesticks getCandlestickBars(String symbol, KlineInterval interval, Integer limit, Long startTime, Long endTime) {
        return RestApiInvoker.callSync(requestImpl.getKline(symbol, interval, startTime, endTime, limit));
    }

    @Override
    public Candlesticks getCandlestickBars(String symbol, KlineInterval interval) {
        return RestApiInvoker.callSync(requestImpl.getKline(symbol, interval, null, null, null));
    }
//
//    @Override
//    public MarketKlineList getHistoricalKline(KLineRequest request) {
//        return RestApiInvoker.callSync(requestImpl.getKline(request.getSymbol(), request.getInterval(), request.getStartTime(), request.getEndTime(), request.getLimit()));
//    }

    @Override
    public OrderBook getOrderBook(String symbol, Integer limit, Integer precision) {
        return RestApiInvoker.callSync(requestImpl.getDepth(symbol, limit));
    }

    @Override
    public List<LatestTrade> getTrades(String symbol, Integer limit) {
        return RestApiInvoker.callSync(requestImpl.getHistoricalTrade(symbol, 0, limit, null, null, null, null, null));
    }
//
//    @Override
//    public TradeList getHistoricalTrade(TradeRequest request) {
//        return RestApiInvoker.callSync(requestImpl.getHistoricalTrade(request.getSymbol(), request.getOffset(), request.getLimit(), request.getOrderId(), request.getSide(), request.getStartTime(), request.getEndTime(), null == request.getTotal() ? 0 : (request.getTotal() ? 1 : 0)));
//    }
//
//    @Override
//    public TradeList getOrderTrade(String symbol, String orderId) {
//        return RestApiInvoker.callSync(requestImpl.getHistoricalTrade(symbol, null, null, orderId, null, null, null, null));
//    }
//
//    @Override
//    public DepositWithdrawList getWithdrawAndDepositHistory(String address, String tokenId, Integer offset, Integer limit) {
//        return RestApiInvoker.callSync(requestImpl.getWithdrawAndDepositHistory(address, tokenId, offset, limit));
//    }

    @Override
    public Order getOrderDetail( QueryOrderRequest queryOrderRequest) {
        return RestApiInvoker.callSync(requestImpl.getOrder(queryOrderRequest.getAddress(), queryOrderRequest.getOrderId()));
    }
//
//    @Override
//    public OrderList getOpenOrders(OpenOrderRequest request) {
//        return RestApiInvoker.callSync(requestImpl.getHistoricalOrder(request.getAddress(), request.getQuoteTokenSymbol(), request.getTradeTokenSymbol(), request.getOffset(), request.getLimit(), null, null, null, null, null == request.getNeedTotal() ? -1 : (request.getNeedTotal() ? 1 : -1)));
//    }
//
//    @Override
//    public OrderList getHistoricalOrders(HistoricalOrdersRequest request) {
//        return RestApiInvoker.callSync(requestImpl.getHistoricalOrder(request.getAddress(), request.getQuoteTokenSymbol(), request.getTradeTokenSymbol(), request.getOffset(), request.getLimit(), request.getStartTime(), request.getEndTime(), request.getSide(), request.getStatus(), null == request.getNeedTotal() ? -1 : (request.getNeedTotal() ? 1 : -1)));
//    }

    @Override
    public List<TickerStatistics> getTickers( String symbols, String quoteTokenSymbol, QuoteTokenCategory quoteTokenCategory) {
        return RestApiInvoker.callSync(requestImpl.get24HTickerStatistics(symbols, quoteTokenSymbol, null == quoteTokenCategory ? null : quoteTokenCategory.toString()));
    }

//    @Override
//    public List<TickerStatistics> getTickersBySymbols(String symbols) {
//        return getTickers(symbols, null, null);
//    }

//    @Override
//    public List<TickerStatistics> getTickersByQuoteTokenSymbol(String quoteTokenSymbol) {
//        return getTickers(null, quoteTokenSymbol, null);
//    }
//
//    @Override
//    public List<TickerStatistics> getTickersByQuoteTokenCategory(QuoteTokenCategory quoteTokenCategory) {
//        return getTickers(null, null, quoteTokenCategory);
//    }

    @Override
    public NewOrderResponse newOrder( NewOrderRequest order) {
        return RestApiInvoker.callSync(requestImpl.placeOrder(order));
    }

    @Override
    public void newOrderTest(NewOrderRequest order) {
        RestApiInvoker.callSync(requestImpl.placeOrderTest(order));
    }

    @Override
    public CancelOrderResponse cancelOrder( CancelOrderRequest cancelOrderRequest) {
        return RestApiInvoker.callSync(requestImpl.cancelOrder(cancelOrderRequest.getSymbol(), cancelOrderRequest.getOrderId()));
    }

    @Override
    public OrderList getAllOrders( QueryOrdersRequest orderRequest) {
        return RestApiInvoker.callSync(requestImpl.getAllOrders(orderRequest));
    }

    @Override
    public List<CancelOrderResponse> cancelOrders(String symbol) {
        return RestApiInvoker.callSync(requestImpl.cancelOrders(symbol));
    }
}
