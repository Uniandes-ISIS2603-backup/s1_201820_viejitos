/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.resources;


import co.edu.uniandes.csw.viejitos.dtos.UserDTO;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * <pre>Clase que implementa el recurso "login".
 * URL: /login
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * este recurso tiene la ruta "login".</i>
 *
 * <h2>Anotaciones </h2>
 * <pre>
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio). 
 * </pre>
 * @author lf.naranjo11
 * @version 1.0
 */
@Path("/login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class loginResource {
  
    /**
     * <h1>POST /login : Hace el inicio de sesión de un usuario.</h1>
     *
     * <pre>Cuerpo de petición: JSON {@link UserDTO}.
     * 
     * Se inicia la sesión del usuario que se recibe en el cuerpo de la petición.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Se inició sesión.
     * </code>
     * </pre>
     * @param dto {@link UserDTO} - El usuario que intenta acceder.
     * @return JSON {@link UserDTO} - El usuario que inicia la sesión.
     */
    @POST
    public UserDTO login(UserDTO dto) {
        return dto;
    }

}