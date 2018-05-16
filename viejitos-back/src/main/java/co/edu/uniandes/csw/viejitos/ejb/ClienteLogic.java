/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.ejb;

import co.edu.uniandes.csw.viejitos.entities.CalificacionEntity;
import co.edu.uniandes.csw.viejitos.entities.ClienteEntity;
import co.edu.uniandes.csw.viejitos.entities.EnfermeroEntity;
import co.edu.uniandes.csw.viejitos.entities.HistoriaClinicaEntity;
import co.edu.uniandes.csw.viejitos.entities.ServicioEntity;
import co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viejitos.persistence.CalificacionPersistence;
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
    private CalificacionPersistence cPersistence;
    
    @Inject
    private CalificacionLogic cLogic;

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
    
    public List<CalificacionEntity> getCalificaciones(Long clienteId) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de consultar todas las entidades de Calificacion");
        ClienteEntity cliente = getById(clienteId);
        if (cliente.getCalificaciones() == null || cliente.getCalificaciones().isEmpty()) {
            throw new BusinessLogicException("El cliente que consulta aún no tiene calificaciones");
        }
        LOGGER.info("Termina proceso de consultar todas las entidades de Queja");
        return cliente.getCalificaciones();
    }
    
    public CalificacionEntity getCalificacionById(Long clienteId, Long calificacionId) throws BusinessLogicException {
        List<CalificacionEntity> calificaciones = getCalificaciones(clienteId);
        CalificacionEntity calificacion = cLogic.getById(calificacionId);
        int index = calificaciones.indexOf(calificacion);
        if (index >= 0) {
            return calificaciones.get(index);
        }
        throw new BusinessLogicException("La calificacion no está asociada al cliente");
    }
    
    public CalificacionEntity addCalificacion(Long clienteId, CalificacionEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de una entidad de Calificacion");  
        ClienteEntity cliente = getById(clienteId);
        cliente.getCalificaciones().add(entity);
        LOGGER.info("Termina proceso de creación de entidad de Calificacion");
        return cPersistence.create(entity);
    } 
    
    public void removeCalificacion(Long clienteId, Long id) {
        ClienteEntity cliente = getById(clienteId);
        CalificacionEntity calificacion = cLogic.getById(id);
        cliente.getCalificaciones().remove(calificacion);
        cPersistence.delete(calificacion);
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
