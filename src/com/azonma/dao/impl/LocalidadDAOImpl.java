package com.azonma.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.azonma.dao.LocalidadDAO;
import com.azonma.exceptions.DataException;
import com.azonma.model.Localidad;
import com.azonma.util.JDBCUtils;
import com.azonma.util.QueryUtils;

public class LocalidadDAOImpl implements LocalidadDAO{ 

	private static Logger logger = LogManager.getLogger(LocalidadDAOImpl.class.getName()); 

	@Override
	public Localidad findById(Connection connection, long id) throws DataException{  

		Localidad localidad = new Localidad();  
		PreparedStatement preparedStatement = null;
		String query = null;
		ResultSet rs = null;

		try {

			StringBuilder stringBuilder = new StringBuilder( 
					" SELECT ID_LOCALIDAD, CIUDAD, ID_PROVINCIA FROM LOCALIDAD "
					); 

			boolean first = true;

			first = QueryUtils.addClause(id, stringBuilder, first, " ID_LOCALIDAD = ? ");

			query = stringBuilder.toString();
			preparedStatement = connection.prepareStatement(query);

			if(logger.isDebugEnabled()) {
				logger.debug("Query: {} ", query); 
			}

			int i = 1;
			preparedStatement.setLong(i++, id);

			rs = preparedStatement.executeQuery(); 

			if(rs.next()) {
				localidad = loadNext(rs); 
			}

		} catch (SQLException e) {
			logger.error("Error. idLocalidad: {}", id);  
		} 

		finally {            
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(preparedStatement);
		} 

		return localidad; 
	}

	@Override
	public List<Localidad> findByCiudad(Connection connection, String nombreCiudad) throws DataException {

		List <Localidad> localidades = new ArrayList<Localidad>();   
		PreparedStatement preparedStatement = null;
		String query = null;
		ResultSet rs = null;

		try {

			StringBuilder stringBuilder = new StringBuilder( 
					" SELECT ID_LOCALIDAD, CIUDAD, ID_PROVINCIA FROM LOCALIDAD "
					); 

			boolean first = true;

			first = QueryUtils.addClause(nombreCiudad, stringBuilder, first, " UPPER(CIUDAD) LIKE UPPER(?) "); 

			query = stringBuilder.toString();
			preparedStatement = connection.prepareStatement(query);

			int i = 1;
			preparedStatement.setString(i++, "%" + nombreCiudad +"%"); 

			rs = preparedStatement.executeQuery();

			while(rs.next()) {
				Localidad r = new Localidad();
				r = loadNext(rs); 
				localidades.add(r);
			} 

			if(localidades.size() == 0) {
				logger.warn("Criterios no encontrados..."); 
			}else {
				logger.info("Han salido {} resultados", localidades.size()); 
			} 

			if(logger.isDebugEnabled()) {
				logger.debug("Query: {} ", query);
			}

		} catch (SQLException e) {
			logger.error("Error: {}", preparedStatement.toString());  
		}

		finally {            
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(preparedStatement);
		}
		return localidades; 
	}

	@Override
	public List<Localidad> findByProvincia(Connection connection, long idProvincia) throws DataException {

		List <Localidad> localidades = new ArrayList<Localidad>();   
		PreparedStatement preparedStatement = null;
		String query = null;
		ResultSet rs = null;

		try {

			StringBuilder stringBuilder = new StringBuilder( 
					" SELECT ID_LOCALIDAD, CIUDAD, ID_PROVINCIA FROM LOCALIDAD "
					); 

			boolean first = true;

			first = QueryUtils.addClause(idProvincia, stringBuilder, first, " ID_PROVINCIA = ? "); 
 
			query = stringBuilder.toString();
			preparedStatement = connection.prepareStatement(query); 

			int i = 1;
			preparedStatement.setLong(i++, idProvincia);  

			rs = preparedStatement.executeQuery();

			while(rs.next()) {
				Localidad r = new Localidad(); 
				r = loadNext(rs); 
				localidades.add(r); 
			}

			if(localidades.size() == 0) { 
				logger.warn("Criterios no encontrados para idProvincia: {}", idProvincia);    
			}else {
				logger.info("Han salido {} resultados", localidades.size()); 
			} 

			if(logger.isDebugEnabled()) {
				logger.debug("Query: {} ", query); 
			}

		}catch (SQLException e) {
			logger.error("Error: {}", preparedStatement.toString());  
		}

		finally {            
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(preparedStatement);
		} 

		return localidades; 
	}

	public Localidad loadNext(ResultSet rs) throws SQLException { 

		Localidad r = new Localidad();
		int i = 1;

		r.setId(rs.getLong(i++));
		r.setCiudad(rs.getString(i++));
		r.setIdProvincia(rs.getLong(i++));

		return r;
	}

}
