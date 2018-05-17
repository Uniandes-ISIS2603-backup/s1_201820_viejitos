/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.resources;

import co.edu.uniandes.csw.viejitos.dtos.CalificacionDTO;
import co.edu.uniandes.csw.viejitos.dtos.CalificacionDetailDTO;
import co.edu.uniandes.csw.viejitos.ejb.CalificacionLogic;
import co.edu.uniandes.csw.viejitos.entities.CalificacionEntity;
import co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException;
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
import javax.ws.rs.WebApplicationException;

/**
 * <pre>Clase que implementa el recurso "Calificacion".
 * URL: /api/calificaciones </pre>
 * <p>
 * <h2>Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio).
 * </pre>
 *
 * @author js.espitia
 */
@Path("calificaciones")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CalificacionResource {

    @Inject
    private CalificacionLogic logic;

    private static final Logger LOGGER = Logger.getLogger(CalificacionPersistence.class.getName());

    /**
     * <h1>POST /api/calificaciones : Crear una entidad de Calificacion.</h1>
     * <p>
     * <pre>Cuerpo de petición: JSON {@link CalificacionDTO}.
     * Crea una nueva entidad de Calificacion con la informacion que se recibe en el cuerpo
     * de la petición y se regresa un objeto identico con un id auto-generado
     * por la base de datos.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó la nueva calificacion .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: No es válida.
     * </code>
     * </pre>
     *
     * @param dto {@link CalificacionDTO} La entidad a guardar
     * @return JSON {@link CalificacionDTO} La entidad calificacion
     * guardada con su id correspondiente
     * @throws WebApplicationException {@link WebApplicationException} si falla alguna regla de negocio
     */
    @POST
    public CalificacionDTO createCalificacion(CalificacionDTO dto){
        try {
            logic.create(dto.toEntity());
        } catch (BusinessLogicException ex) {
            LOGGER.log(Level.INFO, "El BusinessLogicException se transforma a webapp");
            throw new WebApplicationException(ex.getMessage(), 412);
        }
        return dto;
    }

    /**
     * <h1>GET /api/calificaciones : Obtener todas las entidades de
     * Calificacion.</h1>
     * <p>
     * <pre>Busca y devuelve todas las entidades de Calificacion que existen en la aplicacion.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todas las calificaciones de la aplicacion.</code>
     * </pre>
     *
     * @return JSONArray con las entidades de Calificacion encontradas.
     */
    @GET
    public List<CalificacionDetailDTO> getCalificaciones() {
        List<CalificacionEntity> califs = logic.getAll();
        List<CalificacionDetailDTO> resp = new ArrayList<>();
        for (CalificacionEntity actual : califs) {
            resp.add(new CalificacionDetailDTO(actual));
        }
        return resp;
    }

    /**
     * <h1>GET /api/calificaciones/{id} : Obtener una entidad de Calificacion
     * por id.</h1>
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
     *
     * @param id el identificador que se asigna a la calificacion
     * @return JSON {@link CalificacionDetailDTO} con la informacion de la
     * entidad calificacion
     * @throws WebApplicationException {@link WebApplicationException} si no
     * existe
     */
    @GET
    @Path("{id: \\d+}")
    public CalificacionDetailDTO getCalificacion(@PathParam("id") Long id) throws WebApplicationException {
        CalificacionEntity calif = logic.getById(id);
        if (calif != null) {
            return new CalificacionDetailDTO(calif);
        }
        else{
            throw new WebApplicationException("No existe una calificacion con el id " + id , 404);
        }
     
    }

    /**
     * <h1>PUT /api/calificaciones/{id} : Actualizar una entidad de Calificacion
     * con el id dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link CalificacionDetailDTO}.
     * Actualiza la entidad de Calificacion con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza la calificacion con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una calificacion con el id dado.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precondition failed. No es una calificacion valida.
     * </code>
     * </pre>
     *
     * @param id identificador de la calificacion que se desea actualizar.Este
     * debe ser una cadena de numeros
     * @param dto {@link CalificacionDetailDTO} La entidad de Calificacion que
     * se desea guardar.
     * @return JSON {@link CalificacionDetailDTO} - La entidad de Calificacion
     * guardada.
     * @throws WebApplicationException {@link WebApplicationException} - Error de
     * lógica que se genera al no poder actualizar la entidad de Enfermero
     * porque ya existe una con ese nombre.
     */
    @PUT
    @Path("{id: \\d+}")
    public CalificacionDetailDTO updateCalificacion(@PathParam("id") Long id, CalificacionDetailDTO dto) {
        if(logic.getById(id) == null){
                throw new WebApplicationException("No existe una entidad de Calificacion con el ID \"" + id + "\"" ,404);
        }
        try {
            //TODO: DONE primero hay que validar que es una calificación válida
            if(dto!=null){
                CalificacionEntity entidad = dto.toEntity();
                entidad.setId(id);

                logic.update(entidad);
            }
        } catch (BusinessLogicException ex) {
            LOGGER.log(Level.INFO, "El BusinessLogicException se transforma a webapp");
            throw new WebApplicationException(ex.getMessage(), 412);
        }
       return dto;
    }

    /**
     * <h1>DELETE /api/calificaciones/{id} : Borrar una entidad de Calificacion
     * por id.</h1>
     * <p>
     * <pre>Borra la entidad de Calificacion con el id asociado recibido en la URL.
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la calificacion correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una calificacion con el id dado.
     * </code>
     * </pre>
     *
     * @param id identificador de la calificacion que se desea borrar. Este debe
     * ser una cadena de numeros.
     * @throws WebApplicationException {@link WebApplicationException} si se intenta eliminar una calificacion
     * que no existe
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCalificacion(@PathParam("id") Long id) throws WebApplicationException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar una calificacion con id {0}", id);
        //TODO: DONE primero hay que validar que es una calificaicón válida
        if(logic.getById(id)==null)
            throw new WebApplicationException("No existe una calificacion con el id = " + id, 404);
        logic.delete(id);
    }
}
