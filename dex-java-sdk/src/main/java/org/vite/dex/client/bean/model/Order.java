package org.vite.dex.client.bean.model;

import lombok.Data;

@Data
public class Order {

    //owner
    String address;
    //订单ID
    String orderId;
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
    //方向
    Byte side;
    //价格
    String price;
    //数量
    String quantity;
    //交易量
    String amount;
    //成交Quantity
    String executedQuantity;
    //成交Amount
    String executedAmount;
    //成交率
    String executedPercent;
    //均价
    String executedAvgPrice;
    //手续费
    String fee;
    //状态
    Byte status;
    //类型
    Byte type;
    //时间
    Long createTime;
}
