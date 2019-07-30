package org.vite.dex.client.utils;

@FunctionalInterface
public interface Handler<T> {

    void handle(T t);
}
