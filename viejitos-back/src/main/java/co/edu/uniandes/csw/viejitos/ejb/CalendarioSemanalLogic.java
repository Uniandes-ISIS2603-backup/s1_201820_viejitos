/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.ejb;

//TODO:DONE Borrar lo que no se utilice
import co.edu.uniandes.csw.viejitos.entities.CalendarioSemanalEntity;
import co.edu.uniandes.csw.viejitos.entities.EnfermeroEntity;
import co.edu.uniandes.csw.viejitos.entities.FranjaHorariaEntity;
import co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viejitos.persistence.CalendarioSemanalPersistence;
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

    private static final Logger LOGGER = Logger.getLogger(CalendarioSemanalLogic.class.getName());

    @Inject
    private FranjaHorariaLogic franjaLogic;

    @Inject
    private CalendarioSemanalPersistence persistencia;
    
    @Inject
    private MedicoLogic medicoLogic;
    
    
   @Inject
    private EnfermeroLogic enfermeroLogic;
    

    /**
     * Obtiene los datos de una instancia de CalendarioSemanal a partir de su
     * ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de CalendarioSemanalEntity con los datos del calendario
     * consultado.
     */
    public CalendarioSemanalEntity getCalendario(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar calendario con id={0}", id);
        CalendarioSemanalEntity calendario = persistencia.find(id);
        if (calendario == null) {
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
    public List<CalendarioSemanalEntity> getCalendarios() {
        LOGGER.log(Level.INFO, "Inicia proceso de consulta de todos los calendarios");
        List<CalendarioSemanalEntity> calendarios = persistencia.findAll();
        LOGGER.info("Termina proceso de consulta .");
        return calendarios;

    }

    /**
     * Se encarga de crear un calendario en la base de datos.
     *Se debe verificar que no existe un calendario con el id
     * @param entity Objeto de calendarioEntity con los datos nuevos
     * @return Objeto de CalendarioSemanalEntity con los datos nuevos y su ID.
     * @throws  BusinessLogicException si ya existe el calendario
     */
    public CalendarioSemanalEntity createCalendario(CalendarioSemanalEntity entity) throws BusinessLogicException {

          CalendarioSemanalEntity ent=null;
        //TODO :DONE validar regla de negocio.
        if(entity!=null&&entity.getId()!=null &&persistencia.find(entity.getId())!=null)
       {
            throw new  BusinessLogicException("el id no es valido"); 
        }  
        else if(entity==null)
        {
        throw new  BusinessLogicException("la entidad es nula"); 
        }
        else
        {
      ent=persistencia.create(entity);
        }
         return ent;
    }

    /**
     * Actualiza la información de una instancia de Calendario.
     *
     * @param entity Instancia de CalendarioSemanalEntity con los nuevos datos.
     * @return Instancia de CalendarioSemanalEntity con los datos actualizados.
     * @throws BussinesLogicException si el calendario no existe en la base de dato
     */
    public CalendarioSemanalEntity updateCalendario(CalendarioSemanalEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar un calendario ");
        //TODO:DONE validar regla de negocio.
           CalendarioSemanalEntity ent=null;
        //TODO :DONE validar regla de negocio.
        if(entity!=null&&persistencia.find(entity.getId())==null)
       {
            throw new  BusinessLogicException("el id no es valido. no se puede modificar porque no existe aun"); 
        }  
        else if(entity==null)
        {
        throw new  BusinessLogicException("la entidad es nula"); 
        }
        else
        {
      ent= persistencia.update(entity);
        }
         return ent;
    

    }

    /**
     * Obtiene una colección de instancias de FranjaHorariaEntity asociadas a
     * una instancia de Calendario
     *
     * @param calendarioId Identificador de la instancia de calendario
     * @return Colección de instancias de FranjaHorariaEntity asociadas a la
     * instancia de CalendarioSemanal
     @throws BusinessLogicException Si la franja ya existe en otro calendario
     * o si la franja no cabe en el calendario actual
     */
    public List<FranjaHorariaEntity> listFranjas(Long calendarioId)  throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos las franjas  del calendario con id = {0}", calendarioId);
        //TODO DONE: QUé pasa si calendarioId no existe? Arrojaría un NullPointerException
      if(calendarioId!=null)
          {
        return getCalendario(calendarioId).getFranjas();
         }
      else
          {
          throw new BusinessLogicException("se necesita prporcionar una id");
          }
    }

    /**
     * Obtiene una instancia de FranjaHoraria asociada a una instancia de
     * Calendario
     *
     * @param calendarioId Identificador de la instancia de CalendarioSemanal
     * @param franjaId Identificador de la instancia de FranjaHoraria
     * @return La entidadd de FranjaHoraria del calendarioSemanal
     */
    public FranjaHorariaEntity getFranja(Long franjaId, Long calendarioId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar una franja con id = {0}", franjaId);
        //TODO:DONE(duda en la sentencia) Esto hay que remplazarlo por un query en la BD
  
      franjaLogic.getFranjaByCalendario(franjaId,calendarioId );
      
      
        List<FranjaHorariaEntity> list = getCalendario(calendarioId).getFranjas();
        FranjaHorariaEntity franjaEntity = new FranjaHorariaEntity();
        franjaEntity.setId(franjaId);
        int index = list.indexOf(franjaEntity);
        if (index >= 0)
        {
            return list.get(index);
        }
        return null;
        
    }

    /**
     * sirve para reasignar a otro calendario una franja.revisa la regla de negocio 
     * que dice que no se pueden sobrelapar horarios en el mismo calendario.
     * <pre>la franja ya fue creada, el calendario ya fue creado
     * </pre>
     *
     * @param franjaId el id de la franja a añadir
     * @param calendarioId id de la franja a añadir
     * @return la franja añadida
     * @throws BusinessLogicException Si la franja ya existe en otro calendario
     * o si la franja no cabe en el calendario actual
     *
     */
    
    //TODO:UNDONE Darle una doble revisada a este código complicado. NO ENTIENDO PORQUE NO ESTA FUNCIONANDO LA REVISION DE LA BUSSINESS RULE.LA QUE ESTA COMENTADA LLAMA A UN NULLLPOINTEREXCEPTION
    public FranjaHorariaEntity addFranja(Long franjaId, Long calendarioId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de agregar una franja con id = {0}", calendarioId);
        FranjaHorariaEntity entityF = franjaLogic.getFranja(franjaId);
        LOGGER.log(Level.INFO, "Inicia proceso de consultar las franja de un"
                + "calendario con id = {0}", calendarioId);

       CalendarioSemanalEntity ent = persistencia.find(calendarioId);
       List<FranjaHorariaEntity> lista = ent.getFranjas();
        for (FranjaHorariaEntity franja : lista) {
            if (franja.getDiaSemana().equals(entityF.getDiaSemana())) {
                if ((franja.getHoraInicio() < entityF.getHoraInicio() && entityF.getHoraFin() < franja.getHoraFin())
                        || (entityF.getHoraInicio() < franja.getHoraInicio() && franja.getHoraInicio() < entityF.getHoraFin())
                        || (entityF.getHoraInicio() < franja.getHoraFin() && franja.getHoraFin() < entityF.getHoraFin())) {
                    throw new BusinessLogicException("el horario de la franja ya esta ocupado");
                }
            }
        }
        List<CalendarioSemanalEntity> listaprueba = persistencia.findAll();
        for (CalendarioSemanalEntity calen : listaprueba) {
            List<FranjaHorariaEntity> franjasactuales = calen.getFranjas();
            if (franjasactuales.indexOf(entityF) >= 0) {
                throw new BusinessLogicException("la franja ya fue asignada a otro calendario");
            }

        }
   //BookEntity book = bookLogic.getBook(bookid);
       // entity.setBook(book);
      // persistence.update(entity);
     lista.add(entityF);
     ent.setFranjas(lista);
     entityF.setCalendario(ent);
     franjaLogic.updateFranja(entityF);
     persistencia.update(ent);
     return entityF;
    }

    /**
     * Desasocia una franjaHoraria existente de un CalendarioSemanal existente
     *
     * @param franjaId Identificador de la instancia de franja
     * @param calendarioId Identificador de la instancia de calendario
     */
    public void removeFranja(Long franjaId, Long calendarioId) {

        CalendarioSemanalEntity ent = persistencia.find(calendarioId);
        List<FranjaHorariaEntity> lista = ent.getFranjas();
        FranjaHorariaEntity franjaEntity = new FranjaHorariaEntity();
        franjaEntity.setId(franjaId);
        int index = lista.indexOf(franjaEntity);
        if (index >= 0) {
            lista.remove(index);
            ent.setFranjas(lista);
            persistencia.update(ent);
            franjaLogic.deleteFranja(franjaEntity.getId());

        }

    }

    /**
     * Elimina una instancia de CalendarioSemanal de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @throws BusinessLogicException cuando noe existe el calendario a eliminar
     */
    public void deleteCalendario(CalendarioSemanalEntity entity) throws BusinessLogicException {
        // TODO:DONE Hay que validar que existe CalendarioSemanalEntity con ese id
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la entidad de calendario con id={0}", entity.getId());
        if(entity!=null &&getCalendario(entity.getId())!=null)
        {   
        persistencia.delete(entity.getId());
       }
        else
            {
            throw new BusinessLogicException("no se puede borrar un calendario que no existe en la base de datos");
            }
        LOGGER.log(Level.INFO, "Termina proceso de borrar la entidad de calendario con id={0}", entity.getId());
    }
    
    
    /**
     * Asigna una instancia de enfermero Existente a un calendario NUEVO, es decir que aun no existe(el calendario).
     * @param idEnfermero
     * @param calendarioId
     * @return la entidad de nefermero
     */
    public CalendarioSemanalEntity setCalendariotoEnfermero(Long idEnfermero,Long calendarioId) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de obtener un enfermero con id  = {0}", idEnfermero);
        EnfermeroEntity entityEnf = enfermeroLogic.getById(idEnfermero);
        LOGGER.log(Level.INFO, "termina proceso de obtener un enfermero");
        
        LOGGER.log(Level.INFO, "Inicia proceso de obtener un calendario con id  = {0}", calendarioId);
        CalendarioSemanalEntity entityCal =getCalendario(calendarioId);
        LOGGER.log(Level.INFO, "termina proceso de obtener un enfermero");

        
      LOGGER.log(Level.INFO, "Inicia proceso de asignar a un  enfermero"+idEnfermero+ "con calendario con id  = {0}", calendarioId);
      entityEnf.setCalendario(entityCal);
      enfermeroLogic.update(entityEnf);
     LOGGER.log(Level.INFO, "finaliza proceso de asignar a un  enfermero "+idEnfermero+"con calendario con id  = {0}", calendarioId);

     return entityCal;
    
    
    }
    
    

    
    
    /**
     * 
     * dado el id del enfermero entrega el calendario.
     * @param  enfermeroId  la id del enfermero
     *@return la entidad de calendario asociada a un enefermo con id dado
     **/
    public CalendarioSemanalEntity getCalendarioByEnfermero(Long enfermeroId)
    {
        CalendarioSemanalEntity cal=null;
    LOGGER.log(Level.INFO,"Inicia proceso de consultar enfermero con id={0}" , enfermeroId);
       EnfermeroEntity enf= enfermeroLogic.getById(enfermeroId);
       LOGGER.log(Level.INFO,"Finaliza porceso de consulta de enfermero con id={0}" , enfermeroId);
        if(enf!=null)
       {
        cal=enf.getCalendario(); 
      }
      
     return cal;
    
    }
    
    /**
     * dado el id de un enfermero le remueve su calendario
     * @param idEnfermero
     * @return el entidad de enfermero ya sin su calendario
     * @throws co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException
     */
    //TODO ES posible que sobre y se pueda poner con setCalendarioToeEnfefermero
    public EnfermeroEntity   removeCalendarioOfEnfermero(Long idEnfermero) throws BusinessLogicException
    {
         LOGGER.log(Level.INFO, "Inicia proceso de obtener un enfermero con id  = {0}", idEnfermero);
        EnfermeroEntity entityEnf;
        entityEnf = enfermeroLogic.getById(idEnfermero);
        LOGGER.log(Level.INFO, "termina proceso de obtener un enfermero");
        entityEnf.setCalendario(null);
        enfermeroLogic.update(entityEnf);
        LOGGER.log(Level.INFO, "finaliza proceso de asignar a un  enfermero {0}", idEnfermero);
        return entityEnf;
        
    }
 

}
