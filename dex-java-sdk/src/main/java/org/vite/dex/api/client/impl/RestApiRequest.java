package org.vite.dex.api.client.impl;

import okhttp3.Request;

public class RestApiRequest<T> {

    public Request request;
    RestApiJsonParser<T> jsonParser;
}
