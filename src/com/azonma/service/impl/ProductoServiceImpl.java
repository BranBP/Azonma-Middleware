package com.azonma.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.azonma.dao.ProductoDAO;
import com.azonma.dao.impl.ProductoDAOImpl;
import com.azonma.exceptions.DataException;
import com.azonma.model.Producto;
import com.azonma.model.criteria.ProductoCriteria;
import com.azonma.service.ProductoService;
import com.azonma.util.DBUtils;
import com.azonma.util.JDBCUtils;

public class ProductoServiceImpl implements ProductoService{

	private static Logger logger = LogManager.getLogger(ProductoServiceImpl.class.getName());
	
	private ProductoDAO dao = null; 

	public ProductoServiceImpl() {
		dao = new ProductoDAOImpl();
	}

	@Override
	public Producto findById(Long id) throws DataException {

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
	public List<Producto> findByCriteria(ProductoCriteria c, Integer startIndex, Integer timesCount) throws DataException { 

		Connection connection = null;

		try {

			connection = DBUtils.conectar(); // ConnectionManager.getConnection();
			connection.setAutoCommit(true);

			return dao.findByCriteria(connection, c, startIndex, timesCount);	

		} catch (SQLException e){
			logger.error(e.getMessage(),e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection); 
		}
	}

}
