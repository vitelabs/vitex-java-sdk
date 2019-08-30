package org.vite.dex.api.client.impl;


import org.vite.dex.api.client.constant.DexApiConstants;
import org.vite.dex.api.client.domain.account.CancelOrderResponse;
import org.vite.dex.api.client.domain.account.NewOrderResponse;
import org.vite.dex.api.client.domain.account.Order;
import org.vite.dex.api.client.domain.general.Market;
import org.vite.dex.api.client.domain.general.MarketDetail;
import org.vite.dex.api.client.domain.market.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * Vite's REST API URL mappings and endpoint security configuration.
 */
public interface ViteApiService {

    // General endpoints

    @GET("openapi/v1/ping")
    Call<Void> ping();

    @GET("openapi/v1/timestamp")
    Call<Long> getServerTime();

    @GET("openapi/v1/markets")
    Call<List<Market>> getAllMarkets(@Query("operator") String operator, @Query("quoteCurrency") String quoteCurrency);

    @GET("openapi/v1/market")
    Call<MarketDetail> getMarketDetail(@Query("symbol") String symbol);

    // Market data endpoints

    @GET("openapi/v1/depth")
    Call<OrderBook> getOrderBook(@Query("symbol") String symbol, @Query("limit") Integer limit, @Query("precision") Integer precision);

    @GET("openapi/v1/trades")
    Call<List<LatestTrade>> getTrades(@Query("symbol") String symbol, @Query("limit") Integer limit);

    @GET("openapi/v1/klines")
    Call<Candlesticks> getCandlestickBars(@Query("symbol") String symbol, @Query("interval") String interval, @Query("limit") Integer limit,
                                          @Query("startTime") Long startTime, @Query("endTime") Long endTime);

    @GET("openapi/v1/ticker/24hr")
    Call<List<TickerStatistics>> get24HrPriceStatistics(@Query("symbol") String symbol, @Query("quoteCurrency") String quoteCurrency);


    @GET("openapi/v1/ticker/bookTicker")
    Call<BookTicker> getBookTicker(@Query("symbol") String symbol);

    // Account endpoints

    @Headers(DexApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @POST("openapi/v1/order")
    Call<NewOrderResponse> newOrder(@Query("symbol") String symbol,
                                    @Query("price") String price,
                                    @Query("amount") String amount,
                                    @Query("side") Integer side,
                                    @Query("timestamp") Long timestamp);

    @Headers(DexApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @POST("openapi/v1/order/test")
    Call<Void> newOrderTest(@Query("symbol") String symbol,
                            @Query("price") String price,
                            @Query("amount") String amount,
                            @Query("side") Integer side,
                            @Query("timestamp") Long timestamp);

    @Headers(DexApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("openapi/v1/order")
    Call<Order> getOrderStatus(@Query("symbol") String symbol,
                               @Query("orderId") String orderId,
                               @Query("timestamp") Long timestamp);

    @Headers(DexApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @DELETE("openapi/v1/order")
    Call<CancelOrderResponse> cancelOrder(@Query("symbol") String symbol,
                                          @Query("orderId") String orderId,
                                          @Query("timestamp") Long timestamp);

    @Headers(DexApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("openapi/v1/orders")
    Call<List<Order>> getAllOrders(@Query("symbol") String symbol,
                                   @Query("startTime") Long startTime,
                                   @Query("endTime") Long endTime,
                                   @Query("side") Integer side,
                                   @Query("status") Integer status,
                                   @Query("limit") Integer limit,
                                   @Query("timestamp") Long timestamp);

    @Headers(DexApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @DELETE("openapi/v1/orders")
    Call<List<CancelOrderResponse>> cancelOrders(@Query("symbol") String symbol,
                                                 @Query("timestamp") Long timestamp);

}
