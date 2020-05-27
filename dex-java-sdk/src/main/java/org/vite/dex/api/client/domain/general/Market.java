package org.vite.dex.api.client.domain.general;

public class Market {
    private String symbol;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol( String symbol ) {
        this.symbol = symbol;
    }

    private String tradingCurrency;

    private String quoteCurrency;

    private String tradingCurrencyId;

    private String quoteCurrencyId;

    private String tradingCurrencyName;

    private String quoteCurrencyName;

    private String operator;

    private String operatorName;

    private String operatorLogo;

    private Integer pricePrecision;

    private Integer amountPrecision;

    private String minOrderSize;

    private Double operatorMakerFee;

    private Double operatorTakerFee;

    public String getTradingCurrency() {
        return tradingCurrency;
    }

    public void setTradingCurrency( String tradingCurrency ) {
        this.tradingCurrency = tradingCurrency;
    }

    public String getQuoteCurrency() {
        return quoteCurrency;
    }

    public void setQuoteCurrency( String quoteCurrency ) {
        this.quoteCurrency = quoteCurrency;
    }

    public String getTradingCurrencyId() {
        return tradingCurrencyId;
    }

    public void setTradingCurrencyId( String tradingCurrencyId ) {
        this.tradingCurrencyId = tradingCurrencyId;
    }

    public String getQuoteCurrencyId() {
        return quoteCurrencyId;
    }

    public void setQuoteCurrencyId( String quoteCurrencyId ) {
        this.quoteCurrencyId = quoteCurrencyId;
    }

    public String getTradingCurrencyName() {
        return tradingCurrencyName;
    }

    public void setTradingCurrencyName( String tradingCurrencyName ) {
        this.tradingCurrencyName = tradingCurrencyName;
    }

    public String getQuoteCurrencyName() {
        return quoteCurrencyName;
    }

    public void setQuoteCurrencyName( String quoteCurrencyName ) {
        this.quoteCurrencyName = quoteCurrencyName;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator( String operator ) {
        this.operator = operator;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName( String operatorName ) {
        this.operatorName = operatorName;
    }

    public String getOperatorLogo() {
        return operatorLogo;
    }

    public void setOperatorLogo( String operatorLogo ) {
        this.operatorLogo = operatorLogo;
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

    public String getMinOrderSize() {
        return minOrderSize;
    }

    public void setMinOrderSize( String minOrderSize ) {
        this.minOrderSize = minOrderSize;
    }

    public Double getOperatorMakerFee() {
        return operatorMakerFee;
    }

    public void setOperatorMakerFee( Double operatorMakerFee ) {
        this.operatorMakerFee = operatorMakerFee;
    }

    public Double getOperatorTakerFee() {
        return operatorTakerFee;
    }

    public void setOperatorTakerFee( Double operatorTakerFee ) {
        this.operatorTakerFee = operatorTakerFee;
    }
}
