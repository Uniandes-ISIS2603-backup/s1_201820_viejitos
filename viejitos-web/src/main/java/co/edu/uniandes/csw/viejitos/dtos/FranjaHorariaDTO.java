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
    private int horaInicio;
    private int horaFin;
    private boolean ocupado;
    private String diaSemana;
    
    public FranjaHorariaDTO()
    {
    }
            
    private int getHoraInicio()
            {
                return horaInicio;
            }
    private void setHoraInicio(int pHoraInicio)
            {
                horaInicio=pHoraInicio;
            }
    
    
    private int getHoraFin ()
            {
                return horaFin;
            }
    private void setHoraFin(int pHoraFin)
            {
                horaFin=pHoraFin;
            }
    
    
    
    private boolean getOcupado()
            {
                return ocupado;
            }
    
    private void setOcupado(int pOcupado)
            {
                ocupado=pOcupado;
            }
    
    
    private String getDiaSemana()
            {
                return diaSemana;
            }
    
    private void setDiaSemana(int pDiaSemana)
            {
                horaInicio=pDiaSemana;
            }
    
}
