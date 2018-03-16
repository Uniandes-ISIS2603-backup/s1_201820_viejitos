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

import co.edu.uniandes.csw.viejitos.entities.MedicoEntity;

/**
 * MedicoDTO Objeto de transferencia de datos de Medicos. Los DTO contienen las
 * represnetaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *         "nombre":string,
 *          "tipo": integer,
 *          "login":string,
 *          "contrasena":String,
 *          "id":number
 *   }
 * </pre> Por ejemplo un medico se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *          "nombre":"Juan Perez",
 *          "tipo": 1,
 *          "login":"j.perez",
 *          "contrasena":"megustanlosgatos2",
 *          "id":"1010233999"
 *   }
 *
 * </pre>
 *
 * @author l.pardo
 */
public class MedicoDTO {

    //atributos
    private String nombre;
    private Integer tipo;
    private String login;
    private String contrasena;
    private Long id;

    public MedicoDTO() {

    }

    public MedicoDTO(MedicoEntity entity) {
        if (entity != null) {
            this.nombre = entity.getNombre();
            this.contrasena = entity.getContrasena();
            this.login = entity.getLogin();
            this.tipo = entity.getTipo();
            this.id = entity.getId();
        }
    }

    public MedicoEntity toEntity() {
        MedicoEntity entity = new MedicoEntity();
        entity.setContrasena(this.contrasena);
        entity.setId(this.id);
        entity.setLogin(this.login);
        entity.setNombre(this.nombre);
        entity.setTipo(this.tipo);
        return entity;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the tipo
     */
    public Integer getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(Integer tipo) {
        this.tipo = tipo;
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

    /**
     * @return the contrasena
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * @param contrasena the contrasena to set
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

}
