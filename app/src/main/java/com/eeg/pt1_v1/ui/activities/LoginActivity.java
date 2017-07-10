package com.eeg.pt1_v1.ui.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.eeg.pt1_v1.R;
import com.eeg.pt1_v1.fragments.logintemsandconditions.SinginFragment;

/**
 * Created by Jorge Zepeda Tinoco on 7/1/2017.
 */

public class LoginActivity extends AppCompatActivity{

    public static final String TAG = "login_activity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container);

        if (savedInstanceState == null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.fragment_container, new SinginFragment());
            ft.commit();
        }
    }

}
