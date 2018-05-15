/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.ejb;

import co.edu.uniandes.csw.viejitos.entities.CalificacionEntity;
import co.edu.uniandes.csw.viejitos.entities.EnfermeroEntity;
import co.edu.uniandes.csw.viejitos.entities.ServicioEntity;
import co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viejitos.persistence.EnfermeroPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author js.espitia
 */
@Stateless
public class EnfermeroLogic {

    private static final Logger LOGGER = Logger.getLogger( EnfermeroLogic.class.getName( ) );

    @Inject
    private EnfermeroPersistence persistencia;
    
    @Inject
    private ServicioLogic servicioLogic;
    
    public EnfermeroEntity create(EnfermeroEntity entity) throws BusinessLogicException{
        if(entity!=null){
            LOGGER.info( "Inicia proceso de creación de una entidad de Enfermero" );
            // Verifica la regla de negocio que dice que no puede haber dos entidades de Viejitos con el mismo nombre
            if( persistencia.findByLogin(entity.getLogin()) != null ){
                throw new BusinessLogicException( "Ya existe una entidad de Enfermero con el login \"" + entity.getLogin( ) + "\"" );
            }
            // Invoca la persistencia para crear la entidad de Viejito
            persistencia.create( entity );
            LOGGER.info( "Termina proceso de creación de entidad de Enfermero" );
        }
	return entity;
    }
    
    public List<EnfermeroEntity> getAll(){
	LOGGER.info( "Inicia proceso de consultar todas las entidades de Enfermero" );
	// Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
	List<EnfermeroEntity> entities = persistencia.findAll( );
	LOGGER.info( "Termina proceso de consultar todas las entidades de Enfermero" );
	return entities;
    }
    
    public EnfermeroEntity getById( Long id ){
	return persistencia.find( id );
    }
    
    public EnfermeroEntity getByName(String name){
        return persistencia.findByName(name);
    }
    
    public EnfermeroEntity getByLogin( String login ){
        return persistencia.findByLogin(login);
    }

    public EnfermeroEntity update( EnfermeroEntity entity ) throws BusinessLogicException{
         if (persistencia.find(entity.getId()) == null) {
            throw new BusinessLogicException("No existe una entidad de Enfermero con el id \"" + entity.getId() + "\"");
        }
         return persistencia.update(entity);
    }

    public void delete( long id) {
        LOGGER.log( Level.INFO, "Inicia proceso de borrar la entidad de Enfermero con id={0}", id );
        
        persistencia.delete( id );
        LOGGER.log( Level.INFO, "Termina proceso de borrar la entidad de Enfermero con id={0}", id );
    }

    public CalificacionEntity addCalificacion(Long enfermeroId, CalificacionEntity toEntity) {
        EnfermeroEntity enfermero = getById(enfermeroId);
        if(enfermero!=null){
            List<CalificacionEntity> calificacionest = enfermero.getCalificaciones();
            calificacionest.add(toEntity);
            enfermero.setCalificaciones(calificacionest);
        }
        return toEntity;
    }
    
    /**
     * Obtiene una colección de instancias de ServicioEntity asociadas a una
     * instancia de Enfermero
     * @param enfermeroId Identificador de la instancia de Enfermero
     * @return Colección de instancias de ServicioEntity asociadas a la instancia de
     * Enfermero
     */
    public List<ServicioEntity> listServicios(Long enfermeroId) {
        return getById(enfermeroId).getServicios();
    }
    
     /**
     * Retorna un servicio asociado a un enfermero
     * @param enfermeroId El id del enfermero a buscar.
     * @param servicioId El id del servicio a buscar
     * @return El servicio encontrado dentro del enfermero.
     * @throws BusinessLogicException Si el servicio no se encuentra en el enfermero
     */
    public ServicioEntity getServicio(Long enfermeroId, Long servicioId) throws BusinessLogicException {
        List<ServicioEntity> servicios = getById(enfermeroId).getServicios();
        ServicioEntity servicio = servicioLogic.getById(servicioId);
        int index = servicios.indexOf(servicio);
        if (index >= 0) {
            return servicios.get(index);
        }
        throw new BusinessLogicException("El servicio no está asociado al enfermero");

    }
    
     /**
     * Agregar un servicio al enfermero
     * @paramservicioId El id del servicio a guardar
     * @param enfermeroId El id del enfermero en la cual se va a guardar el
     * servicio.
     * @return El servicio que fue agregado al enfermero.
     */
    public ServicioEntity addServicio(Long enfermeroId, Long servicioId) {
        EnfermeroEntity enfermeroEntity = getById(enfermeroId);
        ServicioEntity servicioEntity = servicioLogic.getById(servicioId);
        servicioEntity.setEnfermero(enfermeroEntity);
        return servicioEntity;
    }
    
     /**
     * Remplazar servicios de un enfermero
     * @param servicios Lista de servicios que serán los del enfermero.
     * @param enfermeroId El id del enfermero que se quiere actualizar.
     * @return La lista de servicios actualizada.
     */
    public List<ServicioEntity> replaceServicios(Long enfermeroId, List<ServicioEntity> servicios) {
        EnfermeroEntity enfermero = getById(enfermeroId);
        List<ServicioEntity> serviciosList = servicioLogic.getAll();
        for (ServicioEntity servicio : serviciosList) {
            if (servicios.contains(servicio)) {
                servicio.setEnfermero(enfermero);
            } else if (servicio.getEnfermero() != null && servicio.getEnfermero().equals(enfermero)) {
                servicio.setEnfermero(null);
            }
        }
        return servicios;
    }
    
     /**
     * Borrar un servicio de un enfermero
     * @param servicioId El servicio que se desea borrar del enfermero.
     * @param enfermeroId El enfermero del cual se desea eliminar.
     */
    public void removeServicio(Long servicioId, Long enfermeroId) {
        EnfermeroEntity enfermeroEntity = getById(enfermeroId);
        ServicioEntity servicio = servicioLogic.getById(servicioId);
        servicio.setEnfermero(null);
        enfermeroEntity.getServicios().remove(servicio);
    }
    
}