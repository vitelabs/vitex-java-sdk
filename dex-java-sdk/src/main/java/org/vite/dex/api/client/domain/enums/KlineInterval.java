package org.vite.dex.api.client.domain.enums;

import org.vite.dex.api.client.utils.EnumLookup;

public enum KlineInterval {

    Minute("minute"),

    Minute30("minute30"),

    Hour("hour"),

    Hour6("hour6"),

    Hour12("hour12"),

    Day("day"),

    Week("week");

    private static final EnumLookup<KlineInterval> lookup = new EnumLookup<>(KlineInterval.class);
    private final String code;

    KlineInterval(String code) {
        this.code = code;
    }

    public static KlineInterval lookup(String name) {
        return lookup.lookup(name);
    }

    @Override
    public String toString() {
        return code;
    }

}
