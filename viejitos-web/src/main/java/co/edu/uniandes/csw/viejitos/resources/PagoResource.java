/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.resources;

import co.edu.uniandes.csw.viejitos.dtos.PagoDTO;
import co.edu.uniandes.csw.viejitos.dtos.PagoDetailDTO;
import co.edu.uniandes.csw.viejitos.ejb.PagoLogic;
import co.edu.uniandes.csw.viejitos.entities.PagoEntity;
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
 * <pre>Clase que implementa el recurso "pagos".
 * URL: /api/pagos
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta
 * "/api" y este recurso tiene la ruta "pagos".</i>
 *
 * <h2>Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio).
 * </pre>
 *
 * @author ISIS2603
 * @version 1.0
 */
//TODO: Revisar el path para llegar a este recurso
@Path("servicios/{idServicio: \\d+}/pagos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PagoResource {

    @Inject
    PagoLogic pagoLogic;

    /**
     * <h1>POST /api/pagos : Crear una entidad de Pago.</h1>
     * <pre>Cuerpo de petición: JSON {@link PagoDetailDTO}.
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Crea el pago.</code>
     * </pre> Crea una nueva entidad de Pago con la informacion que se recibe en
     * el cuerpo de la petición.
     *
     * @param dto {@link PagoDetailDTO} - La entidad de Pago que se desea
     * guardar.
     * @return JSON {@link PagoDetailDTO} - La entidad de Pago guardada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la entidad de Pago.
     */
    @POST
    public PagoDTO createPago(@PathParam("idServicio") Long idServicio, PagoDTO dto) throws BusinessLogicException {
        return new PagoDTO(pagoLogic.create(idServicio, dto.toEntity()));
    }

    /**
     * <h1>GET /api/pagos : Obtener todas las entidades de Pago.</h1>
     * <pre>Busca y devuelve todas las entidades de Pago que existen en la aplicacion.
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve los pagos.</code>
     * </pre>
     *
     * @param idServicio
     * @return JSONArray {@link PagoDetailDTO} - Las entidades de Pago
     * encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     * @throws co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException
     */
    @GET
    public List<PagoDetailDTO> getPagos(@PathParam("idServicio") Long idServicio) throws BusinessLogicException {
        List<PagoDetailDTO> pagos = new ArrayList<PagoDetailDTO>();
        for (PagoEntity actual : pagoLogic.getAll(idServicio)) {
            pagos.add(new PagoDetailDTO(actual));
        }
        return pagos;
    }

    /**
     * <h1>GET /api/pagos/{id} : Obtener una entidad de Pago por id.</h1>
     * <pre>Busca la entidad de Pago con el id asociado recibido en la URL y la devuelve.
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve el pago.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe el pago con el id dado.
     * </code></pre>
     *
     * @param id Identificador de la entidad de Pago que se esta buscando. Este
     * debe ser una cadena de dígitos.
     * @return JSON {@link PagoDetailDTO} - La entidad de Pago buscada
     */
    @GET
    @Path("{id: \\d+}")
    public PagoDetailDTO getPago(@PathParam("idServicio") Long idServicio, @PathParam("id") Long id) {
        PagoEntity entity = pagoLogic.getById(idServicio, id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /servicios/" + idServicio + "/pagos/" + id + " no existe.", 404);
        }
        return new PagoDetailDTO(entity);
    }

    /**
     * <h1>PUT /api/pagos/{id} : Actualizar una entidad de Pago con el id
     * dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link PagoDetailDTO}.
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza el pago con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un pago con el id dado.
     * </code>
     * </pre> Actualiza la entidad de Pago con el id recibido en la URL con la
     * informacion que se recibe en el cuerpo de la petición.
     *
     * @param idServicio
     * @param id Identificador de la entidad de Pago que se desea actualizar.
     * Este debe ser una cadena de dígitos.
     * @param detailDTO {@link PagoDetailDTO} La entidad de Pago que se desea
     * guardar.
     * @return JSON {@link PagoDetailDTO} - La entidad de Pago guardada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera al no poder actualizar la entidad de Pago
     * porque ya existe una con ese nombre.
     */
    @PUT
    @Path("{id: \\d+}")
    public PagoDetailDTO updatePago(@PathParam("idServicio") Long idServicio, @PathParam("id") Long id, PagoDetailDTO detailDTO) throws BusinessLogicException {
        detailDTO.setId(id);
        PagoEntity entity = pagoLogic.getById(idServicio, id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /servicios/" + idServicio + "/pagos/" + id + " no existe.", 404);
        }
        return new PagoDetailDTO(pagoLogic.update(idServicio, detailDTO.toEntity()));
    }

    /**
     * <h1>DELETE /api/pagos/{id} : Borrar una entidad de Pago por id.</h1>
     * <pre>Borra la entidad de Pago con el id asociado recibido en la URL.
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina el pago correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un pago con el id dado.</code>
     * </pre>
     *
     * @param idServicio
     * @param id Identificador de la entidad de Pago que se desea borrar. Este
     * debe ser una cadena de dígitos.
     * @throws co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deletePago(@PathParam("idServicio") Long idServicio, @PathParam("id") Long id) throws BusinessLogicException {
        PagoEntity entity = pagoLogic.getById(idServicio, id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /servicios/" + idServicio + "/pago/" + id + " no existe.", 404);
        }
        pagoLogic.delete(idServicio, id);
    }
}
