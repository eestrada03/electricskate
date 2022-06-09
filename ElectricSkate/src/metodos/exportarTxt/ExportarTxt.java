package metodos.exportarTxt;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import metodos.menus.Menus;

/**
 * Esta clase sirve para poder ilustrar el uso de los listados 
 * y exportarlos a ficheros txt
 * @author Reginaldo, Alberto, Kike, Jose Y Luz
 * @version 1.0
 */

public class ExportarTxt {
	
	/**
	 * Método para mostrar el listado de patinetes no alquilados 
	 * y exportar dicho listado a un fichero txt
	 * @param connection  la variable que establece la conexión con la base de datos
	 * @param BDNombre nombre de la base de datos por defecto
	 * @throws SQLException este nos sirve para lanzar una excepción
	 * @throws InterruptedException es una parte de la firma del método 
	 * y un posible resultado de llamar al método que está llamando
	 */
	
	// Método para exportar el listado de patinetes NO ALQUILADOS a ficheros TXT
	public static void listadoPatineteNoAlquilado(Connection connection, String BDNombre)
			throws SQLException, InterruptedException {
		
		//Abrimos el Statement
		Statement stmt = null;
		
		//Realizamos una consulta y la guardamos en un String
		String query = "select numSerie, marca, color, modelo, kmRecorridoPatinete from " + BDNombre
				+ ".patinete where disponible = '1' ";

		try {
			// Creamos un Statement
			stmt = connection.createStatement();
			
			//Ejecutamos la consulta y la guardamos en un objeto ResultSet
			ResultSet rs = stmt.executeQuery(query);
			
			// Creamos el directorio "Informes" en el disco C
			crearDirectorio();
			
			// Creamos el archivo  
			FileWriter escritura = new FileWriter("C:\\Informes\\informes.txt");

			// Creamos el buffer
			BufferedWriter buff = new BufferedWriter(escritura);
			
			// Escribimos la siguiente frase en el buffer
			buff.write("-- Listado de patinetes no alquilados --");
			buff.newLine();  // Escribe una nueva línea
			buff.newLine();	 // y dejamos una línea más en blanco para separar
			
			//Bucle while, el "next()" mueve el cursor hacia adelante en cada iteración de nuestra consulta
			while (rs.next()) {

				
				
				buff.write("---------------------------------------------");
				buff.newLine();
				// Recuperamos información de la base de datos
				String numSerie = rs.getString("numSerie");
				// Escribimos en la buffer los resultados obtenidos
				buff.write("Nº de Serie: " + numSerie);
				buff.newLine();
				
				//Realizamos el mismo procedimiento para el resto de información que queramos obtener de la base de datos
				
				String marca = rs.getString("marca");
				buff.write("Marca: " + marca);
				buff.newLine();

				String color = rs.getString("color");
				buff.write("Color: " + color);
				buff.newLine();

				String modelo = rs.getString("modelo");
				buff.write("Modelo: " + modelo);
				buff.newLine();

				String kmRecorridoPatinete = rs.getString("kmRecorridoPatinete");
				buff.write("Km recorridos: " + kmRecorridoPatinete + "km");
				buff.newLine();

				buff.write("---------------------------------------------");
				buff.newLine();
				buff.newLine();

			}
			
			System.out.println("");
			System.out.println("El fichero se ha escrito y guardado correctamente!");
			
			// Cerramos la conexión con el buffer
			buff.close();
			
		// Control de excepciones	
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// Cerramos el Statement
			stmt.close();
			
			Menus.volverAlMenuPrincipal(connection, BDNombre);
		}

	}
	
	/**
	 * Método para mostrar el listado de patinetes alquilados
	 * y exportar dicho listado a un fichero txt
	 * @param connection  la variable que establece la conexión con la base de datos
	 * @param BDNombre  nombre de la base de datos por defecto
	 * @throws SQLException este nos sirve para lanzar una excepción
	 * @throws InterruptedException es una parte de la firma del método 
	 * y un posible resultado de llamar al método que está llamando
	 */
	
