package org.vite.dex.client;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vite.dex.client.bean.model.OrderReceive;
import org.vite.dex.client.bean.model.TokenDetail;
import org.vite.dex.client.exception.DexApiException;
import vite.WalletClient;
import vite.bean.Key;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Ignore
public class NodeRpcClientTest {

    private static final Logger logger = LoggerFactory.getLogger(NodeRpcClientTest.class);

    @Test
    public void orderBuy() {
        String mnemonic = "meat rescue animal pilot mirror squeeze cricket aim toast develop want travel pen lonely junk time craft cargo crane rabbit awake roof image lava";
        Key key = WalletClient.getKeyPairFromMnemonics(mnemonic, 0);
        NodeRpcClient rpcClient = DexClientFactory.nodeRpcClient().runPow(true).key(key).serverUrl("http://148.70.30.139:48132").build();

        String orderHash = rpcClient.orderBuy("tti_44bb5d1c7b6a97df5829b87d", "tti_5649544520544f4b454e6e40", "10.0", quantityConvert(18, "tti_44bb5d1c7b6a97df5829b87d"));
        logger.info("orderHash:{}", orderHash);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private String quantityConvert(Integer quantity, String tokenId) {

        TokenDetail tokenDetail = DexClientFactory.syncRequestClient().serverUrl("https://vitex.vite.net/test").build().getTokenDetailByTokenId(tokenId);

        logger.info("tokenDetail:{}", tokenDetail);

        StringBuilder decimalStr = new StringBuilder("1");
        for (int i = 0; i < tokenDetail.getTokenDecimals(); i++) {
            decimalStr.append("0");
        }

        //数量需要乘以token精度
        BigDecimal result = new BigDecimal(quantity).multiply(new BigDecimal(decimalStr.toString()));

        if (result.doubleValue() <= 0) {
            throw new DexApiException(DexApiException.INPUT_ERROR, "quantity is error");
        }
        return result.toPlainString();
    }

    @Test

    public void getOrderIdByAccountBlockHash() {
        NodeRpcClient rpcClient = DexClientFactory.nodeRpcClient().runPow(true).serverUrl("http://148.70.30.139:48132").build();
        OrderReceive receive = rpcClient.getOrderIdByAccountBlockHash("ccee1632102be259c86c457a74bbafcb6e8286b3677b8cfea5163825b925be32");
        logger.info("receive:{}", receive);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void cancelOrder() {
        String mnemonic = "meat rescue animal pilot mirror squeeze cricket aim toast develop want travel pen lonely junk time craft cargo crane rabbit awake roof image lava";
        Key key = WalletClient.getKeyPairFromMnemonics(mnemonic, 0);
        NodeRpcClient rpcClient = DexClientFactory.nodeRpcClient().runPow(true).key(key).serverUrl("http://148.70.30.139:48132").build();
        String cancelHash = rpcClient.cancelOrder("00000400fffffffff5ffffffffff005d3e9f49000444");
        logger.info("cancelHash:{}", cancelHash);
    }


}
