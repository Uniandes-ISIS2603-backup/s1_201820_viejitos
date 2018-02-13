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
 *         "fecha":date,
 *          "hora": date,
 *          "id":number
 *   }
 * </pre>
 * Por ejemplo una cita se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *          "fecha":"08-06-2018",
 *          "hora": "2:00 PM",
 *          "id":"192837465"
 *   }
 *
 * </pre>
 * @author l.pardo
 */
import java.util.Date;

/**
 *
 * @author l.pardo
 */
public class CitaDTO {

   
    private Date fecha;
    private Date hora;
    private Long id;
    public CitaDTO()
    {
        
    }
    public Date getFecha()
    {
        return fecha;
    }
    public Date getHora()
    {
        return hora;
    }
    public void setFecha(Date pFecha)
    {
        fecha=pFecha;
    }
    public void setHora(Date pHora)
    {
        hora= pHora;
    }
     /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
}
