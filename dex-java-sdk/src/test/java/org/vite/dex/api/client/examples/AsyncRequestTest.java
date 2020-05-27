package org.vite.dex.api.client.examples;


import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.Test;
import org.vite.dex.api.client.DexClientFactory;
import org.vite.dex.api.client.domain.enums.KlineInterval;
import org.vite.dex.api.client.domain.enums.OrderSide;
import org.vite.dex.api.client.domain.account.request.*;
import org.vite.dex.api.client.impl.AsyncRequestClient;

import java.util.concurrent.TimeUnit;

@Ignore
@Slf4j
public class AsyncRequestTest {

    AsyncRequestClient client = DexClientFactory.newAsyncRestClient()
            .serverUrl("https://api.vitex.net/test/").build();

//    @Test
//    public void ping() {
//        client.ping(t->{});
//    }

    @Test
    public void getServerTime() {
        client.getServerTime(t->{
            log.info("getServerTime:{}",t.getData());
        });

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getMarkets() {
        client.getAllMarkets(null,null,t->{
            log.info("getMarkets:{}",t);
        });
    }

    @Test
    public void getMarketDetail() {
        client.getMarketDetail("VTT-000_VITE",t->{
            log.info("getMarketDetail:{}",t);
        });
    }

    @Test
    public void getOrderBook() {
        client.getOrderBook("VTT-000_VITE",100,null,t->{
            log.info("getOrderBook:{}",t);
        });
    }

    @Test
    public void getTrades() {
        client.getTrades("VTT-000_VITE",100,t->{
            log.info("getTrades:{}",t);
        });
    }

    @Test
    public void getCandlestickBars() {
        client.getCandlestickBars("VTT-000_VITE", KlineInterval.Minute,t->{
            log.info("getCandlestickBars:{}",t);
        });
    }

    @Test
    public void get24HrPriceStatistics() {
        client.get24HrPriceStatistics("VTT-000_VITE",null, null,t->{
            log.info("get24HrPriceStatistics:{}",t);
        });
    }

    @Test
    public void getBookTicker() {
        client.getBookTicker("VTT-000_VITE",t->{
            log.info("getBookTicker:{}",t);
        });
    }

    @Test
    public void newOrder() {
        client.newOrder(new NewOrderRequest("VTT-000_VITE","41.0","10", OrderSide.SELL),t->{
            log.info("newOrder:{}",t);
        });
    }

    @Test
    public void newOrderTest() {
        client.newOrderTest(new NewOrderRequest("VTT-000_VITE","40.0","10", OrderSide.SELL),t->{

        });
    }

    @Test
    public void getOrderStatus() {
       client.getOrderStatus(new QueryOrderRequest("VTT-000_VITE","d15454b89ee86fdda8e455c022d82e06cda591a6156df46f0d9df79c33745d68"),t->{
           log.info("getOrderDetail:{}",t);
       });
    }

    @Test
    public void cancelOrder() {
        client.cancelOrder(new CancelOrderRequest("VTT-000_VITE","9b1d2e595ff776c1ee6d864177a780e9e951e96262778eb003553ef6f160212f"),t->{
            log.info("cancelOrder:{}",t);
        });
    }

    @Test
    public void getAllOrders() {
        client.getAllOrders(new QueryOrdersRequest("VTT-000_VITE"),t->{
            log.info("getAllOrders:{}",t);
        });
    }

    @Test
    public void cancelOrders() {
        client.cancelOrders(new CancelOrdersRequest("VTT-000_VITE"),t->{
            log.info("cancelOrders:{}",t);
        });
    }
}
