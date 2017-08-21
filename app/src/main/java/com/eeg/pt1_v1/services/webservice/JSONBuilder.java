package com.eeg.pt1_v1.services.webservice;

import com.eeg.pt1_v1.entities.Paciente;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Stack;

/**
 * Created by ing_ragde on 13/08/17.
 */

public class JSONBuilder {

    // SPETIAL CHARA
    private static final char OPEN_JSON = '{';
    private static final char CLOSE_JSON = '}';
    private static final char DOUBLE_QUOTES = '"';

    public static boolean validateJsonStructure(String json){
        return true;
        /*Stack pila = new Stack();
        // check if the general structure corresponds to a json object
        for(int i=0;i<json.length();i++){
            if(json.charAt(i)== JSONBuilder.OPEN_JSON)
                pila.add(JSONBuilder.OPEN_JSON);
            else if(json.charAt(i)== JSONBuilder.DOUBLE_QUOTES)
                pila.add(JSONBuilder.DOUBLE_QUOTES);
            else if(json.charAt(i) == JSONBuilder.CLOSE_JSON)
                pila.remove(pila.size()-1);
        }
        return pila.isEmpty();*/
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

    public static Object getObjectFromJson(String json, Class clase){
        return new Gson().fromJson(json, clase);
    }

    public static String getJsonFromJson(String json, String KEY ){
        JSONObject object = null;
        try {
            object = new JSONObject(json);
            return object.getString(KEY);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }
}
