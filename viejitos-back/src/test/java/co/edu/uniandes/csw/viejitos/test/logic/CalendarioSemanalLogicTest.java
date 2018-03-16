/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.test.logic;

import co.edu.uniandes.csw.viejitos.ejb.CalendarioSemanalLogic;
import co.edu.uniandes.csw.viejitos.ejb.FranjaHorariaLogic;
import co.edu.uniandes.csw.viejitos.entities.CalendarioSemanalEntity;
import co.edu.uniandes.csw.viejitos.entities.FranjaHorariaEntity;
import co.edu.uniandes.csw.viejitos.persistence.CalendarioSemanalPersistence;
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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
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
public class CalendarioSemanalLogicTest {
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private CalendarioSemanalLogic calendarioLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<CalendarioSemanalEntity> data = new ArrayList<CalendarioSemanalEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CalendarioSemanalEntity.class.getPackage())
                .addPackage(CalendarioSemanalLogic.class.getPackage())
                .addPackage(CalendarioSemanalPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     *
     *
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
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

    /**
     * Limpia las tablas que están implicadas en la prueba.
     *
     *
     */
    private void clearData() {
                em.createQuery("delete from CalendarioSemanalEntity").executeUpdate();
        em.createQuery("delete from FranjaHorariaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {

        for (int i = 0; i < 3; i++) {
            CalendarioSemanalEntity entity = factory.manufacturePojo(CalendarioSemanalEntity.class);
            em.persist(entity);
            data.add(entity);
         
        }

    }
    
    
    public CalendarioSemanalLogicTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getCalendario method, of class CalendarioSemanalLogic.
     */
    @Test
    public void testGetCalendario() throws Exception {
   
      CalendarioSemanalEntity entity = data.get(0);
        CalendarioSemanalEntity resultEntity = calendarioLogic.getCalendario(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getUltimaModficacion(), resultEntity.getUltimaModficacion());
    
    }

    /**
     * Test of getCalendarios method, of class CalendarioSemanalLogic.
     */
    @Test
    public void testGetCalendarios() throws Exception {
      List<CalendarioSemanalEntity> list = calendarioLogic.getCalendarios();
        Assert.assertEquals(data.size(), list.size());
        for (CalendarioSemanalEntity entity : list) {
            boolean found = false;
            for (CalendarioSemanalEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }  
    }

    /**
     * Test of createCalendario method, of class CalendarioSemanalLogic.
     */
    @Test
    public void testCreateCalendario() throws Exception {
    
    CalendarioSemanalEntity newEntity = factory.manufacturePojo(CalendarioSemanalEntity.class);
        CalendarioSemanalEntity result = calendarioLogic.createCalendario(newEntity);
        Assert.assertNotNull(result);
        CalendarioSemanalEntity entity = em.find(CalendarioSemanalEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getUltimaModficacion(), entity.getUltimaModficacion());
    
    
    }

    /**
     * Test of updateCalendario method, of class CalendarioSemanalLogic.
     */
    @Test
    public void testUpdateCalendario() throws Exception {
    
      CalendarioSemanalEntity entity = data.get(0);
        CalendarioSemanalEntity pojoEntity = factory.manufacturePojo(CalendarioSemanalEntity.class);

        pojoEntity.setId(entity.getId());

        calendarioLogic.updateCalendario(pojoEntity);

        CalendarioSemanalEntity resp = em.find(CalendarioSemanalEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getUltimaModficacion(), resp.getUltimaModficacion());
    
    
    
    }

    /**
     * Test of listFranjas method, of class CalendarioSemanalLogic.
     */
    @Test
    public void testListFranjas() throws Exception {
    }

    /**
     * Test of getFranja method, of class CalendarioSemanalLogic.
     */
    @Test
    public void testGetFranja() throws Exception {
    }

    /**
     * Test of addFranja method, of class CalendarioSemanalLogic.
     */
    @Test
    public void testAddFranja() throws Exception {
    }

    /**
     * Test of removeFranja method, of class CalendarioSemanalLogic.
     */
    @Test
    public void testRemoveFranja() throws Exception {
    }

    /**
     * Test of deleteCalendario method, of class CalendarioSemanalLogic.
     */
    @Test
    public void testDeleteCalendario() throws Exception {
        
         CalendarioSemanalEntity entity = data.get(0);
        calendarioLogic.deleteCalendario(entity);
        CalendarioSemanalEntity deleted = em.find(CalendarioSemanalEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
}
