package org.vite.dex.client.utils;

import org.vite.dex.client.exception.DexApiException;
import org.vite.dex.client.i.AsyncResult;

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
