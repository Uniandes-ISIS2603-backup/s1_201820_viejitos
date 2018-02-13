/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.dtos;

/**
 *
 * @author f.escobar
 * PagoDetailDTO Objeto de transferencia de datos de Pagos. Los DTO contienen las
 represnetaciones de los JSON que se transfieren entre el cliente y el
 servidor.
 
 Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 <pre>
   {
      "id": Long,
      "medio": String,
      "pagado": Boolean,
      "fechaLimite": Date,
      "valor": Double,
      "servicio": ServicioDTO
   }
 </pre>
 Por ejemplo una ciudad se representa asi:<br>
 
 <pre>
 
   {
      "id": 123456,
      "medio": "Efectivo",
      "pagado": TRUE,
      "fechaLimite": 12/04/18,
      "valor": 150000
      "servicio": {"tipo": 1,
 *      "fecha: "12/02/2018",
 *      "hora": "8:37",
 *      "descripción": "Acompañamiento a cita médica",
 *      "finalizado": true}
   }
   </pre>
 */
public class PagoDetailDTO extends PagoDTO {

    /**
     * Servicio al que pertenece el pago
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
    public PagoDetailDTO()
    {
        
    }
}
