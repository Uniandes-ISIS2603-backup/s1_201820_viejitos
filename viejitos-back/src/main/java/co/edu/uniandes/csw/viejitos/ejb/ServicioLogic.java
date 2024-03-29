/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.ejb;

import co.edu.uniandes.csw.viejitos.entities.ServicioEntity;
import co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viejitos.persistence.ClientePersistence;
import co.edu.uniandes.csw.viejitos.persistence.EnfermeroPersistence;
import co.edu.uniandes.csw.viejitos.persistence.PagoPersistence;
import co.edu.uniandes.csw.viejitos.persistence.ServicioPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author c.gomezs
 */
@Stateless
public class ServicioLogic {

    private static final Logger LOGGER = Logger.getLogger(QuejaLogic.class.getName());

    @Inject
    private ServicioPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
    //TODO: DONE esta variable no se usa
    //TODO: DONE esta variable no se usa

    @Inject
    private EnfermeroPersistence enfermeroPersistence;

    public ServicioEntity create(ServicioEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de una entidad de Servicio");
        // Invoca la persistencia para crear la entidad de Queja
        //TODO: DONE No hay ninguna regla de negocio? 
        if(entity.getFinalizado())
        {
            throw new BusinessLogicException("No se puede crear un servicio que ya está finalizado.");
        }
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de entidad de Servicio");
        return entity;
    }

    public List<ServicioEntity> getAll() {
        LOGGER.info("Inicia proceso de consultar todas las entidades de Servicio");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<ServicioEntity> entities = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las entidades de Servicio");
        return entities;
    }

    public ServicioEntity getById(Long id) {
        return persistence.find(id);
    }

    public ServicioEntity update(ServicioEntity entity) throws BusinessLogicException {
        if (persistence.find(entity.getId()) == null) {
            throw new BusinessLogicException("No existe una entidad de Servicio con el id \"" + entity.getId() + "\"");
        }
        //TODO: DONE No hay ninguna regla de negocio? 
        
        //if(persistence.find(entity.getId()).getFinalizado()==true)
        //{
          //  throw new BusinessLogicException("No se puede actualizar un servicio que ya está finalizado.");
        //}
        return persistence.update(entity);
    }

    public void delete(ServicioEntity entity) throws BusinessLogicException {
        //TODO: DONE este método debe recibir un id y hay que validar que existe un ServicioEntity con ese id
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la entidad de Servicio con id={0}", entity.getId());
        if(persistence.find(entity.getId()) == null)
        {
           throw new BusinessLogicException("No existe una entidad de Servicio con el id \"" + entity.getId() + "\""); 
        }
        persistence.delete(entity.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar la entidad de Servicio con id={0}", entity.getId());
    }

}
