package com.treslines.pagsegurodemo;

import android.support.annotation.NonNull;

import java.math.BigDecimal;

/**
 * Use this item to describe a product sold over pagseguro<br/>
 * <br/>Author: Ricardo Ferreira, 17/08/2015
 */
public class PagSeguroItem {
    /** Product's id that can be freely defined by the seller and have a length of max 100 characters */
    private String id;
    /** Product's description */
    private String description;
    /** Product's price */
    private BigDecimal amount;
    /** Number of units purchased */
    private int quantity;
    /** Gross product's weight in grams */
    private int weightInGramms;

    /**
     * Creates a new pagseguro product item.
     * @param id the product's id with max length of 100 characters
     * @param description the product's description with max length of 100 characters
     * @param amount the prodtuct's price with 2 decimals greater 0 and max 9999999.00
     * @param quantity the number of units purchased greater 0 and max 999
     * @param weightInGramms the gross product's weight in grams greater 0 and max. 1000*30 (30kg)
     */
    public PagSeguroItem(@NonNull final String id, @NonNull final String description, @NonNull final BigDecimal amount, @NonNull final int quantity, @NonNull final int weightInGramms) {
        validate(id, description, amount, quantity, weightInGramms);
        this.id = id;
        this.description = description;
        this.amount = amount.setScale(2, BigDecimal.ROUND_FLOOR);
        this.quantity = quantity;
        this.weightInGramms = weightInGramms;
    }

    /** returns the xml used to define a pagseguro product. You don't need to call this method.<br/>
     * It will be called from {@link PagSeguroCheckout}
     */
    public String buildPagSeguroItemXml(){
        String item =   "        <item>\n" +
                        "            <id>%s</id>\n" +
                        "            <description>%s</description>\n" +
                        "            <amount>%s</amount>\n" +
                        "            <quantity>%s</quantity>\n" +
                        "            <weight>%s</weight>\n" +
                        "        </item>\n" ;
        return String.format(item, id, description, amount.toString(),String.valueOf(quantity),String.valueOf(weightInGramms));
    }

    /** validation acc. to pagseguro's web documentation*/
    private void validate(final String id, final String description, final BigDecimal amount, final int quantity, final int weightInGramms){
        if(id.isEmpty()){
            throw new IllegalArgumentException("Product's Id can not be empty!");
        }
        if(id.length()>100){
            throw new IllegalArgumentException("Product's Id can not exceed 100 characters!");
        }
        if(description.isEmpty()){
            throw new IllegalArgumentException("Product's description can not be empty!");
        }
        if(description.length()>100){
            throw new IllegalArgumentException("Product's description can not exceed 100 characters!");
        }
        if(quantity<=0 || quantity >999){
            throw new IllegalArgumentException("Product's quantity can not be smaller or equals 0 and not greater then 999!");
        }
        if(weightInGramms<0 || weightInGramms > (1000*30)){
            throw new IllegalArgumentException("Product's weight can not be smaller 0 and the sum of all products can not exceed 30kg!");
        }
        if (amount.compareTo(BigDecimal.valueOf(1)) == -1 || amount.compareTo(BigDecimal.valueOf(9999999.00)) == 1 ){
            throw new IllegalArgumentException("Product's amount can not be smaller then R$1.00 and not greater then R$9999999.00!");
        }
    }
}
