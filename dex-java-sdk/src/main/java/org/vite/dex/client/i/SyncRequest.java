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
     * Get minimum order quantity for all 4 markets
     *
     * @return See {@link Limit}
     */
    Limit getLimit();

    /**
     * Get the tokens
     *
     * @param offset Starting with 0 . Default 0
     * @param limit  Default 500 . Max 500
     * @return The list of token data, see {@link Token}
     */
    List<Token> getTokens(Integer offset, Integer limit);

    /**
     * Get the tokens by category
     *
     * @param category  Default all . Allowed value: [ quote , all ]
     * @param offset Starting with 0 . Default 0
     * @param limit Default 500 . Max 500
     * @return The list of token data, see {@link Token}
     */
    List<Token> getTokensByCategory(TokenCategory category, Integer offset, Integer limit);

    /**
     * Get the tokens by tokenSymbolLike
     *
     * @param tokenSymbolLike Token symbol. Example: ETH
     * @param offset Starting with 0 . Default 0
     * @param limit Default 500 . Max 500
     * @return The list of token data, see {@link Token}
     */
    List<Token> getTokensBySymbolLike(String tokenSymbolLike, Integer offset, Integer limit);

    /**
     * Get the tokens by category or symbol
     *
     * @param category  Default all . Allowed value: [ quote , all ]
     * @param tokenSymbolLike Token symbol. Example: ETH
     * @param offset Starting with 0 . Default 0
     * @param limit Default 500 . Max 500
     * @return The list of token data, see {@link Token}
     */
    List<Token> getTokens(TokenCategory category, String tokenSymbolLike, Integer offset, Integer limit);


    /**
     * Get the token detail info by tokenId
     *
     * @param tokenId Token Id. Example: tti_5649544520544f4b454e6e40
     * @return
     */
    TokenDetail getTokenDetailByTokenId(String tokenId);


    /**
     * Get the token detail info by symbol
     *
     * @param tokenSymbol Token symbol. Example: VITE
     * @return
     */
    TokenDetail getTokenDetailBySymbol(String tokenSymbol);


    /**
     * Get the token detail info by symbol or tokenId
     *
     * @param tokenSymbol Token symbol. Example: VITE
     * @param tokenId Token Id. Example: tti_5649544520544f4b454e6e40
     * @return
     */
    TokenDetail getTokenDetail(String tokenSymbol, String tokenId);


    /**
     * Get the mapped tokens, that has opened the trade pair
     *
     * @param quoteTokenSymbol Token symbol. Example: VITE
     * @return
     */
    List<TokenMapping> getTokenMapped(String quoteTokenSymbol);


    /**
     * Get the unmapped tokens, that hasn't opened the trade pair
     *
     * @param quoteTokenSymbol Token symbol. Example: VITE
     * @return
     */
    List<TokenMapping> getTokenUnmapped(String quoteTokenSymbol);


    /**
     * Get all market
     *
     * @param offset Starting with 0 . Default 0
     * @param limit Default 500 . Max 500
     * @return
     */
    List<Market> getMarkets(Integer offset, Integer limit);


    /**
     * Get the best buy price or sell price
     *
     * @param symbol Market pair. Example: BTC-000_VITE
     * @return
     */
    BookTicker getBestBookTicker(String symbol);

    /**
     * Get the exchange rate by tokenIds
     *
     * @param tokenIds Token ids split by `,` . Example: tti_5649544520544f4b454e6e40,tti_5649544520544f4b454e6e40
     * @return
     */
    List<ExchangeRate> getExchangeRateByTokenIds(String tokenIds);


    /**
     * Get the exchange rate by tokenSymbols
     *
     * @param tokenSymbols Token symbols split by `,` . Example: VITE, ETH
     * @return
     */
    List<ExchangeRate> getExchangeRate(String tokenSymbols);


    /**
     * Get the exchange rate by tokenIds or tokenSymbols
     *
     * @param tokenIds Token ids split by `,` . Example: tti_5649544520544f4b454e6e40,tti_5649544520544f4b454e6e40
     * @param tokenSymbols Token symbols split by `,` . Example: VITE, ETH
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
     * @param symbol   Market pair. Example: BTC-000_VITE
     * @param interval Interval. Allowed value: [ minute 、 hour 、 day 、 minute30 、 hour6 、 hour12 、 week ]
     * @param size     The maximum number of kline requested. Range [1 - 1500] (mandatory)
     * @return
     */
    MarketKlineList getLatestKline(String symbol, KlineInterval interval, int size);

    /**
     * Get the kline for the specified symbol. The data number is 150 as default.
     *
     * @param request  The request for getting kline data, see {@link KLineRequest}
     * @return
     */
    MarketKlineList getHistoricalKline(KLineRequest request);

    /**
     * Get the Market Depth of a symbol
     *
     * @param symbol    Market pair. Example: BTC-000_VITE
     * @param size     The maximum number of Market Depth requested. range [1 - 150] (mandatory)
     * @return
     */
    MarketDepth getLatestDepth(String symbol, int size);

    /**
     * Get the last trade with their price, volume and direction.
     *
     * @param symbol    Market pair. Example: BTC-000_VITE
     * @param size      Default 100.
     * @return
     */
    TradeList getLatestTrade(String symbol, int size);

    /**
     * Get the most recent trades with their price, volume and direction.
     *
     * @param request  The request for getting kline data, see {@link TradeRequest}
     * @return
     */
    TradeList getHistoricalTrade(TradeRequest request);

    /**
     * Get the trade of an order
     *
     * @param symbol    Market pair. Example: BTC-000_VITE
     * @param orderId  The order Id.
     * @return
     */
    TradeList getOrderTrade(String symbol, String orderId);

    /**
     *Get the records of a address
     *
     * @param address  The buyer/seller address
     * @param tokenId  Token Id. Example: tti_5649544520544f4b454e6e40
     * @param offset Starting with 0 . Default 0
     * @param limit Default 100 . Max 500
     * @return
     */
    DepositWithdrawList getWithdrawAndDepositHistory(String address, String tokenId, Integer offset, Integer limit);

    /**
     * Get the details of an order.
     *
     * @param address  The buyer/seller address
     * @param orderId  The order id
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
     * @param symbols Market pair split by `,` . Example: ABC-000_VITE, ABC-001_VITE
     * @param quoteTokenSymbol Token symbol. Example: VITE
     * @param quoteTokenCategory The category of quote token. Allowed value: [ VITE , ETH , BTC , USDT ]
     * @return The statistics of all symbols
     */
    public List<TickerStatistics> getTickers(String symbols, String quoteTokenSymbol, QuoteTokenCategory quoteTokenCategory);

    /**
     * Get Latest Tickers for All Pairs.
     *
     * @param symbols Market pair split by `,` . Example: ABC-000_VITE, ABC-001_VITE
     * @return The statistics of all symbols
     */
    public List<TickerStatistics> getTickersBySymbols(String symbols);

    /**
     * Get Latest Tickers for All Pairs.
     *
     * @param quoteTokenSymbol Token symbol. Example: VITE
     * @return The statistics of all symbols
     */
    public List<TickerStatistics> getTickersByQuoteTokenSymbol(String quoteTokenSymbol);

    /**
     * Get Latest Tickers for All Pairs.
     *
     * @param quoteTokenCategory The category of quote token. Allowed value: [ VITE , ETH , BTC , USDT ]
     * @return The statistics of all symbols
     */
    public List<TickerStatistics> getTickersByQuoteTokenCategory(QuoteTokenCategory quoteTokenCategory);


}
