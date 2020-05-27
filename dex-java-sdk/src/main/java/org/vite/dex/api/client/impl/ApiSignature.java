package org.vite.dex.api.client.impl;

import org.vite.dex.api.client.exception.DexApiException;
import org.vite.dex.api.client.secret.HmacSHA256Signer;
import org.vite.dex.api.client.utils.UrlParamsBuilder;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

class ApiSignature {

    private static final String key = "key";
    private static final String timestamp = "timestamp";
    private static final String signature = "signature";

    private static final DateTimeFormatter DT_FORMAT = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss");
    private static final ZoneId ZONE_GMT = ZoneId.of("Z");

    private static long epochNow() {
        return Instant.now().getEpochSecond();
    }

    static String gmtNow() {
        return Instant.ofEpochSecond(epochNow()).atZone(ZONE_GMT).format(DT_FORMAT);
    }

    void createSignature(String accessKey, String secretKey, UrlParamsBuilder builder) {

        if (accessKey == null || "".equals(accessKey) || secretKey == null || "".equals(secretKey)) {
            throw new DexApiException(DexApiException.KEY_MISSING,
                    "API key and secret key are required");
        }
        builder.putToPost(key, accessKey)
                .putToPost(timestamp, System.currentTimeMillis());

        String payload = builder.buildPostSignature();
        String sign = HmacSHA256Signer.sign(payload, secretKey);

        builder.putToPost(signature, sign);

    }

}
