package org.vite.dex.api.client.domain.account.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CancelOrdersRequest {

    private String symbol;

    private Long timestamp;

    public CancelOrdersRequest(String symbol) {
        this.symbol = symbol;
        this.timestamp = System.currentTimeMillis();
    }
}
