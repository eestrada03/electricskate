package metodos.devolucion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

import metodos.excepciones.Excepciones;
import metodos.menus.Menus;

public class Devolucion {
	
	//Creamos un nuevo objeto de tipo Scanner para pedir datos por consola
	static Scanner teclado = new Scanner(System.in);
	
	// M�todo para realizar devoluciones de patinetes
	public static void realizarDevolucion(Connection connection, String electricskate) throws SQLException, InterruptedException {
		
		// Variable boolean para el bucle "do while"
		boolean exit = false;
		
		do {
			
			System.out.println("");
			System.out.println("===============================");
			System.out.println("======REALIZAR DEVOLUCI�N======");
			System.out.println("===============================");
			System.out.println("");
			System.out.print("introduzca el Id del alquiler con el patinete a devolver: ");
			//Pedimos informaci�n por consola
			String idAlquiler = teclado.nextLine();
			System.out.println("");
			
			//Abrimos el Statement
			Statement stmt = null;
			
			//Realizamos una consulta y la guardamos en un String
			String comprobarAlquiler = "SELECT numSerie, dni " + " from " + electricskate + ".alquiler" + " WHERE idAlquiler = '"
					+ idAlquiler + "'";

			try {
				//Creamos un Statement
				stmt = connection.createStatement();
				//Ejecutamos la consulta y la guardamos en un objeto ResultSet
				ResultSet registro = stmt.executeQuery(comprobarAlquiler);
				
				// Con este if comprobamos si la informaci�n que ha introducido el usuario por consola existe en la base de datos
				if (registro.next()) { 
					//Si existe nos dejar� continuar, si no nos la volver� a solicitar
					double kmRecorridoCliente;
					
					// Recuperamos el kmRecorridoCliente con el idAlquiler que haya introducido el usuario
					ResultSet rs3 = stmt.executeQuery("SELECT kmRecorridoCliente FROM alquiler WHERE idAlquiler = '" + idAlquiler + "'");
					rs3.next();
					
					kmRecorridoCliente = rs3.getDouble("kmRecorridoCliente");
					
					//Si el kmRecorridoCliente es igual a 0 quiere decir que el alquiler sigue activo y nos permite continuar
					if (kmRecorridoCliente == 0) {
						
					System.out.print("introduzca los kilometros recorridos por el cliente: ");
					kmRecorridoCliente = teclado.nextDouble();
					System.out.println("");

					// Actualizamos la tabla cliente y lo ponemos en alquiler NO activo
					ResultSet rs4 = stmt.executeQuery("SELECT dni FROM alquiler WHERE idAlquiler = '" + idAlquiler + "'");
					rs4.next();
					String dni = rs4.getString("dni");
					
					stmt.executeUpdate("UPDATE " + electricskate + ".cliente SET alquilerActivo = 0 WHERE dni = '" + dni + "'");
					
					 
					// Actualizamos los km recorridos por el cliente
					stmt.executeUpdate("UPDATE " + electricskate + ".alquiler SET kmRecorridoCliente = '"
							+ kmRecorridoCliente + "'" + " WHERE idAlquiler = '" + idAlquiler + "'");

					// Actualizamos la tabla patinete y lo ponemos en disponible
					ResultSet rs = stmt.executeQuery("SELECT numSerie FROM alquiler WHERE idAlquiler = '" + idAlquiler + "'");
					rs.next();
					int numSerie = rs.getInt("numSerie");
					
					stmt.executeUpdate("UPDATE " + electricskate + ".patinete SET disponible = 1 WHERE numSerie =" + numSerie);
					
				
					// Actualizamos los km recorridos por el patinete en la tabla patinete
					ResultSet rs2 = stmt.executeQuery("SELECT kmRecorridoPatinete FROM patinete WHERE numSerie =" + numSerie);
					rs2.next();
					double kmRecorridoPatinete = rs2.getInt("kmRecorridoPatinete");

					stmt.executeUpdate("UPDATE " + electricskate + ".patinete SET kmRecorridoPatinete = '"
							+ (kmRecorridoPatinete + kmRecorridoCliente) + "'" + " WHERE numSerie =" + numSerie);				
					
					
					System.out.println("Se ha devuelto el patinete correctamente.");
					
					//Una vez devuelto el patinete el cliente tiene la opci�n de realizar otra devoluci�n
					Scanner tecla = new Scanner(System.in);
					System.out.println("�Desea realizar otra devoluci�n?: [S/N]");
					System.out.print("--> ");
					String sn = "";
					sn = tecla.nextLine();
					sn = sn.toLowerCase();
					if (sn.equals("s")) {
						realizarDevolucion(connection, electricskate);
					}else {
						//o puede volver al men� principal
						System.out.println("Saliendo...");
						Thread.sleep(2500);
						Menus.menuPrincipal(connection, electricskate);
					}

					exit = true;
						
					} else {
						//Si el patinete ya ha sido devuelto, nos devuelve al men� principal
						System.out.println("Patinete ya devuelto");
						System.out.println("Volviendo al men� principal...");
						Menus.volverAlMenuPrincipal(connection, comprobarAlquiler);
						
					} 

				} else {
					//Si el Id introducido es incorrecto aparecer� este mensaje y volver� a pedirnos que ingresemos un Id
					System.out.println("Id del alquiler incorrecto.");
				}
				
			// Control de excepciones	
			} catch (SQLException e) {
				Excepciones.printSQLException(e);
				
			/*} catch (InputMismatchException e){
				System.out.println("\n�Error!, introduce un n�mero.");
				Thread.sleep(3000);
				teclado.nextLine();
				realizarDevolucion(connection, electricskate);
			*/	
			} finally {
				// Cerramos el Statement
				stmt.close();
			}
			//Si se cumple la restricci�n, "exit=true", el bucle termina
		} while (!exit);
	}

	
}


