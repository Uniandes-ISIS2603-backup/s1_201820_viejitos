/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.resources;

import co.edu.uniandes.csw.viejitos.dtos.CalificacionDetailDTO;
import co.edu.uniandes.csw.viejitos.ejb.CalificacionLogic;
import co.edu.uniandes.csw.viejitos.ejb.EnfermeroLogic;
import co.edu.uniandes.csw.viejitos.entities.CalificacionEntity;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author js.espitia
 */
@Path("enfermeros/{enfermeroId: \\d+}/calificaciones")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class EnfermeroCalificacionResource {
     @Inject
    private CalificacionLogic calificacionLogic;
     
     @Inject
    private EnfermeroLogic enfermeroLogic;
     /**
     * <h1>GET /api/enfermeros/{enfermeroId}/calificaciones : Obtener todas las entidades de
     * Calificacion asociadas a un enfermero en particular.</h1>
     * <p>
     * <pre>Busca y devuelve todas las entidades de Calificacion que existen en la aplicacion.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todas las calificaciones de la aplicacion.</code>
     * </pre>
     *
     * @param enfermeroId el id del enfermero dueño de las calificaciones.
     * @return JSONArray con las entidades de Calificacion encontradas.
     */
    @GET
    public List<CalificacionDetailDTO> getCalificaciones(@PathParam("enfermeroId") Long enfermeroId) {
        List<CalificacionEntity> califs = calificacionLogic.getAllForEnf(enfermeroId);
        List<CalificacionDetailDTO> resp = new ArrayList<>();
        for (CalificacionEntity actual : califs) {
            resp.add(new CalificacionDetailDTO(actual));
        }
        return resp;
    }
    
    
    
    /**
     * <h1>POST /api/enfermeros/{enfermeroId}/calificaciones/{calificacionId}} : Asociar una calificacion a un enfermero.</h1>
     *
     * <pre> Asocia una nueva calificacion a un enfermero ya existente
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Se creo la calificacion .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found: No existe el enfermero
     * </code>
     * </pre>
     * @param calificacion {@link CalificacionDetailDTO} El ID de la calificacion que se va a asociar
     * @param enfermeroId El ID del enfermero al cual se le va a asociar la calificacion
     * @return JSON {@link CalificacionDetailDTO} - La calificacion asociada.
     */
    @POST
    @Path("{calificacionId: \\d+}")
    public CalificacionDetailDTO addCalificacion(@PathParam("enfermeroId") Long enfermeroId, CalificacionDetailDTO calificacion)
    {
        if(enfermeroLogic.getById(enfermeroId) == null)
        {
             throw new WebApplicationException("No existe un enfermero con ese id", 404);
        }
        else
        {
            //return new CalificacionDetailDTO(enfermeroLogic.addCalificacion(enfermeroId, calificacion.toEntity()));
        } 
        return null;
    }
    
    /**
     * <h1>PUT /api/enfermeros/{enfermeroId}/calificaciones/{calificacionId} : Actualizar una de las calificaciones de un enfermero.</h1>
     *
     * <pre>Cuerpo de petición: JSONArray {@link CalificacionDetailDTO}.
     * 
     * Actualiza una calificacion particular de un enfermero con la calificacion que pasa por parámetro
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Se actualizó la calificacion deseada
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: No se pudo actualizar la calificacion
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not found: No existe el enfermero o la calificacion
     * </code>
     * </pre>
     * @param enfermeroId El ID del enfermero al cual se le va a actualizar la calificacion
     * @param calificacion JSONArray {@link CalificacionDetailDTO} - La calificacion que se desea cambiar.
     * @return JSONArray {@link CalificacionDetailDTO}  - La calificacion actualizada.
     */
    @PUT
    public CalificacionDetailDTO replaceCalificacion(@PathParam("enfermeroId") Long enfermeroId, CalificacionDetailDTO calificacion)
    {
        if(enfermeroLogic.getById(enfermeroId) == null)
        {
             throw new WebApplicationException("No existe un enfermero con ese id", 404);
        }
        else
        {
            //Procesamiento y actualización
          return calificacion;  
        }
    }
}
