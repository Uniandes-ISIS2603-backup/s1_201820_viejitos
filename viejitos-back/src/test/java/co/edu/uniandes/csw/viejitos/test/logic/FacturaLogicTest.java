/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.test.logic;

import co.edu.uniandes.csw.viejitos.ejb.FacturaLogic;
import co.edu.uniandes.csw.viejitos.entities.FacturaEntity;
import co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viejitos.persistence.FacturaPersistence;
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
public class FacturaLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private FacturaLogic facturaLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<FacturaEntity> data = new ArrayList<FacturaEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FacturaEntity.class.getPackage())
                .addPackage(FacturaLogic.class.getPackage())
                .addPackage(FacturaPersistence.class.getPackage())
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
        em.createQuery("delete from FacturaEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++)
        {
            FacturaEntity entity = factory.manufacturePojo(FacturaEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Prueba para crear una Factura
     */
    @Test
    public void createFacturaTest() throws BusinessLogicException {
        FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);
        if( newEntity.getServicio() == null)
        {
            try{
                FacturaEntity result = facturaLogic.create(newEntity);
                fail("Debería lanzar excepción");
            }
            catch(Exception e)
            {
                //Debería entrar acá
            }
        }
        else
        {
            FacturaEntity result = facturaLogic.create(newEntity);
            Assert.assertNotNull(result);
            FacturaEntity entity = em.find(FacturaEntity.class, result.getId());
            Assert.assertEquals(newEntity.getId(), entity.getId());
            Assert.assertEquals(newEntity.getCostoTotal(), entity.getCostoTotal());
            Assert.assertEquals(newEntity.getFechaExpedicion(), entity.getFechaExpedicion());
            Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
            Assert.assertEquals(newEntity.getNombreCliente(), entity.getNombreCliente());
            Assert.assertEquals(newEntity.getCcCliente(), entity.getCcCliente());
            Assert.assertEquals(newEntity.getServicioPrestado(), entity.getServicioPrestado());
            Assert.assertEquals(newEntity.getNombreEmpresa(), entity.getNombreEmpresa());
            Assert.assertEquals(newEntity.getServicio(), entity.getServicio());
        }
    }
    
     /**
     * Prueba para consultar la lista de Facturas
     */
    @Test
    public void getFacturasTest() {
        List<FacturaEntity> list = facturaLogic.getAll();
        Assert.assertEquals(data.size(), list.size());
        for (FacturaEntity entity : list) {
            boolean found = false;
            for (FacturaEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para consultar una Factura
     */
    @Test
    public void getFacturaTest() {
        FacturaEntity entity = data.get(0);
        FacturaEntity resultEntity = facturaLogic.getById(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getCostoTotal(), resultEntity.getCostoTotal());
        Assert.assertEquals(entity.getFechaExpedicion(), resultEntity.getFechaExpedicion());
        Assert.assertEquals(entity.getDescripcion(), resultEntity.getDescripcion());
        Assert.assertEquals(entity.getNombreCliente(), resultEntity.getNombreCliente());
        Assert.assertEquals(entity.getCcCliente(), resultEntity.getCcCliente());
        Assert.assertEquals(entity.getServicioPrestado(), resultEntity.getServicioPrestado());
        Assert.assertEquals(entity.getNombreEmpresa(), resultEntity.getNombreEmpresa());
        Assert.assertEquals(entity.getServicio(), resultEntity.getServicio());
    }
    
    /**
     * Prueba para eliminar una Factura
     */
    @Test
    public void deletFacturaTest() {
        FacturaEntity entity = data.get(0);
        facturaLogic.delete(entity);
        FacturaEntity deleted = em.find(FacturaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
     /**
     * Prueba para actualizar una Factura
     */
    @Test
    public void updateFacturaTest() throws BusinessLogicException {
        FacturaEntity entity = data.get(0);
        FacturaEntity pojoEntity = factory.manufacturePojo(FacturaEntity.class);

        pojoEntity.setId(entity.getId());

        if( pojoEntity.getServicio() == null)
        {
            try{
                facturaLogic.update(pojoEntity);
                fail("Debería lanzar excepción");
            }
            catch(Exception e)
            {
                //Debería entrar acá
            }
        }
        else
        {
            facturaLogic.update(pojoEntity);

            FacturaEntity resp = em.find(FacturaEntity.class, entity.getId());

            Assert.assertEquals(pojoEntity.getId(), resp.getId());
            Assert.assertEquals(pojoEntity.getCostoTotal(), resp.getCostoTotal());
            Assert.assertEquals(pojoEntity.getFechaExpedicion(), resp.getFechaExpedicion());
            Assert.assertEquals(pojoEntity.getDescripcion(), resp.getDescripcion());
            Assert.assertEquals(pojoEntity.getNombreCliente(), resp.getNombreCliente());
            Assert.assertEquals(pojoEntity.getCcCliente(), resp.getCcCliente());
            Assert.assertEquals(pojoEntity.getServicioPrestado(), resp.getServicioPrestado());
            Assert.assertEquals(pojoEntity.getNombreEmpresa(), resp.getNombreEmpresa());
            Assert.assertEquals(pojoEntity.getServicio(), resp.getServicio());
        }
    }
    
}
    

