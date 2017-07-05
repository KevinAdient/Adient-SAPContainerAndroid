package com.adient.mobility.sapcontainer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.IOException;
import java.io.InputStream;

public class LoadPage extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_page);
        webView=(WebView)findViewById(R.id.webview_data);
        webView.setWebViewClient(new MyWebview());
        WebSettings webSettings = webView.getSettings();
        webSettings.setSavePassword(false);
        webSettings.setSaveFormData(false);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(false);

        //CookieSyncManager.createInstance(this);
        //it is default true, but hey...
        //CookieManager.getInstance().setAcceptCookie(true);

        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.getSettings().setSupportZoom(true);


        Intent intent=getIntent();
        String pos=intent.getStringExtra("grade");

        if(pos.equalsIgnoreCase("IRIS Mobile Dashboard")){
           //webView.loadUrl("https://agsm.adient.com/siteminderagent/forms/ag_primary.fcc?TYPE=33554433&REALMOID=06-ee9a9733-4a38-4ee7-95c9-7fe1801058c2&GUID=&SMAUTHREASON=0&METHOD=GET&SMAGENTNAME=8yUsyDSZUFV1o873Kdzm1dYMiJFJF9ekwOP8E9bnkUGqJ5Nn4Wg6ke9iIkL8xSYH&TARGET=-SM-HTTPS%3a%2f%2fbiprod%2eadient%2ecom%2fBOE%2fOpenDocument%2fopendoc%2fopenDocument%2ejsp%3fsIDType%3dCUID%26iDocID%3dAcQWMlBUGSVPs5ELyCwz9Nw");
           // webView.loadUrl("http://biprod.adient.com/BOE/OpenDocument/opendoc/openDocument.jsp?sIDType=CUID&iDocID=AcQWMlBUGSVPs5ELyCwz9Nw");

            webView.loadUrl("http://biprod.adient.com/BOE/OpenDocument/opendoc/openDocument.jsp?sIDType=CUID&iDocID=AdI88wNoxH9PsQqZUej9_DA");


        }

        else if(pos.equalsIgnoreCase("Operations Dashboard")){
            webView.loadUrl("  http://biprod.adient.com/BOE/OpenDocument/opendoc/openDocument.jsp?sIDType=CUID&iDocID=AVcWuinS1f9BigAq4cUBjK0");
            //webView.loadUrl("https://agsm.adient.com/siteminderagent/forms/ag_primary.fcc?TYPE=33554433&REALMOID=06-ee9a9733-4a38-4ee7-95c9-7fe1801058c2&GUID=&SMAUTHREASON=0&METHOD=GET&SMAGENTNAME=8yUsyDSZUFV1o873Kdzm1dYMiJFJF9ekwOP8E9bnkUGqJ5Nn4Wg6ke9iIkL8xSYH&TARGET=-SM-HTTPS%3a%2f%2fbiprod%2eadient%2ecom%2fBOE%2fOpenDocument%2fopendoc%2fopenDocument%2ejsp%3fsIDType%3dCUID%26iDocID%3dAcD8UbmaXElBlgKf7N3UMzI");
        }
        else if(pos.equalsIgnoreCase("Select One")){
            finish();
        }

    }
    class MyWebview extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);

            Log.v("url","url:: "+url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

            injectScriptFile(view, "js/hide.js"); // see below ...

            // test if the script was loaded
            view.loadUrl("javascript:setTimeout(hideNobe(), 500)");
        }
    }

    private void injectScriptFile(WebView view, String scriptFile) {
        InputStream input;
        try {
            input = getAssets().open(scriptFile);
            byte[] buffer = new byte[input.available()];
            input.read(buffer);
            input.close();

            // String-ify the script byte-array using BASE64 encoding !!!
            String encoded = Base64.encodeToString(buffer, Base64.NO_WRAP);
            view.loadUrl("javascript:(function() {" +
                    "var parent = document.getElementsByTagName('head').item(0);" +
                    "var script = document.createElement('script');" +
                    "script.type = 'text/javascript';" +
                    // Tell the browser to BASE64-decode the string into your script !!!
                    "script.innerHTML = window.atob('" + encoded + "');" +
                    "parent.appendChild(script)" +
                    "})()");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
