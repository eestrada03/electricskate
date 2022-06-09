package metodos.registrar;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

import metodos.buscar.Buscar;
import metodos.excepciones.Excepciones;
import metodos.menus.Menus;

/**
 * Esta clase sirve para ilustrar el uso de los registros
 * @author Reginaldo, Alberto, Kike, Jose Y Luz
 * @version 1.0
 */

public class Registrar {
	
	static Scanner teclado = new Scanner(System.in);
	
	/**
	 * metodo para poder registrar un nuevo cliente
	 * @param connection la variable que establece la conexion con la base de datos
	 * @param electricskate es el nombre que le hemos dado a nuestra base de datos
	 * @throws SQLException este nos sirve para lanzar una excepcion
	 * @throws InterruptedException es una parte de la firma del m�todo 
	 * y un posible resultado de llamar al m�todo que est� llamando
	 */
	
	//M�todo para registrar un nuevo cliente
	public static void nuevoCliente(Connection connection, String electricskate) throws SQLException, InterruptedException {
		
		
		try {
	
			System.out.println("");
			System.out.println("===============================");
			System.out.println("====REGISTRAR NUEVO CLIENTE====");
			System.out.println("===============================");
			System.out.println("");
			System.out.println("Introduzca los valores correspondientes");
			System.out.println("");
			
			Statement stmt = null;

			//Creamos el Statement
			stmt = connection.createStatement();
			
			//Recogemos datos mediante Scanner.
			System.out.print("DNI: ");
			String dni = teclado.nextLine();
			System.out.println("");	
			
			String comprobarDni = "SELECT dni " + " from " + electricskate + ".cliente" + " WHERE dni = '"
					+ dni + "'";	
			
			ResultSet registro = stmt.executeQuery(comprobarDni);
			//Comprobamos si el DNI ya existe en la base de datos
			if (!registro.next()) {
			
			
			System.out.print("Nombre: ");
			String nombre = teclado.nextLine();
			System.out.println("");
			
			System.out.print("Apellidos: ");
			String apellidos = teclado.nextLine();
			System.out.println("");
			
			System.out.print("Edad: ");
			int edad = teclado.nextInt();
			teclado.nextLine();
			System.out.println("");
						
			System.out.print("introduzca el email: ");
			String email = teclado.nextLine();
			System.out.println("");
					

			//Ejecutamos ek Statement para insertar el usuario utilizando los par�metros recogidos anteriormente.
			stmt.executeUpdate("insert into " + electricskate + ".cliente VALUES('" + nombre + "','" + apellidos + "',"
					+ edad + ",'" + dni + "','" + email + "', 0)");

			System.out.println("Usuario a�adido correctamente.");
			System.out.println(" ");
			
			System.out.println("�Desea a�adir otro Usuario?: [S/N]");
			System.out.print("--> ");
			String sn = "";
			sn = teclado.nextLine();
			sn = sn.toLowerCase();
			
			if (sn.equals("s")) {
				nuevoCliente(connection, electricskate);
			}else {
				System.out.println("Saliendo...");
				Thread.sleep(2500);
				Menus.menuPrincipal(connection, electricskate);
			}
					
			} else {
				System.out.println("�Error!, el DNI ya existe.");
				Thread.sleep(2500);
				System.out.println("");
				System.out.println("�Desea introducidor otro DNI?: [S/N]");
				System.out.print("--> ");
				String sn = "";
				sn = teclado.nextLine();
				sn = sn.toLowerCase();
				if (sn.equals("s")) {
					nuevoCliente(connection, electricskate);
				}else {
					System.out.println("Saliendo...");
					Thread.sleep(2500);
					Menus.volverAlMenuPrincipal(connection, electricskate);
				}
			
			}
			
		} catch (SQLException e) {
			Excepciones.printSQLException(e);
			
		} catch (InputMismatchException e){
				System.out.println("\n�Error!, introduce un n�mero sin decimales.");
				Thread.sleep(3000);
				teclado.nextLine();
				nuevoCliente(connection, electricskate);	
			
		} finally {

			connection.close();
		}
	}
	
	/**
	 * metodo para registrar un nuevo administrador
	 * @param connection la variable que establece la conexion con la base de datos
	 * @param electricskate es el nombre que le hemos dado a nuestra base de datos
	 * @throws SQLException este nos sirve para lanzar una excepcion
	 * @throws InterruptedException es una parte de la firma del m�todo 
	 * y un posible resultado de llamar al m�todo que est� llamando
	 */
	
