package org.vite.dex.api.client.impl;


import lombok.Data;

@Data
class WebSocketRequest<T> {

    final SubscriptionListener<T> updateCallback;
    final SubscriptionErrorHandler errorHandler;
    String topics;
    WebSocketEventParser<T> eventParser;
    WebSocketRequest(SubscriptionListener<T> listener, SubscriptionErrorHandler errorHandler) {
        this.updateCallback = listener;
        this.errorHandler = errorHandler;
    }
}
