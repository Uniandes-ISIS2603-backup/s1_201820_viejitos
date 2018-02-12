/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.dtos;

import java.util.Date;

/**
 *
 * @author f.escobar
 */
public class FacturaDTO {
    
    private double costoTotal;
    
    private Date fechaExpedicion;
    
    private String descripcion;
    
    private String nombreCliente;
    
    private int ccCliente;
    
    private String servicioPrestado;
    
    private String nombreEmpresa;
    
    public FacturaDTO()
    {
        
    }
    
}
