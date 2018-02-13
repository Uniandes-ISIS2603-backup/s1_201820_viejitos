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

import java.util.Collection;
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
