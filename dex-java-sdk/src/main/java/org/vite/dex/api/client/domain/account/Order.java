package org.vite.dex.api.client.domain.account;

import lombok.Data;
import org.vite.dex.api.client.domain.OrderSide;
import org.vite.dex.api.client.domain.OrderStatus;
import org.vite.dex.api.client.domain.OrderType;
import org.vite.dex.api.client.domain.TimeInForce;

@Data
public class Order {

    private String symbol;

    private String orderId;

    private OrderStatus status;

    private OrderSide side;

    private String price;

    private String amount;

    private String filledAmount;

    private String filledValue;

    private String fee;

    private Long created;

    private Long updated;

    private TimeInForce timeInForce;

    private OrderType type;
}
