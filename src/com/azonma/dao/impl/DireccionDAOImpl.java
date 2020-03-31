package com.azonma.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.azonma.dao.DireccionDAO;
import com.azonma.exceptions.DataException;
import com.azonma.model.Direccion;
import com.azonma.util.JDBCUtils;
import com.azonma.util.QueryUtils;
import com.mysql.jdbc.Statement;

public class DireccionDAOImpl implements DireccionDAO{

	private static Logger logger = LogManager.getLogger(DireccionDAOImpl.class.getName());

	@Override
	public Direccion findById(Connection connection, long id) throws DataException {

		Direccion direccion = null;  
		
		PreparedStatement preparedStatement = null;
		String query = null;
		ResultSet rs = null;

		try {

			StringBuilder sb = new StringBuilder(
					" SELECT ID_DIRECCION, NOMBRE, CALLE, ID_LOCALIDAD, ID_USUARIO FROM DIRECCION "
					);

			boolean first = true;

			first = QueryUtils.addClause(id, sb, first, " ID_DIRECCION = ? ");

			query = sb.toString();
			preparedStatement = connection.prepareStatement(query);

			if(logger.isDebugEnabled()) {
				logger.debug("Query: {} ", query);
			}

			int i = 1;
			preparedStatement.setLong(i++, id);

			rs = preparedStatement.executeQuery(); 

			if(rs.next()) {
				direccion = loadNext(rs);  
			}

		} catch (SQLException e) {
			logger.error("Error. idDireccion: {}", id);  
		} 

		finally {            
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(preparedStatement);
		} 

		return direccion;  
	}

	@Override
	public List<Direccion> findByUsuario(Connection connection, long idUsuario) throws DataException {

		List <Direccion> direcciones = null; 
		Direccion r = null; 

		PreparedStatement preparedStatement = null;
		String query = null;
		ResultSet rs = null;

		try {

			StringBuilder sb = new StringBuilder(
					" SELECT ID_DIRECCION, NOMBRE, CALLE, ID_LOCALIDAD, ID_USUARIO FROM DIRECCION "
					);

			boolean first = true;

			first = QueryUtils.addClause(idUsuario, sb, first, " ID_USUARIO = ? "); 

			query = sb.toString();
			preparedStatement = connection.prepareStatement(query);

			int i = 1;
			preparedStatement.setLong(i++, idUsuario); 

			rs = preparedStatement.executeQuery();

			while(rs.next()) {
				direcciones = new ArrayList<Direccion>();
				r = loadNext(rs);
				direcciones.add(r);
			}

			if(direcciones.size() == 0) { 
				logger.warn("Criterios no encontrados para idUsuario: {}", idUsuario);  
			}else {
				logger.info("Han salido {} resultados", direcciones.size()); 
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

		return direcciones;
	}

	@Override
	public List<Direccion> findByLocalidad(Connection connection, long idLocalidad) throws DataException { 

		List <Direccion> direcciones = null;  
		Direccion r = null;
		
		PreparedStatement preparedStatement = null;
		String query = null;
		ResultSet rs = null;

		try {

			StringBuilder sb = new StringBuilder(
					" SELECT ID_DIRECCION, NOMBRE, CALLE, ID_LOCALIDAD, ID_USUARIO FROM DIRECCION "
					);

			boolean first = true;

			first = QueryUtils.addClause(idLocalidad, sb, first, " ID_LOCALIDAD = ? "); 

			query = sb.toString();
			preparedStatement = connection.prepareStatement(query);

			int i = 1;
			preparedStatement.setLong(i++, idLocalidad);  

			rs = preparedStatement.executeQuery();

			while(rs.next()) { 
				direcciones = new ArrayList<Direccion>();
				r = loadNext(rs);
				direcciones.add(r);
			}

			if(direcciones.size() == 0) { 
				logger.warn("Criterios no encontrados para idLocalidad: {}", idLocalidad);  
			}else {
				logger.info("Han salido {} resultados", direcciones.size()); 
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

		return direcciones;
	}

	@Override
	public Direccion create(Connection cn, Direccion d) throws DataException { 

		PreparedStatement preparedStatement = null; 
		String query = null;
		ResultSet rs = null;

		try {

			query = " INSERT INTO DIRECCION (NOMBRE, CALLE, ID_LOCALIDAD, ID_USUARIO) "
					+" VALUES (?, ?, ?, ?)";

			preparedStatement = cn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);


			if(logger.isDebugEnabled()) {
				logger.debug("Query: {} ", query);
			}

			int i = 1;
			preparedStatement.setString(i++, d.getNombre());
			preparedStatement.setString(i++, d.getCalle());
			preparedStatement.setLong(i++, d.getIdLocalidad()); 
			preparedStatement.setLong(i++, d.getIdUsuario());

			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows == 0) {
				throw new SQLException("Can not add row to table 'Direccion'");
			}

			rs = preparedStatement.getGeneratedKeys();

			if (rs.next()) {
				Long id = rs.getLong(1); 
				d.setId(id);
			} 

		} catch (SQLException e) {
			logger.error("Error. Query: {}", preparedStatement.toString());  
		}

		finally {            
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(preparedStatement); 
		}
		return d;
	}

	public Direccion loadNext(ResultSet rs) throws SQLException {

		Direccion r = new Direccion();  
		int i = 1;

		r.setId(rs.getLong(i++));
		r.setNombre(rs.getString(i++));
		r.setCalle(rs.getString(i++));
		r.setIdLocalidad(rs.getLong(i++));
		r.setIdUsuario(rs.getLong(i++));  

		return r;
	}

}
