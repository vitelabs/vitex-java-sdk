package org.vite.dex.api.client.domain.account;

import lombok.Data;
import org.vite.dex.api.client.domain.OrderSide;

@Data
public class NewOrderResponse {

    private String symbol;

    private String orderId;
}
