package com.azonma.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Provincia {

	private long id = 0;
	private String nombre = null;
	private long idPais = 0;
	
	private List<Localidad> localidades = null;

	public Provincia() {
		localidades = new ArrayList<Localidad>(); 
	}

	public List<Localidad> getLocalidades() {
		return Collections.unmodifiableList(localidades);    
	}

	public void setLocalidades(List<Localidad> localidades) {
		this.localidades = localidades;
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
		return "Provincia [id=" + id + ", nombre=" + nombre + ", idPais=" + idPais + ", localidades=" + localidades
				+ "]";
	}
}
