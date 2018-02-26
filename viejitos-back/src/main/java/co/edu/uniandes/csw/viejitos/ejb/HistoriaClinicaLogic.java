/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.ejb;

import co.edu.uniandes.csw.viejitos.entities.HistoriaClinicaEntity;
import co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viejitos.persistence.HistoriaClinicaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jj.silva
 */
@Stateless
public class HistoriaClinicaLogic
{
    private static final Logger LOGGER = Logger.getLogger( HistoriaClinicaLogic.class.getName( ) );
    
    @Inject
    private HistoriaClinicaPersistence persistence;
    
    public HistoriaClinicaEntity create( HistoriaClinicaEntity entity )
	{
		LOGGER.info( "Inicia proceso de creación de una entidad de HistoriaClinica" );
		// Invoca la persistencia para crear la entidad de HistoriaClinica
		persistence.create( entity );
		LOGGER.info( "Termina proceso de creación de entidad de HistoriaClinica" );
		return entity;
	}
    
    public List<HistoriaClinicaEntity> getAll( )
	{
		LOGGER.info( "Inicia proceso de consultar todas las entidades de HistoriaClinica" );
		List<HistoriaClinicaEntity> entities = persistence.findAll( );
		LOGGER.info( "Termina proceso de consultar todas las entidades de HistoriaClinica" );
		return entities;
	}
    
    public HistoriaClinicaEntity getById( Long id )
	{
		return persistence.find( id );
	}
    
    public HistoriaClinicaEntity update( HistoriaClinicaEntity entity ) 
        {
		return persistence.update( entity );
	}
    
    public void delete( HistoriaClinicaEntity entity ) 
	{
		LOGGER.log( Level.INFO, "Inicia proceso de borrar la entidad de HistoriaClinica con id={0}", entity.getId( ) );
		persistence.delete( entity.getId() );
		LOGGER.log( Level.INFO, "Termina proceso de borrar la entidad de HistoriaClinica con id={0}", entity.getId( ) );
	}
}
