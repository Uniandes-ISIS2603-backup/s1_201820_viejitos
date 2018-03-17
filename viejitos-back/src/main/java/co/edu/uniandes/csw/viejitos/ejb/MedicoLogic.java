/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.ejb;

import co.edu.uniandes.csw.viejitos.entities.MedicoEntity;
import co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viejitos.persistence.MedicoPersistence;
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
public class MedicoLogic {

    private static final Logger LOGGER = Logger.getLogger(MedicoLogic.class.getName());

    @Inject
    private MedicoPersistence persistence;

    public MedicoEntity create(MedicoEntity entity) throws BusinessLogicException {
        //verificar
        LOGGER.info("Inicia proceso de creación de una entidad de Medico");
        // Verifica la regla de negocio que dice que no puede haber dos entidades de Cliente con el mismo login
        // Invoca la persistencia para crear la entidad de Cliente
        //TODO: No hay ninguna regla de negocio? 
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de entidad de Medico");
        return entity;
    }

    public List<MedicoEntity> getAll() {
        LOGGER.info("Inicia proceso de consultar todas las entidades de Medico");
        List<MedicoEntity> entities = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las entidades de Medico");
        return entities;
    }

    public MedicoEntity getById(Long id) {
        return persistence.find(id);
    }

    public MedicoEntity update(MedicoEntity entity) throws BusinessLogicException {
        if (persistence.find(entity.getId()) == null) {
            throw new BusinessLogicException("No existe una entidad de Medico con el id \"" + entity.getId() + "\"");
        }
        //TODO: No hay ninguna regla de negocio? 
        return persistence.update(entity);
    }

    public void delete(MedicoEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la entidad de Medico con id={0}", entity.getId());
        //TODO: este método debe recibir un id y hay que validar que existe un MedicoEntity con ese id
        persistence.delete(entity.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar la entidad de Medico con id={0}", entity.getId());
    }
}
