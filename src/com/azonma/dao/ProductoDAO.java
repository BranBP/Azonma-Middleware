package com.azonma.dao;

import java.sql.Connection;
import java.util.List;

import com.azonma.exceptions.DataException;
import com.azonma.model.Producto;
import com.azonma.model.criteria.ProductoCriteria;

public interface ProductoDAO {

	public Producto findById(Connection cn, long id) throws DataException;

	public List<Producto> findByCriteria(Connection cn, ProductoCriteria c, int startIndex, int timesCount) throws DataException;

	public List<Producto> findByCategoria(Connection connection, long idCategoria) throws DataException; 

	//	No están implementados ni el create ni el update ni el delete de momento

}