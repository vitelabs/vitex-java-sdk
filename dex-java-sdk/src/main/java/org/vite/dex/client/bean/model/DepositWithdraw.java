package org.vite.dex.client.bean.model;

import lombok.Data;

@Data
public class DepositWithdraw {

    Long time;

    String tokenSymbol;

    String amount;

    Byte type;
}
