package com.azonma.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.azonma.dao.DireccionDAO;
import com.azonma.dao.impl.DireccionDAOImpl;
import com.azonma.exceptions.DataException;
import com.azonma.model.Direccion;
import com.azonma.service.DireccionService;
import com.azonma.util.DBUtils;
import com.azonma.util.JDBCUtils; 

public class DireccionServiceImpl implements DireccionService{ 

	private static Logger logger = LogManager.getLogger(DireccionServiceImpl.class.getName()); 
	 
		private DireccionDAO dao = null;

		public DireccionServiceImpl() { 
			dao = new DireccionDAOImpl();
		}

	@Override
	public Direccion findById(Long id) throws DataException {

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
	public List<Direccion> findByUsuario(Long idUsuario) throws DataException {

		Connection connection = null;

		try {

			connection = DBUtils.conectar(); // ConnectionManager.getConnection();
			connection.setAutoCommit(true);

			return dao.findByUsuario(connection, idUsuario);	 

		} catch (SQLException e){
			logger.error(e.getMessage(),e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection); 
		}

	}

	@Override
	public List<Direccion> findByLocalidad(Long idLocalidad) throws DataException {

		Connection connection = null;

		try {

			connection = DBUtils.conectar(); // ConnectionManager.getConnection();
			connection.setAutoCommit(true);

			return dao.findByLocalidad(connection, idLocalidad);	 

		} catch (SQLException e){
			logger.error(e.getMessage(),e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection); 
		}

	}

	@Override
	public Direccion create(Direccion d) throws DataException { 

		Connection connection = null;
		boolean commit = false;

		try {
			connection = DBUtils.conectar(); // ConnectionManager.getConnection();

			connection.setTransactionIsolation(
					Connection.TRANSACTION_READ_COMMITTED);		

			connection.setAutoCommit(false);

			d = dao.create(connection, d); 
			commit = true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConnection(connection, commit); 
		}

		return d;
	}

}
