package org.vite.dex.client.bean.model;

import lombok.Data;

@Data
public class TickerStatistics {

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
    //价格
    String openPrice;
    //价格
    String prevClosePrice;
    //价格
    String closePrice;
    //价格
    String priceChange;
    //变化率
    Double priceChangePercent;
    //价格
    String highPrice;
    //价格
    String lowPrice;
    //数量
    String quantity;
    //成交额
    String amount;
    //price精度
    Integer pricePrecision;
    //quantity精度
    Integer quantityPrecision;
    //time
    //String openTime;
    //time
    //String closeTime;
    //最新
    //String bidPrice;
    //最新
    //String bidQuantity;
    //最新
    //String askPrice;
    //最新
    //String askQuantity;
}
