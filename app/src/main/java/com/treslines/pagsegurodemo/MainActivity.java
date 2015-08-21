package com.treslines.pagsegurodemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Shows how the payment process works with pagseguro.
 * Simulates an user buying a playstation using your sandbox account.
 */
public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // at this point you should check if the user has internet connection
                // before stating the pagseguro checkout process.(it will need internet connection)

                // simulating an user buying an iphone 6
                final PagSeguroFactory pagseguro = PagSeguroFactory.instance();
                List<PagSeguroItem> shoppingCart = new ArrayList<>();
                shoppingCart.add(pagseguro.item("123", "PlayStation", BigDecimal.valueOf(3.50), 1, 300));
                PagSeguroPhone buyerPhone = pagseguro.phone(PagSeguroAreaCode.DDD81, "998187427");
                PagSeguroBuyer buyer = pagseguro.buyer("Ricardo Ferreira", "14/02/1978", "15061112000", "test@email.com.br", buyerPhone);
                PagSeguroAddress buyerAddress = pagseguro.address("Av. Boa Viagem", "51", "Apt201", "Boa Viagem", "51030330", "Recife", PagSeguroBrazilianStates.PERNAMBUCO);
                PagSeguroShipping buyerShippingOption = pagseguro.shipping(PagSeguroShippingType.PAC, buyerAddress);
                PagSeguroCheckout checkout = pagseguro.checkout("Ref0001", shoppingCart, buyer, buyerShippingOption);
                // starting payment process
                new PagSeguroPayment(MainActivity.this).pay(checkout.buildCheckoutXml());
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_CANCELED) {
            // se foi uma tentativa de pagamento
            if(requestCode==PagSeguroPayment.PAG_SEGURO_REQUEST_CODE){
                // exibir confirmação de cancelamento
                final String msg = getString(R.string.transaction_cancelled);
                AppUtil.showConfirmDialog(this, msg, null);
            }
        } else if (resultCode == RESULT_OK) {
            // se foi uma tentativa de pagamento
            if(requestCode==PagSeguroPayment.PAG_SEGURO_REQUEST_CODE){
                // exibir confirmação de sucesso
                final String msg = getString(R.string.transaction_succeded);
                AppUtil.showConfirmDialog(this, msg, null);
            }
        }
        else if(resultCode == PagSeguroPayment.PAG_SEGURO_REQUEST_CODE){
            switch (data.getIntExtra(PagSeguroPayment.PAG_SEGURO_EXTRA, 0)){
                case PagSeguroPayment.PAG_SEGURO_REQUEST_SUCCESS_CODE:{
                    final String msg =getString(R.string.transaction_succeded);
                    AppUtil.showConfirmDialog(this,msg,null);
                    break;
                }
                case PagSeguroPayment.PAG_SEGURO_REQUEST_FAILURE_CODE:{
                    final String msg = getString(R.string.transaction_error);
                    AppUtil.showConfirmDialog(this,msg,null);
                    break;
                }
                case PagSeguroPayment.PAG_SEGURO_REQUEST_CANCELLED_CODE:{
                    final String msg = getString(R.string.transaction_cancelled);
                    AppUtil.showConfirmDialog(this,msg,null);
                    break;
                }
            }
        }
    }

}
