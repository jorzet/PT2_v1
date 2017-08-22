package com.eeg.pt1_v1.fragments.logintemsandconditions;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.eeg.pt1_v1.R;

/**
 * Created by Jorge Zepeda Tinoco on 08/07/17.
 */

public class SingupFragment extends Fragment {

    /* For the View */
    private EditText mName;
    private EditText mLastName;
    private EditText mEmail;
    private EditText mPassword;
    private EditText mPasswordConfirmation;
    private Button mSingup;

    /* For the SaveInstanceState */
    private static final String NAME_USER_SINGUP = "name_user_singup";
    private static final String LASTNAME_USER_SINGUP = "lastname_user_singup";
    private static final String EMAIL_USER_SINGUP = "email_user_singup";
    private static final String PASSWORD_USER_SINGUP = "password_user_singup";
    private static final String PASSWORD_CONFIRMATION_USER_SINGUP = "password_confirmation_user_singup";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(container == null)
            return null;

        View rootView = inflater.inflate(R.layout.signup_fragment,container,false);
        mName = (EditText) rootView.findViewById(R.id.name_user_singup);
        mLastName = (EditText) rootView.findViewById(R.id.lastname_user_singup);
        mEmail = (EditText) rootView.findViewById(R.id.email_user_singup);
        mPassword = (EditText) rootView.findViewById(R.id.password_user_singup);
        mPasswordConfirmation = (EditText) rootView.findViewById(R.id.password_confirmation_user_singup);
        mSingup = (Button) rootView.findViewById(R.id.singup_button);


        if(savedInstanceState != null){
            mName.setText(NAME_USER_SINGUP);
            mLastName.setText(LASTNAME_USER_SINGUP);
            mEmail.setText(EMAIL_USER_SINGUP);
            mPassword.setText(PASSWORD_USER_SINGUP);
            mPasswordConfirmation.setText(PASSWORD_CONFIRMATION_USER_SINGUP);
        }

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString(NAME_USER_SINGUP, mName.getText().toString());
        savedInstanceState.putString(LASTNAME_USER_SINGUP, mLastName.getText().toString());
        savedInstanceState.putString(EMAIL_USER_SINGUP, mEmail.getText().toString());
        savedInstanceState.putString(PASSWORD_USER_SINGUP, mPassword.getText().toString());
        savedInstanceState.putString(PASSWORD_CONFIRMATION_USER_SINGUP, mPasswordConfirmation.getText().toString());
    }



}
