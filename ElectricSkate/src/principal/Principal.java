package principal;

import metodos.excepciones.Excepciones;
import metodos.menus.Menus;
import metodos.tablas.Tablas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Principal {

	public static void main(String[] args) throws InterruptedException {

		try {

			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/electricskate", "root", "");

			System.out.println("�Conexi�n establecida correctamente!\n");

			//Creamos la base de datos, UNA VEZ CREADA COMENTAR EL M�TODO
			Tablas.createBaseDeDatos(connection, "electricskate");
			
			
			Menus.LogIn(connection, "electricskate");
			
		} catch (SQLException e) {
			Excepciones.printSQLException(e);			
		}
	}
}
