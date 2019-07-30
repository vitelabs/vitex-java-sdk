package org.vite.dex.client;

import org.vite.dex.client.exception.DexApiException;

import java.net.URL;

/**
 * The configuration for the request APIs
 */
public class RequestOptions {

    private String url;

    public RequestOptions() {
    }

    public RequestOptions(RequestOptions option) {
        setUrl(url);
    }

    public RequestOptions(String url) {
        setUrl(url);
    }

    public String getUrl() {
        return url;
    }

    /**
     * Set the URL for request.
     *
     * @param url The URL name like "https://xxxx/test"".
     */
    public void setUrl(String url) {
        try {
            URL u = new URL(url);
        } catch (Exception e) {
            throw new DexApiException(DexApiException.INPUT_ERROR, "The URL is incorrect: " + e.getMessage());
        }
        this.url = url;
    }
}
