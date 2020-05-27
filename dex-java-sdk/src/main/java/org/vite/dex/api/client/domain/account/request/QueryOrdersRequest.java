package org.vite.dex.api.client.domain.account.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.vite.dex.api.client.domain.enums.OrderSide;
import org.vite.dex.api.client.domain.enums.OrderStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryOrdersRequest {

    private String address;

    private String symbol;

    private Long startTime;

    private Long endTime;

    private OrderSide side;

    private OrderStatus status;

    private Integer limit = 100;

    public QueryOrdersRequest(String address) {
        this.address = address;
    }


    public QueryOrdersRequest(String address, String symbol) {
        this.address = address;
        this.symbol = symbol;
    }

    public QueryOrdersRequest(String address, String symbol, OrderStatus status) {
        this.address = address;
        this.symbol = symbol;
        this.status = status;
    }

    public QueryOrdersRequest(String symbol, Long startTime, Long endTime) {
        this.symbol = symbol;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
