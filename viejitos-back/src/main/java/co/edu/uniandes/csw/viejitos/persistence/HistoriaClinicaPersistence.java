/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.persistence;

import co.edu.uniandes.csw.viejitos.entities.HistoriaClinicaEntity;
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
public class HistoriaClinicaPersistence
{
    private static final Logger LOGGER = Logger.getLogger( HistoriaClinicaPersistence.class.getName( ) );
    
    @PersistenceContext( unitName = "ViejitosPU" )
    protected EntityManager em;
    
    /**
	 * @param entity objeto HistoriaClinica que se creará en la base de datos
	 * @return devuelve la entidad creada con un id dado por la base de datos.
	 */
	public HistoriaClinicaEntity create( HistoriaClinicaEntity entity )
	{
		LOGGER.info( "Creando una nueva entidad de HistoriaClinica" );
		em.persist( entity );
		LOGGER.info( "Creando una entidad de HistoriaClinica" );
		return entity;
	}
        
        /**
         * 
         * @return Todas las entidades HistoriaClinica. 
         */
        public List<HistoriaClinicaEntity> findAll( )
	{
		LOGGER.info( "Consultando todas las entidades de HistoriaClinica" );
		TypedQuery<HistoriaClinicaEntity> query = em.createQuery( "select u from HistoriaClinicaEntity u", HistoriaClinicaEntity.class );
		return query.getResultList( );
	}
        
        /**
         * 
         * @param id Identificador de la entidad HistoriaClinica que se quiere encontrar.
         * @return La entidad con el id pasado por parametro.
         */
        public HistoriaClinicaEntity find(Long id)
        {
                LOGGER.log(Level.INFO, "Consultando HistoriaClinica con id={0}", id);
                return em.find(HistoriaClinicaEntity.class, id);
        }
        
        /**
         * 
         * @param entity Objeto HistoriaClinica que se actualizara.
         * @return La entidad HistoriaClinica ya actualizada.
         */
        public HistoriaClinicaEntity update(HistoriaClinicaEntity entity)
        {
                LOGGER.log(Level.INFO, "Actualizando HistoriaClinica con id={0}", entity.getId());
                return em.merge(entity);
        }

        /**
         * 
         * @param id Identificador de la HistoriaClinica que se quiere borrar. 
         */
        public void delete(Long id)
        {
                LOGGER.log(Level.INFO, "Borrando HistoriaClinica con id={0}", id);
                HistoriaClinicaEntity entity = em.find(HistoriaClinicaEntity.class, id);
                em.remove(entity);
        }
}
