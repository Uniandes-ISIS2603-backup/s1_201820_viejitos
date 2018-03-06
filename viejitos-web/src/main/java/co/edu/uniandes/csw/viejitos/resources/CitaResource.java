/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.resources;


import co.edu.uniandes.csw.viejitos.dtos.CitaDetailDTO;
import co.edu.uniandes.csw.viejitos.ejb.CitaLogic;
import co.edu.uniandes.csw.viejitos.entities.CitaEntity;
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
/**
 * <pre>Clase que implementa el recurso "Cita".</pre>
 * URL: /api/Cita
 * @author l.pardo
 */
@Path( "citas" )
@Produces( "application/json" )
@Consumes( "application/json" )
@RequestScoped
public class CitaResource
{
    @Inject
    CitaLogic logic;
    /**
	 * <h1>POST /api/citas : Crear una entidad de Cita.</h1>
	 * <p>
	 * <pre>Cuerpo de petición: JSON {@link CitaDetailDTO}.
	 *
         * Codigos de respuesta:
         * <code style="color: mediumseagreen; background-color: #eaffe0;">
         * 200 OK Crea la cita.</code> 
         * </pre>
	 * Crea una nueva entidad de Cita con la informacion que se recibe en el cuerpo
	 * de la petición.
	 * @param dto {@link CitaDetailDTO} - La entidad de Cita que se desea guardar.
	 * @return JSON {@link CitaDetailDTO}  - La entidad de Cita guardada.
	 * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe la entidad de Servicio.
	 */
	@POST
	public CitaDetailDTO createCita( CitaDetailDTO dto ) throws BusinessLogicException
	{
		return  new CitaDetailDTO(logic.create(dto.toEntity()));
	}
        
        /**
	 * <h1>GET /api/citas : Obtener todas las entidades de Cita.</h1>
	 * <p>
	 * <pre>Busca y devuelve todas las entidades de Cita que existen en la aplicacion.
         *      
         * Codigos de respuesta:
         * <code style="color: mediumseagreen; background-color: #eaffe0;">
         * 200 OK Devuelve las citas.</code> 
         * </pre>
	 * @return JSONArray {@link CitaDetailDTO} - Las entidades de Cita encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
	 */
	@GET
	public List<CitaDetailDTO> getCitas( )
	{
		List<CitaDetailDTO> list= new ArrayList<CitaDetailDTO>();
            for(CitaEntity e :logic.getAll())
            {
               list.add(new CitaDetailDTO(e));
            }
            return list;
	}
        
        /**
	 * <h1>GET /api/Cita/{id} : Obtener una entidad de Cita por id.</h1>
	 * <p>
	 * <pre>Busca la entidad de Cita con el id asociado recibido en la URL y la devuelve.
         *     
         * Codigos de respuesta:
         * <code style="color: mediumseagreen; background-color: #eaffe0;">
         * 200 OK Devuelve la cita.</code> 
         * <code style="color: #c7254e; background-color: #f9f2f4;">
         * 404 Not Found. No existe una cita con el id dado.
         * </code></pre>
	 * @param id Identificador de la entidad de Cita que se esta buscando. Este debe ser una cadena de dígitos.
	 * @return JSON {@link CitaDetailDTO} - La entidad de Cita buscada
	 */
	@GET
	@Path( "{id: \\d+}" )
	public CitaDetailDTO getCita( @PathParam( "id" ) Long id )
	{
		return new CitaDetailDTO(logic.getById(id));
	}
        
        /**
	 * <h1>PUT /api/Citas/{id} : Actualizar una entidad de Cita con el id dado.</h1>
	 * <pre> 
         * Actualiza la entidad de Medico con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
         * Cuerpo de petición: JSON {@link CitaDetailDTO}.
         * Codigos de respuesta:
         * <code style="color: mediumseagreen; background-color: #eaffe0;">
         * 200 OK Actualiza la cita con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
         * <code style="color: #c7254e; background-color: #f9f2f4;">
         * 404 Not Found. No existe una cita con el id dado.
         * </code> 
         * </pre>
	 * @param id        Identificador de la entidad de Cita que se desea actualizar. Este debe ser una cadena de dígitos.
	 * @param detailDTO {@link CitaDetailDTO} La entidad de Cita que se desea guardar.
	 * @return JSON {@link CitaDetailDTO} - La entidad de Cita guardada.
	 * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar la entidad de Servicio porque ya existe una con ese nombre.
	 */
	@PUT
	@Path( "{id: \\d+}" )
	public CitaDetailDTO updateCita( @PathParam( "id" ) Long id, CitaDetailDTO detailDTO ) throws BusinessLogicException
	{
            CitaEntity e = logic.getById(id);
            if(e==null)
            {
                throw new WebApplicationException("El recurso /citas/" + id + " no existe.", 404);
            }
            return new CitaDetailDTO(logic.update(detailDTO.toEntity()));
	}
        
        /**
	 * <h1>DELETE /api/Citas/{id} : Borrar una entidad de Cita por id.</h1>
	 * <p>
	 * <pre>Borra la entidad de Cita con el id asociado recibido en la URL.
         *
         * Códigos de respuesta:<br>
         * <code style="color: mediumseagreen; background-color: #eaffe0;">
         * 200 OK Elimina la cita correspondiente al id dado.</code>
         * <code style="color: #c7254e; background-color: #f9f2f4;">
         * 404 Not Found. No existe una cita con el id dado.</code>
         * </pre>
	 * @param id Identificador de la entidad de Cita que se desea borrar. Este debe ser una cadena de dígitos.
	 */
	@DELETE
	@Path( "{id: \\d+}" )
	public void deleteCita( @PathParam( "id" ) Long id )
	{
            CitaEntity e = logic.getById(id);
            if(e==null)
            {
                throw new WebApplicationException("El recurso /citas/" + id + " no existe.", 404);
            }
            logic.delete(e);
	}
}

