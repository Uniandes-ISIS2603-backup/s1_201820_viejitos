/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.test.logic;

import co.edu.uniandes.csw.viejitos.ejb.ClienteLogic;
import co.edu.uniandes.csw.viejitos.entities.CitaEntity;
import co.edu.uniandes.csw.viejitos.entities.ClienteEntity;
import co.edu.uniandes.csw.viejitos.entities.ServicioEntity;
import co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viejitos.persistence.ClientePersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author jj.silva
 */
@RunWith(Arquillian.class)
public class ClienteLogicTest 
{
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private ClienteLogic clienteLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<ClienteEntity> data = new ArrayList<ClienteEntity>();
    private List<CitaEntity> citasData = new ArrayList<CitaEntity>();
    private List<ServicioEntity> serviciosData = new ArrayList();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ClienteEntity.class.getPackage())
                .addPackage(ClienteLogic.class.getPackage())
                .addPackage(ClientePersistence.class.getPackage())
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
        em.createQuery("delete from CitaEntity").executeUpdate();
        em.createQuery("delete from ServicioEntity").executeUpdate();
        em.createQuery("delete from ClienteEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            CitaEntity cita = factory.manufacturePojo(CitaEntity.class);
            em.persist(cita);
            citasData.add(cita);
        }
        
        for (int i = 0; i < 3; i++) {
            ServicioEntity servicios = factory.manufacturePojo(ServicioEntity.class);
            em.persist(servicios);
            serviciosData.add(servicios);
        }
        
        for (int i = 0; i < 3; i++)
        {
            ClienteEntity entity = factory.manufacturePojo(ClienteEntity.class);
            em.persist(entity);
            data.add(entity);
            if (i == 0) {
                citasData.get(i).setCliente(entity);
                serviciosData.get(i).setCliente(entity);
            }
        }
    }
    
    /**
     * Prueba para consultar la lista de Clientes
     */
    @Test
    public void getClientesTest() {
        List<ClienteEntity> list = clienteLogic.getAll();
        Assert.assertEquals(data.size(), list.size());
        for (ClienteEntity entity : list) {
            boolean found = false;
            for (ClienteEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para consultar un Cliente
     */
    @Test
    public void getClienteTest() {
        ClienteEntity entity = data.get(0);
        ClienteEntity resultEntity = clienteLogic.getById(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());
        Assert.assertEquals(entity.getContrasena(), resultEntity.getContrasena());
        Assert.assertEquals(entity.getEstado(), resultEntity.getEstado());
        Assert.assertEquals(entity.getLogin(), resultEntity.getLogin());
        Assert.assertEquals(entity.getTipo(), resultEntity.getTipo());
    }
    
    /**
     * Prueba para eliminar un Cliente
     */
    @Test
    public void deleteClienteTest() throws BusinessLogicException {
        ClienteEntity entity = data.get(0);
        clienteLogic.delete(entity);
        ClienteEntity deleted = em.find(ClienteEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
     /**
     * Prueba para actualizar un Cliente
     */
    @Test
    public void updateClienteTest() throws BusinessLogicException {
        ClienteEntity entity = data.get(0);
        ClienteEntity pojoEntity = factory.manufacturePojo(ClienteEntity.class);

        pojoEntity.setId(entity.getId());

        clienteLogic.update(pojoEntity);

        ClienteEntity resp = em.find(ClienteEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
        Assert.assertEquals(pojoEntity.getContrasena(), resp.getContrasena());
        Assert.assertEquals(pojoEntity.getEstado(), resp.getEstado());
        Assert.assertEquals(pojoEntity.getLogin(), resp.getLogin());
        Assert.assertEquals(pojoEntity.getTipo(), resp.getTipo());
    }
    
    
      /**
     * Prueba para obtener una colección de instancias de Citas asociados a una
     * instancia Enfermero
     */
    @Test
    public void listCitasTest() {
        CitaEntity list = clienteLogic.listCitas(data.get(0).getId());
        Assert.assertEquals(citasData.get(0), list);
    }
    
       /**
     * Prueba para asociar una cita existente a un Cliente
     */
    @Test
    public void addCitaTest() {
        ClienteEntity entity = data.get(0);
        CitaEntity citaEntity = citasData.get(1);
        CitaEntity response = clienteLogic.addCita(entity.getId(), citaEntity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(citaEntity.getId(), response.getId());
    }
    
     /**
     * Prueba para remplazar las instancias de citas asociadas a una instancia
     * de Cliente
     */
    @Test
    public void replaceCitaTest() {
        ClienteEntity entity = data.get(0);
        CitaEntity list = citasData.get(2);
        try {
            clienteLogic.replaceCita(entity.getId(), list);
        } catch (BusinessLogicException ex) {
            Logger.getLogger(ClienteLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        entity = clienteLogic.getById(entity.getId());
        Assert.assertFalse(entity.getCita() == citasData.get(0));
        Assert.assertFalse(entity.getCita() == citasData.get(1));
        Assert.assertTrue(entity.getCita() == citasData.get(2));
    }
    
    /**
     * Prueba para desasociar una cita existente de un cliente existente 
     * @throws co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException
     */
    @Test
    public void removeCitaTest() throws BusinessLogicException {
        try {
            clienteLogic.removeCita(data.get(0).getId(), citasData.get(2).getId());
            CitaEntity response = clienteLogic.getCita(data.get(0).getId(), citasData.get(2).getId());
        } catch (BusinessLogicException e) {
        }

    }
    
    /**
     * Prueba para obtener una colección de instancias de Servicios asociados a una
     * instancia Cliente
     */
    @Test
    public void lisServiciosTest() {
        List<ServicioEntity> list = clienteLogic.listServicios(data.get(0).getId());
        Assert.assertEquals(1, list.size());
    }
    
     /**
     * Prueba para asociar un Servicio existente a un Cliente
     */
    @Test
    public void addServicioTest() {
        ClienteEntity entity = data.get(0);
        ServicioEntity servicioEntity = serviciosData.get(1);
        ServicioEntity response = clienteLogic.addServicio(entity.getId(), servicioEntity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(servicioEntity.getId(), response.getId());
    }
    
    /**
     * Prueba para remplazar las instancias de Servicios asociadas a una instancia
     * de Cliente
     */
    @Test
    public void replaceServiciosTest() {
        ClienteEntity entity = data.get(0);
        List<ServicioEntity> list = serviciosData.subList(1, 3);
        clienteLogic.replaceServicios(entity.getId(), list);

        entity = clienteLogic.getById(entity.getId());
        Assert.assertFalse(entity.getServicios().contains(serviciosData.get(0)));
        Assert.assertTrue(entity.getServicios().contains(serviciosData.get(1)));
        Assert.assertTrue(entity.getServicios().contains(serviciosData.get(2)));
    }
    
    /**
     * Prueba para desasociar un Servicio existente de un Cliente existente 
     */
    @Test
    public void removeServiciosTest() throws BusinessLogicException {
        try {
            clienteLogic.removeServicio(data.get(0).getId(), serviciosData.get(0).getId());
            ServicioEntity response = clienteLogic.getServicio(data.get(0).getId(), serviciosData.get(0).getId());
        } catch (BusinessLogicException e) {
        }

    }
}