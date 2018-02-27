/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.ejb;

import co.edu.uniandes.csw.viejitos.entities.CalendarioSemanalEntity;
import co.edu.uniandes.csw.viejitos.persistence.CalendarioSemanalPersistence;
import co.edu.uniandes.csw.viejitos.persistence.FranjaHorariaPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author lf.naranjo11
 */
@Stateless
public class CalendarioSemanalLogic {
  
     private static final Logger LOGGER = Logger.getLogger( FranjaHorariaLogic.class.getName( ) );

    @Inject
    private FranjaHorariaPersistence persistenciaFranja;
    
    @Inject
    private CalendarioSemanalPersistence persistencia;
    
    public CalendarioSemanalEntity createCalendario(CalendarioSemanalEntity entity)
           {
               
        return persistencia.create(entity);
           }
    public CalendarioSemanalEntity updateCalendario(CalendarioSemanalEntity entity)
    {
        return persistencia.update(entity);
    
    }
    
    
    
        public void deleteCalendario( CalendarioSemanalEntity entity ) 
	{
		LOGGER.log( Level.INFO, "Inicia proceso de borrar la entidad de Cliente con id={0}", entity.getId( ) );
		persistencia.delete( entity.getId() );
		LOGGER.log( Level.INFO, "Termina proceso de borrar la entidad de Cliente con id={0}", entity.getId( ) );
	}
}
