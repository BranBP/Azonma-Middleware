package com.azonma.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.azonma.dao.LineaDAO;
import com.azonma.exceptions.DataException;
import com.azonma.model.Linea;
import com.azonma.util.JDBCUtils;
import com.azonma.util.QueryUtils;
import com.mysql.jdbc.Statement;

public class LineaDAOImpl implements LineaDAO{ 

	private static Logger logger = LogManager.getLogger(ProductoDAOImpl.class.getName());

	@Override
	public Linea findById(Connection cn, long id)  throws DataException{ 

		Linea linea = new Linea(); 
		PreparedStatement preparedStatement = null;
		String query = null;
		ResultSet rs = null;

		try {

			StringBuilder stringBuilder = new StringBuilder(
					" SELECT ID_LINEA, ID_PEDIDO, ID_PRODUCTO, PRECIO_TOTAL, PRECIO_UNITARIO, UNIDADES, VALORACION "
							+ " FROM LINEA "
					); 

			boolean first = true;

			first = QueryUtils.addClause(id, stringBuilder, first, " ID_LINEA = ? ");

			query = stringBuilder.toString();
			preparedStatement = cn.prepareStatement(query);

			if(logger.isDebugEnabled()) {
				logger.debug("Query: {} ", query);
			}

			int i = 1;
			preparedStatement.setLong(i++, id);

			rs = preparedStatement.executeQuery();

			if(rs.next()) {
				linea = loadNext(rs); 
			}

		} catch (SQLException e) {
			logger.error("Error. idLinea: {}", id);  
		} 

		finally {            
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(preparedStatement);
		} 

		return linea; 
	}

	@Override
	public List<Linea> findByPedido(Connection cn, long idPedido) throws DataException { 

		List <Linea> lineas = new ArrayList<Linea>();
		PreparedStatement preparedStatement = null;
		String query = null;
		ResultSet rs = null;

		try {

			StringBuilder sb = new StringBuilder(
					" SELECT L.ID_LINEA, L.ID_PEDIDO, L.ID_PRODUCTO, L.PRECIO_TOTAL, L.PRECIO_UNITARIO, L.UNIDADES, L.VALORACION "
							+ " FROM LINEA L INNER JOIN PEDIDO P ON P.ID_PEDIDO = L.ID_PEDIDO "
					);

			boolean first = true;

			first = QueryUtils.addClause(idPedido, sb, first, " L.ID_PEDIDO = ? ");

			query = sb.toString();
			preparedStatement = cn.prepareStatement(query);

			int i = 1;
			preparedStatement.setLong(i++, idPedido);

			rs = preparedStatement.executeQuery();

			while(rs.next()) {
				Linea l = new Linea();
				l = loadNext(rs);
				lineas.add(l);
			}

			if(lineas.size() == 0) { 
				logger.warn("Criterios no encontrados para idPedido: {}", idPedido);  
			}else {
				logger.info("Han salido {} resultados", lineas.size()); 
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

		return lineas;
	}

	@Override
	public void create(Connection cn, Linea l) throws DataException{

		PreparedStatement preparedStatement = null;
		String query = null;
		ResultSet rs = null;

		try {

			query = " INSERT INTO LINEA (PRECIO_UNITARIO, PRECIO_TOTAL, UNIDADES, VALORACION, ID_PEDIDO, ID_PRODUCTO) "
					+" VALUES (?, ?, ?, ?, ?, ?)";

			preparedStatement = cn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);


			if(logger.isDebugEnabled()) {
				logger.debug("Query: {} ", query);
			}

			int i = 1;
			preparedStatement.setDouble(i++, l.getPrecioUnitario());
			preparedStatement.setDouble(i++, l.getPrecioTotal());
			preparedStatement.setInt(i++, l.getUnidades());

			if(l.getValoracion() != null) {
				preparedStatement.setInt(i++, l.getValoracion());
			}else {
				preparedStatement.setNull(i++, java.sql.Types.INTEGER);  
			}

			preparedStatement.setLong(i++, l.getIdPedido());
			preparedStatement.setLong(i++, l.getIdProducto());

			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows == 0) {
				throw new SQLException("Can not add row to table 'Linea'");
			}

			rs = preparedStatement.getGeneratedKeys();

			if (rs.next()) {
				Long id = rs.getLong(1); 
				l.setId(id);
			} 

		} catch (SQLException e) {
			logger.error("Error. Linea: {}", l);  
		}

		finally {            
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(preparedStatement);
		}

	}

	public Linea loadNext(ResultSet rs) throws SQLException { 

		Linea r = new Linea();
		int i = 1;

		r.setId(rs.getLong(i++));
		r.setIdPedido(rs.getLong(i++));
		r.setIdProducto(rs.getLong(i++));
		r.setPrecioTotal(rs.getDouble(i++));
		r.setPrecioUnitario(rs.getDouble(i++));
		r.setUnidades(rs.getInt(i++));
		r.setValoracion(rs.getInt(i++));

		return r;
	}

}
