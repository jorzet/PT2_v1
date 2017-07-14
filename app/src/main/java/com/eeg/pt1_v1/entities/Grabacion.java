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
        return this.canal;
    }

    public float getFrecuenciaDominante(){
        return this.frecuenciaDominante;
    }

    public int getSegundo(){
        return this.segundo;
    }

    public double [] getSenial(){
        return this.senial;
    }

    public String getTipoOnda(){
        return this.tipoDeOnda;
    }

    /**
     *
     * @param canal
     */
    public void setCanal(String canal){
        this.canal = canal;
    }

    /**
     *
     * @param frecuenciaDominante
     */
    public void setFrecuenciaDominante(float frecuenciaDominante){
        this.frecuenciaDominante = frecuenciaDominante;
    }

    /**
     *
     * @param segundo
     */
    public void setSegundo(int segundo){
        this.segundo = segundo;
    }

    /**
     *
     * @param senial
     */
    public void setSenial(double [] senial){
        this.senial = senial;
    }

    /**
     *
     * @param tipoOnda
     */
    public void setTipoOnda(String tipoOnda){
        this.tipoDeOnda = tipoOnda;
    }
}//end Grabaci�n