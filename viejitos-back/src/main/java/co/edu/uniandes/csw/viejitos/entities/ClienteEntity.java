/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jj.silva
 */
@Entity
@XmlRootElement
public class ClienteEntity extends BaseEntity implements Serializable
{
    private String atributo;

	/**
	 * @return the atribute
	 */
	public String getAtributo( )
	{
		return atributo;
	}

	/**
	 * @param atributo the atribute to set
	 */
	public void setAtributo( String atributo )
	{
		this.atributo = atributo;
	}
}
