/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.resources;

import co.edu.uniandes.csw.viejitos.dtos.CalificacionDetailDTO;
import co.edu.uniandes.csw.viejitos.ejb.CalificacionLogic;
import co.edu.uniandes.csw.viejitos.entities.CalificacionEntity;
import co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viejitos.mappers.BusinessLogicExceptionMapper;
import co.edu.uniandes.csw.viejitos.persistence.CalificacionPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 *<pre>Clase que implementa el recurso "Calificacion".
 * URL: /api/Calificaciones </pre>
 * <p>
 * <h2>Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio).
 * </pre>
 * @author js.espitia
 */
@Path("Calificaciones")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CalificacionResource {
    
    @Inject
    private CalificacionLogic logic;
    
     private static final Logger LOGGER = Logger.getLogger(CalificacionPersistence.class.getName());
    /**
     * <h1>POST /api/Calificaciones : Crear una entidad de Calificacion.</h1>
     * <p>
     * <pre>Cuerpo de petición: JSON {@link CalificacionDetailDTO}. 
     * Crea una nueva entidad de Calificacion con la informacion que se recibe en el cuerpo
     * de la petición y se regresa un objeto identico con un id auto-generado
     * por la base de datos.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó la nueva calificacion .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe la calificacion.
     * </code>
     * </pre>
     * @param dto La entidad a guardar
     * @return JSON La entidad calificacion guardada con su id correspondiente
     * @throws BusinessLogicException Si ya existe una entidad de calificacion igual
     */
    @POST
    public CalificacionDetailDTO createCalificacion( CalificacionDetailDTO dto ) throws BusinessLogicException{
        logic.create(dto.toEntity());
        return dto;
    }
    
    /**
     * <h1>GET /api/Calificaciones : Obtener todas las entidades de Calificacion.</h1>
     * <p>
     * <pre>Busca y devuelve todas las entidades de Calificacion que existen en la aplicacion. 
     *  
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todas las calificaciones de la aplicacion.</code> 
     * </pre>
     * @return JSONArray con las entidades de Calificacion encontradas.
     */
    @GET
    public List<CalificacionDetailDTO> getCalificaciones (){
        List<CalificacionEntity> califs = logic.getAll();
        List<CalificacionDetailDTO> resp = new ArrayList<>();
        for(CalificacionEntity actual:califs){
           resp.add( new CalificacionDetailDTO(actual));
        }
        return resp;
    }
    
    /**
     * <h1>GET /api/Calificaciones/{id} : Obtener una entidad de Calificacion por id.</h1>
     * <p>
     * <pre>Busca la entidad de Calificacion con el id asociado recibido en la URL y la devuelve.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve la calificacion correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe una calificacion con el id dado.
     * </code> 
     * </pre>
     * @param id el identificador que se asigna a la calificacion 
     * @return JSON con la informacion de la entidad calificacion
     * @throws BusinessLogicException si no existe
     */
    @GET
    @Path("{id:[0-9]*}")
    public CalificacionDetailDTO getCalificacion(@PathParam("id") Long id) throws BusinessLogicException{
        CalificacionEntity calif = logic.getById(id);
        if(calif == null)
            throw new BusinessLogicException("No existe una calificacion con el id " + id);
        else return new CalificacionDetailDTO( calif );
    }
    
    /**
     * <h1>PUT /api/Calificaciones/{id} : Actualizar una entidad de Calificacion con el id dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link CalificacionDetailDTO}.
     * Actualiza la entidad de Calificacion con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     *  
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza la calificacion con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una calificacion con el id dado.
     * </code>
     * </pre>
     * @param id identificador de la calificacion que se desea actualizar.Este debe ser una cadena de numeros
     * @param dto {@link CalificacionDetailDTO} La entidad de Calificacion que se desea guardar.
     * @return JSON {@link CalificacionDetailDTO} - La entidad de Calificacion guardada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar la entidad de Enfermero porque ya existe una con ese nombre.
     */
    @PUT
    @Path("{id:[0-9]*}")
    public CalificacionDetailDTO updateCalificacion(@PathParam("id") Long id, CalificacionDetailDTO dto) throws BusinessLogicException{
        CalificacionEntity entidad = dto.toEntity();
        entidad.setId( id );
        logic.update(entidad);
        return dto;
    }
    
    /**
     * <h1>DELETE /api/Calificaciones/{id} : Borrar una entidad de Calificacion por id.</h1>
     * <p>
     * <pre>Borra la entidad de Calificacion con el id asociado recibido en la URL.
      * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la calificacion correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una calificacion con el id dado.
     * </code>
     * </pre>
     * @param id identificador de la calificacion que se desea borrar. Este debe ser una cadena de numeros.
     * @throws BusinessLogicException si se intenta eliminar una calificacion que no existe
     */
    @DELETE
    @Path( "{id: \\d+}" )
    public void deleteCalificacion( @PathParam( "id" ) Long id) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicia proceso de borrar una calificacion con id {0}", id);
        logic.delete(id);
    }
}
