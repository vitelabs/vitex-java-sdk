package org.vite.dex.api.client.domain.event;

import lombok.Data;
import org.vite.dex.api.client.domain.enums.KlineInterval;

@Data
public class KlineEvent {

    String clientId;

    String topic;

    String symbol;

    KlineInterval interval;

    DexPushMessage.KlineProto data;
}
