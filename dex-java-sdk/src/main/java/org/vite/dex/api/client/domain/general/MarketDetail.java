package org.vite.dex.api.client.domain.general;

public class MarketDetail extends Market {

    private String highPrice;

    private String lowPrice;

    private String lastPrice;

    private String volume;

    private String baseVolume;

    private String bidPrice;

    private String askPrice;

    private Integer openBuyOrders;

    private Integer openSellOrders;

    public String getHighPrice() {
        return highPrice;
    }

    public String getLowPrice() {
        return lowPrice;
    }

    public String getLastPrice() {
        return lastPrice;
    }

    public String getVolume() {
        return volume;
    }

    public String getBaseVolume() {
        return baseVolume;
    }

    public String getBidPrice() {
        return bidPrice;
    }

    public String getAskPrice() {
        return askPrice;
    }

    public Integer getOpenBuyOrders() {
        return openBuyOrders;
    }

    public Integer getOpenSellOrders() {
        return openSellOrders;
    }

    public void setHighPrice( String highPrice ) {
        this.highPrice = highPrice;
    }

    public void setLowPrice( String lowPrice ) {
        this.lowPrice = lowPrice;
    }

    public void setLastPrice( String lastPrice ) {
        this.lastPrice = lastPrice;
    }

    public void setVolume( String volume ) {
        this.volume = volume;
    }

    public void setBaseVolume( String baseVolume ) {
        this.baseVolume = baseVolume;
    }

    public void setBidPrice( String bidPrice ) {
        this.bidPrice = bidPrice;
    }

    public void setAskPrice( String askPrice ) {
        this.askPrice = askPrice;
    }

    public void setOpenBuyOrders( Integer openBuyOrders ) {
        this.openBuyOrders = openBuyOrders;
    }

    public void setOpenSellOrders( Integer openSellOrders ) {
        this.openSellOrders = openSellOrders;
    }
}
