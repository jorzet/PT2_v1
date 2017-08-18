package com.eeg.pt1_v1.services.database;

import android.content.Context;

import com.eeg.pt1_v1.entities.Paciente;
import com.eeg.pt1_v1.services.webservice.JSONBuilder;

/**
 * Created by jorgezeped on 17/08/17.
 */

public class InfoHandler {
    public static void savePatient(String json, Context context){
        DataBase db = new DataBase(context);
        Paciente patient = (Paciente) JSONBuilder.getObjectFromJson(json, Paciente.class);
        db.storePatient(patient);
    }

    public static Paciente getPatientInfo(Context context){
        Paciente patient = new Paciente();
        DataBase db = new DataBase(context);
        patient = db.getPatient();
        return patient;
    }
}
