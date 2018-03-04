/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.test.persistence;

import co.edu.uniandes.csw.viejitos.entities.CalificacionEntity;
import co.edu.uniandes.csw.viejitos.persistence.CalificacionPersistence;
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
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author js.espitia
 */
@RunWith(Arquillian.class)
public class CalificacionPersistenceTest {
    
     /**
     * Variable para marcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;
    
    /**
    * lista que tiene los datos de prueba
    */
   private List<CalificacionEntity> data = new ArrayList<CalificacionEntity>();
    
   /**
    * Limpia las tablas que están implicadas en la prueba.
    *
    *
    */
   private void clearData() {
       em.createQuery("delete from EnfermeroEntity").executeUpdate();
   }

   /**
    * Inserta los datos iniciales para el correcto funcionamiento de las
    * pruebas.
    *
    *
    */
   private void insertData() {
       PodamFactory factory = new PodamFactoryImpl();
       for (int i = 0; i < 3; i++) {
           
           CalificacionEntity entity = factory.manufacturePojo(CalificacionEntity.class);

           em.persist(entity);
           
           data.add(entity);
       }
   }   
   
   @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp()
    {
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
    
    @After
    public void tearDown()
    {
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
    
    @Inject
    private CalificacionPersistence calificacionPersistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Deployment
    public static JavaArchive createDeployment(){
     return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CalificacionEntity.class.getPackage())
                .addPackage(CalificacionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Test
    public void createCalificacionPersistenceTest(){
        PodamFactory fact = new PodamFactoryImpl();
        CalificacionEntity entidad = fact.manufacturePojo(CalificacionEntity.class);
        CalificacionEntity creada = calificacionPersistence.create(entidad);
        
        Assert.assertNotNull(creada);
        CalificacionEntity e1 = calificacionPersistence.find(entidad.getId());
        Assert.assertNotNull(e1);
        Assert.assertEquals(e1, creada);
    }
    
    /**
     * Prueba del metodo para encontrar todas las instancias, de la clase EnfermeroPersistence.
     */
    @Test
    public void testFindAll() throws Exception 
    {
        List<CalificacionEntity> list = calificacionPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (CalificacionEntity ent : list)
        {
            boolean found = false;
            for (CalificacionEntity entity : data)
            {
                if (ent.getId().equals(entity.getId()))
                {
                    found = true;
                }
            }
        Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para el metodo find en la clase EnfermeroPersistence.
     */
    @Test
    public void testFind() throws Exception 
    {
        CalificacionEntity entity = data.get(0);
        CalificacionEntity newEntity = em.find(CalificacionEntity.class, entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    /**
     * Prueba para el metodo update de la clase EnfermeroPersistence.
     */
    @Test
    public void testUpdate() throws Exception
    {
        CalificacionEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        CalificacionEntity newEntity = factory.manufacturePojo(CalificacionEntity.class);

        newEntity.setId(entity.getId());

        calificacionPersistence.update(newEntity);

        CalificacionEntity resp = em.find(CalificacionEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }

    /**
     * Prueba para el metodo delete de la clase EnfermeroPersistence.
     */
    @Test
    public void testDelete() throws Exception 
    {
        CalificacionEntity entity = data.get(0);
        calificacionPersistence.delete(entity.getId());
        CalificacionEntity deleted = em.find(CalificacionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
