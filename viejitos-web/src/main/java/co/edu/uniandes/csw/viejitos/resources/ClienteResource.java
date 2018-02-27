/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.resources;

import co.edu.uniandes.csw.viejitos.dtos.ClienteDetailDTO;
import co.edu.uniandes.csw.viejitos.ejb.ClienteLogic;
import co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException;
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

/**
 * <pre>Clase que implementa el recurso "cliente".
 * </pre>
 * URL: /api/clientes
 * @author jj.silva
 */
@Path( "clientes" )
@Produces( "application/json" )
@Consumes( "application/json" )
@RequestScoped
public class ClienteResource
{
    @Inject
    private ClienteLogic cLogic;
    /**
	 * <h1>POST /api/clientes : Crear una entidad de Cliente.</h1>
	* <pre>Cuerpo de petición: JSON {@link ClienteDetailDTO}.
	 *         
         * Codigos de respuesta:
         * <code style="color: mediumseagreen; background-color: #eaffe0;">
         * 200 OK Crea el cliente.</code> 
         * </pre>
	 * Crea una nueva entidad de Cliente con la informacion que se recibe en el cuerpo
	 * de la petición.
	 * @param dto {@link ClienteDetailDTO} - La entidad de Cliente que se desea guardar.
	 * @return JSON {@link ClienteDetailDTO}  - La entidad de Cliente guardada.
	 *
	 */
	@POST
	public ClienteDetailDTO createCliente( ClienteDetailDTO dto ) 
	{
		return dto;
	}
        
        /**
	 * <h1>GET /api/clientes : Obtener todas las entidades de Cliente.</h1>
	 * <pre>Busca y devuelve todas las entidades de Cliente que existen en la aplicacion.
         * Codigos de respuesta:
         * <code style="color: mediumseagreen; background-color: #eaffe0;">
         * 200 OK Devuelve los clientes.</code>
         * </pre> 
	 * @return JSONArray {@link ClienteDetailDTO} - Las entidades de Cliente encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
	 */
	@GET
	public List<ClienteDetailDTO> getClientes( )
	{
		return new ArrayList<>();
	}
        
        /**
	 * <h1>GET /api/clientes/{id} : Obtener una entidad de Cliente por id.</h1>
	 * <pre>Busca la entidad de Cliente con el id asociado recibido en la URL y la devuelve.
         * Codigos de respuesta:
         * <code style="color: mediumseagreen; background-color: #eaffe0;">
         * 200 OK Devuelve el Cliente.</code> 
         * <code style="color: #c7254e; background-color: #f9f2f4;">
         * 404 Not Found. No existe un Cliente con el id dado.
         * </code>
         * </pre>
	 * @param id Identificador de la entidad de Cliente que se esta buscando. Este debe ser una cadena de dígitos.
	 * @return JSON {@link ClienteDetailDTO} - La entidad de Cliente buscada
	 */
	@GET
	@Path( "{id: \\d+}" )
	public ClienteDetailDTO getCliente( @PathParam( "id" ) Long id )
	{
		return null;
	}
        
        /**
	 * <h1>PUT /api/clientes/{id} : Actualizar una entidad de Cliente con el id dado.</h1>
	 * <pre>Cuerpo de petición: JSON {@link ClienteDetailDTO}.
         * Codigos de respuesta:
         * <code style="color: mediumseagreen; background-color: #eaffe0;">
         * 200 OK Actualiza el cliente con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
         * <code style="color: #c7254e; background-color: #f9f2f4;">
         * 404 Not Found. No existe un Cliente con el id dado.
         * </code> 
	 *</pre>
	 * Actualiza la entidad de Cliente con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
	 * @param id        Identificador de la entidad de Cliente que se desea actualizar. Este debe ser una cadena de dígitos.
	 * @param detailDTO {@link ClienteDetailDTO} La entidad de Cliente que se desea guardar.
	 * @return JSON {@link ClienteDetailDTO} - La entidad de Cliente guardada.
	 * 
	 */
	@PUT
	@Path( "{id: \\d+}" )
	public ClienteDetailDTO updateCliente( @PathParam( "id" ) Long id, ClienteDetailDTO detailDTO ) 
	{
		return detailDTO;
	}
        
        /**
	 * <h1>DELETE /api/clientes/{id} : Borrar una entidad de Cliente por id.</h1>
	 * <pre>Borra la entidad de Cliente con el id asociado recibido en la URL.
         * Códigos de respuesta:<br>
         * <code style="color: mediumseagreen; background-color: #eaffe0;">
         * 200 OK Elimina el cliente correspondiente al id dado.</code>
         * <code style="color: #c7254e; background-color: #f9f2f4;">
         * 404 Not Found. No existe un cliente con el id dado.</code>
	 * </pre>
	 *
	 * @param id Identificador de la entidad de Cliente que se desea borrar. Este debe ser una cadena de dígitos.
	 */
	@DELETE
	@Path( "{id: \\d+}" )
	public void deleteCliente( @PathParam( "id" ) Long id )
	{
		// Void
	}
}
