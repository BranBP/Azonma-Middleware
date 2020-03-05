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
import com.azonma.dao.CategoriaIdiomaDAO;
import com.azonma.exceptions.DataException;
import com.azonma.model.Categoria;
import com.azonma.model.CategoriaIdioma;
import com.azonma.util.JDBCUtils;
import com.azonma.util.QueryUtils;
import com.mysql.jdbc.Statement;

public class CategoriaDAOImpl implements CategoriaDAO{ 

	private static Logger logger = LogManager.getLogger(ProductoDAOImpl.class.getName());

	private CategoriaIdiomaDAO categoriaIdiomaDAO = null;

	public CategoriaDAOImpl() {
		categoriaIdiomaDAO = new CategoriaIdiomaDAOImpl(); 
	}

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

	@Override
	public Categoria create(Connection connection, Categoria c) throws DataException {

		PreparedStatement preparedStatement = null; 
		String query = null;
		ResultSet rs = null;

		try {

			query = " INSERT INTO CATEGORIA (ID_CATEGORIA_PADRE) "
					+" VALUES (?, ?)";

			preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS); 


			if(logger.isDebugEnabled()) {
				logger.debug("Query: {} ", query);
			}

			int i = 1;
			if(c.getIdPadre()!=null) {
				preparedStatement.setLong(i++, c.getIdPadre());
			}else {
				preparedStatement.setNull(i++, java.sql.Types.BIGINT); 
			}

			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows == 0) {
				throw new SQLException("Can not add row to table 'Categoria'");
			}

			rs = preparedStatement.getGeneratedKeys();

			if (rs.next()) {
				Long id = rs.getLong(1); 
				c.setId(id); 
			} 
			
			for(CategoriaIdioma ci: c.getIdiomas()) {
				ci.setIdCategoria(c.getId());;
				categoriaIdiomaDAO.create(connection, ci);  
			}

		} catch (SQLException e) {
			logger.error("Error. Categoria: {}", c);  
		}

		finally {            
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(preparedStatement); 
		}
		return c; 
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
