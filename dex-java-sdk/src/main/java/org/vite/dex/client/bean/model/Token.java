package org.vite.dex.client.bean.model;

import lombok.Data;

@Data
public class Token {

    String tokenId;

    String name;

    String symbol;

    String originalSymbol;

    String totalSupply;

    String owner;
}
