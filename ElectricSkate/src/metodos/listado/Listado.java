package metodos.listado;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import metodos.excepciones.Excepciones;
import metodos.menus.Menus;

public class Listado {
	
	//Creamos un nuevo objeto de tipo Scanner para pedir datos por consola
	static Scanner teclado = new Scanner(System.in);
	
	// Metodo para mostrar el listado de los clientes registrados
	public static void clientes(Connection connection, String BDNombre) throws SQLException, InterruptedException {
		
		//Abrimos el Statement
		Statement consulta = null;
		//creamos la sentencia select para los datos del cliente, almacenandolos en una variable de tippo String
		String query = "select nombre, apellidos, edad, dni, email " + " from " + BDNombre + ".cliente";

		try {
			
			//guardamos dentro del objeto consulta de la clase Statement la conexión a la base de datos
			consulta = connection.createStatement();
			//guardamos todos los registros de la tabla que vamos a consultar
			ResultSet registro = consulta.executeQuery(query);

			System.out.println("");
			System.out.println("===============================");
			System.out.println("========LISTADO CLIENTES=======");
			System.out.println("===============================");
			
			//comprobamos si existen los valores de dicha tabla
			while (registro.next()) {

				System.out.println("");
				System.out.println("*************************************");
				
				//almacenamos los valores obtenidos dendro de una variable
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
			// Control de excepciones	
		} catch (SQLException e) {
			Excepciones.printSQLException(e);
		} finally {
			// Cerramos el Statement
			consulta.close();
			Thread.sleep(3000);
			Menus.volverAlMenuPrincipal(connection, BDNombre);
		}

	}
	
	//Metodos para los patinetes alquilados
	public static void patinetesAlquilados(Connection con, String BDNombre) throws SQLException, InterruptedException {
		
		//Abrimos el Statement
		Statement stmt = null;

		System.out.println("");
		System.out.println("================================");
		System.out.println("==LISTADO PATINETES ALQUILADOS==");
		System.out.println("================================");
		
		//creamos la sentencia select para los datos del cliente, almacenandolos en una variable de tippo String
		String query = "select numSerie, marca, color, modelo from " + BDNombre + ".patinete where disponible = '0' ";

		try {
			
			//guardamos dentro del objeto stmt de la clase Statement la conexión a la base de datos
			stmt = con.createStatement();
			//guardamos todos los registros de la tabla que vamos a consultar
			ResultSet rs = stmt.executeQuery(query);
			
			//comprobamos si existen los valores de dicha tabla
			while (rs.next()) {

				System.out.println("");
				System.out.println("---------------------------------------------");
				//almacenamos los valores obtenidos dendro de una variable
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
			
			// Control de excepciones	
		} catch (SQLException e) {
			Excepciones.printSQLException(e);
		} finally {
			// Cerramos el Statement
			stmt.close();
			Thread.sleep(3000);
			Menus.volverAlMenuPrincipal(con, BDNombre);
		}

	}
	
	//Metodo para los patietes no alquilados
	public static void patinetesNoAlquilados(Connection con, String BDNombre) throws SQLException, InterruptedException {
		
		//Abrimos el Statement
		Statement stmt = null;

		System.out.println("");
		System.out.println("===================================");
		System.out.println("==LISTADO PATINETES NO ALQUILADOS==");
		System.out.println("===================================");
		
		//creamos la sentencia select para los datos del cliente, almacenandolos en una variable de tippo String
		String query = "select numSerie, marca, color, modelo from " + BDNombre + ".patinete where disponible = '1' ";

		try {
			
			//guardamos dentro del objeto stmt de la clase Statement la conexión a la base de datos
			stmt = con.createStatement();
			//guardamos todos los registros de la tabla que vamos a consultar
			ResultSet rs = stmt.executeQuery(query);
			
			//comprobamos si existen los valores de dicha tabla
			while (rs.next()) {

				System.out.println("");
				System.out.println("---------------------------------------------");
				
				//almacenamos los valores obtenidos dendro de una variable
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
		
		// Control de excepciones	
		} catch (SQLException e) {
			Excepciones.printSQLException(e);
		} finally {
			// Cerramos el Statement
			stmt.close();
			Thread.sleep(3000);
			Menus.volverAlMenuPrincipal(con, BDNombre);
		}

	}
	
	//Metodo para el listado de patinetes 
	public static void completoPatinetes(Connection con, String BDNombre) throws SQLException, InterruptedException {
		
		//Abrimos el Statement
		Statement stmt = null;

		System.out.println("");
		System.out.println("================================");
		System.out.println("===LISTADO COMPLETO PATINETES===");
		System.out.println("================================");
		
		//creamos la sentencia select para los datos del cliente, almacenandolos en una variable de tippo String
		String query = "select numSerie, marca, color, modelo from " + BDNombre + ".patinete";

		try {
			
			//guardamos dentro del objeto stmt de la clase Statement la conexión a la base de datos
			stmt = con.createStatement();
			//guardamos todos los registros de la tabla que vamos a consultar
			ResultSet rs = stmt.executeQuery(query);
			
			//comprobamos si existen los valores de dicha tabla
			while (rs.next()) {

				System.out.println("");
				System.out.println("---------------------------------------------");
				
				//almacenamos los valores obtenidos dendro de una variable
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
		
		// Control de excepciones	
		} catch (SQLException e) {
			Excepciones.printSQLException(e);
		} finally {
			// Cerramos el Statement
			stmt.close();
			Thread.sleep(3000);
			Menus.volverAlMenuPrincipal(con, BDNombre);

	}

	
	}
}
