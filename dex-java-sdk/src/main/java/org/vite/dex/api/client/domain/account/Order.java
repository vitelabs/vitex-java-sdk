package org.vite.dex.api.client.domain.account;

import org.vite.dex.api.client.domain.enums.OrderSide;
import org.vite.dex.api.client.domain.enums.OrderStatus;
import org.vite.dex.api.client.domain.enums.OrderType;
import org.vite.dex.api.client.domain.general.BaseMarket;

public class Order extends BaseMarket {
    private String address;

    private String orderId;

    private OrderStatus status;

    private OrderSide side;

    private String price;

    private String quantity;

    private String executedQuantity;

    private String executedAmount;
    private String executedPercent;
    private String executedAvgPrice;

    private String fee;

    private Long createTime;

    private OrderType type;

    public String getAddress() {
        return address;
    }

    public void setAddress( String address ) {
        this.address = address;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId( String orderId ) {
        this.orderId = orderId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus( OrderStatus status ) {
        this.status = status;
    }

    public OrderSide getSide() {
        return side;
    }

    public void setSide( OrderSide side ) {
        this.side = side;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice( String price ) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity( String quantity ) {
        this.quantity = quantity;
    }

    public String getExecutedQuantity() {
        return executedQuantity;
    }

    public void setExecutedQuantity( String executedQuantity ) {
        this.executedQuantity = executedQuantity;
    }

    public String getExecutedAmount() {
        return executedAmount;
    }

    public void setExecutedAmount( String executedAmount ) {
        this.executedAmount = executedAmount;
    }

    public String getExecutedPercent() {
        return executedPercent;
    }

    public void setExecutedPercent( String executedPercent ) {
        this.executedPercent = executedPercent;
    }

    public String getExecutedAvgPrice() {
        return executedAvgPrice;
    }

    public void setExecutedAvgPrice( String executedAvgPrice ) {
        this.executedAvgPrice = executedAvgPrice;
    }

    public String getFee() {
        return fee;
    }

    public void setFee( String fee ) {
        this.fee = fee;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime( Long createTime ) {
        this.createTime = createTime;
    }

    public OrderType getType() {
        return type;
    }

    public void setType( OrderType type ) {
        this.type = type;
    }
}
