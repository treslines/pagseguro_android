package com.treslines.pagsegurodemo;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Checkout is the completion of a purchase process. Create an instance of it,<br/>
 * when you want to finalize the sale paying over pagseguro.<br/>
 * <br/>Author: Ricardo Ferreira, 18/08/2015
 */
public class PagSeguroCheckout {
    /** Can be freely defined by the seller with max length of 200 characters */
    private String saleReference =null;
    /** After payment pagseguro will redirect to this address. Must be valid and max 255 characters */
    private String redirectUrl=null;
    /** The international specific land currency code. For example: Brasil = BRL */
    private PagSeguroCurrency landCurrency;
    /** List of items bought by the user */
    private List<PagSeguroItem> boughtItems;
    /** Buyer's identification and contact information*/
    private PagSeguroBuyer pagSeguroBuyer;
    /** Buyer's shipping option and delevery address*/
    private PagSeguroShipping pagSeguroShipping;

    /**
     * Creates a pagseguro checkout object. After creation, call the method<br/>
     * buildCheckoutXml() to build the webservice's request body.
     * @param saleReference the sale reference code with max length of 200 characters
     * @param landCurrency Only BRL is permitted. Case sensitive. Example: Brasil =  BRL
     * @param boughtItems list of items bought. This representes the shopping cart
     * @param pagSeguroBuyer buyer's identification and contact information
     * @param pagSeguroShipping buyer's shipping option and delivery address
     */
    public PagSeguroCheckout(@NonNull final String saleReference, @NonNull final PagSeguroCurrency landCurrency, @NonNull final List<PagSeguroItem> boughtItems, @NonNull final PagSeguroBuyer pagSeguroBuyer, @NonNull final PagSeguroShipping pagSeguroShipping) {
        validate(saleReference);
        this.saleReference = saleReference;
        this.landCurrency = landCurrency;
        this.boughtItems = boughtItems;
        this.pagSeguroBuyer = pagSeguroBuyer;
        this.pagSeguroShipping = pagSeguroShipping;
    }

    /**
     * Creates a pagseguro checkout object with a redirectUrl. After creation, call the method<br/>
     * buildCheckoutXml() to build the webservice's request body.
     * @param saleReference the sale reference code with max length of 200 characters
     * @param redirectUrl after payment pagseguro will redirect to this url. Must be valid and max 255 characters.
     * @param landCurrency Only BRL is permitted. Case sensitive. Example: Brasil =  BRL
     * @param boughtItems list of items bought. This representes the shopping cart
     * @param pagSeguroBuyer buyer's identification and contact information
     * @param pagSeguroShipping buyer's shipping option and delivery address
     */
    public PagSeguroCheckout(@NonNull final String saleReference,@NonNull final String redirectUrl, @NonNull final PagSeguroCurrency landCurrency, @NonNull final List<PagSeguroItem> boughtItems, @NonNull final PagSeguroBuyer pagSeguroBuyer, @NonNull final PagSeguroShipping pagSeguroShipping) {
        this(saleReference, landCurrency, boughtItems, pagSeguroBuyer, pagSeguroShipping);
        validateRedirectUrl(redirectUrl);
        this.redirectUrl = redirectUrl;
    }

    /** returns the pagseguro xml to execute a checkout request over the pagseguro webservice*/
    public String buildCheckoutXml(){
        String xml = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\"?>\n" +
                "<checkout>\n" +
                "    <currency>%s</currency>\n" +
                (redirectUrl!=null?"<redirectURL>%s</redirectURL>\n":"") +
                "    <items>\n" +
                buildPagSeguroItemsXml() +
                "    </items>\n" +
                "    <reference>%s</reference>\n" +
                "    <sender>\n" +
                pagSeguroBuyer.buildPagSeguroBuyerXml()+
                "    </sender>\n" +
                "    <shipping>\n" +
                pagSeguroShipping.buildPagSeguroShippingXml()+
                "    </shipping>\n" +
                "</checkout>";
        String formattedXml = "";
        if(redirectUrl!=null){
            formattedXml =String.format(xml, landCurrency.getCurrencyCode(),redirectUrl, saleReference);
        }else{
            formattedXml = String.format(xml, landCurrency.getCurrencyCode(), saleReference);
        }
        return formattedXml;
    }

    private String buildPagSeguroItemsXml(){
        String itemXml = "";
        for (PagSeguroItem item: boughtItems) {
            itemXml+=item.buildPagSeguroItemXml();
        }
        return itemXml;
    }

    /** validation acc. to pagseguro's web documentation*/
    private void validate(final String saleReference){
        if(saleReference.isEmpty()){
            throw new IllegalArgumentException("Product's sale reference can not be empty!");
        }
        if(saleReference.length()>200){
            throw new IllegalArgumentException("Product's sale reference can not exceed 200 characters!");
        }
    }

    /** validation acc. to pagseguro's web documentation*/
    private void validateRedirectUrl(final String redirectUrl){
        if(redirectUrl.isEmpty()){
            throw new IllegalArgumentException("Redirect url can not be empty!");
        }
        if(redirectUrl.length()>255){
            throw new IllegalArgumentException("Redirect url can not exceed 255 characters!");
        }
    }
}
