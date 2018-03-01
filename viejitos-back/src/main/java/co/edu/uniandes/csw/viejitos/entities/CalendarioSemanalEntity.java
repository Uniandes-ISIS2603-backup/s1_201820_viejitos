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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
   @OneToMany(cascade=CascadeType.ALL,orphanRemoval=true,mappedBy= "calendario", targetEntity= FranjaHorariaEntity.class )
  private List<FranjaHorariaEntity> franjas=new ArrayList<FranjaHorariaEntity>();
   @PodamExclude
   @OneToOne
   private EnfermeroEntity enfermero;
   @PodamExclude
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

    /**
     * @return the enfermero
     */
    public EnfermeroEntity getEnfermero() {
        return enfermero;
    }

    /**
     * @param enfermero the enfermero to set
     */
    public void setEnfermero(EnfermeroEntity enfermero) {
        this.enfermero = enfermero;
    }

    /**
     * @return the medico
     */
    public MedicoEntity getMedico() {
        return medico;
    }

    /**
     * @param medico the medico to set
     */
    public void setMedico(MedicoEntity medico) {
        this.medico = medico;
    }

    public Iterable<FranjaHorariaEntity> getFranjasHorarias() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
