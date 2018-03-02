/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.test.logic;

import co.edu.uniandes.csw.viejitos.ejb.HistoriaClinicaLogic;
import co.edu.uniandes.csw.viejitos.entities.HistoriaClinicaEntity;
import co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viejitos.persistence.HistoriaClinicaPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author jj.silva
 */
@RunWith(Arquillian.class)
public class HistoriaClinicaLogicTest 
{
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private HistoriaClinicaLogic historiaCLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<HistoriaClinicaEntity> data = new ArrayList<HistoriaClinicaEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(HistoriaClinicaEntity.class.getPackage())
                .addPackage(HistoriaClinicaLogic.class.getPackage())
                .addPackage(HistoriaClinicaPersistence.class.getPackage())
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
        em.createQuery("delete from HistoriaClinicaEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++)
        {
            HistoriaClinicaEntity entity = factory.manufacturePojo(HistoriaClinicaEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Prueba para crear una HistoriaClinica
     */
    @Test
    public void createHistoriaClinicaTest() throws BusinessLogicException{
        HistoriaClinicaEntity newEntity = factory.manufacturePojo(HistoriaClinicaEntity.class);
        HistoriaClinicaEntity result = historiaCLogic.create(newEntity);
        Assert.assertNotNull(result);
        HistoriaClinicaEntity entity = em.find(HistoriaClinicaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getCirugias(), entity.getCirugias());
        Assert.assertEquals(newEntity.getEnfermedades(), entity.getEnfermedades());
        Assert.assertEquals(newEntity.getMedicamentos(), entity.getMedicamentos());
    }
    
     /**
     * Prueba para consultar la lista de HistoriasClinicas
     */
    @Test
    public void getHistoriasClinicasTest() {
        List<HistoriaClinicaEntity> list = historiaCLogic.getAll();
        Assert.assertEquals(data.size(), list.size());
        for (HistoriaClinicaEntity entity : list) {
            boolean found = false;
            for (HistoriaClinicaEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para consultar una HistoriaClinica
     */
    @Test
    public void getHistoriaClinicaTest() {
        HistoriaClinicaEntity entity = data.get(0);
        HistoriaClinicaEntity resultEntity = historiaCLogic.getById(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getCirugias(), resultEntity.getCirugias());
        Assert.assertEquals(entity.getEnfermedades(), resultEntity.getEnfermedades());
        Assert.assertEquals(entity.getMedicamentos(), resultEntity.getMedicamentos());
    }
    
    /**
     * Prueba para eliminar una HistoriaClinica
     */
    @Test
    public void deleteHistoriaClinicaTest() {
        HistoriaClinicaEntity entity = data.get(0);
        historiaCLogic.delete(entity);
        HistoriaClinicaEntity deleted = em.find(HistoriaClinicaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
     /**
     * Prueba para actualizar una HistoriaClinica
     */
    @Test
    public void updateHistoriaClinicaTest() throws BusinessLogicException {
        HistoriaClinicaEntity entity = data.get(0);
        HistoriaClinicaEntity pojoEntity = factory.manufacturePojo(HistoriaClinicaEntity.class);

        pojoEntity.setId(entity.getId());

        historiaCLogic.update(pojoEntity);

        HistoriaClinicaEntity resp = em.find(HistoriaClinicaEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getCirugias(), resp.getCirugias());
        Assert.assertEquals(pojoEntity.getEnfermedades(), resp.getEnfermedades());
        Assert.assertEquals(pojoEntity.getMedicamentos(), resp.getMedicamentos());
    }
}
