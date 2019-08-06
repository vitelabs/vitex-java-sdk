package org.vite.dex.client;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vite.dex.client.bean.enums.KlineInterval;
import org.vite.dex.client.bean.enums.QuoteTokenCategory;
import org.vite.dex.client.i.Subscription;

import java.util.concurrent.TimeUnit;

@Ignore
public class SubscriptionTest {

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionTest.class);

    SubscriptionClient subscriptionClient = DexClientFactory.subscriptionClient().serverUrl("wss://vitex.vite.net/websocket").build();

    @Test
    public void subscribeKlineEvent() {
        subscriptionClient.subscribeKlineEvent("GRIN-000_ETH-000", KlineInterval.Minute, t -> {
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
        subscriptionClient.subscribeKlineEvent("GRIN-000_ETH-000", KlineInterval.Minute, t -> {
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
        subscriptionClient.subscribeDepthEvent("GRIN-000_ETH-000", t -> {
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
        subscriptionClient.subscribeDepthEvent("GRIN-000_ETH-000", t -> {
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
        subscriptionClient.subscribeTradeEvent("GRIN-000_ETH-000", t -> {
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
        subscriptionClient.subscribeTradeEvent("GRIN-000_ETH-000", t -> {
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
        subscriptionClient.subscribeOrderUpdateEvent("vite_836ba5e4d3f75b013bf33f1a19fafdcacc59eb8eb6e66d2b24", t -> {
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
        subscriptionClient.subscribeOrderUpdateEvent("vite_23e03f049557d5ef24ab2625b18306419d6f238d83d3528a46", t -> {
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
        subscriptionClient.subscribe24HTickerStatisticsEvent("T-000_VITE", t -> {
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
        subscriptionClient.subscribe24HTickerStatisticsEvent("T-000_VITE", t -> {
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
    public void subscribe24HTickerStatisticsEvent2() {
        subscriptionClient.subscribe24HTickerStatisticsEvent(QuoteTokenCategory.USD, t -> {
            logger.info("data:{}", t);
        },e->{
            throw new RuntimeException("");
        });

        subscriptionClient.subscribe24HTickerStatisticsEvent(QuoteTokenCategory.ETH, t -> {
            logger.info("data:{}", t);
        });

        subscriptionClient.subscribe24HTickerStatisticsEvent(QuoteTokenCategory.BTC, t -> {
            logger.info("data:{}", t);
        });

        subscriptionClient.subscribe24HTickerStatisticsEvent(QuoteTokenCategory.VITE, t -> {
            logger.info("data:{}", t);
        });

        try {
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void unsubscribeAll() {
        subscriptionClient.unsubscribeAll();

        try {
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}