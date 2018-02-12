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
    
    public ServicioDetailDTO()
    {
        
    }
    
}
