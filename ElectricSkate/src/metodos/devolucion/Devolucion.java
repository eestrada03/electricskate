package metodos.devolucion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

import metodos.excepciones.Excepciones;

public class Devolucion {
	
	static Scanner teclado = new Scanner(System.in);
	
	// Método para realizar devoluciones de patinetes
	public static void realizarDevolucion(Connection connection, String electricskate) throws SQLException, InterruptedException {

		boolean exit = false;

		do {
			
			System.out.println("");
			System.out.println("===============================");
			System.out.println("======REALIZAR DEVOLUCIÓN======");
			System.out.println("===============================");
			System.out.println("");
			System.out.print("introduzca el Id del alquiler con el patinete a devolver: ");
			String idAlquiler = teclado.nextLine();
			System.out.println("");
			
			Statement stmt = null;
			String comprobarAlquiler = "SELECT numSerie, dni " + " from " + electricskate + ".alquiler" + " WHERE idAlquiler = '"
					+ idAlquiler + "'";

			try {

				stmt = connection.createStatement();
				ResultSet registro = stmt.executeQuery(comprobarAlquiler);

				if (registro.next()) {
					
					double kmRecorridoCliente;
					
					ResultSet rs3 = stmt.executeQuery("SELECT kmRecorridoCliente FROM alquiler WHERE idAlquiler = '" + idAlquiler + "'");
					rs3.next();
					kmRecorridoCliente = rs3.getDouble("kmRecorridoCliente");
					
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

					exit = true;
						
					} else {
						
						System.out.println("Patinete ya devuelto");
						
						exit = true;
					} 

				} else {
					System.out.println("Id del alquiler incorrecto.");
				}

			} catch (SQLException e) {
				Excepciones.printSQLException(e);
				
			/*} catch (InputMismatchException e){
				System.out.println("\n¡Error!, introduce un número.");
				Thread.sleep(3000);
				teclado.nextLine();
				realizarDevolucion(connection, electricskate);
			*/	
			} finally {
				stmt.close();
			}

		} while (!exit);
	}

	
}


