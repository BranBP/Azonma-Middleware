package com.azonma.model;

public class Direccion {

	private long id = 0;
	private String nombre = null;
	private String calle = null;
	private long idLocalidad = 0;
	private long idUsuario = 0;

	public Direccion() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public long getIdLocalidad() {
		return idLocalidad;
	}

	public void setIdLocalidad(long idLocalidad) {
		this.idLocalidad = idLocalidad;
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Override
	public String toString() {
		return "Direccion [id=" + id 
				+", nombre=" + nombre 
				+", calle=" + calle 
				+", idLocalidad=" + idLocalidad
				+", idUsuario=" + idUsuario + "]";
	}
}
