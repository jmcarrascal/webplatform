package ar.com.jmc.webplatform.base.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;


@Entity
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long idUsuario;
	
	private String username;

	private String password;

	private String apyn;
	
	private String userAS400;

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getApyn() {
		return apyn;
	}

	public void setApyn(String apyn) {
		this.apyn = apyn;
	}

	public String getUserAS400() {
		return userAS400;
	}

	public void setUserAS400(String userAS400) {
		this.userAS400 = userAS400;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	

}