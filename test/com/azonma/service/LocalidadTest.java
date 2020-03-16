package com.azonma.service;

import java.util.ArrayList;
import java.util.List;

import com.azonma.exceptions.DataException;
import com.azonma.model.Localidad;
import com.azonma.service.impl.LocalidadServiceImpl;

public class LocalidadTest {

	private LocalidadService service = null; 

	public LocalidadTest() {
		service = new LocalidadServiceImpl();  
	}

	public void findById() throws DataException{

		Localidad l = new Localidad(); 

		l = service.findById(1l);

		System.out.println(l);
	}

	public void findByCiudad() throws DataException{ 

		List<Localidad> localidades = new ArrayList<Localidad>();

		localidades = service.findByCiudad("h");

		for(Localidad l: localidades) { 
			System.out.println(l);
		}
	}

	public void findByProvincia() throws DataException{  

		List<Localidad> localidades = new ArrayList<Localidad>();

		localidades = service.findByProvincia(27l); 

		for(Localidad l: localidades) { 
			System.out.println(l);
		}
	}

	public static void main(String[] args) throws DataException{ 

		LocalidadTest test = new LocalidadTest();

		test.findById(); 
		test.findByCiudad(); 
		test.findByProvincia();

	}

}
