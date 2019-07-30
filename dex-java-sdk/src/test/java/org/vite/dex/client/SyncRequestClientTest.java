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

    @Test
    public void getLatestDepth() {
        MarketDepth marketDepth = DexClientFactory.syncRequestClient().serverUrl("https://vitex.vite.net/").build().getLatestDepth("VITE_BTC-000", 10);
        logger.info("data:{}", marketDepth);
    }

    @Test
    public void getLimit() {
        Limit limit = DexClientFactory.syncRequestClient().serverUrl("https://vitex.vite.net/").build().getLimit();
        logger.info("data:{}", limit);
    }

    @Test
    public void getTokens() {

        //获取所有币种
        List<Token> tokens = DexClientFactory.syncRequestClient().serverUrl("https://vitex.vite.net/").build().getTokens(0, 100);
        logger.info("data:{}", tokens);
    }

    @Test
    public void getTokensByCategory() {

        //获取计价币种
        List<Token> quoteTokens = DexClientFactory.syncRequestClient().serverUrl("https://vitex.vite.net/").build().getTokens(TokenCategory.QUOTE, null, 0, 100);
        logger.info("data:{}", quoteTokens);
    }

    @Test
    public void getTokensBySymbolLike() {

        //搜索币种
        List<Token> searchTokens = DexClientFactory.syncRequestClient().serverUrl("https://vitex.vite.net/").build().getTokens(TokenCategory.ALL, "GRIN", 0, 100);
        logger.info("data:{}", searchTokens);
    }

    @Test
    public void getTokenDetailByTokenId() {

        TokenDetail tokenDetail = DexClientFactory.syncRequestClient().serverUrl("https://vitex.vite.net/").build().getTokenDetailByTokenId("tti_289ee0569c7d3d75eac1b100");
        logger.info("data:{}", tokenDetail);
    }

    @Test
    public void getTokenDetailBySymbol() {
        TokenDetail tokenDetail = DexClientFactory.syncRequestClient().serverUrl("https://vitex.vite.net/").build().getTokenDetailBySymbol("VITE");
        logger.info("data:{}", tokenDetail);
    }

    @Test
    public void getTokenMapped() {
        //已开通交易对
        List<TokenMapping> tokenMappings = DexClientFactory.syncRequestClient().serverUrl("https://vitex.vite.net/").build().getTokenMapped("VITE");
        logger.info("data:{}", tokenMappings);
    }

    @Test
    public void getTokenUnmapped() {
        //未开通交易对
        List<TokenMapping> tokenMappings = DexClientFactory.syncRequestClient().serverUrl("https://vitex.vite.net/").build().getTokenUnmapped("VITE");
        logger.info("data:{}", tokenMappings);
    }

    @Test
    public void getMarkets() {
        //获取所有市场
        List<Market> markets = DexClientFactory.syncRequestClient().serverUrl("https://vitex.vite.net/").build().getMarkets(0, 10);
        logger.info("data:{}", markets);
    }

    @Test
    public void getBestBookTicker() {
        BookTicker bestBookTicker = DexClientFactory.syncRequestClient().serverUrl("https://vitex.vite.net/").build().getBestBookTicker("GRIN-000_VITE");
        logger.info("data:{}", bestBookTicker);
    }

    @Test
    public void getExchangeRateByTokenIds() {
        //获取token汇率
        List<ExchangeRate> exchangeRateByTokenIds = DexClientFactory.syncRequestClient().serverUrl("https://vitex.vite.net/").build().getExchangeRateByTokenIds("tti_289ee0569c7d3d75eac1b100,tti_5649544520544f4b454e6e40");
        logger.info("data:{}", exchangeRateByTokenIds);
    }

    @Test
    public void getExchangeRate() {
        //获取token汇率
        List<ExchangeRate> exchangeRateByTokenIds = DexClientFactory.syncRequestClient().serverUrl("https://vitex.vite.net/").build().getExchangeRate("VITE,GRIN-000");
        logger.info("data:{}", exchangeRateByTokenIds);
    }

    @Test
    public void getUsd2Cny() {
        //人民币对美元汇率
        Double usd2Cny = DexClientFactory.syncRequestClient().serverUrl("https://vitex.vite.net/").build().getUsd2Cny();
        logger.info("data:{}", usd2Cny);
    }

    @Test
    public void getServerTime() {
        //获取API服务器时间
        Long serverTime = DexClientFactory.syncRequestClient().serverUrl("https://vitex.vite.net/").build().getServerTime();
        logger.info("data:{}", serverTime);
    }

    @Test
    public void getClosedMarkets() {
        //获取已关闭的市场
        List<MarketStatus> marketStatuses = DexClientFactory.syncRequestClient().serverUrl("https://vitex.vite.net/").build().getClosedMarkets();
        logger.info("data:{}", marketStatuses);
    }

    @Test
    public void getMarketStatus() {
        //获取市场状态
        MarketStatus marketStatus = DexClientFactory.syncRequestClient().serverUrl("https://vitex.vite.net/").build().getMarketStatus("GRIN-000_VITE");
        logger.info("data:{}", marketStatus);
    }

    @Test
    public void getLatestKline() {
        //获取最新的kline
        MarketKlineList minuteKline = DexClientFactory.syncRequestClient().serverUrl("https://vitex.vite.net/").build().getLatestKline("GRIN-000_VITE", KlineInterval.Minute, 10);
        logger.info("minuteKline:{}", minuteKline);

        //获取最新的kline
        MarketKlineList minute30Kline = DexClientFactory.syncRequestClient().serverUrl("https://vitex.vite.net/").build().getLatestKline("GRIN-000_VITE", KlineInterval.Minute30, 10);
        logger.info("minute30Kline:{}", minute30Kline);

        //获取最新的kline
        MarketKlineList hourKline = DexClientFactory.syncRequestClient().serverUrl("https://vitex.vite.net/").build().getLatestKline("GRIN-000_VITE", KlineInterval.Hour, 10);
        logger.info("hourKline:{}", hourKline);

        //获取最新的kline
        MarketKlineList hour6Kline = DexClientFactory.syncRequestClient().serverUrl("https://vitex.vite.net/").build().getLatestKline("GRIN-000_VITE", KlineInterval.Hour6, 10);
        logger.info("hour6Kline:{}", hour6Kline);

        //获取最新的kline
        MarketKlineList hour12Kline = DexClientFactory.syncRequestClient().serverUrl("https://vitex.vite.net/").build().getLatestKline("GRIN-000_VITE", KlineInterval.Hour12, 10);
        logger.info("hour12Kline:{}", hour12Kline);

        //获取最新的kline
        MarketKlineList dayKline = DexClientFactory.syncRequestClient().serverUrl("https://vitex.vite.net/").build().getLatestKline("GRIN-000_VITE", KlineInterval.Day, 10);
        logger.info("dayKline:{}", dayKline);

        //获取最新的kline
        MarketKlineList weekKline = DexClientFactory.syncRequestClient().serverUrl("https://vitex.vite.net/").build().getLatestKline("GRIN-000_VITE", KlineInterval.Week, 10);
        logger.info("weekKline:{}", weekKline);
    }

    @Test
    public void getHistoricalKline() {
        MarketKlineList weekKline = DexClientFactory.syncRequestClient().serverUrl("https://vitex.vite.net/").build().getHistoricalKline(new KLineRequest("GRIN-000_VITE", KlineInterval.Week, null, null, 10));
        logger.info("weekKline:{}", weekKline);
    }

    @Test
    public void getLatestTrade() {
        TradeList tradeList = DexClientFactory.syncRequestClient().serverUrl("https://vitex.vite.net/").build().getLatestTrade("GRIN-000_VITE", 10);
        logger.info("data:{}", tradeList);
    }

    @Test
    public void getHistoricalTrade() {
        //历史成交记录
        TradeList tradeList = DexClientFactory.syncRequestClient().serverUrl("https://vitex.vite.net/").build().getHistoricalTrade(new TradeRequest("GRIN-000_VITE", null, null, null, null, null, null, null));
        logger.info("data:{}", tradeList);
    }

    @Test
    public void getOrderTrade() {
        TradeList tradeList = DexClientFactory.syncRequestClient().serverUrl("https://vitex.vite.net/").build().getOrderTrade("GRIN-000_VITE", "00000900ffffffffa4ffffffffff005d3be6e0000000");
        logger.info("data:{}", tradeList);
    }

    @Test
    public void getWithdrawAndDepositHistory() {
        DepositWithdrawList depositWithdrawList = DexClientFactory.syncRequestClient().serverUrl("https://vitex.vite.net/").build().getWithdrawAndDepositHistory("vite_a6f541b21ff15e0b42fc3ac37df2e38e27f4b548498e8d2c9a", "tti_5649544520544f4b454e6e40", null, null);
        logger.info("data:{}", depositWithdrawList);
    }

    @Test
    public void getOrder() {
        Order order = DexClientFactory.syncRequestClient().serverUrl("https://vitex.vite.net/").build().getOrder("vite_efeccc7f8c776b6e7fdedcf145bf560e32cef368514480b2df", "00000900ffffffffa4ffffffffff005d3e97ee000001");
        logger.info("data:{}", order);
    }

    @Test
    public void getOpenOrders() {
        OrderList order = DexClientFactory.syncRequestClient().serverUrl("https://vitex.vite.net/").build().getOpenOrders(new OpenOrderRequest("vite_efeccc7f8c776b6e7fdedcf145bf560e32cef368514480b2df", null, null, null, null, null));
        logger.info("data:{}", order);
    }

    @Test
    public void getHistoricalOrders() {
        //0:buy,1:sell
        OrderList order = DexClientFactory.syncRequestClient().serverUrl("https://vitex.vite.net/").build().getHistoricalOrders(new HistoricalOrdersRequest("vite_efeccc7f8c776b6e7fdedcf145bf560e32cef368514480b2df", null, null, null, null, null, null, null, null, null));
        logger.info("data:{}", order);
    }

    @Test
    public void getTickersByQuoteTokenCategory() {
        List<TickerStatistics> tickersByQuoteTokenCategory = DexClientFactory.syncRequestClient().serverUrl("https://vitex.vite.net/").build().getTickersByQuoteTokenCategory(QuoteTokenCategory.BTC);
        logger.info("data:{}", tickersByQuoteTokenCategory);
    }

    @Test
    public void getTickersByQuoteTokenSymbol() {
        List<TickerStatistics> tickersByQuoteTokenSymbol = DexClientFactory.syncRequestClient().serverUrl("https://vitex.vite.net/").build().getTickersByQuoteTokenSymbol("VITE");
        logger.info("data:{}", tickersByQuoteTokenSymbol);
    }

    @Test
    public void getTickersBySymbols() {
        List<TickerStatistics> tickersBySymbols = DexClientFactory.syncRequestClient().serverUrl("https://vitex.vite.net/").build().getTickersBySymbols("GRIN-000_VITE");
        logger.info("data:{}", tickersBySymbols);
    }

}
