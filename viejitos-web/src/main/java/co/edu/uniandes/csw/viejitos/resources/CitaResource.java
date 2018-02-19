/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.resources;


import co.edu.uniandes.csw.viejitos.dtos.CitaDetailDTO;
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
 * <pre>Clase que implementa el recurso "Cita".</pre>
 * URL: /api/Cita
 * @author l.pardo
 */
@Path( "Citas" )
@Produces( "application/json" )
@Consumes( "application/json" )
@RequestScoped
public class CitaResource
{
    /**
	 * <h1>POST /api/Citas : Crear una entidad de Cita.</h1>
	 * <p>
	 * <pre>Cuerpo de petición: JSON {@link CitaDetailDTO}.</pre>
	 *
	 * Crea una nueva entidad de Cita con la informacion que se recibe en el cuerpo
	 * de la petición.
	 * @param dto {@link CitaDetailDTO} - La entidad de Cita que se desea guardar.
	 * @return JSON {@link CitaDetailDTO}  - La entidad de Cita guardada.
	 * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe la entidad de Servicio.
	 */
	@POST
	public CitaDetailDTO createCita( CitaDetailDTO dto ) throws BusinessLogicException
	{
		return dto;
	}
        
        /**
	 * <h1>GET /api/Cita : Obtener todas las entidades de Cita.</h1>
	 * <p>
	 * <pre>Busca y devuelve todas las entidades de Cita que existen en la aplicacion.</pre>
	 * @return JSONArray {@link CitaDetailDTO} - Las entidades de Cita encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
	 */
	@GET
	public List<CitaDetailDTO> getCitas( )
	{
		return new ArrayList<>();
	}
        
        /**
	 * <h1>GET /api/Cita/{id} : Obtener una entidad de Cita por id.</h1>
	 * <p>
	 * <pre>Busca la entidad de Cita con el id asociado recibido en la URL y la devuelve.</pre>
	 * @param id Identificador de la entidad de Cita que se esta buscando. Este debe ser una cadena de dígitos.
	 * @return JSON {@link CitaDetailDTO} - La entidad de Cita buscada
	 */
	@GET
	@Path( "{id: \\d+}" )
	public CitaDetailDTO getCita( @PathParam( "id" ) Long id )
	{
		return null;
	}
        
        /**
	 * <h1>PUT /api/Citas/{id} : Actualizar una entidad de Cita con el id dado.</h1>
	 * <pre>Cuerpo de petición: JSON {@link CitaDetailDTO}.</pre>
	 *
	 * Actualiza la entidad de Medico con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
	 * @param id        Identificador de la entidad de Cita que se desea actualizar. Este debe ser una cadena de dígitos.
	 * @param detailDTO {@link CitaDetailDTO} La entidad de Cita que se desea guardar.
	 * @return JSON {@link CitaDetailDTO} - La entidad de Cita guardada.
	 * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar la entidad de Servicio porque ya existe una con ese nombre.
	 */
	@PUT
	@Path( "{id: \\d+}" )
	public CitaDetailDTO updateCita( @PathParam( "id" ) Long id, CitaDetailDTO detailDTO ) throws BusinessLogicException
	{
		return detailDTO;
	}
        
        /**
	 * <h1>DELETE /api/Citas/{id} : Borrar una entidad de Cita por id.</h1>
	 * <p>
	 * <pre>Borra la entidad de Cita con el id asociado recibido en la URL.
	 * </pre>
	 *
	 * @param id Identificador de la entidad de Cita que se desea borrar. Este debe ser una cadena de dígitos.
	 */
	@DELETE
	@Path( "{id: \\d+}" )
	public void deleteCita( @PathParam( "id" ) Long id )
	{
		// Void
	}
}

