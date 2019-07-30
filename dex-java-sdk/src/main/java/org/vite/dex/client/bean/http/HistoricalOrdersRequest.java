package org.vite.dex.client.bean.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoricalOrdersRequest {
    String address;
    String quoteTokenSymbol;
    String tradeTokenSymbol;
    Integer offset;
    Integer limit;
    Long startTime;
    Long endTime;
    Integer side;
    Integer status;
    Boolean needTotal;
}
