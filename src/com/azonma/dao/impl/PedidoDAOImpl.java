package com.azonma.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.azonma.dao.LineaDAO;
import com.azonma.dao.PedidoDAO;
import com.azonma.exceptions.DataException;
import com.azonma.model.Estado;
import com.azonma.model.Linea;
import com.azonma.model.Pedido;
import com.azonma.model.criteria.PedidoCriteria;
import com.azonma.util.JDBCUtils;
import com.azonma.util.QueryUtils;
import com.mysql.jdbc.Statement;

public class PedidoDAOImpl implements PedidoDAO{

	private static Logger logger = LogManager.getLogger(PedidoDAOImpl.class.getName()); 

	private LineaDAO lineaDAO = null;

	public PedidoDAOImpl() {
		lineaDAO = new LineaDAOImpl(); 
	}

	@Override
	public Pedido findById(Connection connection, long id) throws DataException{

		Pedido pedido = null;
		
		PreparedStatement preparedStatement = null;
		String query = null;
		ResultSet rs = null;

		try {

			StringBuilder stringBuilder = new StringBuilder(" SELECT ID_PEDIDO, FECHA, PRECIO_TOTAL, CARRITO, ID_USUARIO, ID_ESTADO FROM PEDIDO "); 

			boolean first = true;

			first = QueryUtils.addClause(id, stringBuilder, first, " ID_PEDIDO = ? ");

			query = stringBuilder.toString();
			preparedStatement = connection.prepareStatement(query);

			int i = 1;
			preparedStatement.setLong(i++, id);

			if(logger.isDebugEnabled()) {
				logger.debug("Query: {} ", preparedStatement.toString());
			}

			rs = preparedStatement.executeQuery(); 

			if(rs.next()) {
				pedido = loadNext(connection, rs);
			}

		} catch (SQLException e) {
			logger.error("Error. idPedido: {}", id);  
		} 

		finally {            
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(preparedStatement);
		} 

		return pedido; 
	}

