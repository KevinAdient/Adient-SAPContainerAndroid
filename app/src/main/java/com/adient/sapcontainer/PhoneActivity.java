package com.adient.sapcontainer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.digits.sdk.android.*;
import com.digits.sdk.android.DigitsException;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import io.fabric.sdk.android.Fabric;

public class PhoneActivity extends AppCompatActivity{

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "w7fJRbXIRBhMzT9qp3lKNohty";
    private static final String TWITTER_SECRET = "NjZ1840eJgBUv3h8G2qYWJbZ96wYfsj9mzQnLRbXHasWqgJffT";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new TwitterCore(authConfig), new Digits.Builder().build());
        sharedPreferences=getSharedPreferences("sap",MODE_PRIVATE);
        String ref=sharedPreferences.getString("validate",null);

        if(ref!=null&&!ref.isEmpty()){
            Intent intent=new Intent(PhoneActivity.this,FingerPrintPage.class);
            startActivity(intent);

            finish();
        }
            setContentView(R.layout.activity_phone);




        DigitsAuthButton digitsButton = (DigitsAuthButton) findViewById(R.id.auth_button);
        digitsButton.setCallback(new AuthCallback() {
            @Override
            public void success(DigitsSession session, String phoneNumber) {
                sharedPreferences=getSharedPreferences("sap",MODE_PRIVATE);
                editor=sharedPreferences.edit();
                editor.putString("validate","success");
                editor.commit();


                Toast.makeText(getApplicationContext(), "Authentication successful for "
                        + phoneNumber, Toast.LENGTH_LONG).show();
                Intent intent=new Intent(PhoneActivity.this,FingerPrintPage.class);
                startActivity(intent);
            }

            @Override
            public void failure(DigitsException error) {
                Log.d("Digits", "Sign in with Digits failure", error);

            }
        });




}}
