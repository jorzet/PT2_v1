package com.eeg.pt1_v1.services.webservice;

import android.content.Context;

import com.eeg.pt1_v1.entities.Especialista;
import com.eeg.pt1_v1.entities.Palabras;
import com.eeg.pt1_v1.entities.Usuario;
/**
 * Created by Jorge Zepeda Tinoco on 13/08/17.
 */

public class MetadataInfo {

    //public static final String URL = "http://148.204.86.36:8084/WSEEG/terminalproject/electroencephalography";
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

    public String requestLogin(String email, String hashPassword, Context context){
        /*TODO obtain the hash password*/
        if(HttpRequest.isConnected(context))
            return HttpRequest.sendGetRequest(MetadataInfo.URL +
                    MetadataInfo.SING_IN +
                    JSONBuilder.bildLoginJson(email,hashPassword));
        else
            return Palabras.ERROR_FROM_NETWORK_NOT_CONNECTED;

    }

    public String requestSingupPatient(Usuario user){
        return HttpRequest.sendGetRequest(MetadataInfo.URL + MetadataInfo.SING_UP_PATIENT + JSONBuilder.bildSingupJson(user));
    }

    public String requestSingupSpetialist(Especialista spetialist){
        return HttpRequest.sendGetRequest(MetadataInfo.URL + MetadataInfo.SING_UP_SPETIALIST + JSONBuilder.bildSingupJson(spetialist));
    }

    public String requestGetPatientData(int idPatient){
        return HttpRequest.sendGetRequest(MetadataInfo.URL + MetadataInfo.GET_PATIENT_DATA + idPatient);
    }

    public String requestGetSpetialistData(int idSpetialist){
        return HttpRequest.sendGetRequest(MetadataInfo.URL + MetadataInfo.GET_SPETIALIST_DATA + idSpetialist);
    }

    public String requestGetAllSpetialist(){
        return HttpRequest.sendGetRequest(MetadataInfo.URL + MetadataInfo.GET_ALL_SPETIALIST);
    }

    public String requestGetPatientsBySpetialist(int idSpetialist){
        return HttpRequest.sendGetRequest(MetadataInfo.URL + MetadataInfo.GET_PATIENTS_BY_SPETIALIST + idSpetialist);
    }

    public String requestGetPatientSchedule(int idPatient, int idSchedule){
        return HttpRequest.sendGetRequest(MetadataInfo.URL + MetadataInfo.GET_PATIENT_SCHEDULE + JSONBuilder.buildPatientScheduleJson(idPatient,idSchedule));
    }

    public String requestGetPatientSchedules(int idPatient){
        return HttpRequest.sendGetRequest(MetadataInfo.URL + MetadataInfo.GET_PATIENT_SCHEDULES + idPatient);
    }

    public String requestGetStudyByPatient(int idPatient, int idSchedule){
        return HttpRequest.sendGetRequest(MetadataInfo.URL + MetadataInfo.GET_PATIENT_SCHEDULES + JSONBuilder.buildPatientScheduleJson(idPatient,idSchedule));
    }
}
