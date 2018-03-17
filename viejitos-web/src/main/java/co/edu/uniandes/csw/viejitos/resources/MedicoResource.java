/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.resources;

import co.edu.uniandes.csw.viejitos.dtos.MedicoDTO;
import co.edu.uniandes.csw.viejitos.dtos.MedicoDetailDTO;
import co.edu.uniandes.csw.viejitos.ejb.MedicoLogic;
import co.edu.uniandes.csw.viejitos.entities.MedicoEntity;
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
 * <pre>Clase que implementa el recurso "Medico".</pre> URL: /api/Medico
 *
 * @author l.pardo
 */
@Path("medicos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class MedicoResource {

    @Inject
    MedicoLogic logic;

    /**
     * <h1>POST /api/medicos : Crear una entidad de Medico.</h1>
     * <p>
     * <
     * pre>Cuerpo de petición: JSON {@link MedicoDetailDTO}.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Crea el medico.</code>
     * </pre> Crea una nueva entidad de Medico con la informacion que se recibe
     * en el cuerpo de la petición.
     *
     * @param dto {@link MedicoDetailDTO} - La entidad de Medico que se desea
     * guardar.
     * @return JSON {@link MedicoDetailDTO} - La entidad de Medico guardada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la entidad de Servicio.
     */
    @POST
    public MedicoDTO createMedico(MedicoDTO dto) throws BusinessLogicException {
        return new MedicoDTO(logic.create(dto.toEntity()));
    }

    /**
     * <h1>GET /api/medicos : Obtener todas las entidades de Medico.</h1>
     * <p>
     * <
     * pre>Busca y devuelve todas las entidades de Medico que existen en la aplicacion.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve los medicos.</code>
     * </pre>
     *
     * @return JSONArray {@link MedicoDetailDTO} - Las entidades de Medico
     * encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<MedicoDetailDTO> getMedicos() {
        List<MedicoDetailDTO> list = new ArrayList<>();
        for (MedicoEntity e : logic.getAll()) {
            list.add(new MedicoDetailDTO(e));
        }
        return list;
    }

    /**
     * <h1>GET /api/medicos/{id} : Obtener una entidad de Medico por id.</h1>
     * <p>
     * <
     * pre>Busca la entidad de Medico con el id asociado recibido en la URL y la devuelve.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve el medico.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un medico con el id dado.
     * </code></pre>
     *
     * @param id Identificador de la entidad de Medico que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSON {@link MedicoDetailDTO} - La entidad de Medico buscada
     */
    @GET
    @Path("{id: \\d+}")
    public MedicoDetailDTO getMedico(@PathParam("id") Long id) {
        if (logic.getById(id) == null) {
            throw new WebApplicationException("El recurso /medicos/" + id + " no existe.", 404);
        }
        return new MedicoDetailDTO(logic.getById(id));
    }

    /**
     * <h1>PUT /api/Medicos/{id} : Actualizar una entidad de Medico con el id
     * dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link MedicoDetailDTO}.
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza el medico con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un medico con el id dado.
     * </code>
     * </pre>
     *
     * Actualiza la entidad de Medico con el id recibido en la URL con la
     * informacion que se recibe en el cuerpo de la petición.
     *
     * @param id Identificador de la entidad de Medico que se desea actualizar.
     * Este debe ser una cadena de dígitos.
     * @param detailDTO {@link MedicoDetailDTO} La entidad de Medico que se
     * desea guardar.
     * @return JSON {@link MedicoDetailDTO} - La entidad de Medico guardada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera al no poder actualizar la entidad de
     * Servicio porque ya existe una con ese nombre.
     */
    @PUT
    @Path("{id: \\d+}")
    public MedicoDetailDTO updateMedico(@PathParam("id") Long id, MedicoDetailDTO detailDTO) throws BusinessLogicException {
        MedicoEntity e = logic.getById(id);
        if (e == null) {
            throw new WebApplicationException("El recurso /medicos/" + id + " no existe.", 404);
        }
        return new MedicoDetailDTO(logic.update(detailDTO.toEntity()));

    }

    /**
     * <h1>DELETE /api/Medicos/{id} : Borrar una entidad de Medico por id.</h1>
     * <p>
     * <
     * pre>Borra la entidad de Medico con el id asociado recibido en la URL.
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 204 OK Elimina el medico correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un medico con el id dado.</code>
     * </pre>
     *
     * @param id Identificador de la entidad de Medico que se desea borrar. Este
     * debe ser una cadena de dígitos.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteMedico(@PathParam("id") Long id) {

        MedicoEntity e = logic.getById(id);
        if (e == null) {
            throw new WebApplicationException("El recurso /medicos/" + id + " no existe.", 404);
        }
        logic.delete(e);
    }
}
