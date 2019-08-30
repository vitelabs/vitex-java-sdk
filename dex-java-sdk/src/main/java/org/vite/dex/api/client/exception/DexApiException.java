package org.vite.dex.api.client.exception;

public class DexApiException extends RuntimeException {

    public static final String RUNTIME_ERROR = "RuntimeError";
    public static final String INPUT_ERROR = "InputError";
    public static final String REQUESTS_LIMIT_ERROR = "RequestsLimit";
    public static final String KEY_MISSING = "KeyMissing";
    public static final String GENERATE_BLOCK_ERROR = "GenerateBlockError";
    public static final String SERVER_ERROR = "ServerError";
    public static final String SUBSCRIPTION_ERROR = "SubscriptionError";
    public static final String ENV_ERROR = "EnvironmentError";
    public static final String EXEC_ERROR = "ExecuteError";
    private final String errCode;

    public DexApiException(String errType, String errMsg) {
        super(errMsg);
        this.errCode = errType;
    }

    public DexApiException(String errType, String errMsg, Throwable e) {
        super(errMsg, e);
        this.errCode = errType;
    }

    public String getErrType() {
        return this.errCode;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
