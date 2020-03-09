package com.azonma.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.azonma.dao.PedidoDAO;
import com.azonma.dao.impl.PedidoDAOImpl;
import com.azonma.exceptions.DataException;
import com.azonma.exceptions.InstanceNotFoundException;
import com.azonma.model.Pedido;
import com.azonma.model.criteria.PedidoCriteria;
import com.azonma.service.PedidoService;
import com.azonma.util.DBUtils;
import com.azonma.util.JDBCUtils;

public class PedidoServiceImpl implements PedidoService{

	private static Logger logger = LogManager.getLogger(PedidoServiceImpl.class.getName());
 
	private PedidoDAO dao = null;

	public PedidoServiceImpl() {
		dao = new PedidoDAOImpl();
	}

	@Override
	public Pedido findById(Long id) throws InstanceNotFoundException, DataException {

		Connection connection = null;

		try {

			connection = DBUtils.conectar(); // ConnectionManager.getConnection();
			connection.setAutoCommit(true);

			return dao.findById(connection, id);	

		} catch (SQLException e){
			logger.error(e.getMessage(),e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection); 
		}

	}

	@Override
	public Pedido create(Pedido p) throws DataException {

		Connection connection = null;
		boolean commit = false;

		try {
			connection = DBUtils.conectar(); // ConnectionManager.getConnection();

			connection.setTransactionIsolation(
					Connection.TRANSACTION_READ_COMMITTED);		

			connection.setAutoCommit(false);

			p = dao.create(connection, p);
			commit = true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConnection(connection, commit); 
		}

		return p;
	}

	@Override
	public List<Pedido> findByCriteria(PedidoCriteria c) throws DataException {

		Connection connection = null;

		try {

			connection = DBUtils.conectar(); // ConnectionManager.getConnection();
			connection.setAutoCommit(true);

			return dao.findByCriteria(connection, c);	

		} catch (SQLException e){
			logger.error(e.getMessage(),e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection); 
		}
	}

	@Override
	public void update(Pedido pedido, Long idPedido) throws DataException {

		Connection connection = null;

		try {

			connection = DBUtils.conectar(); // ConnectionManager.getConnection();
			connection.setAutoCommit(true);

			dao.update(connection, pedido, idPedido);	  

		} catch (SQLException e){
			logger.error(e.getMessage(),e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection); 
		}
	}

	@Override
	public void updateEstado(Long id, Integer idEstado) throws DataException{

		Connection connection = null;

		try {

			connection = DBUtils.conectar(); // ConnectionManager.getConnection();
			connection.setAutoCommit(true);

			dao.updateEstado(connection, id, idEstado);	

		} catch (SQLException e){
			logger.error(e.getMessage(),e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection); 
		}
	}

	@Override
	public void delete(long idPedido) throws DataException {

		Connection connection = null;

		try {

			connection = DBUtils.conectar(); // ConnectionManager.getConnection();
			connection.setAutoCommit(true);

			dao.delete(connection, idPedido);	 

		} catch (SQLException e){
			logger.error(e.getMessage(),e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection); 
		}
	}
}
