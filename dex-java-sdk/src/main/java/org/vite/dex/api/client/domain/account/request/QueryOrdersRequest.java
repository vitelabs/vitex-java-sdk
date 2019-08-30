package org.vite.dex.api.client.domain.account.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.vite.dex.api.client.domain.OrderSide;
import org.vite.dex.api.client.domain.OrderStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryOrdersRequest {

    private String symbol;

    private Long startTime;

    private Long endTime;

    private OrderSide side;

    private OrderStatus status;

    private Integer limit = 100;

    private Long timestamp = System.currentTimeMillis();


    public QueryOrdersRequest(String symbol) {
        this.symbol = symbol;
        this.timestamp = System.currentTimeMillis();
    }

    public QueryOrdersRequest(String symbol, Long startTime, Long endTime) {
        this.symbol = symbol;
        this.startTime = startTime;
        this.endTime = endTime;
        this.timestamp = System.currentTimeMillis();
    }
}
