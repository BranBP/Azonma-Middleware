package com.azonma.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.azonma.dao.PaisDAO;
import com.azonma.dao.ProvinciaDAO;
import com.azonma.exceptions.DataException;
import com.azonma.model.Pais;
import com.azonma.model.Provincia;
import com.azonma.util.JDBCUtils;
import com.azonma.util.QueryUtils;

public class PaisDAOImpl implements PaisDAO{ 

	private static Logger logger = LogManager.getLogger(PaisDAOImpl.class.getName()); 

	private ProvinciaDAO provinciaDAO = null;

	public PaisDAOImpl() {
		provinciaDAO = new ProvinciaDAOImpl();  
	}

	@Override
	public Pais findById(Connection connection, long id) throws DataException {

		Pais pais = new Pais();  
		PreparedStatement preparedStatement = null;
		String query = null;
		ResultSet rs = null;

		try {

			StringBuilder stringBuilder = new StringBuilder(
					" SELECT ID_PAIS, ISO, NOMBRE FROM PAIS "
					); 

			boolean first = true;

			first = QueryUtils.addClause(id, stringBuilder, first, " ID_PAIS = ? ");

			query = stringBuilder.toString();
			preparedStatement = connection.prepareStatement(query);

			int i = 1;
			preparedStatement.setLong(i++, id);

			if(logger.isDebugEnabled()) {
				logger.debug("Query: {} ", query); 
			}

			rs = preparedStatement.executeQuery(); 

			if(rs.next()) {
				pais = loadNext(connection, rs);  
			}

		} catch (SQLException e) {
			logger.error("Error. idPais: {}", id);   
		} 

		finally {            
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(preparedStatement);
		} 

		return pais; 
	}

	@Override
	public List<Pais> findByNombre(Connection connection, String nombre) throws DataException {

		List <Pais> paises = new ArrayList<Pais>();    
		PreparedStatement preparedStatement = null;
		String query = null;
		ResultSet rs = null;

		try {

			StringBuilder stringBuilder = new StringBuilder(
					" SELECT ID_PAIS, ISO, NOMBRE FROM PAIS "
					);

			boolean first = true;

			first = QueryUtils.addClause(nombre, stringBuilder, first, " UPPER(NOMBRE) LIKE ? ");

			query = stringBuilder.toString();
			preparedStatement = connection.prepareStatement(query);

			if(logger.isDebugEnabled()) {
				logger.debug("Query: {} ", query);
			}

			int i = 1;
			preparedStatement.setString(i++, nombre); 

			rs = preparedStatement.executeQuery();

			while(rs.next()) {
				Pais r = new Pais(); 
				r = loadNext(connection, rs);  
				paises.add(r);
			} 

			if(paises.size() == 0) {
				logger.warn("Criterios no encontrados..."); 
			}else {
				logger.info("Han salido {} resultados", paises.size()); 
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
		return paises; 
	}

	public Pais loadNext(Connection connection, ResultSet rs) throws SQLException, DataException { 

		Pais r = new Pais(); 
		int i = 1;

		r.setId(rs.getLong(i++));
		r.setIso(rs.getString(i++));
		r.setNombre(rs.getString(i++));

		List<Provincia> provincias = provinciaDAO.findByPais(connection, r.getId());
		r.setProvincias(provincias); 

		return r;
	}

}
