package principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Principal {

	public static void main(String[] args) throws InterruptedException {

		try {

			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/electricskate", "root", "");

			System.out.println("�Conexi�n establecida correctamente!");

			// creamos la base de datos
			// Metodos.createBaseDeDatos(connection, "electricskate");
					
			//Metodos.listadoPatineteAlquilado(connection, "electricskate");
			//Metodos.listadoPatineteNoAlquilado(connection, "electricskate");
			//Metodos.listadoCompletoPatinetes(connection, "electricskate");
			
			// Metodos.listadoCliente(connection, "electricskate");
			// Metodos.buscarCliente(connection, "electricskate");

			// Metodos.registrarNuevosClientes(connection, "electricskate");
			// Metodos.registrarNuevosAdministradores(connection, "electricskate");
			
			// Metodos.realizarAlquiler(connection, "electricskate");
			 Metodos.realizarDevolucion(connection, "electricskate");
			// Metodos.exportarListadoPatineteAlquiladoTXT(connection, "electricskate");
			// Metodos.exportarListadoPatineteNoAlquiladoTXT(connection, "electricskate");
			// Metodos.exportarListadoCompletoPatinetesTXT(connection, "electricskate");
			// Metodos.exportarListadoClientesTXT(connection, "electricskate");
			// Metodos.registrarPatinete(connection, "electricskate");
			
			//Metodos.menuPrincipal(connection, "electricskate");
			 //Metodos.LogIn(connection, "electricskate");

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
