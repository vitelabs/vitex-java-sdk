package org.vite.dex.client.bean.event;

import lombok.Data;

@Data
public class DepthEvent {

    String clientId;

    String topic;

    String symbol;

    Integer steps;

    DexPushMessage.DepthListProto data;
}
