/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.resources;
import co.edu.uniandes.csw.viejitos.dtos.MedicoDetailDTO;
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
 * <pre>Clase que implementa el recurso "Medico".
 * URL: /api/Medico
 * @author l.pardo
 */
@Path( "Medicos" )
@Produces( "application/json" )
@Consumes( "application/json" )
@RequestScoped
public class MedicoResource
{
    /**
	 * <h1>POST /api/Medicos : Crear una entidad de Medico.</h1>
	 * <p>
	 * <pre>Cuerpo de petición: JSON {@link MedicoDetailDTO}.
	 *
	 * Crea una nueva entidad de Medico con la informacion que se recibe en el cuerpo
	 * de la petición.
	 * @param dto {@link MedicoDetailDTO} - La entidad de Medico que se desea guardar.
	 * @return JSON {@link MedicoDetailDTO}  - La entidad de Medico guardada.
	 * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe la entidad de Servicio.
	 */
	@POST
	public MedicoDetailDTO createMedico( MedicoDetailDTO dto ) throws BusinessLogicException
	{
		return dto;
	}
        
        /**
	 * <h1>GET /api/Medicos : Obtener todas las entidades de Medico.</h1>
	 * <p>
	 * <pre>Busca y devuelve todas las entidades de Medico que existen en la aplicacion.
	 * @return JSONArray {@link MedicoDetailDTO} - Las entidades de Medico encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
	 */
	@GET
	public List<MedicoDetailDTO> getMedicos( )
	{
		return new ArrayList<MedicoDetailDTO>();
	}
        
        /**
	 * <h1>GET /api/Medicos/{id} : Obtener una entidad de Medico por id.</h1>
	 * <p>
	 * <pre>Busca la entidad de Medico con el id asociado recibido en la URL y la devuelve.
	 * @param id Identificador de la entidad de Medico que se esta buscando. Este debe ser una cadena de dígitos.
	 * @return JSON {@link ServicioDetailDTO} - La entidad de Medico buscada
	 */
	@GET
	@Path( "{id: \\d+}" )
	public MedicoDetailDTO getMedico( @PathParam( "id" ) Long id )
	{
		return null;
	}
        
        /**
	 * <h1>PUT /api/Medicos/{id} : Actualizar una entidad de Medico con el id dado.</h1>
	 * <pre>Cuerpo de petición: JSON {@link MedicoDetailDTO}.
	 *
	 * Actualiza la entidad de Medico con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
	 * @param id        Identificador de la entidad de Medico que se desea actualizar. Este debe ser una cadena de dígitos.
	 * @param detailDTO {@link MedicoDetailDTO} La entidad de Medico que se desea guardar.
	 * @return JSON {@link MedicoDetailDTO} - La entidad de Medico guardada.
	 * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar la entidad de Servicio porque ya existe una con ese nombre.
	 */
	@PUT
	@Path( "{id: \\d+}" )
	public MedicoDetailDTO updateMedico( @PathParam( "id" ) Long id, MedicoDetailDTO detailDTO ) throws BusinessLogicException
	{
		return detailDTO;
	}
        
        /**
	 * <h1>DELETE /api/Medicos/{id} : Borrar una entidad de Medico por id.</h1>
	 * <p>
	 * <pre>Borra la entidad de Cita con el id asociado recibido en la URL.
	 * </pre>
	 *
	 * @param id Identificador de la entidad de Medico que se desea borrar. Este debe ser una cadena de dígitos.
	 */
	@DELETE
	@Path( "{id: \\d+}" )
	public void deleteMedico( @PathParam( "id" ) Long id )
	{
		// Void
	}
}