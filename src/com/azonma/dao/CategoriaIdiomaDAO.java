package com.azonma.dao;

import java.sql.Connection;
import java.util.List;

import com.azonma.exceptions.DataException;
import com.azonma.model.CategoriaIdioma;

public interface CategoriaIdiomaDAO {
	
	public CategoriaIdioma findById(Connection connection, long id) throws DataException; 

	public List<CategoriaIdioma> findByCategoria(Connection connection, long idPedido) throws DataException;   

	public void create(Connection connection, CategoriaIdioma ci) throws DataException; 
}
