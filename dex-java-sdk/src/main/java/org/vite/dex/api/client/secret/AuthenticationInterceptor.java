package org.vite.dex.api.client.secret;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okio.Buffer;
import org.apache.commons.lang3.StringUtils;
import org.vite.dex.api.client.constant.DexApiConstants;
import org.vite.dex.api.client.exception.DexApiException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * A request interceptor that injects the API Key Header into requests, and signs messages, whenever required.
 */
@Slf4j
public class AuthenticationInterceptor implements Interceptor {

    private final String apiKey;

    private final String secret;

    public AuthenticationInterceptor(String apiKey, String secret) {
        this.apiKey = apiKey;
        this.secret = secret;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder newRequestBuilder = original.newBuilder();

        boolean isSignatureRequired = original.header(DexApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED) != null;

        // Endpoint requires signing the payload
        if (isSignatureRequired) {
            HttpUrl keyUrl = original.url().newBuilder().addQueryParameter("key", apiKey).build();
            String payload = buildSignature(keyUrl.query());
            log.info("payload:{}",payload);
            if (StringUtils.isNotEmpty(payload)) {
                String signature = HmacSHA256Signer.sign(payload, secret);
                log.info("signature:{}",signature);
                HttpUrl signedUrl = keyUrl.newBuilder().addQueryParameter("signature", signature).build();
                log.info("signedUrl:{}",signedUrl);
                newRequestBuilder.url(signedUrl);
            }
        }

        // Build new request after adding the necessary authentication information
        Request newRequest = newRequestBuilder.build();
        return chain.proceed(newRequest);
    }

    /**
     * Extracts the request body into a String.
     *
     * @return request body as a string
     */
    @SuppressWarnings("unused")
    private static String bodyToString(RequestBody request) {
        try (final Buffer buffer = new Buffer()) {
            final RequestBody copy = request;
            if (copy != null) {
                copy.writeTo(buffer);
            } else {
                return "";
            }
            return buffer.readUtf8();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        final AuthenticationInterceptor that = (AuthenticationInterceptor) o;
        return Objects.equals(apiKey, that.apiKey) &&
                Objects.equals(secret, that.secret);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apiKey, secret);
    }


    public String buildSignature(String payload) {
        Map<String, String> map = new TreeMap<>();
        String[] params = payload.split("&");
        for (String s : params) {
            int index = s.indexOf("=");
            map.put(s.substring(0, index), s.substring(index + 1));
        }
        StringBuilder head = new StringBuilder();
        return AppendUrl(map, head);

    }

    private String AppendUrl(Map<String, String> map, StringBuilder stringBuilder) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (!("").equals(stringBuilder.toString())) {
                stringBuilder.append("&");
            }
            stringBuilder.append(entry.getKey());
            stringBuilder.append("=");
            stringBuilder.append(urlEncode(entry.getValue()));
        }
        return stringBuilder.toString();
    }

    /**
     * 使用标准URL Encode编码。注意和JDK默认的不同，空格被编码为%20而不是+。
     *
     * @param s String字符串
     * @return URL编码后的字符串
     */
    private static String urlEncode(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new DexApiException(DexApiException.RUNTIME_ERROR,
                    "[URL] UTF-8 encoding not supported!");
        }
    }
}