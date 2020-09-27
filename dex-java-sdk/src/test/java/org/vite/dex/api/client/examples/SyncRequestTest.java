package org.vite.dex.api.client.examples;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.Test;
import org.vite.dex.api.client.DexClientFactory;
import org.vite.dex.api.client.SyncRequest;
import org.vite.dex.api.client.domain.enums.KlineInterval;
import org.vite.dex.api.client.domain.enums.OrderSide;
import org.vite.dex.api.client.domain.account.request.*;
import org.vite.dex.api.client.domain.enums.OrderStatus;

@Slf4j
@Ignore
public class SyncRequestTest {

    SyncRequest client = DexClientFactory.newRestClient()
            .apiKey("913423DE46E97751CCC734F018F09217")
            .secretKey("F6BED9F34912C0B658B58C73B6531721")
            .serverUrl("https://api.vitex.net/test/").build();

    @Test
    public void ping() {
        client.ping();
    }

    @Test
    public void getServerTime() {
        log.info("getServerTime:{}",client.getServerTime());
    }

    @Test
    public void getMarkets() {
        log.info("getMarkets:{}", JSONObject.toJSONString(client.getAllMarkets(0,100)));
    }

    @Test
    public void getMarketDetail() {
        log.info("getMarketDetail:{}",JSONObject.toJSONString(client.getMarketDetail("ETH-000_BTC-000")));
    }

    @Test
    public void getOrderBook() {
        log.info("getOrderBook:{}",client.getOrderBook("ETH-000_BTC-000",100,null));
    }

    @Test
    public void getTrades() {
        log.info("getTrades:{}",client.getTrades("ETH-000_BTC-000",10));
    }

    @Test
    public void getCandlestickBars() {
        log.info("getCandlestickBars:{}",client.getCandlestickBars("ETH-000_BTC-000", KlineInterval.Week));
    }

    @Test
    public void get24HrPriceStatistics() {
        log.info("get24HrPriceStatistics:{}",JSONObject.toJSONString(client.getTickers("ETH-000_BTC-000",null, null)));
    }

    @Test
    public void getBookTicker() {
        log.info("getBookTicker:{}",client.getBookTicker("ETH-000_BTC-000"));
    }

    @Test
    public void newOrder() {
        log.info("newOrder:{}",JSONObject.toJSONString(client.newOrder(new NewOrderRequest("ETH-000_BTC-000","0.19381","0.6", OrderSide.SELL))));
    }

    @Test
    public void newOrderTest() {
        client.newOrderTest(new NewOrderRequest("ETH-000_BTC-000","40.0","10", OrderSide.SELL));
    }

    @Test
    public void getOrderStatus() {
        log.info("getOrderDetail:{}",JSONObject.toJSONString(client.getOrderDetail(new QueryOrderRequest("vite_562d82b129541fc4ceb6cca3fec234b09dbaa3412cf248e298","a45a3b1b1bdc73b8c7238c31f933e36aec79b811532c197ba433658ee807d5cb"))));
    }

    @Test
    public void cancelOrder() {
        log.info("cancelOrder:{}",JSONObject.toJSONString(client.cancelOrder(new CancelOrderRequest("ETH-000_BTC-000","a45a3b1b1bdc73b8c7238c31f933e36aec79b811532c197ba433658ee807d5cb"))));
    }

    @Test
    public void getAllOrders() {
        log.info("getAllOrders:{}",JSONObject.toJSONString(client.getAllOrders(new QueryOrdersRequest("vite_562d82b129541fc4ceb6cca3fec234b09dbaa3412cf248e298", "ETH-000_BTC-000", OrderStatus.OPEN))));
    }

    @Test
    public void cancelOrders() {
        log.info("cancelOrders:{}",client.cancelOrders("ETH-000_BTC-000"));
    }
}
