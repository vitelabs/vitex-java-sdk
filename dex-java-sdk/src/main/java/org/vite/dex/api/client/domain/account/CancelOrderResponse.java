package org.vite.dex.api.client.domain.account;

import lombok.Data;
import org.vite.dex.api.client.domain.enums.OrderStatus;

@Data
public class CancelOrderResponse {

    private String symbol;

    private String orderId;

    private String cancelRequest;

    private OrderStatus status;

}
