package org.vite.dex.client.bean.model;

import lombok.Data;

@Data
public class MarketStatus {

    private String symbol;

    private String tradeToken;

    private String quoteToken;

    private String tradeTokenSymbol;

    private String quoteTokenSymbol;

    private String owner;

    private Integer stopped;
}
