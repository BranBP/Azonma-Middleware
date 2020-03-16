package com.azonma.service;

import java.util.List;

import com.azonma.exceptions.DataException;
import com.azonma.model.Direccion;

public interface DireccionService {

	public Direccion findById(Long id) throws DataException;  

	public List<Direccion> findByUsuario(Long idUsuario) throws DataException;  

	public List<Direccion> findByLocalidad(Long idLocalidad) throws DataException;   

	public Direccion create(Direccion d) throws DataException; 
}
