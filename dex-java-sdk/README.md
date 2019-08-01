# Dex Java SDK (beta version)

This is Dex Java SDK, This is a lightweight Java library, you can import to your Java project and use this SDK to query all market data, trading.

The SDK supports both synchronous and asynchronous RESTful API invoking, and subscribe the market data from the Websocket connection.

### Dependent on maven for internal use only (Users can upload nexus to use)
```
<dependency>
   <groupId>org.vite.sdk</groupId>
   <artifactId>dex-java-sdk</artifactId>
   <version>1.0.0-SNAPSHOT</version>
</dependency>
```

## Table of Contents

- [Beginning](#Beginning)
  - [Installation](#Installation)
  - [Quick Start](#Quick-Start)
  - [Request vs. Subscription](#Request-vs.-Subscription)
  - [Clients](#Clients)
  - [Create client](#Create-client)
- [Usage](#Usage)
  - [URL Options](#URL-Options)
  - [Synchronous](#Synchronous)
  - [Asynchronous](#Asynchronous)
  - [Subscription](#Subscription)
  - [NodeRpcClient](#NodeRpcClient)

- [Request example](#Request-example)

  - [Reference data](#Reference-data)
    - [Dex Server Timestamp](#Dex-Server-Timestamp)
    - [Token data](#Token-data)
    - [ExchangeRate data](#ExchangeRate-data)
  - [Market data](#Market-data)
    - [KLine](#KLine)
    - [Depth](#Depth)
    - [Latest trade](#Latest-trade)
    - [Best bid/ask](#best-bid/ask)
    - [Historical](#historical)
    - [24H Statistics](#24h-statistics)
  - [Wallet](#wallet)
    - [Withdraw and deposit history](#Withdraw-and-deposit-history)
  - [Trading](#trading)
    - [Create order](#create-order)
    - [Cancel order](#cancel-order)
    - [Get order info](#get-order-info)
    - [Opened orders](#Opened-orders)
    - [Historical orders](#Historical-orders)
- [Subscription example](#Subscription-example)
  - [Subscribe trade update](#Subscribe-trade-update)
  - [Subscribe KLine update](#Subscribe-KLine-update)
  - [Subscribe order update](#subscribe-order-update)
  - [Subscribe 24h Statistics](#Subscribe-24h-Statistics)
  - [Unsubscribe](#unsubscribe)
 
## Beginning

### Installation

*The SDK is compiled by Java8*

For Beta version, please import the source code in java IDE (idea or eclipse)

The example code is in `/vite-dex-sdk/dex-java-sdk/java/src/test/java/org/vite/dex/client/SyncRequestClientTest.java`.


### Quick Start

In your Java project, you can follow below steps:

* Create the client instance.
* Call the interfaces provided by client.

```java
SyncRequestClient syncClient = DexClientFactory.syncRequestClient()
                                               .serverUrl("https://vitex.vite.net/beta")
                                               .build();
        
// Get the latest VITE_BTC-000‘s kline data and print the highest price on console
MarketKlineList minuteKline = syncRequestClient.getLatestKline("GRIN-000_VITE", KlineInterval.Minute, 10);

```

Please NOTE:

All timestamp which is got from SDK is the Unix timestamp based on UTC.


### Request vs. Subscription

Dex Sdk supports 2 types of invoking.

1. Dex Server method: You can use it to get the market related data from Dex server , You also can subscribe the market updated data from Dex server.
2. Vite Node method:  You can use it to trade. You can also use it to get the orderId from Node server.


We recommend developers to use Vite Node request method to trade , to use Dex Server method to access the market related data.


### Clients

There are 3 clients for Dex Server method, ```SyncRequestClient``` 、 ```AsyncRequestClient``` 、 ```SubscriptionClient``` . One client for Vite Node method,  ```NodeRpcClient```.

* **SyncRequestClient**: It is a synchronous request, it will invoke the Dex Server API via synchronous method, all invoking will be blocked until receiving the response from server.

* **AsyncRequestClient**: It is an asynchronous request, it will invoke the  Dex Server API via asynchronous method, all invoking will return immediately, instead of waiting the server's response. So you must implement the ```onResponse()``` method in```RequestCallback``` interface. As long as receiving the response of the server, callback method you defined will be called. You can use the lambda expression to simplify the implementation, see [Asynchronous usage](#Asynchronous) for detail. 

* **SubscriptionClient**: It is the subscription, it is used for subscribing any market data update.  It is asynchronous, so you must implement ```onUpdate()``` method in  ```SubscriptionListener``` interface. The server will push any update for the client. if client receive the update, the ```onUpdate()``` method will be called. You can use the lambda expression to simplify the implementation, see [Subscription usage](#Subscription) for detail. 

* **NodeRpcClient**: It is a synchronous request, it will invoke the Vite Node Rpc API via synchronous method, all invoking will be blocked until receiving the response from server. 
  
### Create client

```java

SyncRequestClient syncRequestClient = DexClientFactory.syncRequestClient()
                                                      .serverUrl(DEX_SERVER_URL)
                                                      .build();

AsyncRequestClient asyncRequestClient = DexClientFactory.asyncRequestClient()
                                                        .serverUrl(DEX_SERVER_URL)
                                                        .build();

SubscriptionClient subscriptionClient = DexClientFactory.subscriptionClient()
                                                        .serverUrl(DEX_SERVER_SUBSCRIPTION_SERVER_URL)
                                                        .build();

Key key = WalletClient.getKeyPairFromMnemonics(mnemonic, index);
NodeRpcClient rpcClient = DexClientFactory.nodeRpcClient()
                                          .key(key)
                                          .runPow(true)
                                          .serverUrl(VITE_NODE_SERVEL_URL)
                                          .build();
```


## Usage

### URL Options

* For Dex Server test request: `https://vitex.vite.net/beta`
* For Dex Server online request: `https://vitex.vite.net/`
* For Dex Server test subscription: `wss://vitex.vite.net/beta/websocket`
* For Dex Server online subscription: `wss://vitex.vite.net/websocket`
* For Vite Node test request: `TODO`
* For Vite Node online request: `TODO`

### Synchronous

To invoke the interface by synchronous, you can create the ```SyncRequestClient``` by calling ```DexClientFactory.syncRequestClient().serverUrl(DEX_SERVER_URL).build()```, and call the API directly.

```java
SyncRequestClient syncRequestClient = DexClientFactory.syncRequestClient()
                                                      .serverUrl("https://vitex.vite.net/")
                                                      .build();

// Get the best bid and ask for GRIN-000_VITE
BookTicker bestBookTicker = syncRequestClient.getBestBookTicker("GRIN-000_VITE");
```


### Asynchronous

To invoke the interface by asynchronous, you can create the ```AsyncRequestClient``` by calling ```DexClientFactory.asyncRequestClient().serverUrl(DEX_SERVER_URL).build()```, and call the API which  implement the callback by yourself. You will get a resultset after the asynchronous invoking completed, call ```succeeded()``` to check whether the invoking succeeded or not, then call ```getData()``` to get the server's response data.

```java
AsyncRequestClient asyncRequestClient = DexClientFactory.asyncRequestClient()
                                                        .serverUrl("https://vitex.vite.net/test")
                                                        .build();

// Get the price depth for VTT-000_VITE, print bid price and ask price in first level.
asyncRequestClient.getLatestDepth("VTT-000_VITE", 10, t -> {
     logger.info("success:{},data:{}", t.succeeded(), t.getData());
});

try {
    TimeUnit.SECONDS.sleep(30);
} catch (InterruptedException e) {
    e.printStackTrace();
}

```



### Subscription

To receive the subscribed data, you can create the ```SubscriptionClient``` by calling ```DexClientFactory.subscriptionClient().serverUrl(DEX_SERVER_SUBSCRIPTION_SERVER_URL).build()```. When subscribing the event, you should define your listener. See below example:

```java
SubscriptionClient subscriptionClient = DexClientFactory.subscriptionClient()
                                                        .serverUrl("wss://vitex.vite.net/websocket")
                                                        .build();

// Subscribe the trade update for GRIN-000_ETH-000.
subscriptionClient.subscribeTradeEvent("GRIN-000_ETH-000", t -> {
     logger.info("data:{}", t);
});

try {
    TimeUnit.HOURS.sleep(1);
} catch (InterruptedException e) {
    e.printStackTrace();
}
```

The subscription method supports multi-symbol string. Each symbol should be separated by a comma.

```java
subscriptionClient.subscribeTradeEvent("GRIN-000_ETH-000,GRIN-000_VITE", (tradeEvent) -> {
  ......
});
```

### NodeRpcClient

To invoke the interface by synchronous, you can create the ```NodeRpcClient``` by calling ```DexClientFactory.nodeRpcClient().runPow(true).key(key).serverUrl(VITE_NODE_SERVEL_URL).build()```, and call the API directly.

```java
Key key = WalletClient.getKeyPairFromMnemonics("", 0);
NodeRpcClient rpcClient = DexClientFactory.nodeRpcClient()
                                          .key(key)
                                          .runPow(true)
                                          .serverUrl("xxxx")
                                          .build();

SyncRequestClient syncRequestClient = DexClientFactory.syncRequestClient()
                                                      .serverUrl("https://vitex.vite.net/")
                                                      .build();

// cancle the order  on console.
String cancelHash = rpcClient.cancelOrder("00000400fffffffff5ffffffffff005d3e9f49000444");
logger.info("cancelHash:{}", cancelHash);
```

## Request example

### Reference data

#### Dex Server Timestamp

```java
//Synchronous
Long serverTime = syncRequestClient.getServerTime();
logger.info("data:{}", serverTime);
```

```java
//Asynchronous
asyncRequestClient.getServerTime(t -> {
    logger.info("success:{},data:{}", t.succeeded(), t.getData());
});
```

#### Token data

```java
//Synchronous
List<Token> tokens = syncRequestClient.getTokens(0, 100);
logger.info("data:{}", tokens);
```

```java
//Asynchronous
asyncRequestClient.getTokens(0, 100, t -> {
    logger.info("success:{},data:{}", t.succeeded(), t.getData());
});
```

#### ExchangeRate data

```java
//Synchronous
List<ExchangeRate> exchangeRateByTokenIds = syncRequestClient.getExchangeRate("VITE,GRIN-000");
logger.info("data:{}", exchangeRateByTokenIds);
```

```java
//Asynchronous
asyncRequestClient.getExchangeRate("VITE,GRIN-000", t -> {
    logger.info("success:{},data:{}", t.succeeded(), t.getData());
});
```

### Market data

#### Markets

```java
//Synchronous
List<Market> markets = syncRequestClient.getMarkets(0, 10);
logger.info("data:{}", markets);
```

```java
//Asynchronous
asyncRequestClient.getMarkets(0, 10, t -> {
    logger.info("success:{},data:{}", t.succeeded(), t.getData());
});
```

#### KLine

```java
//Synchronous
MarketKlineList minuteKline = syncRequestClient.getLatestKline("GRIN-000_VITE", KlineInterval.Minute, 10);
logger.info("minuteKline:{}", minuteKline);
```

```java
//Asynchronous
asyncRequestClient.getLatestKline("GRIN-000_VITE", KlineInterval.Minute, 10, t -> {
    logger.info("success:{},data:{}", t.succeeded(), t.getData());
});
```

#### Depth

```java
//Synchronous
MarketDepth marketDepth = syncRequestClient.getLatestDepth("VITE_BTC-000", 10);
logger.info("data:{}", marketDepth);
```

```java
//Asynchronous
asyncRequestClient.getLatestDepth("VTT-000_VITE", 10, t -> {
    logger.info("success:{},data:{}", t.succeeded(), t.getData());
});
```

#### Latest trade

```java
//Synchronous
TradeList tradeList = syncRequestClient.getLatestTrade("GRIN-000_VITE", 10);
logger.info("data:{}", tradeList);
```

```java
//Asynchronous
asyncRequestClient.getLatestTrade("GRIN-000_VITE", 10, t -> {
    logger.info("success:{},data:{}", t.succeeded(), t.getData());
});
```

#### Best bid/ask

```java
//Synchronous
BookTicker bestBookTicker = syncRequestClient.getBestBookTicker("GRIN-000_VITE");
logger.info("data:{}", bestBookTicker);
```

```java
//Asynchronous
asyncRequestClient.getBestBookTicker("GRIN-000_VITE", t -> {
    logger.info("success:{},data:{}", t.succeeded(), t.getData());
});
```

#### Historical

```java
//Synchronous
TradeList tradeList = syncRequestClient.getHistoricalTrade(new TradeRequest("GRIN-000_VITE", null, null, null, null, null, null, null));
logger.info("data:{}", tradeList);
```

```java
//Asynchronous
asyncRequestClient.getHistoricalTrade(new TradeRequest("GRIN-000_VITE", null, null, null, (byte) 0, null, null, null), t -> {
    logger.info("success:{},data:{}", t.succeeded(), t.getData());
});
```

#### 24H statistics

```java
//Synchronous
List<TickerStatistics> tickersByQuoteTokenCategory = syncRequestClient.getTickersByQuoteTokenCategory(QuoteTokenCategory.BTC);
logger.info("data:{}", tickersByQuoteTokenCategory);
```

```java
//Asynchronous
asyncRequestClient.getTickersByQuoteTokenCategory(QuoteTokenCategory.BTC, t -> {
    logger.info("success:{},data:{}", t.succeeded(), t.getData());
});
```

### Wallet

#### Withdraw and deposit history

```java
//Synchronous
DepositWithdrawList depositWithdrawList = syncRequestClient.getWithdrawAndDepositHistory("vite_a6f541b21ff15e0b42fc3ac37df2e38e27f4b548498e8d2c9a", "tti_5649544520544f4b454e6e40", null, null);
logger.info("data:{}", depositWithdrawList);
```

```java
//Asynchronous
asyncRequestClient.getWithdrawAndDepositHistory("vite_a6f541b21ff15e0b42fc3ac37df2e38e27f4b548498e8d2c9a", "tti_5649544520544f4b454e6e40", null, null, t -> {
    logger.info("success:{},data:{}", t.succeeded(), t.getData());
});
```

### Trading

#### Create order

*Authentication is required.*

```java
//Synchronous
Key key = WalletClient.getKeyPairFromMnemonics("", 0);
NodeRpcClient rpcClient = DexClientFactory.nodeRpcClient().runPow(true).key(key).serverUrl("xxxxx").build();

public void orderBuy() {
   String orderHash = rpcClient.orderBuy("tti_44bb5d1c7b6a97df5829b87d", "tti_5649544520544f4b454e6e40", "10.0", quantityConvert(18, "tti_44bb5d1c7b6a97df5829b87d"));
   logger.info("orderHash:{}", orderHash);
   try {
        TimeUnit.SECONDS.sleep(2);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}

SyncRequestClient syncRequestClient = DexClientFactory.syncRequestClient().serverUrl("https://vitex.vite.net/").build();
private String quantityConvert(Integer quantity, String tokenId) {
    TokenDetail tokenDetail = syncRequestClient.getTokenDetailByTokenId(tokenId);
    logger.info("tokenDetail:{}", tokenDetail);
    StringBuilder decimalStr = new StringBuilder("1");
    for (int i = 0; i < tokenDetail.getTokenDecimals(); i++) {
        decimalStr.append("0");
    }
    //The quantity of Trade Token. The actual quantity multiplied by the accuracy of the currency in which it is traded.
    BigDecimal result = new BigDecimal(quantity).multiply(new BigDecimal(decimalStr.toString()));
    if (result.doubleValue() <= 0) {
        throw new DexApiException(DexApiException.INPUT_ERROR, "quantity is error");
    }
    return result.toPlainString();
}
```

#### Cancel order

*Authentication is required.*

```java
//Synchronous
OrderReceive receive = rpcClient.getOrderIdByAccountBlockHash("ccee1632102be259c86c457a74bbafcb6e8286b3677b8cfea5163825b925be32");
logger.info("receive:{}", receive);
try {
    TimeUnit.SECONDS.sleep(2);
} catch (InterruptedException e) {
    e.printStackTrace();
}

String cancelHash = rpcClient.cancelOrder("00000400fffffffff5ffffffffff005d3e9f49000444");
logger.info("cancelHash:{}", cancelHash);
```

#### Get order info

```java
//Synchronous
Order order = syncRequestClient.getOrder("vite_efeccc7f8c776b6e7fdedcf145bf560e32cef368514480b2df", "00000900ffffffffa4ffffffffff005d3e97ee000001");
logger.info("data:{}", order);
```

```java
//Asynchronous
asyncRequestClient.getOrder("vite_efeccc7f8c776b6e7fdedcf145bf560e32cef368514480b2df", "00000900ffffffffa4ffffffffff005d3e97ee000001", t -> {
    logger.info("success:{},data:{}", t.succeeded(), t.getData());
});
```

#### Opened orders

```java
//Synchronous
OrderList order = syncRequestClient.getOpenOrders(new OpenOrderRequest("vite_efeccc7f8c776b6e7fdedcf145bf560e32cef368514480b2df", null, null, null, null, null));
logger.info("data:{}", order);
```

```java
//Asynchronous
asyncRequestClient.getOpenOrders(new OpenOrderRequest("vite_efeccc7f8c776b6e7fdedcf145bf560e32cef368514480b2df", null, null, null, null, true), t -> {
    logger.info("success:{},data:{}", t.succeeded(), t.getData());
});
```

#### Historical orders

```java
//Synchronous
//0:buy,1:sell
OrderList order = syncRequestClient.getHistoricalOrders(new HistoricalOrdersRequest("vite_efeccc7f8c776b6e7fdedcf145bf560e32cef368514480b2df", null, null, null, null, null, null, null, null, null));
logger.info("data:{}", order);
```

```java
//Asynchronous
//0:buy,1:sell
asyncRequestClient.getHistoricalOrders(new HistoricalOrdersRequest("vite_efeccc7f8c776b6e7fdedcf145bf560e32cef368514480b2df", null, null, null, null, null, null, null, null, false), t -> {
    logger.info("success:{},data:{}", t.succeeded(), t.getData());
});
```


## Subscription example

### Subscribe trade update

```java
// Subscribe the trade update for btcusdt.
subscriptionClient.subscribeTradeEvent("GRIN-000_ETH-000", t -> {
    logger.info("data:{}", t);
});
```

###Subscribe KLine update

```java
// Subscribe candlestick update for btcusdt.
subscriptionClient.subscribeKlineEvent("GRIN-000_ETH-000", KlineInterval.Minute, t -> {
    logger.info("data:{}", t);
});
```

### Subscribe order update

*Authentication is required.*

```java
// Subscribe order update
subscriptionClient.subscribeOrderUpdateEvent("vite_836ba5e4d3f75b013bf33f1a19fafdcacc59eb8eb6e66d2b24", t -> {
    logger.info("data:{}", t);
});
```

### Subscribe 24h Statistics

*Authentication is required.*

```java
// Subscribe order update
subscriptionClient.subscribe24HTickerStatisticsEvent("T-000_VITE", t -> {
    logger.info("data:{}", t);
});
```

### Unsubscribe

You can cancel all subscription by calling ```unsubscribeAll()```.

```java
subscriptionClient.unsubscribeAll();
```
