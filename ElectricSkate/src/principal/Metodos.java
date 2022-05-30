package principal;

import java.io.*;
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
				+ "kmRecorridoPatinete float(40) NOT NULL," + "disponible bit NOT NULL," + "PRIMARY KEY (numSerie))";

		Statement stmt = null;

		try {

			stmt = connection.createStatement(); // Creamos un Statement
			stmt.executeUpdate(createString); // Ejecutamos la consulta

			System.out.println("�Se ha creado la tabla Patinete correctamente!");

		} catch (SQLException e) {
			printSQLException(e);
		} finally {
			stmt.close(); // Cerramos la conexi�n
		}

	}

	// metodo para crear la tabla clientes
	public static void createCliente(Connection connection, String BDNombre) throws SQLException {

		String createString = "create table " + BDNombre + ".cliente " + "(nombre varchar(40) NOT NULL,"
				+ "apellidos varchar(40) NOT NULL," + "edad integer(40) NOT NULL," + "dni varchar(40) NOT NULL," 
				+ "email varchar(40) NOT NULL," + "PRIMARY KEY (dni))";

		Statement stmt = null;

		try {

			stmt = connection.createStatement(); // Creamos un Statement
			stmt.executeUpdate(createString); // Ejecutamos la consulta

			System.out.println("�Se ha creado la tabla Cliente correctamente!");

		} catch (SQLException e) {
			printSQLException(e);
		} finally {
			stmt.close(); // Cerramos la conexi�n
		}

	}

	// metodo para crear la tabla administradosres
	public static void createAdministrador(Connection connection, String BDNombre) throws SQLException {

		String createString = "create table " + BDNombre + ".administrador " + "(nombre varchar(40) NOT NULL,"
				+ "apellido varchar(40) NOT NULL," + "edad integer NOT NULL," + "dni varchar(40) NOT NULL,"
				+ "email varchar(40) NOT NULL," + "nombreUsuario varchar(40) NOT NULL,"
				+ "contrase�a varchar(40) NOT NULL," + "PRIMARY KEY (dni))";

		Statement stmt = null;

		try {

			stmt = connection.createStatement(); // Creamos un Statement
			stmt.executeUpdate(createString); // Ejecutamos la consulta

			System.out.println("�Se ha creado la tabla Administradores correctamente!");

		} catch (SQLException e) {
			printSQLException(e);
		} finally {
			stmt.close(); // Cerramos la conexi�n
		}

	}
	
	public static void createAlquiler(Connection connection, String BDNombre) throws SQLException {

		String createString = "create table " + BDNombre + ".alquiler " + "(idAlquiler integer NOT NULL,"
				+ "dni varchar(40) NOT NULL," + "numSerie integer NOT NULL," + "fechaAlquiler date NOT NULL,"
				+ "fechaDevolucion date NOT NULL," + "kmRecorridoCliente float(40) NOT NULL," 
				+ "PRIMARY KEY (idAlquiler)," 
				+ "FOREIGN KEY (dni) REFERENCES cliente (dni)," + "FOREIGN KEY (numSerie) REFERENCES patinete (numSerie))";

		Statement stmt = null;

		try {

			stmt = connection.createStatement(); // Creamos un Statement
			stmt.executeUpdate(createString); // Ejecutamos la consulta

			System.out.println("�Se ha creado la tabla Alquiler correctamente!");

		} catch (SQLException e) {
			printSQLException(e);
		} finally {
			stmt.close(); // Cerramos la conexi�n
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
					+ "6789, 'Hiboy','Maron','Patinete El�ctrico S2 Pro',40,1)");
			stmt.executeUpdate("INSERT INTO " + BDNombre + ".patinete VALUES ("
					+ "6556, 'Cecotec','Azul','Patinete El�ctrico Bongo Serie A',700,1)");

			System.out.println("");
			System.out.println("�Se han agregado 3 patinetes a la tabla Patinete!");

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
					+ "'Jose','Maria',35, 'X45678990', 'josemaria@docente.edib.es')");
			stmt.executeUpdate("INSERT INTO " + BDNombre + ".cliente VALUES ("
					+ "'Laura','Montenegro',32, 'Y76589006','lauramontenegro@docente.edib.es')");
			stmt.executeUpdate("INSERT INTO " + BDNombre + ".cliente VALUES ("
					+ "'Ivan','Suarez',25, 'X87623790', 'ivansuarez@docente.edib.es')");

			System.out.println("");
			System.out.println("�Se han agregado 3 clientes a la tabla Cliente!");

		} catch (SQLException e) {
			printSQLException(e);
		} finally {
			stmt.close();
		}

	}

	// metodo para buscar a los clientes registrados
	public static void busquedaClientes(Connection connection, String BDNombre) throws SQLException {
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
			System.out.println("3) Volver al men� principal");
			System.out.println("");
			System.out.println("=======================================");
			System.out.println("===========Qu� desea hacer?============");
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
				System.out.println("salir al men� principal");
				break;

			default:

				System.out.println("�error! esta opcion no es valida");
			}

		} while (!salir);

	}

	// M�todo para realizar devoluciones de patinetes
	public static void realizarDevolucion(Connection connection, String BDNombre) throws SQLException{
		
		boolean exit = false;
		
		do {
		
			System.out.println("introduzca el DNI del cliente con el patinete a devolver: ");
			System.out.println("");
			String dni = teclado.nextLine();
				
			Statement stmt = null;
			String comprobar = "SELECT numSerie " + " from " + BDNombre + ".cliente"
					+ " WHERE dni = '" + dni + "'";
			
			
			try {
	
				stmt = connection.createStatement();
				ResultSet registro = stmt.executeQuery(comprobar);
				
		
				
				if (registro.next()) {
					
			/*		double kmRecorridoPatinete = SELECT kmRecorridoPatinete " + " from " + BDNombre + ".patinete"
					+ " WHERE numSerie = '" + numSerie + "'";
					double kmRecorridoCliente = SELECT kmRecorridoCliente " + " from " + BDNombre + ".cliente"
					+ " WHERE numSerie = '" + numSerie + "'";
					
					stmt.executeUpdate("UPDATE " + BDNombre + ".patinete SET kmRecorrido = " 
									+ (kmRecorridoPatinete + kmRecorridoCliente) + " WHERE dni = '" + dni + "'");
				*/	
					stmt.executeUpdate("UPDATE " + BDNombre + ".cliente SET numSerie = NULL " + " WHERE dni = '" + dni + "'");
					
					
					System.out.println("");
					System.out.println("Se ha devuelto el patinete correctamente.");
					
					 exit = true;
				
				}else {
					System.out.println("DNI incorrecto.");
				}
				
			} catch (SQLException e) {
				printSQLException(e);
			} finally {
				stmt.close();
			}
			
		} while (!exit);
	}
	
	// M�todo para exportar listados a ficheros TXT
	public static void ExportarArchivoTXT(Connection connection, String BDNombre) throws SQLException{
		
		String frase = "SELECT * " + " FROM " + BDNombre + ".cliente";
		
		try {
			// Crear archivo
			FileWriter escritura = new FileWriter ("D:\\informes.txt");
			
			// Creamos el buffer
			BufferedWriter buff = new BufferedWriter (escritura);
			
			// Escribimos la frase en el buffer
			buff.write(frase);
			buff.newLine();
			buff.newLine();
			
			System.out.println("guardado correctamente");
			
			buff.close();
			
			
		} catch (IOException e){
			e.printStackTrace();
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
	
	public static void listadoPatinetes(Connection con, String BDNombre) throws SQLException {
		
		String query = "";
		Statement stmt = null;

		System.out.println("-- Lista de Patinetes --");
		System.out.println("");
		System.out.println("1) Listado de patinetes alquilados");
		System.out.println("");
		System.out.println("2) Listado de patinetes no alquilados");
		System.out.println("");
		System.out.println("3) Listado de patinetes alquilados y no alquilados");
		System.out.println("");
		System.out.print("Seleccione una opci�n: ");

		int menu = teclado.nextInt();
		teclado.nextLine();

		switch (menu) {

		case 1:
			
			System.out.println("");
			System.out.println("-- Listado de patinetes alquilados --");

			query = "select numSerie, marca, color, modelo from " + BDNombre + ".patinete where disponible = '0' ";

			try {

				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);

				while (rs.next()) {

					System.out.println("");
					System.out.println("---------------------------------------------");

					String numSerie = rs.getString("numSerie");
					System.out.println("N� de Serie: " + numSerie);

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

			break;

		case 2:
			
			System.out.println("");
			System.out.println("-- Listado de patinetes no alquilados --");

			query = "select numSerie, marca, color, modelo from " + BDNombre + ".patinete where disponible = '1' ";

			try {

				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);

				while (rs.next()) {

					System.out.println("");
					System.out.println("---------------------------------------------");

					String numSerie = rs.getString("numSerie");
					System.out.println("N� de Serie: " + numSerie);

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
			
			break;

		case 3:
			
			System.out.println("");
			System.out.println("-- Listado de patinetes alquilados y no alquilados --");

			query = "select numSerie, marca, color, modelo from " + BDNombre + ".patinete";

			try {

				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);

				while (rs.next()) {

					System.out.println("");
					System.out.println("---------------------------------------------");

					String numSerie = rs.getString("numSerie");
					System.out.println("N� de Serie: " + numSerie);

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
			
			break;

		}

	}
	
	
}
