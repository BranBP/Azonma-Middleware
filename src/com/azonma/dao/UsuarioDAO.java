package com.azonma.dao;

import java.sql.Connection;
import java.util.List;

import com.azonma.exceptions.DataException;
import com.azonma.model.Usuario;
import com.azonma.model.criteria.UsuarioCriteria;

public interface UsuarioDAO {

	public Usuario findById(Connection connection, long id) throws DataException;
	
	public List<Usuario> findByCriteria(Connection connection, UsuarioCriteria uc) throws DataException;  

	public Usuario create(Connection connection, Usuario u) throws DataException;

	public void update(Connection connection, long id, Usuario u) throws DataException;   

	public void delete(Connection connection, long id) throws DataException;

}