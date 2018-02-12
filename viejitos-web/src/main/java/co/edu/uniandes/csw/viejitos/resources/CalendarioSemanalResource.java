/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.resources;

/**
 *
 * @author lf.naranjo11
 */

import co.edu.uniandes.csw.viejitos.dtos.CalendarioSemanalDetailDTO;
import co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException;
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
    
    /**
 * <pre>Clase que implementa el recurso "calendarioSemanal".
 * URL: /api/CalendarioSemanal
 */
@Path( "CalendarioSemanal" )
@Produces( "application/json" )
@Consumes( "application/json" )
@RequestScoped

    /**
	 * <h1>POST /api/calendarioSemanal : Crear una entidad de calendarioSemanal.</h1>
	 * <p>
	 * <pre>Cuerpo de petición: JSON {@link calendarioSemanalDetailDTO}.
	 *
	 * Crea una nueva entidad de calendario semanl con la informacion que se recibe en el cuerpo
	 * de la petición.
         * 
	 * @param dto {@link  calendarioSemanalDetailDTO} - La entidad de calendario que se desea guardar.
          * @return dto {@link calendarioSemanalDetailDTO} - La entidad de calendario que se desea guardar.
	 * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe la entidad de Cliente.
	 */
	@POST
	public CalendarioSemanalDetailDTO createCalendario( CalendarioSemanalDetailDTO dto ) throws BusinessLogicException
	{
		return dto;
	}
        
       
        
        /**
	 * <h1>GET /api/calendarioSemanal/ : Obtener la entidad de calendario </h1>
	 * <p>
	 * <pre>Busca la entidad de calendario  y la devuelve.
	 * @return JSON {@link CalendarioSemanalDetailDTO} - La entidad de calenadrio buscada
	 */
	@GET
	
	public CalendarioSemanalDetailDTO getCliente(  )
	{
		return null;
	}
        
        /**
	 * <h1>PUT /api/calendarioSemanal/ : Actualizar la entidad de calendario .</h1>
	 * <pre>Cuerpo de petición: JSON {@link CalendarioSemanalDetailDTO}.
	 *
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
	 * <p>
	 * <pre>Borra la entidad de calendario asociado recibido en la URL.
	*/
	@DELETE
	
	public void deleteCalendario( )
	{
		// Void
	}
    
    
    
    
}
