/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.dtos;

/**
 *
 * @author jj.silva
 */
public class ClienteDTO
{
    private Long id;
    
    private int estado;
    
    private String nombre;
    
    private int tipo;
    
    private String login;
    
    private String contrasena;
    
    public ClienteDTO()
    {
        
    }
    
    public Long getId()
    {
        return id;
    }
    
    public void setId(Long pId)
    {
        id = pId;
    }
    
    public int getEstado()
    {
        return estado;
    }
    
    public void setEstado(int pEstado)
    {
        estado = pEstado;
    }
    
    public String getNombre()
    {
        return nombre;
    }
    
    public void setNombre(String pNombre)
    {
        nombre = pNombre;
    }
    
    public int getTipo()
    {
        return tipo;
    }
    
    public void setTipo(int pTipo)
    {
        tipo = pTipo;
    }
    
    public String getLogin()
    {
        return login;
    }
    
    public void setLogin(String pLogin)
    {
        login = pLogin;
    }
    
    public String getContrasena()
    {
        return contrasena;
    }
    
    public void setContrasena(String pContrasena)
    {
        contrasena = pContrasena;
    }
}
