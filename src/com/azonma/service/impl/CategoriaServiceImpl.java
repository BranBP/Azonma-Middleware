package com.azonma.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.azonma.dao.CategoriaDAO;
import com.azonma.dao.impl.CategoriaDAOImpl;
import com.azonma.exceptions.DataException;
import com.azonma.model.Categoria;
import com.azonma.service.CategoriaService;
import com.azonma.util.DBUtils;
import com.azonma.util.JDBCUtils;

public class CategoriaServiceImpl  implements CategoriaService{

	private static Logger logger = LogManager.getLogger(CategoriaServiceImpl.class.getName()); 

	private CategoriaDAO dao = null;

	public CategoriaServiceImpl() {
		dao = new CategoriaDAOImpl(); 
	}

	@Override
	public Categoria findById(Long id) throws DataException {

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
	public List<Categoria> findAll(String idioma, Integer startIndex, Integer timesCount) throws DataException {

		Connection connection = null;

		try {

			connection = DBUtils.conectar(); // ConnectionManager.getConnection();
			connection.setAutoCommit(true);

			return dao.findAll(connection, idioma, startIndex, timesCount);	 

		} catch (SQLException e){
			logger.error(e.getMessage(),e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection); 
		}
	}

}
