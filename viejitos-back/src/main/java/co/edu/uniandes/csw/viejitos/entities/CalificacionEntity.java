package co.edu.uniandes.csw.viejitos.entities;

import java.io.Serializable;
import javax.persistence.Entity;

@Entity
public class CalificacionEntity extends BaseEntity implements Serializable{

	private String loginCalificador;

	private String tipoCalificador;

	private String loginCalificado;

	private String tipoCalificado;

	private Double puntaje;

	private String comentario;

	public String getLoginCalificador(){
		return loginCalificador;
	}
	
	public void setLoginCalificador(String loginCalificador){
		this.loginCalificador = loginCalificador;
	}

	public String getTipoCalificador{
		return tipoCalificador;
	}
	
	public void setTipoCalificador(String tipoCalificador){
		this.tipoCalificador = tipoCalificador;
	}

	public String getLoginCalificado{
		return loginCalificado;
	}

	public void setLoginCalificado(String loginCalificado){
		this.loginCalificado = loginCalificado;
	}
	
	public String getTipoCalificado{
		return tipoCalificado;
	}

	public void setTipoCalificado(String tipoCalificado){
		this.tipoCalificado = tipoCalificado;
	}
	
	public Double getPuntaje{
		return puntaje;
	}

	public void setPuntaje(Double puntaje){
		this.puntaje = puntaje;
	}
	
	public String getComentario{
		return comentario;
	}
	
	public void setComentario(String comentario){
		this.comentario = comentario;
	}
}