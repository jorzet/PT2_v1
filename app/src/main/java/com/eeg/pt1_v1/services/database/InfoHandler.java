package com.eeg.pt1_v1.services.database;

import android.content.Context;
import android.util.Log;

import com.eeg.pt1_v1.entities.Cita;
import com.eeg.pt1_v1.entities.Dispositivo;
import com.eeg.pt1_v1.entities.Especialista;
import com.eeg.pt1_v1.entities.Paciente;
import com.eeg.pt1_v1.entities.Palabras;
import com.eeg.pt1_v1.entities.Usuario;
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
    public void saveDevices(String json){
        DataBase db = new DataBase(mContext);
        db.storeDevices(json);
    }

    public void saveUserAndToken(String json){
        DataBase db = new DataBase(mContext);
        Log.d("MyTAG","json: "+ json);
        String user = JSONBuilder.getJsonFromJson(json, Palabras.USER);
        Log.d("MyTAG", "saveUserAndToken: s" + user);
        db.storeJSONUser(user);

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

    public Usuario getUserInfo(){
        DataBase db = new DataBase(mContext);
        Log.i("MyTAG: ","getUserInfo" + db.getJsonUser() );
        return (Usuario) JSONBuilder.getObjectFromJson(db.getJsonUser(), Usuario.class);
    }

    public void savePatientSchedules(String json){
        DataBase db = new DataBase(mContext);
        db.storeJSONPatientSchedules(json);
    }

    public String getPatientSchedulesJson(){
        DataBase db = new DataBase(mContext);
        return db.getJsonPatientSchedules();
    }

    public String getPatientDevicesJson(){
        DataBase db = new DataBase(mContext);
        return db.getJsonPatientDevices();
    }

    public ArrayList<Cita> getPatientSchedules(String devices, Class clase){
        DataBase db = new DataBase(mContext);
        ArrayList<Object> objects = JSONBuilder.getArrayListFromJsonArray(devices, clase);
        ArrayList<Cita> citas = new ArrayList<>();
        if (objects != null) {
            for (int i = 0; i < objects.size(); i++)
                citas.add((Cita) objects.get(i));
            return citas;
        }
        return null;
    }

    public ArrayList<Dispositivo> getPatientDevices(String schedules, Class clase){
        DataBase db = new DataBase(mContext);
        ArrayList<Object> objects = JSONBuilder.getArrayListFromJsonArray(schedules, clase);
        ArrayList<Dispositivo> dispositivos = new ArrayList<>();
        if (objects != null) {
            for (int i = 0; i < objects.size(); i++)
                dispositivos.add((Dispositivo) objects.get(i));
            return dispositivos;
        }
        return null;
    }

    public Cita getPatientSchedule(int idSchedule){
        Cita c = new Cita();
        String savedSchedules = getPatientSchedulesJson();
        ArrayList<Cita> citas = getPatientSchedules(savedSchedules, Cita.class);
        if (citas != null) {
            for (int i = 0; i <= idSchedule; i++) {
                c = citas.get(i);
            }
        }
        return c;
    }

    public void saveReferceObject(Object object){
        DataBase db = new DataBase(mContext);
        Log.i("MyTAG: ","object name 1: " + object.getClass().toString());
        String json = JSONBuilder.buildObjectReferenceJson(object, object.getClass().toString());
        Log.i("MyTAG: ","json reference: " + json + " object: " + object);
        db.saveReference(json, object.getClass().toString());
    }

    public void saveCurrentSchedule(Cita cita){
        DataBase db = new DataBase(mContext);
        System.out.println("beforeUildJson: ");
        String json = JSONBuilder.buildObjectReferenceJson(cita);
        System.out.println("jsonGenerated: "+json);
        db.saveJsonCurrentSchedule(json);
    }

    public Cita getCurrentScheduele(){
        DataBase db = new DataBase(mContext);
        String json = db.getJsonCurrentSchedule();
        System.out.println("currentSchedule: "+json);
        return (Cita)JSONBuilder.getObjectFromJson(json,Cita.class);
    }

    public Object getReferenceObject(Class clase){
        DataBase db = new DataBase(mContext);
        Log.i("MyTAG: ","object name 2: " + clase.toString());
        String json = db.getReference(clase.toString(), clase);
        Object object = JSONBuilder.getObjectReferenceFromJson(json, clase.toString());
        Log.i("MyTAG: ","object: " + object);
        return object;
    }

    /* Those methods are why the app needs to store the view values
     * when the user opens the app from the notification
     * it produces a null pointer exception */
    public void saveExtraFromActivity(String TAG, String param){
        DataBase db = new DataBase(mContext);
        db.saveExtra(TAG, param);
    }
    public String getExtraStored(String TAG){
        DataBase db = new DataBase(mContext);
        return db.getExtra(TAG);
    }
}
