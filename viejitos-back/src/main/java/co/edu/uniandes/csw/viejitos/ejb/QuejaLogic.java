/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.ejb;

import co.edu.uniandes.csw.viejitos.entities.QuejaEntity;
import co.edu.uniandes.csw.viejitos.entities.ServicioEntity;
import co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viejitos.persistence.QuejaPersistence;
import co.edu.uniandes.csw.viejitos.persistence.ClientePersistence;
import co.edu.uniandes.csw.viejitos.persistence.ServicioPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la entidad de Queja.
 * @author c.gomezs
 */
@Stateless
public class QuejaLogic {

    private static final Logger LOGGER = Logger.getLogger(QuejaLogic.class.getName());

    @Inject
    private QuejaPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    @Inject
    private ServicioLogic servicioLogic;
    
    //TODO: DONE esta variable no se usa

    //TODO: DONE esta variable no se usa

       /**
     * Se encarga de crear una Queja en la base de datos.
     * @param entity Objeto de QuejaEntity con los datos nuevos
     * @param servicioid id del Servicio el cual sera padre de la queja.
     * @return Objeto de QuejaEntity con los datos nuevos y su ID.
     */
    public QuejaEntity create(Long serviceid, QuejaEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de una Queja");
        // Invoca la persistencia para crear la entidad de Queja
        //TODO: No hay ninguna regla de negocio?  
        ServicioEntity servicio=servicioLogic.getById(serviceid);
        entity.setServicio(servicio);
        LOGGER.info("Termina proceso de creación de entidad de Queja");
        return persistence.create(entity);
    }

        /**
     * Obtiene la lista de los registros de Queja que pertenecen a un Servicio.
     * @param servicioid id del Servicio el cual es padre de las Quejas.
     * @return Colección de objetos de QuejaEntity.
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException Error cuando el servicio no tiene quejas.
     */
    public List<QuejaEntity> getAll(Long servicioid) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de consultar todas las entidades de Queja");
        ServicioEntity servicio=servicioLogic.getById(servicioid);
        if (servicio.getQuejas() == null) {
            throw new BusinessLogicException("El servicio que consulta aún no tiene quejas");
        }
       if (servicio.getQuejas().isEmpty()) {
            throw new BusinessLogicException("El servicio que consulta aún no tiene quejas");
        }
        LOGGER.info("Termina proceso de consultar todas las entidades de Queja");
        return servicio.getQuejas();
    }
   

     /**
     * Obtiene los datos de una instancia de Queja a partir de su ID.
     * La existencia del elemento padre Servicio se debe garantizar.
     * @param servicioid El id del Servicio buscado
     * @param quejaid Identificador de la Queja a consultar
     * @return Instancia de QuejaEntity con los datos de la Queja consultada.
     * 
     */
    public QuejaEntity getById(Long servicioid, Long quejaid) {
        return persistence.find(servicioid, quejaid);
    }
    
    /**
     * Actualiza la información de una instancia de Queja
     * @param entity Instancia de QuejaEntity con los nuevos datos.
     * @param servicioid id del Servicio el cual sera padre de la Queja actualizada.
     * @return Instancia de QuejaEntity con los datos actualizados.
     * 
     */
    public QuejaEntity update(Long servicioid, QuejaEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de actualizar queja");
        ServicioEntity servicio=servicioLogic.getById(servicioid);
        entity.setServicio(servicio);
        if (persistence.find(servicioid, entity.getId()) == null) {
            throw new BusinessLogicException("No existe una entidad de Queja con el id \"" + entity.getId() + "\"");
        }
        //TODO: No hay ninguna regla de negocio? 
        return persistence.update(entity);
    }

    
     /**
     * Elimina una instancia de Queja de la base de datos.
     * @param id Identificador de la instancia a eliminar.
     * @param servicioid id del Servicio el cual es padre de la Queja.
     * 
     */
    public void delete(Long servicioid, Long id) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la entidad de Queja con id={0}", id);
        //TODO: DONE este método debe recibir un id y hay que validar que existe un QuejaEntity con ese id
        if(persistence.find(servicioid,id) == null)
        {
           throw new BusinessLogicException("No existe una entidad de Queja con el id \"" + id + "\""); 
        }
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la entidad de Queja con id={0}", id);
    }
}
