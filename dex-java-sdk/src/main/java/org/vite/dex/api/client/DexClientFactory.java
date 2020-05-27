package org.vite.dex.api.client;

import org.vite.dex.api.client.impl.AsyncRequestClient;
import org.vite.dex.api.client.impl.SubscriptionClient;
import org.vite.dex.api.client.impl.SyncRequestClient;

/**
 * A factory for creating ViteApi client objects.
 */
public class DexClientFactory {

    /**
     * Creates a new synchronous/blocking REST client.
     */
    public static SyncRequestClient newRestClient() {
        return SyncRequestClient.builder();
    }

    public static SubscriptionClient subscriptionClient() {
        return SubscriptionClient.builder();
    }

    /**
     * Creates a new asynchronous/non-blocking REST client.
     */
    public static AsyncRequestClient newAsyncRestClient() {
        return AsyncRequestClient.builder();
    }

}
