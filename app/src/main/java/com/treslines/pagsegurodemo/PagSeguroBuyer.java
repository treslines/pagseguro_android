package com.treslines.pagsegurodemo;

import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;

/**
 * Use this class to describe a pagseguro's buyer<br/>
 * <br/>Author: Ricardo Ferreira, 17/08/2015
 */
public class PagSeguroBuyer {
    /** The full buyer's name with max. 50 characters*/
    private String completeName;
    /** The registered pagseguro buyer's email */
    private String email;
    /** Buyer's born date in the format dd/mm/yyyy*/
    private String bornDate;
    /** Buyer's cpf identification with exactly 11 digits*/
    private String cpf;
    /** The buyer's cell phone */
    private PagSeguroPhone phone;

    /**
     * Creates a new pagseguro buyer.
     * @param completeName The full buyer's name with max. 50 characters
     * @param bornDate Buyer's born date in the format dd/mm/yyyy
     * @param cpf Buyer's cpf identification with exactly 11 digits
     * @param email The registered, valid pagseguro buyer's email
     * @param phone The buyer's cell phone
     */
    public PagSeguroBuyer(@NonNull final String completeName, @NonNull final String bornDate,@NonNull final String cpf, @NonNull final String email,@NonNull final PagSeguroPhone phone) {
        validate(completeName, bornDate, cpf, email);
        this.completeName = completeName;
        this.bornDate=bornDate;
        this.cpf=cpf;
        this.email = email;
        this.phone = phone;
    }

    /** returns the xml used to define a pagseguro buyer. You don't need to call this method.<br/>
     * It will be called from {@link PagSeguroCheckout}
     */
    public String buildPagSeguroBuyerXml(){
        String xml ="    <sender>\n" +
                    "        <name>%s</name>\n" +
                    "        <bornDate>%s</bornDate>\n"+
                    "        <documents>\n" +
                    "            <document>\n"+
                    "                <type>CPF</type>\n"+
                    "                <value>%s</value>\n" +
                    "            </document>\n"+
                    "        </documents>\n"+
                    "        <email>%s</email>\n" +
                             phone.buildPagSeguroPhoneXml()+
                    "    </sender>\n";
        return String.format(xml, completeName, bornDate, cpf, email);
    }

    /** validation defined acc. to pagseguro's web documentation */
    private void validate(final String completeName, final String bornDate, final String cpf, final String email){
        if(completeName.isEmpty()){
            throw new IllegalArgumentException("Name can not be empty!");
        }
        if(completeName.split(" ").length<2){
            throw new IllegalArgumentException("Name must be composed at a minimum of two sequences of characters such as first name and second name!");
        }
        if(completeName.length()>50){
            throw new IllegalArgumentException("Name can not exceed 50 characters!");
        }

        if(bornDate.isEmpty()){
            throw new IllegalArgumentException("Born date can not be empty!");
        }
        if(bornDate.length()!=10){
            throw new IllegalArgumentException("The format of born date must be dd/mm/yyyy!");
        }
        try{
            new SimpleDateFormat("dd/MM/yyyy").parse(bornDate);
        }catch (Exception e){
            throw new IllegalArgumentException("The format of born date must be dd/mm/yyyy!");
        }

        if(cpf.isEmpty()){
            throw new IllegalArgumentException("CPF can not be empty!");
        }
        if(cpf.length()!=11){
            throw new IllegalArgumentException("The CPF must contain exactly 11 digits!");
        }
        try{
            Double.valueOf(cpf);
        }catch(Exception e){
            throw new IllegalArgumentException("The CPF must be composed of numbers only!");
        }
        if(email.isEmpty()){
            throw new IllegalArgumentException("Email can not be empty!");
        }
        if(email.length()>60){
            throw new IllegalArgumentException("Email can not exceed 60 characters!");
        }
        String regex = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";
        if(!email.matches(regex)){
            throw new IllegalArgumentException("Invalid Email address!");
        }
    }
}
