/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

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
package co.edu.uniandes.csw.viejitos.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author l.pardo
 */
@Entity
public class MedicoEntity extends BaseEntity implements Serializable {

    private String nombre;
    private Integer tipo;
    private String login;
    private String contrasena;
    @PodamExclude
    @OneToMany(mappedBy = "medico", cascade = CascadeType.PERSIST)
    private List<CitaEntity> citas = new ArrayList<CitaEntity>();
    @PodamExclude
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<HistoriaClinicaEntity> historiasClinicas = new ArrayList<HistoriaClinicaEntity>();
    @PodamExclude
    @OneToMany(mappedBy = "medico", cascade = CascadeType.PERSIST)
    private List<ClienteEntity> clientes = new ArrayList<ClienteEntity>();
    @PodamExclude
    @OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = true)
    private CalendarioSemanalEntity calendario;

    public MedicoEntity() {

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
     * @return the citas
     */
    public List<CitaEntity> getCitas() {
        return citas;
    }

    /**
     * @param citas the citas to set
     */
    public void setCitas(List<CitaEntity> citas) {
        this.citas = citas;
    }

    /**
     * @return the historiasClinicas
     */
    public List<HistoriaClinicaEntity> getHistoriasClinicas() {
        return historiasClinicas;
    }

    /**
     * @param historiasClinicas the historiasClinicas to set
     */
    public void setHistoriasClinicas(List<HistoriaClinicaEntity> historiasClinicas) {
        this.historiasClinicas = historiasClinicas;
    }

    /**
     * @return the clientes
     */
    public List<ClienteEntity> getClientes() {
        return clientes;
    }

    /**
     * @param clientes the clientes to set
     */
    public void setClientes(List<ClienteEntity> clientes) {
        this.clientes = clientes;
    }

    /**
     * @return the calendario
     */
    public CalendarioSemanalEntity getCalendario() {
        return calendario;
    }

    /**
     * @param calendario the calendario to set
     */
    public void setCalendario(CalendarioSemanalEntity calendario) {
        this.calendario = calendario;
    }

}
