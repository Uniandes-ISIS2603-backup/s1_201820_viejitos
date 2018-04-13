/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.persistence;

import co.edu.uniandes.csw.viejitos.entities.QuejaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author c.gomezs
 */

@Stateless
public class QuejaPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(QuejaPersistence.class.getName());

    @PersistenceContext(unitName = "ViejitosPU")
    protected EntityManager em;

    public QuejaEntity find(Long servicioid, Long quejaid) {
        LOGGER.log(Level.INFO, "Consultando queja con id={0}", quejaid);
        TypedQuery<QuejaEntity> q = em.createQuery("select p from QuejaEntity p where (p.servicio.id = :servicioid) and (p.id = :quejaid)", QuejaEntity.class);
        q.setParameter("servicioid", servicioid);
        q.setParameter("quejaid", quejaid);
        List<QuejaEntity> results = q.getResultList();
        QuejaEntity queja = null;
        if (results == null) {
            queja = null;
        } else if (results.isEmpty()) {
            queja = null;
        } else if (results.size() >= 1) {
            queja = results.get(0);
        }
        return queja;
    }

    public QuejaEntity create(QuejaEntity entity) {
        LOGGER.info("Creando una queja nueva");
        em.persist(entity);
        LOGGER.info("Queja creada");
        return entity;
    }

    public QuejaEntity update(QuejaEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando queja con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando queja con id={0}", id);
        QuejaEntity entity = em.find(QuejaEntity.class, id);
        em.remove(entity);
    }
    
}