	// Método para exportar el listado de patinetes ALQUILADOS a ficheros TXT
	public static void listadoPatineteAlquilado(Connection connection, String BDNombre) throws SQLException, InterruptedException {

		//Abrimos el Statement
		Statement stmt = null;
				
		//Realizamos una consulta y la guardamos en un String
		String query = "select numSerie, marca, color, modelo, kmRecorridoPatinete from " + BDNombre
				+ ".patinete where disponible = '0' ";

		try {
			// Creamos un Statement
			stmt = connection.createStatement();
			
			//Ejecutamos la consulta y la guardamos en un objeto ResultSet
			ResultSet rs = stmt.executeQuery(query);
			
			// Creamos el directorio "Informes" en el disco C
			crearDirectorio();
			
			// Creamos el archivo  
			FileWriter escritura = new FileWriter("C:\\Informes\\informes.txt");

			// Creamos el buffer
			BufferedWriter buff = new BufferedWriter(escritura);
			
			// Escribimos la siguiente frase en el buffer
			buff.write("-- Listado de patinetes alquilados --");
			buff.newLine();  // Escribe una nueva línea
			buff.newLine();	 // y dejamos una línea más en blanco para separar
			
			//Bucle while, el "next()" mueve el cursor hacia adelante en cada iteración de nuestra consulta
			while (rs.next()) {

				
				
				buff.write("---------------------------------------------");
				buff.newLine();
				// Recuperamos información de la base de datos
				String numSerie = rs.getString("numSerie");
				// Escribimos en la buffer los resultados obtenidos
				buff.write("Nº de Serie: " + numSerie);
				buff.newLine();
				
				//Realizamos el mismo procedimiento para el resto de información que queramos obtener de la base de datos
				
				String marca = rs.getString("marca");
				buff.write("Marca: " + marca);
				buff.newLine();

				String color = rs.getString("color");
				buff.write("Color: " + color);
				buff.newLine();

				String modelo = rs.getString("modelo");
				buff.write("Modelo: " + modelo);
				buff.newLine();

				String kmRecorridoPatinete = rs.getString("kmRecorridoPatinete");
				buff.write("Km recorridos: " + kmRecorridoPatinete + "km");
				buff.newLine();

				buff.write("---------------------------------------------");
				buff.newLine();
				buff.newLine();

			}
			
			System.out.println("");
			System.out.println("El fichero se ha escrito y guardado correctamente!");
			
			// Cerramos la conexión con el buffer
			buff.close();
			
		// Control de excepciones	
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// Cerramos el Statement
			stmt.close();
			
			Menus.volverAlMenuPrincipal(connection, BDNombre);
		}

	}
	
	/**
	 * Método para mostrar el listado completo de patinetes 
	 * y exportar dicho listado a un fichero txt
	 * @param connection la variable que establece la conexión con la base de datos
	 * @param BDNombre  nombre de la base de datos por defecto
	 * @throws SQLException este nos sirve para lanzar una excepción
	 * @throws InterruptedException es una parte de la firma del método 
	 * y un posible resultado de llamar al método que está llamando
	 */
	
	// Método para exportar el listado de TODOS los patinetes a ficheros TXT
	public static void listadoCompletoPatinetes(Connection connection, String BDNombre) throws SQLException, InterruptedException {

		//Abrimos el Statement
		Statement stmt = null;
				
		//Realizamos una consulta y la guardamos en un String
		String query = "select * from " + BDNombre + ".patinete ";

		try {
			// Creamos un Statement
			stmt = connection.createStatement();
			
			//Ejecutamos la consulta y la guardamos en un objeto ResultSet
			ResultSet rs = stmt.executeQuery(query);
			
			// Creamos el directorio "Informes" en el disco C
			crearDirectorio();
			
			// Creamos el archivo  
			FileWriter escritura = new FileWriter("C:\\Informes\\informes.txt");

			// Creamos el buffer
			BufferedWriter buff = new BufferedWriter(escritura);
			
			// Escribimos la siguiente frase en el buffer
			buff.write("-- Listado completo de patinetes --");
			buff.newLine();  // Escribe una nueva línea
			buff.newLine();	 // y dejamos una línea más en blanco para separar
			
			//Bucle while, el "next()" mueve el cursor hacia adelante en cada iteración de nuestra consulta
			while (rs.next()) {

				
				
				buff.write("---------------------------------------------");
				buff.newLine();
				// Recuperamos información de la base de datos
				String numSerie = rs.getString("numSerie");
				// Escribimos en la buffer los resultados obtenidos
				buff.write("Nº de Serie: " + numSerie);
				buff.newLine();
				
				//Realizamos el mismo procedimiento para el resto de información que queramos obtener de la base de datos
				
				String marca = rs.getString("marca");
				buff.write("Marca: " + marca);
				buff.newLine();

				String color = rs.getString("color");
				buff.write("Color: " + color);
				buff.newLine();

				String modelo = rs.getString("modelo");
				buff.write("Modelo: " + modelo);
				buff.newLine();

				String kmRecorridoPatinete = rs.getString("kmRecorridoPatinete");
				buff.write("Km recorridos: " + kmRecorridoPatinete + "km");
				buff.newLine();

				buff.write("---------------------------------------------");
				buff.newLine();
				buff.newLine();

			}
			
			System.out.println("");
			System.out.println("El fichero se ha escrito y guardado correctamente!");
			
			// Cerramos la conexión con el buffer
			buff.close();
			
		// Control de excepciones	
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// Cerramos el Statement
			stmt.close();
			
			Menus.volverAlMenuPrincipal(connection, BDNombre);
		}
	}
	
