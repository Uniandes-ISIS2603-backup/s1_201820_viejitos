/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.dtos;

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
    
    private String id;
    
    private String contrasena;
    
    private String cv;
    
    private Integer tipo;

    
    public EnfermeroDTO(){
    
    }  

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    
}
