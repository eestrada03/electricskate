package metodos.tablas;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import metodos.cargarDatos.CargarDatos;
import metodos.excepciones.Excepciones;

/**
 * Esta clase sirve para crear las tablas de la BBDD desde java eclipse 
 * @author Reginaldo, Alberto, Kike, Jose Y Luz
 * @version 1.0
 */

public class Tablas {
	
	/**
	 * M?todo para llamar otros metodos de creaci?n de las tablas a la BBDD
	 * @param connection la variable que establece la conexi?n con la base de datos
	 * @param BDNombre nombre de la base de datos por defecto
	 * @throws SQLException este nos sirve para lanzar una excepci?n
	 */
	
	public static void createBaseDeDatos(Connection connection, String BDNombre) throws SQLException {
		createPatinete(connection, BDNombre);
		createCliente(connection, BDNombre);
		createAdministrador(connection, BDNombre);
		createAlquiler(connection, BDNombre);
		CargarDatos.administrador(connection, BDNombre);
		CargarDatos.patinete(connection, BDNombre);
		CargarDatos.cliente(connection, BDNombre);

	}

	/**
	 * M?todo para crear la tabla de patinetes en la BBDD
	 * @param connection la variable que establece la conexi?n con la base de datos
	 * @param BDNombre nombre de la base de datos por defecto
	 * @throws SQLException este nos sirve para lanzar una excepci?n
	 */
	
	public static void createPatinete(Connection connection, String BDNombre) throws SQLException {

		String createString = "create table " + BDNombre + ".patinete " + "(numSerie integer NOT NULL,"
				+ "marca varchar(40) NOT NULL," + "color varchar(40) NOT NULL," + "modelo varchar(40) NOT NULL,"
				+ "kmRecorridoPatinete float(40) NOT NULL," + "disponible bit NOT NULL," + "PRIMARY KEY (numSerie))";

		Statement stmt = null;

		try {

			stmt = connection.createStatement(); // Creamos un Statement
			stmt.executeUpdate(createString); // Ejecutamos la consulta

			System.out.println("?Se ha creado la tabla Patinete correctamente!");

		} catch (SQLException e) {
			Excepciones.printSQLException(e);
		} finally {
			stmt.close(); // Cerramos la conexi?n
		}

	}
	
	/**
	 * M?todo para crear la tabla de los clientes en la BBDD
	 * @param connection la variable que establece la conexi?n con la base de datos
	 * @param BDNombre nombre de la base de datos por defecto
	 * @throws SQLException este nos sirve para lanzar una excepci?n
	 */
	
	// metodo para crear la tabla clientes
	public static void createCliente(Connection connection, String BDNombre) throws SQLException {

		String createString = "create table " + BDNombre + ".cliente " + "(nombre varchar(40) NOT NULL,"
				+ "apellidos varchar(40) NOT NULL," + "edad integer(40) NOT NULL," + "dni varchar(40) NOT NULL,"
				+ "email varchar(40) NOT NULL," + "alquilerActivo bit NOT NULL ," + "PRIMARY KEY (dni))";

		Statement stmt = null;

		try {

			stmt = connection.createStatement(); // Creamos un Statement
			stmt.executeUpdate(createString); // Ejecutamos la consulta

			System.out.println("?Se ha creado la tabla Cliente correctamente!");

		} catch (SQLException e) {
			Excepciones.printSQLException(e);
		} finally {
			stmt.close(); // Cerramos la conexi?n
		}

	}
	
	/**
	 * M?todo para crear la tabla de los administradores en la BBDD
	 * @param connection la variable que establece la conexi?n con la base de datos
	 * @param BDNombre nombre de la base de datos por defecto
	 * @throws SQLException este nos sirve para lanzar una excepci?n
	 */

	// metodo para crear la tabla administradosres
	public static void createAdministrador(Connection connection, String BDNombre) throws SQLException {

		String createString = "create table " + BDNombre + ".administrador " + "(nombre varchar(40) NOT NULL,"
				+ "apellidos varchar(40) NOT NULL," + "edad integer NOT NULL," + "dni varchar(40) NOT NULL,"
				+ "email varchar(40) NOT NULL," + "nombreUsuario varchar(40) NOT NULL,"
				+ "contrase?a varchar(40) NOT NULL," + "PRIMARY KEY (dni))";

		Statement stmt = null;

		try {

			stmt = connection.createStatement(); // Creamos un Statement
			stmt.executeUpdate(createString); // Ejecutamos la consulta

			System.out.println("?Se ha creado la tabla Administradores correctamente!");

		} catch (SQLException e) {
			Excepciones.printSQLException(e);
		} finally {
			stmt.close(); // Cerramos la conexi?n
		}

	}
	
	/**
	 * M?todo para crear la tabla de alquiler en la BBDD
	 * @param connection la variable que establece la conexi?n con la base de datos
	 * @param BDNombre nombre de la base de datos por defecto
	 * @throws SQLException este nos sirve para lanzar una excepci?n
	 */

	// metodo para crear la tabla Alquiler
	public static void createAlquiler(Connection connection, String BDNombre) throws SQLException {

		String createString = "create table " + BDNombre + ".alquiler " + "(idAlquiler integer NOT NULL AUTO_INCREMENT,"
				+ "dni varchar(40) NOT NULL," + "numSerie integer NOT NULL," + "fechaAlquiler date NOT NULL,"
				+ "fechaDevolucion date NOT NULL," + "kmRecorridoCliente float(40) NOT NULL," + "PRIMARY KEY (idAlquiler),"
				+ "FOREIGN KEY (dni) REFERENCES cliente (dni),"
				+ "FOREIGN KEY (numSerie) REFERENCES patinete (numSerie))";

		Statement stmt = null;

		try {

			stmt = connection.createStatement(); // Creamos un Statement
			stmt.executeUpdate(createString); // Ejecutamos la consulta

			System.out.println("?Se ha creado la tabla Alquiler correctamente!");

		} catch (SQLException e) {
			Excepciones.printSQLException(e);
		} finally {
			stmt.close(); // Cerramos la conexi?n
		}

	}


	
}
