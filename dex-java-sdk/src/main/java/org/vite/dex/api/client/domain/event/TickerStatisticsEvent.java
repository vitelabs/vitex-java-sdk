package org.vite.dex.api.client.domain.event;

import lombok.Data;
import org.vite.dex.api.client.domain.enums.QuoteTokenCategory;

@Data
public class TickerStatisticsEvent {

    String clientId;

    String topic;

    String symbol;

    QuoteTokenCategory quoteTokenCategory;

    DexPushMessage.TickerStatisticsProto data;
}
