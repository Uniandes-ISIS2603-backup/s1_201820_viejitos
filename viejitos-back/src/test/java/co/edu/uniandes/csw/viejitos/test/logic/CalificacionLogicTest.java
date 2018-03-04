/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.test.logic;

import co.edu.uniandes.csw.viejitos.ejb.CalificacionLogic;
import co.edu.uniandes.csw.viejitos.entities.CalificacionEntity;
import co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viejitos.persistence.CalificacionPersistence;
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
public class CalificacionLogicTest {
     
    PodamFactoryImpl fact = new PodamFactoryImpl();
    
    @Inject
    CalificacionLogic logic;
    
    @PersistenceContext
    EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    List<CalificacionEntity> data = new ArrayList<>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CalificacionEntity.class.getPackage())
                .addPackage(CalificacionLogic.class.getPackage())
                .addPackage(CalificacionPersistence.class.getPackage())
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
        em.createQuery("delete from CalificacionEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++)
        {
            CalificacionEntity entity = fact.manufacturePojo(CalificacionEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
     /**
     * Prueba para crear una calificacion
     * @throws co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException
     */
    @Test
    public void createCalificacionTest() throws BusinessLogicException{
        CalificacionEntity nueva = fact.manufacturePojo(CalificacionEntity.class);
        CalificacionEntity result = logic.create(nueva);
        Assert.assertNotNull(result);
        CalificacionEntity entity = em.find(CalificacionEntity.class, result.getId());
        Assert.assertEquals(nueva.getId(), entity.getId());
        Assert.assertEquals(nueva.getLoginCalificado(), entity.getLoginCalificado());
        Assert.assertEquals(nueva.getLoginCalificador(), entity.getLoginCalificador());
        Assert.assertEquals(nueva.getPuntaje(), entity.getPuntaje());
        Assert.assertEquals(nueva.getComentario(), entity.getComentario());
    }
    
    /**
     * Prueba para consultar la lista de Calificaciones
     */
    @Test
    public void getCalificacionesTest() {
        List<CalificacionEntity> list = logic.getAll();
        Assert.assertEquals(data.size(), list.size());
        for (CalificacionEntity entity : list) {
            boolean found = false;
            for (CalificacionEntity storedEntity : data) {
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
    public void getCalificacionTest() {
        CalificacionEntity entity = data.get(0);
        CalificacionEntity resultEntity = logic.getById(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getPuntaje(), resultEntity.getPuntaje());
        Assert.assertEquals(entity.getComentario(), resultEntity.getComentario());
        Assert.assertEquals(entity.getLoginCalificado(), resultEntity.getLoginCalificado());
        Assert.assertEquals(entity.getLoginCalificador(), resultEntity.getLoginCalificador());

    }
    /*
    * Prueba para actualizar una califcacion
    */
    @Test
    public void updateCalificacionTest()throws BusinessLogicException {
        CalificacionEntity entity = data.get(0);
        CalificacionEntity pojoEntity = fact.manufacturePojo(CalificacionEntity.class);

        pojoEntity.setId(entity.getId());

        logic.update(pojoEntity);

        CalificacionEntity resp = em.find(CalificacionEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
         Assert.assertEquals(resp.getLoginCalificado(), pojoEntity.getLoginCalificado());
        Assert.assertEquals(resp.getLoginCalificador(), pojoEntity.getLoginCalificador());
        Assert.assertEquals(resp.getPuntaje(), pojoEntity.getPuntaje());
        Assert.assertEquals(resp.getComentario(), pojoEntity.getComentario());
    }
    
    /**
     * Prueba para eliminar una calificacion
     */
    @Test
    public void deleteCalificacionTest() throws BusinessLogicException{
        CalificacionEntity entity = data.get(0);
        logic.delete(entity);
        CalificacionEntity deleted = em.find(CalificacionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
