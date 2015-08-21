package com.treslines.pagsegurodemo;

import android.support.annotation.NonNull;

/**
 * Use this class to define the buyer's shipping address<br/>
 * <br/>Author: Ricardo Ferreira, 17/08/2015
 */
public class PagSeguroAddress {

    /** Is the name of the avenue, road or street */
    private String street;
    /** Is the house's number. If there is no number, write N/A*/
    private String number;
    /** Any address complement like block, apartment or reference that may help*/
    private String complement;
    /** Is the neighborhood like Boa Viagem in Recife*/
    private String district;
    /** Is the CEP is Brazil composed only by numbers*/
    private String postalCode;
    /** Is the metropole you live like Recife*/
    private String city;
    /** Is the region you live like PE for Pernambuco*/
    private PagSeguroBrazilianStates state;
    /** Only BRA for Brazil is supported by pagseguro*/
    private PagSeguroCountry countryCode;

    /**
     * Creates a new pagseguro delivery address object
     * @param street Is the name of the avenue, road or street
     * @param number Is the house's number. If there is no number, write N/A
     * @param complement Any address complement like block, apartment or reference that may help*
     * @param district Is the neighborhood like Boa Viagem in Recife
     * @param postalCode Is the CEP is Brazil composed only by numbers
     * @param city Is the metropole you live like Recife
     * @param state Is the region you live like PE for Pernambuco
     * @param countryCode Only BRA for Brazil is supported by pagseguro.
     */
    public PagSeguroAddress(@NonNull final String street, @NonNull final String number, @NonNull final String complement,@NonNull final String district, @NonNull final String postalCode, @NonNull final String city, @NonNull final PagSeguroBrazilianStates state, @NonNull final PagSeguroCountry countryCode) {
        validate(street,number, complement, district,postalCode,city,state);
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.district = district;
        this.postalCode = postalCode;
        this.city = city;
        this.state = state;
        this.countryCode = countryCode;
    }

    /** returns the xml used to describe the pagseguro shipping address information. You don't need to call this method.<br/>
     * It will be called from {@link PagSeguroCheckout}
     */
    public String buildPagSeguroAddressXml(){
        String xml ="        <address>\n" +
                    "            <street>%s</street>\n" +
                    "            <number>%s</number>\n" +
                    "            <complement>%s</complement>\n" +
                    "            <district>%s</district>\n" +
                    "            <postalcode>%s</postalcode>\n" +
                    "            <city>%s</city>\n" +
                    "            <state>%s</state>\n" +
                    "            <country>%s</country>\n" +
                    "        </address>\n";

        return String.format(xml, street, number, complement,district, postalCode, city, state.getInitials(), countryCode.getCountryCode());
    }


    /** validation defined acc. to pagseguro's web documentation */
    private void validate( final String street,  final String number, final String complement, final String district,  final String postalCode,  final String city,  final PagSeguroBrazilianStates state){
        if(street.isEmpty()){
            throw new IllegalArgumentException("Street can not be empty!");
        }
        if(street.length()>80){
            throw new IllegalArgumentException("Street length can not exceed 80 characters!");
        }
        if(number.isEmpty()){
            this.number="N/A";
        }
        if(number.length()>20){
            throw new IllegalArgumentException("Number length can not exceed 20 characters!");
        }

        if(complement.isEmpty()){
            this.complement="";
        }
        if(complement.length()>40){
            throw new IllegalArgumentException("Complement length can not exceed 40 characters!");
        }

        if(district.isEmpty()){
            throw new IllegalArgumentException("District/neighborhood can not be empty!");
        }
        if(district.length()>60){
            throw new IllegalArgumentException("District/neighborhood length can not exceed 60 characters!");
        }

        if(postalCode.isEmpty()){
            throw new IllegalArgumentException("Postal code can not be empty!");
        }
        if(postalCode.length()!=8){
            throw new IllegalArgumentException("Postal code must have exactly 8 digits!");
        }
        try {
            Double.valueOf(postalCode);
        }catch (Exception e){
            throw new IllegalArgumentException("Postal code must be composed of digits only!");
        }
    }
}
