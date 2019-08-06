package org.vite.dex.client;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vite.dex.client.bean.enums.KlineInterval;
import org.vite.dex.client.bean.enums.QuoteTokenCategory;
import org.vite.dex.client.bean.enums.TokenCategory;
import org.vite.dex.client.bean.http.HistoricalOrdersRequest;
import org.vite.dex.client.bean.http.KLineRequest;
import org.vite.dex.client.bean.http.OpenOrderRequest;
import org.vite.dex.client.bean.http.TradeRequest;
import org.vite.dex.client.bean.model.*;

import java.util.List;

@Ignore
public class SyncRequestClientTest {

    private static final Logger logger = LoggerFactory.getLogger(SyncRequestClientTest.class);

    SyncRequestClient syncRequestClient = DexClientFactory.syncRequestClient().serverUrl("https://vitex.vite.net/").build();

    @Test
    public void getLatestDepth() {
        MarketDepth marketDepth = syncRequestClient.getLatestDepth("VITE_BTC-000", 10);
        logger.info("data:{}", marketDepth);
    }

    @Test
    public void getLimit() {
        Limit limit = syncRequestClient.getLimit();
        logger.info("data:{}", limit);
    }

    @Test
    public void getTokens() {
        List<Token> tokens = syncRequestClient.getTokens(0, 100);
        logger.info("data:{}", tokens);
    }

    @Test
    public void getTokensByCategory() {
        List<Token> quoteTokens = syncRequestClient.getTokens(TokenCategory.QUOTE, null, 0, 100);
        logger.info("data:{}", quoteTokens);
    }

    @Test
    public void getTokensBySymbolLike() {
        List<Token> searchTokens = syncRequestClient.getTokens(TokenCategory.ALL, "GRIN", 0, 100);
        logger.info("data:{}", searchTokens);
    }

    @Test
    public void getTokenDetailByTokenId() {
        TokenDetail tokenDetail = syncRequestClient.getTokenDetailByTokenId("tti_289ee0569c7d3d75eac1b100");
        logger.info("data:{}", tokenDetail);
    }

    @Test
    public void getTokenDetailBySymbol() {
        TokenDetail tokenDetail = syncRequestClient.getTokenDetailBySymbol("VITE");
        logger.info("data:{}", tokenDetail);
    }

    @Test
    public void getTokenMapped() {
        List<TokenMapping> tokenMappings = syncRequestClient.getTokenMapped("VITE");
        logger.info("data:{}", tokenMappings);
    }

    @Test
    public void getTokenUnmapped() {
        List<TokenMapping> tokenMappings = syncRequestClient.getTokenUnmapped("VITE");
        logger.info("data:{}", tokenMappings);
    }

    @Test
    public void getMarkets() {
        List<Market> markets = syncRequestClient.getMarkets(0, 10);
        logger.info("data:{}", markets);
    }

    @Test
    public void getBestBookTicker() {
        BookTicker bestBookTicker = syncRequestClient.getBestBookTicker("GRIN-000_VITE");
        logger.info("data:{}", bestBookTicker);
    }

    @Test
    public void getExchangeRateByTokenIds() {
        List<ExchangeRate> exchangeRateByTokenIds = syncRequestClient.getExchangeRateByTokenIds("tti_289ee0569c7d3d75eac1b100,tti_5649544520544f4b454e6e40");
        logger.info("data:{}", exchangeRateByTokenIds);
    }

    @Test
    public void getExchangeRate() {
        List<ExchangeRate> exchangeRateByTokenIds = syncRequestClient.getExchangeRate("VITE,GRIN-000");
        logger.info("data:{}", exchangeRateByTokenIds);
    }

    @Test
    public void getUsd2Cny() {
        Double usd2Cny = syncRequestClient.getUsd2Cny();
        logger.info("data:{}", usd2Cny);
    }

    @Test
    public void getServerTime() {
        Long serverTime = syncRequestClient.getServerTime();
        logger.info("data:{}", serverTime);
    }

    @Test
    public void getClosedMarkets() {
        List<MarketStatus> marketStatuses = syncRequestClient.getClosedMarkets();
        logger.info("data:{}", marketStatuses);
    }

    @Test
    public void getMarketStatus() {
        MarketStatus marketStatus = syncRequestClient.getMarketStatus("GRIN-000_VITE");
        logger.info("data:{}", marketStatus);
    }

