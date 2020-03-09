package com.azonma.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.azonma.dao.PaisDAO;
import com.azonma.exceptions.DataException;
import com.azonma.model.Pais;
import com.azonma.service.PaisService;
import com.azonma.util.DBUtils;
import com.azonma.util.JDBCUtils;

public class PaisServiceImpl implements PaisService{

	private static Logger logger = LogManager.getLogger(PaisServiceImpl.class.getName()); 

	private PaisDAO dao = null; 
	
	@Override
	public Pais findById(Long id) throws DataException {

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
	public List<Pais> findByNombre(String nombre) throws DataException {

		Connection connection = null;

		try {

			connection = DBUtils.conectar(); // ConnectionManager.getConnection();
			connection.setAutoCommit(true);

			return dao.findByNombre(connection, nombre);	  

		} catch (SQLException e){
			logger.error(e.getMessage(),e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection); 
		}
	}

}
