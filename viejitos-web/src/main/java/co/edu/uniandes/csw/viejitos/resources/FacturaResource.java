/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.resources;

import co.edu.uniandes.csw.viejitos.dtos.FacturaDTO;
import co.edu.uniandes.csw.viejitos.dtos.FacturaDetailDTO;
import co.edu.uniandes.csw.viejitos.ejb.FacturaLogic;
import co.edu.uniandes.csw.viejitos.entities.FacturaEntity;
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
    @Inject
    FacturaLogic facturaLogic;
    
    /**
	 * <h1>POST /api/facturas : Crear una entidad de Factura.</h1>
	 * <pre>Cuerpo de petición: JSON {@link FacturaDetailDTO}.
         * Codigos de respuesta:
         * <code style="color: mediumseagreen; background-color: #eaffe0;">
         * 200 OK Crea la factura.</code>
	 * </pre>
	 * Crea una nueva entidad de Factura con la informacion que se recibe en el cuerpo
	 * de la petición.
	 * @param dto {@link FacturaDetailDTO} - La entidad de Factura que se desea guardar.
	 * @return JSON {@link FacturaDetailDTO}  - La entidad de Factura guardada.
	 * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe la entidad de Factura.
	 */
	@POST
	public FacturaDTO createFactura( FacturaDTO dto ) throws BusinessLogicException
	{
		return new FacturaDTO(facturaLogic.create(dto.toEntity()));
	}
        
        /**
	 * <h1>GET /api/facturas : Obtener todas las entidades de Factura.</h1>
	 * <pre>Busca y devuelve todas las entidades de Factura que existen en la aplicacion.
         * Codigos de respuesta:
         * <code style="color: mediumseagreen; background-color: #eaffe0;">
         * 200 OK Devuelve las facturas.</code>
         * </pre>
	 * @return JSONArray {@link FacturaDetailDTO} - Las entidades de Factura encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
	 */
	@GET
	public List<FacturaDetailDTO> getFacturas( )
	{
            List<FacturaDetailDTO> facturas= new ArrayList<FacturaDetailDTO>();
            for(FacturaEntity actual: facturaLogic.getAll())
            {
                facturas.add(new FacturaDetailDTO(actual));
            }
            return facturas;
	}
        
        /**
	 * <h1>GET /api/facturas/{id} : Obtener una entidad de Factura por id.</h1>
	 * <pre>Busca la entidad de Factura con el id asociado recibido en la URL y la devuelve.
         * Codigos de respuesta:
         * <code style="color: mediumseagreen; background-color: #eaffe0;">
         * 200 OK Devuelve la factura.</code> 
         * <code style="color: #c7254e; background-color: #f9f2f4;">
         * 404 Not Found. No existe la factura con el id dado.
         * </code>
         * </pre>
	 * @param id Identificador de la entidad de Factura que se esta buscando. Este debe ser una cadena de dígitos.
	 * @return JSON {@link FacturaDetailDTO} - La entidad de Factura buscada
	 */
	@GET
	@Path( "{id: \\d+}" )
	public FacturaDetailDTO getFactura( @PathParam( "id" ) Long id )
	{
            FacturaEntity entity = facturaLogic.getById(id);
            if (entity == null) {
            throw new WebApplicationException("El recurso /facturas/" + id + " no existe.", 404);
            }
            return new FacturaDetailDTO(entity);
	}
        
        /**
	 * <h1>PUT /api/facturas/{id} : Actualizar una entidad de Factura con el id dado.</h1>
	 * <pre>Cuerpo de petición: JSON {@link FacturaDetailDTO}.
         * Codigos de respuesta:
         * <code style="color: mediumseagreen; background-color: #eaffe0;">
         * 200 OK Actualiza la factura con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
         * <code style="color: #c7254e; background-color: #f9f2f4;">
         * 404 Not Found. No existe una factura con el id dado.
         * </code> 
         * </pre>
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
            detailDTO.setId(id);
            FacturaEntity entity = facturaLogic.getById(id);
            if (entity == null) {
            throw new WebApplicationException("El recurso /facturas/" + id + " no existe.", 404);
            }
            return new FacturaDetailDTO(facturaLogic.update(detailDTO.toEntity()));
	}
        
        /**
	 * <h1>DELETE /api/facturas/{id} : Borrar una entidad de Factura por id.</h1>
	 * <pre>Borra la entidad de Factura con el id asociado recibido en la URL.
         * Códigos de respuesta:<br>
         * <code style="color: mediumseagreen; background-color: #eaffe0;">
         * 200 OK Elimina la factura correspondiente al id dado.</code>
         * <code style="color: #c7254e; background-color: #f9f2f4;">
         * 404 Not Found. No existe una factura con el id dado.</code>
	 * </pre>
	 *
	 * @param id Identificador de la entidad de Factura que se desea borrar. Este debe ser una cadena de dígitos.
	 */
	@DELETE
	@Path( "{id: \\d+}" )
	public void deleteFactura( @PathParam( "id" ) Long id )
	{
            FacturaEntity entity = facturaLogic.getById(id);
            if (entity == null) {
                throw new WebApplicationException("El recurso /facturas/" + id + " no existe.", 404);
            }
            facturaLogic.delete(entity);
	}
}

