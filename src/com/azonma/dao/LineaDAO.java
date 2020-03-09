package com.azonma.dao;

import java.sql.Connection;
import java.util.List;

import com.azonma.exceptions.DataException;
import com.azonma.model.Linea;

public interface LineaDAO { 

	public Linea findById(Connection connection, long id) throws DataException;

	public List<Linea> findByPedido(Connection connection, long idPedido) throws DataException;

	public void create(Connection connection, Linea l) throws DataException;

	//	public void deleteByPedido(Connection connection, long idPedido) throws DataException;

}
