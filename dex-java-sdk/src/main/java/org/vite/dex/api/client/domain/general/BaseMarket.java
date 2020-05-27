package org.vite.dex.api.client.domain.general;

public class BaseMarket {
    private String symbol;

    private String tradeTokenSymbol;
    private String quoteTokenSymbol;
    private String tradeToken;
    private String quoteToken;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol( String symbol ) {
        this.symbol = symbol;
    }

    public String getTradeTokenSymbol() {
        return tradeTokenSymbol;
    }

    public void setTradeTokenSymbol( String tradeTokenSymbol ) {
        this.tradeTokenSymbol = tradeTokenSymbol;
    }

    public String getQuoteTokenSymbol() {
        return quoteTokenSymbol;
    }

    public void setQuoteTokenSymbol( String quoteTokenSymbol ) {
        this.quoteTokenSymbol = quoteTokenSymbol;
    }

    public String getTradeToken() {
        return tradeToken;
    }

    public void setTradeToken( String tradeToken ) {
        this.tradeToken = tradeToken;
    }

    public String getQuoteToken() {
        return quoteToken;
    }

    public void setQuoteToken( String quoteToken ) {
        this.quoteToken = quoteToken;
    }
}
