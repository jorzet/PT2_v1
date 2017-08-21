package com.eeg.pt1_v1.services.database;

import android.content.Context;

import com.eeg.pt1_v1.entities.Paciente;
import com.eeg.pt1_v1.entities.Palabras;
import com.eeg.pt1_v1.security.AccessToken;
import com.eeg.pt1_v1.services.webservice.JSONBuilder;

/**
 * Created by jorgezeped on 17/08/17.
 */

public class InfoHandler {
    Context mContext;
    public InfoHandler(Context context){
        this.mContext = context;
    }

    public void savePatient(String json){
        DataBase db = new DataBase(mContext);

        String patient = JSONBuilder.getJsonFromJson(json, Palabras.USER);
        db.storeJSONPatient(patient);

        String token = JSONBuilder.getJsonFromJson(json, Palabras.TOKEN);
        AccessToken.setAccessToken(token, mContext);
    }

    public Paciente getPatientInfo(){
        DataBase db = new DataBase(mContext);
        return (Paciente) JSONBuilder.getObjectFromJson(db.getJsonPatient(), Paciente.class);
    }
}
