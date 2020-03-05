package com.azonma.model;

public class Idioma {

	private long id = 0;
	private String nombre = null;

	public Idioma() {
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

	@Override
	public String toString() {
		return "Idioma [id=" + id 
				+", nombre=" + nombre + "]";
	}

}
