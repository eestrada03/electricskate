package principal;

import metodos.excepciones.Excepciones;
import metodos.menus.Menus;
import metodos.tablas.Tablas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase principal del sistema
 * @author Reginaldo, Alberto, Kike, Jose Y Luz
 * @version 1.0
 */

public class Principal {
	
	/**
	 * Este es el método principal
	 * @param args 
	 * @throws InterruptedException
	 */
	
	public static void main(String[] args) throws InterruptedException {

		try {

			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/electricskate", "root", "");

			System.out.println("¡Conexión establecida correctamente!\n");

			//Creamos la base de datos, UNA VEZ CREADA COMENTAR EL MÉTODO
			//Tablas.createBaseDeDatos(connection, "electricskate");
			
			Menus.menuPrincipal(connection, "electricskate");
			//Menus.LogIn(connection, "electricskate");
			
		} catch (SQLException e) {
			Excepciones.printSQLException(e);			
		}
	}
}
