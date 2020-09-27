# vite-dex-sdk
### SDK Support 

|Contact Us||
|---|---|
|Telegram|https://t.me/vite_en|

### Supported language: Java
---

|list|language|comment|
|---|---|---|
|dex-java-sdk|Java|-|


### Usage
#### Sync Request
```java
SyncRequest client = DexClientFactory.newRestClient()
            .apiKey("913423DE46E97751CCC734F018F09217")
            .secretKey("F6BED9F34912C0B658B58C73B6531721")
            .serverUrl("https://api.vitex.net/test/").build();

Long serverTime = client.getServerTime();
```

#### Async Request
```java
AsyncRequestClient client = DexClientFactory.newAsyncRestClient()
            .serverUrl("https://api.vitex.net/test/").build();

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
```

#### Subscription
```java
SubscriptionClient subscriptionClient = DexClientFactory.subscriptionClient().serverUrl("wss://vitex.vite.net/websocket").build();

public void subscribeKlineEvent() {
        subscriptionClient.subscribeKlineEvent("VX_ETH-000", KlineInterval.Minute, t -> {
            logger.info("data:{}", t);
        });

        try {
            TimeUnit.HOURS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
```