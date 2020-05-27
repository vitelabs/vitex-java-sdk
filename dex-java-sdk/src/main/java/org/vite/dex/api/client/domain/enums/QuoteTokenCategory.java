package org.vite.dex.api.client.domain.enums;

import org.vite.dex.api.client.utils.EnumLookup;

public enum QuoteTokenCategory {

    VITE("VITE"),

    BTC("BTC"),

    ETH("ETH"),

    USD("USDT");

    private static final EnumLookup<QuoteTokenCategory> lookup = new EnumLookup<>(QuoteTokenCategory.class);
    private final String code;

    QuoteTokenCategory(String code) {
        this.code = code;
    }

    public static QuoteTokenCategory lookup(String name) {
        return lookup.lookup(name);
    }

    @Override
    public String toString() {
        return code;
    }
}
