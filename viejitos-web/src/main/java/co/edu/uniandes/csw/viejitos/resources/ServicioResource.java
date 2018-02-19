/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.resources;

import co.edu.uniandes.csw.viejitos.dtos.ServicioDetailDTO;
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

/**
 * <pre>Clase que implementa el recurso "servicios".
 * URL: /api/servicios
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * este recurso tiene la ruta "servicios".</i>
 *
 * <h2>Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio). 
 * </pre>
 * @author c.gomezs
 * @version 1.0
 */
@Path( "Servicios" )
@Produces( "application/json" )
@Consumes( "application/json" )
@RequestScoped

public class ServicioResource
{
    /**
	 * <h1>POST /api/Servicios : Crear una entidad de Servicio.</h1>
	 * <p>
	 * <pre>Cuerpo de petición: JSON {@link ServicioDetailDTO}.</pre>
	 *
	 * Crea una nueva entidad de Servicio con la informacion que se recibe en el cuerpo
	 * de la petición.
	 * @param dto {@link ServicioDetailDTO} - La entidad de Servicio que se desea guardar.
	 * @return JSON {@link ServicioDetailDTO}  - La entidad de Servicio guardada.
	 * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe la entidad de Servicio.
	 */
	@POST
	public ServicioDetailDTO createServicio( ServicioDetailDTO dto ) throws BusinessLogicException
	{
		return dto;
	}
        
        /**
	 * <h1>GET /api/Servicios : Obtener todas las entidades de Servicio.</h1>
	 * <p>
	 * <pre>Busca y devuelve todas las entidades de Servicio que existen en la aplicacion.</pre>
	 * @return JSONArray {@link ServicioDetailDTO} - Las entidades de Servicio encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
	 */
	@GET
	public List<ServicioDetailDTO> getServicios( )
	{
		return new ArrayList<>();
	}
        
        /**
	 * <h1>GET /api/Servicios/{id} : Obtener una entidad de Servicio por id.</h1>
	 * <p>
	 * <pre>Busca la entidad de Servicio con el id asociado recibido en la URL y la devuelve.</pre>
	 * @param id Identificador de la entidad de Servicio que se esta buscando. Este debe ser una cadena de dígitos.
	 * @return JSON {@link ServicioDetailDTO} - La entidad de Servicio buscada
	 */
	@GET
	@Path( "{id: \\d+}" )
	public ServicioDetailDTO getServicio( @PathParam( "id" ) Long id )
	{
		return null;
	}
        
        /**
	 * <h1>PUT /api/Servicios/{id} : Actualizar una entidad de Servicio con el id dado.</h1>
	 * <pre>Cuerpo de petición: JSON {@link ServicioDetailDTO}. </pre>
	 *
	 * Actualiza la entidad de Servicio con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
	 * @param id        Identificador de la entidad de Servicio que se desea actualizar. Este debe ser una cadena de dígitos.
	 * @param detailDTO {@link ServicioDetailDTO} La entidad de Servicio que se desea guardar.
	 * @return JSON {@link ServicioDetailDTO} - La entidad de Servicio guardada.
	 * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar la entidad de Servicio porque ya existe una con ese nombre.
	 */
	@PUT
	@Path( "{id: \\d+}" )
	public ServicioDetailDTO updateServicio( @PathParam( "id" ) Long id, ServicioDetailDTO detailDTO ) throws BusinessLogicException
	{
		return detailDTO;
	}
        
        /**
	 * <h1>DELETE /api/Servicios/{id} : Borrar una entidad de Servicio por id.</h1>
	 * <p>
	 * <pre>Borra la entidad de Servicio con el id asociado recibido en la URL.
	 * </pre>
	 *
	 * @param id Identificador de la entidad de Servicio que se desea borrar. Este debe ser una cadena de dígitos.
	 */
	@DELETE
	@Path( "{id: \\d+}" )
	public void deleteServicio( @PathParam( "id" ) Long id )
	{
		// Void
	}
}

