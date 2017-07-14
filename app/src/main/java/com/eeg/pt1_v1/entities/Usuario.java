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
		return this.age;
	}

	public String getEmail(){
		return this.email;
	}

	public String getLastName(){
		return this.lastName;
	}

	public String getName(){
		return this.name;
	}

	public String getPassword(){
		return this.password;
	}

	public byte [] getPrifilePhoto(){
		return this.profilePhoto;
	}

	/**
	 * 
	 * @param age
	 */
	public void setAge(int age){
		this.age = age;

	}

	/**
	 * 
	 * @param email
	 */
	public void setEmail(String email){
		this.email = email;
	}

	/**
	 * 
	 * @param lastName
	 */
	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * 
	 * @param password
	 */
	public void setPassword(String password){
		this.password = password;
	}

	/**
	 * 
	 * @param profilePhoto
	 */
	public void setProfilePhoto(byte [] profilePhoto){
		this.profilePhoto = profilePhoto;
	}
}//end Usuario