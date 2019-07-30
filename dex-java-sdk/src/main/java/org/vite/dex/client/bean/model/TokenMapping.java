package org.vite.dex.client.bean.model;

import lombok.Data;

import java.util.Objects;

@Data
public class TokenMapping {

    String tokenId;

    String symbol;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TokenMapping that = (TokenMapping) o;
        return Objects.equals(symbol, that.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol);
    }
}
