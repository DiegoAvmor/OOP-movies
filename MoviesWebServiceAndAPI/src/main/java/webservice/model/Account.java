package webservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "accounts")
@XmlRootElement
public class Account {
	@Id
	private String username;
	private String password;
	private String summary;
	
	public Account() {}
	
	public Account(String username, String password)
	{
		this.username= username;
		this.password= password;
	}

	public Account(String username, String password, String summary) {
		this.username = username;
		this.password = password;
		this.summary = summary;
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
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	/**
	 * <p>Metodo para verificar si dos perfiles son identicos.
	 * Utiliza los atributos username y password para checar si son identicos.
	 * @param obj
	 * @return
	 */
	public boolean equalsLogin(Object obj) 
	{
		boolean flag=false;
		if(obj instanceof Account && obj!=null )
		{
			Account aux= (Account)obj;
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
		if(obj instanceof Account && obj!=null )
		{
			Account aux= (Account)obj;
			if(this.getUsername().equals(aux.getUsername())) 
			{
				flag= true; //Significa que ya existe un perfil con este nombre de usuario
			}
		}
		return flag;
	}
}
