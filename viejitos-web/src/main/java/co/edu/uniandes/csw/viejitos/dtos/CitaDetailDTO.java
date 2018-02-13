/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.dtos;

/**
 * Clase que extiende de {@link CitaDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos. Para conocer el
 * contenido de la ciudad vaya a la documentacion de {@link CitaDTO}
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
