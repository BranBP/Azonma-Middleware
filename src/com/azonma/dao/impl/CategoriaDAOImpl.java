package com.azonma.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.azonma.dao.CategoriaDAO;
import com.azonma.exceptions.DataException;
import com.azonma.model.Categoria;
import com.azonma.util.JDBCUtils;
import com.azonma.util.QueryUtils;
import com.mysql.jdbc.Statement;

public class CategoriaDAOImpl implements CategoriaDAO{ 

	private static Logger logger = LogManager.getLogger(CategoriaDAOImpl.class.getName()); 

	@Override
	public Categoria findById(Connection connection, long id) throws DataException {

		Categoria categoria = new Categoria();    

		PreparedStatement preparedStatement = null;
		String query = null;
		ResultSet rs = null;

		try {

			StringBuilder stringBuilder = new StringBuilder(
					" SELECT C.ID_CATEGORIA, CI.NOMBRE, I.NOMBRE, C.ID_CATEGORIA_PADRE "
							+" FROM CATEGORIA C INNER JOIN CATEGORIA_IDIOMA CI ON C.ID_CATEGORIA = CI.ID_CATEGORIA"
							+" INNER JOIN IDIOMA I ON I.ID_IDIOMA = CI.ID_IDIOMA  "
					);

			boolean first = true;

			first = QueryUtils.addClause(id, stringBuilder, first, " ID_CATEGORIA = ? ");

			query = stringBuilder.toString();
			preparedStatement = connection.prepareStatement(query);

			if(logger.isDebugEnabled()) {
				logger.debug("Query: {} ", query);
			}

			int i = 1;
			preparedStatement.setLong(i++, id);

			rs = preparedStatement.executeQuery(); 

			if(rs.next()) {
				categoria = loadNext(rs);  
			}

		} catch (SQLException e) {
			logger.error("Error. idCategoria: {}", id);  
		} 

		finally {            
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(preparedStatement);
		} 

		return categoria;  
	}

	@Override
	public List<Categoria> findAll(Connection connection, int startIndex, int timesCount) throws DataException {   

		List<Categoria> categorias = new ArrayList<Categoria>();	    
		Categoria r = null; 

		PreparedStatement preparedStatement = null;  
		String query = null;
		ResultSet rs = null;

		try {
			query = (
					" SELECT C.ID_CATEGORIA, CI.NOMBRE, I.NOMBRE, C.ID_CATEGORIA_PADRE "
							+" FROM CATEGORIA C INNER JOIN CATEGORIA_IDIOMA CI ON C.ID_CATEGORIA = CI.ID_CATEGORIA"
							+" INNER JOIN IDIOMA I ON I.ID_IDIOMA = CI.ID_IDIOMA  "
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
					categorias.add(r);
					currentCount++; 
				}while ((currentCount < timesCount) && rs.next());  
			}

			if(categorias.size() == 0) {
				logger.warn("Warn. Categorías no encontradas..."); 
			}else {
				logger.info("Han salido {} resultados", categorias.size());
			} 

		} catch (SQLException e) {
			logger.error("Error. Query completa {} ", preparedStatement.toString());  
		}

		finally {            
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(preparedStatement);
		}

		return categorias;
	}

	public Categoria loadNext(ResultSet rs) throws SQLException {

		Categoria r = new Categoria();   
		int i = 1;

		r.setId(rs.getLong(i++));
		r.setNombre(rs.getString(i++));
		r.setIdioma(rs.getString(i++));
		r.setIdPadre(rs.getLong(i++)); 

		return r;
	}
}
