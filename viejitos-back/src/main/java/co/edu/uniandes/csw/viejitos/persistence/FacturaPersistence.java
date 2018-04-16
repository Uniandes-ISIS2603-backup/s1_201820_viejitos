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

    public FacturaEntity find(Long idServicio, Long idFactura) {
        LOGGER.log(Level.INFO, "Consultando factura con id={0}", idFactura);
        TypedQuery<FacturaEntity> q = em.createQuery("select p from QuejaEntity p where (p.servicio.id = :idServicio) and (p.id = :idFactura)", FacturaEntity.class);
        q.setParameter("idServicio", idServicio);
        q.setParameter("idFactura", idFactura);
        List<FacturaEntity> results = q.getResultList();
        FacturaEntity factura = null;
        if (results == null) {
            factura = null;
        } else if (results.isEmpty()) {
            factura = null;
        } else if (results.size() >= 1) {
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
