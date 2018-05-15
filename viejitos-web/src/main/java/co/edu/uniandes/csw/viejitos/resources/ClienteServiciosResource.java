/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.resources;

import co.edu.uniandes.csw.viejitos.dtos.ServicioDetailDTO;
import co.edu.uniandes.csw.viejitos.ejb.ClienteLogic;
import co.edu.uniandes.csw.viejitos.ejb.EnfermeroLogic;
import co.edu.uniandes.csw.viejitos.entities.ServicioEntity;
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
 * <pre>Clase que implementa el recurso "clientes/{id}/servicios".
 * URL: /api/clientes/{clienteId}/servicios
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * este recurso tiene la ruta "clientes/{clienteId}/servicios".</i>
 * <h2>Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio). 
 * </pre>
 * @author c.gomezs
 */

@Path("clientes/{clienteId: \\d+}/servicios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClienteServiciosResource {
    
    @Inject
    private ClienteLogic clienteLogic;

    /**
     * Convierte una lista de ServicioEntity a una lista de ServicioDetailDTO.
     * @param entityList Lista de ServicioEntity a convertir.
     * @return Lista de ServicioDetailDTO convertida.
     */
    private List<ServicioDetailDTO> serviciosListEntity2DTO(List<ServicioEntity> entityList) {
        List<ServicioDetailDTO> list = new ArrayList<>();
        for (ServicioEntity entity : entityList) {
            list.add(new ServicioDetailDTO(entity));
        }
        return list;
    }
    
     /**
     * Convierte una lista de ServicioDetailDTO a una lista de ServicioEntity.
     * @param dtos Lista de ServicioDetailDTO a convertir.
     * @return Lista de ServicioEntity convertida.
     * 
     */
    private List<ServicioEntity> serviciosListDTO2Entity(List<ServicioDetailDTO> dtos) {
        List<ServicioEntity> list = new ArrayList<>();
        for (ServicioDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
    
    /**
     * <h1>GET /api/clientes/{clienteId}/servicios : Obtener todos los servicios de un cliente.</h1>
     * <pre>Busca y devuelve todos los servicios que existen del cliente.
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todos los servicios de la aplicacion.</code> 
     * </pre>
     * @param clienteId Identificador del cliente que se esta buscando. Este debe ser una cadena de dígitos.
     * @return JSONArray {@linkServicioDetailDTO} - Los servicios encontrados del cliente. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<ServicioDetailDTO> listServicios(@PathParam("clienteId") Long clienteId) {
        return serviciosListEntity2DTO(clienteLogic.listServicios(clienteId));
    }
    private ExecutorService executorService = java.util.concurrent.Executors.newCachedThreadPool();
    
     /**
     * <h1>GET /api/enfermeros/{enfermerosId}/servicios/{servicioId} : Obtener servicio por id del enfermero por id.</h1>
     * <pre>Busca el servicio con el id asociado dentro del enfermero con id asociado.
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve el servicio correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe un servicio con el id dado dentro del enfermero.
     * </code> 
     * </pre>
     * @param enfermeroId Identificador del enfermero que se esta buscando. Este debe ser una cadena de dígitos.
     * @param servicioId Identificador del servicio que se esta buscando. Este debe ser una cadena de dígitos.
     * @return JSON {@link ServicioDetailDTO} - El servicio buscado
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando no se encuentra el enfermero o el servicio.
     */
    @GET
    @Path("{servicioId: \\d+}")
    public ServicioDetailDTO getServicio(@PathParam("enfermeroId") Long enfermeroId, @PathParam("servicioId") Long servicioId) throws BusinessLogicException {
        return new ServicioDetailDTO(enfermeroLogic.getServicio(enfermeroId, servicioId));
    }
    
     /**
     * <h1>POST /api/enfermeros/{enfermerosId}/servicios/{serviciosId} : Guarda un servicio dentro del enfermero.</h1>
     * <pre> Guarda un servicio dentro de un enfermero con la informacion que 
     * recibe el la URL. Se devuelve el servicio que se guarda en el enfermero.
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Guardó el nuevo servicio .
     * </code>
     * </pre>
     * @param enfermeroId Identificador del enfermero que se esta buscando. Este debe ser una cadena de dígitos.
     * @param servicioId Identificador del servicio que se desea guardar. Este debe ser una cadena de dígitos.
     * @return JSON {@link ServicioDetailDTO} - El servicio guardado en el enfermero.
     */
    @POST
    @Path("{servicioId: \\d+}")
    public ServicioDetailDTO addServicio(@PathParam("enfermeroId") Long enfermeroId, @PathParam("servicioId") Long servicioId) {
        return new ServicioDetailDTO(enfermeroLogic.addServicio(enfermeroId,servicioId));
    }
    
     /**
     * <h1>PUT /api/enfermeros/{enfermeroId}/servicios/{servicioId} : Edita los servicios de un enfermero..</h1>
     * <pre> Remplaza las instancias de Servicio asociadas a una instancia de Enfermero
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Guardó los servicios del enfermero.
     * </code>
     * </pre>
     * @param enfermeroId Identificador del enfermero que se esta buscando. Este debe ser una cadena de dígitos.
     * @param servicios JSONArray {@link ServicioDetailDTO} El arreglo de servicios nuevo para el enfermero.
     * @return JSON {@link ServicioDetailDTO} - El arreglo de servicios guardado en el enfermero.
     */
    @PUT
    public List<ServicioDetailDTO> replaceServicios(@PathParam("enfermeroId") Long enfermeroId, List<ServicioDetailDTO> servicios) {
        return serviciosListEntity2DTO(enfermeroLogic.replaceServicios(enfermeroId, serviciosListDTO2Entity(servicios)));
    }
    
     /**
     * <h1>DELETE /api/enfermeros/{enfermeroId}/servicios/{servicioId} : Elimina un servicio dentro del enfermero.</h1>
     * <pre> Elimina la referencia del servicio asociado al ID dentro del enfermero
     * con la informacion que recibe el la URL. 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Se eliminó la referencia del servicio.
     * </code>
     * </pre>
     * @param enfermeroId Identificador del enfermero que se esta buscando. Este debe ser una cadena de dígitos.
     * @param servicioId Identificador del servicio que se desea quitar. Este debe ser una cadena de dígitos.
     */
    @DELETE
    @Path("{servicioId: \\d+}")
    public void removeServicio(@PathParam("enfermeroId") Long enfermeroId, @PathParam("servicioId") Long servicioId) {
        enfermeroLogic.removeServicio(servicioId,enfermeroId);
    }
    
}
