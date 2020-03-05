package com.azonma.dao.impl;

import java.sql.Connection;
import java.util.List;

import com.azonma.dao.CategoriaIdiomaDAO;
import com.azonma.exceptions.DataException;
import com.azonma.model.CategoriaIdioma;

public class CategoriaIdiomaDAOImpl implements CategoriaIdiomaDAO{

	@Override 
	public CategoriaIdioma findById(Connection connection, long id) throws DataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoriaIdioma> findByCategoria(Connection connection, long idPedido) throws DataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Connection connection, CategoriaIdioma ci) throws DataException {
		// TODO Auto-generated method stub
		
	}

}
