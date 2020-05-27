package org.vite.dex.api.client.domain.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.vite.dex.api.client.utils.EnumLookup;

/**
 * Type of order to submit to the system.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public enum OrderType {
    LIMIT("0"),
    MARKET("1");

    private static final EnumLookup<OrderType> lookup = new EnumLookup<>(OrderType.class);
    private final String code;

    OrderType(String code) {
        this.code = code;
    }

    public static OrderType lookup(String name) {
        return lookup.lookup(name);
    }

    @Override
    public String toString() {
        return code;
    }
}
