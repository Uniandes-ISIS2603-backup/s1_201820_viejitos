/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.ejb;

import co.edu.uniandes.csw.viejitos.entities.FacturaEntity;
import co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viejitos.persistence.FacturaPersistence;
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
public class FacturaLogic {
    
    private static final Logger LOGGER = Logger.getLogger( FacturaLogic.class.getName( ) );
    
    @Inject
    private FacturaPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    //TODO: Esta variable no se usa
    @Inject
    private ServicioPersistence persistenceServicio;
    
    public FacturaEntity create( FacturaEntity entity ) throws BusinessLogicException
	{
		LOGGER.info( "Inicia proceso de creación de una entidad de Factura" );
		// Invoca la persistencia para crear la entidad de Factura
                // Verifica las reglas de negocio
		
                /**if( entity.getServicio() == null )
		{
			throw new BusinessLogicException( "La entidad de Factura debe tener un servicio asociado" );
		}
                if( persistenceServicio.find(entity.getServicio().getId()) == null )
		{
			throw new BusinessLogicException( "El Servicio de la Factura no es válido" );
		}*/
                if( entity.getCostoTotal() < 0 )
                {
                        throw new BusinessLogicException( "La entidad de Factura no puede tener un costo total negativo" );
                }
                
		persistence.create( entity );
		LOGGER.info( "Termina proceso de creación de entidad de Factura" );
		return entity;
	}

	public List<FacturaEntity> getAll( )
	{
		LOGGER.info( "Inicia proceso de consultar todas las entidades de Factura" );
		List<FacturaEntity> entities = persistence.findAll( );
		LOGGER.info( "Termina proceso de consultar todas las entidades de Factura" );
		return entities;
	}

	public FacturaEntity getById( Long id )
	{
		return persistence.find( id );
	}

	public FacturaEntity update( FacturaEntity entity ) throws BusinessLogicException
	{
            //Reglas de negocio
                if( persistence.find( entity.getId()) == null )
                {
                        throw new BusinessLogicException( "Para actualizar, debe existir una entidad de Factura con el id \"" + entity.getId( ) + "\"" );
                }
                /**if( entity.getServicio() == null )
		{
			throw new BusinessLogicException( "La entidad de Factura debe tener un servicio asociado" );
		}
                if( persistenceServicio.find(entity.getServicio().getId()) == null )
		{
			throw new BusinessLogicException( "El Servicio de la Factura no es válido" );
		}*/
                if( entity.getCostoTotal() < 0 )
                {
                        throw new BusinessLogicException( "La entidad de Factura no puede tener un costo total negativo" );
                }
                
		return persistence.update( entity );
	}

	public void delete( FacturaEntity entity )
	{
	//TODO: este método debe recibir un id y hay que validar que existe un FacturaEntity con ese id
            LOGGER.log( Level.INFO, "Inicia proceso de borrar la entidad de Factura con id={0}", entity.getId( ) );
                
		persistence.delete( entity.getId() );
		LOGGER.log( Level.INFO, "Termina proceso de borrar la entidad de Factura con id={0}", entity.getId( ) );
	}
    
}
