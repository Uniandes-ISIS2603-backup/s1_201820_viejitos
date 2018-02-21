/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.dtos;

/**
 * FacturaDetailDTO Objeto de transferencia de datos de Facturas. Los DTO contienen las
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
 *      "nombreEmpresa": String,
 *      "servicio": ServicioDTO
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
 *      "servicio": {"tipo": 1,
 *      "fecha: "12/02/2018",
 *      "hora": "8:37",
 *      "descripción": "Acompañamiento a cita médica",
 *      "finalizado": true}
 *   }
 *
 * </pre>
 * @author f.escobar
 */
public class FacturaDetailDTO extends FacturaDTO {

    /**
     * Servicio al que pertenece la factura
     */
    private ServicioDTO servicio;
    
    /**
     * @return servicio
     */
    public ServicioDTO getServicio() {
        return servicio;
    }

    /**
     * @param servicio el nuevo servicio
     */
    public void setServicio(ServicioDTO servicio) {
        this.servicio = servicio;
    }
    
    /**
     * COnstructor
     */
    public FacturaDetailDTO()
    {
        super();
    }
}
