package com.azonma.model;

public class Pais {

	private long id = 0;
	private char iso;
	private String nombre = null;

	public Pais() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public char getIso() {
		return iso;
	}

	public void setIso(char iso) {
		this.iso = iso;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Pais [id=" + id 
				+", iso=" + iso 
				+", nombre=" + nombre + "]";
	}
}
