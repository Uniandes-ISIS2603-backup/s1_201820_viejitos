/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.resources;

/**
 *
 * <pre>Clase que implementa el recurso "calendarioSemanal".
 * URL: /api/calendariosemanal
 *</pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * este recurso tiene la ruta "calendariosemanal".</i>
 *
 * <h2>Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio). 
 * </pre>
 * @author lf.naranjo11
 */

import co.edu.uniandes.csw.viejitos.dtos.CalendarioSemanalDetailDTO;
import co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viejitos.mappers.BusinessLogicExceptionMapper;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
public class CalendarioSemanalResource {
    
 
@Path( "calendariosemanal" )
@Produces( "application/json" )
@Consumes( "application/json" )
@RequestScoped

    /**
	 * <h1>POST /api/calendariosemanal : Crear una entidad(la unica) de calendarioSemanal9.</h1>
         * 
	 * <pre>Cuerpo de petición: JSON {@link calendarioSemanalDetailDTO}.
	 *
	 * Crea una nueva entidad de calendario semanal con la informacion que se recibe en el cuerpo
	 * de la petición.
         * 
         * Codigos de respuesta:
         * <code style="color: mediumseagreen; background-color: #eaffe0;">
         * 200 OK Creó el calendario semanal .
          * </code>
         * <code style="color: #c7254e; background-color: #f9f2f4;">
          * 412 Precodition Failed: Ya existe el calendario.
         * </code>
         * </pre>
	 * @param dto {@link  calendarioSemanalDetailDTO} - La entidad de calendario que se desea guardar.
          * @return JSON {@link calendarioSemanalDetailDTO} - La entidad de calendario que se desea guardar.
	 * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe la entidad de calnedario semanal.
	 */
	@POST
	public CalendarioSemanalDetailDTO createCalendario( CalendarioSemanalDetailDTO dto ) throws BusinessLogicException
	{
		return dto;
	}
        
       
        
        /**
	 * <h1>GET /api/calendariosemanal/ : Obtener la entidad de calendario </h1>

	 * <pre>Busca la entidad de calendario  y la devuelve.
         * Codigos de respuesta:
         * <code style="color: mediumseagreen; background-color: #eaffe0;">
         * 200 OK Devuelve el calendario semanal.</code> 
        * </pre>
	 * @return JSON {@link CalendarioSemanalDetailDTO} - La entidad de calenadrio buscada
	 */
	@GET
	
	public CalendarioSemanalDetailDTO getCalendario(  )
	{
		return null;
	}
        
        /**
	 * <h1>PUT /api/calendariosemanal/ : Actualizar la entidad de calendario .</h1>
	 * <pre>Cuerpo de petición: JSON {@link CalendarioSemanalDetailDTO}.
          * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza el calendario con la información enviada como parámetro. Retorna un objeto identico.</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un calendario semanal.
     * </code> 
     * </pre>
	 * Actualiza la entidad de calendario  con la informacion que se recibe en el cuerpo de la petición.
         * @param detailDTO {@link CalendarioSemanalDetailDTO} La entidad de calendario que se desea guardar.
	 * @return JSON {@link CalendarioSemanalDetailDTO} - La entidad de calendario guardada.
	 * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar la entidad de Cliente porque ya existe una con ese nombre.
	 */
	@PUT
	public CalendarioSemanalDetailDTO updateCalendario(  CalendarioSemanalDetailDTO detailDTO ) throws BusinessLogicException
	{
		return detailDTO;
	}
        
        /**
	 * <h1>DELETE /api/calendarioSemanal Borrar la entidad de calendario .</h1>
	 * <pre>Borra la entidad de calendario asociado recibido en la URL.
         * Códigos de respuesta:<br>
        * <code style="color: mediumseagreen; background-color: #eaffe0;">
        * 200 OK Elimina el calendario semanal.</code>
        * <code style="color: #c7254e; background-color: #f9f2f4;">
       * 404 Not Found. No existe un calendario semanal
        * </code>
        * </pre> 
	*/
	@DELETE
	public void deleteCalendario( )
	{
		// Void
	}
    
    
    
    
}
