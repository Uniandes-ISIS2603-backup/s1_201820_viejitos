/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.test.persistence;

import co.edu.uniandes.csw.viejitos.entities.FranjaHorariaEntity;
import co.edu.uniandes.csw.viejitos.persistence.FranjaHorariaPersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author lf.naranjo11
 */
@RunWith(Arquillian.class)
public class FranjaHorariaPersistenceTest 
{
    
     /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Editorial, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FranjaHorariaEntity.class.getPackage())
                .addPackage(FranjaHorariaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Inyección de la dependencia a la clase EditorialPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private FranjaHorariaPersistence franjaPersistence;
    
    /**
     * Contexto de Persistencia que se va a utilizar para acceder a la Base de
     * datos por fuera de los métodos que se están probando.
     */
    @PersistenceContext
    private EntityManager em;
    
     /**
     * Prueba para crear un Editorial.
     *
     *
     */
    @Test
    public void createFranjaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        FranjaHorariaEntity newEntity = factory.manufacturePojo(FranjaHorariaEntity.class);
        FranjaHorariaEntity result = franjaPersistence.create(newEntity);
        

        Assert.assertNotNull(result);

        FranjaHorariaEntity entity = em.find(FranjaHorariaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
    }
    
}
