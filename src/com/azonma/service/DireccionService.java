package com.azonma.service;

import com.azonma.exceptions.DataException;
import com.azonma.model.Direccion;

public interface DireccionService {

	public Direccion findById(long id) throws DataException;  

	public Direccion create(Direccion d) throws DataException; 
}