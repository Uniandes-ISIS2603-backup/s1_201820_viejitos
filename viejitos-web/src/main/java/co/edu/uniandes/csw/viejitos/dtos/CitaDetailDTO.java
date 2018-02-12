/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.dtos;

/**
 *
 * @author l.pardo
 */
public class CitaDetailDTO extends CitaDTO{
   private Cliente cliente;
   
   public Cliente getCliente()
   {
       return cliente;
   }
   
   public void setCliente(Cliente c)
   {
       cliente=c;
   }
}
