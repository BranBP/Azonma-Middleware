package com.azonma.service;

import java.util.List;

import com.azonma.exceptions.DataException;
import com.azonma.model.Localidad;

public interface LocalidadService {

	public Localidad findById(Long id) throws DataException;
 
	public List<Localidad> findByCiudad(String nombre) throws DataException;  

	public List<Localidad> findByProvincia(Long idProvincia) throws DataException; 
}
