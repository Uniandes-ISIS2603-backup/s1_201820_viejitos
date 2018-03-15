/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.resources;


import co.edu.uniandes.csw.viejitos.dtos.FranjaHorariaDTO;
import co.edu.uniandes.csw.viejitos.ejb.FranjaHorariaLogic;
import co.edu.uniandes.csw.viejitos.entities.FranjaHorariaEntity;
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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
/**
 * <pre>Clase que implementa el recurso "franjaHoraria".
 * URL: /api/franjashorarias
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * este recurso tiene la ruta "franjashorarias".</i>
 *
 * <h2>Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio). 
 * </pre>
 * @author lf.naranjo11  
 * @version 1.0
 */

 @Path("/franjashorarias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class FranjaHorariaResource {
    
   @Inject
   private FranjaHorariaLogic franjaLogic;
    
     /**
	 * <h1>POST /api/franjashorarias : Crear una entidad de FranjaHoraria.</h1>
	 * <pre>Cuerpo de petición: JSON {@link FranjaHorariaDTO}.
	 *
	 * Crea una nueva entidad de franjaHoraria con la informacion que se recibe en el cuerpo
	 * de la petición.
         *  Codigos de respuesta:
         * <code style="color: mediumseagreen; background-color: #eaffe0;">
         * 200 OK Creó la nueva franja .
         * </code>
         * <code style="color: #c7254e; background-color: #f9f2f4;">
         * 412 Precodition Failed: Ya existe la franja.
         * </code>
         * </pre>
	 * @param dto {@link  FranjaHorariaDTO} - La entidad de franja  que se desea guardar.
	 * @return JSON {@link  FranjaHorariaDTO}  - La entidad de franja guardada.
	 * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe la entidad de franja.
	 */
	@POST
	public FranjaHorariaDTO createFranjaHoraria( FranjaHorariaDTO dto ) throws BusinessLogicException
	{
		  return new FranjaHorariaDTO(franjaLogic.create(dto.toEntity()));
	}
        
        /**
	 * <h1>GET /api/franjashorarias : Obtener todas las entidades de franja horaria correspondientes a un calendario semanal.</h1>
	 * <pre>Busca y devuelve todas las entidades de franja que se relacion a un calendariosemanal.
          * Codigos de respuesta:
        * <code style="color: mediumseagreen; background-color: #eaffe0;">
        * 200 OK Devuelve todas las franjas horarias.</code> 
        * </pre>
	 * @return JSONArray {@link FranjaHorariaDTO} - Las entidades de franja horaria encontradas en la aplicación.
	 */
	@GET
	public List<FranjaHorariaDTO> getFranjasHorarias( )
	{
		List<FranjaHorariaDTO> franjas= new ArrayList<FranjaHorariaDTO>();
            for(FranjaHorariaEntity actual: franjaLogic.getFranjas())
            {
                franjas.add(new FranjaHorariaDTO(actual));
            }
            return franjas;
	}
        
        
        
        /**
            * <h1>GET /api/franjashorarias/{id} : Obtener una entidad de franja Horaria por id.</h1>
	 * <pre>Busca la entidad de franja horaria con el id asociado recibido en la URL y la devuelve.
	 * Codigos de respuesta:
        * <code style="color: mediumseagreen; background-color: #eaffe0;">
        * 200 OK Devuelve la franja correspondiente al id.
        * </code> 
        * <code style="color: #c7254e; background-color: #f9f2f4;">
        * 404 Not Found No existe una franja con el id dado.
       * </code> 
       * </pre>
         * @param id Identificador de la entidad de franja horaria que se esta buscando. Este debe ser una cadena de dígitos.
	 * @return JSON {@link FranjaHorariaDTO} - La entidad de franja buscada
	 * @throws WebApplicationException {@link WebApplicationExceptionMapper} - Error de lógica que se genera cuando no se encuentra el autor.
         */
	@GET
	@Path( "{id: \\d+}" )
	public FranjaHorariaDTO getFranja( @PathParam( "id" ) Long id )
	{
             FranjaHorariaEntity entity = franjaLogic.getFranja(id);
        if (entity == null) {
            throw new WebApplicationException("la franja no existe", 404);
        }
        return new FranjaHorariaDTO(entity);
		
	}
        
      
      /**
       * <h1> PUT api/franjashorarias/{id}: actualizar la franja con el id dado</h1>
     * <pre>Cuerpo de peticion: JSON {@link FranjaHorariaDTO}.
     * 
     * Actualiza la franja con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza la franaj con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una franja con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador de la ciudad que se desea actualizar.Este debe ser una cadena de dígitos.
     * @param franja {@link FranjaHorariaDTO} La franja que se desea guardar.
     * @return JSON {@link FranjaHorariaDTO} - La franja guardada.
     * @throws BusinessLogicException {@link co.edu.uniandes.csw.viejitos.mappers.BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar la franaja porque ya existe una con ese nombre.
     */
        @PUT
        @Path ("{id: \\d+}")
        public FranjaHorariaDTO updateFranja(@PathParam("id") Long id, FranjaHorariaDTO franja) throws BusinessLogicException
        {
           FranjaHorariaEntity newEntity= franja.toEntity();
           newEntity.setId(id);
           FranjaHorariaEntity entity = franjaLogic.getFranja(id);
            if (entity == null) {
                throw new WebApplicationException("El recurso /franjashorarias/" + id + " no existe.", 404);
            }
            return new FranjaHorariaDTO(franjaLogic.updateFranja(newEntity));
        
        
        }
 
                /**
	 * <h1>DELETE /api/franjashorarias/{id} : Borrar una entidad de franja por id.</h1>
	 
	 * <pre>Borra la entidad de franja con el id asociado recibido en la URL.
         * * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la franja correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una frnaja con el id dado.
     * </code>
         * 
	 * </pre>
	 *
	 * @param id Identificador de la entidad de franja que se desea borrar. Este debe ser una cadena de dígitos.
	 */
	@DELETE
	@Path( "{id: \\d+}" )
	public void deleteFranjaHoraria( @PathParam( "id" ) Long id )
	{
       FranjaHorariaEntity entity = franjaLogic.getFranja(id);
        if (entity == null) {
            throw new WebApplicationException("la franja no existe", 404);
        }
        franjaLogic.deleteFranja(id);	
            
// Void
	}
        
    
    
    
    
}
