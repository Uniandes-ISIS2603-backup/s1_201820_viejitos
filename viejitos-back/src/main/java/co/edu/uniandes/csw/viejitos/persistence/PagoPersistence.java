/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.persistence;

import co.edu.uniandes.csw.viejitos.entities.PagoEntity;
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
 * @author f.escobar
 */
@Stateless
public class PagoPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(PagoPersistence.class.getName());

    @PersistenceContext(unitName = "ViejitosPU")
    protected EntityManager em;

    public PagoEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando pago con id={0}", id);
        return em.find(PagoEntity.class, id);
    }

    public PagoEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando pago con name= ", name);
        TypedQuery<PagoEntity> q
                = em.createQuery("select u from PagoEntity u where u.name = :name", PagoEntity.class);
        q = q.setParameter("name", name);
        return q.getSingleResult();
    }

    public List<PagoEntity> findAll() {
        LOGGER.info("Consultando todos los pagos");
        Query q = em.createQuery("select u from PagoEntity u");
        return q.getResultList();
    }

    public PagoEntity create(PagoEntity entity) {
        LOGGER.info("Creando un pago nuevo");
        em.persist(entity);
        LOGGER.info("Pago creado");
        return entity;
    }

    public PagoEntity update(PagoEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando pago con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando Pago con id={0}", id);
        PagoEntity entity = em.find(PagoEntity.class, id);
        em.remove(entity);
    }
}
