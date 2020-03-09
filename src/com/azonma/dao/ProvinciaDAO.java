package com.azonma.dao;

import java.sql.Connection;
import java.util.List;

import com.azonma.exceptions.DataException;
import com.azonma.model.Provincia;

public interface ProvinciaDAO {

	public Provincia findById(Connection connection, long id) throws DataException;

	public List<Provincia> findByNombre(Connection connection, String nombre) throws DataException;  

	public List<Provincia> findByPais(Connection connection, long idPais) throws DataException;     

	//	No existirá create(), delete() ni update(), puesto que son datos fijos

}
