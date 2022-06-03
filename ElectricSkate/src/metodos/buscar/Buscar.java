package metodos.buscar;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import metodos.excepciones.Excepciones;
import metodos.menus.Menus;

public class Buscar {
	
	
	// metodo para buscar a los clientes registrados mediante su dni
	public static void cliente(Connection connection, String BDNombre) throws SQLException, InterruptedException {
		Scanner teclado = new Scanner(System.in);

		System.out.println("");
		System.out.println("===============================");
		System.out.println("========BUSCAR CLIENTE=========");
		System.out.println("===============================");
		System.out.println("");

		System.out.print("introduzca el DNI del cliente a buscar: ");
		System.out.println("");
		String dni = teclado.nextLine();

		Statement consulta = null;
		String query = "select nombre, apellidos, edad, email " + " from " + BDNombre + ".cliente" + " WHERE dni = '"
				+ dni + "'";

		try {

			consulta = connection.createStatement();
			ResultSet registro = consulta.executeQuery(query);

			System.out.println("");
			System.out.println("**** DATOS DEL CLIENTE CON EL DNI: " + dni + " ****");

			if (registro.next()) {

				System.out.println("");
				System.out.println("*************************************************");

				String cliente = registro.getString("nombre");
				System.out.println("Nombre: " + cliente);

				String apellidos = registro.getString("apellidos");
				System.out.println("Apellidos: " + apellidos);

				String edad = registro.getString("edad");
				System.out.println("Edad: " + edad + " años");

				String email = registro.getString("email");
				System.out.println("Email: " + email);

				System.out.println("");
				System.out.println("*************************************************");
				
				Scanner tecla = new Scanner(System.in);
				System.out.println("¿Desea buscar otro DNI?: [S/N]");
				System.out.print("--> ");
				String sn = "";
				sn = tecla.nextLine();
				sn = sn.toLowerCase();
				if (sn.equals("s")) {
					Buscar.cliente(connection, BDNombre);
				}else {
					System.out.println("Saliendo...");
					Thread.sleep(2500);
					Menus.menuPrincipal(connection, BDNombre);
				}
				
			} else {
				System.out.println("");
				System.out.println("DNI incorrecto.");
				Thread.sleep(2500);
				Buscar.cliente(connection, BDNombre);
			}

		} catch (SQLException e) {
			Excepciones.printSQLException(e);
		} finally {
			consulta.close();
		}

	}


}
