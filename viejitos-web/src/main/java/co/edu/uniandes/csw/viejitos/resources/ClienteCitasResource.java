/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.resources;

import co.edu.uniandes.csw.viejitos.dtos.CitaDetailDTO;
import co.edu.uniandes.csw.viejitos.dtos.HistoriaClinicaDTO;
import co.edu.uniandes.csw.viejitos.dtos.HistoriaClinicaDetailDTO;
import co.edu.uniandes.csw.viejitos.ejb.CitaLogic;
import co.edu.uniandes.csw.viejitos.ejb.ClienteLogic;
import co.edu.uniandes.csw.viejitos.ejb.HistoriaClinicaLogic;
import co.edu.uniandes.csw.viejitos.entities.CitaEntity;
import co.edu.uniandes.csw.viejitos.entities.HistoriaClinicaEntity;
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
@Path("clientes/{clienteId: \\d+}/citas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class ClienteCitasResource 
{
    @Inject
    private ClienteLogic clienteLogic;
    
    @Inject
    private CitaLogic cLogic;

    /**
     * <h1>GET /api/clientes/{clienteId}/citas : Obtener la cita de un cliente.</h1>
     *
     * <pre>Busca y devuelve la cita que tiene el cliente.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve la cita del cliente.</code> 
     * </pre>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un cliente con el id dado.
     * </code>
     * @param clienteId El ID del cliente del cual se busca la cita
     * @return JSONArray {@link CitaDetailDTO} - La cita encontrada del cliente.
     * @throws co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException Cuando el cliente no tiene una Cita asociada.
     */
    @GET
    public CitaDetailDTO getCita(@PathParam("clienteId") Long clienteId) throws BusinessLogicException
    {
        if(clienteLogic.getById(clienteId) == null)
        {
             throw new WebApplicationException("No existe un cliente con ese id", 404);
        }
        else
        {
            CitaEntity entity = clienteLogic.getCita(clienteId);
            return new CitaDetailDTO(entity);
        }
    }

    /**
     * <h1>POST /api/clientes/{clienteId}/citas/ : Agregar una cita a un cliente.</h1>
     *
     * <pre> Agrega una cita a un cliente existente
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creo la cita .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found: No existe el cliente
     * </code>
     * </pre>
     * @param clienteId El ID del cliente al cual se le va a crear la cita
     * @param dto El dto de la Cita que se va a agregar
     * @return JSON {@link CitaDetailDTO} - La cita creada.
     * @throws co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException Cuando el cliente al cual se le quiere agregar la Cita no existe
     */
    @POST
    public CitaDetailDTO addCita(@PathParam("clienteId") Long clienteId, CitaDetailDTO dto) throws BusinessLogicException
    {
        if(clienteLogic.getById(clienteId) == null)
        {
             throw new WebApplicationException("No existe un cliente con ese id", 404);
        }
        else
        {
            return new CitaDetailDTO(clienteLogic.addCita(clienteId, dto.toEntity()));
        } 
    }

    /**
     * <h1>PUT /api/clientes/{clienteId}/citas/ : Actualizar la cita de un cliente.</h1>
     *
     * <pre>Cuerpo de petición: JSONArray {@link CitaDetailDTO}.
     * 
     * Actualiza la cita de un cliente con la cita que se recibe en el
     * cuerpo
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Se actualizó la cita
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: No se pudo actualizar la cita
     * </code>
     * </pre>
     * @param clienteId El ID del cliente al cual se le va a asociar la nueva cita
     * @param cita JSONArray {@link CitaDetailDTO} - La cita que se desea guardar.
     * @return JSONArray {@link CitaDetailDTO}  - La cita actualizada.
     */
    @PUT
    public CitaDetailDTO replaceCita(@PathParam("clienteId") Long clienteId, CitaDetailDTO cita) 
    {
        if(clienteLogic.getById(clienteId) == null)
        {
             throw new WebApplicationException("No existe un cliente con ese id", 404);
        }
        else
        {
           try
           {
               cita.setId(clienteLogic.getCita(clienteId).getId());
               return new CitaDetailDTO(clienteLogic.replaceCita(clienteId, cita.toEntity()));
           }
           catch(BusinessLogicException e)
           {
               throw new WebApplicationException(e.getMessage(), 404);
           }
        }
    }
    
    /**
     * <h1>DELETE /api/clientes/{clienteId}/citas/ : Borrar la Cita de un Cliente.</h1>
     * <pre>Borra la Cita de un CLiente cuyo id se recibe en la URL.
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la Cita correspondiente al cliente con el id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un cliente con el id dado, o dicho cliente no tiene una Cita asociada.
     * </code>
     * </pre>
     * @param clienteId El ID del Cliente del cual se va a eliminar la Cita.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando no se puede eliminar la Cita.
     */
    @DELETE
    public void deleteCita(@PathParam("clienteId") Long clienteId) throws BusinessLogicException {
        if(clienteLogic.getById(clienteId) == null)
        {
             throw new WebApplicationException("No existe un cliente con ese id", 404);
        } 
        try
        {
            clienteLogic.deleteCita(clienteId);
        }
        catch(BusinessLogicException e)
        {
            throw new WebApplicationException(e.getMessage(), 404);
        }
    }
}
