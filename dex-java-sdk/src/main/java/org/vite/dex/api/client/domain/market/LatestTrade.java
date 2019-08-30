package org.vite.dex.api.client.domain.market;

import lombok.Data;

@Data
public class LatestTrade {

    private Long timestamp;

    private String price;

    private String amount;

    private Integer side;
}
