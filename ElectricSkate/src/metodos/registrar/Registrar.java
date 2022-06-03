package metodos.registrar;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

import metodos.excepciones.Excepciones;

public class Registrar {
	
	static Scanner teclado = new Scanner(System.in);
	
	public static void nuevoCliente(Connection connection, String electricskate) throws SQLException, InterruptedException {
		
		
		try {
	
			System.out.println("");
			System.out.println("===============================");
			System.out.println("====REGISTRAR NUEVO CLIENTE====");
			System.out.println("===============================");
			System.out.println("");
			System.out.println("Introduzca los valores correspondientes");
			System.out.println("");
			
			System.out.print("Nombre: ");
			String nombre = teclado.nextLine();
			System.out.println("");
			
			System.out.print("Apellidos: ");
			String apellidos = teclado.nextLine();
			System.out.println("");
			
			System.out.print("Edad: ");
			int edad = teclado.nextInt();
			System.out.println("");
			
			System.out.print("DNI: ");
			teclado.nextLine();
			String dni = teclado.nextLine();
			System.out.println("");	
			
			
			System.out.print("introduzca el email: ");
			String email = teclado.nextLine();
			System.out.println("");		
			
			Statement stmt = null;

		
			stmt = connection.createStatement();
			stmt.executeUpdate("insert into " + electricskate + ".cliente VALUES('" + nombre + "','" + apellidos + "',"
					+ edad + ",'" + dni + "','" + email + "', 0)");

			System.out.println("Usuario añadido correctamente.");

		} catch (SQLException e) {
			Excepciones.printSQLException(e);
			
		} catch (InputMismatchException e){
				System.out.println("\n¡Error!, introduce un número sin decimales.");
				Thread.sleep(3000);
				teclado.nextLine();
				nuevoCliente(connection, electricskate);	
			
		} finally {

			connection.close();
		}
	}

	public static void nuevoAdministradore(Connection connection, String electricskate) throws SQLException, InterruptedException {

		try {
			
			System.out.println("");
			System.out.println("===============================");
			System.out.println("=REGISTRAR NUEVO ADMINISTRADOR=");
			System.out.println("===============================");
			System.out.println("");
			
			System.out.println("Introduzca los valores correspondientes");
			System.out.println("");
			
			System.out.print("Nombre: ");
			String nombre = teclado.nextLine();
			System.out.println("");
			
			System.out.print("Apellidos: ");
			String apellidos = teclado.nextLine();
			System.out.println("");
			
			System.out.print("Edad: ");
			int edad = teclado.nextInt();
			System.out.println("");
			
			System.out.print("DNI: ");
			teclado.nextLine();
			String dni = teclado.nextLine();
			System.out.println("");			
					
			System.out.print("introduzca el email: ");
			String email = teclado.nextLine();
			System.out.println("");	
					
			System.out.print("Nombre de usuario: ");
			String nombreUsuario = teclado.nextLine();
			System.out.println("");
				
			System.out.print("Contraseña: ");
			String contraseña = teclado.nextLine();
			System.out.println("");
						
			Statement stmt1 = null;
			
			stmt1 = connection.createStatement();

			stmt1.executeUpdate("insert into " + electricskate + ".administrador VALUES('" + nombre + "','" + apellidos
					+ "'," + edad + ",'" + dni + "','" + email + "','" + nombreUsuario + "','" + contraseña + "')");

			System.out.println("Usuario administrador añadido correctamente.");

		} catch (SQLException e) {
			Excepciones.printSQLException(e);
			
		} catch (InputMismatchException e){
			System.out.println("\n¡Error!, introduce un número sin decimales.");
			Thread.sleep(3000);
			teclado.nextLine();
			nuevoAdministradore(connection, electricskate);
			
		}finally {

			connection.close();
		}

	}

	// Método para añadir patinete.
	public static void nuevopatinete(Connection connection, String electricskate) throws SQLException, InterruptedException {

		
		try {
			
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

			stmt = connection.createStatement();

			stmt.executeUpdate("insert into " + electricskate + ".patinete VALUES(" + numSerie + ",'" + marca + "','" + color
					+ "','" + modelo + "'," + 0 + ", " + 1 + ")");
			
			System.out.println("Se ha añadido el patinete nº " + numSerie + " correctamente.");

		} catch (SQLException e) {
			Excepciones.printSQLException(e);
			
		} catch (InputMismatchException e){
			System.out.println("\n¡Error!, introduce un número sin decimales.");
			Thread.sleep(3000);
			teclado.nextLine();
			nuevopatinete(connection, electricskate);
			
		}finally {

			connection.close();
		}

	}


	
}
