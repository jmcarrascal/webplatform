package ar.com.jmc.webplatform.base.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Log implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private Timestamp dateMovimiento;
	
	private String errorDescrip;
	
	private String nombreArchivo;
	
	private String tipoMovimiento;
	
	private Long idUsuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getDateMovimiento() {
		return dateMovimiento;
	}

	public void setDateMovimiento(Timestamp dateMovimiento) {
		this.dateMovimiento = dateMovimiento;
	}

	public String getErrorDescrip() {
		return errorDescrip;
	}

	public void setErrorDescrip(String errorDescrip) {
		this.errorDescrip = errorDescrip;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}