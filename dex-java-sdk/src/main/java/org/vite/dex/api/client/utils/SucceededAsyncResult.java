package org.vite.dex.api.client.utils;


import org.vite.dex.api.client.exception.DexApiException;

public class SucceededAsyncResult<T> implements AsyncResult<T> {

    private final T data;

    public SucceededAsyncResult(T data) {
        this.data = data;
    }

    @Override
    public DexApiException getException() {
        return null;
    }

    @Override
    public boolean succeeded() {
        return true;
    }

    @Override
    public T getData() {
        return data;
    }
}
