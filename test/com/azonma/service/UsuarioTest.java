package com.azonma.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.azonma.exceptions.DataException;
import com.azonma.model.Sexo;
import com.azonma.model.Usuario;
import com.azonma.model.criteria.UsuarioCriteria;
import com.azonma.service.impl.UsuarioServiceImpl;

public class UsuarioTest {

	private UsuarioService service = null;

	public UsuarioTest() {
		service = new UsuarioServiceImpl();
	}

	public void findById() throws DataException{

		Usuario u = new Usuario();
		u = service.findById(1);
		System.out.println(u);
	}

	public void findByCriteria() throws DataException{

		List<Usuario> usuarios = new ArrayList<Usuario>();
		UsuarioCriteria uc = new UsuarioCriteria();

		uc.setNombre("b");
		uc.setApellido1("a");
		uc.setEmail("pascualinbbp@gmail.com");
		uc.setFechaNacimiento(new Date(2001-01-30)); 
		uc.setIdioma("es");
		uc.setSexo("h");

		usuarios = service.findByCriteria(uc);

		for(Usuario u: usuarios) {
			System.out.println(u);
		}
	}

	public void create() throws DataException{

		Usuario u = new Usuario();

		u.setEmail("pascualinbbp@gmail.com");
		u.setContrasena("123");
		u.setFechaNacimiento(new Date(2001-01-30));  
		u.setNombre("Bran");
		u.setApellido1("Bouzas");
		u.setIdSexo(Sexo.HOMBRE); 
		u.setIdIdioma(1);

		service.create(u); 
		System.out.println(u);
	}

	public static void main(String[] args) throws DataException{

		UsuarioTest test = new UsuarioTest();

		test.findById();
		test.findByCriteria();
		test.create();

	}

}
