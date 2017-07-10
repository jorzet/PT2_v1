package com.eeg.pt1_v1.entities;

/**
 * @author Jorge
 * @version 1.0
 * @created 02-Jul-2017 1:24:04 PM
 */
public class Datos {


	public Datos(){

	}

	public void finalize() throws Throwable {

	}
	/**
	 * 
	 * @param dato
	 * @param tipo
	 * @param id
	 */
	public int actualizarInformacionUsuario(String dato, String tipo, int id){
		return 0;
	}

	/**
	 * 
	 * @param correo
	 */
	public Usuario consultarUsuario(String correo){
		return null;
	}

	/**
	 * 
	 * @param medico
	 * @param paciente
	 */
	public Cita obtenerCita(Medico medico, Paciente paciente){
		return null;
	}

	/**
	 * 
	 * @param paciente
	 */
	public Estudio obtenerEstudio(Paciente paciente){
		return null;
	}

	/**
	 * 
	 * @param cita
	 */
	public int realizarCita(Cita cita){
		return 0;
	}

	/**
	 * 
	 * @param usuario
	 */
	public String registrarUsuario(Usuario usuario){
		return "";
	}
}//end Datos