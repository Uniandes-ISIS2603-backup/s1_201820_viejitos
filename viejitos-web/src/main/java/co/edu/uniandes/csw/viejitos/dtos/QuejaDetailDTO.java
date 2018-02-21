/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.dtos;

/**
 * Clase que extiende de {@link QuejaDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos. Para conocer el
 * contenido de la ciudad vaya a la documentacion de {@link QuejaDTO}
 * 
 *  <pre>
 *   {
 *      "reclamo": string,
 *      "resuelto": boolean,
 *      "servicio":servicioDTO,
 *      "cliente":clienteDTO
 *   }
 * </pre>
 * Por ejemplo una queja se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *      "reclamo": "El enfermero tenia una actitud muy grosera.",
 *      "resuelto": true
 *      "servicio":{}
 *      "cliente": {"id": 12345, "nombre: "John Doe", "login": "johndoe23", "contrasena": "jd124", "estado": 1, "tipo": 1}
 *    }
 * </pre>
 * 
 * * @author c.gomezs
 */
public class QuejaDetailDTO extends QuejaDTO{
    
    private ServicioDTO servicio;
    
    private ClienteDTO cliente;
    
    /**
     * Constructor por defecto
     */
     public QuejaDetailDTO()
    {
        super();
    }

    /**
     * @return el servicio de la queja
     */
    public ServicioDTO getServicio() {
        return servicio;
    }

    /**
     * @param servicio nuevo servicio 
     */
    public void setServicio(ServicioDTO servicio) {
        this.servicio = servicio;
    }

    /**
     * @return el cliente que puso la queja
     */
    public ClienteDTO getCliente() {
        return cliente;
    }

    /**
     * @param cliente nuevo cliente
     */
    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }
    
}
