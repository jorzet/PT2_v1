package com.eeg.pt1_v1.entities;

/**
 * @author Jorge
 * @version 1.0
 * @created 02-Jul-2017 1:24:04 PM
 */
public class Grabacion {

    private String canal;
    private float frecuenciaDominante;
    private int segundo;
    private double [] senial;
    private String tipoDeOnda;
    public Estudio m_Estudio;
    public Datos m_Datos;

    public Grabacion(){

    }

    public void finalize() throws Throwable {

    }
    public String getCanal(){
        return "";
    }

    public float getFrecuenciaDominante(){
        return 0;
    }

    public int getSegundo(){
        return 0;
    }

    public double [] getSenial(){
        return null;
    }

    public String getTipoOnda(){
        return "";
    }

    /**
     *
     * @param canal
     */
    public void setCanal(String canal){

    }

    /**
     *
     * @param frecuenciaDominante
     */
    public void setFrecuenciaDominante(float frecuenciaDominante){

    }

    /**
     *
     * @param segundo
     */
    public void setSegundo(int segundo){

    }

    /**
     *
     * @param senial
     */
    public void setSenial(double [] senial){

    }

    /**
     *
     * @param tipoOnda
     */
    public void setTipoOnda(String tipoOnda){

    }
}//end Grabaci�n