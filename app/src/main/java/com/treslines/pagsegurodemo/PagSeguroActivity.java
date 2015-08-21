package com.treslines.pagsegurodemo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class PagSeguroActivity extends ActionBarActivity {

    private WebView pagSeguroWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_PROGRESS);
        this.setProgressBarVisibility(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pag_seguro);
        pagSeguroWebView = (WebView)findViewById(R.id.pagSeguroView);
        pagSeguroWebView.setWebViewClient(new PagSeguroWebViewClient());
        pagSeguroWebView .getSettings().setJavaScriptEnabled(true);
        pagSeguroWebView .getSettings().setDomStorageEnabled(true);
        final Activity activity = this;
        pagSeguroWebView.setWebChromeClient(new WebChromeClient() {
            // keep changing loading message till process is completed
            public void onProgressChanged(WebView view, int progress) {
                if(activity.getTitle().toString().equalsIgnoreCase(getString(R.string.loading_3))){
                    activity.setTitle(getString(R.string.loading_1));
                }else if(activity.getTitle().toString().equalsIgnoreCase(getString(R.string.loading_1))){
                    activity.setTitle(getString(R.string.loading_2));
                }else if(activity.getTitle().toString().equalsIgnoreCase(getString(R.string.loading_2))){
                    activity.setTitle(getString(R.string.loading_3));
                }else {
                    activity.setTitle(getString(R.string.loading_3));
                }
                activity.setProgress(progress * 100);
                if (progress == 100) {
                    activity.setTitle(view.getTitle().split(" - ")[0]);
                }
            }
        });
        // load pagseguro payment page into view
        pagSeguroWebView.loadUrl(getIntent().getStringExtra("uri"));
    }

    private String actualPage;
    public class PagSeguroWebViewClient extends WebViewClient{
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            actualPage=url;
            // we need this to know when is time to navigate back to our android app
            // this url (www.yourcompany.com) can be definied in you pagseguro account
            if(url.contains("www.google.com")) {
                // confirmation
                Intent data = new Intent();
                data.putExtra(PagSeguroPayment.PAG_SEGURO_EXTRA, PagSeguroPayment.PAG_SEGURO_REQUEST_SUCCESS_CODE);
                setResult(PagSeguroPayment.PAG_SEGURO_REQUEST_CODE, data);
                finish();
            } else{
                super.onPageStarted(view, url, favicon);
            }
        }
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            // show loading error
            Toast.makeText(PagSeguroActivity.this, "Oh no! " + description, Toast.LENGTH_LONG).show();
            // navigate back to android app
            Intent data = new Intent();
            data.putExtra(PagSeguroPayment.PAG_SEGURO_EXTRA, PagSeguroPayment.PAG_SEGURO_REQUEST_FAILURE_CODE);
            setResult(PagSeguroPayment.PAG_SEGURO_REQUEST_CODE, data);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        if(actualPage.contains("conclusion")){
            Intent data = new Intent();
            data.putExtra(PagSeguroPayment.PAG_SEGURO_EXTRA, PagSeguroPayment.PAG_SEGURO_REQUEST_SUCCESS_CODE);
            setResult(PagSeguroPayment.PAG_SEGURO_REQUEST_CODE, data);
            finish();
        }
        else if (pagSeguroWebView.canGoBack()) {
            pagSeguroWebView.goBack();
        } else {
            AppUtil.showOkCancelDialog(this, getString(R.string.cancel_pagseguro), null, null, new AppUtil.AlertAction() {
                @Override
                public void perform() {
                    // go back to app
                    Intent data = new Intent();
                    data.putExtra(PagSeguroPayment.PAG_SEGURO_EXTRA, PagSeguroPayment.PAG_SEGURO_REQUEST_CANCELLED_CODE);
                    setResult(PagSeguroPayment.PAG_SEGURO_REQUEST_CODE, data);
                    finish();
                }
            }, new AppUtil.AlertAction() {
                @Override
                public void perform() {
                    // if cancel pressed do nothing
                }
            }, new AppUtil.AlertAction() {
                @Override
                public void perform() {
                    // if back pressed threat like cancel
                }
            });
        }
    }


}
