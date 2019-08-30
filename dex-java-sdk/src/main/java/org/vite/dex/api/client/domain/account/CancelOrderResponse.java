package org.vite.dex.api.client.domain.account;

import lombok.Data;

@Data
public class CancelOrderResponse {

    private String symbol;

    private String orderId;

    private String cancelRequest;

}
