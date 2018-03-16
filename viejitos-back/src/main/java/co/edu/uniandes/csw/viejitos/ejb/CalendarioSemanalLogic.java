/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.ejb;

import co.edu.uniandes.csw.viejitos.entities.CalendarioSemanalEntity;
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
public class CalendarioSemanalLogic {
  
     private static final Logger LOGGER = Logger.getLogger( CalendarioSemanalLogic.class.getName( ) );

    @Inject
    private FranjaHorariaLogic franjaLogic;
    
    @Inject
    private CalendarioSemanalPersistence persistencia;
    
    
    /**
     * Obtiene los datos de una instancia de CalendarioSemanal a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de CalendarioSemanalEntity con los datos del calendario consultado.
     */
      public CalendarioSemanalEntity getCalendario(Long id)
    {
     LOGGER.log(Level.INFO, "Inicia proceso de consultar calendario con id={0}", id);
     CalendarioSemanalEntity calendario= persistencia.find(id);
     if(calendario==null)
     {
      LOGGER.log(Level.SEVERE, "El calendario con el id {0} no existe", id);
     }
    LOGGER.info("Termina proceso de consulta.");
     return calendario;
           
    }
      
       /**
     * Obtiene la lista de los registros de calendarios.
     *
     * @return Colección de objetos de CalendarioSemanalEntity.
     */
      public List<CalendarioSemanalEntity> getCalendarios()
    {
     LOGGER.log(Level.INFO, "Inicia proceso de consulta de todos los calendarios" );
    List<CalendarioSemanalEntity> calendarios=persistencia.findAll();
    LOGGER.info("Termina proceso de consulta .");
     return calendarios;
           
    }
      
   
    /**
     * Se encarga de crear un calendario en la base de datos.
     *
     * @param entity Objeto de calendarioEntity con los datos nuevos
     * @return Objeto de CalendarioSemanalEntity con los datos nuevos y su ID.
     */
    public CalendarioSemanalEntity createCalendario(CalendarioSemanalEntity entity)
           {
               
               //validar regla de negocio.
             return persistencia.create(entity);
           }
    
    
       /**
     * Actualiza la información de una instancia de Calendario.
     *
     * @param entity Instancia de CalendarioSemanalEntity con los nuevos datos.
     * @return Instancia de CalendarioSemanalEntity con los datos actualizados.
     */
    public CalendarioSemanalEntity updateCalendario(CalendarioSemanalEntity entity)
    {
                LOGGER.log(Level.INFO, "Inicia proceso de actualizar un calendario ");
        return persistencia.update(entity);
    
    }
    
    
    /**
     * Obtiene una colección de instancias de FranjaHorariaEntity asociadas a una
     * instancia de Calendario
     *
     * @param calendarioId Identificador de la instancia de calendario
     * @return Colección de instancias de FranjaHorariaEntity asociadas a la instancia de
     * CalendarioSemanal
     */
    public List<FranjaHorariaEntity> listFranjas(Long calendarioId)
    {
            LOGGER.log(Level.INFO, "Inicia proceso de consultar todos las franjas  del calendario con id = {0}",calendarioId);
            return getCalendario(calendarioId).getFranjas();
    }
    
    /**
     * Obtiene una instancia de FranjaHoraria asociada a una instancia de Calendario
     *
     * @param calendarioId Identificador de la instancia de CalendarioSemanal
     * @param franjaId Identificador de la instancia de FranjaHoraria
     * @return La entidadd de FranjaHoraria del calendarioSemanal
     */
    
    public FranjaHorariaEntity getFranja(Long franjaId, Long calendarioId)
    {
    LOGGER.log(Level.INFO, "Inicia proceso de consultar una franja con id = {0}", franjaId);
        List<FranjaHorariaEntity> list = getCalendario(calendarioId).getFranjas();
        FranjaHorariaEntity franjaEntity = new FranjaHorariaEntity();
        franjaEntity.setId(franjaId);
        int index = list.indexOf(franjaEntity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }
    
    /**
     * sirve para reasignar a otro calendario una franja.
    *<pre>la franja ya fue creada, el calendario ya fue creado
    *</pre>
    *@param franjaId el id de la franja a añadir
    *@param  calendarioId id de la franja a añadir
    *@return la franja añadida
     * @throws BusinessLogicException Si la franja ya existe en otro calendario o si la franja no cabe en el calendario actual
     * 
    */
     public FranjaHorariaEntity addFranja(Long franjaId, Long calendarioId) throws BusinessLogicException
     {
        LOGGER.log(Level.INFO, "Inicia proceso de agregar una franja con id = {0}", calendarioId);
       FranjaHorariaEntity entityF =franjaLogic.getFranja(franjaId);
       
        CalendarioSemanalEntity ent=persistencia.find(calendarioId);
        List<FranjaHorariaEntity> lista=ent.getFranjas();
        for(FranjaHorariaEntity franja:lista)
        {
            if(franja.getDiaSemana().equals(entityF.getDiaSemana()))
            {
        if(franja.getHoraInicio()<entityF.getHoraInicio() && entityF.getHoraInicio()<franja.getHoraInicio()
                || entityF.getHoraInicio()<franja.getHoraInicio() && franja.getHoraInicio()<entityF.getHoraFin()
                || entityF.getHoraInicio()<franja.getHoraFin()&& franja.getHoraFin()<entityF.getHoraFin()
                )
             {
                throw new BusinessLogicException("el horario de la franja ya esta ocupado");
             }
            }
        }
       List<CalendarioSemanalEntity> listaprueba= persistencia.findAll();
       for(CalendarioSemanalEntity calen:listaprueba)
        {
          List<FranjaHorariaEntity> franjasactuales=calen.getFranjas();
            if(franjasactuales.indexOf(entityF)>=0)
            {
             throw new BusinessLogicException("la franja ya fue asignada a otro calendario");
            }
            
       }
        lista.add(entityF);
        ent.setFranjas(lista);
        persistencia.update(ent);
        return  entityF;
    }
     
     /**
     * Desasocia una franjaHoraria existente de un CalendarioSemanal existente
     * @param franjaId Identificador de la instancia de franja
     * @param calendarioId Identificador de la instancia de calendario
     */
     public void removeFranja(Long franjaId, Long calendarioId)
     {
         
           CalendarioSemanalEntity ent=  persistencia.find(calendarioId);
            List<FranjaHorariaEntity> lista=ent.getFranjas();
            FranjaHorariaEntity franjaEntity = new FranjaHorariaEntity();
        franjaEntity.setId(franjaId);
           int index = lista.indexOf(franjaEntity);
        if (index >= 0) {
            lista.remove(index);
            ent.setFranjas(lista);
            persistencia.update(ent);
                franjaLogic.deleteFranja(franjaEntity);

        }
           
     }
    
    /**
     * Elimina una instancia de CalendarioSemanal de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     */
        public void deleteCalendario( CalendarioSemanalEntity entity ) 
	{
		LOGGER.log( Level.INFO, "Inicia proceso de borrar la entidad de calendario con id={0}", entity.getId() );
		persistencia.delete( entity.getId() );
		LOGGER.log( Level.INFO, "Termina proceso de borrar la entidad de calendario con id={0}",entity.getId() );
	}
     
}
