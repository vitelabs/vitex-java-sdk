package org.vite.dex.api.client.impl;

import org.vite.dex.api.client.DexApiCallback;
import org.vite.dex.api.client.exception.DexApiException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * An adapter/wrapper which transforms a Callback from Retrofit into a ViteApiCallback which is exposed to the client.
 */
public class DexApiCallbackAdapter<T> implements Callback<T> {

    private final DexApiCallback<T> callback;

    public DexApiCallbackAdapter(DexApiCallback<T> callback) {
        this.callback = callback;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            callback.onResponse(response.body());
        } else {
            onFailure(call, new DexApiException(DexApiException.ENV_ERROR,"[ResonseCode]"+response.code()));
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable throwable) {
        callback.onFailure(throwable);
    }
}