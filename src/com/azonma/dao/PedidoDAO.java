package com.azonma.dao;

import java.util.List;

import com.azonma.exceptions.DataException;
import com.azonma.model.Pedido;
import com.azonma.model.criteria.PedidoCriteria;
import java.sql.Connection;

public interface PedidoDAO {

	public Pedido findById(Connection connection, long identificador) throws DataException; 

	public List<Pedido> findByCriteria(Connection connection, PedidoCriteria pedidoCriteria) throws DataException;

	public Pedido create(Connection connection, Pedido pedido) throws DataException;

	public void update(Connection connection, Pedido pedido, long idPedido) throws DataException;  

	public void updateEstado(Connection connection, long id, int idEstado) throws DataException;

	public void delete(Connection connection, long idPedido) throws DataException;

}
