package com.eeg.pt1_v1.entities;

import java.util.Date;

/**
 * @author Jorge
 * @version 1.0
 * @created 02-Jul-2017 1:24:04 PM
 */
public class Estudio {

	private Date fecha;
	private String tiempo;
	public Paciente m_Paciente;
	public Datos m_Datos;

	public Estudio(){

	}

	public void finalize() throws Throwable {

	}
	public Date getFecha(){
		return this.fecha;
	}

	public String getTiempo(){
		return this.tiempo;
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
	 * @param tiempo
	 */
	public void setTiempo(String tiempo){
		this.tiempo = tiempo;
	}
}//end Estudio