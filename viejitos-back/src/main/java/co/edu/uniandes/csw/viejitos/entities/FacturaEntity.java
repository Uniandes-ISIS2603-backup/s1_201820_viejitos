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
 * @author f.escobar
 */
@Entity
public class FacturaEntity extends BaseEntity implements Serializable  {

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
     * id de la factura
     */
    private Long id;
    
    /**
     * costo total del servicio
     */
    private Double costoTotal;
    
    /**
     * fecha de expedicion de la factura
     */
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
     * servicio que se prestó
     */
    private String servicioPrestado;
    
    /**
     * nombre de la empresa
     */
    private String nombreEmpresa;
}
