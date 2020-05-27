package org.vite.dex.api.client.impl;

@FunctionalInterface
interface RestApiJsonParser<T> {

    T parseJson( String data );
}
