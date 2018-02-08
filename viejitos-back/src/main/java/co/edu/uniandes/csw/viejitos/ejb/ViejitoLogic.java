/*
MIT License

Copyright (c) 2017 ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.csw.viejitos.ejb;

import co.edu.uniandes.csw.viejitos.entities.ViejitoEntity;
import co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viejitos.persistence.ViejitoPersistence;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author ISIS2603
 */
@Stateless
public class ViejitoLogic
{

	private static final Logger LOGGER = Logger.getLogger( ViejitoLogic.class.getName( ) );

	@Inject
	private ViejitoPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

	public ViejitoEntity create( ViejitoEntity entity ) throws BusinessLogicException
	{
		LOGGER.info( "Inicia proceso de creación de una entidad de Viejito" );
		// Verifica la regla de negocio que dice que no puede haber dos entidades de Viejitos con el mismo nombre
		if( persistence.findByName( entity.getName( ) ) != null )
		{
			throw new BusinessLogicException( "Ya existe una entidad de Viejito con el nombre \"" + entity.getName( ) + "\"" );
		}
		// Invoca la persistencia para crear la entidad de Viejito
		persistence.create( entity );
		LOGGER.info( "Termina proceso de creación de entidad de Viejito" );
		return entity;
	}

	public List<ViejitoEntity> getAll( )
	{
		LOGGER.info( "Inicia proceso de consultar todas las entidades de Viejito" );
		// Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
		List<ViejitoEntity> entities = persistence.findAll( );
		LOGGER.info( "Termina proceso de consultar todas las entidades de Viejito" );
		return entities;
	}

	public ViejitoEntity getById( Long id )
	{
		return persistence.find( id );
	}

	public ViejitoEntity update( ViejitoEntity entity ) throws BusinessLogicException
	{
		if( persistence.findByName( entity.getName( ) ) != null )
		{
			throw new BusinessLogicException( "Ya existe una entidad de Viejito con el nombre \"" + entity.getName( ) + "\"" );
		}
		return persistence.update( entity );
	}

	public void delete( ViejitoEntity entity ) throws BusinessLogicException
	{
		LOGGER.log( Level.INFO, "Inicia proceso de borrar la entidad de Viejito con id={0}", entity.getId( ) );
		persistence.delete( entity );
		LOGGER.log( Level.INFO, "Termina proceso de borrar la entidad de Viejito con id={0}", entity.getId( ) );
	}
}
