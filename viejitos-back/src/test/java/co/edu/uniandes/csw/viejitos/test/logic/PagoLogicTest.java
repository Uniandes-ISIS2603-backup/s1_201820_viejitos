/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.test.logic;

import co.edu.uniandes.csw.viejitos.ejb.PagoLogic;
import co.edu.uniandes.csw.viejitos.entities.PagoEntity;
import co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viejitos.persistence.PagoPersistence;
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
 * @author f.escobar
 */
@RunWith(Arquillian.class)
public class PagoLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private PagoLogic pagoLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<PagoEntity> data = new ArrayList<PagoEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PagoEntity.class.getPackage())
                .addPackage(PagoLogic.class.getPackage())
                .addPackage(PagoPersistence.class.getPackage())
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
     */
    private void clearData() {
        em.createQuery("delete from PagoEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++)
        {
            PagoEntity entity = factory.manufacturePojo(PagoEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Prueba para crear un Pago
     */
    @Test
    public void createPagoTest() throws BusinessLogicException {
        PagoEntity newEntity = factory.manufacturePojo(PagoEntity.class);
        if( newEntity.getServicio() == null)
        {
            try{
                PagoEntity result = pagoLogic.create(newEntity);
                fail("Debería lanzar excepción");
            }
            catch(Exception e)
            {
                //Debería entrar acá
            }
        }
        else
        {
            PagoEntity result = pagoLogic.create(newEntity);
            Assert.assertNotNull(result);
            PagoEntity entity = em.find(PagoEntity.class, result.getId());
            Assert.assertEquals(newEntity.getId(), entity.getId());
            Assert.assertEquals(newEntity.getMedio(), entity.getMedio());
            Assert.assertEquals(newEntity.getPagado(), entity.getPagado());
            Assert.assertEquals(newEntity.getFechaLimite(), entity.getFechaLimite());
            Assert.assertEquals(newEntity.getValor(), entity.getValor());
            Assert.assertEquals(newEntity.getServicio(), entity.getServicio());
        }
    }
    
     /**
     * Prueba para consultar la lista de Pagos
     */
    @Test
    public void getPagosTest() {
        List<PagoEntity> list = pagoLogic.getAll();
        Assert.assertEquals(data.size(), list.size());
        for (PagoEntity entity : list) {
            boolean found = false;
            for (PagoEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para consultar un Pago
     */
    @Test
    public void getPagoTest() {
        PagoEntity entity = data.get(0);
        PagoEntity resultEntity = pagoLogic.getById(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getMedio(), resultEntity.getMedio());
        Assert.assertEquals(entity.getPagado(), resultEntity.getPagado());
        Assert.assertEquals(entity.getValor(), resultEntity.getValor());
        Assert.assertEquals(entity.getFechaLimite(), resultEntity.getFechaLimite());
        Assert.assertEquals(entity.getServicio(), resultEntity.getServicio());
    }
    
    /**
     * Prueba para eliminar un Pago
     */
    @Test
    public void deletePagoTest() {
        PagoEntity entity = data.get(0);
        pagoLogic.delete(entity);
        PagoEntity deleted = em.find(PagoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
     /**
     * Prueba para actualizar un Pago
     */
    @Test
    public void updatePagoTest() throws BusinessLogicException {
        PagoEntity entity = data.get(0);
        PagoEntity pojoEntity = factory.manufacturePojo(PagoEntity.class);

        pojoEntity.setId(entity.getId());

        if( pojoEntity.getServicio() == null)
        {
            try{
                pagoLogic.update(pojoEntity);
                fail("Debería lanzar excepción");
            }
            catch(Exception e)
            {
                //Debería entrar acá
            }
        }
        else
        {
            pagoLogic.update(pojoEntity);

            PagoEntity resp = em.find(PagoEntity.class, entity.getId());

            Assert.assertEquals(pojoEntity.getId(), resp.getId());
            Assert.assertEquals(pojoEntity.getMedio(), resp.getMedio());
            Assert.assertEquals(pojoEntity.getPagado(), resp.getPagado());
            Assert.assertEquals(pojoEntity.getValor(), resp.getValor());
            Assert.assertEquals(pojoEntity.getFechaLimite(), resp.getFechaLimite());
            Assert.assertEquals(pojoEntity.getServicio(), resp.getServicio());
        }
    }
    
}
