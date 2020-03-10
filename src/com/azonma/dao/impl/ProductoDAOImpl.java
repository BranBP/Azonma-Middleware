package com.azonma.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.azonma.dao.ProductoDAO;
import com.azonma.exceptions.DataException;
import com.azonma.model.Producto;
import com.azonma.model.criteria.ProductoCriteria;
import com.azonma.util.JDBCUtils;
import com.azonma.util.QueryUtils;

public class ProductoDAOImpl implements ProductoDAO {

	private static Logger logger = LogManager.getLogger(ProductoDAOImpl.class.getName()); 

	@Override
	public Producto findById(Connection connection, long id) throws DataException{

		Producto producto = new Producto(); 
		PreparedStatement preparedStatement = null;
		String query = null;
		ResultSet rs = null;

		try {

			StringBuilder stringBuilder = new StringBuilder(
					" SELECT P.ID_PRODUCTO, P.PRECIO, P.ID_CATEGORIA, PI.NOMBRE, PI.DESCRIPCION, I.NOMBRE, AVG(L.VALORACION) "  
							+ " FROM PRODUCTO P INNER JOIN PRODUCTO_IDIOMA PI ON P.ID_PRODUCTO = PI.ID_PRODUCTO " 
							+ " INNER JOIN IDIOMA I ON I.ID_IDIOMA = PI.ID_IDIOMA " 
							+ " LEFT OUTER JOIN LINEA L ON L.ID_PRODUCTO = P.ID_PRODUCTO "); 

			boolean first = true;

			first = QueryUtils.addClause(id, stringBuilder, first, " P.ID_PRODUCTO = ? ");

			query = stringBuilder.toString();
			preparedStatement = connection.prepareStatement(query);

			if(logger.isDebugEnabled()) {
				logger.debug("Query: {} ", query);
			}

			int i = 1;
			preparedStatement.setLong(i++, id);

			rs = preparedStatement.executeQuery(); 

			if(rs.next()) {
				producto = loadNext(rs);  
			}

		} catch (SQLException e) {
			logger.error("Error. idProducto: {}", id);  
		} 

		finally {            
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(preparedStatement);
		} 

		return producto; 
	}

