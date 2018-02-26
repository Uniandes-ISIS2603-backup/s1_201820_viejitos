/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.ejb;

import co.edu.uniandes.csw.viejitos.entities.QuejaEntity;
import co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viejitos.persistence.QuejaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author c.gomezs
 */

@Stateless
public class QuejaLogic {
    
    private static final Logger LOGGER = Logger.getLogger( QuejaLogic.class.getName( ) );

	@Inject
	private QuejaPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

	public QuejaEntity create( QuejaEntity entity ) throws BusinessLogicException
	{
		LOGGER.info( "Inicia proceso de creación de una entidad de Queja" );
		// Invoca la persistencia para crear la entidad de Queja
		persistence.create( entity );
		LOGGER.info( "Termina proceso de creación de entidad de Queja" );
		return entity;
	}

	public List<QuejaEntity> getAll( )
	{
		LOGGER.info( "Inicia proceso de consultar todas las entidades de Queja" );
		// Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
		List<QuejaEntity> entities = persistence.findAll( );
		LOGGER.info( "Termina proceso de consultar todas las entidades de Queja" );
		return entities;
	}

	public QuejaEntity getById( Long id )
	{
		return persistence.find( id );
	}

	public QuejaEntity update( QuejaEntity entity ) throws BusinessLogicException
	{
		return persistence.update( entity );
	}

	public void delete( QuejaEntity entity ) throws BusinessLogicException
	{
		LOGGER.log( Level.INFO, "Inicia proceso de borrar la entidad de Queja con id={0}", entity.getId( ) );
		persistence.delete( entity.getId() );
		LOGGER.log( Level.INFO, "Termina proceso de borrar la entidad de Queja con id={0}", entity.getId( ) );
	}
    
}