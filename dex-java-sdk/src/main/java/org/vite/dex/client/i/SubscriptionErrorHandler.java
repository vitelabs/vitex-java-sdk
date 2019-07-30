package org.vite.dex.client.i;

import org.vite.dex.client.exception.DexApiException;

/**
 * The error handler for the subscription.
 */
@FunctionalInterface
public interface SubscriptionErrorHandler {

    void onError(DexApiException exception);
}
