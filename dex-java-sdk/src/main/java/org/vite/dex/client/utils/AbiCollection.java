package org.vite.dex.client.utils;

public class AbiCollection {


    public static final String ADDRESS_DEX_FUND= "vite_0000000000000000000000000000000000000006e82b8ba657"; // 下单合约
    public static final String ADDRESS_DEX_TRADE = "vite_00000000000000000000000000000000000000079710f19dc7"; // 撤单合约

    public static final String METHOD_ORDER = "DexFundNewOrder";
    public static final String METHOD_CANCEL_ORDER = "DexTradeCancelOrder";

    public static final String ABI_ORDER = "[{\"type\":\"function\",\"name\":\"DexFundNewOrder\", \"inputs\":[{\"name\":\"tradeToken\",\"type\":\"tokenId\"}, {\"name\":\"quoteToken\",\"type\":\"tokenId\"}, {\"name\":\"side\", \"type\":\"bool\"}, {\"name\":\"orderType\", \"type\":\"uint8\"}, {\"name\":\"price\", \"type\":\"string\"}, {\"name\":\"quantity\", \"type\":\"uint256\"}]}]";
    public static final String ABI_CANCEL_ORDER = "[{\"type\":\"function\",\"name\":\"DexTradeCancelOrder\", \"inputs\":[{\"name\":\"orderId\",\"type\":\"bytes\"}]}]";

}
