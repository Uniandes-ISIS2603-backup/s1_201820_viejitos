/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.test.persistence;

import co.edu.uniandes.csw.viejitos.entities.FacturaEntity;
import co.edu.uniandes.csw.viejitos.entities.ServicioEntity;
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
public class FacturaPersistenceTest {
   
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Queja, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FacturaEntity.class.getPackage())
                .addPackage(FacturaPersistence.class.getPackage())
                .addPackage(ServicioEntity.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Contexto de Persistencia que se va a utilizar para acceder a la Base de
     * datos por fuera de los métodos que se están probando.
     */
    @PersistenceContext
    private EntityManager em;
    
    /**
     * Inyección de la dependencia a la clase QuejaPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private FacturaPersistence facturaPersistence;
    
    /**
     * Variable para marcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;
    
    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() {
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
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from FacturaEntity").executeUpdate();
        em.createQuery("delete from ServicioEntity").executeUpdate();
    }
    
     /**
     * Lista que tiene los datos de prueba.
     */
    private List<FacturaEntity> data = new ArrayList<FacturaEntity>();
    private List<ServicioEntity> dataServicio = new ArrayList<ServicioEntity>();
    
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        data = new ArrayList<>();
        dataServicio = new ArrayList<>();
        PodamFactory factory = new PodamFactoryImpl();
        
        ServicioEntity entity = factory.manufacturePojo(ServicioEntity.class);
           
        em.persist(entity);
        dataServicio.add(entity);
      
        for (int i = 0; i < 1; i++) {
            FacturaEntity entityFactura = factory.manufacturePojo(FacturaEntity.class);
            if (i == 0) {
                System.out.println("id servicio insert: " + dataServicio.get(0).getId());
                entityFactura.setServicio(dataServicio.get(0));
                //entity.setFactura(entityFactura);
            }
           
            em.persist(entityFactura);
            data.add(entityFactura);
        }
        System.out.println("id del servicio: " + data.get(0).getServicio().getId() + " ; " + data.get(0).getId());
    }

    
    @Test
    public void createFacturaEntityTest() {
        PodamFactory factory = new PodamFactoryImpl();
        FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);
        FacturaEntity result = facturaPersistence.create(newEntity);

        Assert.assertNotNull(result);
        FacturaEntity entity = em.find(FacturaEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Prueba para consultar la lista de facturas. 
     */
    @Test
    public void getFacturasTest() {
        List<FacturaEntity> list = facturaPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (FacturaEntity ent : list) {
            boolean found = false;
            for (FacturaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
     /**
     * Prueba para consultar una factura.
     */
    @Test
    public void getFacturaTest() {
        FacturaEntity entity = data.get(0);
        //System.out.println("id del servicio: " + dataServicio.get(0).getId());
        //System.out.println("id del servicio: " + entity.getServicio().getId() + " ; " + entity.getId());
        FacturaEntity newEntity = facturaPersistence.find(entity.getServicio().getId(), entity.getId());
        System.out.println(newEntity == null);
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    /**
     * Prueba para eliminar una factura.
     */
    @Test
    public void deleteFacturaTest() {
        FacturaEntity entity = data.get(0);
        facturaPersistence.delete(entity.getId());
        FacturaEntity deleted = em.find(FacturaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar una factura.
     */
    @Test
    public void updateFacturaTest() {
        FacturaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);

        newEntity.setId(entity.getId());

        facturaPersistence.update(newEntity);

        FacturaEntity resp = em.find(FacturaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
}