/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.resources;

import co.edu.uniandes.csw.viejitos.dtos.ClienteDetailDTO;
import co.edu.uniandes.csw.viejitos.dtos.QuejaDetailDTO;
import co.edu.uniandes.csw.viejitos.dtos.QuejaDTO;
import co.edu.uniandes.csw.viejitos.ejb.QuejaLogic;
import co.edu.uniandes.csw.viejitos.entities.QuejaEntity;
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
 * <pre>Clase que implementa el recurso "quejas".
 * URL: /api/quejas
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * este recurso tiene la ruta "quejas".</i>
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

@Path( "quejas" )
@Produces( "application/json" )
@Consumes( "application/json" )
@RequestScoped
public class QuejaResource {
    
@Inject
private QuejaLogic quejaLogic;
    
     /**
	 * <h1>POST /api/quejas : Crear una entidad de Queja.</h1>
	 * <pre>Cuerpo de petición: JSON {@link ClienteDetailDTO}.
         * Codigos de respuesta:
         * <code style="color: mediumseagreen; background-color: #eaffe0;">
         * 200 OK Crea la queja.</code>
	 *</pre>
	 * Crea una nueva entidad de Queja con la informacion que se recibe en el cuerpo
	 * de la petición.
	 * @param dto {@link QuejaDetailDTO} - La entidad de Queja que se desea guardar.
	 * @return JSON {@link QuejaDTO}  - La entidad de queja guardada.
	 */
	@POST
	public QuejaDetailDTO createQueja(QuejaDetailDTO dto ) throws BusinessLogicException
	{
            return new QuejaDetailDTO(quejaLogic.create(dto.toEntity()));
        }
        
        /**
	 * <h1>GET /api/quejas : Obtener todas las entidades de Queja.</h1>
	 * <pre>Busca y devuelve todas las entidades de Queja que existen en la aplicacion.
         * Codigos de respuesta:
         * <code style="color: mediumseagreen; background-color: #eaffe0;">
         * 200 OK Devuelve las quejas.</code>
         * </pre>
	 * @return JSONArray {@link ClienteDetailDTO} - Las entidades de Queja encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
	 */
        @GET
	public List<QuejaDetailDTO> getQuejas( )
	{
            List<QuejaDetailDTO> quejas= new ArrayList<QuejaDetailDTO>();
            for(QuejaEntity actual: quejaLogic.getAll())
            {
                quejas.add(new QuejaDetailDTO(actual));
            }
            return quejas;
	}
    
        /**
	 * <h1>GET /api/quejas/{id} : Obtener una entidad de Queja por id.</h1>
	 * <pre>Busca la entidad de Queja con el id asociado recibido en la URL y la devuelve.
         * Codigos de respuesta:
         * <code style="color: mediumseagreen; background-color: #eaffe0;">
         * 200 OK Devuelve la queja.</code> 
         * <code style="color: #c7254e; background-color: #f9f2f4;">
         * 404 Not Found. No existe la queja con el id dado.
         * </code></pre>
	 * @param id Identificador de la entidad de Queja que se esta buscando. Este debe ser una cadena de dígitos.
	 * @return JSON {@link QuejaDetailDTO} - La entidad de Queja buscada
	 */
	@GET
	@Path( "{id: \\d+}" )
	public QuejaDetailDTO getQueja( @PathParam( "id" ) Long id )
	{
            QuejaEntity entity = quejaLogic.getById(id);
            if (entity == null) {
                throw new WebApplicationException("El recurso /quejas/" + id + " no existe.", 404);
            }
            return new QuejaDetailDTO(entity);
	}
        
        /**
	 * <h1>PUT /api/Quejas/{id} : Actualizar una entidad de Queja con el id dado.</h1>
	 * <pre>Cuerpo de petición: JSON {@link QuejaDetailDTO}
	 * Codigos de respuesta:
         * <code style="color: mediumseagreen; background-color: #eaffe0;">
         * 200 OK Actualiza la queja con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
         * <code style="color: #c7254e; background-color: #f9f2f4;">
         * 404 Not Found. No existe una queja con el id dado.
         * </code> 
         * </pre>
	 * Actualiza la entidad de Queja con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
	 * @param id        Identificador de la entidad de Queja que se desea actualizar. Este debe ser una cadena de dígitos.
	 * @param detailDTO {@link QuejaDetailDTO} La entidad de Queja que se desea guardar.
	 * @return JSON {@link QuejaDetailDTO} - La entidad de Queja guardada.
	 * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar la entidad de Queja porque ya existe una con ese nombre.
	 */
	@PUT
	@Path( "{id: \\d+}" )
	public QuejaDetailDTO updateQueja( @PathParam( "id" ) Long id, QuejaDetailDTO detailDTO ) throws BusinessLogicException
	{
            detailDTO.setId(id);
            QuejaEntity entity = quejaLogic.getById(id);
            if (entity == null) {
                throw new WebApplicationException("El recurso /quejas/" + id + " no existe.", 404);
            }
            return new QuejaDetailDTO(quejaLogic.update(detailDTO.toEntity()));
	}
        
        /**
	 * <h1>DELETE /api/Quejas/{id} : Borrar una entidad de Queja por id.</h1>
	 * <pre>Borra la entidad de Queja con el id asociado recibido en la URL.
         * Códigos de respuesta:<br>
         * <code style="color: mediumseagreen; background-color: #eaffe0;">
         * 200 OK Elimina la queja correspondiente al id dado.</code>
         * <code style="color: #c7254e; background-color: #f9f2f4;">
         * 404 Not Found. No existe una queja con el id dado.</code>
	 * </pre>
	 * @param id Identificador de la entidad de Queja que se desea borrar. Este debe ser una cadena de dígitos.
	 */
	@DELETE
	@Path( "{id: \\d+}" )
	public void deleteQueja( @PathParam( "id" ) Long id )
	{
            QuejaEntity entity = quejaLogic.getById(id);
            if (entity == null) {
                throw new WebApplicationException("El recurso /quejas/" + id + " no existe.", 404);
            }
            quejaLogic.delete(entity);
	}
   
}
