package org.vite.dex.api.client.constant;

import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Constants used throughout Vite's API.
 */
public class DexApiConstants {

    /**
     * REST API base URL.
     */
    public static final String API_BASE_URL = "https://api.vitex.net/test/";
    public static final String PROD_API_BASE_URL = "https://api.vitex.net/";
            //"http://localhost:8082/dev/";
     //       "http://132.232.65.121:8080/test/";
    //https://api.vitex.net

    /**
     * Streaming API base URL.
     */
    public static final String WS_API_BASE_URL = "wss://vitex.vite.net/websocket";

    /**
     * Default receiving window.
     */
    public static final long DEFAULT_RECEIVING_WINDOW = 60_000L;

    /**
     * Default ToStringStyle used by toString methods.
     * Override this to change the output format of the overridden toString methods.
     * - Example ToStringStyle.JSON_STYLE
     */
    public static ToStringStyle TO_STRING_BUILDER_STYLE = ToStringStyle.SHORT_PREFIX_STYLE;

    public static final String ENDPOINT_SECURITY_TYPE_SIGNED = "SIGNED";
    public static final String ENDPOINT_SECURITY_TYPE_SIGNED_HEADER =ENDPOINT_SECURITY_TYPE_SIGNED + ": #";

}
