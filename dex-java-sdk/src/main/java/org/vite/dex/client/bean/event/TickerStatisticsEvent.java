package org.vite.dex.client.bean.event;

import lombok.Data;
import org.vite.dex.client.bean.enums.QuoteTokenCategory;

@Data
public class TickerStatisticsEvent {

    String clientId;

    String topic;

    String symbol;

    QuoteTokenCategory quoteTokenCategory;

    DexPushMessage.TickerStatisticsProto data;
}
