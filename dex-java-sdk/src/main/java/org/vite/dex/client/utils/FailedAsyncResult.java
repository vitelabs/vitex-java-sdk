package org.vite.dex.client.utils;

import org.vite.dex.client.exception.DexApiException;
import org.vite.dex.client.i.AsyncResult;

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
