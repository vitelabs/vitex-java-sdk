package org.vite.dex.api.client.domain.account.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CancelOrderRequest {

    private String symbol;

    private String orderId;

    public CancelOrderRequest(String symbol, String orderId) {
        this.symbol = symbol;
        this.orderId = orderId;
    }
}
