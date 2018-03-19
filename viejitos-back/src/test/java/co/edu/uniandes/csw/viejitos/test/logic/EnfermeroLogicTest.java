/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.test.logic;

import co.edu.uniandes.csw.viejitos.ejb.EnfermeroLogic;
import co.edu.uniandes.csw.viejitos.entities.EnfermeroEntity;
import co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException;
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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author js.espitia
 */
@RunWith(Arquillian.class)
public class EnfermeroLogicTest {
    
    PodamFactoryImpl fact = new PodamFactoryImpl();
    
    @Inject
    EnfermeroLogic logic;
    
    @PersistenceContext
    EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    List<EnfermeroEntity> data = new ArrayList<>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EnfermeroEntity.class.getPackage())
                .addPackage(EnfermeroLogic.class.getPackage())
                .addPackage(EnfermeroPersistence.class.getPackage())
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
        em.createQuery("delete from EnfermeroEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++)
        {
            EnfermeroEntity entity = fact.manufacturePojo(EnfermeroEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
     /**
     * Prueba para crear un enfermero
     * @throws co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException
     */
    @Test
    public void createEnfermeroTest() throws BusinessLogicException{
        EnfermeroEntity nueva = fact.manufacturePojo(EnfermeroEntity.class);
        EnfermeroEntity result = logic.create(nueva);
        Assert.assertNotNull(result);
        EnfermeroEntity entity = em.find(EnfermeroEntity.class, result.getId());
        Assert.assertEquals(nueva.getId(), entity.getId());
        Assert.assertEquals(nueva.getCV(), entity.getCV());
        Assert.assertEquals(nueva.getLogin(), entity.getLogin());
        Assert.assertEquals(nueva.getContrasenia(), entity.getContrasenia());
    }
    
    /**
     * Prueba para consultar la lista de Enfermeros
     */
    @Test
    public void getEnfermerosTest() {
        List<EnfermeroEntity> list = logic.getAll();
        Assert.assertEquals(data.size(), list.size());
        for (EnfermeroEntity entity : list) {
            boolean found = false;
            for (EnfermeroEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                    break;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para consultar un Enfermeros
     */
    @Test
    public void getEnfermeroTest() {
        EnfermeroEntity entity = data.get(0);
        EnfermeroEntity resultEntity = logic.getById(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getCV(), resultEntity.getCV());
        Assert.assertEquals(entity.getLogin(), resultEntity.getLogin());
        Assert.assertEquals(entity.getContrasenia(), resultEntity.getContrasenia());
    }
    /*
    * Prueba para actualizar un enfermero
    */
    @Test
    public void updateEnfermeroTest()throws BusinessLogicException {
        EnfermeroEntity entity = data.get(0);
        EnfermeroEntity pojoEntity = fact.manufacturePojo(EnfermeroEntity.class);

        pojoEntity.setId(entity.getId());

        logic.update(pojoEntity);

        EnfermeroEntity resp = em.find(EnfermeroEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getCV(), resp.getCV());
        Assert.assertEquals(pojoEntity.getLogin(), resp.getLogin());
        Assert.assertEquals(pojoEntity.getContrasenia(), resp.getContrasenia());
    }
    
    /**
     * Prueba para eliminar un enfermero
     */
    @Test
    public void deleteEnfermeroTest() throws BusinessLogicException{
        EnfermeroEntity entity = data.get(0);
        logic.delete(entity.getId());
        EnfermeroEntity deleted = em.find(EnfermeroEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
