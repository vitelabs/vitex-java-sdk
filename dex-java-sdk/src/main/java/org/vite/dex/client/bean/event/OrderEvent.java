package org.vite.dex.client.bean.event;

import lombok.Data;

@Data
public class OrderEvent {

    String clientId;

    String topic;

    String symbol;

    DexPushMessage.OrderProto data;
}
