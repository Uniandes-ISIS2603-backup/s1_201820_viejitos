/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.ejb;

import co.edu.uniandes.csw.viejitos.entities.CitaEntity;
import co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viejitos.persistence.CalendarioSemanalPersistence;
import co.edu.uniandes.csw.viejitos.persistence.CitaPersistence;
import co.edu.uniandes.csw.viejitos.persistence.ClientePersistence;
import co.edu.uniandes.csw.viejitos.persistence.MedicoPersistence;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author l.pardo
 */
@Stateless
public class CitaLogic {
    private static final Logger LOGGER = Logger.getLogger(CitaLogic.class.getName());

    @Inject
    private CitaPersistence persistence;

    public CitaEntity create(CitaEntity entity) throws BusinessLogicException {
        //verificar
        LOGGER.info("Inicia proceso de creación de una entidad de Cita");
        //TODO: No hay ninguna regla de negocio? 
        persistence.create(entity);
        return entity;
    }

    public List<CitaEntity> getAll() {
        LOGGER.info("Inicia proceso de consultar todas las entidades de Cita");
        List<CitaEntity> entities = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las entidades de Cita");
        return entities;
    }

    public CitaEntity getById(Long id) {
         LOGGER.info("Inicia proceso de consultar todas las entidades de Cita con id"+id);
        return persistence.find(id);
    }

    public CitaEntity update(CitaEntity entity) throws BusinessLogicException {
        
        if (persistence.find(entity.getId()) == null) {
            throw new BusinessLogicException("No existe una entidad de Cita con el id \"" + entity.getId() + "\"");
        }
        //TODO: No hay ninguna regla de negocio? 
        return persistence.update(entity);
    }

    public void delete(CitaEntity entity) throws BusinessLogicException {
        //TODO:DONE
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la entidad de Cita con id={0}", entity.getId());
        if(persistence.find(entity.getId())==null)
        {
             throw new BusinessLogicException("No existe una entidad de Cita con el id \"" + entity.getId() + "\"");
        }
        persistence.delete(entity.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar la entidad de Cita con id={0}", entity.getId());
    }
}
