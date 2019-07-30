package org.vite.dex.client;


import org.vite.dex.client.bean.enums.KlineInterval;
import org.vite.dex.client.bean.enums.QuoteTokenCategory;
import org.vite.dex.client.bean.enums.TokenCategory;
import org.vite.dex.client.bean.http.HistoricalOrdersRequest;
import org.vite.dex.client.bean.http.KLineRequest;
import org.vite.dex.client.bean.http.OpenOrderRequest;
import org.vite.dex.client.bean.http.TradeRequest;
import org.vite.dex.client.bean.model.*;
import org.vite.dex.client.i.AsyncRequest;
import org.vite.dex.client.i.AsyncResult;
import org.vite.dex.client.i.ResponseCallback;

import java.util.List;

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
        this.requestImpl = new RestApiRequestParser(apiKey, secretKey, new RequestOptions(serverUrl));
        return this;
    }

    @Override
    public void getLimit(ResponseCallback<AsyncResult<Limit>> callback) {
        RestApiInvoker.callASync(requestImpl.getLimit(), callback);

    }

    @Override
    public void getTokens(Integer offset, Integer limit, ResponseCallback<AsyncResult<List<Token>>> callback) {
        getTokens(null, null, offset, limit, callback);
    }

    @Override
    public void getTokensByCategory(TokenCategory category, Integer offset, Integer limit, ResponseCallback<AsyncResult<List<Token>>> callback) {
        getTokens(category, null, offset, limit, callback);
    }

    @Override
    public void getTokensBySymbolLike(String tokenSymbolLike, Integer offset, Integer limit, ResponseCallback<AsyncResult<List<Token>>> callback) {
        getTokens(null, tokenSymbolLike, offset, limit, callback);
    }

    @Override
    public void getTokens(TokenCategory category, String tokenSymbolLike, Integer offset, Integer limit, ResponseCallback<AsyncResult<List<Token>>> callback) {
        RestApiInvoker.callASync(requestImpl.getTokens(category, tokenSymbolLike, offset, limit), callback);
    }

    @Override
    public void getTokenDetailByTokenId(String tokenId, ResponseCallback<AsyncResult<TokenDetail>> callback) {
        getTokenDetail(null, tokenId, callback);
    }

    @Override
    public void getTokenDetailBySymbol(String tokenSymbol, ResponseCallback<AsyncResult<TokenDetail>> callback) {
        getTokenDetail(tokenSymbol, null, callback);
    }

    @Override
    public void getTokenDetail(String tokenSymbol, String tokenId, ResponseCallback<AsyncResult<TokenDetail>> callback) {
        RestApiInvoker.callASync(requestImpl.getTokenDetail(tokenSymbol, tokenId), callback);
    }

    @Override
    public void getTokenMapped(String quoteTokenSymbol, ResponseCallback<AsyncResult<List<TokenMapping>>> callback) {
        RestApiInvoker.callASync(requestImpl.getTokenMapped(quoteTokenSymbol), callback);
    }

    @Override
    public void getTokenUnmapped(String quoteTokenSymbol, ResponseCallback<AsyncResult<List<TokenMapping>>> callback) {
        RestApiInvoker.callASync(requestImpl.getTokenUnmapped(quoteTokenSymbol), callback);
    }

    @Override
    public void getMarkets(Integer offset, Integer limit, ResponseCallback<AsyncResult<List<Market>>> callback) {
        RestApiInvoker.callASync(requestImpl.getMarkets(offset, limit), callback);
    }

    @Override
    public void getBestBookTicker(String symbol, ResponseCallback<AsyncResult<BookTicker>> callback) {
        RestApiInvoker.callASync(requestImpl.getBestBookTicker(symbol), callback);
    }

    @Override
    public void getExchangeRateByTokenIds(String tokenIds, ResponseCallback<AsyncResult<List<ExchangeRate>>> callback) {
        getExchangeRate(tokenIds, null, callback);
    }

    @Override
    public void getExchangeRate(String tokenSymbols, ResponseCallback<AsyncResult<List<ExchangeRate>>> callback) {
        getExchangeRate(null, tokenSymbols, callback);
    }

    @Override
    public void getExchangeRate(String tokenIds, String tokenSymbols, ResponseCallback<AsyncResult<List<ExchangeRate>>> callback) {
        RestApiInvoker.callASync(requestImpl.getExchangeRate(tokenIds, tokenSymbols), callback);
    }

    @Override
    public void getUsd2Cny(ResponseCallback<AsyncResult<Double>> callback) {
        RestApiInvoker.callASync(requestImpl.getUsd2Cny(), callback);
    }

    @Override
    public void getServerTime(ResponseCallback<AsyncResult<Long>> callback) {
        RestApiInvoker.callASync(requestImpl.getServerTime(), callback);
    }

    @Override
    public void getClosedMarkets(ResponseCallback<AsyncResult<List<MarketStatus>>> callback) {
        RestApiInvoker.callASync(requestImpl.getClosedMarkets(), callback);
    }

    @Override
    public void getMarketStatus(String symbol, ResponseCallback<AsyncResult<MarketStatus>> callback) {
        RestApiInvoker.callASync(requestImpl.getMarketStatus(symbol), callback);
    }

    @Override
    public void getLatestKline(String symbol, KlineInterval interval, int size, ResponseCallback<AsyncResult<MarketKlineList>> callback) {
        RestApiInvoker.callASync(requestImpl.getKline(symbol, interval, null, null, size), callback);
    }

    @Override
    public void getHistoricalKline(KLineRequest request, ResponseCallback<AsyncResult<MarketKlineList>> callback) {
        RestApiInvoker.callASync(requestImpl.getKline(request.getSymbol(), request.getInterval(), request.getStartTime(), request.getEndTime(), request.getLimit()), callback);
    }

    @Override
    public void getLatestDepth(String symbol, int size, ResponseCallback<AsyncResult<MarketDepth>> callback) {
        RestApiInvoker.callASync(requestImpl.getDepth(symbol, size), callback);
    }

    @Override
    public void getLatestTrade(String symbol, int size, ResponseCallback<AsyncResult<TradeList>> callback) {
        RestApiInvoker.callASync(requestImpl.getHistoricalTrade(symbol, 0, size, null, null, null, null, null), callback);
    }

    @Override
    public void getOrderTrade(String symbol, String orderId, ResponseCallback<AsyncResult<TradeList>> callback) {
        RestApiInvoker.callASync(requestImpl.getHistoricalTrade(symbol, null, null, orderId, null, null, null, null), callback);
    }

    @Override
    public void getHistoricalTrade(TradeRequest request, ResponseCallback<AsyncResult<TradeList>> callback) {
        RestApiInvoker.callASync(requestImpl.getHistoricalTrade(request.getSymbol(), request.getOffset(), request.getLimit(), request.getOrderId(), request.getSide(), request.getStartTime(), request.getEndTime(), null == request.getTotal() ? -1 : (request.getTotal() ? 1 : -1)), callback);
    }

    @Override
    public void getWithdrawAndDepositHistory(String address, String tokenId, Integer offset, Integer limit, ResponseCallback<AsyncResult<DepositWithdrawList>> callback) {
        RestApiInvoker.callASync(requestImpl.getWithdrawAndDepositHistory(address, tokenId, offset, limit), callback);
    }

    @Override
    public void getOrder(String address, String orderId, ResponseCallback<AsyncResult<Order>> callback) {
        RestApiInvoker.callASync(requestImpl.getOrder(address, orderId), callback);
    }

    @Override
    public void getOpenOrders(OpenOrderRequest request, ResponseCallback<AsyncResult<OrderList>> callback) {
        RestApiInvoker.callASync(requestImpl.getHistoricalOrder(request.getAddress(), request.getQuoteTokenSymbol(), request.getTradeTokenSymbol(), request.getOffset(), request.getLimit(), null, null, null, null, null == request.getNeedTotal() ? 0 : (request.getNeedTotal() ? 1 : 0)), callback);
    }

    @Override
    public void getHistoricalOrders(HistoricalOrdersRequest request, ResponseCallback<AsyncResult<OrderList>> callback) {
        RestApiInvoker.callASync(requestImpl.getHistoricalOrder(request.getAddress(), request.getQuoteTokenSymbol(), request.getTradeTokenSymbol(), request.getOffset(), request.getLimit(), request.getStartTime(), request.getEndTime(), request.getSide(), request.getStatus(), null == request.getNeedTotal() ? 0 : (request.getNeedTotal() ? 1 : 0)), callback);
    }

    @Override
    public void getTickers(String symbols, String quoteTokenSymbol, QuoteTokenCategory quoteTokenCategory, ResponseCallback<AsyncResult<List<TickerStatistics>>> callback) {
        RestApiInvoker.callASync(requestImpl.get24HTickerStatistics(symbols, quoteTokenSymbol, null == quoteTokenCategory ? null : quoteTokenCategory.toString()), callback);
    }

    @Override
    public void getTickersBySymbols(String symbols, ResponseCallback<AsyncResult<List<TickerStatistics>>> callback) {
        getTickers(symbols, null, null, callback);
    }

    @Override
    public void getTickersByQuoteTokenSymbol(String quoteTokenSymbol, ResponseCallback<AsyncResult<List<TickerStatistics>>> callback) {
        getTickers(null, quoteTokenSymbol, null, callback);

    }

    @Override
    public void getTickersByQuoteTokenCategory(QuoteTokenCategory quoteTokenCategory, ResponseCallback<AsyncResult<List<TickerStatistics>>> callback) {
        getTickers(null, null, quoteTokenCategory, callback);
    }


}
