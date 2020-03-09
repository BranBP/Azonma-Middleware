package com.azonma.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.azonma.dao.ProvinciaDAO;
import com.azonma.exceptions.DataException;
import com.azonma.model.Provincia;
import com.azonma.service.ProvinciaService;
import com.azonma.util.DBUtils;
import com.azonma.util.JDBCUtils;

public class ProvinciaServiceImpl implements ProvinciaService{

	private static Logger logger = LogManager.getLogger(ProvinciaServiceImpl.class.getName());

	private ProvinciaDAO dao = null;

	@Override
	public Provincia findById(Long id) throws DataException {

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
	public List<Provincia> findByNombre(String nombre) throws DataException {

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

	@Override
	public List<Provincia> findByPais(Long idPais) throws DataException {
		// TODO Auto-generated method stub
		return null;
	}

}
