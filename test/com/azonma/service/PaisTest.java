package com.azonma.service;

import java.util.ArrayList;
import java.util.List;

import com.azonma.exceptions.DataException;
import com.azonma.model.Pais;
import com.azonma.service.impl.PaisServiceImpl;

public class PaisTest {

	private PaisService service = null; 

	public PaisTest() {
		service = new PaisServiceImpl();  
	}

	public void findById() throws DataException{

		Pais p = new Pais(); 

		p = service.findById(73l); 

		System.out.println(p);
	}

	public void findByNombre() throws DataException{ 

		List<Pais> paises = new ArrayList<Pais>();

		paises = service.findByNombre("h"); 

		for(Pais p: paises) { 
			System.out.println(p);
		}
	}

	public static void main(String[] args) throws DataException{ 

		PaisTest test = new PaisTest();

		test.findById(); 
		test.findByNombre(); 

	}

}
