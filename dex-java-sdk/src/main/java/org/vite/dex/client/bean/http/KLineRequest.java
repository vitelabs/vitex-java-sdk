package org.vite.dex.client.bean.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.vite.dex.client.bean.enums.KlineInterval;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KLineRequest {
    private String symbol;
    private KlineInterval interval;
    private Long startTime = null;
    private Long endTime = null;
    private Integer limit = null;
}
