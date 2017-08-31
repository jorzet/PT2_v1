package com.eeg.pt1_v1.services.database;

import android.content.Context;
import android.util.Log;

import com.eeg.pt1_v1.entities.Cita;
import com.eeg.pt1_v1.entities.Especialista;
import com.eeg.pt1_v1.entities.Paciente;
import com.eeg.pt1_v1.entities.Palabras;
import com.eeg.pt1_v1.security.AccessToken;
import com.eeg.pt1_v1.services.webservice.JSONBuilder;

import java.util.ArrayList;

/**
 * Created by Jorge Zepeda Tinoco on 17/08/17.
 */

public class InfoHandler {
    Context mContext;
    public InfoHandler(Context context){
        this.mContext = context;
    }

    public void savePatientAndToken(String json){
        DataBase db = new DataBase(mContext);

        String patient = JSONBuilder.getJsonFromJson(json, Palabras.USER);
        db.storeJSONPatient(patient);

        String token = JSONBuilder.getJsonFromJson(json, Palabras.TOKEN);
        AccessToken.setAccessToken(token, mContext);
    }

    public void removePatientAndToken(){
        AccessToken.setAccessToken(null,mContext);
    }

    public void saveSpetilistInfo(String json){
        DataBase db = new DataBase(mContext);

        Especialista spetialist = (Especialista) JSONBuilder.getObjectFromJson(json, Especialista.class);
        Paciente patient = new InfoHandler(mContext).getPatientInfo();
        patient.setEspecialista(spetialist);

        String newJson = JSONBuilder.bildJsonFromObject(patient);

        db.storeJSONPatient(newJson);
    }

    public Paciente getPatientInfo(){
        DataBase db = new DataBase(mContext);
        Log.i("MyTAG: ",db.getJsonPatient() );
        return (Paciente) JSONBuilder.getObjectFromJson(db.getJsonPatient(), Paciente.class);
    }

    public void savePatientSchedules(String json){
        DataBase db = new DataBase(mContext);
        db.storeJSONPatientSchedules(json);
    }

    public String getPatientSchedulesJson(){
        DataBase db = new DataBase(mContext);
        return db.getJsonPatientSchedules();
    }

    public ArrayList<Cita> getPatientSchedules(String schedules, Class clase){
        DataBase db = new DataBase(mContext);
        ArrayList<Object> objects = JSONBuilder.getArrayListFromJsonArray(schedules, clase);
        ArrayList<Cita> citas = new ArrayList<>();
        if (objects != null) {
            for (int i = 0; i < objects.size(); i++)
                citas.add((Cita) objects.get(i));
            return citas;
        }
        return null;
    }

    public Cita getPatientSchedule(int idSchedule){
        Cita c = new Cita();
        return c;
    }

    public void saveReferceObject(Object object){
        DataBase db = new DataBase(mContext);
        Log.i("MyTAG: ","object name 1: " + object.getClass().toString());
        String json = JSONBuilder.buildObjectReferenceJson(object, object.getClass().toString());
        Log.i("MyTAG: ","json reference: " + json + " object: " + object);
        db.saveReference(json, object.getClass().toString());
    }

    public Object getReferenceObject(Class clase){
        DataBase db = new DataBase(mContext);
        Log.i("MyTAG: ","object name 2: " + clase.toString());
        String json = db.getReference(clase.toString(), clase);
        Object object = JSONBuilder.getObjectReferenceFromJson(json, clase.toString());
        Log.i("MyTAG: ","object: " + object);
        return object;
    }
}
