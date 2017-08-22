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
 * Created by Jorge Zepeda Tinoco on 7/2/2017.
 */

public class RestartPasswordFragment extends Fragment{

    /* For the View */
    private EditText mEmail;
    private Button mRestartPassword;

    /* For the SaveInstanceState */
    private static final String EMAIL_USER_FORGOT = "email_user_forgot";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if(container == null)
            return null;

        View rootView = inflater.inflate(R.layout.forgot_fragment,container,false);
        mEmail = (EditText) rootView.findViewById(R.id.email_user_forgot);
        mRestartPassword = (Button) rootView.findViewById(R.id.restart_password_button);

        if(savedInstanceState != null)
            mEmail.setText(EMAIL_USER_FORGOT);

        return rootView;

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString(EMAIL_USER_FORGOT, mEmail.getText().toString());
    }

}
