package com.eeg.pt1_v1.entities;

/**
 * @author Jorge
 * @version 1.0
 * @created 02-Jul-2017 1:24:04 PM
 */
public class Usuario {

	protected int age;
	protected String email;
	protected String lastName;
	protected String name;
	protected String password;
	protected byte [] profilePhoto;
	public Datos mDatos;

	public Usuario(){

	}

	public void finalize() throws Throwable {

	}
	public int getAge(){
		return 0;
	}

	public String getEmail(){
		return "";
	}

	public String getLastName(){
		return "";
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
}//end Usuario