package metodos.buscar;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import metodos.excepciones.Excepciones;
import metodos.menus.Menus;

public class Buscar {
	
	
	// Método para buscar a los clientes registrados mediante su dni
	public static void cliente(Connection connection, String BDNombre) throws SQLException, InterruptedException {
		//Creamos un nuevo objeto de tipo Scanner para pedir datos por consola
		Scanner teclado = new Scanner(System.in);

		System.out.println("");
		System.out.println("===============================");
		System.out.println("========BUSCAR CLIENTE=========");
		System.out.println("===============================");
		System.out.println("");

		System.out.print("introduzca el DNI del cliente a buscar: ");
		System.out.println("");
		//Almacenamos lo que escribamos por consola en una variable
		String dni = teclado.nextLine();
		
		//Abrimos el Statement
		Statement consulta = null;
		//Creamos la sentencia select para los datos del cliente, buscandolo por su dni
		String query = "select nombre, apellidos, edad, email " + " from " + BDNombre + ".cliente" + " WHERE dni = '"
				+ dni + "'";

		try {
			
			//Guardamos dentro del objeto consulta de la clase Statement la conexión a la base de datos
			consulta = connection.createStatement();
			//guardamos todos los registros de la tabla que vamos a consultar
			ResultSet registro = consulta.executeQuery(query);

			System.out.println("");
			System.out.println("**** DATOS DEL CLIENTE CON EL DNI: " + dni + " ****");
			
			//comprobamos si existen los valores de dicha tabla
			if (registro.next()) {

				System.out.println("");
				System.out.println("*************************************************");
				
				//almacenamos los valores obtenidos dendro de una variable
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
				
				//si deseamos buscar otro dni
				Scanner tecla = new Scanner(System.in);
				System.out.println("¿Desea buscar otro DNI?: [S/N]");
				System.out.print("--> ");
				String sn = "";
				sn = tecla.nextLine();
				sn = sn.toLowerCase();
				if (sn.equals("s")) {
					Buscar.cliente(connection, BDNombre);
				}else {
					
					Menus.volverAlMenuPrincipal(connection, BDNombre);
				}
				
			} else {
				System.out.println("");
				System.out.println("DNI incorrecto.");
				Thread.sleep(2500);
				Buscar.cliente(connection, BDNombre);
			}
			
		// Control de excepciones
		} catch (SQLException e) {
			Excepciones.printSQLException(e);
		} finally {
			// Cerramos el Statement
			consulta.close();
		}

	}


}
