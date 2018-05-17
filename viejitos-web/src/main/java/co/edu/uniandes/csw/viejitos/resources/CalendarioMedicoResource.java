/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.resources;

import co.edu.uniandes.csw.viejitos.dtos.CalendarioSemanalDTO;
import co.edu.uniandes.csw.viejitos.dtos.CalendarioSemanalDetailDTO;
import co.edu.uniandes.csw.viejitos.ejb.CalendarioSemanalLogic;
import co.edu.uniandes.csw.viejitos.entities.CalendarioSemanalEntity;
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

/**
 *
 * @author lf.naranjo11
 */

    
    

/**
 *
 * <pre>Clase que implementa el recurso "calendarioSemanal".
 * URL: /api/medicos/{idMedico}/calendariosemanal
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * que el servicio {@link MedicoResource} define este servicio de forma relativa 
 * con la ruta "calendarios" con respecto un libro.</i>
 *
 * <h2>Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio).
 * </pre>
 *
 * @author lf.naranjo11
 */



@Path("medicos/{medicosId: \\d+}/calendariossemanales")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CalendarioMedicoResource  {

    @Inject
    private CalendarioSemanalLogic calendarioLogic;

    /**
     * <h1>POST /api/medicos/{idMedico}/calendariossemanales : Crear una entidad(la unica) de
     * calendarioSemanal.</h1>
     *
     * <pre>Cuerpo de petición: JSON {@link calendarioSemanalDetailDTO}.
     *
     * Crea una nueva entidad de calendario semanal con la informacion que se recibe en el cuerpo
     * de la petición.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó el calendario semanal .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe el calendario.
     * </code>
     * </pre>
     *
     * @param dto {@link  calendarioSemanalDetailDTO} - La entidad de calendario
     * que se desea guardar.
       * @param idMedico El ID del libro del cual se guarda la reseña
     * @return JSON {@link calendarioSemanalDetailDTO} - La entidad de
     * calendario que se desea guardar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la entidad de calnedario
     * semanal.
     */
    @POST
    public CalendarioSemanalDTO createCalendario(CalendarioSemanalDTO dto,@PathParam("idMedico") Long idMedico) throws BusinessLogicException {
       
       CalendarioSemanalDTO dto2=new CalendarioSemanalDTO(calendarioLogic.createCalendario(dto.toEntity()));
            if(dto2!=null)
            {  
            calendarioLogic.setCalendariotoMedico(idMedico,dto2.getId());
	 
            }
            return  dto2;  
    }
    
     /**
     *
     * <h1>GET  /api/medicos/{idMedico}/calendariossemanales/{id} : Obtener la entidad de calendario
     * </h1>
     * <pre>Busca la entidad de calendario  y la devuelve.
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve el calendario semanal.</code>
     * </pre>
     * @param idMedico  identificador del medico al cual pertenece
     *@throws WebApplicationException si la entidad no es buena o es nula.
     * @return JSON {@link CalendarioSemanalDetailDTO} - La entidad de
     * calenadrio buscada
     */
    @GET
    public CalendarioSemanalDetailDTO getCalendario(@PathParam("idMedico") Long idMedico) throws WebApplicationException {
        CalendarioSemanalEntity entity = calendarioLogic.getCalendarioByMedico(idMedico);
        if (entity == null) {
            throw new WebApplicationException("el calendarario del medico con id"+idMedico+"noexiste",404);
         }
       CalendarioSemanalDetailDTO dto=new CalendarioSemanalDetailDTO(entity);
        return dto;
    }

}