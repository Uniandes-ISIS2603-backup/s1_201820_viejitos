/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;

/**
 *
 * @author lf.naranjo11
 */
@Entity
public class CalendarioSemanalEntity extends BaseEntity implements Serializable
{
    private Long id;
    private Date ultimaModficacion;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the ultimaModficacion
     */
    public Date getUltimaModficacion() {
        return ultimaModficacion;
    }

    /**
     * @param ultimaModficacion the ultimaModficacion to set
     */
    public void setUltimaModficacion(Date ultimaModficacion) {
        this.ultimaModficacion = ultimaModficacion;
    }
    
    
}
