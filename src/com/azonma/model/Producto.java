package com.azonma.model;

public class Producto {

	private long idProducto = 0;
	private String nombre = null;
	private double precio = 0.0;
	private String descripcion = null;
	private String idioma = null;
	private int idCategoria = 0;
	private double valoracion = 0d;

	public Producto() {
	}

	public double getValoracion() {
		return valoracion;
	}

	public void setValoracion(double valoracion) {
		this.valoracion = valoracion;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto 
				+", nombre=" + nombre  
				+", precio=" + precio 
				+", descripcion=" + descripcion 
				+", idioma=" + idioma 
				+", idCategoria=" + idCategoria 
				+", valoracion=" + valoracion + "]";
	}
}
