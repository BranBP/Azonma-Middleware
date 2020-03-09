package com.azonma.service;

import java.util.List;

import com.azonma.exceptions.DataException;
import com.azonma.model.Provincia;

public interface ProvinciaService {

	public Provincia findById(Long id) throws DataException;

	public List<Provincia> findByNombre(String nombre) throws DataException;  

	public List<Provincia> findByPais(Long idPais) throws DataException; 
}
