/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.test.logic;

import co.edu.uniandes.csw.viejitos.ejb.MedicoLogic;
import co.edu.uniandes.csw.viejitos.entities.MedicoEntity;
import co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException;
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
public class MedicoLogicTest {
     private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private MedicoLogic medicoLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<MedicoEntity> data = new ArrayList<MedicoEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MedicoEntity.class.getPackage())
                .addPackage(MedicoLogic.class.getPackage())
                .addPackage(MedicoPersistence.class.getPackage())
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
        em.createQuery("delete from MedicoEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++)
        {
            MedicoEntity entity = factory.manufacturePojo(MedicoEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Prueba para crear un Servicio
     */
    @Test 
    public void createMedicoTest() throws BusinessLogicException {
               PodamFactory factory = new PodamFactoryImpl();
        MedicoEntity newEntity = factory.manufacturePojo(MedicoEntity.class);
        MedicoEntity result = medicoLogic.create(newEntity);

        Assert.assertNotNull(result);

        MedicoEntity entity = em.find(MedicoEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        
    }
    
     /**
     * Prueba para consultar la lista de Servicios
     */
    @Test
    public void getMedicosTest() {
        List<MedicoEntity> list = medicoLogic.getAll();
        Assert.assertEquals(data.size(), list.size());
        for (MedicoEntity entity : list) {
            boolean found = false;
            for (MedicoEntity storedEntity : data) {
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
    public void getMedicoTest() {
        MedicoEntity entity = data.get(0);
        MedicoEntity newEntity = medicoLogic.getById(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }
    
    /**
     * Prueba para eliminar un Servicio
     */
    @Test
    public void deleteMedicoTest() {
        MedicoEntity entity = data.get(0);
        medicoLogic.delete(entity);
        MedicoEntity deleted = em.find( MedicoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
     /**
     * Prueba para actualizar un Servicio
     */
    @Test
    public void updateMedicoTest() {

        MedicoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        MedicoEntity newEntity = factory.manufacturePojo(MedicoEntity.class);

        newEntity.setId(entity.getId());
        if(em.find(MedicoEntity.class, entity.getId())==null)
        {
            try
            {
                medicoLogic.update(newEntity);
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
                medicoLogic.update(newEntity);
            }
            catch(Exception e)
            {
                Assert.fail("No deberia lanzar excepcion");
            }
            MedicoEntity resp = em.find(MedicoEntity.class, entity.getId());
         
            Assert.assertEquals(newEntity.getId(), resp.getId());
        }
    }   
    


}
