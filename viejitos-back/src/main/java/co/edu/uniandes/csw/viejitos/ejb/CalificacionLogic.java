/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.ejb;

import co.edu.uniandes.csw.viejitos.entities.CalificacionEntity;
import co.edu.uniandes.csw.viejitos.entities.EnfermeroEntity;
import co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viejitos.persistence.CalificacionPersistence;
import co.edu.uniandes.csw.viejitos.persistence.EnfermeroPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ejb.Stateless;

/**
 *
 * @author js.espitia
 */
@Stateless
public class CalificacionLogic {
    
    private static final Logger LOGGER = Logger.getLogger( EnfermeroLogic.class.getName( ) );

    @Inject
    private CalificacionPersistence persistencia;
    
    @Inject 
    private EnfermeroPersistence persistenciaEnf;
    
    public CalificacionEntity create(CalificacionEntity entity) throws BusinessLogicException{
        if(entity!=null){
            LOGGER.info( "Inicia proceso de creación de una entidad de Calificacion" );

            // Invoca la persistencia para crear la entidad de Calificacion
            //TODO: No hay ninguna regla de negocio? 
            if(entity.getLoginCalificado().equals(entity.getLoginCalificador())||entity.getTipoCalificado().equals(entity.getTipoCalificador()))
                throw new BusinessLogicException("El calificador debe ser diferente al calificado");
            persistencia.create( entity );

            LOGGER.info( "Termina proceso de creación de entidad de Calificacion" );
        }
        return entity;
    }
    
    public List<CalificacionEntity> getAll(){
	LOGGER.info( "Inicia proceso de consultar todas las entidades de Calificaiones" );
	// Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
	List<CalificacionEntity> entities = persistencia.findAll( );
	LOGGER.info( "Termina proceso de consultar todas las entidades de Calidicaciones" );
	return entities;
    }
    
    public List<CalificacionEntity> getAllForEnf( Long idEnfermero){
       return persistencia.findForTarget(persistenciaEnf.find(idEnfermero).getLogin());
    }
    
   public CalificacionEntity getById( Long id ){
	return persistencia.find( id );
    }

    public CalificacionEntity update( CalificacionEntity entity ) throws BusinessLogicException{
        
        //TODO: No hay ninguna regla de negocio? 
        if(entity.getLoginCalificado().equals(entity.getLoginCalificador())||entity.getTipoCalificado().equals(entity.getTipoCalificador()))
            throw new BusinessLogicException("El calificador debe ser diferente al calificado");
        
	return persistencia.update( entity );
    }

    public void delete( Long id ) {
        if(persistencia.find(id)!=null){
            LOGGER.log( Level.INFO, "Inicia proceso de borrar la entidad de Calificacion con id={0}", id );
        
            // TODO:DONE Hay que validar que existe CalificacionEntity con ese id

            persistencia.delete( id );
            LOGGER.log( Level.INFO, "Termina proceso de borrar la entidad de Calificacion con id={0}", id );
        }
    }
    
}
