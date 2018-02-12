/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.dtos;
import co.edu.uniandes.csw.viejitos.entities.HistoriaClinicaEntity;
import java.util.Collections;
import java.util.Iterator;
/**
 *
 * @author l.pardo
 */
public class MedicoDetailDTO extends MedicoDTO{
   private List<CitaDTO> citas;
   private List<HistoriaClinicaDTO> historiasClinicas;
   private List<ClienteDTO> clientes; 
   
   public List<CitaDTO> getCitas()
   {
       return citas;
   }
    
   public List<HistoriaClinicaDTO> getHistoriasClinicas()
   {
       return historiasClinicas;
   }
   
   public List<ClienteDTO> getClientes()
   {
       return clientes;
   }
   
   public void setCitas(List<CitaDTO> l)
   {
       citas= l;
   }
   
   public void setHistoriasClinicas(List<HistoriaClinicaDTO> l)
   {
       historiasClinicas= l;
   }
   
   public void setHistoriasClinicas(List<ClienteDTO> l)
   {
       clientes= l;
   }
      
}
