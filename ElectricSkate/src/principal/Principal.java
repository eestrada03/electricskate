package principal;

import metodos.alquiler.Alquiler;
import metodos.buscar.Buscar;
import metodos.devolucion.Devolucion;
import metodos.excepciones.Excepciones;
import metodos.exportarTxt.ExportarTxt;
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
			
			//Devolucion.realizarDevolucion(connection, "electricskate");
			//Alquiler.realizarAlquiler(connection, "electricskate");
						
			Menus.menuPrincipal(connection, "electricskate");
			//Menus.LogIn(connection, "electricskate");
			//ExportarTxt.listadoPatineteNoAlquilado(connection, "electricskate");

		} catch (SQLException e) {
			Excepciones.printSQLException(e);			
		}
	}
}
