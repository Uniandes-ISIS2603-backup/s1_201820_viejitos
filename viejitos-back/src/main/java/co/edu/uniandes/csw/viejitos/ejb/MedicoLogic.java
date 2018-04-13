/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.ejb;

import co.edu.uniandes.csw.viejitos.entities.CitaEntity;
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
        //TODO: DONE

        if (persistence.findByLogin(entity.getLogin()) != null) {
            throw new BusinessLogicException("Ya existe un médico con el login " + entity.getLogin());
        }
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

    public MedicoEntity getByLogin(String login) {
        return persistence.findByLogin(login);
    }

    public MedicoEntity getByName(String name) {
        return persistence.findByName(name);
    }

    public MedicoEntity update(MedicoEntity entity) throws BusinessLogicException {
        if (persistence.find(entity.getId()) == null) {
            throw new BusinessLogicException("No existe una entidad de Medico con el id \"" + entity.getId() + "\"");
        }
        if(persistence.findByLogin(entity.getLogin())==null)
        {
             throw new BusinessLogicException("El login no puede ser cambiado");
        }
        //TODO: DONE 
        return persistence.update(entity);
    }

    public void delete(MedicoEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la entidad de Medico con id={0}", entity.getId());
        //TODO: DONE
        if (persistence.find(entity.getId()) == null) {
            throw new BusinessLogicException("No existe una entidad de Medico con el id \"" + entity.getId() + "\"");
        }
        persistence.delete(entity.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar la entidad de Medico con id={0}", entity.getId());
    }
    
        /**
     * Obtiene una colección de instancias de CitaEntity asociadas a una
     * instancia de Medico
     *
     * @param medicoId Identificador de la instancia de Medico
     * @return Colección de instancias de CitaEntity asociadas a la instancia
     * de Medico
     * 
     */
    public List<CitaEntity> listCitas(Long medicoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los autores del libro con id = {0}", medicoId);
        return getById(medicoId).getCitas();
    }

    /**
     * Obtiene una instancia de CitaEntity asociada a una instancia de Medico
     *
     * @param medicoId Identificador de la instancia de Medico
     * @param citaId Identificador de la instancia de Cita
     * @return La entidad del Cita asociada al medico
     */
    public CitaEntity getCita(Long medicoId, Long citaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar una cita del medico con id = {0}", medicoId);
        List<CitaEntity> list = getById(medicoId).getCitas();
        CitaEntity citaEntity = new CitaEntity();
        citaEntity.setId(citaId);
        int index = list.indexOf(citaEntity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }

    /**
     * Asocia una Cita existente a un Medico
     *
     * @param medicoId Identificador de la instancia de Book
     * @param citaId Identificador de la instancia de Author
     * @return Instancia de AuthorEntity que fue asociada a Book
     * 
     */
    public CitaEntity addCita(Long medicoId, Long citaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociar una cita al medico con id = {0}", medicoId);
        MedicoEntity medicoEntity = getById(medicoId);
        CitaEntity citaEntity = new CitaEntity();
        citaEntity.setId(citaId);
        medicoEntity.getCitas().add(citaEntity);
        return getCita( medicoId,  citaId);
    }

    /**
     * Remplaza las instancias de Citas asociadas a una instancia de Medico
     *
     * @param medicoId Identificador de la instancia de Book
     * @param list Colección de instancias de CitaEntity a asociar a instancia
     * de Medico
     * @return Nueva colección de CitaEntity asociada a la instancia de Book
     * 
     */
    public List<CitaEntity> replaceCitas(Long medicoId, List<CitaEntity> list) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar un autor del libro con id = {0}", medicoId);
        MedicoEntity medicoEntity = getById(medicoId);
        medicoEntity.setCitas(list);
        return medicoEntity.getCitas();
    }

    /**
     * Desasocia un Author existente de un Book existente
     *
     * @param medicoId Identificador de la instancia de Book
     * @param citaId Identificador de la instancia de Author
     * 
     */
    public void removeCita(Long medicoId, Long citaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un autor del libro con id = {0}", medicoId);
        MedicoEntity entity = getById(medicoId);
        CitaEntity citaEntity = new CitaEntity();
        citaEntity.setId(citaId);
        entity.getCitas().remove(citaEntity);
    }
}
