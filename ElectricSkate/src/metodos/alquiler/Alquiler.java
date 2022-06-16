package metodos.alquiler;



import java.nio.file.attribute.AclEntry;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping;

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
	 * M�todo que nos permite realizar el alquiler de un determinado patinete
	 * @param connection la variable que establece la conexi�n con la base de datos
	 * @param BDNombre nombre de la base de datos por defecto
	 * @throws SQLException este nos sirve para lanzar una excepci�n
	 * @throws InterruptedException es una parte de la firma del m�todo 
	 * y un posible resultado de llamar al m�todo que est� llamando
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
			// Pedimos informaci�n por consola
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

				// Con este if comprobamos si la informaci�n que ha introducido el usuario por
				// consola existe en la base de datos
				if (rs1.next()) {
					// Si existe nos dejar� continuar, si no, nos la volver� a solicitar

					// Recuperamos el alquilerActivo con el dni que haya introducido el usuario
					ResultSet rs2 = stmt.executeQuery("SELECT alquilerActivo FROM cliente WHERE dni = '" + dni + "'");
					rs2.next();
					int alquilerActivo = rs2.getInt("alquilerActivo");

					// Si el alquilerActivo es igual a 0 quiere decir que el cliente no tiene ning�n
					// alquiler activo y nos permite continuar
					if (alquilerActivo == 0) {

						do {

							System.out.println(" ");

							System.out.print("N� de serie: ");
							// Pedimos informaci�n por consola
							String numSerie = teclado.nextLine();
							System.out.println(" ");
							// Realizamos una consulta y la guardamos en un String
							String comprobarNumSerie = "SELECT marca, modelo " + " from " + BDNombre + ".patinete"
									+ " WHERE numSerie =  '" + numSerie + "'";

							try {
								// En este caso comprobar� si el numSerie introducido por consola existe en la
								// base de datos
								ResultSet rs3 = stmt.executeQuery(comprobarNumSerie);

								// Si existe nos dejar� continuar, si no, nos lo volver� a solicitar
								if (rs3.next()) {

									// Realizamos otra consulta para comprobar si el patinete este disponible
									ResultSet rs4 = stmt.executeQuery(
											"SELECT disponible FROM patinete WHERE numSerie = '" + numSerie + "'");
									rs4.next();
									int disponible = rs4.getInt("disponible");
									
									
										

									// Si disponible = 1, implica que est� disponible y nos dejar� continuar
									if (disponible == 1) {
										
										String dateAlquiler = " ";
										String dateDevolucion = " ";
										
										try {
										// Pedimos por consola la informaci�n restante para realizar el alquiler
										System.out.print("Fecha Alquiler [aaaa-mm-dd]: ");
										dateAlquiler = teclado.nextLine();
										System.out.println(" ");

										System.out.print("Fecha Devoluci�n [aaaa-mm-dd]: ");
										dateDevolucion = teclado.nextLine();
										System.out.println(" ");		
										
										// Insertamos el nuevo alquiler en la tabla alquiler
										stmt.executeUpdate("insert into " + BDNombre + ".alquiler VALUES(NULL,'" + dni
												+ "','" + numSerie + "','" + dateAlquiler + "','" + dateDevolucion
												+ "', 0)");
										
										} catch (Exception e) {
											System.out.println("Error al realizar el alquiler, introduzca la fecha en un formato correcto.");
											Thread.sleep(2500);
											realizarAlquiler(connection, BDNombre);
										}	
										// Actualizamos la tabla cliente y ponemos alquilerActivo = 1
										stmt.executeUpdate(
												"update cliente set AlquilerActivo = 1 where dni = '" + dni + "'");

										// Actualizamos la tabla patinete y ponemos disponible = 0
										stmt.executeUpdate(
												"update patinete set disponible = 0 where numSerie = " + numSerie);

										System.out.println("Se ha a�adido el alquiler correctamente.");
										System.out.println(" ");

										// Si el alquiler se realiza con exito, tenemos la opci�n de realizar otro
										// alquiler
										System.out.println("�Desea realizar otro alquiler?: [S/N]");
										System.out.print("--> ");
										String sn = "";
										sn = teclado.nextLine();
										sn = sn.toLowerCase();
										if (sn.equals("s")) {
											realizarAlquiler(connection, BDNombre);
										} else {
											// O volver al men� principal
											System.out.println("Saliendo...");
											Thread.sleep(2500);
											Menus.menuPrincipal(connection, BDNombre);
										}

										exit = true;

									} else {
										// Si el patinete no est� disponible, termina el bucle y nos dirije al menu
										// principal
										System.out.println("Patinete no disponible");
										System.out.println("�Desea realizar otro alquiler?: [S/N]");
										System.out.print("--> ");
										String sn = "";
										sn = teclado.nextLine();
										sn = sn.toLowerCase();
										if (sn.equals("s")) {
											realizarAlquiler(connection, BDNombre);
										} else {
											// O volver al men� principal
											System.out.println("Saliendo...");
											Thread.sleep(2500);
											Menus.menuPrincipal(connection, BDNombre);
										}
									}

									exit = true;
								} else {
									// Si el NumSerie es incorrecto nos aparece este mensaje
									System.out.println("N�mero de serie incorrecto");
									Thread.sleep(3000);

								}

							} catch (SQLException e) {

								Excepciones.printSQLException(e);
							}

						} while (!exit);

					} else {
						System.out.println("");
						System.out.println("El cliente ya tiene un alquiler activo, no es posible efectuar el alquiler.");
						Thread.sleep(3000);
						System.out.println("�Desea introducir otro DNI?: [S/N]");
						System.out.print("--> ");
						String sn = "";
						sn = teclado.nextLine();
						sn = sn.toLowerCase();
						if (sn.equals("s")) {
							realizarAlquiler(connection, BDNombre);
						} else {
							// O volver al men� principal
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
