package com.azonma.dao;

import java.sql.Connection;
import java.util.List;

import com.azonma.exceptions.DataException;
import com.azonma.model.Categoria;

public interface CategoriaDAO {

	public Categoria findById(Connection connection, long id) throws DataException;  

	public List<Categoria> findAll(Connection connection, String idioma, int startIndex, int timesCount) throws DataException;   

	//	No están implementados ni el create ni el update ni el delete de momento
	//	Falta el lado de la empresa, es decir la aplicación propia para crear una categoria, producto...etc

}
