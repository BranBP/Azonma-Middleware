package com.azonma.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Localidad {

	private long id = 0;
	private String ciudad = null;
	private long idProvincia = 0;
	
	List<Direccion> direcciones = null;

	public Localidad() {
		direcciones = new ArrayList<Direccion>(); 
	}

	public List<Direccion> getDirecciones() {
		return Collections.unmodifiableList(direcciones);   
	}

	public void setDirecciones(List<Direccion> direcciones) {
		this.direcciones = direcciones;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public long getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(long idProvincia) {
		this.idProvincia = idProvincia;
	}

	@Override
	public String toString() {
		return "Localidad [id=" + id + ", ciudad=" + ciudad + ", idProvincia=" + idProvincia + ", direcciones="
				+ direcciones + "]";
	} 
}
