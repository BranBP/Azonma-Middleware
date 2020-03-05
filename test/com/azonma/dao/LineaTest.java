package com.azonma.dao;

import java.sql.Connection;

import com.azonma.dao.impl.LineaDAOImpl;
import com.azonma.exceptions.DataException;
import com.azonma.model.Linea;
import com.azonma.util.DBUtils;

public class LineaTest {

	private LineaDAO dao = null;

	public LineaTest() {
		dao = new LineaDAOImpl();
	}

	//	Esto es para probar que funciona el findById de LineaDAO
	public void findById() throws DataException{

		Linea l = new Linea();
		Connection connection = null;
		
		connection = DBUtils.conectar();
		
		l = dao.findById(connection, 1); 
		System.out.println(l);
	}

	public static void main(String[] args) throws DataException{

		LineaTest test = new LineaTest();

		test.findById();

	}

}
