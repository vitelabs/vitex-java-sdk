package org.vite.dex.client.i;

import org.vite.dex.client.bean.enums.KlineInterval;
import org.vite.dex.client.bean.enums.QuoteTokenCategory;
import org.vite.dex.client.bean.enums.TokenCategory;
import org.vite.dex.client.bean.http.HistoricalOrdersRequest;
import org.vite.dex.client.bean.http.KLineRequest;
import org.vite.dex.client.bean.http.OpenOrderRequest;
import org.vite.dex.client.bean.http.TradeRequest;
import org.vite.dex.client.bean.model.*;

import java.util.List;

/**
 * Asynchronous request interface, invoking RestAPI via asynchronous method.
 * All methods in this interface will return immediately, do not wait the server's response.
 * So you must implement the ResponseCallback interface yourself.
 * As long as the server response received, the onResponse callback method will be called..
 */
public interface AsyncRequest {

    /**
     * Get the limit info
     *
     * @return
     */
    void getLimit(ResponseCallback<AsyncResult<Limit>> callback);

    /**
     * Get the tokens
     *
     * @param offset
     * @param limit
     * @return
     */
    void getTokens(Integer offset, Integer limit, ResponseCallback<AsyncResult<List<Token>>> callback);

    /**
     * Get the tokens by category
     *
     * @param category
     * @param offset
     * @param limit
     * @return
     */
    void getTokensByCategory(TokenCategory category, Integer offset, Integer limit, ResponseCallback<AsyncResult<List<Token>>> callback);

    /**
     * Get the tokens by tokenSymbolLike
     *
     * @param tokenSymbolLike
     * @param offset
     * @param limit
     * @return
     */
    void getTokensBySymbolLike(String tokenSymbolLike, Integer offset, Integer limit, ResponseCallback<AsyncResult<List<Token>>> callback);

    /**
     * Get the tokens by category or symbol
     *
     * @param category
     * @param tokenSymbolLike
     * @param offset
     * @param limit
     * @return
     */
    void getTokens(TokenCategory category, String tokenSymbolLike, Integer offset, Integer limit, ResponseCallback<AsyncResult<List<Token>>> callback);


    /**
     * Get the token detail info by tokenId
     *
     * @param tokenId
     * @return
     */
    void getTokenDetailByTokenId(String tokenId, ResponseCallback<AsyncResult<TokenDetail>> callback);


    /**
     * Get the token detail info by symbol
     *
     * @param tokenSymbol
     * @return
     */
    void getTokenDetailBySymbol(String tokenSymbol, ResponseCallback<AsyncResult<TokenDetail>> callback);


    /**
     * Get the token detail info by symbol or tokenId
     *
     * @param tokenSymbol
     * @param tokenId
     * @return
     */
    void getTokenDetail(String tokenSymbol, String tokenId, ResponseCallback<AsyncResult<TokenDetail>> callback);


    /**
     * Get the mapped tokens, that has opened the trade pair
     *
     * @param quoteTokenSymbol
     * @return
     */
    void getTokenMapped(String quoteTokenSymbol, ResponseCallback<AsyncResult<List<TokenMapping>>> callback);


    /**
     * Get the unmapped tokens, that hasn't opened the trade pair
     *
     * @param quoteTokenSymbol
     * @return
     */
    void getTokenUnmapped(String quoteTokenSymbol, ResponseCallback<AsyncResult<List<TokenMapping>>> callback);


    /**
     * Get all market
     *
     * @param offset
     * @param limit
     * @return
     */
    void getMarkets(Integer offset, Integer limit, ResponseCallback<AsyncResult<List<Market>>> callback);


    /**
     * Get the best buy price or sell price
     *
     * @param symbol
     * @return
     */
    void getBestBookTicker(String symbol, ResponseCallback<AsyncResult<BookTicker>> callback);

    /**
     * Get the exchange rate by tokenIds
     *
     * @param tokenIds
     * @return
     */
    void getExchangeRateByTokenIds(String tokenIds, ResponseCallback<AsyncResult<List<ExchangeRate>>> callback);


    /**
     * Get the exchange rate by tokenSymbols
     *
     * @param tokenSymbols
     * @return
     */
    void getExchangeRate(String tokenSymbols, ResponseCallback<AsyncResult<List<ExchangeRate>>> callback);


    /**
     * Get the exchange rate by tokenIds or tokenSymbols
     *
     * @param tokenIds
     * @param tokenSymbols
     * @return
     */
    void getExchangeRate(String tokenIds, String tokenSymbols, ResponseCallback<AsyncResult<List<ExchangeRate>>> callback);


    /**
     * Get the exchange rate of usd_cny
     *
     * @return
     */
    void getUsd2Cny(ResponseCallback<AsyncResult<Double>> callback);


    /**
     * Get the server time
     *
     * @return
     */
    void getServerTime(ResponseCallback<AsyncResult<Long>> callback);


