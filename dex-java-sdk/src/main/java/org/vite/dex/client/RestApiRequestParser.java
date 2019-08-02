package org.vite.dex.client;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import okhttp3.Request;
import org.vite.dex.client.bean.enums.KlineInterval;
import org.vite.dex.client.bean.enums.TokenCategory;
import org.vite.dex.client.bean.model.*;
import org.vite.dex.client.exception.DexApiException;
import org.vite.dex.client.utils.UrlParamsBuilder;

import java.net.URL;
import java.util.List;

class RestApiRequestParser {

    private String apiKey;
    private String secretKey;
    private String serverUrl;
    private RequestOptions options;
    private String tradingHost;

    RestApiRequestParser(String apiKey, String secretKey, RequestOptions options) {
        this.apiKey = apiKey;
        this.secretKey = secretKey;
        this.options = options;
        this.serverUrl = options.getUrl() + "api";
        try {
            String host = new URL(this.options.getUrl()).getHost();
            this.tradingHost = host;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Request createRequestByGet(String path, UrlParamsBuilder builder) {
        return createRequestByGet(serverUrl, path, builder);
    }

    private Request createRequestByGet(String url, String path, UrlParamsBuilder builder) {
        return createRequest(url, path, builder);
    }

    private Request createRequest(String url, String path, UrlParamsBuilder builder) {
        String requestUrl = url + path;
        if (builder != null) {
            if (builder.hasPostParam()) {
                return new Request.Builder().url(requestUrl).post(builder.buildPostBody())
                        .addHeader("Content-Type", "application/json").build();
            } else {
                return new Request.Builder().url(requestUrl + builder.buildUrl())
                        .addHeader("Content-Type", "application/x-www-form-urlencoded").build();
            }
        } else {
            return new Request.Builder().url(requestUrl)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded").build();
        }
    }

    private Request createRequestWithSignature(String url, String path,
                                               String host,
                                               UrlParamsBuilder builder) {
        if (builder == null) {
            throw new DexApiException(DexApiException.RUNTIME_ERROR,
                    "[Invoking] Builder is null when create request with Signature");
        }
        String requestUrl = url + path;
        if (builder.hasPostParam()) {
            new ApiSignature().createSignature(apiKey, secretKey, "POST", host, path, builder);
            requestUrl += builder.buildUrl();
            return new Request.Builder().url(requestUrl).post(builder.buildPostBody())
                    .addHeader("Content-Type", "application/json").build();
        } else {
            new ApiSignature().createSignature(apiKey, secretKey, "GET", host, path, builder);
            requestUrl += builder.buildUrl();
            return new Request.Builder().url(requestUrl)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded").build();
        }
    }

    private Request createRequestByPostWithSignature(String address, UrlParamsBuilder builder) {
        return createRequestWithSignature(serverUrl, address, tradingHost, builder.setPostMode(true));
    }

    private Request createRequestByGetWithSignature(String address, UrlParamsBuilder builder) {
        return createRequestWithSignature(serverUrl, address, tradingHost, builder);
    }


    RestApiRequest<MarketKlineList> getKline(String symbol,
                                             KlineInterval interval,
                                             Long startTime,
                                             Long endTime,
                                             Integer limit) {

        startTime = startTime == null ? 1564070400 : startTime;
        endTime = endTime == null ? System.currentTimeMillis() / 1000 : endTime;
        limit = limit == null ? 10 : limit;
        InputChecker.checker()
                .checkSymbol(symbol)
                .checkRange(limit.longValue(), 1, 1500, "limit")
                .checkRange(startTime, 1564070400, System.currentTimeMillis() / 1000, "startTime")
                .checkRange(endTime, 1564070400, System.currentTimeMillis() / 1000, "startTime")
                .shouldNotNull(interval, "KlineInterval");
        RestApiRequest<MarketKlineList> request = new RestApiRequest<>();


        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol)
                .putToUrl("interval", interval.toString())
                .putToUrl("limit", limit)
                .putToUrl("endTime", endTime)
                .putToUrl("startTime", startTime);
        request.request = createRequestByGet("/v1/klines", builder);
        request.jsonParser = (jsonWrapper -> {
            return JSONObject.parseObject(jsonWrapper, new TypeReference<ResultBean<MarketKlineList>>() {
            }).getData();
        });
        return request;
    }

    RestApiRequest<MarketDepth> getDepth(String symbol, Integer limit) {
        InputChecker.checker()
                .checkSymbol(symbol).checkRange(limit, 1, 100, "limit");
        RestApiRequest<MarketDepth> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol)
                .putToUrl("limit", limit);
        request.request = createRequestByGet("/v1/depth", builder);
        request.jsonParser = (jsonWrapper -> {
            return JSONObject.parseObject(jsonWrapper, new TypeReference<ResultBean<MarketDepth>>() {
            }).getData();
        });
        return request;
    }

