package org.vite.dex.client;


import lombok.Data;
import org.vite.dex.client.i.SubscriptionErrorHandler;
import org.vite.dex.client.i.SubscriptionListener;

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
