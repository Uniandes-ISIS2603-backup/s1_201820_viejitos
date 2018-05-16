/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.csw.viejitos.persistence;

import co.edu.uniandes.csw.viejitos.entities.CitaEntity;
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
 * @author l.pardo
 */
@Stateless
public class CitaPersistence {

    private static final Logger LOGGER = Logger.getLogger(CitaPersistence.class.getName());
    @PersistenceContext(unitName = "ViejitosPU")
    protected EntityManager em;

    public CitaEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando cita con id={0}", id);
        return em.find(CitaEntity.class, id);
    }

    public List<CitaEntity> findAll() {
        LOGGER.info("Consultando todos las Citas");
        Query q = em.createQuery("select u from CitaEntity u");
        return q.getResultList();
    }

    public CitaEntity create(CitaEntity entity) {
        LOGGER.info("Creando una cita nuevo");
        em.persist(entity);
        LOGGER.info("Cita creada");
        return entity;
    }

    public CitaEntity update(CitaEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando cita con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando cita con id={0}", id);
        CitaEntity entity = em.find(CitaEntity.class, id);
        em.remove(entity);
    }
}
