package com.azonma.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

	private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/azonma";
	private static final String USER ="root";
	private static final String PASS ="root";

	public DBUtils() {

	}
	public static Connection conectar() {

		Connection conexion = null;

		try{
			Class.forName(CONTROLADOR);

			conexion = DriverManager.getConnection(URL, USER, PASS);

			System.out.println("Conexion OK!");
		} catch (ClassNotFoundException e){
			System.out.println("Error con el controlador.");
			e.printStackTrace();
		}catch (SQLException e){
			System.out.println("Error en la conexion.");
			e.printStackTrace();
		}
		System.out.println(" ");
		return conexion;

	}

}
