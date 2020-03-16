package com.azonma.service;

import java.util.ArrayList;
import java.util.List;

import com.azonma.exceptions.DataException;
import com.azonma.model.Direccion;
import com.azonma.service.impl.DireccionServiceImpl;

public class DireccionTest {

	private DireccionService service = null;

	public DireccionTest() {
		service = new DireccionServiceImpl();
	}

	public void FindById() throws DataException {

		Direccion d = new Direccion(); 

		d = service.findById(1l);

		System.out.println(d);
	}

	public void findByUsuario() throws DataException{

		List<Direccion> direcciones = new ArrayList<Direccion>();

		direcciones = service.findByUsuario(1l);

		for(Direccion d: direcciones) {
			System.out.println(d); 
		}
	}

	public void findByLocalidad() throws DataException{ 

		List<Direccion> direcciones = new ArrayList<Direccion>();

		direcciones = service.findByLocalidad(1l); 

		for(Direccion d: direcciones) {
			System.out.println(d); 
		}
	}

	public void create() throws DataException{

		Direccion d = new Direccion();

		d.setIdLocalidad(3);
		d.setIdUsuario(1);
		d.setNombre("Casa Vigo");
		d.setCalle("Bailen");

		d = service.create(d);

		System.out.println(d);
	}

	public static void main(String[] args) throws DataException {

		DireccionTest test = new DireccionTest();

		test.FindById();  
		test.findByUsuario();
		test.findByLocalidad();
		test.create(); 
	}

}
