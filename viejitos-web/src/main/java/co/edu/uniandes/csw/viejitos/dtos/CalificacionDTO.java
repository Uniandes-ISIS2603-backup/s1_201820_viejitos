/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.dtos;

import co.edu.uniandes.csw.viejitos.entities.CalificacionEntity;

/**
 * Objeto de transferencia de datos para una calificacion general.
 * Al serializarse como DTO se genera un JSON de la siguiente manera
 * <pre>
 *  {
 *      "id":number,
 *      "puntaje":double,
 *      "comentario":string,
 *      "tipoCalificador":string,
 *      "loginCalificador":string,
 *      "tipoCalificado":string,
 *      "loginCalificado":string,
 *  }
 * </pre>
 * 
 * Por ejemplo, una calificacion se representa asi:
 * <pre>
 *  {
 *      "id":209873,
 *      "puntaje":4.5,
 *      "comentario":"El enfermero fue grosero conmigo, se rehuso a prestarme un servicio adecuado",
 *      "tipoCalificador":"cliente",
 *      "loginCalificador":"af.ramirez",
 *      "tipoCalificado":"enfermero",
 *      "loginCalificado":"ma.marulanda"
 *  }
 * </pre>
 * @author js.espitia
 */
public class CalificacionDTO {

	private Long idCalificacion;

	private Double puntaje;

	private String comentario;

	private String loginCalificado;

	private String tipoCalificado;

	private String loginCalificador;

	private String tipoCalificador;

	public CalificacionDTO(){

	}
        
        public CalificacionDTO(CalificacionEntity entidad){
            this.comentario = entidad.getComentario();
            this.idCalificacion = entidad.getId();
            this.loginCalificado = entidad.getLoginCalificado();
            this.loginCalificador = entidad.getLoginCalificador();
            this.puntaje = entidad.getPuntaje();
            this.tipoCalificado = entidad.getTipoCalificado();
            this.tipoCalificador = entidad.getTipoCalificador();
        }

	public Long getIdCalificacion() {
		return idCalificacion;
	}

	public void setIdCalificacion(Long idCalificacion) {
		this.idCalificacion = idCalificacion;
	}

	public Double getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(Double puntaje) {
		this.puntaje = puntaje;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getLoginCalificado() {
		return loginCalificado;
	}

	public void setLoginCalificado(String loginCalificado) {
		this.loginCalificado = loginCalificado;
	}

	public String getTipoCalificado() {
		return tipoCalificado;
	}

	public void setTipoCalificado(String tipoCalificado) {
		this.tipoCalificado = tipoCalificado;
	}

	public String getLoginCalificador() {
		return loginCalificador;
	}

	public void setLoginCalificador(String loginCalificador) {
		this.loginCalificador = loginCalificador;
	}

	public String getTipoCalificador() {
		return tipoCalificador;
	}

	public void setTipoCalificador(String tipoCalificador) {
		this.tipoCalificador = tipoCalificador;
	}

	public CalificacionEntity toEntity() {
		CalificacionEntity entidad = new CalificacionEntity();
		entidad.setComentario(comentario);
		entidad.setId(idCalificacion);
		entidad.setLoginCalificado(loginCalificado);
		entidad.setLoginCalificador(loginCalificador);
		entidad.setPuntaje(puntaje);
		entidad.setTipoCalificado(tipoCalificado);
		entidad.setTipoCalificador(tipoCalificador);
		return entidad;


	}

}
