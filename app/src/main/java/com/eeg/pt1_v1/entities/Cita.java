package com.eeg.pt1_v1.entities;

import java.util.Date;

/**
 * @author Jorge
 * @version 1.0
 * @created 02-Jul-2017 1:24:04 PM
 */
public class Cita {

	private Date fecha;
	private String hora;
	private Medico medico;
	private String observaciones;
	private Paciente paciente;
	public Datos mDatos;

	public Cita(){

	}

	public void finalize() throws Throwable {

	}
	public Date getFecha(){
		return null;
	}

	public String getHora(){
		return "";
	}

	public Medico getMedico(){
		return null;
	}

	public String getObservaciones(){
		return "";
	}

	public Paciente getPaciente(){
		return null;
	}

	/**
	 * 
	 * @param fecha
	 */
	public void setFecha(Date fecha){

	}

	/**
	 * 
	 * @param hora
	 */
	public void setHora(String hora){

	}

	/**
	 * 
	 * @param medico
	 */
	public void setMedico(Medico medico){

	}

	/**
	 * 
	 * @param observaciones
	 */
	public void setObservaciones(String observaciones){

	}

	/**
	 * 
	 * @param paciente
	 */
	public void setPaciente(Paciente paciente){

	}
}//end Cita