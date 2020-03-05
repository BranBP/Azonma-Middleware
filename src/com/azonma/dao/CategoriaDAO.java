package com.azonma.dao;

import java.sql.Connection;
import java.util.List;

import com.azonma.exceptions.DataException;
import com.azonma.model.Categoria;

public interface CategoriaDAO {

	public Categoria findById(Connection connection, long id) throws DataException;  

	public List<Categoria> findAll(Connection connection, int startIndex, int timesCount) throws DataException;  

	public Categoria create(Connection connection, Categoria c) throws DataException; 	
	
}
