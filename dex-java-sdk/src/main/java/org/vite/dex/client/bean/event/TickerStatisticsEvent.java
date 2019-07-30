package org.vite.dex.client.bean.event;

import lombok.Data;

@Data
public class TickerStatisticsEvent {

    String clientId;

    String topic;

    String symbol;

    DexPushMessage.TickerStatisticsProto data;
}
