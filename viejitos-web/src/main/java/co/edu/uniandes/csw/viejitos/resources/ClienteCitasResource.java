/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.resources;

import co.edu.uniandes.csw.viejitos.dtos.CitaDetailDTO;
import co.edu.uniandes.csw.viejitos.dtos.ServicioDetailDTO;
import co.edu.uniandes.csw.viejitos.ejb.ClienteLogic;
import co.edu.uniandes.csw.viejitos.entities.CitaEntity;
import co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
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
 * <pre>Clase que implementa el recurso "clientes/{id}/citas".
 * URL: /api/clientes/{id}/citas
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * este recurso tiene la ruta "clientes/{clienteId}/citas".</i>
 * <h2>Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio). 
 * </pre>
 * @author f.escobar
 */
@Path("clientes/{clienteId: \\d+}/citas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClienteCitasResource {
    
    @Inject
    private ClienteLogic clienteLogic;
    
    /**
     * Convierte una lista de CitaEntity a una lista de CitaDetailDTO.
     * @param entityList Lista de CitaEntity a convertir.
     * @return Lista de CitaDetailDTO convertida.
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
     * <h1>GET /api/clientes/{clienteId}/citas : Obtener todos las citas de un cliente.</h1>
     * <pre>Busca y devuelve todos las citas que existen del cliente.
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todos los servicios de la aplicacion.</code> 
     * </pre>
     * @param clienteId Identificador del cliente que se esta buscando. Este debe ser una cadena de dígitos.
     * @return JSONArray {@linkCitaDetailDTO} - Las citas encontradas del enfermero. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<CitaDetailDTO> listCitas(@PathParam("clienteId") Long clienteId) {
        return citasListEntity2DTO(clienteLogic.listCitas(clienteId));
    }
    
    private ExecutorService executorService = java.util.concurrent.Executors.newCachedThreadPool();
    
    /**
     * <h1>GET /api/clientes/{clienteId}/citas/{citaId} : Obtener cita por id del cliente por id.</h1>
     * <pre>Busca la cita con el id asociado dentro del cliente con id asociado.
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve la cita correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe una cita con el id dado dentro del cliente.
     * </code> 
     * </pre>
     * @param clienteId Identificador del cliente que se esta buscando. Este debe ser una cadena de dígitos.
     * @param citaId Identificador de la cita que se esta buscando. Este debe ser una cadena de dígitos.
     * @return JSON {@link CitaDetailDTO} - La cita buscado
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando no se encuentra el enfermero o el servicio.
     */
    @GET
    @Path("{citaId: \\d+}")
    public CitaDetailDTO getCita(@PathParam("clienteId") Long clienteId, @PathParam("citaId") Long citaId) throws BusinessLogicException {
        return new CitaDetailDTO(clienteLogic.getCita(clienteId, citaId));
    }
    
    /**
     * <h1>POST /api/clientes/{clienteId}/citas/{citaId} : Guarda una cita dentro del cliente.</h1>
     * <pre> Guarda una cita dentro de un cliente con la informacion que 
     * recibe el la URL. Se devuelve la cita que se guarda en el cliente.
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Guardó el nueva cita .
     * </code>
     * </pre>
     * @param clienteId Identificador del cliente que se esta buscando. Este debe ser una cadena de dígitos.
     * @param citaId Identificador de la cita que se desea guardar. Este debe ser una cadena de dígitos.
     * @return JSON {@link CitaDetailDTO} - La cita guardada en el cliente.
     */
    @POST
    @Path("{citaId: \\d+}")
    public CitaDetailDTO addCita(@PathParam("clienteId") Long clienteId, @PathParam("citaId") Long citaId) {
        return new CitaDetailDTO(clienteLogic.addCita(clienteId, citaId));
    }
    
    /**
     * <h1>PUT /api/clientes/{clienteId}/citas/{citaId} : Edita las citas de un cliente..</h1>
     * <pre> Remplaza las instancias de Citas asociadas a una instancia de cliente
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Guardó las citas del cliente.
     * </code>
     * </pre>
     * @param clienteId Identificador del cliente que se esta buscando. Este debe ser una cadena de dígitos.
     * @param cita La cita nueva para el cliente.
     * @return JSON {@link CitaDetailDTO} - El arreglo de citas guardado en el cliente.
     */
    @PUT
    public CitaDetailDTO replaceCita(@PathParam("clienteId") Long clienteId, CitaDetailDTO cita) throws BusinessLogicException {
        return new CitaDetailDTO(clienteLogic.replaceCita(clienteId, cita.toEntity()));
    }
    
    /**
     * <h1>DELETE /api/clientes/{clienteId}/citas/{citaId} : Elimina una cita dentro del cliente.</h1>
     * <pre> Elimina la referencia de la cita asociado al ID dentro del cliente
     * con la informacion que recibe el la URL. 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Se eliminó la referencia de la cita.
     * </code>
     * </pre>
     * @param clienteId Identificador del cliente que se esta buscando. Este debe ser una cadena de dígitos.
     * @param citaId Identificador de la cita que se desea quitar. Este debe ser una cadena de dígitos.
     */
    @DELETE
    @Path("{citaId: \\d+}")
    public void removeCita(@PathParam("clienteId") Long clienteId, @PathParam("citaId") Long citaId) {
        clienteLogic.deleteCita(clienteId,citaId);
    }
    
    
}
