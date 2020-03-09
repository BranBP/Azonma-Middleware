package com.azonma.service;

import java.util.List;

import com.azonma.exceptions.DataException;
import com.azonma.model.Producto;
import com.azonma.model.criteria.ProductoCriteria;

public interface ProductoService {

	public Producto findById(Long id) throws DataException;
	
	public List<Producto> findByCriteria(ProductoCriteria c, Integer startIndex, Integer timesCount)  
			throws DataException;
}