	@Override
	public List<Producto> findByCriteria(Connection cn, ProductoCriteria c, int startIndex, int timesCount) throws DataException {

		List<Producto> productos = new ArrayList<Producto>();	
		Producto r = null;

		PreparedStatement preparedStatement = null;  
		String query = null;
		ResultSet rs = null;

		try {
			StringBuilder sb = new StringBuilder(
					" SELECT P.ID_PRODUCTO, P.PRECIO, P.ID_CATEGORIA, PI.NOMBRE, PI.DESCRIPCION, I.NOMBRE, AVG(L.VALORACION) "
							+ " FROM PRODUCTO P INNER JOIN PRODUCTO_IDIOMA PI ON P.ID_PRODUCTO = PI.ID_PRODUCTO "
							+ " INNER JOIN IDIOMA I ON I.ID_IDIOMA = PI.ID_IDIOMA "
							+ " LEFT OUTER JOIN LINEA L ON L.ID_PRODUCTO = P.ID_PRODUCTO ");

			boolean first = true;

			if(c!=null) {

				if(c.getPrecioDesde() != null) {
					first = QueryUtils.addClause(c.getPrecioDesde(), sb, first, " P.PRECIO > ? ");
				}

				if(c.getPrecioHasta() != null) {
					first = QueryUtils.addClause(c.getPrecioHasta(), sb, first, " P.PRECIO < ? ");
				}

				if(c.getIdCategoria() != null) {
					first = QueryUtils.addClause(c.getIdCategoria(), sb, first, " P.ID_CATEGORIA = ? ");
				}

				if(c.getIdioma() != null) {
					first = QueryUtils.addClause(c.getIdioma(), sb, first, " UPPER(I.NOMBRE) LIKE ? ");
				}

				if(c.getNombre() != null) {
					first = QueryUtils.addClause(c.getNombre(), sb, first, " UPPER(PI.NOMBRE) LIKE ? ");
				}

				sb.append(" GROUP BY P.ID_PRODUCTO "); 

				if(c.getMinVal() != null) {
					sb.append(" HAVING AVG(L.VALORACION) > " + c.getMinVal()); 
				}

				sb.append(" ORDER BY P.ID_PRODUCTO ASC ");

				query = sb.toString();
				preparedStatement = cn.prepareStatement(query);
				int i = 1;

				if(c.getPrecioDesde() != null) {
					preparedStatement.setDouble(i++,c.getPrecioDesde());
				}

				if(c.getPrecioHasta() != null) {
					preparedStatement.setDouble(i++,c.getPrecioHasta());
				}

				if(c.getIdCategoria() != null) {
					preparedStatement.setInt(i++,c.getIdCategoria());
				}

				if(c.getIdioma() != null) {
					preparedStatement.setString(i++,"%" + c.getIdioma() + "%");
				}

				if(c.getNombre() != null) {
					preparedStatement.setString(i++,"%" + c.getNombre() + "%");
				}
			} 

			if(logger.isDebugEnabled()) {
				logger.debug("Query: {} ", query);
			}

			rs = preparedStatement.executeQuery();
			int currentCount = 0;

			if ((startIndex >=1) && rs.absolute(startIndex)) {
				do {
					r = loadNext(rs);  
					productos.add(r);
					currentCount++; 
				}while ((currentCount < timesCount) && rs.next());
			}

			if(productos.size() == 0) {
				logger.warn("Criterios no encontrados para {}", c); 
			}else {
				logger.info("Han salido {} resultados", productos.size());
			} 

		} catch (SQLException e) {
			logger.error("Error. Producto: {} ", c);  
		}

		finally {            
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(preparedStatement);
		}

		return productos;
	}

	@Override
	public List<Producto> findByCategoria(Connection connection, long idCategoria) throws DataException {

		List <Producto> productos = new ArrayList<Producto>(); 
		PreparedStatement preparedStatement = null;
		String query = null;
		ResultSet rs = null;

		try {

			StringBuilder sb = new StringBuilder(
					" SELECT P.ID_PRODUCTO, P.PRECIO, P.ID_CATEGORIA, PI.NOMBRE, PI.DESCRIPCION, I.NOMBRE, L.VALORACION "
							+" FROM PRODUCTO P INNER JOIN PRODUCTO_IDIOMA PI ON PI.ID_PRODUCTO = P.ID_PRODUCTO "
							+" INNER JOIN IDIOMA I ON I.ID_IDIOMA = PI.ID_IDIOMA "
							+" LEFT OUTER JOIN LINEA L ON L.ID_PRODUCTO = P.ID_PRODUCTO "
					);

			boolean first = true;

			first = QueryUtils.addClause(idCategoria, sb, first, " P.ID_CATEGORIA = ? "); 

			query = sb.toString();
			preparedStatement = connection.prepareStatement(query); 

			int i = 1;
			preparedStatement.setLong(i++, idCategoria); 

			rs = preparedStatement.executeQuery();

			while(rs.next()) {
				Producto r = new Producto();
				r = loadNext(rs); 
				productos.add(r); 
			}

			if(productos.size() == 0) { 
				logger.warn("Criterios no encontrados para idCategoria: {}", idCategoria);   
			}else {
				logger.info("Han salido {} resultados", productos.size()); 
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

		return productos;
	}

	public Producto loadNext(ResultSet rs) throws SQLException, DataException {

		Producto r = new Producto();
		int i = 1;

		r.setIdProducto(rs.getLong(i++)); 
		r.setPrecio(rs.getDouble(i++));
		r.setIdCategoria(rs.getInt(i++));
		r.setNombre(rs.getString(i++));
		r.setDescripcion(rs.getString(i++));
		r.setIdioma(rs.getString(i++));
		r.setValoracion(rs.getInt(i++));

		return r;
	}

}
