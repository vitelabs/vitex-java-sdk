package org.vite.dex.client.bean.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarketKlineList {
    List<Long> t;
    List<Double> c;
    List<Double> p;
    List<Double> h;
    List<Double> l;
    List<Double> v;
}
