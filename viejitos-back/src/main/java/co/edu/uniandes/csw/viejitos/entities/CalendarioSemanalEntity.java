/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.entities;

import co.edu.uniandes.csw.viejitos.podam.DateStrategy;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 *
 * @author lf.naranjo11
 */
@Entity
public class CalendarioSemanalEntity extends BaseEntity implements Serializable
{
    
   @Temporal(TemporalType.DATE)
   @PodamStrategyValue(DateStrategy.class)
    private Date ultimaModficacion;

   @PodamExclude
   @OneToMany(cascade=CascadeType.PERSIST,orphanRemoval=true,mappedBy= "calendario", targetEntity= FranjaHorariaEntity.class )
  private List<FranjaHorariaEntity> franjas=new ArrayList<FranjaHorariaEntity>();
 


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

    /**
     * @return the franjas
     */
    public List<FranjaHorariaEntity> getFranjas() {
        return franjas;
    }

    /**
     * @param franjas the franjas to set
     */
    public void setFranjas(List<FranjaHorariaEntity> franjas) {
        this.franjas = franjas;
    }

    

    

    
    
}
