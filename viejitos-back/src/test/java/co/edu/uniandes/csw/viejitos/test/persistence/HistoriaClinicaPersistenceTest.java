/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.test.persistence;

import co.edu.uniandes.csw.viejitos.entities.HistoriaClinicaEntity;
import co.edu.uniandes.csw.viejitos.persistence.HistoriaClinicaPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author jj.silva
 */
@RunWith(Arquillian.class)
public class HistoriaClinicaPersistenceTest {
    
    public HistoriaClinicaPersistenceTest() {
    }
    
    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de HistoriaClinica, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(HistoriaClinicaEntity.class.getPackage())
                .addPackage(HistoriaClinicaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Inyección de la dependencia a la clase HistoriaClinicaPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private HistoriaClinicaPersistence persistence;

    /**
     * Contexto de Persistencia que se va a utilizar para acceder a la Base de
     * datos por fuera de los métodos que se están probando.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Variable para marcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;

    private List<HistoriaClinicaEntity> data = new ArrayList<HistoriaClinicaEntity>();
    
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
    
    private void clearData() {
        em.createQuery("delete from HistoriaClinicaEntity").executeUpdate();
    }


    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            HistoriaClinicaEntity entity = factory.manufacturePojo(HistoriaClinicaEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class HistoriaClinicaPersistence.
     */
    @Test
    public void testCreate() throws Exception
    {
        PodamFactory factory = new PodamFactoryImpl();
        HistoriaClinicaEntity newEntity = factory.manufacturePojo(HistoriaClinicaEntity.class);
        HistoriaClinicaEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);
        HistoriaClinicaEntity entity = em.find(HistoriaClinicaEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Test of findAll method, of class HistoriaClinicaPersistence.
     */
    @Test
    public void testFindAll() throws Exception
    {
        List<HistoriaClinicaEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (HistoriaClinicaEntity ent : list)
        {
            boolean found = false;
            for (HistoriaClinicaEntity entity : data)
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
     * Test of find method, of class HistoriaClinicaPersistence.
     */
    @Test
    public void testFind() throws Exception
    {
        HistoriaClinicaEntity entity = data.get(0);
        HistoriaClinicaEntity newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    /**
     * Test of update method, of class HistoriaClinicaPersistence.
     */
    @Test
    public void testUpdate() throws Exception
    {
        HistoriaClinicaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        HistoriaClinicaEntity newEntity = factory.manufacturePojo(HistoriaClinicaEntity.class);

        newEntity.setId(entity.getId());

        persistence.update(newEntity);

        HistoriaClinicaEntity resp = em.find(HistoriaClinicaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }

    /**
     * Test of delete method, of class HistoriaClinicaPersistence.
     */
    @Test
    public void testDelete() throws Exception
    {
        HistoriaClinicaEntity entity = data.get(0);
        persistence.delete(entity.getId());
        HistoriaClinicaEntity deleted = em.find(HistoriaClinicaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
}
