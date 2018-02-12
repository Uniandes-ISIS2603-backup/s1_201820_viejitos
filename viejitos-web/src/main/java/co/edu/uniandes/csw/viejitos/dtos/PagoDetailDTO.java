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

    private ServicioDTO servicio;
    
    /**
     * @return the servicio
     */
    public ServicioDTO getServicio() {
        return servicio;
    }

    /**
     * @param servicio the servicio to set
     */
    public void setServicio(ServicioDTO servicio) {
        this.servicio = servicio;
    }
    
    
    
    public PagoDetailDTO()
    {
        
    }
}
