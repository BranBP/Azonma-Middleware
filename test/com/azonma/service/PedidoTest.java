package com.azonma.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.azonma.exceptions.DataException;
import com.azonma.model.Estado;
import com.azonma.model.Linea;
import com.azonma.model.Pedido;
import com.azonma.model.criteria.PedidoCriteria;
import com.azonma.service.impl.PedidoServiceImpl;

public class PedidoTest {

	private PedidoService service = null;

	public PedidoTest() {
		service = new PedidoServiceImpl();
	}

	public void TestFindById() throws DataException {

		Pedido p = new Pedido();

		p = service.findById(37l);
		System.out.println(p);
	}

	public void TestFindByCriteria() throws DataException {

		PedidoCriteria pc = new PedidoCriteria();
		List <Pedido> pedidos = new ArrayList <Pedido>();

		pc.setIdEstado(Estado.BORRADO);
		pc.setFechaHasta(new Date());

		pedidos = service.findByCriteria(pc);

		for(Pedido p: pedidos) {
			System.out.println(p);
		}
	}

	public void TestCreate() throws DataException {

		Linea l = null;
		List <Linea> lineas = new ArrayList <Linea>();
		Pedido p = new Pedido();

		l = new Linea();
		l.setIdProducto(32);
		l.setPrecioUnitario(66.99);
		l.setUnidades(4);
		l.setPrecioTotal(l.getPrecioUnitario()*l.getUnidades());
		l.setValoracion(null);

		lineas.add(l);

		p.setFecha(new Date());
		p.setIdEstado(Estado.CARRITO);
		p.setIdUsuario(1);
		p.setLineas(lineas);
		p.setPrecioTotal(p.getPrecioTotal() + l.getPrecioTotal());

		p = service.create(p); 
		System.out.println(p);
	}

	public void TestUpdateEstado() throws DataException {

		Pedido p = new Pedido();
		p.setId(1);

		service.updateEstado(p.getId(), Estado.BORRADO);
	}

	public static void main(String[] args) throws DataException {

		PedidoTest test = new PedidoTest();

		test.TestUpdateEstado();
		test.TestFindByCriteria();
		test.TestCreate();
		test.TestFindById();
	}

}
