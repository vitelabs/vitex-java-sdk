package org.vite.dex.api.client.examples;

import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.Test;
import org.vite.dex.api.client.DexApiClientFactory;
import org.vite.dex.api.client.DexApiRestClient;
import org.vite.dex.api.client.domain.KlineInterval;
import org.vite.dex.api.client.domain.OrderSide;
import org.vite.dex.api.client.domain.account.request.*;

@Slf4j
@Ignore
public class DexApiRestClientTest {

    DexApiClientFactory factory = DexApiClientFactory.newInstance("11111111", "22222222");
    DexApiRestClient client = factory.newRestClient();

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
        log.info("getMarkets:{}",client.getAllMarkets(null,null));
    }

    @Test
    public void getMarketDetail() {
        log.info("getMarketDetail:{}",client.getMarketDetail("VTT-000_VITE"));
    }

    @Test
    public void getOrderBook() {
        log.info("getOrderBook:{}",client.getOrderBook("VTT-000_VITE",100,null));
    }

    @Test
    public void getTrades() {
        log.info("getTrades:{}",client.getTrades("VTT-000_VITE",100));
    }

    @Test
    public void getCandlestickBars() {
        log.info("getCandlestickBars:{}",client.getCandlestickBars("VTT-000_VITE", KlineInterval.Week));
    }

    @Test
    public void get24HrPriceStatistics() {
        log.info("get24HrPriceStatistics:{}",client.get24HrPriceStatistics("VTT-000_VITE",null));
    }

    @Test
    public void getBookTicker() {
        log.info("getBookTicker:{}",client.getBookTicker("VTT-000_VITE"));
    }

    @Test
    public void newOrder() {
        log.info("newOrder:{}",client.newOrder(new NewOrderRequest("VTT-000_VITE","41.0","10", OrderSide.SELL)));
    }

    @Test
    public void newOrderTest() {
        client.newOrderTest(new NewOrderRequest("VTT-000_VITE","40.0","10", OrderSide.SELL));
    }

    @Test
    public void getOrderStatus() {
        log.info("getOrderStatus:{}",client.getOrderStatus(new QueryOrderRequest("VTT-000_VITE","d15454b89ee86fdda8e455c022d82e06cda591a6156df46f0d9df79c33745d68")));
    }

    @Test
    public void cancelOrder() {
        log.info("cancelOrder:{}",client.cancelOrder(new CancelOrderRequest("VTT-000_VITE","9b1d2e595ff776c1ee6d864177a780e9e951e96262778eb003553ef6f160212f")));
    }

    @Test
    public void getAllOrders() {
        log.info("getAllOrders:{}",client.getAllOrders(new QueryOrdersRequest("VTT-000_VITE")));
    }

    @Test
    public void cancelOrders() {
        log.info("cancelOrders:{}",client.cancelOrders(new CancelOrdersRequest("VTT-000_VITE")));
    }
}
