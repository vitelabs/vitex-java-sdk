package org.vite.dex.api.client.domain.event;

import lombok.Data;

@Data
public class OrderEvent {

    String clientId;

    String topic;

    String symbol;

    DexPushMessage.OrderProto data;
}
