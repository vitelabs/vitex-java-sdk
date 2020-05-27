package org.vite.dex.api.client.domain.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.vite.dex.api.client.utils.EnumLookup;

/**
 * Status of a submitted order.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public enum OrderStatus {
    UNKNOWN("0"),
    PENDING_REQUEST("1"),
    RECEIVED("2"),
    OPEN("3"),
    FILLED("4"),
    PARTIALLY_FILLED("5"),
    PENDING_CANCEL("6"),
    CANCELLED("7"),
    PARTIALLY_CANCELLED("8"),
    FAILED("9"),
    EXPIRED("10");

    private static final EnumLookup<OrderStatus> lookup = new EnumLookup<>(OrderStatus.class);
    private final String code;

    OrderStatus(String code) {
        this.code = code;
    }

    public static OrderStatus lookup(String name) {
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
