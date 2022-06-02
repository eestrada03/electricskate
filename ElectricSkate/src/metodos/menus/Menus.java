package metodos.menus;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import metodos.alquiler.Alquiler;
import metodos.buscar.Buscar;
import metodos.devolucion.Devolucion;
import metodos.excepciones.Excepciones;
import metodos.exportarTxt.ExportarTxt;
import metodos.listado.Listado;
import metodos.registrar.Registrar;

public class Menus {
	
	static Scanner teclado = new Scanner(System.in);
	
	public static void menuPrincipal(Connection connection, String BDNombre) throws SQLException, InterruptedException {

		System.out.println("");
		System.out.println("===============================");
		System.out.println("=============MENÚ==============");
		System.out.println("===============================");
		System.out.println("");
		System.out.println("1) Buscar cliente");
		System.out.println("");
		System.out.println("2) Registrar nuevo usuario");
		System.out.println("");
		System.out.println("3) Registrar patinete");
		System.out.println("");
		System.out.println("4) Listados");
		System.out.println("");
		System.out.println("5) Realizar alquiler");
		System.out.println("");
		System.out.println("6) Realizar devolucion");
		System.out.println("");
		System.out.println("7) Exportar ficheros");
		System.out.println("");
		System.out.println("8) Logout");
		System.out.println("");
		System.out.print("Seleccione una opción: ");
		int opcion = teclado.nextInt();

		switch (opcion) {

		case 1:
			Buscar.cliente(connection, BDNombre);

			break;

		case 2:
			registrarNuevoUsuario(connection, BDNombre);

			break;

		case 3:
			Registrar.nuevopatinete(connection, BDNombre);

			break;

		case 4:
			listados(connection, BDNombre);

			break;

		case 5:
			Alquiler.realizarAlquiler(connection, BDNombre);

			break;

		case 6:
			Devolucion.realizarDevolucion(connection, BDNombre);

			break;

		case 7:
			exportarTXT(connection, BDNombre);

			break;

		case 8:
			
			System.out.println("");
			System.out.println("Saliendo del programa...");
			Thread.sleep(4000);
			LogIn(connection, BDNombre);

			break;

		default:

			System.out.println("\n¡Error!, elija una opción disponible");
			Thread.sleep(4000);
			menuPrincipal(connection, BDNombre);

			break;
		}

	}
	
	public static void registrarNuevoUsuario(Connection connection, String BDNombre)
			throws SQLException, InterruptedException {

		System.out.println("");
		System.out.println("===============================");
		System.out.println("====REGISTRAR NUEVO USUARIO====");
		System.out.println("===============================");
		System.out.println("");
		System.out.println("1) Registrar nuevo cliente");
		System.out.println("");
		System.out.println("2) Registrar nuevo administrador");
		System.out.println("");
		System.out.println("3) Volver al menú principal");
		System.out.println("");
		System.out.print("Seleccione una opción: ");
		int opcion = teclado.nextInt();

		switch (opcion) {

		case 1:
			Registrar.nuevoCliente(connection, BDNombre);

			break;

		case 2:
			Registrar.nuevoAdministradore(connection, BDNombre);

			break;

		case 3:
			menuPrincipal(connection, BDNombre);

			break;

		default:

			System.out.println("\n¡Error!, elija una opción disponible");
			Thread.sleep(5000);
			registrarNuevoUsuario(connection, BDNombre);
			break;
		}

	}

	public static void listados(Connection connection, String BDNombre) throws SQLException, InterruptedException {

		System.out.println("");
		System.out.println("===============================");
		System.out.println("============LISTADOS===========");
		System.out.println("===============================");
		System.out.println("");
		System.out.println("1) Listado clientes");
		System.out.println("");
		System.out.println("2) Listado patinetes");
		System.out.println("");
		System.out.println("3) Volver al menú principal");
		System.out.println("");
		System.out.print("Seleccione una opción: ");
		int opcion = teclado.nextInt();

		switch (opcion) {

		case 1:
			Listado.clientes(connection, BDNombre);

			break;

		case 2:
			listadoPatinetes(connection, BDNombre);

			break;

		case 3:
			menuPrincipal(connection, BDNombre);

			break;

		default:

			System.out.println("\n¡Error!, elija una opción disponible");
			Thread.sleep(5000);
			listados(connection, BDNombre);
			break;
		}

	}

