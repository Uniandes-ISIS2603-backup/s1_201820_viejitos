/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.entities;

import co.edu.uniandes.csw.viejitos.podam.DateStrategy;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 *
 * @author c.gomezs
 */

@Entity
public class ServicioEntity extends BaseEntity implements Serializable{

    @Temporal (TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date fecha;
    
    private String hora;
    
    private String descripcion;
    
    private Boolean finalizado;
    
    @OneToMany (mappedBy="servicio", cascade=CascadeType.PERSIST) 
    @PodamExclude
    private List<QuejaEntity> quejas;
    
    @OneToOne (mappedBy="servicio", cascade=CascadeType.PERSIST)
    @PodamExclude
    private CalificacionEntity calificacion;
    
    @ManyToOne
    @PodamExclude
    private EnfermeroEntity enfermero;
   
    @OneToMany (mappedBy="servicio", cascade=CascadeType.PERSIST)
    @PodamExclude
    private List<FacturaEntity> facturas;
    
    @ManyToOne
    @PodamExclude
    private ClienteEntity cliente;
    
    @OneToMany (mappedBy="servicio", cascade=CascadeType.PERSIST)
    @PodamExclude 
    private List<PagoEntity> pagos;
    
    private Integer tipo;
    
    
    /**
     * @return the pagoInicial
     */
    public List<PagoEntity> getPagos() {
        return pagos;
    }

    /**
     * @param pagos the pagoInicial to set
     */
    public void setPagos(List<PagoEntity> pagos) {
        this.pagos = pagos;
    }
    
    /**
     * @return the quejas
     */
    public List<QuejaEntity> getQuejas() {
        return quejas;
    }

    /**
     * @param quejas the quejas to set
     */
    public void setQuejas(List<QuejaEntity> quejas) {
        this.quejas = quejas;
    }

    /**
     * @return the calificacion
     */
    public CalificacionEntity getCalificacion() {
        return calificacion;
    }

    /**
     * @param calificacion the calificacion to set
     */
    public void setCalificacion(CalificacionEntity calificacion) {
        this.calificacion = calificacion;
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
     * @return the factura
     */
    public List<FacturaEntity> getFacturas() {
        return facturas;
    }

    /**
     * @param facturas the factura to set
     */
    public void setFacturas(List<FacturaEntity> facturas) {
        this.facturas = facturas;
    }

    /**
     * @return the cliente
     */
    public ClienteEntity getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }
            
    /**
     * @return the tipo
     */
    public Integer getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the hora
     */
    public String getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(String hora) {
        this.hora = hora;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the finalizado
     */
    public Boolean getFinalizado() {
        return finalizado;
    }

    /**
     * @param finalizado the finalizado to set
     */
    public void setFinalizado(Boolean finalizado) {
        this.finalizado = finalizado;
    }
    
}