	//M�todo para registrar nuevos administradores.	
	public static void nuevoAdministradore(Connection connection, String electricskate) throws SQLException, InterruptedException {

		try {
			
			System.out.println("");
			System.out.println("===============================");
			System.out.println("=REGISTRAR NUEVO ADMINISTRADOR=");
			System.out.println("===============================");
			System.out.println("");
			
			System.out.println("Introduzca los valores correspondientes");
			System.out.println("");
			
			Statement stmt1 = null;
			
			stmt1 = connection.createStatement();	
			
			//Recogemos datos mediante Scanner.
			System.out.print("DNI: ");
			String dni = teclado.nextLine();
			System.out.println("");	
			
			String comprobarDni = "SELECT dni " + " from " + electricskate + ".administrador" + " WHERE dni = '"
					+ dni + "'";	
			
			ResultSet registro = stmt1.executeQuery(comprobarDni);
			//Comprobamos si el DNI ya existe en la base de datos
			if (!registro.next()) {
			
			System.out.print("Nombre: ");
			String nombre = teclado.nextLine();
			System.out.println("");
			
			System.out.print("Apellidos: ");
			String apellidos = teclado.nextLine();
			System.out.println("");
			
			System.out.print("Edad: ");
			int edad = teclado.nextInt();
			teclado.nextLine();
			System.out.println("");		
					
			System.out.print("introduzca el email: ");
			String email = teclado.nextLine();
			System.out.println("");	
					
			System.out.print("Nombre de usuario: ");
			String nombreUsuario = teclado.nextLine();
			System.out.println("");
				
			System.out.print("Contrase�a: ");
			String contrase�a = teclado.nextLine();
			System.out.println("");
						

			//Ejecutamos ek Statement para insertar el administrador utilizando los par�metros recogidos anteriormente.
			stmt1.executeUpdate("insert into " + electricskate + ".administrador VALUES('" + nombre + "','" + apellidos
					+ "'," + edad + ",'" + dni + "','" + email + "','" + nombreUsuario + "','" + contrase�a + "')");

			System.out.println("Usuario administrador a�adido correctamente.");
			
			System.out.println("�Desea a�adir otro Administrador?: [S/N]");
			System.out.print("--> ");
			String sn = "";
			sn = teclado.nextLine();
			sn = sn.toLowerCase();
			if (sn.equals("s")) {
				nuevoAdministradore(connection, electricskate);
			}else {
				System.out.println("Saliendo...");
				Thread.sleep(2500);
				Menus.menuPrincipal(connection, electricskate);
			}
			
			} else {
				System.out.println("�Error!, el DNI ya existe.");
				Thread.sleep(2500);
				System.out.println("");
				System.out.println("�Desea introducidor otro DNI?: [S/N]");
				System.out.print("--> ");
				String sn = "";
				sn = teclado.nextLine();
				sn = sn.toLowerCase();
				if (sn.equals("s")) {
					nuevoAdministradore(connection, electricskate);
				}else {
					System.out.println("Saliendo...");
					Thread.sleep(2500);
					Menus.volverAlMenuPrincipal(connection, electricskate);
				}
			
			}

		} catch (SQLException e) {
			Excepciones.printSQLException(e);
			
		} catch (InputMismatchException e){
			System.out.println("\n�Error!, introduce un n�mero sin decimales.");
			Thread.sleep(3000);
			teclado.nextLine();
			nuevoAdministradore(connection, electricskate);
			
		}finally {

			connection.close();
		}

	}
	
	/**
	 * metodo para registrar un nuevo patinete
	 * @param connection la variable que establece la conexion con la base de datos
	 * @param electricskate es el nombre que le hemos dado a nuestra base de datos
	 * @throws SQLException este nos sirve para lanzar una excepcion
	 * @throws InterruptedException es una parte de la firma del m�todo 
	 * y un posible resultado de llamar al m�todo que est� llamando
	 */
	
	// M�todo para a�adir patinete.
	public static void nuevopatinete(Connection connection, String electricskate) throws SQLException, InterruptedException {

		
		try {

			System.out.println("");
			System.out.println("===============================");
			System.out.println("======REGISTRAR PATINETE=======");
			System.out.println("===============================");
			System.out.println("");
	
			System.out.println("Introduzca los valores correspondientes");
			System.out.println("");
			
			Statement stmt = null;
			
			stmt = connection.createStatement();
		
			
			System.out.print("N� Serie: ");
			int numSerie = teclado.nextInt();
			teclado.nextLine();
			System.out.println(" ");
			
			String comprobarNumSerie = "SELECT numSerie " + " from " + electricskate + ".patinete" + " WHERE numSerie = '"
					+ numSerie + "'";	
			
			ResultSet registro = stmt.executeQuery(comprobarNumSerie);
			//Comprobamos si el n�mero de serie ya existe en la base de datos
			if (!registro.next()) {				

			System.out.print("Marca: ");
			String marca = teclado.nextLine();	
			System.out.println(" ");
	
			System.out.print("Color: ");
			String color = teclado.nextLine();
			System.out.println(" ");
	
			System.out.print("Modelo: ");
			String modelo = teclado.nextLine();
			System.out.println(" ");

			stmt.executeUpdate("insert into " + electricskate + ".patinete VALUES(" + numSerie + ",'" + marca + "','" + color
					+ "','" + modelo + "'," + 0 + ", " + 1 + ")");
			
			System.out.println("Se ha a�adido el patinete n� " + numSerie + " correctamente.");
			System.out.println(" ");

			System.out.println("�Desea a�adir otro Patinete?: [S/N]");
			System.out.print("--> ");
			String sn = "";
			sn = teclado.nextLine();
			sn = sn.toLowerCase();
			if (sn.equals("s")) {
				nuevopatinete(connection, electricskate);
			}else {
				System.out.println("Saliendo...");
				Thread.sleep(2500);
				Menus.menuPrincipal(connection, electricskate);
			}
			
			
			} else {
				System.out.println("�Error!, el n�mero de serie ya existe.");
				Thread.sleep(2500);
				System.out.println("");
				System.out.println("�Desea introducidor otro n� de serie?: [S/N]");
				System.out.print("--> ");
				String sn = "";
				sn = teclado.nextLine();
				sn = sn.toLowerCase();
				if (sn.equals("s")) {
					nuevopatinete(connection, electricskate);
				}else {
					System.out.println("Saliendo...");
					Thread.sleep(2500);
					Menus.volverAlMenuPrincipal(connection, electricskate);
				}
			
			}
		} catch (SQLException e) {
			Excepciones.printSQLException(e);
			
		} catch (InputMismatchException e){
			System.out.println("\n�Error!, introduce un n�mero sin decimales.");
			Thread.sleep(3000);
			teclado.nextLine();
			nuevopatinete(connection, electricskate);
			
		}finally {

			connection.close();
		}

	}


	
}
