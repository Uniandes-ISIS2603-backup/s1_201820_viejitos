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

    private static final Logger LOGGER = Logger.getLogger(FranjaHorariaLogic.class.getName());

    @Inject
    private FranjaHorariaPersistence persistencia;

    /**
     * Obtiene la lista de los registros de Franjas.
     *
     * @return Colección de objetos de FranjaHorariaEntity.
     */
    public List<FranjaHorariaEntity> getFranjas() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las franjas");
        List<FranjaHorariaEntity> franjas = persistencia.findAll();
        LOGGER.info("Termina proceso de consultar todas los franjas");
        return franjas;

    }
    
     /**
     * Obtiene la franja cuya id es la dada y cuyo calendario es el que tiene la otra id dada.
     *@param  id
     * @param idCalendario 
     * @return  Instancia de FranjaHorariaEntity con los datos de la franja consultada
     */
    public FranjaHorariaEntity getFranjaByCalendario(Long id,Long idCalendario) {
        String msg="Inicia proceso de consultar franja con id=" +id +"y cuyo calendario es el que tiene id="+idCalendario;
        LOGGER.log(Level.INFO, msg);
        FranjaHorariaEntity franja = persistencia.findByCalendario(id,idCalendario);
        LOGGER.info("Termina proceso de consultar todas los franjas");
        return franja;

    }

    
    /**
     * Obtiene los datos de una instancia de FranjaHoraria a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de FranjaHorariaEntity con los datos de la franja
     * consultada.
     */
    public FranjaHorariaEntity getFranja(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar franja con id={0}", id);
        FranjaHorariaEntity franja = persistencia.find(id);
        if (franja == null) {
            LOGGER.log(Level.SEVERE, "La franja con el id {0} no existe", id);
        }
        LOGGER.info("Termina proceso de consulta.");
        return franja;

    }

    /**
     * Se encarga de crear un FranjaHoraria en la base de datos. la regla de
     * negocio se verifica en la logica de calendario semanal
     *
     * @param entity Objeto de FranjaHorariaEntity con los datos nuevos
     * @return Objeto de FranjaHorariaEntity con los datos nuevos y su ID.
     * @ throws BusinessLogicException si la id ya existe.
     */
    public FranjaHorariaEntity create(FranjaHorariaEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de una entidad de franja");

//TODO:DONE. No hay ninguna regla de negocio? .revisar que no existe la id y ya, 
//PORQUE las reglas se revisan cuando 
//se intenta añadir una franja a un calendairo
        if(entity!=null&&entity.getId()!=null &&this.getFranja(entity.getId())!=null)
        {
        throw new BusinessLogicException("Ya existe la franja a crear o por lo menos su id ya esta en uso");
        }
        if(entity==null)
        {
          throw new BusinessLogicException("la entidad proporcionada no sirve");
         }
        /* if(entity.getHoraInicio()>=entity.getHoraFin())
        { 
        throw new BusinessLogicException("la entidad proporcionada no tiene una hora de inicio que pase antes que la hora defin");
        }
          String[ ] diasValidos = new String[7];
          diasValidos[0]="LUNES";
          diasValidos[0]="MARTES";
          diasValidos[0]="MIERCOLES";
          diasValidos[0]="JUEVES";
          diasValidos[0]="VIERNES";
          diasValidos[0]="SABADO";
            boolean sirveDia=true;
        for(int i=0;i<7 && sirveDia;i++)
        {
            String diaDado=entity.getDiaSemana().toUpperCase();
            
     if( diasValidos[i].equals(diaDado))
     {
            sirveDia=false;
     }
        }
      if(!sirveDia)
      {
     throw new BusinessLogicException("la entidad proporcionada no tiene un dia valido");
      }
        */
        persistencia.create(entity);
        LOGGER.info("Termina proceso de creación de entidad franja horaria");
        return entity;
    }

    /**
     * Actualiza la información de una instancia de FranjaHoraria.
     *
     * @param entity Instancia de FranjaHorariaEntity con los nuevos datos.
     * @return Instancia de FranjaHorariaEntity con los datos actualizados.
     * throws BusinessLogicException si la franja todavia no se ha creado.
     */
    public FranjaHorariaEntity updateFranja(FranjaHorariaEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar una franja ");

//TODO:DONE No hay ninguna regla de negocio? (solo la mas basica)
         if(entity!=null&&entity.getId()!=null&&this.getFranja(entity.getId())==null)
        {
        throw new BusinessLogicException("NO existe la franja a modificar");
        }
        if(entity==null)
        {
          throw new BusinessLogicException("la entidad proporcionada no sirve");
         }
  /*      if(entity.getHoraInicio()>=entity.getHoraFin())
        { 
        throw new BusinessLogicException("la entidad proporcionada no tiene una hora de inicio que pase antes que la hora defin");
        }
          String[ ] diasValidos = new String[7];
          diasValidos[0]="LUNES";
          diasValidos[0]="MARTES";
          diasValidos[0]="MIERCOLES";
          diasValidos[0]="JUEVES";
          diasValidos[0]="VIERNES";
          diasValidos[0]="SABADO";
            boolean sirveDia=true;
        for(int i=0;i<7 && sirveDia;i++)
        {
            String diaDado=entity.getDiaSemana().toUpperCase();
            
     if( diasValidos[i].equals(diaDado))
     {
            sirveDia=false;
     }
        }
      if(!sirveDia)
      {
     throw new BusinessLogicException("la entidad proporcionada no tiene un dia valido");
      } 
        
        */
    
        return persistencia.update(entity);
    }

    /**
     * Elimina una instancia de FranjaHoraria de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     */
    public void deleteFranja(Long id) 
    {
      //TODO DONE(BORRADO LA OBTENCION DE LA ID): para qué sirve está línea?
        //TODO:DONE este método debe recibir un id y hay que validar que existe un FranjaHorariaEntity con ese id
      
        if(id!=null&&getFranja(id)!=null )
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar franja con id={0}", id);
     
        persistencia.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar franja con id={0}", id);
    }
    
    }

}
