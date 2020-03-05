package com.azonma.model;

public class Linea {

	private long id = 0;
	private double precioUnitario = 0d;
	private int unidades = 0;
	private double precioTotal = 0d;
	private Integer valoracion = null;
	private long idProducto = 0;
	private long idPedido = 0;

	public Linea() {
	}

	public long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
	}

	public long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}

	public void setValoracion(Integer valoracion) {
		this.valoracion = valoracion;
	}

	public Integer getValoracion() { 
		return valoracion;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}

	@Override
	public String toString() {
		return "Linea [id=" + id 
				+", precioUnitario=" + precioUnitario 
				+", unidades=" + unidades 
				+", precioTotal=" + precioTotal 
				+", valoracion=" + valoracion 
				+", idProducto=" + idProducto 
				+", idPedido=" + idPedido + "]";
	}
}
