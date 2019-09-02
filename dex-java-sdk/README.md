# Java Vite API

dex-java-api is a lightweight Java library for interacting with the [Vite API](https://github.com/vitelabs/vite-dex/wiki/V1.0%E4%B8%AD%E5%BF%83%E5%8C%96OpenAPI), providing complete API coverage, and supporting synchronous and asynchronous requests, as well as event streaming using WebSockets.

## Features
* Support for synchronous and asynchronous REST requests to all [General](https://github.com/vitelabs/vite-dex/wiki/V1.0%E4%B8%AD%E5%BF%83%E5%8C%96OpenAPI#%E9%80%9A%E7%94%A8%E6%8E%A5%E5%8F%A3), [Market Data](https://github.com/vitelabs/vite-dex/wiki/V1.0%E4%B8%AD%E5%BF%83%E5%8C%96OpenAPI#%E5%85%AC%E6%9C%89%E6%8E%A5%E5%8F%A3), [Account](https://github.com/vitelabs/vite-dex/wiki/V1.0%E4%B8%AD%E5%BF%83%E5%8C%96OpenAPI#%E7%A7%81%E6%9C%89%E6%8E%A5%E5%8F%A3) endpoints.

## Installation
1. Install library into your Maven's local repository by running `mvn install`
2. Add the following Maven dependency to your project's `pom.xml`:
```
<dependency>
  <groupId>org.vite.sdk</groupId>
  <artifactId>dex-java-api</artifactId>
  <version>1.0.0</version>
</dependency>
```

Alternatively, you can clone this repository and run the [examples]().

## Examples

### Getting Started

There are three main client classes that can be used to interact with the API:

1. [`DexApiRestClient`](), a synchronous/blocking [Vite API]() client;
2. [`DexApiAsyncRestClient`](), an asynchronous/non-blocking [Vite API]() client;

These can be instantiated through the corresponding factory method of [`DexApiClientFactory`](), by passing the [security parameters]() `API-KEY` and `SECRET`, which can be created at []().

```java
DexApiClientFactory factory = DexApiClientFactory.newInstance("API-KEY", "SECRET");
DexApiRestClient client = factory.newRestClient();
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
MarketDetail marketDetail = client.getMarketDetail("VTT-000_VITE");
System.out.println(marketDetail);
```

### Market Data endpoints

#### Order book of a symbol
```java
OrderBook orderBook = client.getOrderBook("VTT-000_VITE",100,null);();
System.out.println(orderBook.toString());
```

#### Latest trades list of a symbol
```java
List<LatestTrade> trades = client.getTrades("VTT-000_VITE",100);
System.out.println(trades);
```

#### Week candlestick bars for a symbol
```java
Candlesticks candlesticks = client.getCandlestickBars("VTT-000_VITE", KlineInterval.Week);
System.out.println(candlesticks);
```

#### Ticker Statistics of a symbol
```java
List<TickerStatistics> tickerStatistics = client.get24HrPriceStatistics("VTT-000_VITE",null);
System.out.println(tickerStatistics);
```

#### Getting the best price
```java
BookTicker bookTikcer = client.getBookTicker("VTT-000_VITE");
System.out.println(bookTikcer);
```


### Account Data endpoints


#### Get account orders for a symbol
```java
List<Order> orders = client.getAllOrders(new QueryOrdersRequest("VTT-000_VITE"));
System.out.println(orders);
```

#### Get order status
```java
Order order = client.getOrderStatus(new QueryOrderRequest("VTT-000_VITE","d15454b89ee86fdda8e455c022d82e06cda591a6156df46f0d9df79c33745d68"));
System.out.println(order.getExecutedQty());
```

#### Placing a MARKET order test
```java
client.newOrderTest(new NewOrderRequest("VTT-000_VITE","41.0","10", OrderSide.SELL));
```

#### Placing a MARKET order
```java
NewOrderResponse newOrderResponse = client.newOrder(new NewOrderRequest("VTT-000_VITE","41.0","10", OrderSide.SELL));
System.out.println(newOrderResponse);
```

#### Canceling an order
```java
CancelOrderResponse cancleOrderResponse = client.cancelOrder(new CancelOrderRequest("VTT-000_VITE","9b1d2e595ff776c1ee6d864177a780e9e951e96262778eb003553ef6f160212f"));
System.out.println(cancleOrderResponse);
```

#### Canceling orders for a symbol
```java
List<CancelOrderResponse> cancleOrderResponses = client.cancelOrders(new CancelOrdersRequest("VTT-000_VITE"));
System.out.println(cancleOrderResponses);
```

### More examples
An extensive set of examples, covering most aspects of the API, can be found at [].
