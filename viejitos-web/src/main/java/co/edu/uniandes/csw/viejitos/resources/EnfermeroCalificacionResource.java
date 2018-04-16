/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.resources;

import co.edu.uniandes.csw.viejitos.dtos.CalificacionDetailDTO;
import co.edu.uniandes.csw.viejitos.ejb.CalificacionLogic;
import co.edu.uniandes.csw.viejitos.entities.CalificacionEntity;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

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
    private CalificacionLogic logic;
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
        List<CalificacionEntity> califs = logic.getAllForEnf(enfermeroId);
        List<CalificacionDetailDTO> resp = new ArrayList<>();
        for (CalificacionEntity actual : califs) {
            resp.add(new CalificacionDetailDTO(actual));
        }
        return resp;
    }
}
