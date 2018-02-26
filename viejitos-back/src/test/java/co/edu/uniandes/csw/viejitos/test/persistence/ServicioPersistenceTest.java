/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.test.persistence;
import co.edu.uniandes.csw.viejitos.entities.ServicioEntity;
import co.edu.uniandes.csw.viejitos.persistence.ServicioPersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.Assert;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author c.gomezs
 */
public class ServicioPersistenceTest {
    
    /**
     * Contexto de Persistencia que se va a utilizar para acceder a la Base de
     * datos por fuera de los métodos que se están probando.
     */
    @PersistenceContext
    private EntityManager em;
    
    /**
     * Inyección de la dependencia a la clase ServicioPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private ServicioPersistence servicioPersistence;
    
    @Test
    public void createServicioEntityTest() {
    PodamFactory factory = new PodamFactoryImpl();
    ServicioEntity newEntity = factory.manufacturePojo(ServicioEntity.class);
    ServicioEntity result = servicioPersistence.create(newEntity);

    Assert.assertNotNull(result);
    ServicioEntity entity = em.find(ServicioEntity.class, result.getId());
    Assert.assertNotNull(entity);
    Assert.assertEquals(newEntity.getName(), entity.getName());
    }
}
