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
import co.edu.uniandes.csw.viejitos.persistence.HistoriaClinicaPersistence;
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
public class HistoriaClinicaLogic {

    private static final Logger LOGGER = Logger.getLogger(HistoriaClinicaLogic.class.getName());

    @Inject
    private HistoriaClinicaPersistence persistence;
    
    @Inject
    private ClienteLogic clienteLogic;

    //TODO: DONE

    public HistoriaClinicaEntity create(Long clienteId, HistoriaClinicaEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de una entidad de HistoriaClinica");
        // Invoca la persistencia para crear la entidad de HistoriaClinica  
        ClienteEntity cliente = clienteLogic.getById(clienteId);
        if(!cliente.getHistoriaC().isEmpty())
        {
            throw new BusinessLogicException("El cliente ya tiene asociada una HistoriaClinica");
        }
        entity.setCliente(cliente);
        LOGGER.info("Termina proceso de creación de entidad de Historia Clinica");
        return persistence.create(entity);
    }

    public HistoriaClinicaEntity get(Long clienteId) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de consultar la entidad de HistoriaClinica asociada al Cliente con clienteId");
        ClienteEntity cliente = clienteLogic.getById(clienteId);
        if (cliente.getHistoriaC() == null || cliente.getHistoriaC().isEmpty())
        {
            throw new BusinessLogicException("El cliente no tiene asociada una HistoriaClinica");
        }
        LOGGER.info("Termina proceso de consultar la HistoriaClinica del cliente");
        return cliente.getHistoriaC().get(0);
    }

    public HistoriaClinicaEntity update(Long clienteId, HistoriaClinicaEntity entity) throws BusinessLogicException {
    //TODO: DONE. No hay ninguna regla de negocio 
        LOGGER.info("Inicia proceso de actualizar HistoriaClinica");
        ClienteEntity cliente = clienteLogic.getById(clienteId);
        entity.setCliente(cliente);
        if (cliente.getHistoriaC() == null || cliente.getHistoriaC().isEmpty()) {
            throw new BusinessLogicException("El cliente no tiene una HistoriaClinica que actualizar");
        }
        return persistence.update(entity);
    }

    public void delete(Long clienteId) throws BusinessLogicException
    {
        //TODO: DONE
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la entidad de HistoriaClinica del Cliente con id={0}", clienteId);
        HistoriaClinicaEntity entity = get(clienteId);
        if(entity == null)
        {
           throw new BusinessLogicException("No existe una entidad de HistoriaClinica asociada al Cliente con el id \"" + clienteId + "\""); 
        }
        persistence.delete(entity.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar la entidad de HistoriaClinica del Cliente con id={0}", clienteId);
    }
}
