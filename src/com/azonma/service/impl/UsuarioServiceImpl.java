package com.azonma.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.azonma.dao.UsuarioDAO;
import com.azonma.dao.impl.UsuarioDAOImpl;
import com.azonma.exceptions.DataException;
import com.azonma.model.Usuario;
import com.azonma.model.criteria.UsuarioCriteria;
import com.azonma.service.UsuarioService;
import com.azonma.util.DBUtils;
import com.azonma.util.JDBCUtils;

public class UsuarioServiceImpl implements UsuarioService{

	private static Logger logger = LogManager.getLogger(PedidoServiceImpl.class.getName());

	private UsuarioDAO dao = null;

	public UsuarioServiceImpl() {
		dao = new UsuarioDAOImpl();
	}

	@Override
	public Usuario findById(long id) throws DataException {

		Connection connection = null;

		try {

			connection = DBUtils.conectar(); // ConnectionManager.getConnection();
			connection.setAutoCommit(true);

			return dao.findById(connection, id);	

		} catch (SQLException e){
			logger.error(e.getMessage(),e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection); 
		}

	}

	@Override
	public Usuario create(Usuario u) throws DataException { 

		Connection connection = null;
		boolean commit = false;

		try {
			connection = DBUtils.conectar(); // ConnectionManager.getConnection();

			connection.setTransactionIsolation(
					Connection.TRANSACTION_READ_COMMITTED);		

			connection.setAutoCommit(false);

			u = dao.create(connection, u);
			commit = true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConnection(connection, commit); 
		}

		return u;
	}

	@Override
	public void update(long id, Usuario u) throws DataException {

		Connection connection = null;

		try {

			connection = DBUtils.conectar(); // ConnectionManager.getConnection();
			connection.setAutoCommit(true);

			dao.update(connection, id, u);	

		} catch (SQLException e){
			logger.error(e.getMessage(),e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection); 
		}
	}

	@Override
	public void delete(long id) throws DataException {

		Connection connection = null;

		try {

			connection = DBUtils.conectar(); // ConnectionManager.getConnection();
			connection.setAutoCommit(true);

			dao.delete(connection, id);	 

		} catch (SQLException e){
			logger.error(e.getMessage(),e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection); 
		}
	}

	@Override
	public List<Usuario> findByCriteria(UsuarioCriteria c) throws DataException {

		Connection connection = null;

		try {

			connection = DBUtils.conectar(); // ConnectionManager.getConnection();
			connection.setAutoCommit(true);

			return dao.findByCriteria(connection, c);	

		} catch (SQLException e){
			logger.error(e.getMessage(),e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection); 
		}
	}

}
