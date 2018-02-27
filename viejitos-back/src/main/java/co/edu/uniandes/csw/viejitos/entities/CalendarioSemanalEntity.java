/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author lf.naranjo11
 */
@Entity
public class CalendarioSemanalEntity extends BaseEntity implements Serializable
{
   @Temporal(TemporalType.DATE)
    private Date ultimaModficacion;

   @PodamExclude
   @OneToMany(cascade=CascadeType.ALL,orphanRemoval=true,mappedBy= "calendario", targetEntity= FranjaHorariaEntity.class )
  private List<FranjaHorariaEntity> franjas=new ArrayList<FranjaHorariaEntity>();
   
   @OneToOne
   private EnfermeroEntity enfermero;
   
   @OneToOne(mappedBy="calendario",targetEntity=MedicoEntity.class)
   private MedicoEntity medico;

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
