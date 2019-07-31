package org.vite.dex.client;

import org.vite.dex.client.bean.model.OrderReceive;
import org.vite.dex.client.i.NodeRpcRequest;
import vite.bean.Key;

public class NodeRpcClient implements NodeRpcRequest {

    private RpcApiRequestParser requestImpl;

    private Key key;

    private String serverUrl;

    private boolean runPow;

    private NodeRpcClient() {

    }

    public static NodeRpcClient builder() {
        return new NodeRpcClient();
    }

    public NodeRpcClient key(Key key) {
        this.key = key;
        return this;
    }

    public NodeRpcClient serverUrl(String serverUrl) {
        this.serverUrl = serverUrl;
        return this;
    }

    public NodeRpcClient runPow(boolean run) {
        this.runPow = run;
        return this;
    }

    public NodeRpcClient build() {
        this.requestImpl = new RpcApiRequestParser(key, runPow, new RequestOptions(serverUrl));
        return this;
    }

    @Override
    public String orderBuy(String tradeToken, String quoteToken, String price, String quantity) {
        return order(tradeToken,quoteToken,false,price,quantity);
    }

    @Override
    public String orderSell(String tradeToken, String quoteToken, String price, String quantity) {
        return order(tradeToken,quoteToken,true,price,quantity);
    }

    @Override
    public String order(String tradeToken, String quoteToken, Boolean side, String price, String quantity) {
        return requestImpl.order(tradeToken,quoteToken,side, price, quantity );
    }

    @Override
    public OrderReceive getOrderIdByAccountBlockHash(String hash) {
        return requestImpl.getOrderIdByAccountBlockHash(hash);
    }

    @Override
    public String cancelOrder(String orderId) {
        return requestImpl.cancelOrder(orderId);
    }
}
