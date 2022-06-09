package metodos.alquiler;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import metodos.excepciones.Excepciones;
import metodos.menus.Menus;

/**
 * Esta clase sirve para ilustrar el uso del alquiler
 * @author Reginaldo, Alberto, Kike, Jose Y Luz
 * @version 1.0
 */

public class Alquiler {

	static Scanner teclado = new Scanner(System.in);
	
	/**
	 * Método que nos permite realizar el alquiler de un determinado patinete
	 * @param connection la variable que establece la conexión con la base de datos
	 * @param BDNombre nombre de la base de datos por defecto
	 * @throws SQLException este nos sirve para lanzar una excepción
	 * @throws InterruptedException es una parte de la firma del método 
	 * y un posible resultado de llamar al método que está llamando
	 */
	public static void realizarAlquiler(Connection connection, String BDNombre)
			throws SQLException, InterruptedException {

		// Variable boolean para el bucle "do while"
		boolean exit = false;

		do {

			System.out.println("");
			System.out.println("===============================");
			System.out.println("======REALIZAR ALQUILER========");
			System.out.println("===============================");
			System.out.println("");

			System.out.println("Introduzca los valores correspondientes:");
			System.out.println("");
			// Pedimos información por consola
			System.out.print("DNI: ");
			String dni = teclado.nextLine();

			// Abrimos el Statement
			Statement stmt = null;

			// Realizamos una consulta y la guardamos en un String
			String comprobarDni = "SELECT nombre, apellidos " + " from " + BDNombre + ".cliente" + " WHERE dni = '"
					+ dni + "'";

			try {
				// Creamos un Statement
				stmt = connection.createStatement();
				// Ejecutamos la consulta y la guardamos en un objeto ResultSet
				ResultSet rs1 = stmt.executeQuery(comprobarDni);

				// Con este if comprobamos si la información que ha introducido el usuario por
				// consola existe en la base de datos
				if (rs1.next()) {
					// Si existe nos dejará continuar, si no, nos la volverá a solicitar

					// Recuperamos el alquilerActivo con el dni que haya introducido el usuario
					ResultSet rs2 = stmt.executeQuery("SELECT alquilerActivo FROM cliente WHERE dni = '" + dni + "'");
					rs2.next();
					int alquilerActivo = rs2.getInt("alquilerActivo");

					// Si el alquilerActivo es igual a 0 quiere decir que el cliente no tiene ningún
					// alquiler activo y nos permite continuar
					if (alquilerActivo == 0) {

						do {

							System.out.println(" ");

							System.out.print("Nº de serie: ");
							// Pedimos información por consola
							String numSerie = teclado.nextLine();
							System.out.println(" ");
							// Realizamos una consulta y la guardamos en un String
							String comprobarNumSerie = "SELECT marca, modelo " + " from " + BDNombre + ".patinete"
									+ " WHERE numSerie =  '" + numSerie + "'";

							try {
								// En este caso comprobará si el numSerie introducido por consola existe en la
								// base de datos
								ResultSet rs3 = stmt.executeQuery(comprobarNumSerie);

								// Si existe nos dejará continuar, si no, nos lo volverá a solicitar
								if (rs3.next()) {

									// Realizamos otra consulta para comprobar si el patinete este disponible
									ResultSet rs4 = stmt.executeQuery(
											"SELECT disponible FROM patinete WHERE numSerie = '" + numSerie + "'");
									rs4.next();
									int disponible = rs4.getInt("disponible");

									// Si disponible = 1, implica que está disponible y nos dejará continuar
									if (disponible == 1) {

										// Pedimos por consola la información restante para realizar el alquiler
										System.out.print("[0000-00-00] ");
										System.out.print("Fecha Alquiler: ");

										String dateAlquiler = teclado.nextLine();
										System.out.println(" ");

										System.out.print("[0000-00-00] ");
										System.out.print("Fecha Devolución: ");
										String dateDevolucion = teclado.nextLine();
										System.out.println(" ");

										// Insertamos el nuevo alquiler en la tabla alquiler
										stmt.executeUpdate("insert into " + BDNombre + ".alquiler VALUES(NULL,'" + dni
												+ "','" + numSerie + "','" + dateAlquiler + "','" + dateDevolucion
												+ "', 0)");

										// Actualizamos la tabla cliente y ponemos alquilerActivo = 1
										stmt.executeUpdate(
												"update cliente set AlquilerActivo = 1 where dni = '" + dni + "'");

										// Actualizamos la tabla patinete y ponemos disponible = 0
										stmt.executeUpdate(
												"update patinete set disponible = 0 where numSerie = " + numSerie);

										System.out.println("Se ha añadido el alquiler correctamente.");
										System.out.println(" ");

										// Si el alquiler se realiza con exito, tenemos la opción de realizar otro
										// alquiler
										System.out.println("¿Desea realizar otro alquiler?: [S/N]");
										System.out.print("--> ");
										String sn = "";
										sn = teclado.nextLine();
										sn = sn.toLowerCase();
										if (sn.equals("s")) {
											realizarAlquiler(connection, BDNombre);
										} else {
											// O volver al menú principal
											System.out.println("Saliendo...");
											Thread.sleep(2500);
											Menus.menuPrincipal(connection, BDNombre);
										}

										exit = true;

									} else {
										// Si el patinete no está disponible, termina el bucle y nos dirije al menu
										// principal
										System.out.println("Patinete no disponible");
										System.out.println("¿Desea realizar otro alquiler?: [S/N]");
										System.out.print("--> ");
										String sn = "";
										sn = teclado.nextLine();
										sn = sn.toLowerCase();
										if (sn.equals("s")) {
											realizarAlquiler(connection, BDNombre);
										} else {
											// O volver al menú principal
											System.out.println("Saliendo...");
											Thread.sleep(2500);
											Menus.menuPrincipal(connection, BDNombre);
										}
									}

									exit = true;
								} else {
									// Si el NumSerie es incorrecto nos aparece este mensaje
									System.out.println("Número de serie incorrecto");
									Thread.sleep(3000);

								}

							} catch (SQLException e) {

								Excepciones.printSQLException(e);
							}

						} while (!exit);

					} else {
						System.out.println("");
						System.out
								.println("El cliente ya tiene un alquiler activo, no es posible efectuar el alquiler.");
						Thread.sleep(3000);
						System.out.println("¿Desea introducir otro DNI?: [S/N]");
						System.out.print("--> ");
						String sn = "";
						sn = teclado.nextLine();
						sn = sn.toLowerCase();
						if (sn.equals("s")) {
							realizarAlquiler(connection, BDNombre);
						} else {
							// O volver al menú principal
							System.out.println("Saliendo...");
							Thread.sleep(2500);
							Menus.menuPrincipal(connection, BDNombre);
						}
					}

					exit = true;
				} else {

					System.out.println("");
					System.out.println("DNI incorrecto");
					Thread.sleep(3000);
				}

			} catch (SQLException e) {
				Excepciones.printSQLException(e);
			} finally {
				stmt.close();
			}

		} while (!exit);

	}

}
