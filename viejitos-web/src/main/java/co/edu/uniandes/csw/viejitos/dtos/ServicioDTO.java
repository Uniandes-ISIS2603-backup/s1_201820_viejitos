/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.dtos;

import java.util.Date;

/**
 * ServicioDTO Objeto de transferencia de datos de Servicios. Los DTO contienen las
 * represnetaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "tipo": number,
 *      "fecha": date,
 *      "hora": string,
 *      "descripción": string,
 *      "finalizado": boolean,
 *   }
 * </pre>
 * Por ejemplo un servicio se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *      "tipo": 1,
 *      "fecha: "12/02/2018",
 *      "hora": "8:37",
 *      "descripción": "Acompañamiento a cita médica",
 *      "finalizado": true,
 *   }
 *
 * </pre>
 * @author c.gomezs
 */

public class ServicioDTO {
    
    private Integer tipo;
    
    private Date fecha;
    
    private String hora;
    
    private String descripcion;
    
    private Boolean finalizado;
    
     /**
     * Constructor por defecto
     */
    public ServicioDTO()
    {
        
    }

    /**
     * @return el tipo del servicio
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * @param tipo el nuevo tipo del servicio
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    /**
     * @return la fecha del servicio
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha la nueva fecha del servicio
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return la hora del servicio
     */
    public String getHora() {
        return hora;
    }

    /**
     * @param hora la nueva hora del servicio
     */
    public void setHora(String hora) {
        this.hora = hora;
    }

    /**
     * @return la descripción del servicio
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion la nueva descripción del servicio
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return true si ha finalizado el servicio, false de lo contrario.
     */
    public boolean isFinalizado() {
        return finalizado;
    }

    /**
     * @param finalizado nuevo estado del servicio
     */
    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }
   
    
}
