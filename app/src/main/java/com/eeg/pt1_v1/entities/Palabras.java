package com.eeg.pt1_v1.entities;

/**
 * Created by ing_ragde on 13/08/17.
 */

public class Palabras {

    public static final int ERROR_INCORRECT_PASSWORD =                  0x00;
    public static final int WITHOUT_ACOUNT =                              -1;
    public static final int PATIENT_USER =                              0x01;
    public static final int SPETIALIST_USER =                           0x02;
    public static final int ADMINISTRATOR_USER =                        0x03;

    public static final int CODE_ERROR_NOT_SPETIALISTS =                0x04;
    public static final int CODE_ERROR_NOT_PATIENTS =                   0x05;
    public static final int CODE_ERROR_NOT_SCHEDULES =                  0x06;
    public static final int CODE_ERROR_FROM_JSON =                      0x07;
    public static final int CODE_SYNTAX_ERROR_FROM_JSON =               0x08;
    public static final int CODE_ERROR_HAPPENED_A_PROBLEM =             0x09;
    public static final int CODE_ERROR_SPETIALIST_NOT_EXISTS =          0x0A;
    public static final int CODE_ERROR_PATIENT_NOT_EXISTS =             0x0B;
    public static final int CODE_ERROR_USER_NOT_EXISTS =                0x0B;
    public static final int CODE_ERROR_SCHEDULE_NOT_EXISTS =            0x0D;
    public static final int CODE_ERROR_STUDY_NOT_EXISTS =               0x0E;
    public static final int CODE_ERROR_INCORRECT_EMAIL_OR_PASSWORD =    0x0F;

    public static final int CODE_SUCESSFULL_SING_IN =                   0x10;
    public static final int CODE_SUCESSFULL_SCHEDULE_APOINTMENT =       0x11;
    public static final int CODE_SUCESSFULL_STORE_RECORDING =           0x12;
    public static final int CODE_SUCESSFULL_STORE_GENERAL_RESULTS=      0x13;
    public static final int CODE_SUCESSFULL_STORE_CHANEL_RESULTS =      0x14;
    public static final int CODE_SUCESSFULL_STORE_SEGMENT_RESULTS =     0x15;

    /* log in */
    public static final String SUCESSFULL_LOG_IN = "sucesfull_log_in";
    public static final String ERROR_INCORRECT_EMAIL_OR_PASSWORD = "Error, el usuario y/o contraseña son invalidos";
    public static final String ERROR_EMTY_USER_AND_PASSWORD = "Error, por favor ingresa el usuario y contraseña";

    public static final String ERROR_FROM_WEB_WERVICE = "Error al obtener información del servidor, vuelve a intentarlo más tarde";
    public static final String ERROR_FROM_NETWORK_NOT_CONNECTED = "Error, Conexión a internet no disponible";

    public static final String USER = "User";
    public static final String TOKEN = "Token";
}
