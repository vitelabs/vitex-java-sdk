package org.vite.dex.api.client.domain.market;

import lombok.Data;

@Data
public class BookTicker {

    private String symbol;

    private String bidPrice;

    private String bidQuantity;

    private String askPrice;

    private String askQuantity;
}
