/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.dtos;

import java.util.List;

/**
 *
 * @author js.espitia
 */
public class EnfermeroDetailDTO extends EnfermeroDTO{
  
    private List<CalificacionDTO> calificaciones;
    
    private List<ServicioDTO> servicios;
    
    public EnfermeroDetailDTO(){
    
    }
}
