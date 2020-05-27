package org.vite.dex.api.client.domain.account.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QueryOrderRequest {

    private String address;

    private String orderId;

    public QueryOrderRequest(String address, String orderId) {
        this.address = address;
        this.orderId = orderId;
    }
}
