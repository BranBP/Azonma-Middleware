package com.azonma.service;

import java.util.List;

import com.azonma.exceptions.DataException;
import com.azonma.exceptions.MailException;
import com.azonma.model.Usuario;
import com.azonma.model.criteria.UsuarioCriteria;

public interface UsuarioService {
	
	public Usuario findById(Long id) throws DataException;  
	
	public List<Usuario> findByCriteria(UsuarioCriteria c) throws DataException; 

	public Usuario create(Usuario u) throws DataException, MailException;

	public void update(Usuario u, Long id) throws DataException; 
	
	public void updateEstado(Long id, Integer idEstado) throws DataException; 

	public void delete(Long id) throws DataException; 
}
