/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.dtos;

/**
 *
 * @author lf.naranjo11
 */
public class FranjaHorariaDTO {
    private Integer horaInicio;
    private Integer horaFin;
    private Boolean ocupado;
    private String diaSemana;
    
    public FranjaHorariaDTO()
    {
    }
            
    private int getHoraInicio()
            {
                return horaInicio;
            }
    private void setHoraInicio(Integer pHoraInicio)
            {
                horaInicio=pHoraInicio;
            }
    
    
    private int getHoraFin ()
            {
                return horaFin;
            }
    private void setHoraFin(Integer pHoraFin)
            {
                horaFin=pHoraFin;
            }
    
    
    
    private Boolean getOcupado()
            {
                return ocupado;
            }
    
    private void setOcupado(Integer pOcupado)
            {
                ocupado=pOcupado;
            }
    
    
    private String getDiaSemana()
            {
                return diaSemana;
            }
    
    private void setDiaSemana(Integer pDiaSemana)
            {
                horaInicio=pDiaSemana;
            }
    
}
