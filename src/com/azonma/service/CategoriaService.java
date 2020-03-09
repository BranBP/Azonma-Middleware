package com.azonma.service;

import java.util.List;

import com.azonma.exceptions.DataException;
import com.azonma.model.Categoria;

public interface CategoriaService {

	public Categoria findById(Long id) throws DataException;  

	public List<Categoria> findAll(Integer startIndex, Integer timesCount) throws DataException;   

}
