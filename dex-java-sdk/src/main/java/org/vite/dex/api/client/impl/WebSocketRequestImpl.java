package org.vite.dex.api.client.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vite.dex.api.client.domain.enums.KlineInterval;
import org.vite.dex.api.client.domain.enums.QuoteTokenCategory;
import org.vite.dex.api.client.domain.enums.TopicTemplate;
import org.vite.dex.api.client.domain.event.*;
import org.vite.dex.api.client.utils.InputChecker;

import java.util.Arrays;

class WebSocketRequestImpl {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketRequestImpl.class);

    WebSocketRequest<KlineEvent> subscribeKlineEventEvent( String symbols, KlineInterval interval, SubscriptionListener<KlineEvent> callback, SubscriptionErrorHandler errorHandler) {
        InputChecker.checker()
                .checkSymbols(symbols)
                .shouldNotNull(interval, "interval")
                .shouldNotNull(callback, "callback");
        WebSocketRequest<KlineEvent> request = new WebSocketRequest<>(callback, errorHandler);
        request.topics = genTopics(symbols, TopicTemplate.market_kline, interval.toString());
        request.eventParser = (clientId, topic, data) -> {
            KlineEvent klineEvent = new KlineEvent();
            klineEvent.setClientId(clientId);
            klineEvent.setTopic(topic);
            klineEvent.setSymbol(topic.split("\\.")[1]);
            klineEvent.setInterval(interval);
            klineEvent.setData(DexPushMessage.KlineProto.parseFrom(data));
            return klineEvent;
        };
        return request;
    }

    WebSocketRequest<TradeEvent> subscribeTradeEvent( String symbols, SubscriptionListener<TradeEvent> callback, SubscriptionErrorHandler errorHandler) {
        InputChecker.checker()
                .checkSymbols(symbols)
                .shouldNotNull(callback, "callback");
        WebSocketRequest<TradeEvent> request = new WebSocketRequest<>(callback, errorHandler);
        request.topics = genTopics(symbols, TopicTemplate.market_trade, null);
        request.eventParser = (clientId, topic, data) -> {
            TradeEvent tradeEvent = new TradeEvent();
            tradeEvent.setClientId(clientId);
            tradeEvent.setTopic(topic);
            tradeEvent.setSymbol(topic.split("\\.")[1]);
            tradeEvent.setData(DexPushMessage.TradeListProto.parseFrom(data));
            return tradeEvent;
        };
        return request;
    }

    WebSocketRequest<OrderEvent> subscribeOrderUpdateEvent( String addresses, SubscriptionListener<OrderEvent> callback, SubscriptionErrorHandler errorHandler) {
        InputChecker.checker()
                .checkAddresses(addresses)
                .shouldNotNull(callback, "callback");
        WebSocketRequest<OrderEvent> request = new WebSocketRequest<>(callback, errorHandler);
        request.topics = genTopics(addresses, TopicTemplate.order_address, null);
        request.eventParser = (clientId, topic, data) -> {
            OrderEvent orderEvent = new OrderEvent();
            orderEvent.setClientId(clientId);
            orderEvent.setTopic(topic);
            orderEvent.setSymbol(topic.split("\\.")[1]);
            orderEvent.setData(DexPushMessage.OrderProto.parseFrom(data));
            return orderEvent;
        };
        return request;
    }

    WebSocketRequest<DepthEvent> subscribeDepthEvent( String symbols, Integer steps, SubscriptionListener<DepthEvent> callback, SubscriptionErrorHandler errorHandler) {
        InputChecker.checker()
                .checkSymbols(symbols)
                .checkRange(steps, -1, 8, "steps")
                .shouldNotNull(callback, "callback");
        WebSocketRequest<DepthEvent> request = new WebSocketRequest<>(callback, errorHandler);
        if (steps < 0) {
            request.topics = genTopics(symbols, TopicTemplate.market_depth_default, null);
        } else {
            request.topics = genTopics(symbols, TopicTemplate.market_depth, steps.toString());
        }
        request.eventParser = (clientId, topic, data) -> {
            logger.info("clientId:{},topic:{}", clientId, topic);
            DepthEvent depthEvent = new DepthEvent();
            depthEvent.setClientId(clientId);
            depthEvent.setTopic(topic);
            depthEvent.setSymbol(topic.split("\\.")[1]);
            depthEvent.setSteps(steps);
            depthEvent.setData(DexPushMessage.DepthListProto.parseFrom(data));
            return depthEvent;
        };
        return request;
    }

    WebSocketRequest<TickerStatisticsEvent> subscribe24HTickerStatisticsEvent(String symbols, SubscriptionListener<TickerStatisticsEvent> callback, SubscriptionErrorHandler errorHandler) {
        InputChecker.checker()
                .checkSymbols(symbols)
                .shouldNotNull(callback, "callback");
        WebSocketRequest<TickerStatisticsEvent> request = new WebSocketRequest<>(callback, errorHandler);
        request.topics = genTopics(symbols, TopicTemplate.market_tickers, null);
        request.eventParser = (clientId, topic, data) -> {
            TickerStatisticsEvent tickerStatisticsEvent = new TickerStatisticsEvent();
            tickerStatisticsEvent.setClientId(clientId);
            tickerStatisticsEvent.setTopic(topic);
            tickerStatisticsEvent.setSymbol(topic.split("\\.")[1]);
            tickerStatisticsEvent.setData(DexPushMessage.TickerStatisticsProto.parseFrom(data));
            return tickerStatisticsEvent;
        };
        return request;
    }

    WebSocketRequest<TickerStatisticsEvent> subscribe24HTickerStatisticsEvent( QuoteTokenCategory quoteTokenCategory, SubscriptionListener<TickerStatisticsEvent> callback, SubscriptionErrorHandler errorHandler) {
        InputChecker.checker()
                .shouldNotNull(quoteTokenCategory,"quoteTokenCategory")
                .shouldNotNull(callback, "callback");
        WebSocketRequest<TickerStatisticsEvent> request = new WebSocketRequest<>(callback, errorHandler);
        request.topics = genTopics(quoteTokenCategory.toString(), TopicTemplate.market_quoteTokenCategory_tickers, null);
        request.eventParser = (clientId, topic, data) -> {
            TickerStatisticsEvent tickerStatisticsEvent = new TickerStatisticsEvent();
            tickerStatisticsEvent.setClientId(clientId);
            tickerStatisticsEvent.setTopic(topic);
            tickerStatisticsEvent.setQuoteTokenCategory(QuoteTokenCategory.lookup( topic.split("\\.")[2]));
            tickerStatisticsEvent.setData(DexPushMessage.TickerStatisticsProto.parseFrom(data));
            return tickerStatisticsEvent;
        };
        return request;
    }

    private String genTopics(String symbols, TopicTemplate topicTemplate, String param) {
        String[] symbolArray = symbols.split(",");
        StringBuilder topics = new StringBuilder();
        Arrays.stream(symbolArray).forEach(t -> {
            if (topics.length() != 0) {
                topics.append(",");
            }
            topics.append(topicTemplate.getTopic(t, param));
        });
        return topics.toString();
    }
}
