package org.vite.dex.api.client.domain.market;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Candlesticks {
    List<Long> t;
    List<Double> c;
    List<Double> p;
    List<Double> h;
    List<Double> l;
    List<Double> v;
}
