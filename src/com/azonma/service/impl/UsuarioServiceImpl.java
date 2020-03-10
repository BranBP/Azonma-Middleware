package com.azonma.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.azonma.dao.UsuarioDAO;
import com.azonma.dao.impl.UsuarioDAOImpl;
import com.azonma.exceptions.DataException;
import com.azonma.exceptions.MailException;
import com.azonma.model.Usuario;
import com.azonma.model.criteria.UsuarioCriteria;
import com.azonma.service.MailService;
import com.azonma.service.UsuarioService;
import com.azonma.util.DBUtils;
import com.azonma.util.JDBCUtils;

public class UsuarioServiceImpl implements UsuarioService{

	private static Logger logger = LogManager.getLogger(UsuarioServiceImpl.class.getName());
 
	private UsuarioDAO dao = null;

	public UsuarioServiceImpl() {
		dao = new UsuarioDAOImpl();
	}

	@Override
	public Usuario findById(Long id) throws DataException {

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
	public Usuario create(Usuario u) throws DataException, MailException { 

		Connection connection = null;
		boolean commit = false;

		try {
			connection = DBUtils.conectar(); // ConnectionManager.getConnection();

			connection.setTransactionIsolation(
					Connection.TRANSACTION_READ_COMMITTED);		

			connection.setAutoCommit(false);

			u = dao.create(connection, u);
			MailService mail = new MailServiceImpl();
			mail.sendMail("BIENVENIDO A AZONMA", "Gracias por registrarte en Azonma", u.getEmail());
			commit = true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConnection(connection, commit); 
		}

		return u;
	}

	@Override
	public void update(Usuario u, Long id) throws DataException {

		Connection connection = null;

		try {

			connection = DBUtils.conectar(); // ConnectionManager.getConnection();
			connection.setAutoCommit(true);

			dao.update(connection, u, id);	 

		} catch (SQLException e){
			logger.error(e.getMessage(),e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection); 
		}
	}

	@Override
	public void updateEstado(Long id, Integer idEstado) throws DataException {

		Connection connection = null;

		try {

			connection = DBUtils.conectar(); // ConnectionManager.getConnection();
			connection.setAutoCommit(true);

			dao.updateEstado(connection, id, idEstado);	  

		} catch (SQLException e){
			logger.error(e.getMessage(),e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection); 
		}
	}

	@Override
	public void delete(Long id) throws DataException {

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
