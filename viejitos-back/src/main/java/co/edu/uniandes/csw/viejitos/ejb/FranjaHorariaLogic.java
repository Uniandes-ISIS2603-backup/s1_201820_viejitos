/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.ejb;

import co.edu.uniandes.csw.viejitos.entities.FranjaHorariaEntity;
import co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viejitos.persistence.CalendarioSemanalPersistence;
import co.edu.uniandes.csw.viejitos.persistence.FranjaHorariaPersistence;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author lf.naranjo11
 */
public class FranjaHorariaLogic {
      private static final Logger LOGGER = Logger.getLogger( FranjaHorariaLogic.class.getName( ) );

    @Inject
    private FranjaHorariaPersistence persistencia;
    
    @Inject
    private CalendarioSemanalPersistence persistenciaCalendario;
   
    
     public FranjaHorariaEntity create(FranjaHorariaEntity entity) throws BusinessLogicException{
        LOGGER.info( "Inicia proceso de creación de una entidad de Enfermero" );
	// Verifica la regla de negocio que dice que no puede haber dos entidades de Viejitos con el mismo nombre
	if( persistencia.findByDay(entity.getDiaSemana( ) ) != null ){
            throw new BusinessLogicException( "Ya existe una entidad de Enfermero con el nombre \"" + entity.getName( ) + "\"" );
	}
	// Invoca la persistencia para crear la entidad de Viejito
	persistencia.create( entity );
	LOGGER.info( "Termina proceso de creación de entidad de Enfermero" );
	return entity;
    }
    
    
    
}
