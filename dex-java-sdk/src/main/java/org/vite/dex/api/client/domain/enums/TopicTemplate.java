package org.vite.dex.api.client.domain.enums;

public enum TopicTemplate {
    order_address("order.%s"),
    market_kline("market.%s.kline.%s"),
    market_depth("market.%s.depth.step%s"),
    market_depth_default("market.%s.depth"),
    market_tickers("market.%s.tickers"),
    market_quoteToken_tickers("market.quoteToken.%s.tickers"),
    market_quoteTokenCategory_tickers("market.quoteTokenCategory.%s.tickers"),
    market_trade("market.%s.trade");

    String template;

    TopicTemplate(String template) {
        this.template = template;
    }

    public String getTopic(String... params) {
        return String.format(template, params);
    }
}