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
import co.edu.uniandes.csw.viejitos.ejb.CalendarioSemanalLogic;
import co.edu.uniandes.csw.viejitos.entities.CalendarioSemanalEntity;
import co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viejitos.mappers.BusinessLogicExceptionMapper;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
@Path( "calendariossemanales" )
@Produces( "application/json" )
@Consumes( "application/json" )
@RequestScoped
public class CalendarioSemanalResource {
    
  @Inject
    private CalendarioSemanalLogic calendarioLogic;


    /**
	 * <h1>POST /api/calendariossemanales : Crear una entidad(la unica) de calendarioSemanal9.</h1>
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
		return new CalendarioSemanalDetailDTO(calendarioLogic.createCalendario(dto.toEntity()));
	}
        
       
        
        /**
	 * <h1>GET /api/calendariossemanales/{id} : Obtener la entidad de calendario </h1>

	 * <pre>Busca la entidad de calendario  y la devuelve.
         * Codigos de respuesta:
         * <code style="color: mediumseagreen; background-color: #eaffe0;">
         * 200 OK Devuelve el calendario semanal.</code> 
        * </pre> 
        * @param id Identificador de la entidad de calendario buscada. esta debe ser una cadena de digitos
      *
	 * @return JSON {@link CalendarioSemanalDetailDTO} - La entidad de calenadrio buscada
	 */
	@GET
	@Path( "{id: \\d+}" )
	public CalendarioSemanalDetailDTO getCalendario( @PathParam( "id" ) Long id )
	{
	    CalendarioSemanalEntity entity = calendarioLogic.getCalendario(id);
        if (entity == null) {
            throw new WebApplicationException("la franja no existe", 404);
        }
        return new CalendarioSemanalDetailDTO(entity);
	}
        
        
        /**
	 * <h1>GET /api/calendariossemanales : Obtener todas las entidades de calendario semanal correspondientes a un medico o enfermero.</h1>
	 * <pre>Busca y devuelve todas las entidades de franja que se relacion a un calendariosemanal.
          * Codigos de respuesta:
        * <code style="color: mediumseagreen; background-color: #eaffe0;">
        * 200 OK Devuelve todos los calendarios semanales.</code> 
        * </pre>
	 * @return JSONArray {@link CalendarioSemanalDetailDTO} - Las entidades de calendario semanal encontradas en la aplicación.
	 */
	@GET
	public List<CalendarioSemanalDetailDTO> getCalendariosSemanles( )
	{
            
            
            List<CalendarioSemanalEntity> calendarioEntitys=calendarioLogic.getCalendarios();
            
		List<CalendarioSemanalDetailDTO> calendarios= new ArrayList<>();
            for(CalendarioSemanalEntity actual: calendarioEntitys)
            {
                calendarios.add(new CalendarioSemanalDetailDTO(actual));
            }
            return calendarios;
	}
        
        
        /**
	 * <h1>PUT /api/calendariossemanales/ : Actualizar la entidad de calendario .</h1>
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
         @Path ("{id: \\d+}")
	public CalendarioSemanalDetailDTO updateCalendario( @PathParam("id") Long id, CalendarioSemanalDetailDTO detailDTO ) throws BusinessLogicException
	{
            detailDTO.setid(id);
           CalendarioSemanalEntity entity = calendarioLogic.getCalendario(id);
            if (entity == null) {
                throw new WebApplicationException("El recurso /franjashorarias/" + id + " no existe.", 404);
            }
           return new CalendarioSemanalDetailDTO(calendarioLogic.updateCalendario(detailDTO.toEntity()));
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
        @Path( "{id: \\d+}" )
	public void deleteCalendario(@PathParam( "id" ) Long id)
	{
		 CalendarioSemanalEntity entity = calendarioLogic.getCalendario(id);
        if (entity == null) {
            throw new WebApplicationException("la franja no existe", 404);
        }
        calendarioLogic.deleteCalendario(id);
	}
    
    
    
    
}
