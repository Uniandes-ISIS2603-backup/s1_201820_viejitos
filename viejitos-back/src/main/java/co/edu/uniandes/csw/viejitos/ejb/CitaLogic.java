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
import java.util.Date;
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
    @Inject
    private MedicoPersistence medicoPersistence;
    @Inject
    private ClientePersistence clientePersistence;

    public CitaEntity create(CitaEntity entity) throws BusinessLogicException {
        
        LOGGER.info("Inicia proceso de creación de una entidad de Cita");
        //TODO: DONE
                Calendar today = Calendar.getInstance();
        today.set(Calendar.DAY_OF_MONTH, Calendar.WEEK_OF_YEAR,Calendar.YEAR);
        Date d = today.getTime();
        if (entity.getFecha().before(d)) {
            throw new BusinessLogicException("La fecha de la cita es antes de la fecha de hoy");
        }
        if(medicoPersistence.find(entity.getMedico().getId())==null)
        {
             throw new BusinessLogicException("El medico con el id " + entity.getId());
        }
        if(clientePersistence.find(entity.getCliente().getId())==null)
        {
             throw new BusinessLogicException("El cliente con el id " + entity.getId());
        }
        if(clientePersistence.find(entity.getCliente().getId()).getCita()!=null)
        {
            throw new BusinessLogicException("El cliente con el id " + entity.getId()+"ya tiene una cita de valoracion asignada");
        }
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
        LOGGER.info("Inicia proceso de consultar todas las entidades de Cita con id" + id);
        return persistence.find(id);
    }

    public CitaEntity update(CitaEntity entity) throws BusinessLogicException {

        if (persistence.find(entity.getId()) == null) {
            throw new BusinessLogicException("No existe una entidad de Cita con el id \"" + entity.getId() + "\"");
        }
        //TODO: DONE 
        
        Calendar today = Calendar.getInstance();
        today.set(Calendar.DAY_OF_MONTH, Calendar.WEEK_OF_YEAR,Calendar.YEAR);
        Date d = today.getTime();
        if (entity.getFecha().before(d)) {
            throw new BusinessLogicException("La fecha de la cita es antes de la fecha de hoy");
        }
        if(medicoPersistence.find(entity.getMedico().getId())==null)
        {
             throw new BusinessLogicException("El medico con el id " + entity.getId());
        }
        if(clientePersistence.find(entity.getCliente().getId())==null)
        {
             throw new BusinessLogicException("El cliente con el id " + entity.getId());
        }
        return persistence.update(entity);
    }

    public void delete(CitaEntity entity) throws BusinessLogicException {
        //TODO:DONE
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la entidad de Cita con id={0}", entity.getId());
        if (persistence.find(entity.getId()) == null) {
            throw new BusinessLogicException("No existe una entidad de Cita con el id \"" + entity.getId() + "\"");
        }
        persistence.delete(entity.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar la entidad de Cita con id={0}", entity.getId());
    }
}
