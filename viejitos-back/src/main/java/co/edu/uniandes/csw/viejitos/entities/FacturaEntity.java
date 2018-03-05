/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.entities;

import co.edu.uniandes.csw.viejitos.podam.DateStrategy;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import uk.co.jemos.podam.common.PodamExclude;
import javax.persistence.CascadeType;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 *
 * @author f.escobar
 */
@Entity
public class FacturaEntity extends BaseEntity implements Serializable  {
    
    /**
     * costo total del servicio
     */
    private Double costoTotal;
    
    /**
     * fecha de expedicion de la factura
     */
    @Temporal (TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date fechaExpedicion;
    
    /**
     * descripcion del servicio
     */
    private String descripcion;
    
    /**
     * nombre del cliente
     */
    private String nombreCliente;
    
    /**
     * cedula del cliente
     */
    private Integer ccCliente;
    
    /**
     * tipo de servicio que se presto
     */
    private String servicioPrestado;
    
    /**
     * nombre de la empresa
     */
    private String nombreEmpresa;
    
    /**
     * asociacion al servicio
     */
    @PodamExclude
    @OneToOne (mappedBy="factura", cascade = CascadeType.PERSIST) 
    private ServicioEntity servicio;

    /**
     * @return the costoTotal
     */
    public Double getCostoTotal() {
        return costoTotal;
    }

    /**
     * @param costoTotal the costoTotal to set
     */
    public void setCostoTotal(Double costoTotal) {
        this.costoTotal = costoTotal;
    }

    /**
     * @return the fechaExpedicion
     */
    public Date getFechaExpedicion() {
        return fechaExpedicion;
    }

    /**
     * @param fechaExpedicion the fechaExpedicion to set
     */
    public void setFechaExpedicion(Date fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
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
     * @return the nombreCliente
     */
    public String getNombreCliente() {
        return nombreCliente;
    }

    /**
     * @param nombreCliente the nombreCliente to set
     */
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    /**
     * @return the ccCliente
     */
    public Integer getCcCliente() {
        return ccCliente;
    }

    /**
     * @param ccCliente the ccCliente to set
     */
    public void setCcCliente(Integer ccCliente) {
        this.ccCliente = ccCliente;
    }

    /**
     * @return the servicioPrestado
     */
    public String getServicioPrestado() {
        return servicioPrestado;
    }

    /**
     * @param servicioPrestado the servicioPrestado to set
     */
    public void setServicioPrestado(String servicioPrestado) {
        this.servicioPrestado = servicioPrestado;
    }

    /**
     * @return the nombreEmpresa
     */
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    /**
     * @param nombreEmpresa the nombreEmpresa to set
     */
    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }
    
    /**
     * @return the servicio
     */
    public ServicioEntity getServicio() {
        return servicio;
    }

    /**
     * @param servicio the servicio to set
     */
    public void setServicio(ServicioEntity servicio) {
        this.servicio = servicio;
    }
    
    
}
