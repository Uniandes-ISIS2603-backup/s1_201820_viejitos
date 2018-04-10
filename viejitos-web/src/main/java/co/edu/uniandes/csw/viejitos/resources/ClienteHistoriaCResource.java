/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.resources;

import co.edu.uniandes.csw.viejitos.dtos.CitaDetailDTO;
import co.edu.uniandes.csw.viejitos.dtos.HistoriaClinicaDetailDTO;
import co.edu.uniandes.csw.viejitos.ejb.ClienteLogic;
import co.edu.uniandes.csw.viejitos.ejb.MedicoLogic;
import co.edu.uniandes.csw.viejitos.entities.CitaEntity;
import co.edu.uniandes.csw.viejitos.entities.HistoriaClinicaEntity;
import java.util.ArrayList;
import java.util.List;
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
@Path("clientes/{clienteId: \\d+}/historiasc")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClienteHistoriaCResource 
{
    @Inject
    private ClienteLogic clienteLogic;

    /**
     * Convierte una HistoriaClinicaEntity a una HistoriaClinicaDetailDTO.
     *
     * @param entity HistoriaClinicaEntity a convertir.
     * @return HistoriaClinicaDetailDTO convertida.
     * 
     */
    private HistoriaClinicaDetailDTO historiaClinicaEntity2DetailDTO(HistoriaClinicaEntity entity)
        {
            return new HistoriaClinicaDetailDTO(entity);
        }

    /**
     * Convierte una lista de HistoriaClinicaDetailDTO a una lista de HistoriaClinicaEntity.
     *
     * @param dtos Lista de HistoriaClinicaDetailDTO a convertir.
     * @return Lista de HistoriaClinicaEntity convertida.
     * 
     */
    private HistoriaClinicaEntity historiasCDTO2Entity(HistoriaClinicaDetailDTO dtos) {
        return dtos.toEntity();
    }

    /**
     * <h1>GET /api/clientes/{clienteId}/historiasC : Obtener la historia clinica de un cliente.</h1>
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
     * @param clienteId El ID del cliente del cual se busca la historia clinica
     * @return JSONArray {@link HistoriaClinicaDetailDTO} - La historia clinica encontrada del cliente.
     */
    @GET
    public HistoriaClinicaDetailDTO getHistoriaC(@PathParam("clienteId") Long clienteId) {
        return historiaClinicaEntity2DetailDTO(clienteLogic.getHistoriaC(clienteId));
    }

    /**
     * <h1>POST /api/clientes/{clienteId}/historiasc/{historiacId}} : Asociar una historia clinica a un cliente.</h1>
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
     * @param historiacId El ID de la historia clinica que se va a asociar
     * @param clienteId El ID del cliente al cual se le va a asociar la historia clinica
     * @return JSON {@link HistoriaClinicaDetailDTO} - La historia clinica asociada.
     */
    @POST
    @Path("{historiacId: \\d+}")
    public HistoriaClinicaDetailDTO addHistoriaC(@PathParam("clienteId") Long clienteId, @PathParam("historiacId") Long historiacId) {
        return new HistoriaClinicaDetailDTO(clienteLogic.addHistoriaC(clienteId, historiacId));
    }

    /**
     * <h1>PUT /api/clientes/{clienteId}/historiasc/ : Actualizar la historia clinica de un cliente.</h1>
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
     * @param clienteId El ID del cliente al cual se le va a asociar la nueva historia clinica
     * @param historiac JSONArray {@link HistoriaClinicaDetailDTO} - La historia clinica que se desea guardar.
     * @return JSONArray {@link HistoriaClinicaDetailDTO}  - La historia clinica actualizada.
     */
    @PUT
    public HistoriaClinicaDetailDTO replaceHistoriaC(@PathParam("clienteId") Long clienteId, HistoriaClinicaDetailDTO historiac)
    {
        return historiac;
    }
}
