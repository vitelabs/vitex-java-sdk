package org.vite.dex.client.bean.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.vite.dex.client.bean.event.OrderReceiveStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderReceive {

    OrderReceiveStatus receiveStatus;

    String orderId;
}
