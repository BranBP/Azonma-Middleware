package com.azonma.model;

public class CategoriaIdioma {

	private long idCategoria = 0;
	private long idIdioma = 0;
	private String nombreCategoria = null;

	public CategoriaIdioma() {
	}

	public long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(long idCategoria) { 
		this.idCategoria = idCategoria;
	}

	public long getIdIdioma() {
		return idIdioma;
	}

	public void setIdIdioma(long idIdioma) {
		this.idIdioma = idIdioma;
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	@Override
	public String toString() {
		return "CategoriaIdioma [idCategoria=" + idCategoria + ", idIdioma=" + idIdioma + ", nombreCategoria="
				+ nombreCategoria + "]";
	}
}
