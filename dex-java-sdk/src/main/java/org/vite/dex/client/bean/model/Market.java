package org.vite.dex.client.bean.model;

import lombok.Data;

@Data
public class Market {

    //symbol
    String symbol;
    //symbol
    String tradeTokenSymbol;
    //symbol
    String quoteTokenSymbol;
    //tokenId
    String tradeToken;
    //tokenId
    String quoteToken;
    //精度
    Integer pricePrecision;
    //精度
    Integer quantityPrecision;

}
