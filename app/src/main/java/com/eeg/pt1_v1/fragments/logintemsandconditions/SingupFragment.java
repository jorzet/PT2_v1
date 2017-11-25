package com.eeg.pt1_v1.fragments.logintemsandconditions;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eeg.pt1_v1.R;
import com.eeg.pt1_v1.entities.Paciente;
import com.eeg.pt1_v1.entities.Palabras;
import com.eeg.pt1_v1.entities.Usuario;
import com.eeg.pt1_v1.security.Hash;
import com.eeg.pt1_v1.services.database.InfoHandler;
import com.eeg.pt1_v1.services.webservice.MetadataInfo;
import com.eeg.pt1_v1.ui.activities.ContentActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.Objects;

/**
 * Created by Jorge Zepeda Tinoco on 08/07/17.
 */

public class SingupFragment extends Fragment implements View.OnClickListener{

    /* For the View */
    private EditText mName;
    private EditText mFirstLastName;
    private EditText mSecondLastName;
    private EditText mAge;
    private EditText mIllness;
    private EditText mEmail;
    private EditText mPassword;
    private EditText mPasswordConfirmation;
    private CheckBox isMale;
    private CheckBox isFemale;
    private Button mSingup;

    private TextView mErrorSingup;
    private ImageView mLogo;
    private ProgressBar mProgressBar;
    private View mInputSingup;

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
        mFirstLastName = (EditText) rootView.findViewById(R.id.fistlastname_user_singup);
        mSecondLastName = (EditText) rootView.findViewById(R.id.secondlastname_user_singup);
        mAge = (EditText) rootView.findViewById(R.id.age_user_singup);
        mIllness = (EditText) rootView.findViewById(R.id.illness_user_singup);
        mEmail = (EditText) rootView.findViewById(R.id.email_user_singup);
        mPassword = (EditText) rootView.findViewById(R.id.password_user_singup);
        mPasswordConfirmation = (EditText) rootView.findViewById(R.id.password_confirmation_user_singup);
        isMale = (CheckBox) rootView.findViewById(R.id.is_male);
        isFemale = (CheckBox) rootView.findViewById(R.id.is_female);
        mSingup = (Button) rootView.findViewById(R.id.singup_button);

        mErrorSingup = (TextView) rootView.findViewById(R.id.error_singup);
        mLogo = (ImageView) rootView.findViewById(R.id.imgLogo);
        mProgressBar = (ProgressBar) rootView.findViewById(R.id.singup_progress);
        mInputSingup = (View) rootView.findViewById(R.id.inputsWrapper);

        mSingup.setOnClickListener(this);
        isMale.setOnClickListener(this);
        isFemale.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.is_male:
                isMale.setChecked(true);
                isFemale.setChecked(false);
                break;
            case R.id.is_female:
                isFemale.setChecked(true);
                isMale.setChecked(false);
                break;
            case R.id.singup_button:
                doSingUp();
                break;
        }

    }

    private void doSingUp(){
        String name = mName.getText().toString();
        String fistLastNames = mFirstLastName.getText().toString();
        String secondLastName = mSecondLastName.getText().toString();
        String age = mAge.getText().toString();
        String illnes = mIllness.getText().toString();
        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();
        String passwordConf = mPasswordConfirmation.getText().toString();
        String gender = (isMale.isChecked())? ("Masculino"):("Femenino");

        if(!name.equals("") && !fistLastNames.equals("") && !secondLastName.equals("") &&
                !age.equals("") && !illnes.equals("") && !email.equals("") && !password.equals("") &&
                !passwordConf.equals("") && (isMale.isChecked() || isFemale.isChecked())){
            if(password.equals(passwordConf)) {
                mErrorSingup.setText("");
                new SIngUp().execute(name, fistLastNames, secondLastName, age, illnes, email, password, gender);
                mInputSingup.setVisibility(View.GONE);
                mLogo.setVisibility(View.VISIBLE);
                mProgressBar.setVisibility(View.VISIBLE);
            }else
                mErrorSingup.setText(Palabras.ERROR_PASSWORDS_NOT_MATCH);
        }
        else{
            mErrorSingup.setText(Palabras.ERROR_EMTY_INPUTS);
        }


    }

    private void goHomeActivity() {
        Intent intent = new Intent(getActivity(), ContentActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    private class SIngUp extends AsyncTask<String, Long, String>{

        @Override
        protected String doInBackground(String... params) {
            Paciente user = new Paciente();
            user.setId(0);//Tiene que ir
            user.setName(params[0]);
            user.setFistLastName(params[1]);
            user.setSecondLastName(params[2]);
            user.setAge(Integer.parseInt(params[3]));
            user.setPadecimiento(params[4]);
            user.setEmail(params[5]);
            user.setPassword(new String(new Hash().getHash(params[6].getBytes())));
            user.setGender(params[7]);
            Drawable d = getResources().getDrawable(R.drawable.ic_profile);
            Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] profilePhoto = stream.toByteArray();
            user.setProfilePhoto(profilePhoto);
            return new MetadataInfo().requestSingupPatient(user, getContext());
        }

        protected void onPostExecute(String response) {
            if(response==null || response.equals("")) {
                mErrorSingup.setText(Palabras.ERROR_FROM_WEB_WERVICE);
                mLogo.setVisibility(View.GONE);
                mProgressBar.setVisibility(View.GONE);
                mInputSingup.setVisibility(View.VISIBLE);
            }
            else if(response.equals(Palabras.ERROR_FROM_NETWORK_NOT_CONNECTED)) {
                mErrorSingup.setText(Palabras.ERROR_FROM_NETWORK_NOT_CONNECTED);
                mLogo.setVisibility(View.GONE);
                mProgressBar.setVisibility(View.GONE);
                mInputSingup.setVisibility(View.VISIBLE);
            }
            else if(response.contains("Error") && response.contains("Message")) {
                try {
                    JSONObject json = new JSONObject(response);
                    int codeError = json.getInt("Error");
                    String message = json.getString("Message");

                    mErrorSingup.setText(message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mLogo.setVisibility(View.GONE);
                mProgressBar.setVisibility(View.GONE);
                mInputSingup.setVisibility(View.VISIBLE);
            }
            else {
                new InfoHandler(getContext()).savePatientSchedules(response);
                goHomeActivity();
            }
        }
    }

}
