/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.resources;

import co.edu.uniandes.csw.viejitos.dtos.CitaDetailDTO;
import co.edu.uniandes.csw.viejitos.ejb.MedicoLogic;
import co.edu.uniandes.csw.viejitos.entities.CitaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * <pre>Clase que implementa el recurso "medicos/citas".
 * URL: /api/medcos/{medicoId}/citas
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * que el servicio {@link MedicoResource} define este servicio de forma relativa 
 * con la ruta "citas" con respecto un libro.</i>
 *
 * <h2>Anotaciones </h2>
 * <pre>
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio). 
 * </pre>
 * @author l.pardo
 * @version 1.0
 */
@Path("medicos/{medicoId: \\d+}/citas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MedicoCitasResource {
        @Inject
    private MedicoLogic medicoLogic;

    /**
     * Convierte una lista de CitaEntity a una lista de CitaDetailDTO.
     *
     * @param entityList Lista de CitaEntity a convertir.
     * @return Lista de CitaDetailDTO convertida.
     * 
     */
    private List<CitaDetailDTO> citasListEntity2DTO(List<CitaEntity> entityList) {
        List<CitaDetailDTO> list = new ArrayList<>();
        for (CitaEntity entity : entityList) {
            list.add(new CitaDetailDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de CitaDetailDTO a una lista de CitaEntity.
     *
     * @param dtos Lista de CitaDetailDTO a convertir.
     * @return Lista de CitaEntity convertida.
     * 
     */
    private List<CitaEntity> citasListDTO2Entity(List<CitaDetailDTO> dtos) {
        List<CitaEntity> list = new ArrayList<>();
        for (CitaDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }

    /**
     * <h1>GET /api/medicos/{medicoId}/citas : Obtener todos las citas de un medico.</h1>
     *
     * <pre>Busca y devuelve todos las citas que tiene medico.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todos las citas del libro.</code> 
     * </pre>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un medico con el id dado.
     * </code>
     * @param medicoId El ID del medico del cual se buscan las citas
     * @return JSONArray {@link CitaDetailDTO} - Las citas encontrados en el libro. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<CitaDetailDTO> listCitas(@PathParam("medicoId") Long medicoId) {
        return citasListEntity2DTO(medicoLogic.listCitas(medicoId));
    }

    /**
     * <h1>GET /api/medicos/{medicoId}/citas/{citaId} : Obtener una cita de un medico.</h1>
     *
     * <pre>Busca y devuelve la cita con el ID recibido en la URL, relativo a un
     * medico.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve la cita del medico.</code> 
     * </pre>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un medico con el id dado.
     * </code>
     * @param citaId El ID de la cita que se busca
     * @param medicoId El ID del medico del cual se busca el autor
     * @return {@link CitaDetailDTO} - La cita encontrado en el medico.
     */
    @GET
    @Path("{citaId: \\d+}")
    public CitaDetailDTO getCita(@PathParam("medicoId") Long medicoId, @PathParam("citaId") Long citaId) {
        return new CitaDetailDTO(medicoLogic.getCita(medicoId, citaId));
    }

    /**
     * <h1>POST /api/medicos/{medicoId}/citas/{citaId}} : Asociar una cita a un medico.</h1>
     *
     * <pre> Asocia una cita existente con un medico existente
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Asoció la cita .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found: No existe el medico o la cita
     * </code>
     * </pre>
     * @param citaId El ID de la cita que se va a asociar
     * @param medicoId El ID del medico al cual se le va a asociar la cita
     * @return JSON {@link CitaDetailDTO}  - El autor asociado.
     */
    @POST
    @Path("{citaId: \\d+}")
    public CitaDetailDTO addCita(@PathParam("medicoId") Long medicoId, @PathParam("citaId") Long citaId) {
        return new CitaDetailDTO(medicoLogic.addCita(medicoId, citaId));
    }

    /**
     * <h1>PUT /api/medicos/{medicoId}/citas/ : Actualizar las citas de un medico..</h1>
     *
     * <pre>Cuerpo de petición: JSONArray {@link AuthorDetailDTO}.
     * 
     * Actualiza la lista de citas de un medico con la lista que se recibe en el
     * cuerpo
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Se actualizó la lista de citas
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: No se pudo actualizar la lista
     * </code>
     * </pre>
     * @param medicoId El ID del medico al cual se le va a asociar la lista de autores
     * @param citas JSONArray {@link CitaDetailDTO} - La lista de autores que se desea guardar.
     * @return JSONArray {@link CitaDetailDTO}  - La lista actualizada.
     */
    @PUT
    public List<CitaDetailDTO> replaceCitas(@PathParam("medicoId") Long medicoId, List<CitaDetailDTO> citas) {
        return citasListEntity2DTO(medicoLogic.replaceCitas(medicoId, citasListDTO2Entity(citas)));
    }

    /**
     * <h1>DELETE /api/medicos/{medicoId}/citas/{citaId} : Desasociar cita por id.</h1>
     *
     * <pre>Elimina la conexión entre la cita y el medico recibidos en la URL.
     *
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la referencia a la cita.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una cita con el id dado en el medico.
     * </code>
     * </pre>
     * @param medicoId El ID del medico al cual se le va a desasociar la cita
     * @param citaId El ID de la cita que se desasocia
     */
    @DELETE
    @Path("{citaId: \\d+}")
    public void removeAuthors(@PathParam("medicoId") Long medicoId, @PathParam("citaId") Long citaId) {
        medicoLogic.removeCita(medicoId, citaId);
    }
}
