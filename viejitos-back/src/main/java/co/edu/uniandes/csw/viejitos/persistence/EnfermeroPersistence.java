package co.edu.uniandes.csw.viejitos.persistence;

import co.edu.uniandes.csw.viejitos.entities.EnfermeroEntity;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * @author JuanSebastian
 */
@Stateless
public class EnfermeroPersistence
{
	private static final Logger LOGGER = Logger.getLogger( EnfermeroPersistence.class.getName( ) );

	@PersistenceContext( unitName = "ViejitosPU" )
	protected EntityManager em;

	/**
	 * @param entity objeto enfermero que se creará en la base de datos
	 * @return devuelve la entidad creada con un id dado por la base de datos.
	 */
	public EnfermeroEntity create( EnfermeroEntity entity )
	{
		LOGGER.info( "Creando una nueva entidad de viejitos" );
		em.persist( entity );
		LOGGER.info( "Creando una entidad de viejitos" );
		return entity;
	}

	/**
	 * Busca si hay alguna entidad de enfermero con el nombre que se envía de argumento
	 *
	 * @param name: Nombre de la entidad de enfermero que se está buscando
	 * @return null si no existe ninguna entidad enfermero con el nombre del argumento. Si
	 * existe alguna devuelve la primera.
	 */
	public EnfermeroEntity findByName( String name )
	{
		LOGGER.log( Level.INFO, "Consultando entidades de enfermeros por nombre ", name );

		// Se crea un query para buscar entidades de enfermero con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
		TypedQuery<EnfermeroEntity> query = em.createQuery( "Select e From EnfermeroEntity e where e.name = :name", EnfermeroEntity.class );
		// Se remplaza el placeholder ":name" con el valor del argumento
		query = query.setParameter( "name", name );
		// Se invoca el query se obtiene la lista resultado
		List<EnfermeroEntity> sameName = query.getResultList( );
		if( sameName.isEmpty( ) )
		{
			return null;
		}
		else
		{
			return sameName.get( 0 );
		}
	}

        /**
	 * Busca si hay alguna entidad de enfermero con el login que se envía de argumento
	 *
	 * @param login: Nombre de usuario de la entidad de enfermero que se está buscando
	 * @return null si no existe ninguna entidad enfermero con el nombre del argumento. Si
	 * existe alguna devuelve la primera.
	 */
	public EnfermeroEntity findByLogin( String login )
	{
		LOGGER.log( Level.INFO, "Consultando entidades de enfermeros por login ", login );

		// Se crea un query para buscar entidades de enfermero con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
		TypedQuery<EnfermeroEntity> query = em.createQuery( "Select e From EnfermeroEntity e where e.login = :login", EnfermeroEntity.class );
		// Se remplaza el placeholder ":login" con el valor del argumento
		query = query.setParameter( "login", login );
		// Se invoca el query se obtiene la lista resultado
		List<EnfermeroEntity> sameLogin = query.getResultList( );
		if( sameLogin.isEmpty( ) )
		{
			return null;
		}
		else
		{
			return sameLogin.get( 0 );
		}
	}
        
	public List<EnfermeroEntity> findAll( )
	{
		LOGGER.info( "Consultando todas las entidades de enfermeros" );
		TypedQuery<EnfermeroEntity> query = em.createQuery( "select u from EnfermeroEntity u", EnfermeroEntity.class );
		return query.getResultList( );
	}

	public EnfermeroEntity find( Long id )
	{
		return em.find( EnfermeroEntity.class, id );
	}

	public EnfermeroEntity update( EnfermeroEntity entity )
	{
		return em.merge( entity );
	}

	public void delete( EnfermeroEntity entity )
	{
		em.remove( entity );
	}
}