/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author lf.naranjo11
 */
@Entity
public class FranjaHorariaEntity extends BaseEntity implements Serializable {

    private Integer horaInicio;
    private Integer horaFin;
    private Boolean ocupado;
    private String diaSemana;

    @PodamExclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    private CalendarioSemanalEntity calendario;

    /**
     * @return the horaInicio
     */
    public Integer getHoraInicio() {
        return horaInicio;
    }

    /**
     * @param horaInicio the horaInicio to set
     */
    public void setHoraInicio(Integer horaInicio) {
        this.horaInicio = horaInicio;
    }

    /**
     * @return the horaFin
     */
    public Integer getHoraFin() {
        return horaFin;
    }

    /**
     * @param horaFin the horaFin to set
     */
    public void setHoraFin(Integer horaFin) {
        this.horaFin = horaFin;
    }

    /**
     * @return the ocupado
     */
    public Boolean isOcupado() {
        return ocupado;
    }

    /**
     * @param ocupado the ocupado to set
     */
    public void setOcupado(Boolean ocupado) {
        this.ocupado = ocupado;
    }

    /**
     * @return the diaSemana
     */
    public String getDiaSemana() {
        return diaSemana;
    }

    /**
     * @param diaSemana the diaSemana to set
     */
    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    /**
     * @return the calendario
     */
    public CalendarioSemanalEntity getCalendario() {
        return calendario;
    }

    /**
     * @param calendario the calendario to set
     */
    public void setCalendario(CalendarioSemanalEntity calendario) {
        this.calendario = calendario;
    }

}
