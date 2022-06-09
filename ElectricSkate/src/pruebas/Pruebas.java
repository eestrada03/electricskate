package pruebas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import metodos.excepciones.Excepciones;

public class Pruebas {
	
	public static int edadCliente (Connection connection, String electricskate, String dni) throws SQLException {
		
		int edad = 0;
		
		//Abrimos el Statement
		Statement consulta = null;
		//Creamos la sentencia select buscar la edad de un cliente introduciendo su dni
		String query = "select edad from " + electricskate + ".cliente" + " WHERE dni = '"
				+ dni + "'";
		
			try {
				
				//Guardamos dentro del objeto consulta de la clase Statement la conexi�n a la base de datos
				consulta = connection.createStatement();
				//guardamos todos los registros de la tabla que vamos a consultar
				ResultSet registro = consulta.executeQuery(query);
				
				if (registro.next()) {
					
					 edad = registro.getInt("edad");
					
				}
							
			} catch (SQLException e) {
				Excepciones.printSQLException(e);
				
			} finally {
				// Cerramos el Statement
				consulta.close();
			}
		
		return edad;
		
	}
	
	
	public static String colorPatinete (Connection connection, String electricskate, int numSerie) throws SQLException {
		
		String color = " ";
		
		//Abrimos el Statement
		Statement consulta = null;
		//Creamos la sentencia select buscar el color de un patinete introduciendo su n�mero de serie
		String query = "select color from " + electricskate + ".patinete" + " WHERE numSerie = "
				+ numSerie;
		
			try {
				
				//Guardamos dentro del objeto consulta de la clase Statement la conexi�n a la base de datos
				consulta = connection.createStatement();
				//guardamos todos los registros de la tabla que vamos a consultar
				ResultSet registro = consulta.executeQuery(query);
				
				if (registro.next()) {
					
					 color = registro.getString("color");
					
				}
							
			} catch (SQLException e) {
				Excepciones.printSQLException(e);
				
			} finally {
				// Cerramos el Statement
				consulta.close();
			}
		
		return color;
		
	}

	
	public static String contrase�aAdmin (Connection connection, String electricskate, String nombreUsuario) throws SQLException {
		
		String contrase�a = " ";
		
		//Abrimos el Statement
		Statement consulta = null;
		//Creamos la sentencia select buscar la contrase�a de un administrador introduciendo su u
		String query = "select contrase�a from " + electricskate + ".administrador" + " WHERE nombreUsuario = '"
				+ nombreUsuario + "'";
		
			try {
				
				//Guardamos dentro del objeto consulta de la clase Statement la conexi�n a la base de datos
				consulta = connection.createStatement();
				//guardamos todos los registros de la tabla que vamos a consultar
				ResultSet registro = consulta.executeQuery(query);
				
				if (registro.next()) {
					
					 contrase�a = registro.getString("contrase�a");
					
				}
							
			} catch (SQLException e) {
				Excepciones.printSQLException(e);
				
			} finally {
				// Cerramos el Statement
				consulta.close();
			}
		
		return contrase�a;
			
	}
	
}
