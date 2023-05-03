package com.mayab.desarrollo.main;

import com.mayab.desarrollo.persistence.UserDAO;

import com.mayab.desarrollo.entities.Usuario;
import com.mayab.desarrollo.servicios.UsuarioServicio;

public class Test {

	public static void main(String[] args) {

		UserDAO dao= new UserDAO();

		//servicios
		Usuario userP = new Usuario();
		userP.setNombre("UsuarioTester");
		userP.setPassword("pass");
		userP.setEmail("testeo@servicio.com");

		//login servicio usuario
		UsuarioServicio usuarioServicio = new UsuarioServicio(dao);
	}
}
