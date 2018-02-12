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
   private ClienteDTO cliente;
   public CitaDetailDTO()
   {
   }
   public ClienteDTO getCliente()
   {
       return cliente;
   }
   
   public void setClienteDTO(ClienteDTO c)
   {
       cliente=c;
   }
}
