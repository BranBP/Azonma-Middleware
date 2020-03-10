package com.azonma.service;

import java.util.ArrayList;
import java.util.List;

import com.azonma.exceptions.DataException;
import com.azonma.model.Categoria;
import com.azonma.service.impl.CategoriaServiceImpl;

public class CategoriaTest {

	private CategoriaService service = null;

	public CategoriaTest() {
		service = new CategoriaServiceImpl(); 
	}

	public void findById() throws DataException{

		Categoria c = service.findById(1l);
		System.out.println(c);
	}

	public void findAll() throws DataException{
		List<Categoria> categorias = new ArrayList<Categoria>();
		categorias = service.findAll("espa—ol", 1, 20);
		for(Categoria c: categorias) {
			System.out.println(c); 
		}
	}

	public static void main(String[] args) throws DataException{ 
		CategoriaTest test = new CategoriaTest();

		test.findById(); 
		test.findAll();

	}

}
