package com.treslines.pagsegurodemo;

/**
 * Use this enumeration to define the buyer's shipping option<br/>
 * <br/>Author: Ricardo Ferreira, 17/08/2015
 */
public enum PagSeguroShippingType {
    /** Normal post delivery service.*/
    PAC(1),
    /** Post express currier. */
    SEDEX(2),
    /** Undefined shipping option. */
    NOT_DEFINED(3);

    private int shippingTypeRepresentation;
    private PagSeguroShippingType(int typeRepresentation){
        this.shippingTypeRepresentation = typeRepresentation;
    }

    /** returns the integer that represents the enumeration*/
    public String getShippingTypeRepresentation(){
       return String.valueOf(this.shippingTypeRepresentation);
    }
}
