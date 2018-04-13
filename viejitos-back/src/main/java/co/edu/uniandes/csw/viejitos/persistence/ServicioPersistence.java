/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.persistence;

import co.edu.uniandes.csw.viejitos.entities.ServicioEntity;
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
 * @author c.gomezs
 */

@Stateless
public class ServicioPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(ServicioPersistence.class.getName());

    @PersistenceContext(unitName = "ViejitosPU")
    protected EntityManager em;

    public ServicioEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando servicio con id={0}", id);
        return em.find(ServicioEntity.class, id);
    }

    public List<ServicioEntity> findAll() {
        LOGGER.info("Consultando todos los servicios");
        Query q = em.createQuery("select u from ServicioEntity u");
        return q.getResultList();
    }

    public ServicioEntity create(ServicioEntity entity) {
        LOGGER.info("Creando un servicio nuevo");
        em.persist(entity);
        LOGGER.info("Servicio creado");
        return entity;
    }

    public ServicioEntity update(ServicioEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando servicio con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando servicio con id={0}", id);
        ServicioEntity entity = em.find(ServicioEntity.class, id);
        em.remove(entity);
    }
    
}
