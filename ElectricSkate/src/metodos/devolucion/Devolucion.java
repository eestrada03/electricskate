package metodos.devolucion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import metodos.excepciones.Excepciones;

public class Devolucion {
	
	static Scanner teclado = new Scanner(System.in);
	
	// Método para realizar devoluciones de patinetes
	public static void realizarDevolucion(Connection connection, String BDNombre) throws SQLException {

		boolean exit = false;

		do {
			
			System.out.println("");
			System.out.println("===============================");
			System.out.println("======REALIZAR DEVOLUCIÓN======");
			System.out.println("===============================");
			System.out.println("");
			System.out.print("introduzca el Id del Alquiler con el patinete a devolver: ");
			System.out.println("");
			String idAlquiler = teclado.nextLine();

			Statement stmt = null;
			String comprobarAlquiler = "SELECT numSerie, dni " + " from " + BDNombre + ".alquiler" + " WHERE idAlquiler = '"
					+ idAlquiler + "'";

			try {

				stmt = connection.createStatement();
				ResultSet registro = stmt.executeQuery(comprobarAlquiler);

				if (registro.next()) {

					System.out.println("");
					System.out.print("introduzca los kilometros recorridos por el cliente: ");
					System.out.println("");

					double kmRecorridoCliente = teclado.nextDouble();

					// Actualizamos los km recorridos por el cliente
					stmt.executeUpdate("UPDATE " + BDNombre + ".alquiler SET kmRecorridoCliente = '"
							+ kmRecorridoCliente + "'" + " WHERE idAlquiler = '" + idAlquiler + "'");

					// Actualizamos la tabla patinete y lo ponemos en disponible
					ResultSet rs = stmt
							.executeQuery("SELECT numSerie FROM alquiler WHERE idAlquiler = '" + idAlquiler + "'");
					rs.next();
					int numSerie = rs.getInt("numSerie");

					ResultSet alqact = stmt.executeQuery(
							"select AlquilerActivo from cliente where dni = (select dni from alquiler natural join cliente where idAlquiler = "
									+ idAlquiler + ")");
					int AlquilerActivo = 0;
					while (alqact.next()) {

						AlquilerActivo = alqact.getInt("AlquilerActivo");

					}

					stmt.executeUpdate(
							"UPDATE " + BDNombre + ".patinete SET disponible = 1 WHERE numSerie =" + numSerie);

					// Actualizamos los km recorridos por el patinete en la tabla patinete
					ResultSet rs2 = stmt
							.executeQuery("SELECT kmRecorridoPatinete FROM patinete WHERE numSerie =" + numSerie);
					rs2.next();
					double kmRecorridoPatinete = rs2.getInt("kmRecorridoPatinete");

					stmt.executeUpdate("UPDATE " + BDNombre + ".patinete SET kmRecorridoPatinete = '"
							+ (kmRecorridoPatinete + kmRecorridoCliente) + "'" + " WHERE numSerie =" + numSerie);

					if (AlquilerActivo == 1) {

						stmt.executeUpdate(
								"update cliente set AlquilerActivo = 0 where dni = (select dni from alquiler natural join cliente where idAlquiler = "
										+ idAlquiler + ")");

					} else {

						System.out.println("El cliente ya posee un patinete, alquiler rechazado.");
					}
					System.out.println("");
					System.out.println("Se ha devuelto el patinete correctamente.");

					exit = true;

				} else {
					System.out.println("DNI incorrecto.");
				}

			} catch (SQLException e) {
				Excepciones.printSQLException(e);
			} finally {
				stmt.close();
			}

		} while (!exit);
	}

}
