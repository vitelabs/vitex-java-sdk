package org.vite.dex.client;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vite.dex.client.bean.enums.KlineInterval;

import java.util.concurrent.TimeUnit;

@Ignore
public class SubscriptionTest {

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionTest.class);

    @Test
    public void subscribeKlineEvent() {

        DexClientFactory.subscriptionClient().serverUrl("wss://vitex.vite.net/websocket").build().subscribeKlineEvent("GRIN-000_ETH-000", KlineInterval.Minute, t -> {
            logger.info("data:{}", t);
        });

        try {
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void subscribeKlineEvent1() {

        DexClientFactory.subscriptionClient().serverUrl("wss://vitex.vite.net/websocket").build().subscribeKlineEvent("GRIN-000_ETH-000", KlineInterval.Minute, t -> {
            logger.info("data:{}", t);
        },e->{
            e.printStackTrace();
        });

        try {
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void subscribeDepthEvent() {

        DexClientFactory.subscriptionClient().serverUrl("wss://vitex.vite.net/websocket").build().subscribeDepthEvent("GRIN-000_ETH-000", t -> {
            logger.info("data:{}", t);
        });

        try {
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void subscribeDepthEvent1() {

        DexClientFactory.subscriptionClient().serverUrl("wss://vitex.vite.net/websocket").build().subscribeDepthEvent("GRIN-000_ETH-000", t -> {
            logger.info("data:{}", t);
        },e->{
            e.printStackTrace();
        });

        try {
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void subscribeTradeEvent() {

        DexClientFactory.subscriptionClient().serverUrl("wss://vitex.vite.net/websocket").build().subscribeTradeEvent("GRIN-000_ETH-000", t -> {
            logger.info("data:{}", t);
        });

        try {
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void subscribeTradeEvent1() {

        DexClientFactory.subscriptionClient().serverUrl("wss://vitex.vite.net/websocket").build().subscribeTradeEvent("GRIN-000_ETH-000", t -> {
            logger.info("data:{}", t);
        },e->{
            e.printStackTrace();
        });

        try {
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void subscribeOrderUpdateEvent() {

        DexClientFactory.subscriptionClient().serverUrl("wss://vitex.vite.net/websocket").build().subscribeOrderUpdateEvent("vite_836ba5e4d3f75b013bf33f1a19fafdcacc59eb8eb6e66d2b24", t -> {
            logger.info("data:{}", t);
        });

        try {
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void subscribeOrderUpdateEvent1() {
        DexClientFactory.subscriptionClient().serverUrl("wss://vitex.vite.net/websocket").build().subscribeOrderUpdateEvent("vite_23e03f049557d5ef24ab2625b18306419d6f238d83d3528a46", t -> {
            logger.info("data:{}", t);
        }, e -> {
            e.printStackTrace();
        });

        try {
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void subscribe24HTickerStatisticsEvent() {

        DexClientFactory.subscriptionClient().serverUrl("wss://vitex.vite.net/websocket").build().subscribe24HTickerStatisticsEvent("T-000_VITE", t -> {
            logger.info("data:{}", t);
        });

        try {
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void subscribe24HTickerStatisticsEvent1() {

        DexClientFactory.subscriptionClient().serverUrl("wss://vitex.vite.net/websocket").build().subscribe24HTickerStatisticsEvent("T-000_VITE", t -> {
            logger.info("data:{}", t);
        }, e -> {
            e.printStackTrace();
        });

        try {
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void unsubscribeAll() {

        DexClientFactory.subscriptionClient().serverUrl("wss://vitex.vite.net/websocket").build().unsubscribeAll();

        try {
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}