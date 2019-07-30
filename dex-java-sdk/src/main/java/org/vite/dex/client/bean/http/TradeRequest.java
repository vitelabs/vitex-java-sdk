package org.vite.dex.client.bean.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TradeRequest {
    private String symbol;
    private Integer offset = null;
    private Integer limit = null;
    private String orderId = null;
    private Byte side = null;
    private Long startTime = null;
    private Long endTime = null;
    private Boolean total = Boolean.FALSE;
}
