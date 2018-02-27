/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.dtos;

import co.edu.uniandes.csw.viejitos.entities.FacturaEntity;
import java.util.Date;

/**
 * FacturaDTO Objeto de transferencia de datos de Facturas. Los DTO contienen las
 * representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": Long,
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
 *      "id": 123,
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
     * Constructor
     */
    public FacturaDTO()
    {
        
    }
    
    /**
    * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
    * la entidad que viene de argumento.
    * @param FacturaEntity: Es la entidad que se va a convertir a DTO
     */
    public FacturaDTO( FacturaEntity facturaEntity )
    {
        if(facturaEntity!=null)
        {
            this.id = facturaEntity.getId();
            this.costoTotal = facturaEntity.getCostoTotal();
            this.fechaExpedicion = facturaEntity.getFechaExpedicion();
            this.descripcion = facturaEntity.getDescripcion();
            this.nombreCliente = facturaEntity.getNombreCliente();
            this.ccCliente = facturaEntity.getCcCliente();
            this.servicioPrestado = facturaEntity.getServicioPrestado();
            this.nombreEmpresa = facturaEntity.getNombreEmpresa();
        }
    }
        
    /**
    * Convertir DTO a Entity
    * @return Un Entity con los valores del DTO
    */
    public FacturaEntity toEntity( )
    {
        FacturaEntity entity = new FacturaEntity( );
	entity.setId(this.id);
        entity.setCostoTotal(this.costoTotal);
        entity.setFechaExpedicion(this.fechaExpedicion);
        entity.setDescripcion(this.descripcion);
        entity.setNombreCliente(this.nombreCliente);
        entity.setCcCliente(this.ccCliente);
        entity.setServicioPrestado(this.servicioPrestado);
        entity.setNombreEmpresa(this.nombreEmpresa);
        return entity;
    }
    
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
    
    
    
}
