package org.vite.dex.client.bean.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenOrderRequest {

    private String address;
    private String quoteTokenSymbol;
    private String tradeTokenSymbol;
    private Integer offset;
    private Integer limit;
    private Boolean needTotal;
}
