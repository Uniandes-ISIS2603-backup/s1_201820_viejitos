/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.persistence;

import co.edu.uniandes.csw.viejitos.entities.ClienteEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author jj.silva
 */
@Stateless
public class ClientePersistence
{
    private static final Logger LOGGER = Logger.getLogger( ClientePersistence.class.getName( ) );
    
    @PersistenceContext( unitName = "ViejitosPU" )
    protected EntityManager em;
    
    /**
	 * @param entity objeto Cliente que se creará en la base de datos
	 * @return devuelve la entidad creada con un id dado por la base de datos.
	 */
	public ClienteEntity create( ClienteEntity entity )
	{
		LOGGER.info( "Creando una nueva entidad de Cliente" );
		em.persist( entity );
		LOGGER.info( "Creando una entidad de Cliente" );
		return entity;
	}
        
        /**
         * 
         * @return Todas las entidades Cliente. 
         */
        public List<ClienteEntity> findAll( )
	{
		LOGGER.info( "Consultando todas las entidades de Clientes" );
		TypedQuery<ClienteEntity> query = em.createQuery( "select u from ClienteEntity u", ClienteEntity.class );
		return query.getResultList( );
	}
        
        /**
         * 
         * @param id Identificador de la entidad Cliente que se quiere encontrar.
         * @return La entidad con el id pasado por parametro.
         */
        public ClienteEntity find(Long id)
        {
                LOGGER.log(Level.INFO, "Consultando Cliente con id={0}", id);
                return em.find(ClienteEntity.class, id);
        }
        
        public ClienteEntity findByLogin( String login )
	{
		LOGGER.log( Level.INFO, "Consultando entidades de Clientes por login ", login );

		TypedQuery<ClienteEntity> query = em.createQuery( "Select e From ClienteEntity e where e.login = :login", ClienteEntity.class );
		query = query.setParameter( "login", login );
		// Se invoca el query se obtiene la lista resultado
		List<ClienteEntity> sameLogin = query.getResultList( );
		if( sameLogin.isEmpty( ) )
		{
			return null;
		}
		else
		{
			return sameLogin.get( 0 );
		}
	}
         
        /**
         * 
         * @param entity Objeto Cliente que se actualizara.
         * @return La entidad Cliente ya actualizada.
         */
        public ClienteEntity update(ClienteEntity entity)
        {
                LOGGER.log(Level.INFO, "Actualizando Cliente con id={0}", entity.getId());
                return em.merge(entity);
        }

        /**
         * 
         * @param id Identificador del cliente que se quiere borrar. 
         */
        public void delete(Long id)
        {
                LOGGER.log(Level.INFO, "Borrando Cliente con id={0}", id);
                ClienteEntity entity = em.find(ClienteEntity.class, id);
                em.remove(entity);
        }
}
