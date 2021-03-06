package org.vite.dex.api.client.domain.enums;

import org.vite.dex.api.client.utils.EnumLookup;

public enum  TokenCategory {

    QUOTE("quote"),

    ALL("all");

    private static final EnumLookup<TokenCategory> lookup = new EnumLookup<>(TokenCategory.class);
    private final String code;

    TokenCategory(String code) {
        this.code = code;
    }

    public static TokenCategory lookup(String name) {
        return lookup.lookup(name);
    }

    @Override
    public String toString() {
        return code;
    }
}
