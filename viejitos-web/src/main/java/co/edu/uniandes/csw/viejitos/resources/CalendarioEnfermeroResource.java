/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.resources;

import co.edu.uniandes.csw.viejitos.dtos.CalendarioSemanalDTO;
import co.edu.uniandes.csw.viejitos.dtos.CalendarioSemanalDetailDTO;
import co.edu.uniandes.csw.viejitos.dtos.EnfermeroDetailDTO;
import co.edu.uniandes.csw.viejitos.ejb.CalendarioSemanalLogic;
import co.edu.uniandes.csw.viejitos.entities.CalendarioSemanalEntity;
import co.edu.uniandes.csw.viejitos.entities.EnfermeroEntity;
import co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException;
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
 * URL: /api/enfermeros/{idMedico}/calendariosemanal
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



@Path("enfermeros/{idEnfermero: \\d+}/calendariossemanales")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CalendarioEnfermeroResource {


    @Inject
    private CalendarioSemanalLogic calendarioLogic;

    /**
     * <h1>POST /api/enfermeros/{idEnfermero}/calendariossemanales : Crear una entidad(la unica) de
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
       * @param idEnfermero El ID del libro del cual se guarda la reseña
     * @return JSON {@link calendarioSemanalDetailDTO} - La entidad de
     * calendario que se desea guardar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la entidad de calnedario
     * semanal.
     */
    @POST
    public CalendarioSemanalDTO createCalendario(CalendarioSemanalDTO dto,@PathParam("idEnfermero") Long idEnfermero) throws BusinessLogicException {
       
       CalendarioSemanalDTO dto2=new CalendarioSemanalDTO(calendarioLogic.createCalendario(dto.toEntity()));
            if(dto2!=null)
            {  
            calendarioLogic.setCalendariotoEnfermero(idEnfermero,dto2.getId());
	 
            }
            return  dto2;
       
    }

    /**
     *
     * <h1>GET  /api/enfermeros/{idEnfermero}/calendariossemanales/{id} : Obtener la entidad de calendario
     * </h1>
     *
     * <pre>Busca la entidad de calendario  y la devuelve.
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve el calendario semanal.</code>
     * </pre>
     * @param idEnfermero  identificador del medico al cual pertenece
     *@throws WebApplicationException si la entidad no es buena o es nula.
     * @return JSON {@link CalendarioSemanalDetailDTO} - La entidad de
     * calenadrio buscada
     */
    @GET
    public CalendarioSemanalDetailDTO getCalendario(@PathParam("idEnfermero") Long idEnfermero) throws WebApplicationException {
        CalendarioSemanalEntity entity = calendarioLogic.getCalendarioByEnfermero(idEnfermero);
        if (entity == null) {
            throw new WebApplicationException("el calendarario del enfermero con id"+idEnfermero+"noexiste",404);
         }
       CalendarioSemanalDetailDTO dto=new CalendarioSemanalDetailDTO(entity);
        return dto;
    }

   

    /**
     * <h1>PUT  /api/enfermeros/{idEnfermero}/calendariossemanales/ : Actualizar la entidad de calendario
     * .</h1>
     * <pre>Cuerpo de petición: JSON {@link CalendarioSemanalDetailDTO}.
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza el calendario con la información enviada como parámetro. Retorna un objeto identico.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un calendario semanal.
     * </code>
     * </pre> Actualiza la entidad de calendario con la informacion que se
     * recibe en el cuerpo de la petición.
     *
     * @param detailDTO {@link CalendarioSemanalDetailDTO} La entidad de
     * calendario que se desea guardar.
     * @return JSON {@link CalendarioSemanalDetailDTO} - La entidad de
     * calendario guardada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera al no poder actualizar la entidad de
     * Cliente porque ya existe una con ese nombre.
     */
    @PUT
    @Path( "{id: \\d+}" )
    public CalendarioSemanalDetailDTO updateCalendario( @PathParam("id")Long id,@PathParam("idEnfermero") Long idEnfermero) throws BusinessLogicException {
        
        
        CalendarioSemanalEntity ent=calendarioLogic.setCalendariotoEnfermero(idEnfermero, id);
        
        if (ent == null) {
            throw new WebApplicationException("El recurso medicos"+idEnfermero+"+/calendariossemanales/" + id + " no existe.", 404);
        }
        return new CalendarioSemanalDetailDTO(ent);
        
    }

    /**
     * <h1>DELETE  /api/enfermeros/{idEnfermeros}/calendariossemanales/Borrar la entidad de calendario .</h1>
     * <pre>Borra la entidad de calendario asociado recibido en la URL.
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina el calendario semanal.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un calendario semanal
     * </code>
     * </pre>
     */
    @DELETE
    public EnfermeroDetailDTO deleteCalendario(@PathParam("idEnfermero") Long idEnfermero) throws BusinessLogicException {
     
        EnfermeroEntity ent= calendarioLogic.removeCalendarioOfEnfermero(idEnfermero);
        if(ent == null)
        {
         throw new WebApplicationException("El recurso enfermero con"+idEnfermero+ " no existe.", 404);
        } 
        return new EnfermeroDetailDTO(ent);
        
    }
    
    
}