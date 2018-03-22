/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.test.logic;

import co.edu.uniandes.csw.viejitos.ejb.CitaLogic;
import co.edu.uniandes.csw.viejitos.ejb.MedicoLogic;
import co.edu.uniandes.csw.viejitos.entities.CitaEntity;
import co.edu.uniandes.csw.viejitos.entities.MedicoEntity;
import co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viejitos.persistence.CitaPersistence;
import co.edu.uniandes.csw.viejitos.persistence.MedicoPersistence;
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
 * @author l.pardo
 */
@RunWith(Arquillian.class)
public class CitaLogicTest {
      private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private CitaLogic citaLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<CitaEntity> data = new ArrayList<CitaEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CitaEntity.class.getPackage())
                .addPackage(CitaLogic.class.getPackage())
                .addPackage(CitaPersistence.class.getPackage())
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
        
        em.createQuery("delete from CitaEntity").executeUpdate();
        
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++)
        {
            CitaEntity entity = factory.manufacturePojo(CitaEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Prueba para crear un Servicio
     */
    @Test 
    public void createCitaTest() throws BusinessLogicException {
                PodamFactory factory = new PodamFactoryImpl();
        CitaEntity newEntity = factory.manufacturePojo(CitaEntity.class);
        CitaEntity result = citaLogic.create(newEntity);

        Assert.assertNotNull(result);

        CitaEntity entity = em.find(CitaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        //
    }
    
     /**
     * Prueba para consultar la lista de Servicios
     */
    @Test
    public void getCitasTest() {
        List<CitaEntity> list = citaLogic.getAll();
        Assert.assertEquals(data.size(), list.size());
        for (CitaEntity entity : list) {
            boolean found = false;
            for (CitaEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para consultar un Servicio
     */
    @Test
    public void getCitaTest() {
        CitaEntity entity = data.get(0);
        CitaEntity newEntity = citaLogic.getById(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }
    
    /**
     * Prueba para eliminar un Servicio
     */
    @Test
    public void deleteCitaTest() {
        CitaEntity entity = data.get(0);
        if (citaLogic.getById(entity.getId()) != null) {
            try {
                citaLogic.delete(entity);
            } catch (Exception e) {
                Assert.fail("No deberia lanzar excepcion");
            }
        } else {
            try {
                citaLogic.delete(entity);
                Assert.fail("Deberia lanzar excepcion");
            } catch (Exception e) {

            }
        }
        CitaEntity deleted = em.find( CitaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
     /**
     * Prueba para actualizar un Servicio
     */
    @Test
    public void updateCitaTest() {
        CitaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        CitaEntity newEntity = factory.manufacturePojo(CitaEntity.class);

        newEntity.setId(entity.getId());
        if(em.find(CitaEntity.class, entity.getId())==null)
        {
            try
            {
                citaLogic.update(newEntity);
                Assert.fail("Deberia lanzar excepcion");
            }
            catch(Exception e)
            {
                
            }
        }
        else
        {
            try
            {
                citaLogic.update(newEntity);
            }
            catch(Exception e)
            {
                Assert.fail("No deberia lanzar excepcion");
            }
            CitaEntity resp = em.find(CitaEntity.class, entity.getId());
         
            Assert.assertEquals(newEntity.getId(), resp.getId());
        }
    }   
}
