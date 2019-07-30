package org.vite.dex.client.bean.model;

import lombok.Data;

@Data
public class ExchangeRate {

    //编码
    String tokenId;
    //名称
    String tokenSymbol;
    //美元
    Double usdRate;
    //人民币
    Double cnyRate;
}
