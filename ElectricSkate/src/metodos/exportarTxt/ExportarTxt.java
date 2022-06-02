package metodos.exportarTxt;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ExportarTxt {
	
	// Método para exportar el listado de patinetes NO ALQUILADOS a ficheros TXT
	public static void listadoPatineteNoAlquilado(Connection connection, String BDNombre)
			throws SQLException {

		Statement stmt = null;

		String query = "select numSerie, marca, color, modelo, kmRecorridoPatinete from " + BDNombre
				+ ".patinete where disponible = '1' ";

		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			FileWriter escritura = new FileWriter("C:\\Users\\Programming\\Desktop\\informes.txt");

			// Creamos el buffer
			BufferedWriter buff = new BufferedWriter(escritura);

			buff.write("-- Listado de patinetes no alquilados --");
			buff.newLine();
			buff.newLine();

			while (rs.next()) {

				// Crear archivo
				buff.write("---------------------------------------------");
				buff.newLine();
				String numSerie = rs.getString("numSerie");
				buff.write("Nº de Serie: " + numSerie);
				buff.newLine();

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

			System.out.println("El fichero se ha escrito y guardado correctamente!");

			buff.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			stmt.close();
		}

	}

	// Método para exportar el listado de patinetes ALQUILADOS a ficheros TXT
	public static void listadoPatineteAlquilado(Connection connection, String BDNombre) throws SQLException {

		Statement stmt = null;

		String query = "select numSerie, marca, color, modelo, kmRecorridoPatinete from " + BDNombre
				+ ".patinete where disponible = '0' ";

		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			FileWriter escritura = new FileWriter("C:\\Users\\Programming\\Desktop\\informes.txt");

			// Creamos el buffer
			BufferedWriter buff = new BufferedWriter(escritura);

			buff.write("-- Listado de patinetes alquilados --");
			buff.newLine();
			buff.newLine();

			while (rs.next()) {

				// Crear archivo
				buff.write("---------------------------------------------");
				buff.newLine();
				String numSerie = rs.getString("numSerie");
				buff.write("Nº de Serie: " + numSerie);
				buff.newLine();

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

			System.out.println("El fichero se ha escrito y guardado correctamente!");

			buff.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			stmt.close();
		}

	}

	// Método para exportar el listado de TODOS los patinetes a ficheros TXT
	public static void listadoCompletoPatinetes(Connection connection, String BDNombre) throws SQLException {

		Statement stmt = null;

		String query = "select * from " + BDNombre + ".patinete ";

		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			FileWriter escritura = new FileWriter("C:\\Users\\Programming\\Desktop\\informes.txt");

			// Creamos el buffer
			BufferedWriter buff = new BufferedWriter(escritura);

			buff.write("-- Listado total patinetes --");
			buff.newLine();
			buff.newLine();

			while (rs.next()) {

				// Crear archivo
				buff.write("---------------------------------------------");
				buff.newLine();
				String numSerie = rs.getString("numSerie");
				buff.write("Nº de Serie: " + numSerie);
				buff.newLine();

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

				String disponible = rs.getString("disponible");
				buff.write("Disponible: " + disponible);
				buff.newLine();

				buff.write("---------------------------------------------");
				buff.newLine();
				buff.newLine();

			}

			System.out.println("El fichero se ha escrito y guardado correctamente!");

			buff.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			stmt.close();
		}

	}

	// Método para exportar el listado de cliente a ficheros TXT
	public static void listadoClientes(Connection connection, String BDNombre) throws SQLException {

		Statement stmt = null;

		String query = "select nombre, apellidos, edad, dni, email " + " from " + BDNombre + ".cliente";

		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			FileWriter escritura = new FileWriter("C:\\Users\\Programming\\Desktop\\informes.txt");

			// Creamos el buffer
			BufferedWriter buff = new BufferedWriter(escritura);

			buff.write("-- Listado clientes --");
			buff.newLine();
			buff.newLine();

			while (rs.next()) {

				// Crear archivo
				buff.write("---------------------------------------------");
				buff.newLine();
				String cliente = rs.getString("nombre");
				buff.write("Nombre: " + cliente);
				buff.newLine();

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

			System.out.println("El fichero se ha escrito y guardado correctamente!");

			buff.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			stmt.close();
		}

	}


	
}
