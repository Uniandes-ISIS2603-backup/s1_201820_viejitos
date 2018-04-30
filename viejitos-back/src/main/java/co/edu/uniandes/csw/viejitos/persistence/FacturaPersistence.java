/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.persistence;

import co.edu.uniandes.csw.viejitos.entities.FacturaEntity;
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
public class FacturaPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(FacturaPersistence.class.getName());

    @PersistenceContext(unitName = "ViejitosPU")
    protected EntityManager em;

    public FacturaEntity find(Long servicioid, Long facturaid) {
        LOGGER.log(Level.INFO, "Consultando factura con id={0}", facturaid);
        System.out.println(servicioid);
        TypedQuery<FacturaEntity> q = em.createQuery("select p from FacturaEntity p where (p.servicio.id = :servicioid) and (p.id = :facturaid)", FacturaEntity.class);
        q.setParameter("servicioid", servicioid);
        q.setParameter("facturaid", facturaid);
        List<FacturaEntity> results = q.getResultList();
        FacturaEntity factura = null;
        if (results == null) {
            System.out.println("pasa");
            factura = null;
        } else if (results.isEmpty()) {
            System.out.println("pasa2");
            factura = null;
        } else if (results.size() >= 1) {
            System.out.println("pasa3");
            factura = results.get(0);
        }
        return factura;
    }

    public List<FacturaEntity> findAll() {
        LOGGER.info("Consultando todos las facturas");
        Query q = em.createQuery("select u from FacturaEntity u");
        return q.getResultList();
    }

    public FacturaEntity create(FacturaEntity entity) {
        LOGGER.info("Creando una factura nueva");
        em.persist(entity);
        LOGGER.info("Factura creada");
        return entity;
    }

    public FacturaEntity update(FacturaEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando factura con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando factura con id={0}", id);
        FacturaEntity entity = em.find(FacturaEntity.class, id);
        em.remove(entity);
    }
}
