package com.eeg.pt1_v1.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.eeg.pt1_v1.R;

//import com.facebook.AccessToken;
//import com.facebook.FacebookSdk;
//import com.facebook.appevents.AppEventsLogger;

/**
 * Created by Jorge Zepeda Tinoco on 7/1/2017.
 */


public class SplashActivity extends BaseActivityLifecycle {
    private static final int TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //inicializa api facebook
        //FacebookSdk.sdkInitialize(getApplicationContext());
        //AppEventsLogger.activateApp(this);

        setContentView(R.layout.activity_splash);
        launchSplash();
    }

    private void launchSplash() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
          //      if(AccessToken.getCurrentAccessToken()==null){

                Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(i);
            /*)    }
                else{
                    goHomeActivity();
                }
            */
                finish();
            }
        }, TIME_OUT);

    }

    private void goSignInActivity(){

    }

    private void goHomeActivity(){
        //Intent i = new Intent(Splash.this, HomeActivity.class);
        //startActivity(i);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
