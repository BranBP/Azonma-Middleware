package com.azonma.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Pedido {

	private long id = 0;
	private Date fecha = null;
	private double precioTotal = 0.0;
	private long idUsuario = 0;
	private int idEstado = 0;

	private List<Linea> lineas;

	public Pedido() {
		lineas = new ArrayList<Linea>();
	}

	public List<Linea> getLineas() { 
		return Collections.unmodifiableList(lineas); 
	}

	public void setLineas(List<Linea> lineas) {
		this.lineas = lineas;
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getPrecioTotal() { 
		return precioTotal; 
	}

	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id 
				+", fecha=" + fecha 
				+", precioTotal=" + precioTotal 
				+", idUsuario=" + idUsuario
				+", idEstado=" + idEstado 
				+", lineas=" + lineas + "]"; 
	}

}
