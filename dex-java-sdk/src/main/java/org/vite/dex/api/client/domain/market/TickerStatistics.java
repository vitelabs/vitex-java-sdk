package org.vite.dex.api.client.domain.market;

import lombok.Data;

@Data
public class TickerStatistics {

    private String symbol;

    private String tradingCurrency;

    private String quoteCurrency;

    private String tradingCurrencyId;

    private String quoteCurrencyId;

    private String openPrice;

    private String prevPrice;

    private String lastPrice;

    private String priceChange;

    private String priceChangePercent;

    private String highPrice;

    private String lowPrice;

    private String volume;

    private String baseVolume;

    private Integer pricePrecision;

    private Integer amountPrecision;

}
