/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.resources;

import co.edu.uniandes.csw.viejitos.dtos.ClienteDetailDTO;
import co.edu.uniandes.csw.viejitos.dtos.QuejaDetailDTO;
import co.edu.uniandes.csw.viejitos.dtos.QuejaDTO;
import co.edu.uniandes.csw.viejitos.ejb.QuejaLogic;
import co.edu.uniandes.csw.viejitos.entities.QuejaEntity;
import co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viejitos.mappers.BusinessLogicExceptionMapper;
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
 * <pre>Clase que implementa el recurso "quejas".
 * URL: /api/servicios/{idServicios}/quejas
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * que el servicio {@linkServicioResource} define este servicio de forma relativa 
 * con la ruta "quejas" con respecto un servicio.</i>
 *
 * <h2>Anotaciones </h2>
 * <pre>
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio). 
 * </pre>
 * @author c.gomezs
 */
//TODO: DONE Revisar el path para llegar a este recurso
@Path("servicios/{idServicio: \\d+}/quejas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class QuejaResource {

    @Inject
    private QuejaLogic quejaLogic;

/**
     * <h1>POST /api/servicios/{idServicio}/quejas : Crear una queja de un servicio.</h1>
     *
     * <pre>Cuerpo de petición: JSON {@link ReviewDTO}.
     * 
     * Crea una nueva queja con la informacion que se recibe en el cuerpo 
     * de la petición y se regresa un objeto identico con un id auto-generado 
     * por la base de datos.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó la nueva queja .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe la queja.
     * </code>
     * </pre>
     * @param idServicio El ID del servicio del cual se guarda la queja
     * @param queja {@link QuejaDTO} - La queja que se desea guardar.
     * @return JSON {@link QuejaDTO}  - La queja guardada con el atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe la queja.
     */
    @POST
    public QuejaDTO createQueja(@PathParam("idServicio") Long idServicio, QuejaDTO dto) throws BusinessLogicException {
        return new QuejaDTO(quejaLogic.create(idServicio, dto.toEntity()));
    }

   /**
     * <h1>GET /api/servicios/{idServicio}/quejas : Obtener todas las quejas de un servicio.</h1>
     * <pre>Busca y devuelve todas las quejas que existen en un servicio.
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todas las quejas del servicio.</code> 
     * </pre>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un servicio con el id dado.
     * </code>
     * @param idServicio El ID del servicio del cual se buscan las quejas
     * @return JSONArray {@link QuejaDetailDTO} - Las quejas encontradas en el servicio. Si no hay ninguna retorna una lista vacía.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando no se encuentra el servicio.
     */
    @GET
    public List<QuejaDetailDTO> getQuejas(@PathParam("idServicio") Long idServicio) throws BusinessLogicException {
        List<QuejaDetailDTO> quejas = new ArrayList<QuejaDetailDTO>();
        for (QuejaEntity actual : quejaLogic.getAll(idServicio)) {
            quejas.add(new QuejaDetailDTO(actual));
        }
        return quejas;
    }
    
    /**
     * <h1>GET /api/quejas/{idServicio}/quejas/{id} : Obtener una queja de un servicio.</h1>
     * <pre>Busca y devuelve la queja con el ID recibido en la URL, relativa a un servicio.
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve la queja del servicio.</code> 
     * </pre>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un servicio con el id dado.
     * </code>
     * @param idServicio El ID del servicio del cual se buscan las quejas
     * @param id El ID de la queja que se busca
     * @return {@link QuejaDetailDTO} - La queja encontrada en el servicio.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando no se encuentra el libro.
     */
    @GET
    @Path("{id: \\d+}")
    public QuejaDetailDTO getQueja(@PathParam("idServicio") Long idServicio, @PathParam("id") Long id) {
        QuejaEntity entity = quejaLogic.getById(idServicio, id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /servicios/" + idServicio + "/quejas/" + id + " no existe.", 404);
        }
        return new QuejaDetailDTO(entity);
    }

   /**
     * <h1>PUT /api/servicios/{idServicio}/quejas/{id} : Actualizar una queja de un servicio.</h1>
     * <pre>Cuerpo de petición: JSON {@link QuejaDetailDTO}.
     * Actualiza una queja con la informacion que se recibe en el cuerpo de la petición y se regresa el objeto actualizado.
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Se actualizó la queja
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: No se pudo actualizar la queja
     * </code>
     * </pre>
     * @param idServicio El ID del servicio del cual se guarda la queja
     * @param id El ID de la queja que se va a actualizar
     * @param review {@link QuejaDetailDTO} - La queja que se desea guardar.
     * @return JSON {@link QuejaDetailDTO}  - La queja actualizada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe la queja.
     */
    @PUT
    @Path("{id: \\d+}")
    public QuejaDetailDTO updateQueja(@PathParam("idServicio") Long idServicio, @PathParam("id") Long id, QuejaDetailDTO detailDTO) throws BusinessLogicException {
        detailDTO.setId(id);
        QuejaEntity entity = quejaLogic.getById(idServicio, id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /servicios/" + idServicio + "/quejas/" + id + " no existe.", 404);
        }
        return new QuejaDetailDTO(quejaLogic.update(idServicio, detailDTO.toEntity()));
    }

    /**
     * <h1>DELETE /api/servicios/{idServicio}/quejas/{id} : Borrar queja por id.</h1>
     * <pre>Borra la queja con el id asociado recibido en la URL.
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la queja correspondiente al id dado dentro del servicio.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una queja con el id dado en el servicio.
     * </code>
     * </pre>
     * @param idServicio El ID del servicio del cual se va a eliminar la queja.
     * @param id El ID de la queja que se va a eliminar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando no se puede eliminar la queja.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteQueja(@PathParam("idServicio") Long idServicio, @PathParam("id") Long id) throws BusinessLogicException {
        QuejaEntity entity = quejaLogic.getById(idServicio, id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /servicios/" + idServicio + "/quejas/" + id + " no existe.", 404);
        }
        quejaLogic.delete(idServicio, id);
    }
}
