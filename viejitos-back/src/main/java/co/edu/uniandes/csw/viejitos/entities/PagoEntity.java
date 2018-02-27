/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author f.escobar
 */
@Entity
public class PagoEntity extends BaseEntity implements Serializable{
    
    /**
     * medio de pago
     */
    private String medio;
    
    /**
     * estado de pago booleano
     */
    private Boolean pagado;
    
    /**
     * fecha limite de pago
     */
    private Date fechaLimite;
    
    /**
     * valor monetario del pago
     */
    private Double valor;
    
    @OneToOne 
    private ServicioEntity servicio;

    /**
     * @return the medio
     */
    public String getMedio() {
        return medio;
    }

    /**
     * @param medio the medio to set
     */
    public void setMedio(String medio) {
        this.medio = medio;
    }

    /**
     * @return the pagado
     */
    public Boolean getPagado() {
        return pagado;
    }

    /**
     * @param pagado the pagado to set
     */
    public void setPagado(Boolean pagado) {
        this.pagado = pagado;
    }

    /**
     * @return the fechaLimite
     */
    public Date getFechaLimite() {
        return fechaLimite;
    }

    /**
     * @param fechaLimite the fechaLimite to set
     */
    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    /**
     * @return the valor
     */
    public Double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(Double valor) {
        this.valor = valor;
    }
    
    
}
