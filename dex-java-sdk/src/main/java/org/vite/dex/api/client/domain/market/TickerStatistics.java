package org.vite.dex.api.client.domain.market;

import org.vite.dex.api.client.domain.general.BaseMarket;

public class TickerStatistics extends BaseMarket {

    private String openPrice;

    private String prevClosePrice;

    private String closePrice;

    private String priceChange;

    private String priceChangePercent;

    private String highPrice;

    private String lowPrice;

    private String quantity;

    private String amount;

    private Integer pricePrecision;

    private Integer amountPrecision;

    public String getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice( String openPrice ) {
        this.openPrice = openPrice;
    }

    public String getPrevClosePrice() {
        return prevClosePrice;
    }

    public void setPrevClosePrice( String prevClosePrice ) {
        this.prevClosePrice = prevClosePrice;
    }

    public String getClosePrice() {
        return closePrice;
    }

    public void setClosePrice( String closePrice ) {
        this.closePrice = closePrice;
    }

    public String getPriceChange() {
        return priceChange;
    }

    public void setPriceChange( String priceChange ) {
        this.priceChange = priceChange;
    }

    public String getPriceChangePercent() {
        return priceChangePercent;
    }

    public void setPriceChangePercent( String priceChangePercent ) {
        this.priceChangePercent = priceChangePercent;
    }

    public String getHighPrice() {
        return highPrice;
    }

    public void setHighPrice( String highPrice ) {
        this.highPrice = highPrice;
    }

    public String getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice( String lowPrice ) {
        this.lowPrice = lowPrice;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity( String quantity ) {
        this.quantity = quantity;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount( String amount ) {
        this.amount = amount;
    }

    public Integer getPricePrecision() {
        return pricePrecision;
    }

    public void setPricePrecision( Integer pricePrecision ) {
        this.pricePrecision = pricePrecision;
    }

    public Integer getAmountPrecision() {
        return amountPrecision;
    }

    public void setAmountPrecision( Integer amountPrecision ) {
        this.amountPrecision = amountPrecision;
    }
}
