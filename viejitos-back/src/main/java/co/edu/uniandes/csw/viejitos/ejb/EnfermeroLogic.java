/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.ejb;

import co.edu.uniandes.csw.viejitos.entities.EnfermeroEntity;
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
    
    public EnfermeroEntity create(EnfermeroEntity entity) throws BusinessLogicException{
        LOGGER.info( "Inicia proceso de creación de una entidad de Enfermero" );
	// Verifica la regla de negocio que dice que no puede haber dos entidades de Viejitos con el mismo nombre
	if( persistencia.findByName( entity.getName( ) ) != null ){
            throw new BusinessLogicException( "Ya existe una entidad de Enfermero con el nombre \"" + entity.getName( ) + "\"" );
	}
	// Invoca la persistencia para crear la entidad de Viejito
	persistencia.create( entity );
	LOGGER.info( "Termina proceso de creación de entidad de Enfermero" );
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

    public EnfermeroEntity update( EnfermeroEntity entity ) throws BusinessLogicException{
        if( persistencia.findByName( entity.getName( ) ) != null ){
            throw new BusinessLogicException( "Ya existe una entidad de Enfermero con el nombre \"" + entity.getName( ) + "\"" );
	}
	return persistencia.update( entity );
    }

    public void delete( EnfermeroEntity entity ) throws BusinessLogicException {
        LOGGER.log( Level.INFO, "Inicia proceso de borrar la entidad de Enfermero con id={0}", entity.getId( ) );
        persistencia.delete( entity );
        LOGGER.log( Level.INFO, "Termina proceso de borrar la entidad de Enfermero con id={0}", entity.getId( ) );
    }
}
