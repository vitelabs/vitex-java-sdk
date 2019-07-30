package org.vite.dex.client.bean.model;

import lombok.Data;

@Data
public class Trade {

    String tradeId;
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
    //price
    String price;
    //quantity
    String quantity;
    //amount
    String amount;
    //time
    Long time;
    //side
    Byte side;
    //orderId
    String buyerOrderId;
    //orderId
    String sellerOrderId;
    //fee
    String buyFee;
    //fee
    String sellFee;
    //height
    Long blockHeight;
}
