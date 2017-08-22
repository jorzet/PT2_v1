package com.eeg.pt1_v1.entities;
/**
 * @author Jorge Zepeda Tinoco
 * @version 1.0
 * @created 02-Jul-2017 1:24:04 PM
 */
public class Paciente extends Usuario {

    private int age;
    private String padecimiento;
    private Especialista especialista;

    public Paciente(){}

    public int getAge(){
        return this.age;
    }


    public Especialista getEspecialista(){
        return this.especialista;
    }

    public String getPadecimiento(){
        return this.padecimiento;
    }

    /**
     *
     * @param age
     */
    public void setAge(int age){
        this.age = age;
    }

    public void setEspecialista(Especialista especialista){
        this.especialista = especialista;
    }

    public void setPadecimiento(String padecimiento){
        this.padecimiento = padecimiento;
    }
}//end Paciente