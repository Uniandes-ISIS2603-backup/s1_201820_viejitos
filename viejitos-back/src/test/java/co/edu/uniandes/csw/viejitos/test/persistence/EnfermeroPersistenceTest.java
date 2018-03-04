package co.edu.uniandes.csw.viejitos.test.persistence;

import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.viejitos.entities.EnfermeroEntity;
import co.edu.uniandes.csw.viejitos.persistence.EnfermeroPersistence;

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
 * @author JuanSebastian
 */
@RunWith(Arquillian.class)
public class EnfermeroPersistenceTest {
    
      /**
     * Variable para marcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;
    
    /**
    * lista que tiene los datos de prueba
    */
   private List<EnfermeroEntity> data = new ArrayList<EnfermeroEntity>();
    
   /**
    *
    * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
    * embebido. El jar contiene al descriptor de la
    * base de datos y el archivo beans.xml para resolver la inyección de
    * dependencias.
    */
   @Deployment
   public static JavaArchive createDeployment() {
       return ShrinkWrap.create(JavaArchive.class)
               .addPackage(EnfermeroEntity.class.getPackage())
               .addPackage(EnfermeroPersistence.class.getPackage())
               .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
               .addAsManifestResource("META-INF/beans.xml", "beans.xml");
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

   /**
    * Inyección de la dependencia a la clase EnfermeroPersistence cuyos métodos
    * se van a probar.
    */
   @Inject
   private EnfermeroPersistence enfermeroPersistence;

   /**
    * Contexto de Persistencia que se va a utilizar para acceder a la Base de
    * datos por fuera de los métodos que se están probando.
    */
   @PersistenceContext
   private EntityManager em;

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
           
           EnfermeroEntity entity = factory.manufacturePojo(EnfermeroEntity.class);

           em.persist(entity);
           
           data.add(entity);
       }
   }

   /**
    * Prueba para crear un Enfermero.    
    */
   @Test
   public void createEnfermeroTest() {
       PodamFactory factory = new PodamFactoryImpl();
       EnfermeroEntity newEntity = factory.manufacturePojo(EnfermeroEntity.class);
       EnfermeroEntity result = enfermeroPersistence.create(newEntity);

       Assert.assertNotNull(result);

       EnfermeroEntity entity = em.find(EnfermeroEntity.class, result.getId());

       Assert.assertEquals(newEntity.getName(), entity.getName());
   }
   
    /**
     * Prueba del metodo para encontrar todas las instancias, de la clase EnfermeroPersistence.
     */
    @Test
    public void testFindAll() throws Exception 
    {
        List<EnfermeroEntity> list = enfermeroPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (EnfermeroEntity ent : list)
        {
            boolean found = false;
            for (EnfermeroEntity entity : data)
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
        EnfermeroEntity entity = data.get(0);
        EnfermeroEntity newEntity = em.find(EnfermeroEntity.class, entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    /**
     * Prueba para el metodo update de la clase EnfermeroPersistence.
     */
    @Test
    public void testUpdate() throws Exception
    {
        EnfermeroEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        EnfermeroEntity newEntity = factory.manufacturePojo(EnfermeroEntity.class);

        newEntity.setId(entity.getId());

        enfermeroPersistence.update(newEntity);

        EnfermeroEntity resp = em.find(EnfermeroEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }

    /**
     * Prueba para el metodo delete de la clase EnfermeroPersistence.
     */
    @Test
    public void testDelete() throws Exception 
    {
        EnfermeroEntity entity = data.get(0);
        enfermeroPersistence.delete(entity.getId());
        EnfermeroEntity deleted = em.find(EnfermeroEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
}
