/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.dtos;

import co.edu.uniandes.csw.viejitos.entities.ClienteEntity;

/**
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "nombre": string,
 *      "login": string,
 *      "contrasena": string,
 *      "estado": number,
 *      "tipo": number
 *   }
 * </pre> Por ejemplo una entidad de Cliente se representa asi:<br>
 * <p>
 * <
 * pre>
 *
 *   {
 *      "id": 12345,
 *      "nombre": "John Doe",
 *      "login": "johndoe23",
 *      "contrasena": "jd124",
 *      "estado": 1,
 *      "tipo": 1
 *   }
 *
 * </pre>
 *
 * @author jj.silva
 */
public class ClienteDTO {

    private Long id;

    private Integer estado;

    private String nombre;

    private Integer tipo;

    private String login;

    private String contrasena;

    public ClienteDTO() {

    }

    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param ClienteEntity: Es la entidad que se va a convertir a DTO
     */
    public ClienteDTO(ClienteEntity clienteEntity) {
        if(clienteEntity!=null)
        {
        this.id = clienteEntity.getId();
        this.nombre = clienteEntity.getNombre();
        this.estado = clienteEntity.getEstado();
        this.tipo = clienteEntity.getTipo();
        this.login = clienteEntity.getLogin();
        this.contrasena = clienteEntity.getContrasena();
        }

    }

    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public ClienteEntity toEntity() {
        ClienteEntity entity = new ClienteEntity();
        entity.setId(this.id);
        entity.setNombre(this.nombre);
        entity.setContrasena(this.contrasena);
        entity.setEstado(this.estado);
        entity.setLogin(this.login);
        entity.setTipo(this.tipo);
        return entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long pId) {
        id = pId;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer pEstado) {
        estado = pEstado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String pNombre) {
        nombre = pNombre;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer pTipo) {
        tipo = pTipo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String pLogin) {
        login = pLogin;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String pContrasena) {
        contrasena = pContrasena;
    }
}
