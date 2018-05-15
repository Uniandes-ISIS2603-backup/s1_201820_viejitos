/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.ejb;

import co.edu.uniandes.csw.viejitos.entities.PagoEntity;
import co.edu.uniandes.csw.viejitos.entities.ServicioEntity;
import co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viejitos.persistence.PagoPersistence;
import co.edu.uniandes.csw.viejitos.persistence.ServicioPersistence;
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
public class PagoLogic {
    
    private static final Logger LOGGER = Logger.getLogger( PagoLogic.class.getName( ) );
    
    @Inject
    private PagoPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    @Inject
    private ServicioLogic servicioLogic;
    //TODO: DONE Esta variable nunca se usa
    //@Inject
    //private ServicioPersistence persistenceServicio;
    
    public PagoEntity create(Long serviceid, PagoEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de una entidad de Pago");
        // Verifica las reglas de negocio
        if (entity.getValor() < 0) {
            throw new BusinessLogicException("La entidad de Pago no puede tener un valor negativo");
        }

        // Invoca la persistencia para crear la entidad de Pago
        ServicioEntity servicio = servicioLogic.getById(serviceid);
        entity.setServicio(servicio);
        
        LOGGER.info("Termina proceso de creación de entidad de Pago");
        return persistence.create(entity);
    }

    public List<PagoEntity> getAll(Long servicioid) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de consultar todas las entidades de Pago");
        ServicioEntity servicio = servicioLogic.getById(servicioid);

        System.out.println(servicio.getId());
        if (servicio.getPagos() == null) {
            throw new BusinessLogicException("El servicio que consulta aún no tiene facturas");
        }
        if (servicio.getPagos().isEmpty()) {
            throw new BusinessLogicException("El servicio que consulta aún no tiene facturas");
        }

        LOGGER.info("Termina proceso de consultar todas las entidades de Factura");
        return servicio.getPagos();
    }

    /**
     * Obtiene los datos de una instancia de Pago a partir de su ID. La
     * existencia del elemento padre Servicio se debe garantizar.
     *
     * @param idServicio El id del Servicio buscado
     * @param idPago Identificador de la Queja a consultar
     * @return Instancia de QuejaEntity con los datos de la Queja consultada.
     *
     */
    public PagoEntity getById(Long idServicio, Long idPago) {
        return persistence.find(idServicio, idPago);
    }

    /**
     * Actualiza la información de una instancia de Pago
     *
     * @param entity Instancia de PagoEntity con los nuevos datos.
     * @param idServicio id del Servicio el cual sera padre del Pago
     * actualizado.
     * @return Instancia de PagoEntity con los datos actualizados.
     * @throws co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException
     *
     */
    public PagoEntity update(Long idServicio, PagoEntity entity) throws BusinessLogicException {
        
        LOGGER.info("Inicia proceso de actualizar pago");
        ServicioEntity servicio = servicioLogic.getById(idServicio);
        entity.setServicio(servicio);
        
        if (persistence.find(idServicio, entity.getId()) == null) {
            throw new BusinessLogicException("Para actualizar, debe existir una entidad de Pago con el id \"" + entity.getId() + "\"");
        }
        // Verifica las reglas de negocio
        if (entity.getValor() < 0) {
            throw new BusinessLogicException("La entidad de Pago no puede tener un valor negativo");
        }

        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de Pago de la base de datos.
     *
     * @param idPago Identificador de la instancia a eliminar.
     * @param idServicio id del Servicio el cual es padre del Pago.
     * @throws co.edu.uniandes.csw.viejitos.exceptions.BusinessLogicException
     *
     */
    public void delete(Long idServicio, Long idPago) throws BusinessLogicException {

        LOGGER.log(Level.INFO, "Inicia proceso de borrar la entidad de Pago con id={0}", idPago);
        //TODO: DONE este método debe recibir un id y hay que validar que existe un PagoEntity con ese id

        if (persistence.find(idServicio, idPago) == null) {
            throw new BusinessLogicException("No existe una entidad de Pago con el id \"" + idPago + "\"");
        }
        
        persistence.delete(idPago);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la entidad de Pago con id={0}", idPago);
    }
}
