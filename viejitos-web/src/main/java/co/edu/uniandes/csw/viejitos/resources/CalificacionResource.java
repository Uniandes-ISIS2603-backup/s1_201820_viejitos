/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.resources;

import co.edu.uniandes.csw.viejitos.dtos.CalificacionDetailDTO;
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
    /**
     * <h1>POST /api/Calificaciones : Crear una entidad de Calificacion.</h1>
     * <p>
     * <pre>Cuerpo de petición: JSON {@link CalificacionDetailDTO}. </pre>
     * Crea una nueva entidad de Calificacion con la informacion que se recibe en el cuerpo
     * de la petición y se regresa un objeto identico con un id auto-generado
     * por la base de datos.
     * @param dto La entidad a guardar
     * @return JSON La entidad calificacion guardada con su id correspondiente
     * @throws BusinessLogicException Si ya existe una entidad de calificacion igual
     */
    @POST
    public CalificacionDetailDTO createCalificacion( CalificacionDetailDTO dto ) throws BusinessLogicException{
        return dto;
    }
    
    /**
     * <h1>GET /api/Calificaciones : Obtener todas las entidades de Calificacion.</h1>
     * <p>
     * <pre>Busca y devuelve todas las entidades de Calificacion que existen en la aplicacion. </pre>
     * @return JSONArray con las entidades de Calificacion encontradas.
     */
    @GET
    public List<CalificacionDetailDTO> getCalificaciones (){
        return new ArrayList<>();
    }
    
    /**
     * <h1>GET /api/Calificaciones/{id} : Obtener una entidad de Calificacion por id.</h1>
     * <p>
     * <pre>Busca la entidad de Calificacion con el id asociado recibido en la URL y la devuelve.
     * </pre>
     * @param id el identificador que se asigna a la calificacion 
     * @return JSON con la informacion de la entidad calificacion
     */
    @GET
    @Path("{id:[0-9]*}")
    public CalificacionDetailDTO getCalificacion(@PathParam("id") Long id){
        return null;
    }
    
    /**
     * <h1>PUT /api/Calificaciones/{id} : Actualizar una entidad de Calificacion con el id dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link CalificacionDetailDTO}.
     * Actualiza la entidad de Calificacion con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     * </pre>
     * @param id identificador de la calificacion que se desea actualizar.Este debe ser una cadena de numeros
     * @param dto {@link CalificacionDetailDTO} La entidad de Calificacion que se desea guardar.
     * @return JSON {@link CalificacionDetailDTO} - La entidad de Calificacion guardada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar la entidad de Enfermero porque ya existe una con ese nombre.
     */
    @PUT
    @Path("{id:[0-9]*}")
    public CalificacionDetailDTO updateEnfermero(@PathParam("id") Long id, CalificacionDetailDTO dto) throws BusinessLogicException{
        return dto;
    }
    
    /**
     * <h1>DELETE /api/Calificaciones/{id} : Borrar una entidad de Calificacion por id.</h1>
     * <p>
     * <pre>Borra la entidad de Calificacion con el id asociado recibido en la URL.
     * </pre>
     * @param id identificador de la calificacion que se desea borrar. Este debe ser una cadena de numeros.
     */
    @DELETE
    @Path( "{id: \\d+}" )
    public void deleteEnfermero( @PathParam( "id" ) Long id){
    }
}