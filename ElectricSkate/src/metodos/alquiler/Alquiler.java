package metodos.alquiler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import metodos.excepciones.Excepciones;
import metodos.menus.Menus;

public class Alquiler {
	
	static Scanner teclado = new Scanner(System.in);
		
	public static void realizarAlquiler(Connection connection, String BDNombre) throws SQLException, InterruptedException {
		
		boolean exit = false;
		
		do {
			
			System.out.println("");
			System.out.println("===============================");
			System.out.println("======REALIZAR ALQUILER========");
			System.out.println("===============================");
			System.out.println("");
			
			System.out.println("Introduzca los valores correspondientes:");
			System.out.println("");
			
			System.out.print("DNI: ");
			String dni = teclado.nextLine();
			
			Statement stmt = null;
			String comprobarDni = "SELECT nombre, apellidos " + " from " + BDNombre + ".cliente" + " WHERE dni = '" + dni + "'";

			try {
				
				stmt = connection.createStatement();
				ResultSet rs1 = stmt.executeQuery(comprobarDni);
				
				if (rs1.next()) {
					
					ResultSet rs2 = stmt.executeQuery("SELECT alquilerActivo FROM cliente WHERE dni = '" + dni + "'");
					rs2.next();
					int alquilerActivo = rs2.getInt("alquilerActivo");
					
					if (alquilerActivo == 0) {
						
						do {
							
							System.out.println(" ");
							
							System.out.print("Nº de serie: ");
							String numSerie = teclado.nextLine();
							System.out.println(" ");
							
							String comprobarNumSerie = "SELECT marca, modelo " + " from " + BDNombre + ".patinete" + 
													   " WHERE numSerie =  '" + numSerie + "'";
							
							try {
								
								ResultSet rs3 = stmt.executeQuery(comprobarNumSerie);
								
								
								if (rs3.next()) {
									
									ResultSet rs4 = stmt.executeQuery("SELECT disponible FROM patinete WHERE numSerie = '" + numSerie + "'");
									rs4.next();
									int disponible = rs4.getInt("disponible");
									
									if (disponible == 1) {
										
										System.out.print("[0000-00-00] ");
										System.out.print("Fecha Alquiler: ");
										
										String dateAlquiler = teclado.nextLine();
										System.out.println(" ");
										
										
										System.out.print("[0000-00-00] ");
										System.out.print("Fecha Devolución: ");
										String dateDevolucion = teclado.nextLine();
										System.out.println(" ");
										
										//Insertamos el nuevo alquiler en la tabla alquiler
										stmt.executeUpdate("insert into " + BDNombre + ".alquiler VALUES(NULL,'" + dni + "','" +  numSerie
												+ "','" + dateAlquiler + "','" + dateDevolucion + "', 0)");
										
										//Actualizamos la tabla cliente y ponemos alquilerActivo = 1
										stmt.executeUpdate("update cliente set AlquilerActivo = 1 where dni = '" + dni +"'");
										
										//Actualizamos la tabla patinete y ponemos disponible = 0
										stmt.executeUpdate("update patinete set disponible = 0 where numSerie = " + numSerie);
										
										System.out.println("Se ha añadido el alquiler correctamente.");
										System.out.println(" ");
										
										Scanner tecla = new Scanner(System.in);
										System.out.println("¿Desea realizar otro alquiler?: [S/N]");
										System.out.print("--> ");
										String sn = "";
										sn = tecla.nextLine();
										sn = sn.toLowerCase();
										if (sn.equals("s")) {
											realizarAlquiler(connection, BDNombre);
										}else {
											System.out.println("Saliendo...");
											Thread.sleep(2500);
											Menus.menuPrincipal(connection, BDNombre);
										}
										
										exit = true;
										
									} else {

										System.out.println("Patinete no disponible");
										Thread.sleep(3000);
										realizarAlquiler(connection, BDNombre);
									}
	
									exit = true;
								} else {
									
									System.out.println("Número de serie incorrecto");
									Thread.sleep(3000);
									
								}
											
							} catch (SQLException e) {
								
								Excepciones.printSQLException(e);	
							}
							
							
							
						} while (!exit);
									
					} else {
						
						System.out.println("El cliente ya tiene un alquiler activo, no es posible efectuar el alquiler");
						Thread.sleep(3000);
						realizarAlquiler(connection, BDNombre);
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
