package metodos.registrar;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import metodos.excepciones.Excepciones;

public class Registrar {
	
	static Scanner teclado = new Scanner(System.in);
	
	public static void nuevoCliente(Connection connection, String electricskate) throws SQLException {

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
					+ edad + ",'" + dni + "','" + email + "', 0)");
			System.out.println("");
			System.out.println("Usuario añadido correctamente");

		} catch (SQLException e) {
			Excepciones.printSQLException(e);
		} finally {

			connection.close();
		}
	}

	public static void nuevoAdministradore(Connection connection, String electricskate) throws SQLException {

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
			Excepciones.printSQLException(e);
		} finally {

			connection.close();
		}

	}

	// Método para añadir patinete.
	public static void nuevopatinete(Connection connection, String BDnom) throws SQLException {

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
			Excepciones.printSQLException(e);
		} finally {

			connection.close();
		}

	}


	
}
