/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.test.persistence;


import co.edu.uniandes.csw.viejitos.entities.QuejaEntity;
import co.edu.uniandes.csw.viejitos.entities.ServicioEntity;
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
public class QuejaPersistenceTest {
    
     /**
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Queja, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(QuejaEntity.class.getPackage())
                .addPackage(QuejaPersistence.class.getPackage())
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
    private QuejaPersistence quejaPersistence;
    
     /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from QuejaEntity").executeUpdate();
        em.createQuery("delete from ServicioEntity").executeUpdate();
    }
    
     /**
     * Lista que tiene los datos de prueba.
     */
    private List<QuejaEntity> data = new ArrayList<QuejaEntity>();
    private List<ServicioEntity> dataServicio = new ArrayList<ServicioEntity>();
    
    /**
     * Variable para marcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
     PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ServicioEntity entity = factory.manufacturePojo(ServicioEntity.class);
            em.persist(entity);
            dataServicio.add(entity);
        }
        for (int i = 0; i < 3; i++) {
            QuejaEntity entity = factory.manufacturePojo(QuejaEntity.class);
            if (i == 0) {
                entity.setServicio(dataServicio.get(0));
            }
            em.persist(entity);
            data.add(entity);
        }
    }
    
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
     * Prueba para crear una queja.
     */
    @Test
    public void createQuejaEntityTest() {
    PodamFactory factory = new PodamFactoryImpl();
    QuejaEntity newEntity = factory.manufacturePojo(QuejaEntity.class);
    QuejaEntity result = quejaPersistence.create(newEntity);

    Assert.assertNotNull(result);
    QuejaEntity entity = em.find(QuejaEntity.class, result.getId());
    Assert.assertNotNull(entity);
    Assert.assertEquals(newEntity.getName(), entity.getName());
}
   
    
     /**
     * Prueba para consultar una queja.
     */
    @Test
    public void getQuejaTest() {
        QuejaEntity entity = data.get(0);
        QuejaEntity newEntity = quejaPersistence.find(dataServicio.get(0).getId(), entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    /**
     * Prueba para eliminar una queja.
     */
    @Test
    public void deleteQuejaTest() {
        QuejaEntity entity = data.get(0);
        quejaPersistence.delete(entity.getId());
        QuejaEntity deleted = em.find(QuejaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar una queja.
     */
    @Test
    public void updateQuejaTest() {
        QuejaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        QuejaEntity newEntity = factory.manufacturePojo(QuejaEntity.class);

        newEntity.setId(entity.getId());

        quejaPersistence.update(newEntity);

        QuejaEntity resp = em.find(QuejaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
    
}
