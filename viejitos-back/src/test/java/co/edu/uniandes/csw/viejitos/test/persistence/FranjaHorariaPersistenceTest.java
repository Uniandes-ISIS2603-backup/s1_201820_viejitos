/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.test.persistence;

import co.edu.uniandes.csw.viejitos.entities.FranjaHorariaEntity;
import co.edu.uniandes.csw.viejitos.persistence.FranjaHorariaPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author lf.naranjo11
 */
@RunWith(Arquillian.class)
public class FranjaHorariaPersistenceTest 
{
    
     /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Editorial, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FranjaHorariaEntity.class.getPackage())
                .addPackage(FranjaHorariaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Inyección de la dependencia a la clase FranjaHorariaPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private FranjaHorariaPersistence franjaPersistence;
    
    /**
     * Contexto de Persistencia que se va a utilizar para acceder a la Base de
     * datos por fuera de los métodos que se están probando.
     */
    @PersistenceContext
    private EntityManager em;
    
    
    /**
     * Variable para martcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;

    
    
    /**
     *coleccion de calendarios semanales
     */
    private List<FranjaHorariaEntity> data = new ArrayList<FranjaHorariaEntity>();
    
    
     /**
     * Prueba para crear una franja.
     *
     *
     */
    @Test
    public void createFranjaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        FranjaHorariaEntity newEntity = factory.manufacturePojo(FranjaHorariaEntity.class);
        FranjaHorariaEntity result = franjaPersistence.create(newEntity);
        

        Assert.assertNotNull(result);

      FranjaHorariaEntity entity = em.find(FranjaHorariaEntity.class, result.getId());

       Assert.assertEquals(newEntity.getName(), entity.getName());
    }
    
    @Before
    public void setUp() {
    try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    
    private void clearData() {
        em.createQuery("delete from FranjaHorariaEntity").executeUpdate();
    }


 private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            FranjaHorariaEntity entity = factory.manufacturePojo(FranjaHorariaEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
 
  /**
     * Test of update method, of class CalendarioSemanalPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
        FranjaHorariaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        FranjaHorariaEntity newEntity = factory.manufacturePojo(FranjaHorariaEntity.class);

        newEntity.setId(entity.getId());

       franjaPersistence.update(newEntity);

       FranjaHorariaEntity resp = em.find(FranjaHorariaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
    
     /**
     * Test of delete method, of class CalendarioSemanalPersistence.
     */
    @Test
    public void testDelete() throws Exception {
    FranjaHorariaEntity entity = data.get(0);
        franjaPersistence.delete(entity.getId());
        FranjaHorariaEntity deleted = em.find(FranjaHorariaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
  /**
     * Test of find method, of class CalendarioSemanalPersistence.
     */
    @Test
    public void testFind() throws Exception {
       
        FranjaHorariaEntity entity = data.get(0);
        FranjaHorariaEntity newEntity = franjaPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }
       
    
}
