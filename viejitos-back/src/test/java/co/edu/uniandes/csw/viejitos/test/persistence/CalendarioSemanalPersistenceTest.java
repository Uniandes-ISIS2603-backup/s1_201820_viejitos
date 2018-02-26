/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.test.persistence;

import co.edu.uniandes.csw.viejitos.entities.CalendarioSemanalEntity;
import co.edu.uniandes.csw.viejitos.persistence.CalendarioSemanalPersistence;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;


import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.AfterClass;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author lf.naranjo11
 */
@RunWith(Arquillian.class)
public class CalendarioSemanalPersistenceTest {
    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de CalendarioSemanal, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
     public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CalendarioSemanalEntity.class.getPackage())
                .addPackage(CalendarioSemanalPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
   /**
     * Inyección de la dependencia a la clase CalendarioSemanalPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private CalendarioSemanalPersistence persistence;

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
    private List<CalendarioSemanalEntity> data = new ArrayList<CalendarioSemanalEntity>();
    
    
    public CalendarioSemanalPersistenceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
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
        em.createQuery("delete from CalendarioSemanalEntity").executeUpdate();
    }


 private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            CalendarioSemanalEntity entity = factory.manufacturePojo(CalendarioSemanalEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    
    @After
    public void tearDown() {
    }

   

    /**
     * Test of update method, of class CalendarioSemanalPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
        CalendarioSemanalEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        CalendarioSemanalEntity newEntity = factory.manufacturePojo(CalendarioSemanalEntity.class);

        newEntity.setId(entity.getId());

       persistence.update(newEntity);

       CalendarioSemanalEntity resp = em.find(CalendarioSemanalEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }

    /**
     * Test of delete method, of class CalendarioSemanalPersistence.
     */
    @Test
    public void testDelete() throws Exception {
    CalendarioSemanalEntity entity = data.get(0);
        persistence.delete(entity.getId());
        CalendarioSemanalEntity deleted = em.find(CalendarioSemanalEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Test of find method, of class CalendarioSemanalPersistence.
     */
    @Test
    public void testFind() throws Exception {
       
        CalendarioSemanalEntity entity = data.get(0);
        CalendarioSemanalEntity newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }
    
    /**
     * Test of create method, of class CalendarioSemanalPersistence.
     */
    @Test
    public void testCreateCalendarioSemanalEntityTest() throws Exception {
       PodamFactory factory = new PodamFactoryImpl();
    CalendarioSemanalEntity newEntity = factory.manufacturePojo(CalendarioSemanalEntity.class);
    CalendarioSemanalEntity result = persistence.create(newEntity);

    Assert.assertNotNull(result);
    CalendarioSemanalEntity entity = em.find(CalendarioSemanalEntity.class, result.getId());
    Assert.assertNotNull(entity);
    Assert.assertEquals(newEntity.getName(), entity.getName());
    }
    
}
