/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.ejb;

import co.edu.uniandes.csw.viejitos.entities.ServicioEntity;
import co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viejitos.persistence.ClientePersistence;
import co.edu.uniandes.csw.viejitos.persistence.ServicioPersistence;
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
public class ServicioLogic {
    
    private static final Logger LOGGER = Logger.getLogger( QuejaLogic.class.getName( ) );

	@Inject
	private ServicioPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

        @Inject
        private ClientePersistence clientePersistence;
        
	public ServicioEntity create( ServicioEntity entity ) throws BusinessLogicException
	{
		LOGGER.info( "Inicia proceso de creación de una entidad de Servicio" );
                // Verifica la regla de negocio que un servicio debe tener un cliente existente asociado.
		if( clientePersistence.findByLogin(entity.getCliente().getLogin())==null )
		{
			throw new BusinessLogicException( "No existe un cliente con el login \"" + entity.getCliente().getLogin()+ "\"" );
		}
		// Invoca la persistencia para crear la entidad de Queja
		persistence.create( entity );
		LOGGER.info( "Termina proceso de creación de entidad de Servicio" );
		return entity;
	}

	public List<ServicioEntity> getAll( )
	{
		LOGGER.info( "Inicia proceso de consultar todas las entidades de Servicio" );
		// Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
		List<ServicioEntity> entities = persistence.findAll( );
		LOGGER.info( "Termina proceso de consultar todas las entidades de Servicio" );
		return entities;
	}

	public ServicioEntity getById( Long id )
	{
		return persistence.find( id );
	}

	public ServicioEntity update( ServicioEntity entity ) throws BusinessLogicException
	{
		return persistence.update( entity );
	}

	public void delete( ServicioEntity entity ) throws BusinessLogicException
	{
		LOGGER.log( Level.INFO, "Inicia proceso de borrar la entidad de Servicio con id={0}", entity.getId( ) );
		persistence.delete( entity.getId() );
		LOGGER.log( Level.INFO, "Termina proceso de borrar la entidad de Servicio con id={0}", entity.getId( ) );
	}
    
}
