package com.azonma.service;

import java.util.List;

import com.azonma.exceptions.DataException;
import com.azonma.model.Usuario;
import com.azonma.model.criteria.UsuarioCriteria;

public interface UsuarioService {
	
	public Usuario findById(long id) throws DataException; 
	
	public List<Usuario> findByCriteria(UsuarioCriteria c) throws DataException; 

	public Usuario create(Usuario u) throws DataException;

	public void update(long id, Usuario u) throws DataException; 

	public void delete(long id) throws DataException;
}
