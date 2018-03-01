/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.resources;

import co.edu.uniandes.csw.viejitos.dtos.EnfermeroDetailDTO;
import co.edu.uniandes.csw.viejitos.ejb.EnfermeroLogic;
import co.edu.uniandes.csw.viejitos.entities.EnfermeroEntity;
import co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viejitos.mappers.BusinessLogicExceptionMapper;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *<pre>Clase que implementa el recurso "Enfermero".
 * URL: /api/Enfermeros </pre>
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
 * 412 Precodition Failed: Ya existe la entidad de Viejito.
 * </code>
 * </pre>
 * @author js.espitia
 */
@Path("Enfermeros")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class EnfermeroResource {
    
    private EnfermeroLogic logic;
    
    /**
     * <h1>POST /api/Enfermeros : Crear una entidad de Enfermero.</h1>
     * <p>
     * <pre>Cuerpo de petición: JSON {@link EnfermeroDetailDTO}. 
     * Crea una nueva entidad de Enfermero con la informacion que se recibe en el cuerpo
     * de la petición y se regresa un objeto identico con un id auto-generado
     * por la base de datos.
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó la nueva entidad de Viejito.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe la entidad de Viejito.
     * </code>
     * </pre>
     * @param enfermero La entidad a guardar
     * @return JSON La entidad enfermero guardada con su id correspondiente
     * @throws BusinessLogicException Si ya existe una entidad de enfermero igual
     */
    @POST
    public EnfermeroDetailDTO createEnfermero( EnfermeroDetailDTO enfermero ) throws BusinessLogicException{
        logic.create(enfermero.toEntity());
        return enfermero;
    }
    
    /**
     * <h1>GET /api/Enfermeros : Obtener todas las entidadese de Enfermero.</h1>
     * <p>
     * <pre>Busca y devuelve todas las entidades de Enfermero que existen en la aplicacion. </pre>
     * @return JSONArray con las entidades de enfermero encontradas.
     */
    @GET
    public List<EnfermeroDetailDTO> getEnfermeros (){
        List<EnfermeroEntity> entidades = logic.getAll();
        List<EnfermeroDetailDTO> dtos = new ArrayList<>();
        for(EnfermeroEntity actual:entidades){
           dtos.add(new EnfermeroDetailDTO(actual));
        }
        return dtos;
    }
    
    /**
     * <h1>GET /api/Enfermeros/{id} : Obtener una entidad de Enfermeros por id.</h1>
     * <p>
     * <pre>Busca la entidad de Enfermero con el login asociado recibido en la URL y la devuelve.
     *  * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó la nueva entidad de Viejito.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe la entidad de Viejito.
     * </code>
     * </pre>
     * @param login el identificador que usa el enfermero 
     * @return JSON con la informacion de la entidad enfermero
     */
    @GET
    @Path("{login:[a-zA-Z][a-zA-Z0-9]*}")
    public EnfermeroDetailDTO getEnfermero(@PathParam("login") String login){
        EnfermeroEntity entidad = logic.getByLogin(login);
        if(entidad==null){
            return null;
        }
        EnfermeroDetailDTO edto = new EnfermeroDetailDTO(entidad);
        return edto;
    }
    
    /**
     * <h1>PUT /api/Enfermeros/{id} : Actualizar una entidad de Enfermero con el login dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link EnfermeroDetailDTO}.
     * Actualiza la entidad de Enfermero con el login recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     *  * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó la nueva entidad de Viejito.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe la entidad de Viejito.
     * </code>
     * </pre>
     * @param login nombre de usuario del enfermero que se desea actualizar.Este debe ser una cadena alfanumérica
     * @param dto {@link EnfermeroDetailDTO} La entidad de Enfermero que se desea guardar.
     * @return JSON {@link EnfermeroDetailDTO} - La entidad de Enfermero guardada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar la entidad de Enfermero porque ya existe una con ese nombre.
     */
    @PUT
    @Path("{id:[a-zA-Z][a-zA-Z0-9]*}")
    public EnfermeroDetailDTO updateEnfermero(@PathParam("login") String login, EnfermeroDetailDTO dto) throws BusinessLogicException{
        logic.update(dto.toEntity());
        return dto;
    }
    
    /**
     * <h1>DELETE /api/Enfermeros/{id} : Borrar una entidad de Enfermero por login.</h1>
     * <p>
     * <pre>Borra la entidad de Enfermero con el login asociado recibido en la URL.
     *  * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó la nueva entidad de Viejito.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe la entidad de Viejito.
     * </code>
     * </pre>
     * @param login nombre de usuario del enfermero que se desea borrar. Este debe ser una cadena alfanumerica.
     */
    @DELETE
    @Path( "{login: \\d+}" )
    public void deleteEnfermero( @PathParam( "login" ) String login){
        try{
            logic.delete(logic.getByLogin(login));
        }catch(Exception e){}
    }
}
