package metodos.listado;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import metodos.excepciones.Excepciones;
import metodos.menus.Menus;

public class Listado {
	
	static Scanner teclado = new Scanner(System.in);
	
	// Metodo para mostrar el listado de los clientes registrados
	public static void clientes(Connection connection, String BDNombre) throws SQLException, InterruptedException {

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
			Excepciones.printSQLException(e);
		} finally {
			consulta.close();
			Thread.sleep(4000);
			Menus.volverAlMenuPrincipal(connection, BDNombre);
		}

	}

	public static void patinetesAlquilados(Connection con, String BDNombre) throws SQLException, InterruptedException {

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
			Excepciones.printSQLException(e);
		} finally {
			stmt.close();
			Thread.sleep(4000);
			Menus.volverAlMenuPrincipal(con, BDNombre);
		}

	}

	public static void patinetesNoAlquilados(Connection con, String BDNombre) throws SQLException, InterruptedException {

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
			Excepciones.printSQLException(e);
		} finally {
			stmt.close();
			Thread.sleep(4000);
			Menus.volverAlMenuPrincipal(con, BDNombre);
		}

	}

	public static void completoPatinetes(Connection con, String BDNombre) throws SQLException, InterruptedException {

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
			Excepciones.printSQLException(e);
		} finally {
			stmt.close();
			Thread.sleep(4000);
			Menus.volverAlMenuPrincipal(con, BDNombre);

	}

	
	}
}
