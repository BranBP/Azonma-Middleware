package com.azonma.service;

import java.util.List;

import com.azonma.exceptions.DataException;
import com.azonma.model.Pedido;
import com.azonma.model.criteria.PedidoCriteria;

public interface PedidoService {

	public Pedido findById(Long id) throws DataException;

	public List<Pedido> findByCriteria(PedidoCriteria c) throws DataException;
	
    public Pedido create(Pedido p) throws DataException;

	public void update(Pedido pedido, Long idPedido) throws DataException;   
     
    public void updateEstado(Long id, Integer idEstado) throws DataException;

	public void delete(long idPedido) throws DataException;

	//    public Boolean exists(Long id) 
	//    		throws DataException;
	//
	//     public List<Pedido> findAll (int startIndex, int count) 
	//    	throws DataException;
	//     
	//     public long countAll() 
	//     		throws DataException;   
}