    @Test
    public void getLatestKline() {
        MarketKlineList minuteKline = syncRequestClient.getLatestKline("GRIN-000_VITE", KlineInterval.Minute, 10);
        logger.info("minuteKline:{}", minuteKline);

        MarketKlineList minute30Kline = syncRequestClient.getLatestKline("GRIN-000_VITE", KlineInterval.Minute30, 10);
        logger.info("minute30Kline:{}", minute30Kline);

        MarketKlineList hourKline = syncRequestClient.getLatestKline("GRIN-000_VITE", KlineInterval.Hour, 10);
        logger.info("hourKline:{}", hourKline);

        MarketKlineList hour6Kline = syncRequestClient.getLatestKline("GRIN-000_VITE", KlineInterval.Hour6, 10);
        logger.info("hour6Kline:{}", hour6Kline);

        MarketKlineList hour12Kline = syncRequestClient.getLatestKline("GRIN-000_VITE", KlineInterval.Hour12, 10);
        logger.info("hour12Kline:{}", hour12Kline);

        MarketKlineList dayKline = syncRequestClient.getLatestKline("GRIN-000_VITE", KlineInterval.Day, 10);
        logger.info("dayKline:{}", dayKline);

        MarketKlineList weekKline = syncRequestClient.getLatestKline("GRIN-000_VITE", KlineInterval.Week, 10);
        logger.info("weekKline:{}", weekKline);
    }

    @Test
    public void getHistoricalKline() {
        MarketKlineList weekKline = syncRequestClient.getHistoricalKline(new KLineRequest("GRIN-000_VITE", KlineInterval.Week, null, null, 10));
        logger.info("weekKline:{}", weekKline);
    }

    @Test
    public void getLatestTrade() {
        TradeList tradeList = syncRequestClient.getLatestTrade("GRIN-000_VITE", 10);
        logger.info("data:{}", tradeList);
    }

    @Test
    public void getHistoricalTrade() {
        TradeList tradeList = syncRequestClient.getHistoricalTrade(new TradeRequest("GRIN-000_VITE", null, null, null, null, null, null, null));
        logger.info("data:{}", tradeList);
    }

    @Test
    public void getOrderTrade() {
        TradeList tradeList = syncRequestClient.getOrderTrade("GRIN-000_VITE", "00000900ffffffffa4ffffffffff005d3be6e0000000");
        logger.info("data:{}", tradeList);
    }

    @Test
    public void getWithdrawAndDepositHistory() {
        DepositWithdrawList depositWithdrawList = syncRequestClient.getWithdrawAndDepositHistory("vite_a6f541b21ff15e0b42fc3ac37df2e38e27f4b548498e8d2c9a", "tti_5649544520544f4b454e6e40", null, null);
        logger.info("data:{}", depositWithdrawList);
    }

    @Test
    public void getOrder() {
        Order order = syncRequestClient.getOrder("vite_efeccc7f8c776b6e7fdedcf145bf560e32cef368514480b2df", "00000900ffffffffa4ffffffffff005d3e97ee000001");
        logger.info("data:{}", order);
    }

    @Test
    public void getOpenOrders() {
        OrderList order = syncRequestClient.getOpenOrders(new OpenOrderRequest("vite_efeccc7f8c776b6e7fdedcf145bf560e32cef368514480b2df", null, null, null, null, null));
        logger.info("data:{}", order);
    }

    @Test
    public void getHistoricalOrders() {
        //0:buy,1:sell
        OrderList order = syncRequestClient.getHistoricalOrders(new HistoricalOrdersRequest("vite_efeccc7f8c776b6e7fdedcf145bf560e32cef368514480b2df", null, null, null, null, null, null, null, null, null));
        logger.info("data:{}", order);
    }

    @Test
    public void getTickersByQuoteTokenCategory() {
        List<TickerStatistics> usdTickersByQuoteTokenCategory = syncRequestClient.getTickersByQuoteTokenCategory(QuoteTokenCategory.USD);
        logger.info("data:{}", usdTickersByQuoteTokenCategory);
        List<TickerStatistics> btcTickersByQuoteTokenCategory = syncRequestClient.getTickersByQuoteTokenCategory(QuoteTokenCategory.BTC);
        logger.info("data:{}", btcTickersByQuoteTokenCategory);
        List<TickerStatistics> ethTickersByQuoteTokenCategory = syncRequestClient.getTickersByQuoteTokenCategory(QuoteTokenCategory.ETH);
        logger.info("data:{}", ethTickersByQuoteTokenCategory);
        List<TickerStatistics> viteTickersByQuoteTokenCategory = syncRequestClient.getTickersByQuoteTokenCategory(QuoteTokenCategory.VITE);
        logger.info("data:{}", viteTickersByQuoteTokenCategory);
    }

    @Test
    public void getTickersByQuoteTokenSymbol() {
        List<TickerStatistics> tickersByQuoteTokenSymbol = syncRequestClient.getTickersByQuoteTokenSymbol("VITE");
        logger.info("data:{}", tickersByQuoteTokenSymbol);
    }

    @Test
    public void getTickersBySymbols() {
        List<TickerStatistics> tickersBySymbols = syncRequestClient.getTickersBySymbols("GRIN-000_VITE");
        logger.info("data:{}", tickersBySymbols);
    }

}
