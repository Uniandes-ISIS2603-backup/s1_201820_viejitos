/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.test.logic;

import co.edu.uniandes.csw.viejitos.ejb.FranjaHorariaLogic;
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
public class FranjaHorariaLogicTest {
   
     private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private FranjaHorariaLogic franjaHorariaLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<FranjaHorariaEntity> data = new ArrayList<FranjaHorariaEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FranjaHorariaEntity.class.getPackage())
                .addPackage(FranjaHorariaLogic.class.getPackage())
                .addPackage(FranjaHorariaPersistence.class.getPackage())
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
        em.createQuery("delete from FranjaHorariaEntity").executeUpdate();
        em.createQuery("delete from CalendarioSemanalEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {

        for (int i = 0; i < 3; i++) {
            FranjaHorariaEntity entity = factory.manufacturePojo(FranjaHorariaEntity.class);
            em.persist(entity);
            data.add(entity);
         
        }

    }
    
    
    public FranjaHorariaLogicTest() {
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
     * Test of getFranjas method, of class FranjaHorariaLogic.
     */
    @Test
    public void testGetFranjas() throws Exception {
    List<FranjaHorariaEntity> list = franjaHorariaLogic.getFranjas();
        Assert.assertEquals(data.size(), list.size());
        for (FranjaHorariaEntity entity : list) {
            boolean found = false;
            for (FranjaHorariaEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }    
        
    }

    /**
     * Test of getFranja method, of class FranjaHorariaLogic.
     */
    @Test
    public void testGetFranja() throws Exception {
     FranjaHorariaEntity entity = data.get(0);
        FranjaHorariaEntity resultEntity = franjaHorariaLogic.getFranja(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
      Assert.assertEquals(entity.getHoraInicio(), resultEntity.getHoraInicio());
          Assert.assertEquals(entity.getHoraFin(), resultEntity.getHoraFin());
         Assert.assertEquals(entity.isOcupado(), resultEntity.isOcupado());
          Assert.assertEquals(entity.getDiaSemana(), resultEntity.getDiaSemana());
    }

    /**
     * Test of create method, of class FranjaHorariaLogic.
     */
    @Test
    public void testCreate() throws Exception {
         FranjaHorariaEntity newEntity = factory.manufacturePojo(FranjaHorariaEntity.class);
        FranjaHorariaEntity result = franjaHorariaLogic.create(newEntity);
        Assert.assertNotNull(result);
        FranjaHorariaEntity entity = em.find(FranjaHorariaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getHoraInicio(), entity.getHoraInicio());
          Assert.assertEquals(newEntity.getHoraFin(), entity.getHoraFin());
         Assert.assertEquals(newEntity.isOcupado(), entity.isOcupado());
          Assert.assertEquals(newEntity.getDiaSemana(), entity.getDiaSemana());
       
    }

    /**
     * Test of updateFranja method, of class FranjaHorariaLogic.
     */
    @Test
    public void testUpdateFranja() throws Exception {
    
   FranjaHorariaEntity entity = data.get(0);
   FranjaHorariaEntity pojoEntity = factory.manufacturePojo(FranjaHorariaEntity.class);

        pojoEntity.setId(entity.getId());

        franjaHorariaLogic.updateFranja(pojoEntity);

        FranjaHorariaEntity resp = em.find(FranjaHorariaEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
           Assert.assertEquals(pojoEntity.getHoraInicio(), resp.getHoraInicio());
          Assert.assertEquals(pojoEntity.getHoraFin(), resp.getHoraFin());
         Assert.assertEquals(pojoEntity.isOcupado(), resp.isOcupado());
          Assert.assertEquals(pojoEntity.getDiaSemana(), resp.getDiaSemana());
    }
    
    

    /**
     * Test of deleteFranja method, of class FranjaHorariaLogic.
     */
    @Test
    public void testDeleteFranja() throws Exception {
        
          FranjaHorariaEntity entity = data.get(0);
        franjaHorariaLogic.deleteFranja(entity.getId());
        FranjaHorariaEntity deleted = em.find(FranjaHorariaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
}
