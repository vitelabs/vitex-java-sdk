package org.vite.dex.api.client.utils;


import org.vite.dex.api.client.exception.DexApiException;

public class FailedAsyncResult<T> implements AsyncResult<T> {

    private final DexApiException exception;

    public FailedAsyncResult(DexApiException exception) {
        this.exception = exception;
    }

    @Override
    public DexApiException getException() {
        return exception;
    }

    @Override
    public boolean succeeded() {
        return false;
    }

    @Override
    public T getData() {
        return null;
    }
}
