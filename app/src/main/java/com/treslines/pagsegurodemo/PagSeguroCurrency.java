package com.treslines.pagsegurodemo;

/**
 * Use this enum to define the supported pagseguro country currency<br/>
 * <br/>Author: Ricardo Ferreria, 18/08/2015
 */
public enum PagSeguroCurrency {

    /**
     * Country: Brasil, Currency: Real, Code: BRL
     */
    BRASIL("Real", "BRL");
    private String currencyName;
    private String currencyCode;

    private PagSeguroCurrency(String currencyName, String currencyCode) {
        this.currencyName = currencyName;
        this.currencyCode = currencyCode;
    }

    /**
     * @return the currency name
     */
    public String getCurrencyName() {
        return currencyName;
    }

    /**
     * @return the currency code
     */
    public String getCurrencyCode() {
        return currencyCode;
    }
}
