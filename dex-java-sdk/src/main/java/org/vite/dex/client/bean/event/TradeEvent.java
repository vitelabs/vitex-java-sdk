package org.vite.dex.client.bean.event;

import lombok.Data;

@Data
public class TradeEvent {

    String clientId;

    String topic;

    String symbol;

    DexPushMessage.TradeListProto data;
}
