/*
MIT License
Copyright (c) 2017 ISIS2603
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package co.edu.uniandes.csw.viejitos.dtos;
/**
 * Clase que extiende de {@link MedicoDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos. Para conocer el
 * contenido de la ciudad vaya a la documentacion de {@link MedicoDTO}
 * @author l.pardo
 */
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
