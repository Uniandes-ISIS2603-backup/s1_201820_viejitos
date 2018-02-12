/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.resources;

import co.edu.uniandes.csw.viejitos.dtos.PagoDetailDTO;
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
 * <pre>Clase que implementa el recurso "pago".
 * URL: /api/Pagos
 * @author f.escobar
 */
@Path( "Pagos" )
@Produces( "application/json" )
@Consumes( "application/json" )
@RequestScoped
public class PagoResource
{
    /**
	 * <h1>POST /api/Pagos : Crear una entidad de Pago.</h1>
	 * <p>
	 * <pre>Cuerpo de petición: JSON {@link PagoDetailDTO}.
	 *
	 * Crea una nueva entidad de Pago con la informacion que se recibe en el cuerpo
	 * de la petición.
	 * @param dto {@link PagoDetailDTO} - La entidad de Pago que se desea guardar.
	 * @return JSON {@link PagoDetailDTO}  - La entidad de Pago guardada.
	 * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe la entidad de Pago.
	 */
	@POST
	public PagoDetailDTO createPago( PagoDetailDTO dto ) throws BusinessLogicException
	{
		return dto;
	}
        
        /**
	 * <h1>GET /api/Pagos : Obtener todas las entidades de Pago.</h1>
	 * <p>
	 * <pre>Busca y devuelve todas las entidades de Pago que existen en la aplicacion.
	 * @return JSONArray {@link PagoDetailDTO} - Las entidades de Pago encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
	 */
	@GET
	public List<PagoDetailDTO> getPagos( )
	{
		return new ArrayList<>();
	}
        
        /**
	 * <h1>GET /api/Pagos/{id} : Obtener una entidad de Pago por id.</h1>
	 * <p>
	 * <pre>Busca la entidad de Pago con el id asociado recibido en la URL y la devuelve.
	 * @param id Identificador de la entidad de Pago que se esta buscando. Este debe ser una cadena de dígitos.
	 * @return JSON {@link PagoDetailDTO} - La entidad de Pago buscada
	 */
	@GET
	@Path( "{id: \\d+}" )
	public PagoDetailDTO getPago( @PathParam( "id" ) Long id )
	{
		return null;
	}
        
        /**
	 * <h1>PUT /api/Pagos/{id} : Actualizar una entidad de Pago con el id dado.</h1>
	 * <pre>Cuerpo de petición: JSON {@link PagoDetailDTO}.
	 *
	 * Actualiza la entidad de Pago con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
	 * @param id        Identificador de la entidad de Pago que se desea actualizar. Este debe ser una cadena de dígitos.
	 * @param detailDTO {@link PagoDetailDTO} La entidad de Pago que se desea guardar.
	 * @return JSON {@link PagoDetailDTO} - La entidad de Pago guardada.
	 * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar la entidad de Pago porque ya existe una con ese nombre.
	 */
	@PUT
	@Path( "{id: \\d+}" )
	public PagoDetailDTO updatePago( @PathParam( "id" ) Long id, PagoDetailDTO detailDTO ) throws BusinessLogicException
	{
		return detailDTO;
	}
        
        /**
	 * <h1>DELETE /api/Pagos/{id} : Borrar una entidad de Pago por id.</h1>
	 * <p>
	 * <pre>Borra la entidad de Pago con el id asociado recibido en la URL.
	 * </pre>
	 *
	 * @param id Identificador de la entidad de Pago que se desea borrar. Este debe ser una cadena de dígitos.
	 */
	@DELETE
	@Path( "{id: \\d+}" )
	public void deletePago( @PathParam( "id" ) Long id )
	{
		// Void
	}
}


