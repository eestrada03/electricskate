package principal;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Metodos {
	
public static void createPatinete(Connection connection, String BDNombre) throws SQLException{
		
		String createString = "create table " + BDNombre + ".patinete " + "(numSerie integer NOT NULL," 
				+ "marca varchar(40) NOT NULL," + "color varchar(40) NOT NULL," 
				+ "modelo varchar(40) NOT NULL," + "kmRecorrido float(40) NOT NULL,"
				+ "PRIMARY KEY (numSerie))";
		
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

public static void createCliente(Connection connection, String BDNombre) throws SQLException{
	
	String createString = "create table " + BDNombre + ".cliente " + "(nombre varchar(40) NOT NULL," 
			+ "apellido varchar(40) NOT NULL," + "edad integer(40) NOT NULL," + "numSerie integer NOT NULL,"
			+ "dni varchar(40) NOT NULL," + "email varchar(40) NOT NULL," + "FOREIGN KEY (numSerie) REFERENCES patinete (numSerie),"
			+ "PRIMARY KEY (dni))";
	
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

public static void createAdministrador(Connection connection, String BDNombre) throws SQLException{
	
	String createString = "create table " + BDNombre + ".administrador " + "(nombre varchar(40) NOT NULL," 
			+ "apellido varchar(40) NOT NULL," + "edad integer NOT NULL," 
			+ "dni varchar(40) NOT NULL," + "email varchar(40) NOT NULL," + "nombreUsuario varchar(40) NOT NULL," + "contraseña varchar(40) NOT NULL,"
			+ "PRIMARY KEY (dni))";
	
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
