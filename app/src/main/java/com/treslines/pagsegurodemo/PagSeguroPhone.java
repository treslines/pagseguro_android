package com.treslines.pagsegurodemo;

import android.support.annotation.NonNull;

/**
 * Use this class to define the buyer's cell phone<br/>
 * <br/>Author: Ricardo Ferreira, 17/08/2015
 */
public class PagSeguroPhone {
    /** Brazilian's area code like 81 for Recife or 11 for Sao Paulo with exactly 2 digits*/
    private PagSeguroAreaCode areaCode;
     /** The cell phone number with min 7 and max 9 digits*/
    private String number;

    /**
     * Creates a new pagseguro buyer's phone
     * @param areaCode Brazilian's area code like 81 for Recife or 11 for Sao Paulo with exactly 2 digits
     * @param number The cell phone number with min 7 and max 9 digits
     */
    public PagSeguroPhone(@NonNull final PagSeguroAreaCode areaCode, @NonNull final String number) {
        validate(number);
        this.areaCode = areaCode;
        this.number = number;
    }


    /** returns the xml used to define a pagseguro phone number. You don't need to call this method.<br/>
     * It will be called from {@link PagSeguroCheckout}
     */
    public String buildPagSeguroPhoneXml(){
        String xml = "        <phone>\n" +
                     "            <areacode>%s</areacode>\n" +
                     "            <number>%s</number>\n" +
                     "        </phone>\n";
        return String.format(xml, areaCode.getAreaCode(), number);
    }

    /** validation defined acc. to pagseguro's web documentation */
    private void validate(final String number){
        if(number.isEmpty()){
            throw new IllegalArgumentException("Number can not be empty!");
        }
        if(number.length()<7 || number.length()>9){
            throw new IllegalArgumentException("Number can not contain less then 7 digits and not exceed 9!");
        }
        try{
            Double.valueOf(number);
        }catch(Exception e){
            throw new IllegalArgumentException("Number code must be composed of numbers only!");
        }
    }
}
