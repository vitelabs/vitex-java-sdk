package org.vite.dex.api.client.domain.event;

import lombok.Data;

@Data
public class DepthEvent {

    String clientId;

    String topic;

    String symbol;

    Integer steps;

    DexPushMessage.DepthListProto data;
}
