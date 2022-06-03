package principal;

import metodos.alquiler.Alquiler;
import metodos.buscar.Buscar;
import metodos.devolucion.Devolucion;
import metodos.listado.Listado;
import metodos.menus.Menus;
import metodos.registrar.Registrar;
import metodos.tablas.Tablas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Principal {

	public static void main(String[] args) throws InterruptedException {

		try {

			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/electricskate", "root", "");

			System.out.println("¡Conexión establecida correctamente!\n");

			// creamos la base de datos
			//Tablas.createBaseDeDatos(connection, "electricskate");
			
			//Buscar.cliente(connection, "electricskate");
			
			//Registrar.nuevoAdministradore(connection, "electricskate");
			//Registrar.nuevoCliente(connection, "electricskate");
			//Registrar.nuevopatinete(connection, "electricskate");
			
			//Listado.clientes(connection, "electricskate");
			//Listado.completoPatinetes(connection, "electricskate");
			//Listado.patinetesAlquilados(connection, "electricskate");
			//Listado.patinetesNoAlquilados(connection, "electricskate");
			
			Devolucion.realizarDevolucion(connection, "electricskate");
			//Alquiler.realizarAlquiler(connection, "electricskate");
						
			//Menus.menuPrincipal(connection, "electricskate");
			//Menus.LogIn(connection, "electricskate");


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
