/*
MIT License

Copyright (c) 2017 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.csw.viejitos.test.persistence;

import co.edu.uniandes.csw.viejitos.entities.CalendarioSemanalEntity;
import co.edu.uniandes.csw.viejitos.entities.FranjaHorariaEntity;
import co.edu.uniandes.csw.viejitos.entities.MedicoEntity;
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
import static org.junit.Assert.assertNull;
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
public class MedicoPersistenceTest {

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
                .addPackage(MedicoEntity.class.getPackage())
                .addPackage(MedicoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Inyección de la dependencia a la clase MedicoPersistence cuyos métodos se
     * van a probar.
     */
    @Inject
    private MedicoPersistence medicoPersistence;

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
        em.createQuery("delete from MedicoEntity").executeUpdate();
    }

    /**
     * lista que tiene los datos de prueba
     */
    private List<MedicoEntity> data = new ArrayList<MedicoEntity>();
    /**
     * Variable para martcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {

            MedicoEntity entity = factory.manufacturePojo(MedicoEntity.class);

            em.persist(entity);

            data.add(entity);
        }
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
     * Prueba para crear un Medico.
     *
     *
     */
    @Test
    public void createMedicoTest() {
        PodamFactory factory = new PodamFactoryImpl();
        MedicoEntity newEntity = factory.manufacturePojo(MedicoEntity.class);
        MedicoEntity result = medicoPersistence.create(newEntity);

        Assert.assertNotNull(result);

        MedicoEntity entity = em.find(MedicoEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Prueba para consultar la lista de Medicos.
     *
     *
     */
    @Test
    public void getMedicosTest() {
        List<MedicoEntity> list = medicoPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (MedicoEntity ent : list) {
            boolean found = false;
            for (MedicoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Medico.
     *
     *
     */
    @Test
    public void getMedicoTest() {
        MedicoEntity entity = data.get(0);
        MedicoEntity newEntity = medicoPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    /**
     * Prueba para consultar un Medico.
     *
     *
     */
    @Test
    public void getMedicoByLoginTest() {
        MedicoEntity entity = data.get(0);
        if (medicoPersistence.find(entity.getId()) != null) {
            try {
                MedicoEntity newEntity = medicoPersistence.findByLogin(entity.getLogin());
                Assert.assertNotNull(newEntity);
                Assert.assertEquals(entity.getLogin(), newEntity.getLogin());
            } catch (Exception e) {

            }
        } else {
            try {
                MedicoEntity newEntity = medicoPersistence.findByLogin(entity.getLogin());
                Assert.assertNull(newEntity);
            } catch (Exception e) {

            }
        }

    }

    @Test
    public void getMedicoByNameTest() {
        MedicoEntity entity = data.get(0);

        MedicoEntity newEntity = medicoPersistence.findByName(entity.getName());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getLogin(), newEntity.getLogin());

    }

    /**
     * Prueba para eliminar un Medico.
     *
     *
     */
    @Test
    public void deleteMedicoTest() {
        MedicoEntity entity = data.get(0);
        medicoPersistence.delete(entity.getId());
        MedicoEntity deleted = em.find(MedicoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Medico.
     *
     *
     */
    @Test
    public void updateMedicoTest() {
        MedicoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        MedicoEntity newEntity = factory.manufacturePojo(MedicoEntity.class);

        newEntity.setId(entity.getId());

        medicoPersistence.update(newEntity);

        MedicoEntity resp = em.find(MedicoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }

    @Test
    public void findFirstAvailableTest() {
        MedicoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        CalendarioSemanalEntity calendar = factory.manufacturePojo(CalendarioSemanalEntity.class);
        List<FranjaHorariaEntity> l = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            l.add(factory.manufacturePojo(FranjaHorariaEntity.class));
        }

        calendar.setFranjas(l);
        entity.setCalendario(calendar);
        medicoPersistence.update(entity);
        if (l.get(0).isOcupado()) {
            Assert.assertNull(medicoPersistence.findFirstAvailable(l.get(0)));
        } else {
            Assert.assertEquals(entity, medicoPersistence.findFirstAvailable(l.get(0)));
        }
    }
}
