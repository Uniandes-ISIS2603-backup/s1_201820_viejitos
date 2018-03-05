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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author lf.naranjo11
 */
@Stateless
public class FranjaHorariaLogic {
      private static final Logger LOGGER = Logger.getLogger( FranjaHorariaLogic.class.getName( ) );

    @Inject
    private FranjaHorariaPersistence persistencia;
    
    @Inject
    private CalendarioSemanalLogic calendariologic;
   
     /**
     * Obtiene la lista de los registros de Franjas.
     *
     * @return Colección de objetos de FranjaHorariaEntity.
     */
    public List<FranjaHorariaEntity> getFranjas()
    {
     LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las franjas"); 
     List<FranjaHorariaEntity> franjas= persistencia.findAll();
    LOGGER.info("Termina proceso de consultar todas los franjas");
     return franjas;
           
    }
    /**
    * Obtiene los datos de una instancia de FranjaHoraria a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de FranjaHorariaEntity con los datos de la franja consultada.
     */
     public FranjaHorariaEntity getFranja(Long id)
    {
     LOGGER.log(Level.INFO, "Inicia proceso de consultar franja con id={0}", id);
     FranjaHorariaEntity franja= persistencia.find(id);
     if(franja==null)
     {
      LOGGER.log(Level.SEVERE, "La franja con el id {0} no existe", id);
     }
    LOGGER.info("Termina proceso de consulta.");
     return franja;
           
    }
    
     
     /**
     * Se encarga de crear un FranjaHoraria en la base de datos.
     *la regla de negocio se verifica en la logica de calendario semanal
     * @param entity Objeto de FranjaHorariaEntity con los datos nuevos
     * @return Objeto de FranjaHorariaEntity con los datos nuevos y su ID.
     */
     public FranjaHorariaEntity create(FranjaHorariaEntity entity) {
        LOGGER.info( "Inicia proceso de creación de una entidad de franja" );
	// Verifica la regla de negocio que dice que no puede haber dos franjas Horarias 
        
	persistencia.create( entity );
	LOGGER.info( "Termina proceso de creación de entidad franja horaria" );
	return entity;
    }
     
      /**
     * Actualiza la información de una instancia de FranjaHoraria.
     *
     * @param entity Instancia de FranjaHorariaEntity con los nuevos datos.
     * @return Instancia de FranjaHorariaEntity con los datos actualizados.
     */
     public FranjaHorariaEntity  updateFranja(FranjaHorariaEntity entity)
     {
      LOGGER.log(Level.INFO, "Inicia proceso de actualizar una franja ");
        return persistencia.update(entity);
     }
     
     /**
     * Elimina una instancia de FranjaHoraria de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     */
      public void deleteFranja(Long id ) 
	{
		LOGGER.log(Level.INFO, "Inicia proceso de borrar franja con id={0}", id);
		persistencia.delete( id );
	        LOGGER.log(Level.INFO, "Termina proceso de borrar franja con id={0}", id);
	}
    
    
    
}
