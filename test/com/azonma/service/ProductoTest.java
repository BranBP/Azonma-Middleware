package com.azonma.service;

import java.util.ArrayList;
import java.util.List;

import com.azonma.exceptions.DataException;
import com.azonma.model.Producto;
import com.azonma.model.criteria.ProductoCriteria;
import com.azonma.service.impl.ProductoServiceImpl;

public class ProductoTest {

	private ProductoService service = null;

	public ProductoTest() {
		service = new ProductoServiceImpl();
	}

	public void findById() throws DataException {

		Producto p = new Producto(); 

		p = service.findById((long) 12);
		System.out.println(p);
	}

	public void FindByCriteria() throws DataException {

		List<Producto> productos = new ArrayList <Producto>();
		
		ProductoCriteria pc = new ProductoCriteria();

		pc.setNombre("a");
 
		productos = service.findByCriteria(pc, 1, Integer.MAX_VALUE);

		for(Producto p: productos) {
			System.out.println(p);  
		}
	}

	public static void main(String[] args) throws DataException {

		ProductoTest test = new ProductoTest();

		test.FindByCriteria();
//		test.findById();

	}

}
