package co.edu.uniandes.csw.viejitos.persistence;

import co.edu.uniandes.csw.viejitos.entities.CalificacionEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class CalificacionPersistence
{
	private static final Logger LOGGER = Logger.getLogger( CalificacionPersistence.class.getName( ) );

	@PersistenceContext( unitName = "ViejitosPU" )
	protected EntityManager em;

	/**
	 * @param entity objeto calificacion que se creará en la base de datos
	 * @return devuelve la entidad creada con un id dado por la base de datos.
	 */
	public CalificacionEntity create( CalificacionEntity entity )
	{
		LOGGER.info( "Creando una nueva entidad de calificacion" );
		em.persist( entity );
		LOGGER.info( "Creando una entidad de calificacion" );
		return entity;
	}

	
	public List<CalificacionEntity> findAll( )
	{
		LOGGER.info( "Consultando todas las calificaciones" );
		TypedQuery<CalificacionEntity> query = em.createQuery( "select u from CalificacionEntity u", CalificacionEntity.class );
		return query.getResultList( );
	}

	public CalificacionEntity find( Long id )
	{
		return em.find( CalificacionEntity.class, id );
	}
        
        public List<CalificacionEntity> findForTarget( String loginCalificado )
        {
            TypedQuery<CalificacionEntity> query = em.createQuery( "select u from CalificacionEntity u where u.loginCalificado = " + loginCalificado + "", CalificacionEntity.class );
            return query.getResultList( );
        }

	public CalificacionEntity update( CalificacionEntity entity )
	{
		return em.merge( entity );
	}

	public void delete( CalificacionEntity entity )
	{
		em.remove( entity );
	}
        
        public void delete ( Long id )
        {
            LOGGER.log(Level.INFO, "Borrando Calificacion Enfermero con id={0}", id);
            CalificacionEntity entity = em.find(CalificacionEntity.class, id);
            em.remove(entity);
        }
}