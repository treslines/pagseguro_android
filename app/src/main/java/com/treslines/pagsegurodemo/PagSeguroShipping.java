package com.treslines.pagsegurodemo;

/**
 * Use this class to define the buyer's shipping option and delivery address<br/>
 * <br/>Author: Ricardo Ferreira, 17/08/2015
 */
public class PagSeguroShipping {
    /** The available shipping types over pagseguro in Brazil. It is an enumeration*/
    private PagSeguroShippingType pagSeguroShippingType;
    /** The Buyer's delivery address information */
    private PagSeguroAddress pagSeguroAddress;

    /**
     * Creates a new pagseguro shipping and delivery address object
     * @param pagSeguroShippingType The available shipping types like: PAC, SEDEX, EXPRESS
     * @param pagSeguroAddress The Buyer's delivery address information
     */
    public PagSeguroShipping(PagSeguroShippingType pagSeguroShippingType, PagSeguroAddress pagSeguroAddress) {
        this.pagSeguroShippingType = pagSeguroShippingType;
        this.pagSeguroAddress = pagSeguroAddress;
    }

    /** returns the xml used to describe the pagseguro shipping and address information. You don't need to call this method.<br/>
     * It will be called from {@link PagSeguroCheckout}
     */
    public String buildPagSeguroShippingXml(){
        String xml="        <type>%s</type>\n" +
                            pagSeguroAddress.buildPagSeguroAddressXml();
        return String.format(xml, pagSeguroShippingType.getShippingTypeRepresentation());
    }
}
