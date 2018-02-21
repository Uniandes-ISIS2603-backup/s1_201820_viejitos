/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author c.gomezs
 */
@Entity
public class QuejaEntity extends BaseEntity implements Serializable{
    
    private String reclamo;
    
    private Boolean resuelto;
    
    /**
     * @return the reclamo
     */
    public String getReclamo() {
        return reclamo;
    }

    /**
     * @param reclamo the reclamo to set
     */
    public void setReclamo(String reclamo) {
        this.reclamo = reclamo;
    }

    /**
     * @return the resuelto
     */
    public Boolean getResuelto() {
        return resuelto;
    }

    /**
     * @param resuelto the resuelto to set
     */
    public void setResuelto(Boolean resuelto) {
        this.resuelto = resuelto;
    }
}
