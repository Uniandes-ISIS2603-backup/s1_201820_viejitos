/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.ejb;

import co.edu.uniandes.csw.viejitos.entities.FacturaEntity;
import co.edu.uniandes.csw.viejitos.entities.ServicioEntity;
import co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viejitos.persistence.FacturaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author f.escobar
 */
@Stateless
public class FacturaLogic {

    private static final Logger LOGGER = Logger.getLogger(FacturaLogic.class.getName());

    @Inject
    private FacturaPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    @Inject
    private ServicioLogic servicioLogic;
    //TODO: DONE Esta variable no se usa
    //@Inject
    //private ServicioPersistence persistenceServicio;

    public FacturaEntity create(Long serviceid, FacturaEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de una entidad de Factura");
        // Invoca la persistencia para crear la entidad de Factura
        // Verifica las reglas de negocio

       
        if (entity.getCostoTotal() < 0) {
            throw new BusinessLogicException("La entidad de Factura no puede tener un costo total negativo");
        }

        ServicioEntity servicio = servicioLogic.getById(serviceid);
        entity.setServicio(servicio);
        //servicio.setFacturas(entity);
        //servicioLogic.update(servicio);
        LOGGER.info("Termina proceso de creación de entidad de Factura");
        return persistence.create(entity);
    }

    /**
     * Obtiene la lista de los registros de Factura que pertenecen a un
     * Servicio.
     *
     * @param servicioid id del Servicio el cual es padre de las Facturas.
     * @return Colección de objetos de FacturaEntity.
     * @throws co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException
     */
    public List<FacturaEntity> getAll(Long servicioid) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de consultar todas las entidades de Factura");
        ServicioEntity servicio = servicioLogic.getById(servicioid);

        System.out.println(servicio.getId());
        if (servicio.getFacturas() == null) {
            throw new BusinessLogicException("El servicio que consulta aún no tiene facturas");
        }
        if (servicio.getFacturas().isEmpty()) {
            throw new BusinessLogicException("El servicio que consulta aún no tiene facturas");
        }

        LOGGER.info("Termina proceso de consultar todas las entidades de Factura");
        return servicio.getFacturas();
    }

    /**
     * Obtiene los datos de una instancia de Factura a partir de su ID. La
     * existencia del elemento padre Servicio se debe garantizar.
     *
     * @param idServicio El id del Servicio buscado
     * @param idFactura Identificador de la Queja a consultar
     * @return Instancia de QuejaEntity con los datos de la Queja consultada.
     *
     */
    public FacturaEntity getById(Long idServicio, Long idFactura) {
        //System.out.println("idservicio= " + idServicio + "; idfactura = " + idFactura);
        return persistence.find(idServicio, idFactura);
    }

    /**
     * Actualiza la información de una instancia de Factura
     *
     * @param entity Instancia de FacturaEntity con los nuevos datos.
     * @param idServicio id del Servicio el cual sera padre de la Factura
     * actualizada.
     * @return Instancia de FacturaEntity con los datos actualizados.
     * @throws co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException
     *
     */
    public FacturaEntity update(Long idServicio, FacturaEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de actualizar factura");
        ServicioEntity servicio = servicioLogic.getById(idServicio);
        entity.setServicio(servicio);

        if (persistence.find(idServicio, entity.getId()) == null) {
            throw new BusinessLogicException("No existe una entidad de Factura con el id \"" + entity.getId() + "\"");
        }
        //Reglas de negocio
        
        if (entity.getCostoTotal() < 0) {
            throw new BusinessLogicException("La entidad de Factura no puede tener un costo total negativo");
        }

        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de Factura de la base de datos.
     *
     * @param idFactura Identificador de la instancia a eliminar.
     * @param idServicio id del Servicio el cual es padre de la Factura.
     * @throws co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException
     *
     */
    public void delete(Long idServicio, Long idFactura) throws BusinessLogicException {
        //TODO: DONE este método debe recibir un id y hay que validar que existe un FacturaEntity con ese id
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la entidad de Factura con id={0}", idFactura);

        if (persistence.find(idServicio, idFactura) == null) {
            throw new BusinessLogicException("No existe una entidad de Factura con el id \"" + idFactura + "\"");
        }

        persistence.delete(idFactura);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la entidad de Factura con id={0}", idFactura);
    }
    
}
