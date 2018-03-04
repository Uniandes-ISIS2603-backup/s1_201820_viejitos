/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.ejb;

import co.edu.uniandes.csw.viejitos.entities.CalificacionEntity;
import co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viejitos.persistence.CalificacionPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
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
    
    public CalificacionEntity create(CalificacionEntity entity) throws BusinessLogicException{
        LOGGER.info( "Inicia proceso de creación de una entidad de Calificacion" );
	// Verifica la regla de negocio que dice que no puede haber dos entidades de Viejitos con el mismo nombre
	if( persistencia.find( entity.getId()) != null ){
            throw new BusinessLogicException( "Ya existe una entidad de Calificacion con el id \"" + entity.getId( ) + "\"" );
	}
	// Invoca la persistencia para crear la entidad de Viejito
	persistencia.create( entity );
	LOGGER.info( "Termina proceso de creación de entidad de Calificacion" );
	return entity;
    }
    
    public List<CalificacionEntity> getAll(){
	LOGGER.info( "Inicia proceso de consultar todas las entidades de Calificaiones" );
	// Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
	List<CalificacionEntity> entities = persistencia.findAll( );
	LOGGER.info( "Termina proceso de consultar todas las entidades de Calidicaciones" );
	return entities;
    }
    
   public CalificacionEntity getById( Long id ){
	return persistencia.find( id );
    }

    public CalificacionEntity update( CalificacionEntity entity ) throws BusinessLogicException{
        if( persistencia.find( entity.getId( ) ) == null ){
            throw new BusinessLogicException( "No existe una entidad de Calificacion con el ID \"" + entity.getId( ) + "\"" );
	}
	return persistencia.update( entity );
    }

    public void delete( CalificacionEntity entity ) throws BusinessLogicException {
        LOGGER.log( Level.INFO, "Inicia proceso de borrar la entidad de Calificacion con id={0}", entity.getId() );
        persistencia.delete( entity.getId() );
        LOGGER.log( Level.INFO, "Termina proceso de borrar la entidad de Calificacion con id={0}", entity.getId() );
    }
}
