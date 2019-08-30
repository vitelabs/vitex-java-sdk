package org.vite.dex.api.client.domain.general;

import lombok.Data;

@Data
public class Market {

    private String symbol;

    private String tradingCurrency;

    private String quoteCurrency;

    private String tradingCurrencyId;

    private String quoteCurrencyId;

    private String tradingCurrencyName;

    private String quoteCurrencyName;

    private String operator;

    private String operatorName;

    private String operatorLogo;

    private Integer pricePrecision;

    private Integer amountPrecision;

    private String minOrderSize;

    private Double operatorMakerFee;

    private Double operatorTakerFee;

}