    RestApiRequest<TradeList> getHistoricalTrade(String symbol, Integer offset, Integer limit, String orderId, Byte side, Long startTime, Long endTime, Integer total) {
        limit = limit == null ? 100 : limit;
        InputChecker.checker()
                .checkSymbol(symbol)
                .checkRange(limit, 1, 100, "limit");
        RestApiRequest<TradeList> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol)
                .putToUrl("offset", offset)
                .putToUrl("limit", limit)
                .putToUrl("orderId", orderId)
                .putToUrl("side", side)
                .putToUrl("startTime", startTime)
                .putToUrl("endTime", endTime)
                .putToUrl("total", total);
        request.request = createRequestByGet("/v1/trades", builder);
        request.jsonParser = (jsonWrapper -> {
            return JSONObject.parseObject(jsonWrapper, new TypeReference<ResultBean<TradeList>>() {
            }).getData();
        });
        return request;
    }

    RestApiRequest<List<TickerStatistics>> get24HTickerStatistics(String symbols, String quoteTokenSymbol, String quoteTokenCategory) {
        RestApiRequest<List<TickerStatistics>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbols", symbols)
                .putToUrl("quoteTokenSymbol", quoteTokenSymbol)
                .putToUrl("quoteTokenCategory", quoteTokenCategory);
        request.request = createRequestByGet("/v1/ticker/24hr", builder);
        request.jsonParser = (jsonWrapper -> {
            return JSONObject.parseObject(jsonWrapper, new TypeReference<ResultBean<List<TickerStatistics>>>() {
            }).getData();
        });
        return request;
    }


    RestApiRequest<OrderList> getHistoricalOrder(String address, String quoteTokenSymbol, String tradeTokenSymbol, Integer offset, Integer limit, Long startTime, Long endTime, Integer side, Integer status, Integer total) {
        limit = limit == null ? 100 : limit;
        InputChecker.checker()
                .checkAddress(address)
                .checkRange(limit, 1, 100, "limit");
        RestApiRequest<OrderList> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("address", address)
                .putToUrl("quoteTokenSymbol", quoteTokenSymbol)
                .putToUrl("tradeTokenSymbol", tradeTokenSymbol)
                .putToUrl("offset", offset)
                .putToUrl("limit", limit)
                .putToUrl("status", status)
                .putToUrl("side", side) ////0:buy,1:sell
                .putToUrl("startTime", startTime)
                .putToUrl("endTime", endTime)
                .putToUrl("total", total);
        request.request = createRequestByGet("/v1/orders", builder);
        request.jsonParser = (jsonWrapper -> {
            return JSONObject.parseObject(jsonWrapper, new TypeReference<ResultBean<OrderList>>() {
            }).getData();
        });
        return request;
    }


    RestApiRequest<DepositWithdrawList> getWithdrawAndDepositHistory(String address, String tokenId, Integer offset, Integer limit) {
        offset = offset == null ? 0 : offset;
        limit = limit == null ? 100 : limit;
        InputChecker.checker()
                .checkAddress(address)
                .checkToken(tokenId)
                .checkRange(limit, 1, 100, "limit");
        RestApiRequest<DepositWithdrawList> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("address", address)
                .putToUrl("tokenId", tokenId)
                .putToUrl("offset", offset)
                .putToUrl("limit", limit);
        request.request = createRequestByGet("/v1/deposit-withdraw", builder);
        request.jsonParser = (jsonWrapper -> {
            return JSONObject.parseObject(jsonWrapper, new TypeReference<ResultBean<DepositWithdrawList>>() {
            }).getData();
        });
        return request;
    }

    RestApiRequest<Order> getOrder(String address, String orderId) {
        InputChecker.checker()
                .checkAddress(address)
                .checkOrder(orderId);
        RestApiRequest<Order> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("address", address)
                .putToUrl("orderId", orderId);
        request.request = createRequestByGet("/v1/order", builder);
        request.jsonParser = (jsonWrapper -> {
            return JSONObject.parseObject(jsonWrapper, new TypeReference<ResultBean<Order>>() {
            }).getData();
        });
        return request;
    }


    RestApiRequest<Limit> getLimit() {
        RestApiRequest<Limit> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build();
        request.request = createRequestByGet("/v1/limit", builder);
        request.jsonParser = (jsonWrapper -> {
            return JSONObject.parseObject(jsonWrapper, new TypeReference<ResultBean<Limit>>() {
            }).getData();
        });
        return request;
    }


    RestApiRequest<List<Token>> getTokens(TokenCategory category, String tokenSymbolLike, Integer offset, Integer limit) {

        InputChecker.checker()
                .checkRange(limit, 0, 500, "limit");
        RestApiRequest<List<Token>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("category", category == null ? null : category.toString())
                .putToUrl("tokenSymbolLike", tokenSymbolLike)
                .putToUrl("offset", offset)
                .putToUrl("limit", limit);

        request.request = createRequestByGet("/v1/tokens", builder);
        request.jsonParser = (jsonWrapper -> {
            return JSONObject.parseObject(jsonWrapper, new TypeReference<ResultBean<List<Token>>>() {
            }).getData();
        });
        return request;
    }


    RestApiRequest<TokenDetail> getTokenDetail(String tokenSymbol, String tokenId) {

        RestApiRequest<TokenDetail> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("tokenSymbol", tokenSymbol)
                .putToUrl("tokenId", tokenId);

        request.request = createRequestByGet("/v1/token/detail", builder);
        request.jsonParser = (jsonWrapper -> {
            return JSONObject.parseObject(jsonWrapper, new TypeReference<ResultBean<TokenDetail>>() {
            }).getData();
        });
        return request;
    }


    RestApiRequest<List<TokenMapping>> getTokenMapped(String quoteTokenSymbol) {

        InputChecker.checker()
                .checkSymbol(quoteTokenSymbol);
        RestApiRequest<List<TokenMapping>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("quoteTokenSymbol", quoteTokenSymbol);

        request.request = createRequestByGet("/v1/token/mapped", builder);
        request.jsonParser = (jsonWrapper -> {
            return JSONObject.parseObject(jsonWrapper, new TypeReference<ResultBean<List<TokenMapping>>>() {
            }).getData();
        });
        return request;
    }


    RestApiRequest<List<TokenMapping>> getTokenUnmapped(String quoteTokenSymbol) {

        InputChecker.checker()
                .checkSymbol(quoteTokenSymbol);
        RestApiRequest<List<TokenMapping>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("quoteTokenSymbol", quoteTokenSymbol);

        request.request = createRequestByGet("/v1/token/unmapped", builder);
        request.jsonParser = (jsonWrapper -> {
            return JSONObject.parseObject(jsonWrapper, new TypeReference<ResultBean<List<TokenMapping>>>() {
            }).getData();
        });
        return request;
    }


    RestApiRequest<List<Market>> getMarkets(Integer offset, Integer limit) {

        InputChecker.checker()
                .checkRange(limit, 0, 500, "limit");
        RestApiRequest<List<Market>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("offset", offset)
                .putToUrl("limit", limit);

        request.request = createRequestByGet("/v1/markets", builder);
        request.jsonParser = (jsonWrapper -> {
            return JSONObject.parseObject(jsonWrapper, new TypeReference<ResultBean<List<Market>>>() {
            }).getData();
        });
        return request;
    }


    RestApiRequest<BookTicker> getBestBookTicker(String symbol) {

        InputChecker.checker()
                .checkSymbol(symbol);
        RestApiRequest<BookTicker> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol);

        request.request = createRequestByGet("/v1/ticker/bookTicker", builder);
        request.jsonParser = (jsonWrapper -> {
            return JSONObject.parseObject(jsonWrapper, new TypeReference<ResultBean<BookTicker>>() {
            }).getData();
        });
        return request;
    }


    RestApiRequest<List<ExchangeRate>> getExchangeRate(String tokenIds, String tokenSymbols) {

        RestApiRequest<List<ExchangeRate>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("tokenIds", tokenIds)
                .putToUrl("tokenSymbols", tokenSymbols);

        request.request = createRequestByGet("/v1/exchange-rate", builder);
        request.jsonParser = (jsonWrapper -> {
            return JSONObject.parseObject(jsonWrapper, new TypeReference<ResultBean<List<ExchangeRate>>>() {
            }).getData();
        });
        return request;
    }


    RestApiRequest<Double> getUsd2Cny() {

        RestApiRequest<Double> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build();

        request.request = createRequestByGet("/v1/usd-cny", builder);
        request.jsonParser = (jsonWrapper -> {
            return JSONObject.parseObject(jsonWrapper, new TypeReference<ResultBean<Double>>() {
            }).getData();
        });
        return request;
    }


    RestApiRequest<Long> getServerTime() {

        RestApiRequest<Long> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build();

        request.request = createRequestByGet("/v1/time", builder);
        request.jsonParser = (jsonWrapper -> {
            return JSONObject.parseObject(jsonWrapper, new TypeReference<ResultBean<Long>>() {
            }).getData();
        });
        return request;
    }


    RestApiRequest<List<MarketStatus>> getClosedMarkets() {

        RestApiRequest<List<MarketStatus>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build();

        request.request = createRequestByGet("/v1/markets/closed", builder);
        request.jsonParser = (jsonWrapper -> {
            return JSONObject.parseObject(jsonWrapper, new TypeReference<ResultBean<List<MarketStatus>>>() {
            }).getData();
        });
        return request;
    }


    RestApiRequest<MarketStatus> getMarketStatus(String symbol) {

        InputChecker.checker()
                .checkSymbol(symbol);
        RestApiRequest<MarketStatus> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol);

        request.request = createRequestByGet("/v1/market/status", builder);
        request.jsonParser = (jsonWrapper -> {
            return JSONObject.parseObject(jsonWrapper, new TypeReference<ResultBean<MarketStatus>>() {
            }).getData();
        });
        return request;
    }
}
