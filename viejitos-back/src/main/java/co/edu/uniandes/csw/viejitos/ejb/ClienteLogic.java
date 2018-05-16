/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.ejb;

import co.edu.uniandes.csw.viejitos.entities.CitaEntity;
import co.edu.uniandes.csw.viejitos.entities.ClienteEntity;
import co.edu.uniandes.csw.viejitos.entities.EnfermeroEntity;
import co.edu.uniandes.csw.viejitos.entities.HistoriaClinicaEntity;
import co.edu.uniandes.csw.viejitos.entities.ServicioEntity;
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
    
    @Inject
    private ServicioLogic servicioLogic;
    
    @Inject
    private CitaLogic citaLogic;

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
    
     /**
     * Obtiene una colección de instancias de ServicioEntity asociadas a una
     * instancia de Cliente
     * @param clienteId Identificador de la instancia de Cliente
     * @return Colección de instancias de ServicioEntity asociadas a la instancia de
     * Cliente
     */
    public List<ServicioEntity> listServicios(Long clienteId) {
        return getById(clienteId).getServicios();
    }
    
     /**
     * Retorna un servicio asociado a un cliente
     * @param clienteId El id del cliente a buscar.
     * @param servicioId El id del servicio a buscar
     * @return El servicio encontrado dentro del cliente.
     * @throws BusinessLogicException Si el servicio no se encuentra en el cliente
     */
    public ServicioEntity getServicio(Long clienteId, Long servicioId) throws BusinessLogicException {
        List<ServicioEntity> servicios = getById(clienteId).getServicios();
        ServicioEntity servicio = servicioLogic.getById(servicioId);
        int index = servicios.indexOf(servicio);
        if (index >= 0) {
            return servicios.get(index);
        }
        throw new BusinessLogicException("El servicio no está asociado al cliente");

    }
    
     /**
     * Agregar un servicio al cliente
     * @paramservicioId El id del servicio a guardar
     * @param clienteId El id del cliente en la cual se va a guardar el
     * servicio.
     * @return El servicio que fue agregado al cliente.
     */
    public ServicioEntity addServicio(Long clienteId, Long servicioId) {
        ClienteEntity clienteEntity = getById(clienteId);
        ServicioEntity servicioEntity = servicioLogic.getById(servicioId);
        servicioEntity.setCliente(clienteEntity);
        return servicioEntity;
    }
    
     /**
     * Remplazar servicios de un cliente
     * @param servicios Lista de servicios que serán los del cliente.
     * @param enfermeroId El id del cliente que se quiere actualizar.
     * @return La lista de servicios actualizada.
     */
    public List<ServicioEntity> replaceServicios(Long clienteId, List<ServicioEntity> servicios) {
        ClienteEntity cliente = getById(clienteId);
        List<ServicioEntity> serviciosList = servicioLogic.getAll();
        for (ServicioEntity servicio : serviciosList) {
            if (servicios.contains(servicio)) {
                servicio.setCliente(cliente);
            } else if (servicio.getCliente() != null && servicio.getCliente().equals(cliente)) {
                servicio.setCliente(null);
            }
        }
        return servicios;
    }
    
     /**
     * Borrar un servicio de un cliente
     * @param servicioId El servicio que se desea borrar del cliente.
     * @param clienteId El cliente del cual se desea eliminar.
     */
    public void removeServicio(Long servicioId, Long clienteId) {
        ClienteEntity clienteEntity = getById(clienteId);
        ServicioEntity servicio = servicioLogic.getById(servicioId);
        servicio.setCliente(null);
        clienteEntity.getServicios().remove(servicio);
    }
    
    /**
     * Obtiene una colección de instancias de CitaEntity asociadas a una
     * instancia de Cliente
     * @param clienteId Identificador de la instancia de Cliente
     * @return Colección de instancias de ServicioEntity asociadas a la instancia de
     * Cliente
     */
    public CitaEntity listCitas(Long clienteId) {
        return getById(clienteId).getCita();
    }
    
     /**
     * Retorna una cita asociado a un cliente
     * @param clienteId El id del cliente a buscar.
     * @param citaId El id de la cita a buscar
     * @return La cita encontrada dentro del cliente.
     * @throws BusinessLogicException Si la cita no se encuentra en el cliente
     */
    public CitaEntity getCita(Long clienteId, Long citaId) throws BusinessLogicException {
        CitaEntity cita1 = getById(clienteId).getCita();
        CitaEntity cita = citaLogic.getById(citaId);
        
        if(cita1 == cita )
        {
            return cita1;
        }
        
        throw new BusinessLogicException("El servicio no está asociado al cliente");

    }
    
     /**
     * Agregar una cita al cliente
     * @param citaId
     * @paramcitaId El id de la cita a guardar
     * @param clienteId El id del cliente en la cual se va a guardar el
     * cita.
     * @return La cita que fue agregada al cliente.
     */
    public CitaEntity addCita(Long clienteId, Long citaId) {
        ClienteEntity clienteEntity = getById(clienteId);
        CitaEntity citaEntity = citaLogic.getById(citaId);
        citaEntity.setCliente(clienteEntity);
        return citaEntity;
    }
    
     /**
     * Remplazar cita de un cliente
     * @param cita cita que será la del cliente.
     * @param clienteId El id del cliente que se quiere actualizar.
     * @return La cita actualizada.
     */
    public CitaEntity replaceCita(Long clienteId,CitaEntity cita) throws BusinessLogicException {
        ClienteEntity cliente = getById(clienteId);
        List<CitaEntity> citasList = citaLogic.getAll();
        for (CitaEntity citaActual : citasList) {
            if(citaActual == cita )
            {
                citaActual.setCliente(cliente);
                cliente.setCita(citaActual);
                update(cliente);
            }
            else if (citaActual.getCliente() != null && citaActual.getCliente().equals(cliente)) {
                citaActual.setCliente(null);
            }
        }
        return cita;
    }
    
     /**
     * Borrar una cita de un cliente
     * @param citaId El servicio que se desea borrar del cliente.
     * @param clienteId El cliente del cual se desea eliminar.
     */
    public void removeCita(Long clienteId, Long citaId) {
        ClienteEntity clienteEntity = getById(clienteId);
        CitaEntity cita = citaLogic.getById(citaId);
        cita.setCliente(null);
        clienteEntity.setCita(null);
    }
}
