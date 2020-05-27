package org.vite.dex.api.client.impl;

import org.vite.dex.api.client.exception.DexApiException;

/**
 * The error handler for the subscription.
 */
@FunctionalInterface
public interface SubscriptionErrorHandler {

    void onError( DexApiException exception );
}
