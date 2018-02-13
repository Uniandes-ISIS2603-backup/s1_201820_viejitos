/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.dtos;

/**
 *
 * @author f.escobar
 */
public class PagoDetailDTO extends PagoDTO {

    /**
     * Servicio al que pertenece el pago
     */
    private ServicioDTO servicio;
    
    /**
     * @return servicio
     */
    public ServicioDTO getServicio() {
        return servicio;
    }

    /**
     * @param servicio el nuevo servicio
     */
    public void setServicio(ServicioDTO servicio) {
        this.servicio = servicio;
    }
    
    /**
     * COnstructor
     */
    public PagoDetailDTO()
    {
        
    }
}
