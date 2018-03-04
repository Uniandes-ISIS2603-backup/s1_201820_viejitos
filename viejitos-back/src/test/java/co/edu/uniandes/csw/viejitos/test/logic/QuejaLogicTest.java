/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.test.logic;

import co.edu.uniandes.csw.viejitos.ejb.QuejaLogic;
import co.edu.uniandes.csw.viejitos.entities.QuejaEntity;
import co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viejitos.persistence.QuejaPersistence;
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
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author c.gomezs
 */
@RunWith(Arquillian.class)
public class QuejaLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private QuejaLogic quejaLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<QuejaEntity> data = new ArrayList<QuejaEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(QuejaEntity.class.getPackage())
                .addPackage(QuejaLogic.class.getPackage())
                .addPackage(QuejaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Configuración inicial de la prueba.
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
     */
    private void clearData() {
        em.createQuery("delete from QuejaEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++)
        {
            QuejaEntity entity = factory.manufacturePojo(QuejaEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Prueba para crear una Queja
     */
    @Test
    public void createQuejaTest() throws BusinessLogicException {
        QuejaEntity newEntity = factory.manufacturePojo(QuejaEntity.class);
        if(newEntity.getCliente()==null)
        {
            try
            {
                QuejaEntity result = quejaLogic.create(newEntity);
                fail("Debería lanzar exception.");
            }
            catch(Exception e)
            {
                //Debería entrar acá.
            }
        }
        else
        {
            QuejaEntity result = quejaLogic.create(newEntity);
            Assert.assertNotNull(result);
            QuejaEntity entity = em.find(QuejaEntity.class, result.getId());
            Assert.assertEquals(newEntity.getId(), entity.getId());
            Assert.assertEquals(newEntity.getReclamo(), entity.getReclamo());
            Assert.assertEquals(newEntity.getResuelto(), entity.getResuelto());
            Assert.assertEquals(newEntity.getServicio(), entity.getServicio());
        }
    }
    
         /**
     * Prueba para consultar la lista de Quejas
     */
    @Test
    public void getQuejasTest() {
        List<QuejaEntity> list =quejaLogic.getAll();
        Assert.assertEquals(data.size(), list.size());
        for (QuejaEntity entity : list) {
            boolean found = false;
            for (QuejaEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para consultar una Queja
     */
    @Test
    public void getQuejaTest() {
        QuejaEntity entity = data.get(0);
        QuejaEntity resultEntity = quejaLogic.getById(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getReclamo(), resultEntity.getReclamo());
        Assert.assertEquals(entity.getResuelto(), resultEntity.getResuelto());
        Assert.assertEquals(entity.getServicio(), resultEntity.getServicio());
        Assert.assertEquals(entity.getCliente(), resultEntity.getCliente());
    }
    
     /**
     * Prueba para eliminar una Queja
     */
    @Test
    public void deleteQuejaTest() {
        QuejaEntity entity = data.get(0);
        quejaLogic.delete(entity);
        QuejaEntity deleted = em.find(QuejaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    /**
     * Prueba para actualizar una Queja
     */
    @Test
    public void updateQuejaTest() throws BusinessLogicException {
        QuejaEntity entity = data.get(0);
        QuejaEntity pojoEntity = factory.manufacturePojo(QuejaEntity.class);

        pojoEntity.setId(entity.getId());

        quejaLogic.update(pojoEntity);

        QuejaEntity resp = em.find(QuejaEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getReclamo(), resp.getReclamo());
        Assert.assertEquals(pojoEntity.getResuelto(), resp.getResuelto());
        Assert.assertEquals(pojoEntity.getServicio(), resp.getServicio());
        Assert.assertEquals(pojoEntity.getCliente(), resp.getCliente());
    }
}
