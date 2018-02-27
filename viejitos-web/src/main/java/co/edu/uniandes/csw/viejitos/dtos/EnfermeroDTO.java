/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.dtos;

import co.edu.uniandes.csw.viejitos.entities.EnfermeroEntity;

/** 
 * Objeto de transferencia de datos para un enfermero.
 * Al serializarse como DTO se genera un JSON de la siguiente manera
 * <pre>
 *  {
 *      "id":number,
 *      "nombre":string,
 *      "cv":string,
 *      "login":string,
 *      "contrasenia":string,
 *      "tipo":string,
 *  }
 * </pre>
 * 
 * Por ejemplo, un enfermero se representa asi:
 * <pre>
 *  {
 *      "id":2678389,
 *      "nombre":"Juan Espitia",
 *      "cv":"./documents/juanHDV.pdf",
 *      "login":,"js.espitia"
 *      "contrasenia":"a89ess0909",
 *      "tipo":"Enfermero",
 *  }
 * </pre>
 * @author js.espitia
 */
public class EnfermeroDTO {
    
    private String nombre;
    
    private Long id;
    
    private String contrasena;
    
    private String cv;
    
    private Integer tipo;

    private String login;

    
    public EnfermeroDTO(){
    
    } 
    
    public EnfermeroDTO(EnfermeroEntity entidad){
        this.contrasena = entidad.getContrasenia();
        this.cv =entidad.getCV();
        this.id = entidad.getId();
        this.login = entidad.getLogin();
        this.nombre = entidad.getName();
        this.tipo = entidad.getTipo();
    } 
    
    public String getLogin() {
	return login;
    }

    public void setLogin(String login) {
	this.login = login;
    }

    public void setId(Long id) {
	this.id = id;
    } 

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }
    
    public EnfermeroEntity toEntity(){
    	EnfermeroEntity entidad = new EnfermeroEntity();
    	entidad.setContrasenia(this.contrasena);
    	entidad.setCV(this.cv);
    	entidad.setId(this.id);
    	entidad.setLogin(this.login);
    	entidad.setName(nombre);
    	entidad.setTipo(tipo);  
    	return entidad;
    }
    
}
