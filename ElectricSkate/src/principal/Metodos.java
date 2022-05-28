package principal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Metodos {

	static Scanner teclado = new Scanner(System.in);

	public static void createPatinete(Connection connection, String BDNombre) throws SQLException {

		String createString = "create table " + BDNombre + ".patinete " + "(numSerie integer NOT NULL,"
				+ "marca varchar(40) NOT NULL," + "color varchar(40) NOT NULL," + "modelo varchar(40) NOT NULL,"
				+ "kmRecorrido float(40) NOT NULL," + "PRIMARY KEY (numSerie))";

		Statement stmt = null;

		try {

			stmt = connection.createStatement(); // Creamos un Statement
			stmt.executeUpdate(createString); // Ejecutamos la consulta

			System.out.println("¡Se ha creado la tabla patinete correctamente!");

		} catch (SQLException e) {
			printSQLException(e);
		} finally {
			stmt.close(); // Cerramos la conexión
		}

	}

	// metodo para crear la tabla clientes
	public static void createCliente(Connection connection, String BDNombre) throws SQLException {

		String createString = "create table " + BDNombre + ".cliente " + "(nombre varchar(40) NOT NULL,"
				+ "apellido varchar(40) NOT NULL," + "edad integer(40) NOT NULL," + "numSerie integer NOT NULL,"
				+ "dni varchar(40) NOT NULL," + "email varchar(40) NOT NULL,"
				+ "FOREIGN KEY (numSerie) REFERENCES patinete (numSerie)," + "PRIMARY KEY (dni))";

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
				+ "apellido varchar(40) NOT NULL," + "edad integer NOT NULL," + "dni varchar(40) NOT NULL,"
				+ "email varchar(40) NOT NULL," + "nombreUsuario varchar(40) NOT NULL,"
				+ "contraseña varchar(40) NOT NULL," + "PRIMARY KEY (dni))";

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

	// Metodo para insertar los patinetes
	public static void cargaPatinete(Connection connection, String BDNombre) throws SQLException {

		Statement stmt = null;

		try {

			stmt = connection.createStatement();

			stmt.executeUpdate("INSERT INTO " + BDNombre + ".patinete VALUES ("
					+ "5678, 'Xiami','Electric Scooter 3','Negro',250)");
			stmt.executeUpdate("INSERT INTO " + BDNombre + ".patinete VALUES ("
					+ "6789, 'Hiboy','Patinete Eléctrico S2 Pro','Maron',40)");
			stmt.executeUpdate("INSERT INTO " + BDNombre + ".patinete VALUES ("
					+ "6556, 'Cecotec','Patinete eléctrico Bongo Serie A','Azul',700)");

			System.out.println("");
			System.out.println("¡Se han agregado 3 patinetes a la tabla PATINETE!");

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
					+ "'Jose','Maria',35,5678, 'X45678990', 'josemaria@docente.edib.es')");
			stmt.executeUpdate("INSERT INTO " + BDNombre + ".cliente VALUES ("
					+ "'Laura','Montenegro',32,6789, 'Y76589006','lauramontenegro@docente.edib.es')");
			stmt.executeUpdate("INSERT INTO " + BDNombre + ".cliente VALUES ("
					+ "'Ivan','Suarez',25,6556, 'X87623790', 'ivansuarez@docente.edib.es')");

			System.out.println("");
			System.out.println("¡Se han agregado 3 clientes a la tabla Cliente!");

		} catch (SQLException e) {
			printSQLException(e);
		} finally {
			stmt.close();
		}

	}

	// metodo para buscar a los clientes registrados
	public static void BusquedaClientes(Connection connection, String BDNombre) throws SQLException {
		boolean salir = false;
		do {
			
			System.out.println("");
			System.out.println("=======================================");
			System.out.println("=========BUSQUEDA DE CLIENTES==========");
			System.out.println("=======================================");
			System.out.println("");
			System.out.println("1) Ver listado de clientes");
			System.out.println("");
			System.out.println("2) Buscar clientes mediante su dni");
			System.out.println("");
			System.out.println("3) Volver al menú principal");
			System.out.println("");
			System.out.println("=======================================");
			System.out.println("===========Qué desea hacer?============");
			System.out.println("=======================================");
			System.out.println("");
			int opcion = teclado.nextInt();

			switch (opcion) {

			case 1:

				Statement stmt = null;
				String query = "select nombre, apellido, numSerie, edad, dni, email " + " from " + BDNombre
						+ ".cliente";

				try {

					stmt = connection.createStatement();
					ResultSet registro = stmt.executeQuery(query);

					System.out.println("");
					System.out.println("**** LISTADO DE LOS CLIENTES ****");

					while (registro.next()) {

						System.out.println("");
						System.out.println("*************************************");

						String cliente = registro.getString("nombre");
						System.out.println("Nombre: " + cliente);

						String apellido = registro.getString("apellido");
						System.out.println("Apellido: " + apellido);

						String edad = registro.getString("edad");
						System.out.println("Edad: " + edad);

						String numSerie = registro.getString("numSerie");
						System.out.println("NumSerie: " + numSerie);

						String dni = registro.getString("dni");
						System.out.println("Dni: " + dni);

						String email = registro.getString("email");
						System.out.println("Email: " + email);

						System.out.println("*************************************");
					}

				} catch (SQLException e) {
					printSQLException(e);
				} finally {
					stmt.close();
				}
				break;

			case 2:

				teclado.nextLine();
				System.out.println("");
				System.out.println("introduzca el DNI del cliente a buscar");
				System.out.println("");
				String dni = teclado.nextLine();
				System.out.println("");

				Statement consulta = null;
				String consultar = "select nombre, apellido, numSerie, edad, email " + " from " + BDNombre + ".cliente"
						+ " WHERE dni = '" + dni + "'";

				try {

					consulta = connection.createStatement();
					ResultSet registro = consulta.executeQuery(consultar);

					System.out.println("");
					System.out.println("**** LISTA DE LOS CLIENTES CON EL DNI: " + dni + " ****");

					if (registro.next()) {

						System.out.println("");
						System.out.println("*************************************");

						String cliente = registro.getString("nombre");
						System.out.println("Nombre: " + cliente);

						String apellido = registro.getString("apellido");
						System.out.println("Apellido: " + apellido);

						String edad = registro.getString("edad");
						System.out.println("Edad: " + edad);

						String numSerie = registro.getString("numSerie");
						System.out.println("NumSerie: " + numSerie);

						String email = registro.getString("email");
						System.out.println("Email: " + email);

						System.out.println("*************************************");
						System.out.println("");

					} else {
						System.out.println("");
						System.out.println("dni incorrecto");
					}

				} catch (SQLException e) {
					printSQLException(e);
				} finally {
					consulta.close();

				}
				break;

			case 3:
				salir = true;
				System.out.println("salir al menú principal");
				break;

			default:

				System.out.println("¡error! esta opcion no es valida");
			}

		} while (!salir);

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
