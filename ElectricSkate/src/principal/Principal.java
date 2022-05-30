package principal;

import java.sql.*;

public class Principal {

	public static void main(String[] args) {

		try {

			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/electricskate", "root", "");

			// System.out.println("¡Conexión establecida correctamente!");

			// creamos tablas
			Metodos.createPatinete(connection, "electricskate");
			Metodos.createCliente(connection, "electricskate");
			Metodos.createAdministrador(connection, "electricskate");
			Metodos.createAlquiler(connection, "electricskate");
			Metodos.cargaPatinete(connection,"electricskate");
			Metodos.cargaCliente(connection, "electricskate");

			//Metodos.busquedaClientes(connection, "electricskate");
			
			

		} catch (SQLException e) {
			printSQLException(e);

		}
	}

	private static void printSQLException(SQLException ex) {

		ex.printStackTrace(System.err);
		System.err.println("SQLState: " + ex.getSQLState()); // getSQLState()
		System.err.println("Error Code: " + ex.getErrorCode()); // getErrorCode()
		System.err.println("Message: " + ex.getMessage()); // getMessage()

		Throwable t = ex.getCause(); // getCause() - Leemos la primera causa

		while (t != null) {
			System.out.println("Cause: " + t); // Imprimimos una causa
			t = t.getCause(); // Leemos otra causa
		}

	}
}
