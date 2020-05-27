package org.vite.dex.api.client.domain.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.vite.dex.api.client.utils.EnumLookup;

/**
 * Buy/Sell order side.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public enum OrderSide {

    BUY("0"),
    SELL("1");

    private static final EnumLookup<OrderSide> lookup = new EnumLookup<>(OrderSide.class);
    private final String code;

    OrderSide(String code) {
        this.code = code;
    }

    public static OrderSide lookup(String name) {
        return lookup.lookup(name);
    }

    @Override
    public String toString() {
        return code;
    }

    public int code() {
        return Integer.parseInt(code);
    }
}
