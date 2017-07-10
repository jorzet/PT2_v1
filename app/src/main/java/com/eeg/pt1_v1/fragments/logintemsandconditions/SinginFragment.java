package com.eeg.pt1_v1.fragments.logintemsandconditions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.eeg.pt1_v1.R;
import com.eeg.pt1_v1.ui.activities.ContentActivity;
import com.eeg.pt1_v1.ui.activities.LoginActivity;

/**
 * Created by Jorge on 7/2/2017.
 */

public class SinginFragment extends Fragment implements View.OnClickListener{

    /* For the View */
    private Button mLogin;
    private TextView mSingUp;
    private TextView mRestartPassword;
    private EditText mEmail;
    private EditText mPassword;

    /* For the SaveInstanceState */
    private static final String EMAIL_TEXT = "email_text";
    private static final String PASSWORD_TEXT = "password_text";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (container == null)
            return null;
        //FacebookSdk.sdkInitialize(getApplicationContext());
        //AppEventsLogger.activateApp(this.getContext());
        View rootView = inflater.inflate(R.layout.signin_fragment,container,false);

        mLogin = (Button) rootView.findViewById(R.id.singin_button);
        mSingUp = (TextView) rootView.findViewById(R.id.link_to_signup);
        mRestartPassword = (TextView) rootView.findViewById(R.id.link_to_forgot);
        mEmail = (EditText) rootView.findViewById(R.id.email_user_singin);
        mPassword = (EditText) rootView.findViewById(R.id.password_user_singin);

        mLogin.setOnClickListener(this);
        mSingUp.setOnClickListener(this);
        mRestartPassword.setOnClickListener(this);

        if(savedInstanceState!=null){
            mEmail.setText(savedInstanceState.getString(EMAIL_TEXT));
            mPassword.setText(savedInstanceState.getString(PASSWORD_TEXT));
        }

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();

        savedInstanceState.putString(EMAIL_TEXT,email);
        savedInstanceState.putString(PASSWORD_TEXT,password);
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.singin_button:
                goHomeActivity();
                break;
            case R.id.link_to_signup:
                goSingupFragment();
                break;
            case R.id.link_to_forgot:
                goForgotFragment();
                break;
        }
    }

    private void goHomeActivity() {
        Intent intent = new Intent(getActivity(), ContentActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    private void goSingupFragment() {
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, new SingupFragment()).addToBackStack(LoginActivity.TAG).commit();

    }
    private void goForgotFragment() {
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, new RestartPasswordFragment()).addToBackStack(LoginActivity.TAG).commit();
    }

}
