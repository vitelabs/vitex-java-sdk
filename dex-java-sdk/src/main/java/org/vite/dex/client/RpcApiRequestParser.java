package org.vite.dex.client;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.util.encoders.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongycastle.util.encoders.Base64;
import org.vite.dex.client.bean.event.OrderReceiveStatus;
import org.vite.dex.client.bean.model.OrderReceive;
import org.vite.dex.client.exception.DexApiException;
import vite.ViteClient;
import vite.api.vo.AccountBlock;
import vite.bean.Key;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static org.vite.dex.client.utils.AbiCollection.*;

public class RpcApiRequestParser {

    private static final Logger logger = LoggerFactory.getLogger(RpcApiRequestParser.class);

    private Key key;
    private String serverUrl;
    private RequestOptions options;
    private ViteClient viteClient;
    private boolean runPow;


    RpcApiRequestParser(Key key, boolean runPow, RequestOptions options) {
        this.key = key;
        this.options = options;
        this.runPow = runPow;
        this.serverUrl = options.getUrl();
        this.viteClient= new ViteClient(serverUrl);
    }

    String order(String tradeToken, String quoteToken, Boolean side, String price, String quantity){
        InputChecker.checker()
                .checkToken(tradeToken)
                .checkToken(quoteToken)
                .shouldNotNull(side, "side")
                .shouldNotNull(price, "price")
                .shouldNotNull(quantity, "quantity");

        List<Object> params = new ArrayList<>();
        params.add(tradeToken);
        params.add(quoteToken);
        params.add(side);
        params.add(0);
        params.add(price);
        params.add(quantity);

        JSONObject jsonObject = null;
        try {
            jsonObject = viteClient.callContract(key.getHexAddress(), ADDRESS_DEX_FUND, "tti_5649544520544f4b454e6e40", "0", ABI_ORDER, METHOD_ORDER, params, runPow, key.getHexPriKey());
        } catch (Exception e) {
            logger.error("The send tx is failed, tradeToken:{}, quoteToken:{}", tradeToken, quoteToken, e);
            throw new DexApiException(DexApiException.RUNTIME_ERROR,"Send tx error",e);
        }

        String sendHash = jsonObject.getString("hash");
        if (StringUtils.isEmpty(sendHash)) {
            logger.error("The send tx is failed, tradeToken:{}, quoteToken:{}, error:{}", tradeToken, quoteToken, jsonObject.getJSONObject("error"));
            throw new DexApiException(DexApiException.RUNTIME_ERROR,"Send tx error,return block hash is null");
        }
        return sendHash;
    }

    OrderReceive getOrderIdByAccountBlockHash(String hash) {
        AccountBlock sendAccountBlock = viteClient.getBlockByHash(hash);
        String receiveHash = sendAccountBlock.getReceiveBlockHash();
        if (StringUtils.isNotEmpty(receiveHash)) {// 判断send交易有没有被接收
            AccountBlock receiveAccountBlock = viteClient.getBlockByHash(receiveHash);
            logger.info("receiveAccountBlock:{}", JSONObject.toJSONString(receiveAccountBlock));
            if (receiveAccountBlock == null || receiveAccountBlock.getSendBlockList() == null || receiveAccountBlock.getSendBlockList().get(0).getData() == null) {
                logger.error("The AccountBlock is error, receiveHash:{}", receiveHash);
                return new OrderReceive(OrderReceiveStatus.RESPONSE_ERROR,null);
            }
            byte[] dataBytes = Base64.decode(receiveAccountBlock.getSendBlockList().get(0).getData());
            logger.info("data-check:{}", dataBytes[32]);
            if (dataBytes[32] == 0) {//成功
                byte[] temp = new byte[22];
                System.arraycopy(dataBytes, 70, temp, 0, 22);
                return  new OrderReceive(OrderReceiveStatus.RESPONSE_SUCCEED,Hex.toHexString(temp));
            }else {
                logger.error("Order is not succeed, receiveHash:{},accountBlock:{}", receiveHash,receiveAccountBlock);
                return new OrderReceive(OrderReceiveStatus.RESPONSE_ERROR,null);
            }
        }
        return new OrderReceive(OrderReceiveStatus.NO_RESPONSE,null);
    }


    String cancelOrder(String orderId) {

        List<Object> params = new ArrayList<>();
        params.add(orderId);
        JSONObject jsonObject = null;
        try {
            jsonObject = viteClient.callContract(key.getHexAddress(), ADDRESS_DEX_TRADE, "tti_5649544520544f4b454e6e40", "0", ABI_CANCEL_ORDER, METHOD_CANCEL_ORDER, params, runPow, key.getHexPriKey());
        } catch (UnsupportedEncodingException e) {
            logger.error("The send tx is failed, orderId:{}", orderId, e);
            throw new DexApiException(DexApiException.RUNTIME_ERROR,"Send tx error",e);
        }

        String sendHash = jsonObject.getString("hash");
        if (StringUtils.isEmpty(sendHash)) {
            logger.error("The send tx is failed, orderId:{}, error:{}", orderId, jsonObject.getJSONObject("error"));
            throw new DexApiException(DexApiException.RUNTIME_ERROR,"Send tx error,return block hash is null");
        }
        return sendHash;
    }
}
