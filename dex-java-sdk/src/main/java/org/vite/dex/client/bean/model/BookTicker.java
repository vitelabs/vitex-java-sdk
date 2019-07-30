package org.vite.dex.client.bean.model;

import lombok.Data;

@Data
public class BookTicker {
    String symbol;
    String bidPrice; //最优买单价
    String bidQuantity;//挂单量
    String askPrice; //最优卖单价
    String askQuantity; //挂单量
    Long height; //TODO
}


