package org.vite.dex.api.client;

import org.vite.dex.api.client.impl.ViteApiAsyncRestClientImpl;
import org.vite.dex.api.client.impl.ViteApiRestClientImpl;

import static org.vite.dex.api.client.impl.ViteApiServiceGenerator.getSharedClient;

/**
 * A factory for creating ViteApi client objects.
 */
public class ViteApiClientFactory {

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
    private ViteApiClientFactory(String apiKey, String secret) {
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
    public static ViteApiClientFactory newInstance(String apiKey, String secret) {
        return new ViteApiClientFactory(apiKey, secret);
    }

    /**
     * New instance without authentication.
     *
     * @return the vite api client factory
     */
    public static ViteApiClientFactory newInstance() {
        return new ViteApiClientFactory(null, null);
    }

    /**
     * Creates a new synchronous/blocking REST client.
     */
    public ViteApiRestClient newRestClient() {
        return new ViteApiRestClientImpl(apiKey, secret);
    }

    /**
     * Creates a new asynchronous/non-blocking REST client.
     */
    public ViteApiAsyncRestClient newAsyncRestClient() {
        return new ViteApiAsyncRestClientImpl(apiKey, secret);
    }

}
