/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.resources;

import co.edu.uniandes.csw.viejitos.dtos.HistoriaClinicaDetailDTO;
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
 * <pre>Clase que implementa el recurso "historia clinica".
 * URL: /api/historiasc
 * </pre>
 * @author jj.silva
 */
@Path( "historiasc" )
@Produces( "application/json" )
@Consumes( "application/json" )
@RequestScoped
public class HistoriaClinicaResource
{
    /**
	 * <h1>POST /api/historiasc : Crear una entidad de HistoriaClinica.</h1>
	 * <p>
	 * <pre>Cuerpo de petición: JSON {@link HistoriaClinicaDetailDTO}.
	 *
	 * Crea una nueva entidad de HistoriaClinica con la informacion que se recibe en el cuerpo
	 * de la petición.
	 * @param dto {@link HistoriaClinicaDetailDTO} - La entidad de HistoriaClinica que se desea guardar.
	 * @return JSON {@link HistoriaClinicaDetailDTO}  - La entidad de HistoriaClinica guardada.
	 * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe la entidad de HistoriaClinica.
	 */
	@POST
	public HistoriaClinicaDetailDTO createHistoriaC( HistoriaClinicaDetailDTO dto ) throws BusinessLogicException
	{
		return dto;
	}
        
        /**
	 * <h1>GET /api/historiasc : Obtener todas las entidades de HistoriaClinica.</h1>
	 * <p>
	 * <pre>Busca y devuelve todas las entidades de HistoriaClinica que existen en la aplicacion.
	 * @return JSONArray {@link HistoriaClinicaDetailDTO} - Las entidades de HistoriaClinica encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
	 */
	@GET
	public List<HistoriaClinicaDetailDTO> getHistoriasC( )
	{
		return new ArrayList<>();
	}
        
        /**
	 * <h1>GET /api/historiasc/{id} : Obtener una entidad de HistoriaClinica por id.</h1>
	 * <p>
	 * <pre>Busca la entidad de HistoriaClinica con el id asociado recibido en la URL y la devuelve.
	 * @param id Identificador de la entidad de HistoriaClinica que se esta buscando. Este debe ser una cadena de dígitos.
	 * @return JSON {@link HistoriaClinicaDetailDTO} - La entidad de HistoriaClinica buscada
	 */
	@GET
	@Path( "{id: \\d+}" )
	public HistoriaClinicaDetailDTO getHistoriaC( @PathParam( "id" ) Long id )
	{
		return null;
	}
        
        /**
	 * <h1>PUT /api/historiasc/{id} : Actualizar una entidad de HistoriaClinica con el id dado.</h1>
	 * <pre>Cuerpo de petición: JSON {@link HistoriaClinicaDetailDTO}.
	 *
	 * Actualiza la entidad de HistoriaClinica con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
	 * @param id        Identificador de la entidad de HistoriaClinica que se desea actualizar. Este debe ser una cadena de dígitos.
	 * @param detailDTO {@link HistoriaClinicaDetailDTO} La entidad de HistoriaClinica que se desea guardar.
	 * @return JSON {@link HistoriaClinicaDetailDTO} - La entidad de HistoriaClinica guardada.
	 * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar la entidad de HistoriaClinica porque ya existe una con ese nombre.
	 */
	@PUT
	@Path( "{id: \\d+}" )
	public HistoriaClinicaDetailDTO updateHistoriaC( @PathParam( "id" ) Long id, HistoriaClinicaDetailDTO detailDTO ) throws BusinessLogicException
	{
		return detailDTO;
	}
        
        /**
	 * <h1>DELETE /api/historiasc/{id} : Borrar una entidad de HistoriaClinica por id.</h1>
	 * <p>
	 * <pre>Borra la entidad de HistoriaClinica con el id asociado recibido en la URL.
	 * </pre>
	 *
	 * @param id Identificador de la entidad de HistoriaClinica que se desea borrar. Este debe ser una cadena de dígitos.
	 */
	@DELETE
	@Path( "{id: \\d+}" )
	public void deleteHistoriaC( @PathParam( "id" ) Long id )
	{
		// Void
	}
}

