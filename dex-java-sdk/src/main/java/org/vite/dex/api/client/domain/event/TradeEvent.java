package org.vite.dex.api.client.domain.event;

import lombok.Data;

@Data
public class TradeEvent {

    String clientId;

    String topic;

    String symbol;

    DexPushMessage.TradeListProto data;
}
