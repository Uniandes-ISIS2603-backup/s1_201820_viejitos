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
 * MedicoDTO Objeto de transferencia de datos de Medicos. Los DTO contienen las
 * represnetaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *         "nombre":string,
 *          "tipo": String,
 *          "login":string
 *          "contrasena":String,
 *          "id":number, 
 *          "citas":[], 
 *          "historiasClinicas":[], 
 *          "clientes":[],
 *          "calendario":CalendarioSemanalDTO
 *   }
 * </pre>
 * Por ejemplo un medico se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *          "nombre":"Juan Perez",
 *          "tipo": "Medico",
 *          "login":"j.perez",
 *          "contrasena":"megustanlosgatos2",
 *          "id":"1010233999"
 *          "citas":"[{"fecha":"08-06-2018",
 *          "hora": "2:00 PM",
 *          "id":"192837465"}], 
 *          "historiasClinicas":[{"id": 12345,
 *      "cirugias": "Reemplazo de cadera.",
 *      "enfermedades": "Ninguna",
 *      "medicamentos": "Ninguno"}], 
 *          "clientes":[{ "id": 12345,
 *      "nombre: "John Doe",
 *      "login": "johndoe23",
 *      "contrasena": "jd124",
 *      "estado": 1,
 *      "tipo": 1}],
 *          "calendario":,
 *   }
 *
 * </pre>
 * @author l.pardo
 */

import co.edu.uniandes.csw.viejitos.entities.CalendarioSemanalEntity;
import co.edu.uniandes.csw.viejitos.entities.CitaEntity;
import co.edu.uniandes.csw.viejitos.entities.ClienteEntity;
import co.edu.uniandes.csw.viejitos.entities.HistoriaClinicaEntity;
import co.edu.uniandes.csw.viejitos.entities.MedicoEntity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
/**
 *
 * @author l.pardo
 */
public class MedicoDetailDTO extends MedicoDTO{

   
   private List<CitaDTO> citas;
   private List<HistoriaClinicaDTO> historiasClinicas;
   private List<ClienteDTO> clientes; 
   private CalendarioSemanalDTO calendario;
   
   public MedicoDetailDTO()
   {
       super();
   }
   public MedicoDetailDTO(MedicoEntity entity)
   {
       super(entity);
       if(entity!=null)
       {
           this.citas = new ArrayList<CitaDTO>();
           this.clientes= new ArrayList<ClienteDTO>();
           this.historiasClinicas = new ArrayList<HistoriaClinicaDTO>();
            for (CitaEntity entityCitas : entity.getCitas()) 
            {
                citas.add(new CitaDTO(entityCitas));
            }
            for (ClienteEntity entityClientes : entity.getClientes()) 
            {
                clientes.add(new ClienteDTO(entityClientes));
            }
            for (HistoriaClinicaEntity entityHistorias : entity.getHistoriasClinicas())
            {
                historiasClinicas.add(new HistoriaClinicaDTO(entityHistorias));
            }
            this.calendario= new CalendarioSemanalDTO(new CalendarioSemenalDTO(entity.getCalendario()));
       }
   }
   public MedicoEntity toEntity()
   {
       MedicoEntity entity = new MedicoEntity();
       List<HistoriaClinicaEntity> historias = new ArrayList<HistoriaClinicaEntity>();
       List<CitaEntity> cs = new ArrayList<CitaEntity>();
       List<ClienteEntity> clients = new ArrayList<ClienteEntity>();
       for (CitaDTO cita : citas) 
       {
                cs.add(cita.toEntity());
       }
       for (ClienteDTO cliente : clientes) 
       {
                clients.add(cliente.toEntity());
       }
       for (HistoriaClinicaDTO hist : historiasClinicas)
       {
                historias.add(hist.toEntity());
       }
       entity.setCalendario(calendario.toEntity());
       entity.setCitas(cs);
       entity.setClientes(clients);
       entity.setHistoriasClinicas(historias);
       return entity;
   }
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
   
   public void setClientes(List<ClienteDTO> l)
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
