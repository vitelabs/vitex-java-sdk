package org.vite.dex.api.client.domain.general;

import lombok.Data;

@Data
public class MarketDetail extends Market {

    private String highPrice;

    private String lowPrice;

    private String lastPrice;

    private String volume;

    private String baseVolume;

    private String bidPrice;

    private String askPrice;

    private Integer openBuyOrders;

    private Integer openSellOrders;
}
