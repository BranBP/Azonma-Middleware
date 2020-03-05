package com.azonma.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.azonma.dao.CategoriaDAO;
import com.azonma.exceptions.DataException;
import com.azonma.model.Categoria;
import com.azonma.model.Direccion;
import com.azonma.util.JDBCUtils;
import com.mysql.jdbc.Statement;

public class CategoriaDAOImpl implements CategoriaDAO{ 


	@Override
	public Categoria findById(Connection connection, long id) throws DataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Categoria> findAll(Connection connection, int startIndex, int timesCount) throws DataException {   

		List<Direccion> direcciones = new ArrayList<Direccion>();	  
		Direccion r = null; 

		PreparedStatement preparedStatement = null;  
		String query = null;
		ResultSet rs = null;

		try {
			query =(
					" SELECT ID_DIRECCION, NOMBRE, CALLE, ID_LOCALIDAD, ID_USUARIO "
							+ " FROM DIRECCION "
					);

			if(logger.isDebugEnabled()) {
				logger.debug("Query: {} ", query);
			}
			
			preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS); 

			rs = preparedStatement.getGeneratedKeys();
			int currentCount = 0;

			if ((startIndex >=1) && rs.absolute(startIndex)) {
				do {
					r = loadNext(rs);   
					direcciones.add(r);
					currentCount++; 
				}while ((currentCount < timesCount) && rs.next());  
			}

			if(direcciones.size() == 0) {
				logger.warn("Warn. Direcciones no encontradas..."); 
			}else {
				logger.info("Han salido {} resultados", direcciones.size());
			} 

		} catch (SQLException e) {
			logger.error("Error. Query completa {} ", preparedStatement.toString());  
		}

		finally {            
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(preparedStatement);
		}

		return direcciones;
	}
}
