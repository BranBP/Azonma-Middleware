package com.azonma.dao;

import java.sql.Connection;
import java.util.List;

import com.azonma.exceptions.DataException;
import com.azonma.model.Direccion;

public interface DireccionDAO {

	public Direccion findById(Connection connection, long id) throws DataException; 

	public List<Direccion> findByUsuario(Connection connection, long idUsuario) throws DataException; 

	public List<Direccion> findByLocalidad(Connection connection, long idLocalidad) throws DataException;  
	
	public Direccion create(Connection connection, Direccion d) throws DataException;
}
