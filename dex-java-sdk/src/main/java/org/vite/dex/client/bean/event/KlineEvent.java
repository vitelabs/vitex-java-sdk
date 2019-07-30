package org.vite.dex.client.bean.event;

import lombok.Data;
import org.vite.dex.client.bean.enums.KlineInterval;

@Data
public class KlineEvent {

    String clientId;

    String topic;

    String symbol;

    KlineInterval interval;

    DexPushMessage.KlineProto data;
}
