package org.vite.dex.client;

public final class DexClientFactory {

    public static SyncRequestClient syncRequestClient() {
        return SyncRequestClient.builder();
    }

    public static AsyncRequestClient asyncRequestClient() {
        return AsyncRequestClient.builder();
    }

    public static WebSocketStreamClient subscriptionClient() {
        return WebSocketStreamClient.builder();
    }

    public static NodeRpcClient nodeRpcClient() {
        return NodeRpcClient.builder();
    }

}
