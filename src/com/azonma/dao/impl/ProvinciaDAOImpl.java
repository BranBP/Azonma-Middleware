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
import com.azonma.dao.ProvinciaDAO;
import com.azonma.exceptions.DataException;
import com.azonma.model.Localidad;
import com.azonma.model.Provincia;
import com.azonma.util.JDBCUtils;
import com.azonma.util.QueryUtils;

public class ProvinciaDAOImpl implements ProvinciaDAO{ 

	private static Logger logger = LogManager.getLogger(ProvinciaDAOImpl.class.getName());

	private LocalidadDAO localidadDAO = null;

	public ProvinciaDAOImpl() { 
		localidadDAO = new LocalidadDAOImpl();  
	}

	@Override
	public Provincia findById(Connection connection, long id) throws DataException{ 

		Provincia provincia = null;

		PreparedStatement preparedStatement = null;
		String query = null;
		ResultSet rs = null;

		try {

			StringBuilder stringBuilder = new StringBuilder( 
					" SELECT ID_PROVINCIA, NOMBRE, ID_PAIS FROM PROVINCIA "
					); 

			boolean first = true;

			first = QueryUtils.addClause(id, stringBuilder, first, " ID_PROVINCIA = ? ");

			query = stringBuilder.toString();
			preparedStatement = connection.prepareStatement(query);

			if(logger.isDebugEnabled()) {
				logger.debug("Query: {} ", preparedStatement.toString()); 
			}

			int i = 1;
			preparedStatement.setLong(i++, id);

			rs = preparedStatement.executeQuery(); 

			if(rs.next()) {
				provincia = loadNext(connection, rs);  
			}

		} catch (SQLException e) {
			logger.error("Error. idProvincia: {}", id);  
		} 

		finally {            
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(preparedStatement);
		} 

		return provincia; 
	}

	@Override
	public List<Provincia> findByNombre(Connection connection, String nombre) throws DataException {

		List <Provincia> provincias = null;  
		Provincia r = null;

		PreparedStatement preparedStatement = null;
		String query = null;
		ResultSet rs = null;

		try {

			StringBuilder stringBuilder = new StringBuilder(
					" SELECT ID_PROVINCIA, NOMBRE, ID_PAIS FROM PROVINCIA "
					);

			boolean first = true;

			first = QueryUtils.addClause(nombre, stringBuilder, first, " UPPER(NOMBRE) LIKE UPPER(?) ");

			query = stringBuilder.toString();
			preparedStatement = connection.prepareStatement(query);

			int i = 1;
			preparedStatement.setString(i++, "%" + nombre +"%"); 

			rs = preparedStatement.executeQuery();

			while(rs.next()) {
				provincias = new ArrayList<Provincia>();
				r = loadNext(connection, rs); 
				provincias.add(r);
			} 

			if(provincias.size() == 0) {
				logger.warn("Criterios no encontrados..."); 
			}else {
				logger.info("Han salido {} resultados", provincias.size()); 
			} 

			if(logger.isDebugEnabled()) {
				logger.debug("Query: {} ", preparedStatement.toString());
			}

		} catch (SQLException e) {
			logger.error("Error: {}", preparedStatement.toString());  
		}

		finally {            
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(preparedStatement);
		}
		return provincias; 
	}

	@Override
	public List<Provincia> findByPais(Connection connection, long idPais) throws DataException {

		List <Provincia> provincias = null;  
		Provincia r = null;
		
		PreparedStatement preparedStatement = null;
		String query = null;
		ResultSet rs = null;

		try {

			StringBuilder sb = new StringBuilder(
					" SELECT ID_PROVINCIA, NOMBRE, ID_PAIS FROM PROVINCIA "
					);

			boolean first = true;

			first = QueryUtils.addClause(idPais, sb, first, " ID_PAIS = ? ");

			query = sb.toString();
			preparedStatement = connection.prepareStatement(query); 

			int i = 1;
			preparedStatement.setLong(i++, idPais); 

			rs = preparedStatement.executeQuery();

			while(rs.next()) {
				provincias = new ArrayList<Provincia>();
				r = loadNext(connection, rs); 
				provincias.add(r); 
			}

			if(provincias.size() == 0) { 
				logger.warn("Criterios no encontrados para idPais: {}", idPais);   
			}else {
				logger.info("Han salido {} resultados", provincias.size()); 
			} 

			if(logger.isDebugEnabled()) {
				logger.debug("Query: {} ", preparedStatement.toString()); 
			}

		}catch (SQLException e) {
			logger.error("Error: {}", preparedStatement.toString());  
		}

		finally {            
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(preparedStatement);
		} 

		return provincias; 
	}

	public Provincia loadNext(Connection connection, ResultSet rs) throws SQLException, DataException { 

		Provincia r = new Provincia();
		int i = 1;

		r.setId(rs.getLong(i++));
		r.setNombre(rs.getString(i++));
		r.setIdPais(rs.getLong(i++));

		List<Localidad> localidades = localidadDAO.findByProvincia(connection, r.getId()); 
		r.setLocalidades(localidades); 

		return r;
	}

}
