package org.vite.dex.client.bean.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenDetail {

    String tokenId;

    String name;

    String symbol;

    String originalSymbol;

    String totalSupply;

    String publisher;

    Integer tokenDecimals;

    String tokenAccuracy;

    Long publisherDate;

    Byte reissue;

    String urlIcon;

    String gateway;

    String website;

    Map<String, String> links;

    Map<String, String> overview;
}
