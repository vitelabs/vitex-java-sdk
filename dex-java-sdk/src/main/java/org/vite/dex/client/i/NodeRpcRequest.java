package org.vite.dex.client.i;

import org.vite.dex.client.bean.model.OrderReceive;

public interface NodeRpcRequest {

    /**
     * Submit a buy order
     *
     * @param tradeToken Trade token symbol. Example: ABC
     * @param quoteToken Quote token symbol. Example: VITE
     * @param price  The integer part has at most 12 bits，The decimals part has at most 12 bits.
     * @param quantity The quantity of Trade Token. The actual quantity multiplied by the accuracy of the currency in which it is traded
     * @return The request block hash
     */
    public String orderBuy(String tradeToken, String quoteToken, String price, String quantity);

    /**
     * Submit a sell order
     *
     * @param tradeToken Trade token symbol. Example: ABC
     * @param quoteToken Quote token symbol. Example: VITE
     * @param price  The integer part has at most 12 bits，The decimals part has at most 12 bits.
     * @param quantity The quantity of Trade Token. The actual quantity multiplied by the accuracy of the currency in which it is traded.
     * @return The request block hash
     */
    public String orderSell(String tradeToken, String quoteToken, String price, String quantity);

    /**
     * 下单
     * return the block hash
     * @param tradeToken Trade token symbol. Example: ABC
     * @param quoteToken Quote token symbol. Example: VITE
     * @param side TRUE: Sell, FALSE: Buy.
     * @param price The integer part has at most 12 bits，The decimals part has at most 12 bits.
     * @param quantity The quantity of Trade Token. The actual quantity multiplied by the accuracy of the currency in which it is traded.
     * @return The request block hash
     */
    public String order(String tradeToken, String quoteToken, Boolean side, String price, String quantity);

    /**
     * Get the orderId from chain
     * @param hash The request block hash
     * @return The response status of an order
     */
    public OrderReceive getOrderIdByAccountBlockHash(String hash);

    /**
     * Cancel an order
     * @param orderId The order id.
     * @return The request block hash
     */
    public String cancelOrder(String orderId);

}


