package com.azonma.dao;

import java.sql.Connection;
import java.util.List;

import com.azonma.exceptions.DataException;
import com.azonma.model.Localidad;

public interface LocalidadDAO { 

	public Localidad findById(Connection connection, long id) throws DataException;

	public List<Localidad> findByCiudad(Connection connection, String nombreCiudad) throws DataException;  

	public List<Localidad> findByProvincia(Connection connection, long idProvincia) throws DataException;     

	//	No existirá create(), delete() ni update(), puesto que son datos fijos

}
