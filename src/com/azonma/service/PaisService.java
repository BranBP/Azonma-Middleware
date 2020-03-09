package com.azonma.service;

import java.util.List;

import com.azonma.exceptions.DataException;
import com.azonma.model.Pais;

public interface PaisService {

	public Pais findById(Long id) throws DataException; 

	public List<Pais> findByNombre(String nombre) throws DataException;    

}
