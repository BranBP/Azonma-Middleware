package com.azonma.dao;

import java.sql.Connection;

import com.azonma.exceptions.DataException;
import com.azonma.model.Direccion;

public interface DireccionDAO {

	public Direccion findById(Connection connection, long id) throws DataException; 
	
	public Direccion create(Connection connection, Direccion d) throws DataException;
}
