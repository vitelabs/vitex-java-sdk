package org.vite.dex.api.client.utils;

@FunctionalInterface
public interface Handler<T> {

    void handle( T t );
}
