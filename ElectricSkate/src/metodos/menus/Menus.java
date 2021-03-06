package metodos.menus;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

import metodos.alquiler.Alquiler;
import metodos.buscar.Buscar;
import metodos.devolucion.Devolucion;
import metodos.excepciones.Excepciones;
import metodos.exportarTxt.ExportarTxt;
import metodos.listado.Listado;
import metodos.registrar.Registrar;

/**
 * Esta clase sirve para ilustrar los men?s
 * 
 * @author Reginaldo, Alberto, Kike, Jose Y Luz
 * @version 1.0
 */

public class Menus {

	// Objeto scanner para recoger los datos introducidos por consola.
	static Scanner teclado = new Scanner(System.in);

	/**
	 * M?todo para mostrar el men? principal para cada una de las acciones que
	 * queramos realizar
	 * 
	 * @param connection la variable que establece la conexi?n con la base de datos
	 * @param BDNombre   nombre de la base de datos por defecto
	 * @throws SQLException         este nos sirve para lanzar una excepci?n
	 * @throws InterruptedException es una parte de la firma del m?todo y un posible
	 *                              resultado de llamar al m?todo que est? llamando
	 */

	public static void menuPrincipal(Connection connection, String BDNombre) throws SQLException, InterruptedException {

		System.out.println("");
		System.out.println("===============================");
		System.out.println("=============MEN?==============");
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
		System.out.println("6) Realizar devoluci?n");
		System.out.println("");
		System.out.println("7) Exportar listados");
		System.out.println("");
		System.out.println("8) Logout");
		System.out.println("");
		System.out.println("9) Cerrar programa");
		System.out.println("");
		System.out.print("Seleccione una opci?n: ");
		int opcion = 0;
		// Variable int que recoge la opci?n seleccionada por consola.
		try {

			opcion = teclado.nextInt();
			teclado.nextLine();

		} catch (InputMismatchException e) {
			System.out.println("\n?Error!, elija una opcion disponible\"");
			Thread.sleep(3000);
			teclado.nextLine();
			menuPrincipal(connection, BDNombre);

		}

		// Switch para navegar por el men?
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
			System.out.println("Cerrando sesi?n...");
			Thread.sleep(3000);
			LogIn(connection, BDNombre);

			break;

		case 9:

			System.out.println("");
			System.out.println("Cerrando el programa...");
			System.exit(0);

			break;

		default:

			System.out.println("\n?Error!, elija una opci?n disponible");
			Thread.sleep(3000);
			menuPrincipal(connection, BDNombre);

			break;
		}

	}

	/**
	 * M?todo para seleccionar el tipo de usuario que pretendemos registrar
	 * 
	 * @param connection la variable que establece la conexi?n con la base de datos
	 * @param BDNombre   nombre de la base de datos por defecto
	 * @throws SQLException         este nos sirve para lanzar una excepci?n
	 * @throws InterruptedException es una parte de la firma del m?todo y un posible
	 *                              resultado de llamar al m?todo que est? llamando
	 */
	// M?todo para seleccionar el tipo de usuario a registrar
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
		System.out.println("3) Volver al men? principal");
		System.out.println("");
		System.out.print("Seleccione una opci?n: ");
		int opcion = 0;
		try {

			opcion = teclado.nextInt();
			teclado.nextLine();

		} catch (InputMismatchException e) {
			System.out.println("\n?Error!, elija una opcion disponible\"");
			Thread.sleep(3000);
			teclado.nextLine();
			registrarNuevoUsuario(connection, BDNombre);
		}

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

			System.out.println("\n?Error!, elija una opci?n disponible");
			Thread.sleep(3000);
			registrarNuevoUsuario(connection, BDNombre);
			break;
		}

	}

	/**
	 * M?todo para optar por el tipo de listado que pretendemos visualizar
	 * 
	 * @param connection la variable que establece la conexi?n con la base de datos
	 * @param BDNombre   nombre de la base de datos por defecto
	 * @throws SQLException         este nos sirve para lanzar una excepci?n
	 * @throws InterruptedException es una parte de la firma del m?todo y un posible
	 *                              resultado de llamar al m?todo que est? llamando
	 */

	// M?todo para seleccionar el tipo de lista que se desea
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
		System.out.println("3) Volver al men? principal");
		System.out.println("");
		System.out.print("Seleccione una opci?n: ");
		int opcion = 0;

		try {

			opcion = teclado.nextInt();
			teclado.nextLine();

		} catch (InputMismatchException e) {
			System.out.println("\n?Error!, elija una opcion disponible\"");
			Thread.sleep(3000);
			teclado.nextLine();
			listados(connection, BDNombre);
		}

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

			System.out.println("\n?Error!, elija una opci?n disponible");
			Thread.sleep(3000);
			listados(connection, BDNombre);
			break;
		}

	}

	/**
	 * M?todo para seleccionar el tipo de listado que pretendemos visualizar
	 * 
	 * @param connection la variable que establece la conexi?n con la base de datos
	 * @param BDNombre   nombre de la base de datos por defecto
	 * @throws SQLException         este nos sirve para lanzar una excepci?n
	 * @throws InterruptedException es una parte de la firma del m?todo y un posible
	 *                              resultado de llamar al m?todo que est? llamando
	 */

	// M?todo donde selecciona el tipo de listado de patinetes
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
		System.out.println("4) Volver al men? anterior");
		System.out.println("");
		System.out.print("Seleccione una opci?n: ");
		int opcion = 0;

		try {

			opcion = teclado.nextInt();
			teclado.nextLine();

		} catch (InputMismatchException e) {
			System.out.println("\n?Error!, elija una opcion disponible\"");
			Thread.sleep(3000);
			teclado.nextLine();
			listadoPatinetes(connection, BDNombre);
		}

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

			System.out.println("\n?Error!, elija una opci?n disponible");
			Thread.sleep(3000);
			listadoPatinetes(connection, BDNombre);
			break;
		}

	}

	/**
	 * M?todo para optar por el listado que pretendemos exportar a un fichero .txt
	 * 
	 * @param connection la variable que establece la conexi?n con la base de datos
	 * @param BDNombre   nombre de la base de datos por defecto
	 * @throws SQLException         este nos sirve para lanzar una excepci?n
	 * @throws InterruptedException es una parte de la firma del m?todo y un posible
	 *                              resultado de llamar al m?todo que est? llamando
	 */

	// M?todo donde se selecciona es el tipo de lista a exportar a txt
	public static void exportarTXT(Connection connection, String BDNombre) throws SQLException, InterruptedException {

		System.out.println("");
		System.out.println("===============================");
		System.out.println("=======EXPORTAR LISTADOS=======");
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
		System.out.println("5) Volver al men? principal");
		System.out.println("");
		System.out.print("Seleccione una opci?n: ");
		int opcion = 0;
		try {

			opcion = teclado.nextInt();
			teclado.nextLine();

		} catch (InputMismatchException e) {
			System.out.println("\n?Error!, elija una opcion disponible\"");
			Thread.sleep(3000);
			teclado.nextLine();
			exportarTXT(connection, BDNombre);
		}

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

			System.out.println("\n?Error!, elija una opci?n disponible");
			Thread.sleep(3000);
			exportarTXT(connection, BDNombre);
			break;
		}

	}

	/**
	 * M?todo para el login, del cual primero tendr? que acceder el administrador
	 * para poder navegar por la aplicaci?n
	 * 
	 * @param connection la variable que establece la conexi?n con la base de datos
	 * @param BDNombre   nombre de la base de datos por defecto
	 * @throws SQLException         este nos sirve para lanzar una excepci?n
	 * @throws InterruptedException es una parte de la firma del m?todo y un posible
	 *                              resultado de llamar al m?todo que est? llamando
	 */

	// M?todo para iniciar sesi?n
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
		System.out.print("Contrase?a: ");
		String contrase?a = teclado.nextLine();

		// Creamos objeto statement vacio
		Statement consulta = null;
		String queryUsario = "select nombre, apellidos " + " from " + BDNombre + ".administrador"
				+ " WHERE nombreUsuario = '" + nombreUsuario + "'" + " AND contrase?a = '" + contrase?a + "'";

		try {

			// Creamos un statement
			consulta = connection.createStatement();
			// Creamos un ResultSet
			ResultSet registro = consulta.executeQuery(queryUsario);

			System.out.println("");

			// En caso de que exista el usuario introducido
			if (registro.next()) {

				ResultSet rs = consulta.executeQuery(queryUsario);
				rs.next();
				String nombre = rs.getString("nombre");

				System.out.println("Bienvenido " + nombre + "!");
				Thread.sleep(3000);
				menuPrincipal(connection, BDNombre);

				// En caso de que no exista el usuario introducido
			} else {

				System.out.println("?Error!, usuario o contrase?a incorrecto");
				Thread.sleep(3000);
				LogIn(connection, BDNombre);
			}

		} catch (SQLException e) {
			Excepciones.printSQLException(e);
		} finally {
			consulta.close();
		}

	}

	/**
	 * M?todo que nos permite volver al men? principal
	 * 
	 * @param connection la variable que establece la conexi?n con la base de datos
	 * @param BDNombre   nombre de la base de datos por defecto
	 * @throws InterruptedException es una parte de la firma del m?todo y un posible
	 *                              resultado de llamar al m?todo que est? llamando
	 * @throws SQLException         este nos sirve para lanzar una excepci?n
	 */

	// M?todo que te dirige al men? principal
	public static void volverAlMenuPrincipal(Connection connection, String BDNombre)
			throws InterruptedException, SQLException {
		System.out.println("\nVolviendo al men? principal...");
		Thread.sleep(2500);
		Menus.menuPrincipal(connection, BDNombre);
	}

}
