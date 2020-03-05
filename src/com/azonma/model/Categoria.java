package com.azonma.model;

import java.util.ArrayList;
import java.util.List;

public class Categoria { 

	private long id = 0;
	private String nombre = null;
	private String idioma = null; 
	private Long idPadre = null;
	
	private List<CategoriaIdioma> idiomas = new ArrayList<CategoriaIdioma>();
	
	public Categoria() {
	}

	public List<CategoriaIdioma> getIdiomas() {
		return idiomas;
	}

	public void setIdiomas(List<CategoriaIdioma> idiomas) {
		this.idiomas = idiomas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getIdPadre() {
		return idPadre;
	}

	public void setIdPadre(Long idPadre) {
		this.idPadre = idPadre;
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nombre=" + nombre + ", idioma=" + idioma + ", idPadre=" + idPadre
				+ ", idiomas=" + idiomas + "]";
	}
}
