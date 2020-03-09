package com.azonma.model;

import java.util.Date;

import com.azonma.util.PasswordEncryption;

public class Usuario {

	private long id = 0;
	private String email = null; 
	private String contrasena = null;
	private Date fechaNacimiento = null;
	private String nombre = null;
	private String apellido1 = null;
	private String apellido2 = null;
	private String idSexo; 
	private long idIdioma = 0;
	private String estado = null;

	public Usuario() {
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getIdSexo() {
		return idSexo;
	}

	public void setIdSexo(String idSexo) {
		this.idSexo = idSexo;
	}

	public long getIdIdioma() {
		return idIdioma;
	}

	public void setIdIdioma(long idIdioma) { 
		this.idIdioma = idIdioma; 
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", email=" + email + ", contrasena=" + PasswordEncryption.encryptPassword(contrasena)
		+", fechaNacimiento=" + fechaNacimiento + ", nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2=" + apellido2
		+", idSexo=" + idSexo + ", idIdioma=" + idIdioma + ", estado=" + estado + "]"; 
	}
}
