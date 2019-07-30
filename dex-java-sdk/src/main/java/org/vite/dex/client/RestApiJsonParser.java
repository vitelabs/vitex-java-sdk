package org.vite.dex.client;

@FunctionalInterface
interface RestApiJsonParser<T> {

    T parseJson(String data);
}
