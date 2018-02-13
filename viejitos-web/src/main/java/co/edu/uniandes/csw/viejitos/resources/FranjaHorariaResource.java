/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.resources;

/**
 **<pre>Clase que implementa el recurso "FranjaHoraria".
 * URL: /api/FranjasHorarias </pre>
 * <p>
 * <h2>Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio).
 * </pre>
 * @author lf.naranjo11
 */
import co.edu.uniandes.csw.viejitos.dtos.FranjaHorariaDetailDTO;
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

public class FranjaHorariaResource {
    
    @Path("franjashorarias")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
    
     /**
	 * <h1>POST /api/ : Crear una entidad de FranjaHoraria.</h1>
	 * <pre>Cuerpo de petición: JSON {@link FranjaHorariaDetailDTO}.
	 *
	 * Crea una nueva entidad de franjaHoraria con la informacion que se recibe en el cuerpo
	 * de la petición.
         *  Codigos de respuesta:
         * <code style="color: mediumseagreen; background-color: #eaffe0;">
         * 200 OK Creó la nueva ciudad .
         * </code>
         * <code style="color: #c7254e; background-color: #f9f2f4;">
         * 412 Precodition Failed: Ya existe la ciudad.
         * </code>
         * </pre>
	 * @param dto {@link  FranjaHorariaDetailDTO} - La entidad de franja  que se desea guardar.
	 * @return JSON {@link  FranjaHorariaDetailDTO}  - La entidad de franja guardada.
	 * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe la entidad de Servicio.
	 */
	@POST
	public FranjaHorariaDetailDTO createFranjaHoraria( FranjaHorariaDetailDTO dto ) throws BusinessLogicException
	{
		return dto;
	}
        
        /**
	 * <h1>GET /api/franjasHorarias : Obtener todas las entidades de franja horaria.</h1>
	 * <p>
	 * <pre>Busca y devuelve todas las entidades de Cita que se relacion a un medico o enfermero.
	 * @return JSONArray {@link CalendarioSemanalDetailDTO} - Las entidades de calendario semanal encontradas en la aplicación.
	 */
	@GET
	public List<FranjaHorariaDetailDTO> getCitas( )
	{
		return new ArrayList<FranjaHorariaDetailDTO>();
	}
        
        /**
            * <h1>GET /api/FranjaHoraria/{id} : Obtener una entidad de franja Horaria por id.</h1>
	 * <p>
	 * <pre>Busca la entidad de franja horaria con el id asociado recibido en la URL y la devuelve.
	 * @param id Identificador de la entidad de franja horaria que se esta buscando. Este debe ser una cadena de dígitos.
	 * @return JSON {@link CalendarioSemanalDetailDetailDTO} - La entidad de franja buscada
	 */
	@GET
	@Path( "{id: \\d+}" )
	public FranjaHorariaDetailDTO getFranja( @PathParam( "id" ) Long id )
	{
		return null;
	}
        
         /**
	 * <h1>GET /api/franjasHorarias/dia : Obtener todas las entidades de franja horaria de un dia.</h1>
	 * <p>
	 * <pre>Busca y devuelve todas las entidades de Cita que se relacion a un dia dado.
	 * @return JSONArray {@link CalendarioSemanalDetailDTO} - Las entidades de calendario semanal encontradas en la aplicación.
	 */
	@GET
	public List<FranjaHorariaDetailDTO> getCitas( )
	{
		return new ArrayList<FranjaHorariaDetailDTO>();
	}
        
        /**
	 * <h1>DELETE /api/FranjasHorarias/{id} : Borrar una entidad de franja por id.</h1>
	 * <p>
	 * <pre>Borra la entidad de franja con el id asociado recibido en la URL.
	 * </pre>
	 *
	 * @param id Identificador de la entidad de franja que se desea borrar. Este debe ser una cadena de dígitos.
	 */
	@DELETE
	@Path( "{id: \\d+}" )
	public void deleteFranjaHoraria( @PathParam( "id" ) Long id )
	{
		// Void
	}
        
    
    
    
    
}
