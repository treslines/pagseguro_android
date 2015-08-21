package com.treslines.pagsegurodemo;

import java.math.BigDecimal;
import java.util.List;

/**
 * Use this factory to create PagSeguro objects.<br/>
 * <br/>Author: Ricardo Ferreira, 20/08/2015
 */
public class PagSeguroFactory {

    private static PagSeguroFactory FACTORY = new PagSeguroFactory();

    private PagSeguroFactory() {
        //singleton
    }

    public static PagSeguroFactory instance() {
        return FACTORY;
    }

    public PagSeguroItem item(String id, String description, BigDecimal price, int quantity, int weight) {
        return new PagSeguroItem(id, description, price, quantity, weight);
    }

    public PagSeguroPhone phone(PagSeguroAreaCode ddd, String number) {
        return new PagSeguroPhone(ddd, number);
    }

    public PagSeguroAddress address(String street, String number, String complement, String district, String postalCode, String city, PagSeguroBrazilianStates state) {
        return new PagSeguroAddress(street, number, complement, district, postalCode, city, state, PagSeguroCountry.BRASIL);
    }

    public PagSeguroBuyer buyer(String completeName, String bornDate, String cpf, String email, PagSeguroPhone phone) {
        return new PagSeguroBuyer(completeName, bornDate, cpf, email, phone);
    }

    public PagSeguroShipping shipping(PagSeguroShippingType type, PagSeguroAddress address) {
        return new PagSeguroShipping(type, address);
    }

    public PagSeguroCheckout checkout(String saleReference, List<PagSeguroItem> shoppingCart, PagSeguroBuyer buyer, PagSeguroShipping shipping) {
        return new PagSeguroCheckout(saleReference, PagSeguroCurrency.BRASIL, shoppingCart, buyer, shipping);
    }
}
