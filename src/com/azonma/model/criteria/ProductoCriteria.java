package com.azonma.model.criteria;

public class ProductoCriteria {

	private Integer idCategoria = null;  
	private Double precioDesde = null;
	private Double precioHasta = null;
	private Integer minVal = null;
	private String idioma = null;
	private String nombre = null;

	public ProductoCriteria() {
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

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Double getPrecioDesde() {
		return precioDesde;
	}

	public void setPrecioDesde(Double precioDesde) {
		this.precioDesde = precioDesde;
	}

	public Double getPrecioHasta() {
		return precioHasta;
	}

	public void setPrecioHasta(Double precioHasta) {
		this.precioHasta = precioHasta;
	}

	public Integer getMinVal() {
		return minVal;
	}

	public void setMinVal(Integer minVal) {
		this.minVal = minVal;
	}

}


