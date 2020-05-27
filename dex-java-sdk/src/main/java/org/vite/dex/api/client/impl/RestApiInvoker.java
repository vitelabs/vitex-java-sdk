package org.vite.dex.api.client.impl;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vite.dex.api.client.exception.DexApiException;
import org.vite.dex.api.client.utils.*;

import java.io.IOException;

abstract class RestApiInvoker {

    private static final Logger log = LoggerFactory.getLogger(RestApiInvoker.class);

    private static final OkHttpClient client = new OkHttpClient();

    static void checkResponse(Integer code, String msg) {
        try {
            if (code != null) {
                if (0 != code) {
                    throw new DexApiException(DexApiException.EXEC_ERROR, "[Executing] " + code + ": " + msg);
                }
            } else {
                throw new DexApiException(DexApiException.RUNTIME_ERROR, "[Invoking] Status cannot be found in response.");
            }
        } catch (Exception e) {
            throw new DexApiException(DexApiException.RUNTIME_ERROR, "[Invoking] Unexpected error: " + e.getMessage());
        }
    }

    static <T> T callSync(RestApiRequest<T> request) {
        try {
            String str;
            Response response = client.newCall(request.request).execute();
            if (response != null && response.body() != null) {
                str = response.body().string();
                response.close();
            } else {
                throw new DexApiException(DexApiException.ENV_ERROR, "[Invoking] Cannot get the response from server");
            }
            JsonWrapper jsonWrapper = JsonWrapper.parseFromString(str);
            checkResponse(jsonWrapper.getInteger("code"), jsonWrapper.getString("msg"));
            return request.jsonParser.parseJson(str);
        } catch (DexApiException e) {
            throw e;
        } catch (Exception e) {
            throw new DexApiException(DexApiException.ENV_ERROR, "[Invoking] Unexpected error: " + e.getMessage());
        }
    }

    static <T> void callASync(RestApiRequest<T> request, ResponseCallback<AsyncResult<T>> callback) {
        try {
            Call call = client.newCall(request.request);
            call.enqueue(
                    new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            FailedAsyncResult<T> result = new FailedAsyncResult(new DexApiException(DexApiException.RUNTIME_ERROR, "[Invoking] Rest api call failed"));
                            try {
                                callback.onResponse(result);
                            } catch (Exception exception) {
                                log.error("[Invoking] Unexpected error: " + exception.getMessage(), e);
                            }
                        }

                        @Override
                        public void onResponse(Call call, Response response) {
                            String str = "";
                            JsonWrapper jsonWrapper;
                            try {
                                if (response != null && response.body() != null) {
                                    str = response.body().string();
                                    response.close();
                                }
                                jsonWrapper = JsonWrapper.parseFromString(str);
                                checkResponse(jsonWrapper.getInteger("code"), jsonWrapper.getString("msg"));
                            } catch (DexApiException e) {
                                FailedAsyncResult<T> result = new FailedAsyncResult<>(e);
                                callback.onResponse(result);
                                return;
                            } catch (Exception e) {
                                FailedAsyncResult<T> result = new FailedAsyncResult<>(new DexApiException(DexApiException.RUNTIME_ERROR, "[Invoking] Rest api call failed"));
                                callback.onResponse(result);
                                return;
                            }
                            try {
                                SucceededAsyncResult<T> result = new SucceededAsyncResult<>(request.jsonParser.parseJson(str));
                                callback.onResponse(result);
                            } catch (Exception e) {
                                log.error("[Invoking] Unexpected error: " + e.getMessage(), e);
                            }
                        }
                    });
        } catch (Throwable e) {
            throw new DexApiException(DexApiException.ENV_ERROR, "[Invoking] Unexpected error: " + e.getMessage());
        }
    }

    static WebSocket createWebSocket(Request request, WebSocketListener listener) {
        return client.newWebSocket(request, listener);
    }
}
