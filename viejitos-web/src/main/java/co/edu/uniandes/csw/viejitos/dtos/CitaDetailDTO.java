/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.dtos;

/**
 * CitaDTO Objeto de transferencia de datos de Cita. Los DTO contienen las
 * represnetaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *         "fecha":date,
 *          "hora": date,
 *          "id":number,
 *          "cliente":ClienteDTO
 *   }
 * </pre>
 * Por ejemplo una cita se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *          "fecha":"08-06-2018",
 *          "hora": "2:00 PM",
 *          "id":"192837465",
 *          "cliente": {
 *           "id": 12345,
 *           "nombre: "John Doe",
 *           "login": "johndoe23",
 *           "contrasena": "jd124",
 *            "estado": 1,
 *            "tipo": 1 }
 *   }
 *
 * </pre>
 * @author l.pardo
 */
public class CitaDetailDTO extends CitaDTO{
   private ClienteDTO cliente;
   public CitaDetailDTO()
   {
   }
   public ClienteDTO getCliente()
   {
       return cliente;
   }
   
   public void setClienteDTO(ClienteDTO c)
   {
       cliente=c;
   }
}
