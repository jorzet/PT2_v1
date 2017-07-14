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
		return this.fecha;
	}

	public String getHora(){
		return this.hora;
	}

	public Medico getMedico(){
		return this.medico;
	}

	public String getObservaciones(){
		return this.observaciones;
	}

	public Paciente getPaciente(){
		return this.paciente;
	}

	/**
	 * 
	 * @param fecha
	 */
	public void setFecha(Date fecha){
		this.fecha = fecha;
	}

	/**
	 * 
	 * @param hora
	 */
	public void setHora(String hora){
		this.hora = hora;
	}

	/**
	 * 
	 * @param medico
	 */
	public void setMedico(Medico medico){
		this.medico = medico;
	}

	/**
	 * 
	 * @param observaciones
	 */
	public void setObservaciones(String observaciones){
		this.observaciones = observaciones;
	}

	/**
	 * 
	 * @param paciente
	 */
	public void setPaciente(Paciente paciente){
		this.paciente = paciente;
	}
}//end Cita