/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.dtos;

import co.edu.uniandes.csw.viejitos.entities.CalendarioSemanalEntity;
import java.util.Date;

/**
 * CalendarioSemanalDTO Objeto de transferencia de datos de calendarioSemanal.
 * Los DTO contienen las represnetaciones de los JSON que se transfieren entre
 * el cliente y el servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "ultimaModificacion": Date
 *   }
 * </pre> Por ejemplo un calendario semanal se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *     "id": 001101,
 *      "ultimaModificacion": 01/07/2018 08:11:13pm
 *
 *
 *   }
 *
 * </pre>
 *
 * @author lf.naranjo11
 */

//DONE Completar y Revisar la documentación proque es un copy/paste de franja
public class CalendarioSemanalDTO {

    private Long id;
    private Date ultimaModficacion;

     /**
     * Constructor por defecto
     */
    public CalendarioSemanalDTO(CalendarioSemanalEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.ultimaModficacion = entity.getUltimaModficacion();

        }

    }
    
    /**
     * constructor vacio
    */
    public CalendarioSemanalDTO()
    {
        //Constructor
    }
    
    /**
     * @return la fecha de ultima modificacion
     */
    public Date getUltimaModificacion() {
        return ultimaModficacion;
    }

    /**
     * @param pUltimaModificacion la nueva fecha de modifcacion
     */
    public void setUltimaModificacion(Date pUltimaModificacion) {
        ultimaModficacion = pUltimaModificacion;
    }

    /**
     * @return la id del calendario semanal.
     */
    public Long getId() {
        return id;
    }

    /**
     * @param pId la nueva id del calendario semanal.
     */
    public void setId(Long pId) {
        id = pId;
    }

    /**
     * transforma el objeto de calendariosemanaldto en entidad
     * @return la entidad de calendario semanal que equivale al DTO actual
     */
    public CalendarioSemanalEntity toEntity() {
        CalendarioSemanalEntity entity = new CalendarioSemanalEntity();
        entity.setId(this.getId());
        entity.setUltimaModficacion(this.getUltimaModificacion());

        return entity;

    }

}
