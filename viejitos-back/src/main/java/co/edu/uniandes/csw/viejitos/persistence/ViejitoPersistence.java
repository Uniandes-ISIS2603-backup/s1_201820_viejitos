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
package co.edu.uniandes.csw.viejitos.persistence;

import co.edu.uniandes.csw.viejitos.entities.ViejitoEntity;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * @author ISIS2603
 */
@Stateless
public class ViejitoPersistence
{
	private static final Logger LOGGER = Logger.getLogger( ViejitoPersistence.class.getName( ) );

	@PersistenceContext( unitName = "ViejitosPU" )
	protected EntityManager em;

	/**
	 * @param entity objeto viejitos que se creará en la base de datos
	 * @return devuelve la entidad creada con un id dado por la base de datos.
	 */
	public ViejitoEntity create( ViejitoEntity entity )
	{
		LOGGER.info( "Creando una nueva entidad de viejitos" );
		em.persist( entity );
		LOGGER.info( "Creando una entidad de viejitos" );
		return entity;
	}

	/**
	 * Busca si hay alguna entidad de viejitos con el nombre que se envía de argumento
	 *
	 * @param name: Nombre de la entidad de viejitos que se está buscando
	 * @return null si no existe ninguna entidad viejitos con el nombre del argumento. Si
	 * existe alguna devuelve la primera.
	 */
	public ViejitoEntity findByName( String name )
	{
		LOGGER.log( Level.INFO, "Consultando entidades de viejitos por nombre ", name );

		// Se crea un query para buscar entidades de viejitos con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
		TypedQuery<ViejitoEntity> query = em.createQuery( "Select e From ViejitoEntity e where e.name = :name", ViejitoEntity.class );
		// Se remplaza el placeholder ":name" con el valor del argumento
		query = query.setParameter( "name", name );
		// Se invoca el query se obtiene la lista resultado
		List<ViejitoEntity> sameName = query.getResultList( );
		if( sameName.isEmpty( ) )
		{
			return null;
		}
		else
		{
			return sameName.get( 0 );
		}
	}

	public List<ViejitoEntity> findAll( )
	{
		LOGGER.info( "Consultando todas las entidades de viejitos" );
		TypedQuery<ViejitoEntity> query = em.createQuery( "select u from ViejitoEntity u", ViejitoEntity.class );
		return query.getResultList( );
	}

	public ViejitoEntity find( Long id )
	{
		return em.find( ViejitoEntity.class, id );
	}

	public ViejitoEntity update( ViejitoEntity entity )
	{
		return em.merge( entity );
	}

	public void delete( ViejitoEntity entity )
	{
		em.remove( entity );
	}
}