	/**
	 * Método para mostrar el listado de clientes 
	 * y exportar dicho listado a un fichero txt
	 * @param connection la variable que establece la conexión con la base de datos
	 * @param BDNombre nombre de la base de datos por defecto
	 * @throws SQLException este nos sirve para lanzar una excepción
	 * @throws InterruptedException es una parte de la firma del método 
	 * y un posible resultado de llamar al método que está llamando
	 */
	
	// Método para exportar el listado de cliente a ficheros TXT
	public static void listadoClientes(Connection connection, String BDNombre) throws SQLException, InterruptedException {

		//Abrimos el Statement
		Statement stmt = null;
				
		//Realizamos una consulta y la guardamos en un String
		String query = "select nombre, apellidos, edad, dni, email " + " from " + BDNombre + ".cliente";

		try {
			// Creamos un Statement
			stmt = connection.createStatement();
			
			//Ejecutamos la consulta y la guardamos en un objeto ResultSet
			ResultSet rs = stmt.executeQuery(query);

			// Creamos el directorio "Informes" en el disco C
			crearDirectorio();
			
			// Creamos el archivo  
			FileWriter escritura = new FileWriter("C:\\Informes\\informes.txt");

			// Creamos el buffer
			BufferedWriter buff = new BufferedWriter(escritura);

			buff.write("-- Listado clientes --");
			buff.newLine();
			buff.newLine();

			while (rs.next()) {

				// Crear archivo
				buff.write("---------------------------------------------");
				buff.newLine();
				// Recuperamos información de la base de datos
				String cliente = rs.getString("nombre");
				// Escribimos en la buffer los resultados obtenidos
				buff.write("Nombre: " + cliente);
				buff.newLine();

				//Realizamos el mismo procedimiento para el resto de información que queramos obtener de la base de datos
				
				String apellidos = rs.getString("apellidos");
				buff.write("Apellidos: " + apellidos);
				buff.newLine();

				String edad = rs.getString("edad");
				buff.write("Edad: " + edad + " años");
				buff.newLine();

				String dni = rs.getString("dni");
				buff.write("DNI: " + dni);
				buff.newLine();

				String email = rs.getString("email");
				buff.write("Email: " + email);
				buff.newLine();

				buff.write("---------------------------------------------");
				buff.newLine();
				buff.newLine();

			}
			
			System.out.println("");
			System.out.println("El fichero se ha escrito y guardado correctamente!");

			// Cerramos la conexión con el buffer
						buff.close();
						
			// Control de excepciones	
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				// Cerramos el Statement
				stmt.close();
				
				Menus.volverAlMenuPrincipal(connection, BDNombre);
			}

	}
	
	/**
	 * Método para poder crear el directorio para el fichero
	 */
	
	public static void crearDirectorio() {
		
		// Creamos objeto de tipo File y le asignamos una ruta absoluta
        File directorio = new File("C:\\Informes");
        // Creamos un if
        if (!directorio.exists()) {
        	// Si el directorio no existe, creará un directorio
            if (directorio.mkdirs()) {
            // Si ya existe un directorio con ese nombre no hará nada       
            } else {
                
            }
        }
		
	}	
	
}
