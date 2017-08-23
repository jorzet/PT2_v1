package com.eeg.pt1_v1.ui.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.widget.EditText;

import com.eeg.pt1_v1.R;
import com.eeg.pt1_v1.entities.Paciente;
import com.eeg.pt1_v1.services.database.InfoHandler;

/**
 * Created by jorgezeped on 22/08/17.
 */

public class SettingsActivity extends BaseActivityLifecycle{
    private EditText mUserName;
    private EditText mUserFirstLastName;
    private EditText mUserSecondLastName;
    private EditText mUserAge;
    private EditText mUserIllness;
    private EditText mUserEmail;
    private EditText mUserPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_fragment);

        mUserName = (EditText) findViewById(R.id.edit_user_name);
        mUserFirstLastName = (EditText) findViewById(R.id.edit_user_fistLastName);
        mUserSecondLastName = (EditText) findViewById(R.id.edit_user_secondLastName);
        mUserAge = (EditText) findViewById(R.id.edit_user_age);
        mUserIllness = (EditText) findViewById(R.id.edit_user_illness);
        mUserEmail = (EditText) findViewById(R.id.edit_user_email);
        mUserPassword = (EditText) findViewById(R.id.edit_user_password);
        loadUserData();
    }

    private void loadUserData(){
        Paciente patient = new InfoHandler(getApplication()).getPatientInfo();

        mUserName.setText(patient.getName());
        mUserFirstLastName.setText(patient.getFirstLastName());
        mUserSecondLastName.setText(patient.getSecondLastName());
        mUserAge.setText("" + patient.getAge());
        mUserIllness.setText(patient.getPadecimiento());
        mUserEmail.setText(patient.getEmail());
        mUserPassword.setText("**************");
    }
}
