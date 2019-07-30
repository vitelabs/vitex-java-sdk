package org.vite.dex.client.bean.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarketKline {
    Long t;
    Double c;
    Double p;
    Double h;
    Double l;
    Double v;
}
