package com.azonma.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pais {

	private long id = 0;
	private String iso = null; 
	private String nombre = null;

	private List<Provincia> provincias;

	public Pais() {
		provincias = new ArrayList<Provincia>(); 
	}

	public List<Provincia> getProvincias() {
		return Collections.unmodifiableList(provincias);  
	}

	public void setProvincias(List<Provincia> provincias) {
		this.provincias = provincias;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIso() {
		return iso; 
	}

	public void setIso(String iso) {
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
		return "Pais [id=" + id + ", iso=" + iso + ", nombre=" + nombre + ", provincias=" + provincias + "]";
	}
}
