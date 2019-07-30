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

## Beginning

### Installation

*The SDK is compiled by Java8*

For Beta version, please import the source code in java IDE (idea or eclipse)

The example code is in `vite-dex-sdk/dex-java-sdk/java/src/test/java/org/vite/dex/client/`.


### Quick Start

In your Java project, you can follow below steps:

* Create the client instance.
* Call the interfaces provided by client.

```java

SyncRequestClient syncClient = DexClientFactory.syncRequestClient().serverUrl("https://vitex.vite.net/beta").build();
        
// Get the latest VITE_BTC-000‘s kline data and print the highest price on console
MarketKlineList minuteKline = syncRequestClient.getLatestKline("GRIN-000_VITE", KlineInterval.Minute, 10);
logger.info("minuteKline:{}", minuteKline);
       
```

Please NOTE:

All timestamp which is got from SDK is the Unix timestamp based on UTC.


### Request vs. Subscription

Dex Sdk supports 2 types of invoking.

1. Dex Server method: 1. You can use it to get the market related data from Dex server
                              2. You also can subscribe the market updated data from Dex server.
2. Vite Node method:  1. You can use it to trade. You can also use it to get the orderId from Node server.


We recommend developers to use Vite Node request method to trade , to use Dex Server method to access the market related data.


### Clients

There are 3 clients for Dex Server method, ```SyncRequestClient``` 、 ```AsyncRequestClient``` 、 ```SubscriptionClient``` . One client for Vite Node method,  ```NodeRpcClient```.

* **SyncRequestClient**: It is a synchronous request, it will invoke the Dex Server API via synchronous method, all invoking will be blocked until receiving the response from server.

* **AsyncRequestClient**: It is an asynchronous request, it will invoke the  Dex Server API via asynchronous method, all invoking will return immediately, instead of waiting the server's response. So you must implement the ```onResponse()``` method in```RequestCallback``` interface. As long as receiving the response of the server, callback method you defined will be called. You can use the lambda expression to simplify the implementation, see [Asynchronous usage](#Asynchronous) for detail. 

* **SubscriptionClient**: It is the subscription, it is used for subscribing any market data update.  It is asynchronous, so you must implement ```onUpdate()``` method in  ```SubscriptionListener``` interface. The server will push any update for the client. if client receive the update, the ```onUpdate()``` method will be called. You can use the lambda expression to simplify the implementation, see [Subscription usage](#Subscription) for detail. 

* **NodeRpcClient**: It is a synchronous request, it will invoke the Vite Node Rpc API via synchronous method, all invoking will be blocked until receiving the response from server. 
  
### Create client

```java
SyncRequestClient syncRequestClient = DexClientFactory.syncRequestClient().serverUrl(DEX_SERVER_URL).build();
```

```java
AsyncRequestClient asyncRequestClient = DexClientFactory.asyncRequestClient().serverUrl(DEX_SERVER_URL).build();
```

```java
SubscriptionClient subscriptionClient = DexClientFactory.subscriptionClient().serverUrl(DEX_SERVER_SUBSCRIPTION_SERVER_URL).build();
```

```java
Key key = WalletClient.getKeyPairFromMnemonics(mnemonic, index);
NodeRpcClient rpcClient = DexClientFactory.nodeRpcClient().runPow(true).key(key).serverUrl(VITE_NODE_SERVEL_URL).build();
```


## Usage

### URL List

For Dex Server beta request: `https://vitex.vite.net/beta`

For Dex Server online request: `https://vitex.vite.net/`

For Dex Server beta subscription: `wss://vitex.vite.net/beta/websocket`

For Dex Server online subscription: `wss://vitex.vite.net/websocket`

For Vite Node beta request: 

For Vite Node online request: `https://api.vitewallet.com/ios/`


### Synchronous

To invoke the interface by synchronous, you can create the ```SyncRequestClient``` by calling ```DexClientFactory.syncRequestClient().serverUrl(DEX_SERVER_URL).build()```, and call the API directly.

```java
SyncRequestClient syncRequestClient = DexClientFactory.syncRequestClient().serverUrl("https://vitex.vite.net/").build();

// Get the best bid and ask for GRIN-000_VITE, print the best ask price and amount on console.
BookTicker bestBookTicker = syncRequestClient.getBestBookTicker("GRIN-000_VITE");
logger.info("data:{}", bestBookTicker);
```


### Asynchronous

To invoke the interface by asynchronous, you can create the ```AsyncRequestClient``` by calling ```DexClientFactory.asyncRequestClient().serverUrl(DEX_SERVER_URL).build()```, and call the API which  implement the callback by yourself. You will get a resultset after the asynchronous invoking completed, call ```succeeded()``` to check whether the invoking succeeded or not, then call ```getData()``` to get the server's response data.

```java
AsyncRequestClient asyncRequestClient = DexClientFactory.asyncRequestClient().serverUrl("https://vitex.vite.net/test").build();

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
SubscriptionClient subscriptionClient = DexClientFactory.subscriptionClient().serverUrl("wss://vitex.vite.net/websocket").build();

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
NodeRpcClient rpcClient = DexClientFactory.nodeRpcClient().runPow(true).key(key).serverUrl("https://api.vitewallet.com/ios/").build();
SyncRequestClient syncRequestClient = DexClientFactory.syncRequestClient().serverUrl("https://vitex.vite.net/").build();

// cancle the order  on console.
String cancelHash = rpcClient.cancelOrder("00000400fffffffff5ffffffffff005d3e9f49000444");
logger.info("cancelHash:{}", cancelHash);
```


