/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.persistence;

import co.edu.uniandes.csw.viejitos.entities.FranjaHorariaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author lf.naranjo11
 */
@Stateless
public class FranjaHorariaPersistence {
    
      @PersistenceContext(unitName = "ViejitosPU")
    protected EntityManager em;
      
      
      private static final Logger LOGGER = Logger.getLogger(FranjaHorariaPersistence.class.getName());
      
      public FranjaHorariaEntity create(FranjaHorariaEntity entity)
     {
          LOGGER.info("creando franja horaria");
          em.persist(entity);
           LOGGER.info("Franja horaria creado");
          return entity;
      }
      
      
      public FranjaHorariaEntity find(Long id)
      {
       LOGGER.log(Level.INFO, "Consultando franja con id={0}", id);
        return em.find(FranjaHorariaEntity.class, id);   
      }
      
      public List<FranjaHorariaEntity> findAll()
      {
          LOGGER.info("buscando todas las franjas horarias");
         Query q=em.createQuery("select u from FranjaHorariaEntity u",FranjaHorariaEntity.class);
         return q.getResultList();
      }
      
      public List<FranjaHorariaEntity> findByDay(String dia)
      {
          LOGGER.log(Level.INFO,"buscando franjas del dia=",dia);
          
          TypedQuery<FranjaHorariaEntity> q = em.createQuery("select u from FranjaHorariaEntity where u.getDiaSemana = :dia",FranjaHorariaEntity.class);
     // q = q.setParameter("diaSemana", dia);     
     return q.getResultList();
    
      }
    public FranjaHorariaEntity update(FranjaHorariaEntity entity)
    {
    LOGGER.log(Level.INFO,"actualizando franja con id={0}",entity.getId());
    return em.merge(entity);
  
    }
    
    public void delete(Long id)
    {
        LOGGER.log(Level.INFO,"Borrando franja con id={0}",id);
        FranjaHorariaEntity entity=em.find(FranjaHorariaEntity.class, id);
        em.remove(entity);
    }
    
}