	public static void listadoPatinetes(Connection connection, String BDNombre)
			throws SQLException, InterruptedException {

		System.out.println("");
		System.out.println("===============================");
		System.out.println("=====LISTADOS DE PATINETES=====");
		System.out.println("===============================");
		System.out.println("");
		System.out.println("1) Listado de patinetes alquilados");
		System.out.println("");
		System.out.println("2) Listado de patinetes no alquilados");
		System.out.println("");
		System.out.println("3) Listado completo de patinetes");
		System.out.println("");
		System.out.println("4) Volver al menú anterior");
		System.out.println("");
		System.out.print("Seleccione una opción: ");
		int opcion = teclado.nextInt();

		switch (opcion) {

		case 1:
			Listado.patinetesAlquilados(connection, BDNombre);

			break;

		case 2:
			Listado.patinetesNoAlquilados(connection, BDNombre);

			break;

		case 3:
			Listado.completoPatinetes(connection, BDNombre);

			break;

		case 4:
			listados(connection, BDNombre);

			break;

		default:

			System.out.println("\n¡Error!, elija una opción disponible");
			Thread.sleep(5000);
			listadoPatinetes(connection, BDNombre);
			break;
		}

	}
	
	public static void exportarTXT(Connection connection, String BDNombre) throws SQLException, InterruptedException {

		System.out.println("");
		System.out.println("===============================");
		System.out.println("=======Exportar listados=======");
		System.out.println("===============================");
		System.out.println("");
		System.out.println("1) Exportar listado clientes");
		System.out.println("");
		System.out.println("2) Exportar listado de patinetes alquilados");
		System.out.println("");
		System.out.println("3) Exportar listado de patinetes no alquilados");
		System.out.println("");
		System.out.println("4) Exportar listado completo de patinetes");
		System.out.println("");
		System.out.println("5) Volver al menú principal");
		System.out.println("");
		System.out.print("Seleccione una opción: ");
		System.out.println("");
		int opcion = teclado.nextInt();

		switch (opcion) {

		case 1:
			ExportarTxt.listadoClientes(connection, BDNombre);

			break;

		case 2:
			ExportarTxt.listadoPatineteAlquilado(connection, BDNombre);

			break;

		case 3:
			ExportarTxt.listadoPatineteNoAlquilado(connection, BDNombre);

			break;

		case 4:
			ExportarTxt.listadoCompletoPatinetes(connection, BDNombre);

			break;

		case 5:
			menuPrincipal(connection, BDNombre);

			break;

		default:

			System.out.println("\n¡Error!, elija una opción disponible");
			Thread.sleep(5000);
			exportarTXT(connection, BDNombre);
			break;
		}

	}

	public static void LogIn(Connection connection, String BDNombre) throws SQLException, InterruptedException {

		System.out.println("");
		System.out.println("===============================");
		System.out.println("=========ELECTRICSKATE=========");
		System.out.println("===============================");
		System.out.println("");
		System.out.println("             LOGIN");
		System.out.println("");
		System.out.println("");

		System.out.print("Usuario: ");
		String nombreUsuario = teclado.nextLine();

		System.out.println("");
		System.out.print("Contraseña: ");
		String contraseña = teclado.nextLine();
	
		Statement consulta = null;
		String queryUsario = "select nombre, apellidos " + " from " + BDNombre + ".administrador" + " WHERE nombreUsuario = '"
				+ nombreUsuario + "'" + " AND contraseña = '" + contraseña + "'";
		
		try {

			consulta = connection.createStatement();
			ResultSet registro = consulta.executeQuery(queryUsario);

			System.out.println("");

			if (registro.next()) {
						
				ResultSet rs = consulta.executeQuery(queryUsario);
				rs.next();
				String nombre = rs.getString("nombre");
				
				System.out.println("Bienvenido " +nombre+"!");
				Thread.sleep(3000);
				menuPrincipal(connection, BDNombre);
			} else {
				
				System.out.println("¡Error!, usuario o contraseña incorrecto");
				Thread.sleep(3000);
				LogIn(connection, BDNombre);
			}
			
			
		} catch (SQLException e) {
			Excepciones.printSQLException(e);
		} finally {
			consulta.close();
		}
			
		
	}

	
	
	
}
