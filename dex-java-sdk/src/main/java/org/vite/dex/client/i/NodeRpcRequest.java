package org.vite.dex.client.i;

import org.vite.dex.client.bean.model.OrderReceive;

public interface NodeRpcRequest {

    /**
     * 下买单
     * return the block hash
     * @param tradeToken
     * @param quoteToken
     * @param price
     * @param quantity
     * @return
     */
    public String orderBuy(String tradeToken, String quoteToken, String price, String quantity);

    /**
     * 下卖单
     * return the block hash
     * @param tradeToken
     * @param quoteToken
     * @param price
     * @param quantity
     * @return
     */
    public String orderSell(String tradeToken, String quoteToken, String price, String quantity);

    /**
     * 下单
     * return the block hash
     * @param tradeToken
     * @param quoteToken
     * @param side
     * @param price
     * @param quantity
     * @return
     */
    public String order(String tradeToken, String quoteToken, Boolean side, String price, String quantity);

    /**
     * 获取orderId
     * @param hash
     * @return
     */
    public OrderReceive getOrderIdByAccountBlockHash(String hash);

    /**
     * 取消订单
     * @param orderId
     * @return
     */
    public String cancelOrder(String orderId);

}


