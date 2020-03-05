package com.azonma.model;

public class Provincia {

	private long id = 0;
	private String nombre = null;
	private long idPais = 0;

	public Provincia() {
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

	public long getIdPais() {
		return idPais;
	}

	public void setIdPais(long idPais) {
		this.idPais = idPais;
	}

	@Override
	public String toString() {
		return "Provincia [id=" + id 
				+", nombre=" + nombre 
				+", idPais=" + idPais + "]";
	}
}
