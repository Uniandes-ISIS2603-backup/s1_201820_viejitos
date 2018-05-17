/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.resources;

import co.edu.uniandes.csw.viejitos.dtos.HistoriaClinicaDTO;
import co.edu.uniandes.csw.viejitos.dtos.HistoriaClinicaDetailDTO;
import co.edu.uniandes.csw.viejitos.ejb.ClienteLogic;
import co.edu.uniandes.csw.viejitos.ejb.HistoriaClinicaLogic;
import co.edu.uniandes.csw.viejitos.entities.HistoriaClinicaEntity;
import co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException;
import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import java.util.logging.Level;
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
import javax.ws.rs.core.MediaType;

/**
 * <pre>Clase que implementa el recurso "clientes/historiasc".
 * URL: /api/clientes/{clienteId}/historiasc
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
@Path("clientes/{clienteId: \\d+}/historiasc")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class HistoriaClinicaResource {

    @Inject
    private ClienteLogic clienteLogic;

    @Inject
    private HistoriaClinicaLogic hcLogic;

    /**
     * Convierte una HistoriaClinicaEntity a una HistoriaClinicaDetailDTO.
     *
     * @param entity HistoriaClinicaEntity a convertir.
     * @return HistoriaClinicaDetailDTO convertida.
     *
     */
    private HistoriaClinicaDTO historiaClinicaEntity2DTO(HistoriaClinicaEntity entity) {
        return new HistoriaClinicaDTO(entity);
    }

    /**
     * Convierte una lista de HistoriaClinicaDetailDTO a una lista de
     * HistoriaClinicaEntity.
     *
     * @param dtos Lista de HistoriaClinicaDetailDTO a convertir.
     * @return Lista de HistoriaClinicaEntity convertida.
     *
     */
    private HistoriaClinicaEntity historiasCDTO2Entity(HistoriaClinicaDTO dtos) {
        return dtos.toEntity();
    }

    /**
     * <h1>GET /api/clientes/{clienteId}/historiasC : Obtener la historia
     * clinica de un cliente.</h1>
     *
     * <pre>Busca y devuelve la historia clinica que tiene el cliente.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve la historia clinica del cliente.</code>
     * </pre>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un cliente con el id dado.
     * </code>
     *
     * @param clienteId El ID del cliente del cual se busca la historia clinica
     * @return JSONArray {@link HistoriaClinicaDetailDTO} - La historia clinica
     * encontrada del cliente.
     * @throws co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException
     * Cuando el cliente no tiene una HistoriaClinica asociada.
     */
    @GET
    public HistoriaClinicaDetailDTO getHistoriaC(@PathParam("clienteId") Long clienteId) throws BusinessLogicException {
        if (clienteLogic.getById(clienteId) == null) {
            throw new WebApplicationException("No existe un cliente con ese id", 404);
        } else {
            HistoriaClinicaEntity entity = hcLogic.get(clienteId);
            return new HistoriaClinicaDetailDTO(entity);
        }
    }

    /**
     * <h1>POST /api/clientes/{clienteId}/historiasc/{historiacId}} : Asociar
     * una historia clinica a un cliente.</h1>
     *
     * <pre> Asocia una historia clinica existente con un cliente existente
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Asoció la historia clinica .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found: No existe el cliente o la historia clinica
     * </code>
     * </pre>
     *
     * @param clienteId El ID del cliente al cual se le va a asociar la historia
     * clinica
     * @param dto El dto de la HistoriaClinica que se va a agregar
     * @return JSON {@link HistoriaClinicaDetailDTO} - La historia clinica
     * asociada.
     * @throws co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException
     * Cuando el cliente al cual se le quiere agregar la HistoriaClinica no
     * existe
     */
    @POST
    public HistoriaClinicaDetailDTO addHistoriaC(@PathParam("clienteId") Long clienteId, HistoriaClinicaDetailDTO dto) throws BusinessLogicException {
        if (clienteLogic.getById(clienteId) == null) {
            throw new WebApplicationException("No existe un cliente con ese id", 404);
        } else {
            return new HistoriaClinicaDetailDTO(hcLogic.create(clienteId, dto.toEntity()));
        }
    }

    /**
     * <h1>PUT /api/clientes/{clienteId}/historiasc/ : Actualizar la historia
     * clinica de un cliente.</h1>
     *
     * <pre>Cuerpo de petición: JSONArray {@link HistoriaClinicaDetailDTO}.
     *
     * Actualiza la historia clinica de un cliente con la historia clinica que se recibe en el
     * cuerpo
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Se actualizó la historia clinica
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: No se pudo actualizar la historia clinica
     * </code>
     * </pre>
     *
     * @param clienteId El ID del cliente al cual se le va a asociar la nueva
     * historia clinica
     * @param historiac JSONArray {@link HistoriaClinicaDetailDTO} - La historia
     * clinica que se desea guardar.
     * @return JSONArray {@link HistoriaClinicaDetailDTO} - La historia clinica
     * actualizada.
     */
    @PUT
    public HistoriaClinicaDetailDTO replaceHistoriaC(@PathParam("clienteId") Long clienteId, HistoriaClinicaDetailDTO historiac) {
        if (clienteLogic.getById(clienteId) == null) {
            throw new WebApplicationException("No existe un cliente con ese id", 404);
        } else {
            try {
                historiac.setId(hcLogic.get(clienteId).getId());
                return new HistoriaClinicaDetailDTO(hcLogic.update(clienteId, historiac.toEntity()));
            } catch (BusinessLogicException e) {
                LOGGER.log(Level.INFO, "El BusinessLogicException se transforma a webapp");
                throw new WebApplicationException(e.getMessage(), 404);
            }
        }
    }

    /**
     * <h1>DELETE /api/clientes/{clienteId}/historiasc/ : Borrar la
     * HistoriaClinica de un Cliente.</h1>
     * <pre>Borra la HistoriaClinica de un CLiente cuyo id se recibe en la URL.
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la HistoriaClinica correspondiente al cliente con el id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un cliente con el id dado, o dicho cliente no tiene una HistoriaClinica asociada.
     * </code>
     * </pre>
     *
     * @param clienteId El ID del Cliente del cual se va a eliminar la
     * HistoriaClinica.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede eliminar la
     * HistoriaClinica.
     */
    @DELETE
    public void deleteHistoriaC(@PathParam("clienteId") Long clienteId) throws BusinessLogicException {
        if (clienteLogic.getById(clienteId) == null) {
            throw new WebApplicationException("No existe un cliente con ese id", 404);
        }
        try {
            hcLogic.delete(clienteId);
        } catch (BusinessLogicException e) {
            LOGGER.log(Level.INFO, "El BusinessLogicException se transforma a webapp");
            throw new WebApplicationException(e.getMessage(), 404);
        }
    }
}