    /**
     * Get the closed markets
     *
     * @return
     */
    void getClosedMarkets(ResponseCallback<AsyncResult<List<MarketStatus>>> callback);


    /**
     * Get the market status
     *
     * @param symbol
     * @return
     */
    void getMarketStatus(String symbol, ResponseCallback<AsyncResult<MarketStatus>> callback);


    /**
     * Get the latest kline for the specified symbol.
     *
     * @param symbol   The symbol, like "ABC-VITE".
     * @param interval The candlestick/kline interval, MINUTE, HOUR etc. (mandatory)
     * @param size     The maximum number of kline requested. Range [1 - 1500] (mandatory)
     * @param callback The callback you should implemented.
     */
    void getLatestKline(
            String symbol,
            KlineInterval interval,
            int size,
            ResponseCallback<AsyncResult<MarketKlineList>> callback);

    /**
     * Get the history kline for the specified symbol.
     * The data number is 150 as default.
     *
     * @param request  The request for getting kline data, see {@link KLineRequest}
     * @param callback The callback you should implemented.
     */
    void getHistoricalKline(KLineRequest request, ResponseCallback<AsyncResult<MarketKlineList>> callback);

    /**
     * Get the Market Depth of a symbol.
     *
     * @param symbol   The symbol, like "ABC-VITE". (mandatory)
     * @param size     The maximum number of Market Depth requested. range [1 - 150] (mandatory)
     * @param callback The callback you should implemented.
     */
    void getLatestDepth(String symbol, int size, ResponseCallback<AsyncResult<MarketDepth>> callback);

    /**
     * Get the last trade with their price, volume and direction.
     *
     * @param symbol   The symbol, like "ABC-VITE". (mandatory)
     * @param callback The callback you should implemented.
     */
    void getLatestTrade(String symbol, int size, ResponseCallback<AsyncResult<TradeList>> callback);

    /**
     * Get the last trade with their price, volume and direction.
     *
     * @param symbol   The symbol, like "ABC-VITE". (mandatory)
     * @param callback The callback you should implemented.
     */
    void getOrderTrade(String symbol, String orderId, ResponseCallback<AsyncResult<TradeList>> callback);

    /**
     * Get the most recent trades with their price, volume and direction.
     *
     * @param request  The request for getting kline data, see {@link TradeRequest}
     * @param callback The callback you should implemented.
     */
    void getHistoricalTrade(TradeRequest request, ResponseCallback<AsyncResult<TradeList>> callback);

    /**
     * Get the withdraw records of an account
     *
     * @param tokenId  The address, like "vite_xxxxxxxxxxxxxxx". (mandatory)
     * @param tokenId  The currency, like "tti_s". (mandatory)
     * @param offset   The beginning withdraw record id. (mandatory)
     * @param limit    The size of record. (mandatory)
     * @param callback The callback you should implemented.
     */
    void getWithdrawAndDepositHistory(
            String address,
            String tokenId,
            Integer offset,
            Integer limit,
            ResponseCallback<AsyncResult<DepositWithdrawList>> callback);

    /**
     * Get the details of an order
     *
     * @param orderId  The order id
     * @param callback The callback you should implemented.
     */
    void getOrder(String address, String orderId, ResponseCallback<AsyncResult<Order>> callback);

    /**
     * Provide open orders of a symbol for an account<br> When neither account-id nor symbol defined
     * in the request, the system will return all open orders (max 500) for all symbols and all
     * accounts of the user, sorted by order ID in descending.
     *
     * @param request  open order request
     * @param callback The callback you should implemented.
     */
    void getOpenOrders(OpenOrderRequest request, ResponseCallback<AsyncResult<OrderList>> callback);

    /**
     * Get historical orders.
     *
     * @param request  The request for getting historical orders.
     * @param callback The callback you should implemented.
     */
    void getHistoricalOrders(HistoricalOrdersRequest request, ResponseCallback<AsyncResult<OrderList>> callback);

    /**
     * Get Latest TickerStatistics for All Pairs.
     *
     * @param callback The callback you should implemented.
     */
    void getTickers(String symbols, String quoteTokenSymbol, QuoteTokenCategory quoteTokenCategory, ResponseCallback<AsyncResult<List<TickerStatistics>>> callback);

    /**
     * Get Latest TickerStatistics for All Pairs.
     *
     * @param callback The callback you should implemented.
     */
    void getTickersBySymbols(String symbols, ResponseCallback<AsyncResult<List<TickerStatistics>>> callback);


    /**
     * Get Latest TickerStatistics for All Pairs.
     *
     * @param callback The callback you should implemented.
     */
    void getTickersByQuoteTokenSymbol(String quoteTokenSymbol, ResponseCallback<AsyncResult<List<TickerStatistics>>> callback);


    /**
     * Get Latest TickerStatistics for All Pairs.
     *
     * @param callback The callback you should implemented.
     */
    void getTickersByQuoteTokenCategory(QuoteTokenCategory quoteTokenCategory, ResponseCallback<AsyncResult<List<TickerStatistics>>> callback);


}



