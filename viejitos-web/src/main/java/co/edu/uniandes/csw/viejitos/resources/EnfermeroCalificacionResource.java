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
import co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viejitos.persistence.EnfermeroPersistence;
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
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 * <pre>Clase que implementa el recurso "enfermeros/calificaciones".
 * URL: /api/enfermeros/{enfermeroId}/calificaciones
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta
 * "/api" y que el servicio {@link MedicoResource} define este servicio de forma
 * relativa con la ruta "citas" con respecto un libro.</i>
 *
 * <h2>Anotaciones </h2>
 * <pre>
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio).
 * </pre>
 *
 * @author jj.silva
 * @version 1.0
 */
@Path("enfermeros/{enfermeroId: \\d+}/calificaciones")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class EnfermeroCalificacionResource {

    @Inject
    private EnfermeroLogic enfermeroLogic;

    @Inject
    private CalificacionLogic cLogic;

    private static final Logger LOGGER = Logger.getLogger(EnfermeroPersistence.class.getName());
    /**
     * <h1>GET /api/enfermeros/{enfermeroId}/calificaciones : Obtener las
     * calificaciones de un enfermero.</h1>
     *
     * <pre>Busca y devuelve las calificaciones que tiene el enfermero.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve las calificaciones del enfermero.</code>
     * </pre>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un enfermero con el id dado.
     * </code>
     *
     * @param enfermeroId El ID del enfermero del cual se busca las
     * calificaciones
     * @return JSONArray {@link CalificacionDetailDTO} - Las calificaciones
     * encontradas del enfermero.
     * @throws co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException
     * Cuando el enfermero no tiene Calificaciones asociadas.
     */
    @GET
    public List<CalificacionDetailDTO> getCalificaciones(@PathParam("enfermeroId") Long enfermeroId) throws BusinessLogicException {
        if (enfermeroLogic.getById(enfermeroId) == null) {
            throw new WebApplicationException("No existe un enfermero con ese id", 404);
        } else {
            List<CalificacionDetailDTO> calificaciones = new ArrayList<CalificacionDetailDTO>();
            for (CalificacionEntity actual : enfermeroLogic.getCalificaciones(enfermeroId)) {
                calificaciones.add(new CalificacionDetailDTO(actual));
            }
            return calificaciones;
        }
    }

    /**
     * <h1>GET /api/enfermeros/{enfermeroId}/calificaciones/{id} : Obtener una
     * calificacion de un Enfermero.</h1>
     * <pre>Busca y devuelve la calificacion con el ID recibido en la URL, relativa a un Enfermero.
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve la calificacion del Enfermero.</code>
     * </pre>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un enfermero con el id dado.
     * </code>
     *
     * @param enfermeroId El ID del enfermero del cual se busca la calificacion
     * @param id El ID de la calificacion que se busca
     * @return {@link CalificacionDetailDTO} - La calificacion encontrada en el
     * cliente.
     */
    @GET
    @Path("{id: \\d+}")
    public CalificacionDetailDTO getCalificacion(@PathParam("enfermeroId") Long enfermeroId, @PathParam("id") Long id) {
        if (enfermeroLogic.getById(enfermeroId) == null) {
            throw new WebApplicationException("No existe un enfermero con ese id", 404);
        }
        try {
            CalificacionEntity entity = enfermeroLogic.getCalificacionById(enfermeroId, id);
            return new CalificacionDetailDTO(entity);
        } catch (BusinessLogicException e) {
            LOGGER.log(Level.INFO, "El BusinessLogicException se transforma a webapp");
            throw new WebApplicationException(e.getMessage(), 404);
        }
    }

    /**
     * <h1>POST /api/enfermeros/{enfermeroId}/calificaciones/ : Crear una
     * calificacion para un enfermero</h1>
     *
     * <pre> Crea una calificacion para un enfermero existente
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creo la calificacion .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found: No existe el enfermero
     * </code>
     * </pre>
     *
     * @param enfermeroId El ID del enfermero al cual se le va a crear la
     * calificacion
     * @param dto El dto de la Calificacion que se va a agregar
     * @return JSON {@link CalificacionDetailDTO} - La calificacion creada.
     * @throws co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException
     * Cuando el enfermero al cual se le quiere agregar la Calificacion no
     * existe
     */
    @POST
    public CalificacionDetailDTO addCalificacion(@PathParam("enfermeroId") Long enfermeroId, CalificacionDetailDTO dto) throws BusinessLogicException {
        if (enfermeroLogic.getById(enfermeroId) == null) {
            throw new WebApplicationException("No existe un enfermero con ese id", 404);
        } else {
            return new CalificacionDetailDTO(enfermeroLogic.addCalificacion(enfermeroId, dto.toEntity()));
        }
    }

    /**
     * <h1>DELETE /api/enfermeros/{enfermeroId}/calificaciones/{calificacionId}
     * : Elimina una calificacion de un enfermero.</h1>
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Se eliminó la calificacion del enfermero.
     * </code>
     * </pre>
     *
     * @param enfermeroId Identificador del enfermero que se esta buscando.
     * @param id Identificador de la calificacion que se desea borrar.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void removeCalificacion(@PathParam("enfermeroId") Long enfermeroId, @PathParam("id") Long id) {
        enfermeroLogic.removeCalificacion(enfermeroId, id);
    }
}
