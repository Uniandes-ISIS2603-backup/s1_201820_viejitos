/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author c.gomezs
 */

@Entity
public class ServicioEntity extends BaseEntity implements Serializable{

        private Integer tipo;
    
    private Date fecha;
    
    private String hora;
    
    private String descripcion;
    
    private Boolean finalizado;
    
    @OneToMany (mappedBy="servicio") 
    private List<QuejaEntity> quejas;
    
    @OneToOne (mappedBy="servicio", orphanRemoval = true) 
    private CalificacionEntity calificacion;
    
    @ManyToOne  
    private EnfermeroEntity enfermero;
   
    @OneToOne 
    private FacturaEntity factura;
    
    @ManyToOne 
    private ClienteEntity cliente;
    
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
    public FacturaEntity getFactura() {
        return factura;
    }

    /**
     * @param factura the factura to set
     */
    public void setFactura(FacturaEntity factura) {
        this.factura = factura;
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
