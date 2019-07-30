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


public interface SyncRequest {

    /**
     * Get the limit info
     *
     * @return
     */
    Limit getLimit();

    /**
     * Get the tokens
     *
     * @param offset
     * @param limit
     * @return
     */
    List<Token> getTokens(Integer offset, Integer limit);

    /**
     * Get the tokens by category
     *
     * @param category
     * @param offset
     * @param limit
     * @return
     */
    List<Token> getTokensByCategory(TokenCategory category, Integer offset, Integer limit);

    /**
     * Get the tokens by tokenSymbolLike
     *
     * @param tokenSymbolLike
     * @param offset
     * @param limit
     * @return
     */
    List<Token> getTokensBySymbolLike(String tokenSymbolLike, Integer offset, Integer limit);

    /**
     * Get the tokens by category or symbol
     *
     * @param category
     * @param tokenSymbolLike
     * @param offset
     * @param limit
     * @return
     */
    List<Token> getTokens(TokenCategory category, String tokenSymbolLike, Integer offset, Integer limit);


    /**
     * Get the token detail info by tokenId
     *
     * @param tokenId
     * @return
     */
    TokenDetail getTokenDetailByTokenId(String tokenId);


    /**
     * Get the token detail info by symbol
     *
     * @param tokenSymbol
     * @return
     */
    TokenDetail getTokenDetailBySymbol(String tokenSymbol);


    /**
     * Get the token detail info by symbol or tokenId
     *
     * @param tokenSymbol
     * @param tokenId
     * @return
     */
    TokenDetail getTokenDetail(String tokenSymbol, String tokenId);


    /**
     * Get the mapped tokens, that has opened the trade pair
     *
     * @param quoteTokenSymbol
     * @return
     */
    List<TokenMapping> getTokenMapped(String quoteTokenSymbol);


    /**
     * Get the unmapped tokens, that hasn't opened the trade pair
     *
     * @param quoteTokenSymbol
     * @return
     */
    List<TokenMapping> getTokenUnmapped(String quoteTokenSymbol);


    /**
     * Get all market
     *
     * @param offset
     * @param limit
     * @return
     */
    List<Market> getMarkets(Integer offset, Integer limit);


    /**
     * Get the best buy price or sell price
     *
     * @param symbol
     * @return
     */
    BookTicker getBestBookTicker(String symbol);

    /**
     * Get the exchange rate by tokenIds
     *
     * @param tokenIds
     * @return
     */
    List<ExchangeRate> getExchangeRateByTokenIds(String tokenIds);


    /**
     * Get the exchange rate by tokenSymbols
     *
     * @param tokenSymbols
     * @return
     */
    List<ExchangeRate> getExchangeRate(String tokenSymbols);


    /**
     * Get the exchange rate by tokenIds or tokenSymbols
     *
     * @param tokenIds
     * @param tokenSymbols
     * @return
     */
    List<ExchangeRate> getExchangeRate(String tokenIds, String tokenSymbols);


    /**
     * Get the exchange rate of usd_cny
     *
     * @return
     */
    Double getUsd2Cny();


    /**
     * Get the server time
     *
     * @return
     */
    Long getServerTime();


    /**
     * Get the closed markets
     *
     * @return
     */
    List<MarketStatus> getClosedMarkets();


    /**
     * Get the market status
     *
     * @param symbol
     * @return
     */
    MarketStatus getMarketStatus(String symbol);

    /**
     * Get the latest kline for the specified symbol.
     *
     * @param symbol
     * @param interval
     * @param size
     * @return
     */
    MarketKlineList getLatestKline(String symbol, KlineInterval interval, int size);

    /**
     * Get the kline for the specified symbol. The data number is 150 as default.
     *
     * @param request
     * @return
     */
    MarketKlineList getHistoricalKline(KLineRequest request);

    /**
     * Get the Market Depth of a symbol
     *
     * @param symbol
     * @param size
     * @return
     */
    MarketDepth getLatestDepth(String symbol, int size);

    /**
     * Get the last trade with their price, volume and direction.
     *
     * @param symbol
     * @param size
     * @return
     */
    TradeList getLatestTrade(String symbol, int size);

    /**
     * Get the most recent trades with their price, volume and direction.
     *
     * @param request
     * @return
     */
    TradeList getHistoricalTrade(TradeRequest request);

    /**
     * Get the trade of an order
     *
     * @param symbol
     * @param orderId
     * @return
     */
    TradeList getOrderTrade(String symbol, String orderId);

    /**
     *Get the records of a address
     *
     * @param address
     * @param tokenId
     * @param offset
     * @param limit
     * @return
     */
    DepositWithdrawList getWithdrawAndDepositHistory(String address, String tokenId, Integer offset, Integer limit);

    /**
     * Get the details of an order.
     *
     * @param address
     * @param orderId
     * @return
     */
    Order getOrder(String address, String orderId);

    /**
     * Get historical orders.
     *
     * @param req The request for getting open orders.
     * @return The order list, see {@link Order}
     */
    OrderList getOpenOrders(OpenOrderRequest req);

    /**
     * Get historical orders.
     *
     * @param req The request for getting historical orders.
     * @return The order list, see {@link Order}
     */
    OrderList getHistoricalOrders(HistoricalOrdersRequest req);

    /**
     * Get Latest Tickers for All Pairs.
     *
     * @param symbols
     * @param quoteTokenSymbol
     * @param quoteTokenCategory
     * @return The statistics of all symbols
     */
    public List<TickerStatistics> getTickers(String symbols, String quoteTokenSymbol, QuoteTokenCategory quoteTokenCategory);

    /**
     * Get Latest Tickers for All Pairs.
     *
     * @param symbols
     * @return The statistics of all symbols
     */
    public List<TickerStatistics> getTickersBySymbols(String symbols);

    /**
     * Get Latest Tickers for All Pairs.
     *
     * @param quoteTokenSymbol
     * @return The statistics of all symbols
     */
    public List<TickerStatistics> getTickersByQuoteTokenSymbol(String quoteTokenSymbol);

    /**
     * Get Latest Tickers for All Pairs.
     *
     * @param quoteTokenCategory
     * @return The statistics of all symbols
     */
    public List<TickerStatistics> getTickersByQuoteTokenCategory(QuoteTokenCategory quoteTokenCategory);


}
