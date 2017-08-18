package com.eeg.pt1_v1.services.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.eeg.pt1_v1.entities.Especialista;
import com.eeg.pt1_v1.entities.Paciente;

/**
 * Created by Jorge Zepeda Tinoco on 17/08/17.
 */

public class DataBase extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "PT.db";
    private static final String TABLE_PATIENT = "Paciente";
    private static final String TABLE_SPETIALIST = "Especialista";
    private static final String TABLE_SCHEDULE = "Cita";
    private static final String TABLE_STUDY = "Estudio";

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS ";

    private static final String PATIENT_ATRIBUTS = "(idPaciente integer primary key," +
                                                    "idEspecialista integer," +
                                                    "name text," +
                                                    "firstLastName text," +
                                                    "secondLastName text," +
                                                    "illness text" +
                                                    "age integer," +
                                                    "email text," +
                                                    "gender text) ";
    private static final String SPETIALIST_ATRIBUTS = "idPaciente integer primary key," +
                                                    "name text," +
                                                    "firstLastName text," +
                                                    "secondLastName text," +
                                                    "age integer," +
                                                    "email text," +
                                                    "gender text) ";

    public DataBase(Context context){
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                CREATE_TABLE + TABLE_PATIENT + PATIENT_ATRIBUTS
                //CREATE_TABLE + TABLE_SPETIALIST + SPETIALIST_ATRIBUTS
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean removePatient(){

        return true;
    }

    public boolean storePatient(Paciente patient){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("idPaciente", patient.getId());
        contentValues.put("idEspecialista", patient.getEspecialista().getId());
        contentValues.put("nombre", patient.getName());
        contentValues.put("firstLastName", patient.getFirstLastName());
        contentValues.put("secondLastName", patient.getSecondLastName());
        contentValues.put("illness", patient.getPadecimiento());
        contentValues.put("age", patient.getAge());
        contentValues.put("email", patient.getEmail());
        contentValues.put("gender", patient.getGender());
        db.insert(DataBase.TABLE_PATIENT, null, contentValues);
        return true; // just when all lines executed well
    }


    public Paciente getPatient(){
        Paciente patient = new Paciente();
        Especialista spetialist = new Especialista();
        String query = "SELECT * FROM " + TABLE_PATIENT;// + " WHERE " + COLUMN_PRODUCTNAME + " =  \"" + productname + "\"";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);


        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            patient.setId(Integer.parseInt(cursor.getString(0)));
            spetialist.setId(Integer.parseInt(cursor.getString(1)));
            patient.setEspecialista(spetialist);
            patient.setName(cursor.getString(2));
            patient.setFistLastName(cursor.getString(3));
            patient.setSecondLastName(cursor.getString(4));
            patient.setPadecimiento(cursor.getString(5));
            patient.setAge(Integer.parseInt(cursor.getString(6)));
            patient.setEmail(cursor.getString(7));
            patient.setGender(cursor.getString(8));
            cursor.close();
        } else {
            patient = null;
        }
        db.close();
        return patient;
    }
}
