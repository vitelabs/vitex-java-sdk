package org.vite.dex.api.client.domain.account.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.vite.dex.api.client.domain.OrderSide;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewOrderRequest {

    private String symbol;

    private String price;

    private String amount;

    private OrderSide orderSide;

    private Long timestamp = System.currentTimeMillis();

    public NewOrderRequest(String symbol, String price, String amount, OrderSide orderSide) {
        this.symbol = symbol;
        this.price = price;
        this.amount = amount;
        this.orderSide = orderSide;
        this.timestamp = System.currentTimeMillis();
    }
}
