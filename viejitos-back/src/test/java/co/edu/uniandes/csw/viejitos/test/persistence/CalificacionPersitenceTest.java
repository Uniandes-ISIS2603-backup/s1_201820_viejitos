package co.edu.uniandes.csw.viejitos.test.persistence;

import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.viejitos.entities.CalificacionEntity;
import co.edu.uniandes.csw.viejitos.persistence.CalificacionPersistence;
import java.util.ArrayList;
import java.util.List;
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
 * @author JuanSebastian
 */
@RunWith(Arquillian.class)
public class CalificacionPersitenceTest {
	/**
    *
    * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
    * embebido. El jar contiene al descriptor de la
    * base de datos y el archivo beans.xml para resolver la inyección de
    * dependencias.
    */
   @Deployment
   public static JavaArchive createDeployment() {
       return ShrinkWrap.create(JavaArchive.class)
               .addPackage(CalificacionEntity.class.getPackage())
               .addPackage(CalificacionPersistence.class.getPackage())
               .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
               .addAsManifestResource("META-INF/beans.xml", "beans.xml");
   }

   /**
    * Inyección de la dependencia a la clase CalificacionPersistence cuyos métodos
    * se van a probar.
    */
   @Inject
   private CalificacionPersistence calificacionPersistence;

   /**
    * Contexto de Persistencia que se va a utilizar para acceder a la Base de
    * datos por fuera de los métodos que se están probando.
    */
   @PersistenceContext
   private EntityManager em;



   /**
    * Limpia las tablas que están implicadas en la prueba.
    *
    *
    */
   private void clearData() {
       em.createQuery("delete from CitaEntity").executeUpdate();
   }

   /**
    * lista que tiene los datos de prueba
    */
   private List<CalificacionEntity> data = new ArrayList<CalificacionEntity>();

   /**
    * Inserta los datos iniciales para el correcto funcionamiento de las
    * pruebas.
    *
    *
    */
   private void insertData() {
       PodamFactory factory = new PodamFactoryImpl();
       for (int i = 0; i < 3; i++) {
           
           CalificacionEntity entity = factory.manufacturePojo(CalificacionEntity.class);

           em.persist(entity);
           
           data.add(entity);
       }
   }

   /**
    * Prueba para crear una Calificacion.
    *
    *
    */
   @Test
   public void createCalificacionTest() {
       PodamFactory factory = new PodamFactoryImpl();
       CalificacionEntity newEntity = factory.manufacturePojo(CalificacionEntity.class);
       CalificacionEntity result = calificacionPersistence.create(newEntity);

       Assert.assertNotNull(result);

       CalificacionEntity entity = em.find(CalificacionEntity.class, result.getId());

       Assert.assertEquals(newEntity.getName(), entity.getName());
   }
}