	@Override
	public List<Pedido> findByCriteria(Connection cn, PedidoCriteria c) throws DataException{

		List <Pedido> pedidos = null; 
		Pedido r = null;
		
		PreparedStatement preparedStatement = null;
		String query = null;
		ResultSet rs = null;

		try {

			StringBuilder stringBuilder = new StringBuilder("SELECT P.ID_PEDIDO,"
					+" P.FECHA, P.PRECIO_TOTAL, P.CARRITO, P.ID_USUARIO, P.ID_ESTADO FROM PEDIDO P ");

			boolean first = true;

			if (c.getFechaDesde() != null) {
				first = QueryUtils.addClause(c.getFechaDesde(), stringBuilder, first, " P.FECHA > ? ");
			}

			if (c.getFechaHasta() != null) {
				first = QueryUtils.addClause(c.getFechaHasta(), stringBuilder, first, " P.FECHA < ? ");
			}

			if(c.getIdUsuario() != null) {
				first = QueryUtils.addClause(c.getIdUsuario(), stringBuilder, first, " P.ID_USUARIO = ? ");
			}

			if(c.getIdEstado() != null) {
				first = QueryUtils.addClause(c.getIdEstado(), stringBuilder, first, " P.ID_ESTADO = ? ");
			}

			query = stringBuilder.toString();
			preparedStatement = cn.prepareStatement(query);
			int i = 1;

			if(c.getFechaDesde() != null) {
				preparedStatement.setDate(i++, new java.sql.Date(c.getFechaDesde().getTime()));
			}

			if(c.getFechaHasta() != null) {
				preparedStatement.setDate(i++, new java.sql.Date(c.getFechaHasta().getTime()));
			}

			if(c.getIdUsuario() != null) {
				preparedStatement.setLong(i++, c.getIdUsuario());				
			}

			if(c.getIdEstado() != null) {
				preparedStatement.setLong(i++, c.getIdEstado());
			}

			rs = preparedStatement.executeQuery();

			while(rs.next()) {
				pedidos = new ArrayList<Pedido>();
				r = loadNext(cn, rs);
				pedidos.add(r);
			} 

			if(pedidos.size() == 0) {
				logger.warn("Criterios no encontrados para {}", c.toString()); 
			}else {
				logger.info("Han salido {} resultados", pedidos.size()); 
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
		return pedidos; 
	}

	@Override
	public Pedido create(Connection cn, Pedido p) throws DataException { 

		PreparedStatement preparedStatement = null;
		String query = null;
		ResultSet rs = null;

		try {

			// Creamos el preparedstatement
			query = " INSERT INTO PEDIDO (FECHA, PRECIO_TOTAL, CARRITO, ID_USUARIO, ID_ESTADO) "
					+" VALUES (?, ?, ?, ?, "+Estado.CREADO+")";

			preparedStatement = cn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			if(logger.isDebugEnabled()) {
				logger.debug("Query: {} ", query);
			}

			int i = 1;
			p.setFecha(new Date());
			preparedStatement.setDate(i++, new java.sql.Date(p.getFecha().getTime()));
			preparedStatement.setDouble(i++, p.getPrecioTotal());
			preparedStatement.setLong(i++, p.getIdUsuario());
			preparedStatement.setBoolean(i++, p.isCarrito()); 

			// Execute query
			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows == 0) {
				throw new SQLException("Can not add row to table 'Pedido'");
			}

			rs = preparedStatement.getGeneratedKeys();

			if (rs.next()) {
				Long id = rs.getLong(1); 
				p.setId(id);
			} 

			for (Linea l: p.getLineas()) {
				l.setIdPedido(p.getId());
				lineaDAO.create(cn, l);
			}

		} catch (SQLException e) {
			logger.error("Error. Pedido: {}", p);  

		} finally {            
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(preparedStatement);  
		}
		return p;

	}

	@Override
	public void update(Connection connection, Pedido p, long id) throws DataException { 

		PreparedStatement preparedStatement = null;
		String query = null;

		try {

			query = " UPDATE PEDIDO SET FECHA = ?, PRECIO_TOTAL = ?, CARRITO = ?, ID_USUARIO = ?, ID_ESTADO = ? WHERE ID_PEDIDO = " + id; 

			preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);  	

			if(logger.isDebugEnabled()) {
				logger.debug("Query: {} ", query);
			}

			int i = 1;
			p.setFecha(new Date());
			preparedStatement.setDate(i++, new java.sql.Date(p.getFecha().getTime()));
			preparedStatement.setDouble(i++, p.getPrecioTotal());
			preparedStatement.setBoolean(i++, p.isCarrito()); 
			preparedStatement.setLong(i++, p.getIdUsuario());
			preparedStatement.setInt(i++, p.getIdEstado());

			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows == 0) {
				throw new SQLException("Can not update row to table 'Pedido'");
			}

		} catch (SQLException sqle) {
			logger.error("Error: {}", sqle.getMessage()); 
		}catch (Exception e) {
			logger.error("Error: {}", preparedStatement.toString()); 
		} finally {
			JDBCUtils.closeStatement(preparedStatement);
		}
	}

	@Override 
	public void updateEstado(Connection cn, long id, int idEstado) throws DataException {

		PreparedStatement preparedStatement = null;
		String query = null;

		try {

			query = "UPDATE PEDIDO SET ID_ESTADO = " + idEstado + " WHERE ID_PEDIDO = " + id; 

			preparedStatement = cn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			if(logger.isDebugEnabled()) {
				logger.debug("Query: {} ", query);
			}

			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows == 0) {
				throw new SQLException("Can not update row to table 'Pedido'");
			}

		}catch (SQLException e) {
			logger.error("Error. idPedido: , idEstado: {} {}", id, idEstado);  
		}

		finally {            
			JDBCUtils.closeStatement(preparedStatement);
		}
	}

	@Override
	public void delete(Connection connection, long idPedido) throws DataException {
		this.updateEstado(connection, idPedido, Estado.BORRADO);
	}

	public Pedido loadNext(Connection cn, ResultSet rs) throws SQLException, DataException {

		Pedido r = new Pedido();
		int i = 1;

		r.setId(rs.getLong(i++));
		r.setFecha(rs.getDate(i++));
		r.setPrecioTotal(rs.getDouble(i++));
		r.setCarrito(rs.getBoolean(i++)); 
		r.setIdUsuario(rs.getLong(i++));
		r.setIdEstado(rs.getInt(i++));

		List<Linea> lineas = lineaDAO.findByPedido(cn, r.getId());
		r.setLineas(lineas);

		return r;
	}

}
