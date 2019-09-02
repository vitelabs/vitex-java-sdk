package org.vite.dex.api.client;

import org.vite.dex.api.client.impl.DexApiAsyncRestClientImpl;
import org.vite.dex.api.client.impl.DexApiRestClientImpl;

/**
 * A factory for creating ViteApi client objects.
 */
public class DexApiClientFactory {

    /**
     * API Key
     */
    private String apiKey;

    /**
     * Secret.
     */
    private String secret;

    /**
     * Instantiates a new vite api client factory.
     *
     * @param apiKey the API key
     * @param secret the Secret
     */
    private DexApiClientFactory(String apiKey, String secret) {
        this.apiKey = apiKey;
        this.secret = secret;
    }

    /**
     * New instance.
     *
     * @param apiKey the API key
     * @param secret the Secret
     * @return the vite api client factory
     */
    public static DexApiClientFactory newInstance(String apiKey, String secret) {
        return new DexApiClientFactory(apiKey, secret);
    }

    /**
     * New instance without authentication.
     *
     * @return the vite api client factory
     */
    public static DexApiClientFactory newInstance() {
        return new DexApiClientFactory(null, null);
    }

    /**
     * Creates a new synchronous/blocking REST client.
     */
    public DexApiRestClient newRestClient() {
        return new DexApiRestClientImpl(apiKey, secret);
    }

    /**
     * Creates a new asynchronous/non-blocking REST client.
     */
    public DexApiAsyncRestClient newAsyncRestClient() {
        return new DexApiAsyncRestClientImpl(apiKey, secret);
    }

}
