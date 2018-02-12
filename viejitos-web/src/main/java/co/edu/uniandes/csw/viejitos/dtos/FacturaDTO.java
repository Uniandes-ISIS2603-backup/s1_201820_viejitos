/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.dtos;

import java.util.Date;

/**
 * FacturaDTO Objeto de transferencia de datos de Facturas. Los DTO contienen las
 * representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "costoTotal": Double,
 *      "fechaExpedicion": Date,
 *      "descripcion": String,
 *      "nombreCliente": String,
 *      "ccCliente": Integer,
 *      "servicioPrestado": String,
 *      "nombreEmpresa": String
 *   }
 * </pre>
 * Por ejemplo una ciudad se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *      "costoTotal": 120100,
 *      "fechaExpedicion": 12/04/18,
 *      "descripcion": "Acompanar al banco",
 *      "nombreCliente": "Felipe Escobar",
 *      "ccCliente": 1234567,
 *      "servicioPrestado": "Acompanamiento",
 *      "nombreEmpresa": "Hospital"
 *   }
 *
 * </pre>
 * @author f.escobar
 */
public class FacturaDTO {

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
    
    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id el nuevo id
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * @return costoTotal
     */
    public Double getCostoTotal() {
        return costoTotal;
    }

    /**
     * @param costoTotal el nuevo costoTotal
     */
    public void setCostoTotal(Double costoTotal) {
        this.costoTotal = costoTotal;
    }

    /**
     * @return fechaExpedicion
     */
    public Date getFechaExpedicion() {
        return fechaExpedicion;
    }

    /**
     * @param fechaExpedicion la nueva fechaExpedicion
     */
    public void setFechaExpedicion(Date fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    /**
     * @return descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion la nueva descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return nombreCliente
     */
    public String getNombreCliente() {
        return nombreCliente;
    }

    /**
     * @param nombreCliente el nuevo nombreCliente
     */
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    /**
     * @return ccCliente
     */
    public Integer getCcCliente() {
        return ccCliente;
    }

    /**
     * @param ccCliente la nueva ccCliente 
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
     * @return nombreEmpresa
     */
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    /**
     * @param nombreEmpresa el nuevo nombreEmpresa
     */
    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }
    
    /**
     * Constructor
     */
    public FacturaDTO()
    {
        
    }
    
}
