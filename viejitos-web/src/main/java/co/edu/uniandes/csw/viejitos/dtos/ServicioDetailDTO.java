/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.dtos;

import java.util.List;

/**
 * Clase que extiende de {@link ServicioDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos. Para conocer el
 * contenido del servicio vaya a la documentacion de {@link ServicioDTO}
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
     * Constructor por defecto
     */
    public ServicioDetailDTO()
    {
        
    }
    
    /**
     * @return lista de quejas
     */
    public List<QuejaDTO> getQuejas() {
        return quejas;
    }

    /**
     * @param quejas nueva lista de quejas
     */
    public void setQuejas(List<QuejaDTO> quejas) {
        this.quejas = quejas;
    }

    /**
     * @return el pago inicial
     */
    public PagoDTO getPagoInicial() {
        return pagoInicial;
    }

    /**
     * @param pagoInicial nuevo pago inicial
     */
    public void setPagoInicial(PagoDTO pagoInicial) {
        this.pagoInicial = pagoInicial;
    }

    /**
     * @return el pago final
     */
    public PagoDTO getPagoFinal() {
        return pagoFinal;
    }

    /**
     * @param pagoFinal el nuevo pago final
     */
    public void setPagoFinal(PagoDTO pagoFinal) {
        this.pagoFinal = pagoFinal;
    }

    /**
     * @return la factura
     */
    public FacturaDTO getFactura() {
        return factura;
    }

    /**
     * @param factura la nueva factura
     */
    public void setFactura(FacturaDTO factura) {
        this.factura = factura;
    }

    /**
     * @return el cliente
     */
    public ClienteDTO getCliente() {
        return cliente;
    }

    /**
     * @param cliente nuevo cliente
     */
    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    /**
     * @return la clasificación
     */
    public CalificacionDTO getCalificacion() {
        return calificacion;
    }

    /**
     * @param calificacion nueva clasificación
     */
    public void setCalificacion(CalificacionDTO calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * @return el enfermero
     */
    public EnfermeroDTO getEnfermero() {
        return enfermero;
    }

    /**
     * @param enfermero nuevo enfermero
     */
    public void setEnfermero(EnfermeroDTO enfermero) {
        this.enfermero = enfermero;
    }
    
}
