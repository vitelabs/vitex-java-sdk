# Java ViteX API

dex-java-sdk is a lightweight Java library for interacting with the [ViteX API](https://vite.wiki/dex/api/dex-apis.html), providing complete API coverage, and supporting synchronous and asynchronous requests, as well as event streaming using WebSockets.

## Features
* Support for synchronous and asynchronous REST requests to all [Private API](https://vite.wiki/dex/api/dex-apis.html#private-rest-api), [Public API](https://vite.wiki/dex/api/dex-apis.html#public-rest-api) endpoints.

## Installation
1. Install library into your Maven's local repository by running `mvn install`
2. Add the following Maven dependency to your project's `pom.xml`:
```
    <groupId>org.vite.sdk</groupId>
    <artifactId>dex-java-sdk</artifactId>
    <version>1.0.0</version>
```

Alternatively, you can clone this repository and run the [examples](https://github.com/vitelabs/vitex-java-sdk/tree/master/dex-java-sdk/src/test/java/org/vite/dex/api/client/examples).

## Examples

### Getting Started

There are three main client classes that can be used to interact with the API:

1. [`SyncRequestClient`](), a synchronous/blocking [ViteX API]() client;
2. [`AsyncRequestClient`](), an asynchronous/non-blocking [ViteX API]() client;
3. [`SubscriptionClient`](), a subscription [ViteX API]() client;

These can be instantiated through the corresponding factory method of [`DexClientFactory`](), by passing the [security parameters]() `API-KEY` and `SECRET`, which can be created at []().

```java
SyncRequest client = DexClientFactory.newRestClient()
            .apiKey("913423DE46E97751CCC734F018F09217")
            .secretKey("F6BED9F34912C0B658B58C73B6531721")
            .serverUrl("https://api.vitex.net/test/").build();

AsyncRequestClient client = DexClientFactory.newAsyncRestClient()
            .serverUrl("https://api.vitex.net/test/").build();

SubscriptionClient subscriptionClient = DexClientFactory.subscriptionClient()
            .serverUrl("wss://vitex.vite.net/websocket").build();
```

If the client only needs to access endpoints which do not require additional security, then these parameters are optional.

Once the client is instantiated, it is possible to start making requests to the API.

### General endpoints

#### Test connectivity
```java
client.ping();
```

#### Check server time
```java
long serverTime = client.getServerTime();
System.out.println(serverTime);
```

#### All markets
```java
List<Market> markets = client.getAllMarkets(null,null);
System.out.println(markets);
```

#### Market Detail
```java
MarketDetail marketDetail = client.getMarketDetail("ETH-000_BTC-000");
System.out.println(marketDetail);
```

### Market Data endpoints

#### Order book of a symbol
```java
OrderBook orderBook = client.getOrderBook("ETH-000_BTC-000",100,null);();
System.out.println(orderBook.toString());
```

#### Latest trades list of a symbol
```java
List<LatestTrade> trades = client.getTrades("ETH-000_BTC-000",10);
System.out.println(trades);
```

#### Week candlestick bars for a symbol
```java
Candlesticks candlesticks = client.getCandlestickBars("ETH-000_BTC-000", KlineInterval.Week);
System.out.println(candlesticks);
```

#### Ticker Statistics of a symbol
```java
List<TickerStatistics> tickerStatistics = client.get24HrPriceStatistics("ETH-000_BTC-000",null);
System.out.println(tickerStatistics);
```

#### Getting the best price
```java
BookTicker bookTikcer = client.getBookTicker("ETH-000_BTC-000");
System.out.println(bookTikcer);
```


### Account Data endpoints


#### Get account orders for a symbol
```java
List<Order> orders = client.getAllOrders(new QueryOrdersRequest("ETH-000_BTC-000"));
System.out.println(orders);
```

#### Get order status
```java
Order order = client.getOrderStatus(new QueryOrderRequest("ETH-000_BTC-000","d15454b89ee86fdda8e455c022d82e06cda591a6156df46f0d9df79c33745d68"));
System.out.println(order.getExecutedQty());
```

#### Placing a MARKET order test
```java
client.newOrderTest(new NewOrderRequest("ETH-000_BTC-000","41.0","10", OrderSide.SELL));
```

#### Placing a MARKET order
```java
NewOrderResponse newOrderResponse = client.newOrder(new NewOrderRequest("ETH-000_BTC-000","41.0","10", OrderSide.SELL));
System.out.println(newOrderResponse);
```

#### Canceling an order
```java
CancelOrderResponse cancleOrderResponse = client.cancelOrder(new CancelOrderRequest("ETH-000_BTC-000","9b1d2e595ff776c1ee6d864177a780e9e951e96262778eb003553ef6f160212f"));
System.out.println(cancleOrderResponse);
```

#### Canceling orders for a symbol
```java
List<CancelOrderResponse> cancleOrderResponses = client.cancelOrders(new CancelOrdersRequest("ETH-000_BTC-000"));
System.out.println(cancleOrderResponses);
```

### Subscription API
#### Subscription Depth
```java
subscriptionClient.subscribeDepthEvent("VX_ETH-000", t -> {
            logger.info("data:{}", t);
        });

        try {
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
```

#### Subscription KLine
```java
subscriptionClient.subscribeKlineEvent("VX_ETH-000", KlineInterval.Minute, t -> {
            logger.info("data:{}", t);
        });
```

#### Subscription Trade
```java
subscriptionClient.subscribeTradeEvent("GRIN-000_ETH-000", t -> {
            logger.info("data:{}", t);
        });
```

#### Subscription Order
```java
subscriptionClient.subscribeOrderUpdateEvent("vite_836ba5e4d3f75b013bf33f1a19fafdcacc59eb8eb6e66d2b24", t -> {
            logger.info("data:{}", t);
        });
```

#### Subscription 24Tickers
```java
subscriptionClient.subscribe24HTickerStatisticsEvent("T-000_VITE", t -> {
            logger.info("data:{}", t);
        });
```

### More examples
An extensive set of examples, covering most aspects of the API, can be found at [].
