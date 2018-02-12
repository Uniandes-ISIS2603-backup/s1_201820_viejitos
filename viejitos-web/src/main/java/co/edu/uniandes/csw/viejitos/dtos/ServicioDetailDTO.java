/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.dtos;

import java.util.List;

/**
 *
 * @author c.gomezs
 */
public class ServicioDetailDTO extends ServicioDTO{
    
    private List<QuejaDTO> quejas;
    
    private PagoDTO pagoInicial;
    
    private PagoDTO pagoFinal;
    
    private FacturaDTO factura;
    
    private ClienteDTO cliente;
    
    private CalificacionDTO calificacion;
    
    private EnfermeroDTO enfermero;

    /**
     * @return the quejas
     */
    public List<QuejaDTO> getQuejas() {
        return quejas;
    }

    /**
     * @param quejas the quejas to set
     */
    public void setQuejas(List<QuejaDTO> quejas) {
        this.quejas = quejas;
    }

    /**
     * @return the pagoInicial
     */
    public PagoDTO getPagoInicial() {
        return pagoInicial;
    }

    /**
     * @param pagoInicial the pagoInicial to set
     */
    public void setPagoInicial(PagoDTO pagoInicial) {
        this.pagoInicial = pagoInicial;
    }

    /**
     * @return the pagoFinal
     */
    public PagoDTO getPagoFinal() {
        return pagoFinal;
    }

    /**
     * @param pagoFinal the pagoFinal to set
     */
    public void setPagoFinal(PagoDTO pagoFinal) {
        this.pagoFinal = pagoFinal;
    }

    /**
     * @return the factura
     */
    public FacturaDTO getFactura() {
        return factura;
    }

    /**
     * @param factura the factura to set
     */
    public void setFactura(FacturaDTO factura) {
        this.factura = factura;
    }

    /**
     * @return the cliente
     */
    public ClienteDTO getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the calificacion
     */
    public CalificacionDTO getCalificacion() {
        return calificacion;
    }

    /**
     * @param calificacion the calificacion to set
     */
    public void setCalificacion(CalificacionDTO calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * @return the enfermero
     */
    public EnfermeroDTO getEnfermero() {
        return enfermero;
    }

    /**
     * @param enfermero the enfermero to set
     */
    public void setEnfermero(EnfermeroDTO enfermero) {
        this.enfermero = enfermero;
    }
    
    
    public ServicioDetailDTO()
    {
        
    }
    
}
