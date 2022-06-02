package metodos.cargarDatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import metodos.excepciones.Excepciones;

public class CargarDatos {
	
	// Metodo para insertar los administradores
	public static void administrador(Connection connection, String BDNombre) throws SQLException {

		Statement stmt = null;

		try {

			stmt = connection.createStatement();

			stmt.executeUpdate("INSERT INTO " + BDNombre + ".administrador VALUES ("
					+ "'Reginaldo','Manga Angue',23, 'X66655544', 'reginaldo@estudiante.edib.es', 'Regi', '1234')");
			stmt.executeUpdate("INSERT INTO " + BDNombre + ".administrador VALUES ("
					+ "'Jose','Paredes',125, '00000001A','jose@estudiante.edib.es', 'jesucristo', 'password')");
			stmt.executeUpdate("INSERT INTO " + BDNombre + ".administrador VALUES ("
					+ "'Kike','Estrada',18, '12345678K', 'kike@estudiante.edib.es', 'Kiko', '123456')");

			System.out.println("");
			System.out.println("¡Se han agregado 3 administradores a la tabla Administrador!");

		} catch (SQLException e) {
			Excepciones.printSQLException(e);
		} finally {
			stmt.close();
		}

	}

	// Metodo para insertar los patinetes
	public static void patinete(Connection connection, String BDNombre) throws SQLException {

		Statement stmt = null;

		try {

			stmt = connection.createStatement();

			stmt.executeUpdate("INSERT INTO " + BDNombre + ".patinete VALUES ("
					+ "5678, 'Xiami','Negro','Electric Scooter 3',250,1)");
			stmt.executeUpdate("INSERT INTO " + BDNombre + ".patinete VALUES ("
					+ "6789, 'Hiboy','Maron','Patinete Eléctrico S2 Pro',40,1)");
			stmt.executeUpdate("INSERT INTO " + BDNombre + ".patinete VALUES ("
					+ "6556, 'Cecotec','Azul','Patinete Eléctrico Bongo Serie A',700,1)");

			System.out.println("");
			System.out.println("¡Se han agregado 3 patinetes a la tabla Patinete!");

		} catch (SQLException e) {
			Excepciones.printSQLException(e);
		} finally {
			stmt.close();
		}

	}

	// metodo para insertar a los clientes
	public static void cliente(Connection connection, String BDNombre) throws SQLException {

		Statement stmt = null;

		try {

			stmt = connection.createStatement();

			// CAMPOS EQUIPO: TEAM_ID, EQ_NOMBRE, ESTADIO, POBLACION, PROVINCIA, COD_POSTAL
			stmt.executeUpdate("INSERT INTO " + BDNombre + ".cliente VALUES ("
					+ "'Jose','Maria',35, 'X45678990', 'josemaria@docente.edib.es', 0)");
			stmt.executeUpdate("INSERT INTO " + BDNombre + ".cliente VALUES ("
					+ "'Laura','Montenegro',32, 'Y76589006','lauramontenegro@docente.edib.es', 0)");
			stmt.executeUpdate("INSERT INTO " + BDNombre + ".cliente VALUES ("
					+ "'Ivan','Suarez',25, 'X87623790', 'ivansuarez@docente.edib.es', 0)");

			System.out.println("");
			System.out.println("¡Se han agregado 3 clientes a la tabla Cliente!");

		} catch (SQLException e) {
			Excepciones.printSQLException(e);
		} finally {
			stmt.close();
		}

	}

	
}
