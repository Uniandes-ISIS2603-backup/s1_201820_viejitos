/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.resources;

import co.edu.uniandes.csw.viejitos.dtos.FacturaDetailDTO;
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

/**
 * <pre>Clase que implementa el recurso "facturas".
 * URL: /api/facturas
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * este recurso tiene la ruta "facturas".</i>
 *
 * <h2>Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio). 
 * </pre>
 * @author ISIS2603  
 * @version 1.0
 */
@Path( "facturas" )
@Produces( "application/json" )
@Consumes( "application/json" )
@RequestScoped
public class FacturaResource
{
    /**
	 * <h1>POST /api/facturas : Crear una entidad de Factura.</h1>
	 * <p>
	 * <pre>Cuerpo de petición: JSON {@link FacturaDetailDTO}.
	 *
	 * Crea una nueva entidad de Factura con la informacion que se recibe en el cuerpo
	 * de la petición.
	 * @param dto {@link FacturaDetailDTO} - La entidad de Factura que se desea guardar.
	 * @return JSON {@link FacturaDetailDTO}  - La entidad de Factura guardada.
	 * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe la entidad de Factura.
	 */
	@POST
	public FacturaDetailDTO createFactura( FacturaDetailDTO dto ) throws BusinessLogicException
	{
		return dto;
	}
        
        /**
	 * <h1>GET /api/facturas : Obtener todas las entidades de Factura.</h1>
	 * <p>
	 * <pre>Busca y devuelve todas las entidades de Factura que existen en la aplicacion.
	 * @return JSONArray {@link FacturaDetailDTO} - Las entidades de Factura encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
	 */
	@GET
	public List<FacturaDetailDTO> getFacturas( )
	{
		return new ArrayList<>();
	}
        
        /**
	 * <h1>GET /api/facturas/{id} : Obtener una entidad de Factura por id.</h1>
	 * <p>
	 * <pre>Busca la entidad de Factura con el id asociado recibido en la URL y la devuelve.
	 * @param id Identificador de la entidad de Factura que se esta buscando. Este debe ser una cadena de dígitos.
	 * @return JSON {@link FacturaDetailDTO} - La entidad de Factura buscada
	 */
	@GET
	@Path( "{id: \\d+}" )
	public FacturaDetailDTO getFactura( @PathParam( "id" ) Long id )
	{
		return null;
	}
        
        /**
	 * <h1>PUT /api/facturas/{id} : Actualizar una entidad de Factura con el id dado.</h1>
	 * <pre>Cuerpo de petición: JSON {@link FacturaDetailDTO}.
	 *
	 * Actualiza la entidad de Factura con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
	 * @param id        Identificador de la entidad de Factura que se desea actualizar. Este debe ser una cadena de dígitos.
	 * @param detailDTO {@link FacturaDetailDTO} La entidad de Factura que se desea guardar.
	 * @return JSON {@link FacturaDetailDTO} - La entidad de Factura guardada.
	 * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar la entidad de Factura porque ya existe una con ese nombre.
	 */
	@PUT
	@Path( "{id: \\d+}" )
	public FacturaDetailDTO updateFactura( @PathParam( "id" ) Long id, FacturaDetailDTO detailDTO ) throws BusinessLogicException
	{
		return detailDTO;
	}
        
        /**
	 * <h1>DELETE /api/facturas/{id} : Borrar una entidad de Factura por id.</h1>
	 * <p>
	 * <pre>Borra la entidad de Factura con el id asociado recibido en la URL.
	 * </pre>
	 *
	 * @param id Identificador de la entidad de Factura que se desea borrar. Este debe ser una cadena de dígitos.
	 */
	@DELETE
	@Path( "{id: \\d+}" )
	public void deleteFactura( @PathParam( "id" ) Long id )
	{
		// Void
	}
}

