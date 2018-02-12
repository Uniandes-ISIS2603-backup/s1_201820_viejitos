/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.dtos;
import co.edu.uniandes.csw.viejitos.entities.HistoriaClinicaEntity;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
/**
 *
 * @author l.pardo
 */
public class MedicoDetailDTO extends MedicoDTO{

   
   private Collection<CitaDTO> citas;
   private Collection<HistoriaClinicaDTO> historiasClinicas;
   private Collection<ClienteDTO> clientes; 
   private CalendarioSemanalDTO calendario;
   
   public MedicoDetailDTO()
   {
       
   }
   public Collection<CitaDTO> getCitas()
   {
       return citas;
   }
    
   public Collection<HistoriaClinicaDTO> getHistoriasClinicas()
   {
       return historiasClinicas;
   }
   
   public Collection<ClienteDTO> getClientes()
   {
       return clientes;
   }
   
   public void setCitas(Collection<CitaDTO> l)
   {
       citas= l;
   }
   
   public void setHistoriasClinicas(Collection<HistoriaClinicaDTO> l)
   {
       historiasClinicas= l;
   }
   
   public void setClientes(Collection<ClienteDTO> l)
   {
       clientes= l;
   }
    /**
     * @return the calendario
     */
    public CalendarioSemanalDTO getCalendario() {
        return calendario;
    }

    /**
     * @param calendario the calendario to set
     */
    public void setCalendario(CalendarioSemanalDTO calendario) {
        this.calendario = calendario;
    }
      
}
