/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.dtos;

import co.edu.uniandes.csw.viejitos.entities.QuejaEntity;

/**
 * QuejaDTO Objeto de transferencia de datos de Quejas. Los DTO contienen las
 * represnetaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "reclamo": string,
 *      "resuelto": boolean
 *   }
 * </pre> Por ejemplo una queja se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "reclamo": "El enfermero tenia una actitud muy grosera.",
 *      "resuelto": true
 *   }
 *
 * </pre>
 *
 * @author c.gomezs
 */
public class QuejaDTO {

    private String reclamo;

    private Boolean resuelto;

    private Long id;

    /**
     * Constructor por defecto
     */
    public QuejaDTO() {
        //Constructor
    }

    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param quejaEntity: Es la entidad que se va a convertir a DTO
     */
    public QuejaDTO(QuejaEntity quejaEntity) {
        if (quejaEntity != null) {
            this.id = quejaEntity.getId();
            this.reclamo = quejaEntity.getReclamo();
            this.resuelto = quejaEntity.getResuelto();
        }
    }

    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public QuejaEntity toEntity() {
        QuejaEntity entity = new QuejaEntity();
        entity.setId(this.id);
        entity.setReclamo(this.reclamo);
        entity.setResuelto(this.resuelto);
        return entity;
    }

    /**
     * @return el id
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
     * @return el reclamo
     */
    public String getReclamo() {
        return reclamo;
    }

    /**
     * @param reclamo nuevo reclamo
     */
    public void setReclamo(String reclamo) {
        this.reclamo = reclamo;
    }

    /**
     * @return true si esta resuelto, false de lo contrario.
     */
    public boolean isResuelto() {
        return resuelto;
    }

    /**
     * @param resuelto nuevo estado de la queja
     */
    public void setResuelto(boolean resuelto) {
        this.resuelto = resuelto;
    }
}
