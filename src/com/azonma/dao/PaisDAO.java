package com.azonma.dao;

import java.sql.Connection;
import java.util.List;

import com.azonma.exceptions.DataException;
import com.azonma.model.Pais;

public interface PaisDAO {

	public Pais findById(Connection connection, long id) throws DataException; 

	public List<Pais> findByNombre(Connection connection, String nombre) throws DataException;       

	//	No existirá create(), delete() ni update(), puesto que son datos fijos
}
