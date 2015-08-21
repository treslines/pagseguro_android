package com.treslines.pagsegurodemo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.util.Xml;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

/**
 * Use this class to pay over pagseguro<br/>
 * <br/>Author: Ricardo Ferreria, 18/08/2015
 */
public class PagSeguroPayment {

    private Activity activity;
    private ProgressDialog progressDialog;

    public static final String PAG_SEGURO_EXTRA = "PAG_SEGURO_EXTRA";
    public static final int PAG_SEGURO_REQUEST_CODE = 111;
    public static final int PAG_SEGURO_REQUEST_SUCCESS_CODE = 222;
    public static final int PAG_SEGURO_REQUEST_FAILURE_CODE = 333;
    public static final int PAG_SEGURO_REQUEST_CANCELLED_CODE = 444;


    public PagSeguroPayment(Activity activity) {
        this.activity = activity;
        this.progressDialog =  new ProgressDialog(activity);
    }

    public void pay(String checkoutXml) {
        progressDialog.setTitle(this.activity.getString(R.string.pagseguro));
        progressDialog.setMessage(this.activity.getString(R.string.waiting_for_answer));
        progressDialog.show();
        StringEntity checkoutEntity = null;
        try {
            checkoutEntity = new StringEntity(checkoutXml);
            // very important step, if you don`t set this, the request will fail
            checkoutEntity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/xml"));
            checkoutEntity.setContentEncoding("ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            Log.d("PAG_SEGURO", e.getMessage());
        }
        // Make RESTful webservice call using AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        final String vendorEmail = activity.getString(R.string.pagseguro_vendor_email);
        final String vendorToken = activity.getString(R.string.pagseguro_vendor_token);
        final String webService = activity.getString(R.string.pagseguro_webservice_checkout_address);
        final String pagseguroWsRequestAddress = String.format(webService, vendorEmail, vendorToken);
        client.post(activity, pagseguroWsRequestAddress, checkoutEntity, "application/xml", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = null;
                try {
                    response = new String(responseBody, "ISO-8859-1");
                } catch (UnsupportedEncodingException e) {
                    Log.d("PAG_SEGURO", e.getMessage());
                }
                try {
                    // read checkout code
                    final XmlPullParser parser = Xml.newPullParser();
                    parser.setInput(new StringReader(response));
                    int eventType = parser.getEventType();
                    String checkoutCode="";
                    String checkoutDate="";
                    while (eventType != XmlPullParser.END_DOCUMENT) {
                        if(eventType == XmlPullParser.START_TAG) {
                            if(parser.getName().equalsIgnoreCase("code")){
                                parser.next();
                                checkoutCode = parser.getText();
                                parser.next();
                            }
                        }
                        if(eventType == XmlPullParser.START_TAG) {
                            if(parser.getName().equalsIgnoreCase("checkoutDate")){
                                parser.next();
                                checkoutDate = parser.getText();
                                parser.next();
                            }
                        }
                        eventType = parser.next();
                    }
                    final String paymentAddress = activity.getString(R.string.pagseguro_payment_page);
                    final String paymentPage = String.format(paymentAddress, checkoutCode);

                    final Intent pagseguro = new Intent(activity, PagSeguroActivity.class);
                    pagseguro.putExtra("uri", paymentPage);
                    activity.startActivityForResult(pagseguro, PAG_SEGURO_REQUEST_CODE);
                    progressDialog.hide();

                }catch (XmlPullParserException e) {
                    Log.d("PAG_SEGURO", e.getMessage());
                } catch (IOException e) {
                    Log.d("PAG_SEGURO", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                progressDialog.hide();
                StringBuilder errors = new StringBuilder();
                errors.append("List of errors\n");
                errors.append("Statuscode: "+ statusCode + "\n");
                String response = null;
                try {
                    response = new String(responseBody, "ISO-8859-1");
                } catch (UnsupportedEncodingException e) {
                    Log.d("PAG_SEGURO", e.getMessage());
                }
                try {
                    final XmlPullParser parser = Xml.newPullParser();
                    parser.setInput(new StringReader(response));
                    int eventType = parser.getEventType();
                    while (eventType != XmlPullParser.END_DOCUMENT) {
                        if(eventType == XmlPullParser.START_TAG) {
                            if(parser.getName().equalsIgnoreCase("error")){
                                parser.next();
                                errors.append(parser.getText()+"\n");
                                parser.next();
                            }
                        }
                        eventType = parser.next();
                    }
                    Toast.makeText(activity, errors.toString(), Toast.LENGTH_LONG).show();

                }catch (XmlPullParserException e) {
                    Log.d("PAG_SEGURO", e.getMessage());
                } catch (IOException e) {
                    Log.d("PAG_SEGURO", e.getMessage());
                }
            }
        });

    }

}
