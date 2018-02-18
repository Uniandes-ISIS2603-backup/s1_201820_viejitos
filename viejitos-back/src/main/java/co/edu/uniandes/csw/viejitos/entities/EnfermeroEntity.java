package co.edu.uniandes.csw.viejitos.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement

public class EnfermeroEntity extends BaseEntity implements Serializable{
	private String cv;

	private String login;

	private String contrasenia;

	private Integer tipo;

	public void setCV(String cv){
		this.cv = cv;
	}
	
	public String getCV(){
		return cv;
	}
	
	public void setLogin(String login){
		this.login = login;
	}
	
	public String getLogin(){
		return login;
	}
	
	public void setContrasenia(String contrasenia){
		this.contrasenia = contrasenia;
	}
	
	public String getContrasenia(){
		return contrasenia;
	}
	
	public void setTipo(Integer tipo){
		this.tipo = tipo;
	}
	
	public Integer getTipo(){
		return tipo;
	}
	
}
