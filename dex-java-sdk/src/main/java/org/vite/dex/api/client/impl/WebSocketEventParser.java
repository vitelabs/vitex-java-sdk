package org.vite.dex.api.client.impl;

@FunctionalInterface
interface WebSocketEventParser<T> {

    T parseData( String clientId, String topic, byte[] data ) throws Exception;
}
