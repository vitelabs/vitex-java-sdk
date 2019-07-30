package org.vite.dex.client;

@FunctionalInterface
interface WebSocketEventParser<T> {

    T parseData(String clientId, String topic, byte[] data) throws Exception;
}
