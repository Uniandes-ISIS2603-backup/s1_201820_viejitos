/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.ejb;

import co.edu.uniandes.csw.viejitos.entities.ClienteEntity;
import co.edu.uniandes.csw.viejitos.entities.HistoriaClinicaEntity;
import co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viejitos.persistence.ClientePersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jj.silva
 */
@Stateless
public class ClienteLogic {

    private static final Logger LOGGER = Logger.getLogger(ClienteLogic.class.getName());

    @Inject
    private ClientePersistence persistence;

    public ClienteEntity create(ClienteEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de una entidad de Cliente");
        // Verifica la regla de negocio que dice que no puede haber dos entidades de Cliente con el mismo login
        if (persistence.findByLogin(entity.getLogin()) != null) {
            throw new BusinessLogicException("Ya existe una entidad de Cliente con el login \"" + entity.getLogin() + "\"");
        }
        // Invoca la persistencia para crear la entidad de Cliente
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de entidad de Cliente");
        return entity;
    }

    public List<ClienteEntity> getAll() {
        LOGGER.info("Inicia proceso de consultar todas las entidades de Cliente");
        List<ClienteEntity> entities = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las entidades de Cliente");
        return entities;
    }

    public ClienteEntity getById(Long id) {
        return persistence.find(id);
    }

    public ClienteEntity update(ClienteEntity entity) throws BusinessLogicException {
        if (persistence.findByLogin(entity.getLogin()) != null) {
            throw new BusinessLogicException("Ya existe una entidad de Cliente con el login \"" + entity.getLogin() + "\"");
        }
        return persistence.update(entity);
    }

    public void delete(ClienteEntity entity) throws BusinessLogicException {
        //TODO: DONE
        if(persistence.find(entity.getId()) == null)
        {
           throw new BusinessLogicException("No existe una entidad de Cliente con el id \"" + entity.getId() + "\""); 
        }
        else
        {
            LOGGER.log(Level.INFO, "Inicia proceso de borrar la entidad de Cliente con id={0}", entity.getId());
            persistence.delete(entity.getId());
            LOGGER.log(Level.INFO, "Termina proceso de borrar la entidad de Cliente con id={0}", entity.getId());
        }
    }
    
     /**
     * Obtiene una instancia de HistoriaClinicaEntity asociada a una
     * instancia de Cliente
     *
     * @param clienteId Identificador de la instancia de Cliente
     * @return Instancia de HistoriaClinicaEntity asociada a la instancia
     * de Cliente
     */
    public HistoriaClinicaEntity getHistoriaC(Long clienteId)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la historia clinica del cliente con id = {0}", clienteId);
        return getById(clienteId).getHistoriaC();
    }
    
    /**
     * Asocia una HistoriaClinica existente a un Cliente
     *
     * @param clienteId Identificador de la instancia de Cliente
     * @param historiacId Identificador de la instancia de HistoriaClinica
     * @return Instancia de HistoriaClinicaEntity que fue asociada a Cliente
     * 
     */
    public HistoriaClinicaEntity addHistoriaC(Long clienteId, Long historiacId)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de asociar una historia clinica al cliente con id = {0}", clienteId);
        ClienteEntity clienteEntity = getById(clienteId);
        HistoriaClinicaEntity historiacEntity = new HistoriaClinicaEntity();
        historiacEntity.setId(historiacId);
        clienteEntity.setHistoriaC(historiacEntity);
        return getHistoriaC(clienteId);
    }
    
    /**
     * Remplaza la instancia de HistoriaClinica asociada a una instancia de Cliente
     * @param clienteId Identificador de la instancia de Cliente
     * @param historiac Instancia de HistoriaClinicaEntity a asociar a instancia
     * de Cliente
     * @return HistoriaClinicaEntity asociada a la instancia de Cliente
     * 
     */
    public HistoriaClinicaEntity replaceHistoriaC(Long clienteId, HistoriaClinicaEntity historiac)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar la historia clinica del cliente con id = {0}", clienteId);
        ClienteEntity clienteEntity = getById(clienteId);
        clienteEntity.setHistoriaC(historiac);
        return clienteEntity.getHistoriaC();
    }
}
