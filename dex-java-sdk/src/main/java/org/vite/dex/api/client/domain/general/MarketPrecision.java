package org.vite.dex.api.client.domain.general;

public class MarketPrecision extends BaseMarket {
    private Integer pricePrecision;
    private Integer quantityPrecision;

    public Integer getPricePrecision() {
        return pricePrecision;
    }

    public void setPricePrecision( Integer pricePrecision ) {
        this.pricePrecision = pricePrecision;
    }

    public Integer getQuantityPrecision() {
        return quantityPrecision;
    }

    public void setQuantityPrecision( Integer quantityPrecision ) {
        this.quantityPrecision = quantityPrecision;
    }
}
