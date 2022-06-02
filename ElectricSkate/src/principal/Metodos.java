package principal;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Metodos {

	static Scanner teclado = new Scanner(System.in);

	public static void createBaseDeDatos(Connection connection, String BDNombre) throws SQLException {
		createPatinete(connection, BDNombre);
		createCliente(connection, BDNombre);
		createAdministrador(connection, BDNombre);
		createAlquiler(connection, BDNombre);
		cargaAdministrador(connection, BDNombre);
		cargaPatinete(connection, BDNombre);
		cargaCliente(connection, BDNombre);

	}

	public static void createPatinete(Connection connection, String BDNombre) throws SQLException {

		String createString = "create table " + BDNombre + ".patinete " + "(numSerie integer NOT NULL,"
				+ "marca varchar(40) NOT NULL," + "color varchar(40) NOT NULL," + "modelo varchar(40) NOT NULL,"
				+ "kmRecorridoPatinete float(40) NOT NULL," + "disponible bit NOT NULL," + "PRIMARY KEY (numSerie))";

		Statement stmt = null;

		try {

			stmt = connection.createStatement(); // Creamos un Statement
			stmt.executeUpdate(createString); // Ejecutamos la consulta

			System.out.println("¡Se ha creado la tabla Patinete correctamente!");

		} catch (SQLException e) {
			printSQLException(e);
		} finally {
			stmt.close(); // Cerramos la conexión
		}

	}

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
			buscarCliente(connection, BDNombre);

			break;

		case 2:
			registrarNuevoUsuario(connection, BDNombre);

			break;

		case 3:
			registrarPatinete(connection, BDNombre);

			break;

		case 4:
			listados(connection, BDNombre);

			break;

		case 5:
			realizarAlquiler(connection, BDNombre);

			break;

		case 6:
			realizarDevolucion(connection, BDNombre);

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
			registrarNuevosClientes(connection, BDNombre);

			break;

		case 2:
			registrarNuevosAdministradores(connection, BDNombre);

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
			listadoCliente(connection, BDNombre);

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
			listadoPatineteAlquilado(connection, BDNombre);

			break;

		case 2:
			listadoPatineteNoAlquilado(connection, BDNombre);

			break;

		case 3:
			listadoCompletoPatinetes(connection, BDNombre);

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
			exportarListadoClientesTXT(connection, BDNombre);

			break;

		case 2:
			exportarListadoPatineteAlquiladoTXT(connection, BDNombre);

			break;

		case 3:
			exportarListadoPatineteNoAlquiladoTXT(connection, BDNombre);

			break;

		case 4:
			exportarListadoCompletoPatinetesTXT(connection, BDNombre);

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
			printSQLException(e);
		} finally {
			consulta.close();
		}
			
		
	}

	// metodo para crear la tabla clientes
	public static void createCliente(Connection connection, String BDNombre) throws SQLException {

		String createString = "create table " + BDNombre + ".cliente " + "(nombre varchar(40) NOT NULL,"
				+ "apellidos varchar(40) NOT NULL," + "edad integer(40) NOT NULL," + "dni varchar(40) NOT NULL,"
				+ "email varchar(40) NOT NULL," + "AlquilerActivo bit NOT NULL ," + "PRIMARY KEY (dni))";

		Statement stmt = null;

		try {

			stmt = connection.createStatement(); // Creamos un Statement
			stmt.executeUpdate(createString); // Ejecutamos la consulta

			System.out.println("¡Se ha creado la tabla Cliente correctamente!");

		} catch (SQLException e) {
			printSQLException(e);
		} finally {
			stmt.close(); // Cerramos la conexión
		}

	}

	// metodo para crear la tabla administradosres
	public static void createAdministrador(Connection connection, String BDNombre) throws SQLException {

		String createString = "create table " + BDNombre + ".administrador " + "(nombre varchar(40) NOT NULL,"
				+ "apellidos varchar(40) NOT NULL," + "edad integer NOT NULL," + "dni varchar(40) NOT NULL,"
				+ "email varchar(40) NOT NULL," + "nombreUsuario varchar(40) NOT NULL,"
				+ "contraseña varchar(40) NOT NULL," + "PRIMARY KEY (dni))";

		Statement stmt = null;

		try {

			stmt = connection.createStatement(); // Creamos un Statement
			stmt.executeUpdate(createString); // Ejecutamos la consulta

			System.out.println("¡Se ha creado la tabla Administradores correctamente!");

		} catch (SQLException e) {
			printSQLException(e);
		} finally {
			stmt.close(); // Cerramos la conexión
		}

	}

	// metodo para crear la tabla Alquiler
	public static void createAlquiler(Connection connection, String BDNombre) throws SQLException {

		String createString = "create table " + BDNombre + ".alquiler " + "(idAlquiler integer NOT NULL AUTO_INCREMENT,"
				+ "dni varchar(40) NOT NULL," + "numSerie integer NOT NULL," + "fechaAlquiler date NOT NULL,"
				+ "fechaDevolucion date NOT NULL," + "kmRecorridoCliente float(40)," + "PRIMARY KEY (idAlquiler),"
				+ "FOREIGN KEY (dni) REFERENCES cliente (dni),"
				+ "FOREIGN KEY (numSerie) REFERENCES patinete (numSerie))";

		Statement stmt = null;

		try {

			stmt = connection.createStatement(); // Creamos un Statement
			stmt.executeUpdate(createString); // Ejecutamos la consulta

			System.out.println("¡Se ha creado la tabla Alquiler correctamente!");

		} catch (SQLException e) {
			printSQLException(e);
		} finally {
			stmt.close(); // Cerramos la conexión
		}

	}

	// Metodo para insertar los administradores
	public static void cargaAdministrador(Connection connection, String BDNombre) throws SQLException {

		Statement stmt = null;

		try {

			stmt = connection.createStatement();

			stmt.executeUpdate("INSERT INTO " + BDNombre + ".administrador VALUES ("
					+ "'Reginaldo','Manga Angue',23, 'X66655544', 'reginaldo@estudiante.edib.es', 'Regi', '1234')");
			stmt.executeUpdate("INSERT INTO " + BDNombre + ".administrador VALUES ("
					+ "'Jose','Paredes',125, '00000001A','jose@estudiante.edib.es', 'jesucristo', 'password')");
			stmt.executeUpdate("INSERT INTO " + BDNombre + ".administrador VALUES ("
					+ "'Kike','Estrada',18, '12345678K', 'kike@estudiante.edib.es', 'Kiko', '123456')");

			System.out.println("");
			System.out.println("¡Se han agregado 3 administradores a la tabla Administrador!");

		} catch (SQLException e) {
			printSQLException(e);
		} finally {
			stmt.close();
		}

	}

	// Metodo para insertar los patinetes
	public static void cargaPatinete(Connection connection, String BDNombre) throws SQLException {

		Statement stmt = null;

		try {

			stmt = connection.createStatement();

			stmt.executeUpdate("INSERT INTO " + BDNombre + ".patinete VALUES ("
					+ "5678, 'Xiami','Negro','Electric Scooter 3',250,1)");
			stmt.executeUpdate("INSERT INTO " + BDNombre + ".patinete VALUES ("
					+ "6789, 'Hiboy','Maron','Patinete Eléctrico S2 Pro',40,1)");
			stmt.executeUpdate("INSERT INTO " + BDNombre + ".patinete VALUES ("
					+ "6556, 'Cecotec','Azul','Patinete Eléctrico Bongo Serie A',700,1)");

			System.out.println("");
			System.out.println("¡Se han agregado 3 patinetes a la tabla Patinete!");

		} catch (SQLException e) {
			printSQLException(e);
		} finally {
			stmt.close();
		}

	}

	// metodo para insertar a los clientes
	public static void cargaCliente(Connection connection, String BDNombre) throws SQLException {

		Statement stmt = null;

		try {

			stmt = connection.createStatement();

			// CAMPOS EQUIPO: TEAM_ID, EQ_NOMBRE, ESTADIO, POBLACION, PROVINCIA, COD_POSTAL
			stmt.executeUpdate("INSERT INTO " + BDNombre + ".cliente VALUES ("
					+ "'Jose','Maria',35, 'X45678990', 'josemaria@docente.edib.es', 0)");
			stmt.executeUpdate("INSERT INTO " + BDNombre + ".cliente VALUES ("
					+ "'Laura','Montenegro',32, 'Y76589006','lauramontenegro@docente.edib.es', 0)");
			stmt.executeUpdate("INSERT INTO " + BDNombre + ".cliente VALUES ("
					+ "'Ivan','Suarez',25, 'X87623790', 'ivansuarez@docente.edib.es', 0)");

			System.out.println("");
			System.out.println("¡Se han agregado 3 clientes a la tabla Cliente!");

		} catch (SQLException e) {
			printSQLException(e);
		} finally {
			stmt.close();
		}

	}

	// metodo para buscar a los clientes registrados mediante su dni
	public static void buscarCliente(Connection connection, String BDNombre) throws SQLException {
		Scanner teclado = new Scanner(System.in);

		System.out.println("");
		System.out.println("===============================");
		System.out.println("========BUSCAR CLIENTE=========");
		System.out.println("===============================");
		System.out.println("");

		System.out.print("introduzca el DNI del cliente a buscar: ");
		System.out.println("");
		String dni = teclado.nextLine();

		Statement consulta = null;
		String query = "select nombre, apellidos, edad, email " + " from " + BDNombre + ".cliente" + " WHERE dni = '"
				+ dni + "'";

		try {

			consulta = connection.createStatement();
			ResultSet registro = consulta.executeQuery(query);

			System.out.println("");
			System.out.println("**** DATOS DEL CLIENTE CON EL DNI: " + dni + " ****");

			if (registro.next()) {

				System.out.println("");
				System.out.println("*************************************************");

				String cliente = registro.getString("nombre");
				System.out.println("Nombre: " + cliente);

				String apellidos = registro.getString("apellidos");
				System.out.println("Apellidos: " + apellidos);

				String edad = registro.getString("edad");
				System.out.println("Edad: " + edad + " años");

				String email = registro.getString("email");
				System.out.println("Email: " + email);

				System.out.println("");
				System.out.println("*************************************************");

			} else {
				System.out.println("");
				System.out.println("dni incorrecto");
			}

		} catch (SQLException e) {
			printSQLException(e);
		} finally {
			consulta.close();
		}

	}

	// Metodo para mostrar el listado de los clientes registrados
	public static void listadoCliente(Connection connection, String BDNombre) throws SQLException {

		Statement consulta = null;
		String query = "select nombre, apellidos, edad, dni, email " + " from " + BDNombre + ".cliente";

		try {

			consulta = connection.createStatement();
			ResultSet registro = consulta.executeQuery(query);

			System.out.println("");
			System.out.println("===============================");
			System.out.println("========LISTADO CLIENTES=======");
			System.out.println("===============================");

			while (registro.next()) {

				System.out.println("");
				System.out.println("*************************************");

				String cliente = registro.getString("nombre");
				System.out.println("Nombre: " + cliente);

				String apellidos = registro.getString("apellidos");
				System.out.println("Apellidos: " + apellidos);

				String edad = registro.getString("edad");
				System.out.println("Edad: " + edad + " años");

				String dni = registro.getString("dni");
				System.out.println("Dni: " + dni);

				String email = registro.getString("email");
				System.out.println("Email: " + email);

				System.out.println("");
				System.out.println("*************************************");
			}

		} catch (SQLException e) {
			printSQLException(e);
		} finally {
			consulta.close();
		}

	}

	// Método para realiar devoluciones de patinetes
	public static void realizarDevolucion(Connection connection, String BDNombre) throws SQLException {

		boolean exit = false;

		do {
			
			System.out.println("");
			System.out.println("===============================");
			System.out.println("======REALIZAR DEVOLUCIÓN======");
			System.out.println("===============================");
			System.out.println("");
			System.out.print("introduzca el Id del Alquiler con el patinete a devolver: ");
			System.out.println("");
			String idAlquiler = teclado.nextLine();

			Statement stmt = null;
			String comprobarAlquiler = "SELECT numSerie, dni " + " from " + BDNombre + ".alquiler" + " WHERE idAlquiler = '"
					+ idAlquiler + "'";

			try {

				stmt = connection.createStatement();
				ResultSet registro = stmt.executeQuery(comprobarAlquiler);

				if (registro.next()) {

					System.out.println("");
					System.out.print("introduzca los kilometros recorridos por el cliente: ");
					System.out.println("");

					double kmRecorridoCliente = teclado.nextDouble();

					// Actualizamos los km recorridos por el cliente
					stmt.executeUpdate("UPDATE " + BDNombre + ".alquiler SET kmRecorridoCliente = '"
							+ kmRecorridoCliente + "'" + " WHERE idAlquiler = '" + idAlquiler + "'");

					// Actualizamos la tabla patinete y lo ponemos en disponible
					ResultSet rs = stmt
							.executeQuery("SELECT numSerie FROM alquiler WHERE idAlquiler = '" + idAlquiler + "'");
					rs.next();
					int numSerie = rs.getInt("numSerie");

					ResultSet alqact = stmt.executeQuery(
							"select AlquilerActivo from cliente where dni = (select dni from alquiler natural join cliente where idAlquiler = "
									+ idAlquiler + ")");
					int AlquilerActivo = 0;
					while (alqact.next()) {

						AlquilerActivo = alqact.getInt("AlquilerActivo");

					}

					stmt.executeUpdate(
							"UPDATE " + BDNombre + ".patinete SET disponible = 1 WHERE numSerie =" + numSerie);

					// Actualizamos los km recorridos por el patinete en la tabla patinete
					ResultSet rs2 = stmt
							.executeQuery("SELECT kmRecorridoPatinete FROM patinete WHERE numSerie =" + numSerie);
					rs2.next();
					double kmRecorridoPatinete = rs2.getInt("kmRecorridoPatinete");

					stmt.executeUpdate("UPDATE " + BDNombre + ".patinete SET kmRecorridoPatinete = '"
							+ (kmRecorridoPatinete + kmRecorridoCliente) + "'" + " WHERE numSerie =" + numSerie);

					if (AlquilerActivo == 1) {

						stmt.executeUpdate(
								"update cliente set AlquilerActivo = 0 where dni = (select dni from alquiler natural join cliente where idAlquiler = "
										+ idAlquiler + ")");

					} else {

						System.out.println("El cliente ya posee un patinete, alquiler rechazado.");
					}
					System.out.println("");
					System.out.println("Se ha devuelto el patinete correctamente.");

					exit = true;

				} else {
					System.out.println("DNI incorrecto.");
				}

			} catch (SQLException e) {
				printSQLException(e);
			} finally {
				stmt.close();
			}

		} while (!exit);
	}

	// Método para exportar el listado de patinetes NO ALQUILADOS a ficheros TXT
	public static void exportarListadoPatineteNoAlquiladoTXT(Connection connection, String BDNombre)
			throws SQLException {

		Statement stmt = null;

		String query = "select numSerie, marca, color, modelo, kmRecorridoPatinete from " + BDNombre
				+ ".patinete where disponible = '1' ";

		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			FileWriter escritura = new FileWriter("C:\\Users\\Programming\\Desktop\\informes.txt");

			// Creamos el buffer
			BufferedWriter buff = new BufferedWriter(escritura);

			buff.write("-- Listado de patinetes no alquilados --");
			buff.newLine();
			buff.newLine();

			while (rs.next()) {

				// Crear archivo
				buff.write("---------------------------------------------");
				buff.newLine();
				String numSerie = rs.getString("numSerie");
				buff.write("Nº de Serie: " + numSerie);
				buff.newLine();

				String marca = rs.getString("marca");
				buff.write("Marca: " + marca);
				buff.newLine();

				String color = rs.getString("color");
				buff.write("Color: " + color);
				buff.newLine();

				String modelo = rs.getString("modelo");
				buff.write("Modelo: " + modelo);
				buff.newLine();

				String kmRecorridoPatinete = rs.getString("kmRecorridoPatinete");
				buff.write("Km recorridos: " + kmRecorridoPatinete + "km");
				buff.newLine();

				buff.write("---------------------------------------------");
				buff.newLine();
				buff.newLine();

			}

			System.out.println("El fichero se ha escrito y guardado correctamente!");

			buff.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			stmt.close();
		}

	}

	// Método para exportar el listado de patinetes ALQUILADOS a ficheros TXT
	public static void exportarListadoPatineteAlquiladoTXT(Connection connection, String BDNombre) throws SQLException {

		Statement stmt = null;

		String query = "select numSerie, marca, color, modelo, kmRecorridoPatinete from " + BDNombre
				+ ".patinete where disponible = '0' ";

		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			FileWriter escritura = new FileWriter("C:\\Users\\Programming\\Desktop\\informes.txt");

			// Creamos el buffer
			BufferedWriter buff = new BufferedWriter(escritura);

			buff.write("-- Listado de patinetes alquilados --");
			buff.newLine();
			buff.newLine();

			while (rs.next()) {

				// Crear archivo
				buff.write("---------------------------------------------");
				buff.newLine();
				String numSerie = rs.getString("numSerie");
				buff.write("Nº de Serie: " + numSerie);
				buff.newLine();

				String marca = rs.getString("marca");
				buff.write("Marca: " + marca);
				buff.newLine();

				String color = rs.getString("color");
				buff.write("Color: " + color);
				buff.newLine();

				String modelo = rs.getString("modelo");
				buff.write("Modelo: " + modelo);
				buff.newLine();

				String kmRecorridoPatinete = rs.getString("kmRecorridoPatinete");
				buff.write("Km recorridos: " + kmRecorridoPatinete + "km");
				buff.newLine();

				buff.write("---------------------------------------------");
				buff.newLine();
				buff.newLine();

			}

			System.out.println("El fichero se ha escrito y guardado correctamente!");

			buff.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			stmt.close();
		}

	}

	// Método para exportar el listado de TODOS los patinetes a ficheros TXT
	public static void exportarListadoCompletoPatinetesTXT(Connection connection, String BDNombre) throws SQLException {

		Statement stmt = null;

		String query = "select * from " + BDNombre + ".patinete ";

		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			FileWriter escritura = new FileWriter("C:\\Users\\Programming\\Desktop\\informes.txt");

			// Creamos el buffer
			BufferedWriter buff = new BufferedWriter(escritura);

			buff.write("-- Listado total patinetes --");
			buff.newLine();
			buff.newLine();

			while (rs.next()) {

				// Crear archivo
				buff.write("---------------------------------------------");
				buff.newLine();
				String numSerie = rs.getString("numSerie");
				buff.write("Nº de Serie: " + numSerie);
				buff.newLine();

				String marca = rs.getString("marca");
				buff.write("Marca: " + marca);
				buff.newLine();

				String color = rs.getString("color");
				buff.write("Color: " + color);
				buff.newLine();

				String modelo = rs.getString("modelo");
				buff.write("Modelo: " + modelo);
				buff.newLine();

				String kmRecorridoPatinete = rs.getString("kmRecorridoPatinete");
				buff.write("Km recorridos: " + kmRecorridoPatinete + "km");
				buff.newLine();

				String disponible = rs.getString("disponible");
				buff.write("Disponible: " + disponible);
				buff.newLine();

				buff.write("---------------------------------------------");
				buff.newLine();
				buff.newLine();

			}

			System.out.println("El fichero se ha escrito y guardado correctamente!");

			buff.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			stmt.close();
		}

	}

	// Método para exportar el listado de TODOS los patinetes a ficheros TXT
	public static void exportarListadoClientesTXT(Connection connection, String BDNombre) throws SQLException {

		Statement stmt = null;

		String query = "select nombre, apellidos, edad, dni, email " + " from " + BDNombre + ".cliente";

		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			FileWriter escritura = new FileWriter("C:\\Users\\Programming\\Desktop\\informes.txt");

			// Creamos el buffer
			BufferedWriter buff = new BufferedWriter(escritura);

			buff.write("-- Listado clientes --");
			buff.newLine();
			buff.newLine();

			while (rs.next()) {

				// Crear archivo
				buff.write("---------------------------------------------");
				buff.newLine();
				String cliente = rs.getString("nombre");
				buff.write("Nombre: " + cliente);
				buff.newLine();

				String apellidos = rs.getString("apellidos");
				buff.write("Apellidos: " + apellidos);
				buff.newLine();

				String edad = rs.getString("edad");
				buff.write("Edad: " + edad + " años");
				buff.newLine();

				String dni = rs.getString("dni");
				buff.write("DNI: " + dni);
				buff.newLine();

				String email = rs.getString("email");
				buff.write("Email: " + email);
				buff.newLine();

				buff.write("---------------------------------------------");
				buff.newLine();
				buff.newLine();

			}

			System.out.println("El fichero se ha escrito y guardado correctamente!");

			buff.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			stmt.close();
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

	public static void registrarNuevosClientes(Connection connection, String electricskate) throws SQLException {

		System.out.println("");
		System.out.println("===============================");
		System.out.println("====REGISTRAR NUEVO CLIENTE====");
		System.out.println("===============================");
		System.out.println("");
		System.out.println("introduzca el nombre");
		String nombre = teclado.nextLine();
		System.out.println("introduzca los apellidos");
		String apellidos = teclado.nextLine();
		System.out.println("introduzca la edad");
		int edad = teclado.nextInt();
		System.out.println("introduzca el dni");
		teclado.nextLine();
		String dni = teclado.nextLine();
		System.out.println("introduzca el email");
		String email = teclado.nextLine();
		Statement stmt = null;

		try {
			stmt = connection.createStatement();
			stmt.executeUpdate("insert into " + electricskate + ".cliente VALUES('" + nombre + "','" + apellidos + "',"
					+ edad + ",'" + dni + "','" + email + "')");
			System.out.println("");
			System.out.println("Usuario añadido correctamente");

		} catch (SQLException e) {
			printSQLException(e);
		} finally {

			connection.close();
		}
	}

	public static void registrarNuevosAdministradores(Connection connection, String electricskate) throws SQLException {

		System.out.println("");
		System.out.println("===============================");
		System.out.println("=REGISTRAR NUEVO ADMINISTRADOR=");
		System.out.println("===============================");
		System.out.println("");
		System.out.println("introduzca el nombre");
		String nombre = teclado.nextLine();
		System.out.println("introduzca los apellidos");
		String apellidos = teclado.nextLine();
		System.out.println("introduzca la edad");
		int edad = teclado.nextInt();
		System.out.println("introduzca el dni");
		teclado.nextLine();
		String dni = teclado.nextLine();
		System.out.println("introduzca el email");
		String email = teclado.nextLine();
		System.out.println("introduzca el nombre de usuario");
		String nombreUsuario = teclado.nextLine();
		System.out.println("introduzca la contraseña");
		String contraseña = teclado.nextLine();
		Statement stmt1 = null;

		try {
			stmt1 = connection.createStatement();

			stmt1.executeUpdate("insert into " + electricskate + ".administrador VALUES('" + nombre + "','" + apellidos
					+ "'," + edad + ",'" + dni + "','" + email + "','" + nombreUsuario + "','" + contraseña + "')");
			System.out.println("");
			System.out.println("Usuario administrador añadido correctamente");

		} catch (SQLException e) {
			printSQLException(e);
		} finally {

			connection.close();
		}

	}

	public static void listadoPatineteAlquilado(Connection con, String BDNombre) throws SQLException {

		Statement stmt = null;

		System.out.println("");
		System.out.println("================================");
		System.out.println("==LISTADO PATINETES ALQUILADOS==");
		System.out.println("================================");

		String query = "select numSerie, marca, color, modelo from " + BDNombre + ".patinete where disponible = '0' ";

		try {

			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				System.out.println("");
				System.out.println("---------------------------------------------");

				String numSerie = rs.getString("numSerie");
				System.out.println("Nº de Serie: " + numSerie);

				String marca = rs.getString("marca");
				System.out.println("Marca: " + marca);

				String color = rs.getString("color");
				System.out.println("Color: " + color);

				String modelo = rs.getString("modelo");
				System.out.println("Modelo: " + modelo);

				System.out.println("---------------------------------------------");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			stmt.close();
		}

	}

	public static void listadoPatineteNoAlquilado(Connection con, String BDNombre) throws SQLException {

		Statement stmt = null;

		System.out.println("");
		System.out.println("===================================");
		System.out.println("==LISTADO PATINETES NO ALQUILADOS==");
		System.out.println("===================================");

		String query = "select numSerie, marca, color, modelo from " + BDNombre + ".patinete where disponible = '1' ";

		try {

			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				System.out.println("");
				System.out.println("---------------------------------------------");

				String numSerie = rs.getString("numSerie");
				System.out.println("Nº de Serie: " + numSerie);

				String marca = rs.getString("marca");
				System.out.println("Marca: " + marca);

				String color = rs.getString("color");
				System.out.println("Color: " + color);

				String modelo = rs.getString("modelo");
				System.out.println("Modelo: " + modelo);

				System.out.println("---------------------------------------------");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			stmt.close();
		}

	}

	public static void listadoCompletoPatinetes(Connection con, String BDNombre) throws SQLException {

		Statement stmt = null;

		System.out.println("");
		System.out.println("================================");
		System.out.println("===LISTADO COMPLETO PATINETES===");
		System.out.println("================================");

		String query = "select numSerie, marca, color, modelo from " + BDNombre + ".patinete";

		try {

			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				System.out.println("");
				System.out.println("---------------------------------------------");

				String numSerie = rs.getString("numSerie");
				System.out.println("Nº de Serie: " + numSerie);

				String marca = rs.getString("marca");
				System.out.println("Marca: " + marca);

				String color = rs.getString("color");
				System.out.println("Color: " + color);

				String modelo = rs.getString("modelo");
				System.out.println("Modelo: " + modelo);

				System.out.println("---------------------------------------------");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			stmt.close();
		}

	}

	// (KIKE)
	// Método para añadir patinete.

	public static void registrarPatinete(Connection connection, String BDnom) throws SQLException {

		System.out.println("");
		System.out.println("===============================");
		System.out.println("======REGISTRAR PATINETE=======");
		System.out.println("===============================");
		System.out.println("");

		System.out.println("Introduzca los valores correspondientes");
		System.out.println("");

		System.out.print("Nº Serie: ");
		int numSerie = teclado.nextInt();
		System.out.println(" ");

		teclado.nextLine();
		System.out.print("Marca: ");
		String marca = teclado.nextLine();

		System.out.println(" ");

		System.out.print("Color: ");
		String color = teclado.nextLine();
		System.out.println(" ");

		System.out.print("Modelo: ");
		String modelo = teclado.nextLine();
		System.out.println(" ");

		Statement stmt = null;

		try {
			stmt = connection.createStatement();

			stmt.executeUpdate("insert into " + BDnom + ".patinete VALUES(" + numSerie + ",'" + marca + "','" + color
					+ "','" + modelo + "'," + 0 + ", " + 1 + ")");
			System.out.println("");
			System.out.println("Se ha añadido el patinete nº " + numSerie + " correctamente.");

		} catch (SQLException e) {
			printSQLException(e);
		} finally {

			connection.close();
		}

	}

	public static void realizarAlquiler(Connection connection, String BDnom) throws SQLException {

		System.out.println("");
		System.out.println("===============================");
		System.out.println("======REALIZAR ALQUILER========");
		System.out.println("===============================");
		System.out.println("");
		
		System.out.println("Introduzca los valores correspondientes:");
		System.out.println("");
		
		System.out.print("DNI: ");
		String dni = teclado.nextLine();
		
		System.out.println(" ");
		
		System.out.print("Nº de serie: ");
		int numSerie = teclado.nextInt();
		System.out.println(" ");
		
		teclado.nextLine();
		System.out.print("[0000-00-00] ");
		System.out.print("Fecha Alquiler: ");
		
		String dateAlquiler = teclado.nextLine();
		System.out.println(" ");
		
		
		System.out.print("[0000-00-00] ");
		System.out.print("Fecha Devolución: ");
		String dateDevolucion = teclado.nextLine();
		System.out.println(" ");
	
		
		Statement stmt = null;

		try {
			stmt = connection.createStatement();
			
			ResultSet alqact = stmt.executeQuery("select AlquilerActivo from cliente where dni = '" + dni + "'");
			int AlquilerActivo = 0;
			while (alqact.next()) {
			
			AlquilerActivo = alqact.getInt("AlquilerActivo");
			
			if (AlquilerActivo == 1) {
				
				System.out.println("El cliente ya tiene un alquiler activo, no es posible efectuar el alquiler");
				
			}else {
				
				stmt.executeUpdate("insert into " + BDnom + ".alquiler VALUES(NULL,'" + dni + "','" +  numSerie
						+ "','" + dateAlquiler + "','" + dateDevolucion + "',NULL)");
				System.out.println("");
				
				
				stmt.executeUpdate("update patinete set disponible = 0 where numSerie = " + numSerie);
				stmt.executeUpdate("update cliente set AlquilerActivo = 1 where dni = '" + dni +"'");

				System.out.println("Se ha añadido el alquiler correctamente.");
				System.out.println("El patinete con nº " + numSerie + " ya no se encuentra disponible.");
				
				
			}

			}
		} catch (SQLException e) {
				printSQLException(e);
			} finally {
				stmt.close();
			}
	
	
	}
}
