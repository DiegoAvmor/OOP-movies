package com.example.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Profile {
	
	private long id;
	private String username;
	private String password;
	private String description;
	
	public Profile() {}
	
	public Profile(String username, String password) 
	{
		this.username= username;
		this.password= password;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * <p>Metodo para verificar si dos perfiles son identicos.
	 * Utiliza los atributos username y password para checar si son identicos.
	 * @param profile
	 * @return
	 */
	public boolean equalsLogin(Object obj) 
	{
		boolean flag=false;
		if(obj instanceof Profile && obj!=null ) 
		{
			Profile aux= (Profile)obj;
			if(this.getUsername().equals(aux.getUsername()) && this.getPassword().equals(aux.getPassword())) 
			{
				flag= true;
			}
		}
		return flag;
	}
	/**
	 * <p> Verifica la existencia de un perfil en la base de datos
	 */
	public boolean equals(Object obj) 
	{
		boolean flag=false;
		if(obj instanceof Profile && obj!=null ) 
		{
			Profile aux= (Profile)obj;
			if(this.getUsername().equals(aux.getUsername())) 
			{
				flag= true; //Significa que ya existe un perfil con este nombre de usuario
			}
		}
		return flag;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
