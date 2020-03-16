package com.azonma.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.azonma.dao.LocalidadDAO;
import com.azonma.dao.impl.LocalidadDAOImpl;
import com.azonma.exceptions.DataException;
import com.azonma.model.Localidad;
import com.azonma.service.LocalidadService;
import com.azonma.util.DBUtils;
import com.azonma.util.JDBCUtils;

public class LocalidadServiceImpl implements LocalidadService{

	private static Logger logger = LogManager.getLogger(LocalidadServiceImpl.class.getName());

	private LocalidadDAO dao = null;

	public LocalidadServiceImpl() {
		dao = new LocalidadDAOImpl();	
	}

	@Override
	public Localidad findById(Long id) throws DataException { 

		Connection connection = null;

		try {

			connection = DBUtils.conectar(); // ConnectionManager.getConnection();
			connection.setAutoCommit(true);

			return dao.findById(connection, id);	

		} catch (SQLException e){
			logger.error(e.getMessage(),e);
			throw new DataException(e);
		}finally {
			JDBCUtils.closeConnection(connection); 
		}

	}

	@Override
	public List<Localidad> findByCiudad(String nombre) throws DataException {

		Connection connection = null;

		try {

			connection = DBUtils.conectar(); // ConnectionManager.getConnection();
			connection.setAutoCommit(true);

			return dao.findByCiudad(connection, nombre);	   

		} catch (SQLException e){
			logger.error(e.getMessage(),e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection); 
		}
	}

	@Override
	public List<Localidad> findByProvincia(Long idProvincia) throws DataException {

		Connection connection = null;

		try {

			connection = DBUtils.conectar(); // ConnectionManager.getConnection();
			connection.setAutoCommit(true);

			return dao.findByProvincia(connection, idProvincia);	    

		} catch (SQLException e){
			logger.error(e.getMessage(),e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection); 
		}

	}

}
