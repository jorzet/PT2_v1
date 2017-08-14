package com.eeg.pt1_v1.services.webservice;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.eeg.pt1_v1.entities.Especialista;
import com.eeg.pt1_v1.entities.Palabras;
import com.eeg.pt1_v1.entities.Usuario;
import com.eeg.pt1_v1.services.webservice.Httpzoid.Http;
import com.eeg.pt1_v1.services.webservice.Httpzoid.HttpFactory;
import com.eeg.pt1_v1.services.webservice.Httpzoid.HttpResponse;
import com.eeg.pt1_v1.services.webservice.Httpzoid.NetworkError;
import com.eeg.pt1_v1.services.webservice.Httpzoid.ResponseHandler;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Jorge Zepeda Tinoco on 13/08/17.
 */

public class MetadataInfo {

    public static final String URL = "http://192.168.1.74:8084/WSEEG/terminalproject/electroencephalography";

    private static final String SING_IN = "/singin/";
    private static final String SING_UP_PATIENT = "/singuppatient/";
    private static final String SING_UP_SPETIALIST = "/singupspetialist/";
    private static final String GET_PATIENT_DATA = "/getpatientdata/";
    private static final String GET_SPETIALIST_DATA = "/getspetialistdata/";
    private static final String GET_ALL_SPETIALIST = "/getallspetialist";
    private static final String GET_PATIENTS_BY_SPETIALIST = "/getpatientsbyspetialist/";
    private static final String GET_PATIENT_SCHEDULE = "/getpatientschedule/";
    private static final String GET_PATIENT_SCHEDULES = "/getpatientschedules/";
    private static final String GET_STUDY_BY_PATIENT = "/getstudybypatient/";

    private String respuesta= "";
    private boolean isFinish = false;

    public String getResponse(){
        return this.respuesta;
    }

    public boolean isRequestDone(){
        return this.isFinish;
    }

    public void requestLogin(String email, String hashPassword, Context context){

        Http http = HttpFactory.create(context);
        http.get(MetadataInfo.URL + MetadataInfo.SING_IN + JSONBuilder.bildLoginJson(email,hashPassword))
                .handler(new ResponseHandler<String>() {
                    @Override
                    public void success(String json, HttpResponse response) {
                        Log.i("respuesta1: ",json);
                        if(json!=null && !json.equals(""))
                            respuesta = json;
                        else
                            respuesta = Palabras.ERROR_FROM_WEB_WERVICE;

                        isFinish = true;
                    }
                }).send();
    }



    public String requestSingupPatient(Usuario user){
        try {
            return HttpRequest
                    .get(MetadataInfo.URL + MetadataInfo.SING_UP_PATIENT )
                    .accept("application/json")
                    .body();
        } catch (HttpRequest.HttpRequestException exception) {
            return Palabras.ERROR_FROM_WEB_WERVICE;
        }
    }

    public String requestSingupSpetialist(Especialista spetialist){
        try {
            return HttpRequest
                    .get(MetadataInfo.URL + MetadataInfo.SING_UP_SPETIALIST )
                    .accept("application/json")
                    .body();
        } catch (HttpRequest.HttpRequestException exception) {
            return Palabras.ERROR_FROM_WEB_WERVICE;
        }
    }

}
