/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.resources;

import co.edu.uniandes.csw.viejitos.dtos.EnfermeroDTO;
import co.edu.uniandes.csw.viejitos.dtos.EnfermeroDetailDTO;
import co.edu.uniandes.csw.viejitos.ejb.EnfermeroLogic;
import co.edu.uniandes.csw.viejitos.entities.EnfermeroEntity;
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
 * <pre>Clase que implementa el recurso "Enfermero".
 * URL: /api/enfermeros </pre>
 * <p>
 * <h2>Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio).
 * Codigos de respuesta:
 *<code style="color: mediumseagreen; background-color: #eaffe0;">
 * 200 OK Creó la nueva entidad de Viejito.
 * </code>
 * <code style="color: #c7254e; background-color: #f9f2f4;">
 * 412 Precodition Failed: Ya existe la entidad de Enfermero.
 * </code>
 * </pre>
 *
 * @author js.espitia
 */
@Path("enfermeros")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class EnfermeroResource {

    @Inject
    private EnfermeroLogic logic;

    /**
     * <h1>POST /api/enfermeros : Crear una entidad de Enfermero.</h1>
     * <p>
     * <pre>Cuerpo de petición: JSON {@link EnfermeroDTO}.
     * Crea una nueva entidad de Enfermero con la informacion que se recibe en el cuerpo
     * de la petición y se regresa un objeto identico con un id auto-generado
     * por la base de datos.
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creo al nuevo enfermero .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe el enfermero.
     * </code>
     * </pre>
     *
     * @param enfermero {@link EnfermeroDetailDTO} La entidad a guardar
     * @return JSON {@link EnfermeroDetailDTO} La entidad enfermero guardada con
     * su id correspondiente
     */
    @POST
    public EnfermeroDTO createEnfermero(EnfermeroDTO enfermero)  {
        
        try {
            logic.create(enfermero.toEntity());
            return enfermero;
        } catch (BusinessLogicException ex) {
            throw new WebApplicationException(ex.getMessage(), 412);
        }
    }

    /**
     * <h1>GET /api/enfermeros : Obtener todas las entidadese de Enfermero.</h1>
     * <p>
     * <pre>Busca y devuelve todas las entidades de Enfermero que existen en la aplicacion.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todos los enfermeros de la aplicacion.</code>
     * </pre>
     *
     * @return JSONArray con las entidades de enfermero encontradas.
     */
    @GET
    public List<EnfermeroDetailDTO> getEnfermeros() {
        List<EnfermeroEntity> entidades = logic.getAll();
        List<EnfermeroDetailDTO> dtos = new ArrayList<>();
        for (EnfermeroEntity actual : entidades) {
            dtos.add(new EnfermeroDetailDTO(actual));
        }
        return dtos;
    }

    /**
     * <h1>GET /api/enfermeros/{id} : Obtener una entidad de Enfermeros por
     * id.</h1>
     * <p>
     * <pre>Busca la entidad de Enfermero con el id asociado recibido en la URL y la devuelve.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve al enfermero correspondiente al id.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe un enfermero con el id dado.
     * </code>
     * </pre>
     *
     * @param id el identificador que usa el enfermero
     * @return JSON {@link EnfermeroDetailDTO} con la informacion de la entidad
     * enfermero
     */
    @GET
    @Path("{id: \\d+}")
    public EnfermeroDetailDTO getEnfermero(@PathParam("id") Long id) {
        EnfermeroEntity entidad = logic.getById(id);
        if (entidad == null) {
            throw new WebApplicationException("No existe un enfermero con id " + id, 404);
        }
        EnfermeroDetailDTO edto = new EnfermeroDetailDTO(entidad);
        return edto;
    }

    /**
     * <h1>PUT /api/enfermeros/{id} : Actualizar una entidad de Enfermero con el
     * login dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link EnfermeroDetailDTO}.
     * Actualiza la entidad de Enfermero con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza al enfermero con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un enfermero con el id dado.
     * </code>
     * </pre>
     *
     * @param id identificador del enfermero que se desea actualizar.Este
     * debe ser una cadena alfanumérica
     * @param dto {@link EnfermeroDetailDTO} La entidad de Enfermero que se
     * desea guardar.
     * @return JSON {@link EnfermeroDetailDTO} - La entidad de Enfermero
     * guardada.
     */
    @PUT
    @Path("{id: \\d+}")
    public EnfermeroDetailDTO updateEnfermero(@PathParam("id") Long id, EnfermeroDetailDTO dto) {
        if(dto!=null){
            EnfermeroEntity entity = dto.toEntity();
            entity.setId(id);
            //TODO: Este código está errado. A menos que haya un error de lógica en el constyructor, nunca se dsiaparará una exception.
            // Se debe validar si existe y si no existe disparar WebApplicationException 
            if(logic.getById(id) == null){
                throw new WebApplicationException("No existe una entidad de Enfermero con el id\"" + id + "\"" ,404);
            }
            try {
                logic.update(entity);
            } catch (BusinessLogicException ex) {
                throw new WebApplicationException(ex.getMessage(), 404);
            }
        }
        return dto;
    }

    /**
     * <h1>DELETE /api/enfermeros/{id} : Borrar una entidad de Enfermero por
     * login.</h1>
     * <p>
     * <pre>Borra la entidad de Enfermero con el id asociado recibido en la URL.
     *
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina al enfermero correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un enfermero con el id dado.
     * </code>
     * </pre>
     *
     * @param id de usuario del enfermero que se desea borrar. Este
     * debe ser una cadena alfanumerica.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteEnfermero(@PathParam("id") Long id) {
        if(logic.getById(id)==null)
            throw new WebApplicationException("No existe un enfermero con el id " + id, 404);
        logic.delete(id);
    }
}
