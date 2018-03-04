/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.ejb;

import co.edu.uniandes.csw.viejitos.entities.PagoEntity;
import co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viejitos.persistence.PagoPersistence;
import co.edu.uniandes.csw.viejitos.persistence.ServicioPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author f.escobar
 */
@Stateless
public class PagoLogic {
    
    private static final Logger LOGGER = Logger.getLogger( PagoLogic.class.getName( ) );
    
    @Inject
    private PagoPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    @Inject
    private ServicioPersistence persistenceServicio;
    
    public PagoEntity create( PagoEntity entity ) throws BusinessLogicException
	{
		LOGGER.info( "Inicia proceso de creación de una entidad de Pago" );
                // Verifica las reglas de negocio
		if( persistence.find( entity.getId()) != null )
                {
                        throw new BusinessLogicException( "Ya existe una entidad de Pago con el id \"" + entity.getId( ) + "\"" );
                }
                if( entity.getServicio() == null )
		{
			throw new BusinessLogicException( "La entidad de Pago debe tener un servicio asociado" );
		}
                if( persistenceServicio.find(entity.getServicio().getId()) == null )
		{
			throw new BusinessLogicException( "El Servicio del Pago no es válido" );
		}
                if( persistenceServicio.find(entity.getId()).getFactura() == null )
		{
			throw new BusinessLogicException( "El servicio asociado debe tener una factura válida asociada." );
		}
                if( entity.getValor() < 0 )
                {
                        throw new BusinessLogicException( "La entidad de Pago no puede tener un valor negativo" );
                }
                
		// Invoca la persistencia para crear la entidad de Queja
		persistence.create( entity );
		LOGGER.info( "Termina proceso de creación de entidad de Pago" );
		return entity;
	}

	public List<PagoEntity> getAll( )
	{
		LOGGER.info( "Inicia proceso de consultar todas las entidades de Pago" );
		// Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
		List<PagoEntity> entities = persistence.findAll( );
		LOGGER.info( "Termina proceso de consultar todas las entidades de Pago" );
		return entities;
	}

	public PagoEntity getById( Long id )
	{
		return persistence.find( id );
	}

	public PagoEntity update( PagoEntity entity ) throws BusinessLogicException
	{
                // Verifica las reglas de negocio
		if( persistence.find( entity.getId()) == null )
                {
                        throw new BusinessLogicException( "Para actualizar, debe existir una entidad de Pago con el id \"" + entity.getId( ) + "\"" );
                }
                if( entity.getServicio() == null )
		{
			throw new BusinessLogicException( "La entidad de Pago debe tener un servicio asociado" );
		}
                if( persistenceServicio.find(entity.getServicio().getId()) == null )
		{
			throw new BusinessLogicException( "El Servicio del Pago no es válido" );
                        
		}
                if( persistenceServicio.find(entity.getId()).getFactura() == null )
		{
			throw new BusinessLogicException( "El servicio asociado debe tener una factura válida asociada." );
		}
                if( entity.getValor() < 0 )
                {
                        throw new BusinessLogicException( "La entidad de Pago no puede tener un valor negativo" );
                }
            
		return persistence.update( entity );
	}

	public void delete( PagoEntity entity ) throws BusinessLogicException
	{
		LOGGER.log( Level.INFO, "Inicia proceso de borrar la entidad de Pago con id={0}", entity.getId( ) );
		persistence.delete( entity.getId() );
		LOGGER.log( Level.INFO, "Termina proceso de borrar la entidad de Pago con id={0}", entity.getId( ) );
	}
}
