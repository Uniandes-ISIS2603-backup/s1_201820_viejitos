/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.resources;

import co.edu.uniandes.csw.viejitos.dtos.CalificacionDetailDTO;
import co.edu.uniandes.csw.viejitos.ejb.CalificacionLogic;
import co.edu.uniandes.csw.viejitos.ejb.ClienteLogic;
import co.edu.uniandes.csw.viejitos.entities.CalificacionEntity;
import co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException;
import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import java.util.ArrayList;
import java.util.List;
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
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * que el servicio {@link MedicoResource} define este servicio de forma relativa 
 * con la ruta "citas" con respecto un libro.</i>
 *
 * <h2>Anotaciones </h2>
 * <pre>
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio). 
 * </pre>
 * @author jj.silva
 * @version 1.0
 */
@Path("clientes/{clienteId: \\d+}/calificaciones")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class ClienteCalificacionResource 
{
    @Inject
    private ClienteLogic clienteLogic;
    
    @Inject
    private CalificacionLogic cLogic;

    /**
     * <h1>GET /api/clientes/{clienteId}/calificaciones : Obtener las calificaciones de un cliente.</h1>
     *
     * <pre>Busca y devuelve las calificaciones que tiene el cliente.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve las calificaciones del cliente.</code> 
     * </pre>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un cliente con el id dado.
     * </code>
     * @param clienteId El ID del cliente del cual se busca las calificaciones
     * @return JSONArray {@link CalificacionDetailDTO} - Las calificaciones encontradas del cliente.
     * @throws co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException Cuando el cliente no tiene Calificaciones asociadas.
     */
    @GET
    public List<CalificacionDetailDTO> getCalificaciones(@PathParam("clienteId") Long clienteId) throws BusinessLogicException
    {
        if(clienteLogic.getById(clienteId) == null)
        {
             throw new WebApplicationException("No existe un cliente con ese id", 404);
        }
        else
        {
            List<CalificacionDetailDTO> calificaciones = new ArrayList<CalificacionDetailDTO>();
            for (CalificacionEntity actual : clienteLogic.getCalificaciones(clienteId)) {
                calificaciones.add(new CalificacionDetailDTO(actual));
            }
        return calificaciones;
        }
    }
    
    /**
     * <h1>GET /api/clientes/{clienteId}/calificaciones/{id} : Obtener una calificacion de un Cliente.</h1>
     * <pre>Busca y devuelve la calificacion con el ID recibido en la URL, relativa a un Cliente.
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve la calificacion del Cliente.</code> 
     * </pre>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un cliente con el id dado.
     * </code>
     * @param clienteId El ID del cliente del cual se busca la calificacion
     * @param id El ID de la calificacion que se busca
     * @return {@link CalificacionDetailDTO} - La calificacion encontrada en el cliente.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando no se encuentra la calificacion.
     */
    @GET
    @Path("{id: \\d+}")
    public CalificacionDetailDTO getCalificacion(@PathParam("clienteId") Long clienteId, @PathParam("id") Long id) {
        if(clienteLogic.getById(clienteId) == null)
        {
             throw new WebApplicationException("No existe un cliente con ese id", 404);
        }
        try
        {
        CalificacionEntity entity = clienteLogic.getCalificacionById(clienteId, id);
        return new CalificacionDetailDTO(entity);
        }
        catch(BusinessLogicException e)
        {
            LOGGER.log(Level.INFO, "El BusinessLogicException se transforma a webapp");
            throw new WebApplicationException(e.getMessage(), 404);
        }
    }

    /**
     * <h1>POST /api/clientes/{clienteId}/calificaciones/ : Crear una calificacion para un cliente</h1>
     *
     * <pre> Crea una calificacion para un cliente existente
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creo la calificacion .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found: No existe el cliente
     * </code>
     * </pre>
     * @param clienteId El ID del cliente al cual se le va a crear la calificacion
     * @param dto El dto de la Calificacion que se va a agregar
     * @return JSON {@link CalificacionDetailDTO} - La calificacion creada.
     * @throws co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException Cuando el cliente al cual se le quiere agregar la Calificacion no existe
     */
    @POST
    public CalificacionDetailDTO addCalificacion(@PathParam("clienteId") Long clienteId, CalificacionDetailDTO dto) throws BusinessLogicException
    {
        if(clienteLogic.getById(clienteId) == null)
        {
             throw new WebApplicationException("No existe un cliente con ese id", 404);
        }
        else
        {
            return new CalificacionDetailDTO(clienteLogic.addCalificacion(clienteId, dto.toEntity()));
        } 
    }
    
    /**
     * <h1>DELETE /api/clientes/{clienteId}/calificaciones/{calificacionId} : Elimina una calificacion de un cliente.</h1>
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Se eliminó la calificacion del cliente.
     * </code>
     * </pre>
     * @param clienteId Identificador del cliente que se esta buscando.
     * @param id Identificador de la calificacion que se desea borrar.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void removeCalificacion(@PathParam("clienteId") Long clienteId, @PathParam("id") Long id) {
        clienteLogic.removeCalificacion(clienteId, id);
    }
}
