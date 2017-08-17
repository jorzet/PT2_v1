package com.eeg.pt1_v1.services.webservice;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ing_ragde on 13/08/17.
 */

public class JSONBuilder {

    public static boolean validateJsonStructure(String json){
        return true;
    }

    public static String bildLoginJson(String email, String password){
        JSONObject json = new JSONObject();
        try {
            json.put("email",email);
            json.put("password",password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json.toString().replace("{","%7B").replace("}","%7D");
    }

    public static String buildPatientScheduleJson(int idPatient, int idSchedule){
        JSONObject json = new JSONObject();
        try {
            json.put("idPaciente",idPatient);
            json.put("folioCita",idSchedule);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json.toString().replace("{","%7B").replace("}","%7D");
    }

    public static String bildSingupJson(Object object){
        return new Gson().toJson(object).replace("{","%7B").replace("}","%7D");
    }
}
