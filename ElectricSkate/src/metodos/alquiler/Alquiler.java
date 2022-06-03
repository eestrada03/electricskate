package metodos.alquiler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import metodos.excepciones.Excepciones;

public class Alquiler {
	
	static Scanner teclado = new Scanner(System.in);
	
	public static void realizarAlquiler(Connection connection, String BDnom) throws SQLException {

		System.out.println("");
		System.out.println("===============================");
		System.out.println("======REALIZAR ALQUILER========");
		System.out.println("===============================");
		System.out.println("");
		
		System.out.println("Introduzca los valores correspondientes:");
		System.out.println("");
		
		System.out.print("DNI: ");
		String dni = teclado.nextLine();
		
		System.out.println(" ");
		
		System.out.print("Nº de serie: ");
		int numSerie = teclado.nextInt();
		System.out.println(" ");
		
		teclado.nextLine();
		System.out.print("[0000-00-00] ");
		System.out.print("Fecha Alquiler: ");
		
		String dateAlquiler = teclado.nextLine();
		System.out.println(" ");
		
		
		System.out.print("[0000-00-00] ");
		System.out.print("Fecha Devolución: ");
		String dateDevolucion = teclado.nextLine();
		System.out.println(" ");
	
		
		Statement stmt = null;

		try {
			stmt = connection.createStatement();
			
			ResultSet alqact = stmt.executeQuery("select AlquilerActivo from cliente where dni = '" + dni + "'");
			int AlquilerActivo = 0;
			while (alqact.next()) {
			
			AlquilerActivo = alqact.getInt("AlquilerActivo");
			
			if (AlquilerActivo == 1) {
				
				System.out.println("El cliente ya tiene un alquiler activo, no es posible efectuar el alquiler");
				
			}else {
				
				stmt.executeUpdate("insert into " + BDnom + ".alquiler VALUES(NULL,'" + dni + "','" +  numSerie
						+ "','" + dateAlquiler + "','" + dateDevolucion + "', kmRecorridoCliente = 0)");
				System.out.println("");
				
				
				stmt.executeUpdate("update patinete set disponible = 0 where numSerie = " + numSerie);
				stmt.executeUpdate("update cliente set AlquilerActivo = 1 where dni = '" + dni +"'");

				System.out.println("Se ha añadido el alquiler correctamente.");
				System.out.println("El patinete con nº " + numSerie + " ya no se encuentra disponible.");
								
			}

			}
		} catch (SQLException e) {
			Excepciones.printSQLException(e);
			} finally {
				stmt.close();
			}
	
	
	}
	
	
	
}
