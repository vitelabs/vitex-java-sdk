package org.vite.dex.api.client.domain.market;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderBook {

    private Long timestamp;

    private List<String[]> asks;

    private List<String[]> bids;

    @Override
    public String toString(){
        return JSONObject.toJSONString(this);
    }
}
