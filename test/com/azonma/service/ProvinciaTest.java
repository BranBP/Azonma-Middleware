package com.azonma.service;

import java.util.ArrayList;
import java.util.List;

import com.azonma.exceptions.DataException;
import com.azonma.model.Provincia;
import com.azonma.service.impl.ProvinciaServiceImpl;

public class ProvinciaTest {

	private ProvinciaService service = null; 

	public ProvinciaTest() {
		service = new ProvinciaServiceImpl();  
	}

	public void findById() throws DataException{

		Provincia p = new Provincia(); 

		p = service.findById(1l);

		System.out.println(p);
	}

	public void findByNombre() throws DataException{ 

		List<Provincia> provincias = new ArrayList<Provincia>();

		provincias = service.findByNombre("h");

		for(Provincia p: provincias) { 
			System.out.println(p);
		}
	}

	public void findByPais() throws DataException{  

		List<Provincia> provincias = new ArrayList<Provincia>();

		//		Solo están introducidas las provincias españolas, cuyo idPais es 73
		provincias = service.findByPais(73l); 

		for(Provincia p: provincias) { 
			System.out.println(p);
		}
	}

	public static void main(String[] args) throws DataException{ 

		ProvinciaTest test = new ProvinciaTest();

		test.findById(); 
		test.findByNombre();
		test.findByPais();

	}

}
