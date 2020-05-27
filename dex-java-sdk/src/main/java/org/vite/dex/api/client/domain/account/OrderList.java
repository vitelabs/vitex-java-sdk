package org.vite.dex.api.client.domain.account;

import lombok.Data;

import java.util.List;

@Data
public class OrderList {
    private List<Order> order;

    private Long total;
}
