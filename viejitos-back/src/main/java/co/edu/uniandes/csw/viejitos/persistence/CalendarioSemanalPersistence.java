/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.persistence;

import co.edu.uniandes.csw.viejitos.entities.CalendarioSemanalEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author lf.naranjo11
 */

@Stateless
public class CalendarioSemanalPersistence {
    
    
    private static final Logger LOGGER= Logger.getLogger(CalendarioSemanalPersistence.class.getName());
      
    @PersistenceContext(unitName = "ViejitosPU")
    protected EntityManager em;
      
      public CalendarioSemanalEntity create(CalendarioSemanalEntity entity)
      {  
         LOGGER.info("creando calendario semanal");
         em.persist(entity);
        LOGGER.info("calendario semanal creado");
        return entity;
       }
      
       public CalendarioSemanalEntity update(CalendarioSemanalEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando calendario Semanal con id={0}", entity.getId());
        return em.merge(entity);
    }

  
      
       public CalendarioSemanalEntity find(Long id)
      {   
       LOGGER.log(Level.INFO, "Consultando calendario con id={0}", id);
        return em.find(CalendarioSemanalEntity.class, id);  
       }
      
          public List<CalendarioSemanalEntity> findAll()
      {   
         LOGGER.info("buscando todas las franjas horarias");
         Query q=em.createQuery("select u from CalendarioSemanalEntity u",CalendarioSemanalEntity.class);
         return q.getResultList();
       }
          
            public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando calendario Semanal con id={0}", id);
        CalendarioSemanalEntity entity = em.find(CalendarioSemanalEntity.class, id);
        em.remove(entity);
    }
                
}
