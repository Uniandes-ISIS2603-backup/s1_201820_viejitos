/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.dtos;

import java.util.Date;
import co.edu.uniandes.csw.viejitos.entities.ServicioEntity;

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
 *      "finalizado": boolean
 *   }
 * </pre>
 * Por ejemplo un servicio se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *      "tipo": 1,
 *      "fecha": "12/02/2018",
 *      "hora": "8:37",
 *      "descripción": "Acompañamiento a cita médica",
 *      "finalizado": false
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
    
    private Long id;
    
    
     /**
     * Constructor por defecto
     */
    public ServicioDTO()
    {
        
    }
    
    /**
    * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
    * la entidad que viene de argumento.
    * @param ServicioEntity: Es la entidad que se va a convertir a DTO
     */
    public ServicioDTO( ServicioEntity servicioEntity )
    {
        if(servicioEntity!=null)
        {
            this.id = servicioEntity.getId();
            this.tipo = servicioEntity.getTipo();
            this.descripcion = servicioEntity.getDescripcion();
            this.fecha = servicioEntity.getFecha();
            this.finalizado = servicioEntity.getFinalizado();
            this.hora = servicioEntity.getHora();
        }
    }
        
    /**
    * Convertir DTO a Entity
    * @return Un Entity con los valores del DTO
    */
    public ServicioEntity toEntity( )
    {
        ServicioEntity entity = new ServicioEntity( );
	entity.setId( this.id );
	entity.setDescripcion(this.descripcion);
        entity.setFecha(this.fecha);
        entity.setFinalizado(this.finalizado);
        entity.setHora(this.hora);
        entity.setTipo(this.getTipo());
        return entity;
    }
    
    /**
     * @return el id del servicio
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id nuevo id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return el tipo del servicio
     */
    public Integer getTipo() {
        return tipo;
    }

    /**
     * @param tipo el nuevo tipo del servicio
     */
    public void setTipo(Integer tipo) {
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
