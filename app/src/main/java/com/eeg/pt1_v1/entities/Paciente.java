package com.eeg.pt1_v1.entities;

/**
 * @author Jorge
 * @version 1.0
 * @created 02-Jul-2017 1:24:04 PM
 */
public class Paciente extends Usuario {

	public Cita m_Cita;

	public Paciente(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}
	public int getAge(){
		return 0;
	}

	public String getEmail(){
		return "";
	}

	public Estudio getEstudio(){
		return null;
	}

	public String getLastName(){
		return "";
	}

	public Medico getMedico(){
		return null;
	}

	public String getName(){
		return "";
	}

	public String getPassword(){
		return "";
	}

	public byte [] getPrifilePhoto(){
		return null;
	}

	/**
	 * 
	 * @param age
	 */
	public void setAge(int age){

	}

	/**
	 * 
	 * @param email
	 */
	public void setEmail(String email){

	}

	/**
	 * 
	 * @param lastName
	 */
	public void setLastName(String lastName){

	}

	/**
	 * 
	 * @param medico
	 */
	public void setMedico(Medico medico){

	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name){

	}

	/**
	 * 
	 * @param password
	 */
	public void setPassword(String password){

	}

	/**
	 * 
	 * @param profilePhoto
	 */
	public void setProfilePhoto(byte [] profilePhoto){

	}
}//end Paciente