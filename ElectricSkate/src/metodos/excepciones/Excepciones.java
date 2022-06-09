package metodos.excepciones;

import java.sql.SQLException;

/**
 * Esta clase sirve solo para establecer las excepciones
 * @author Reginaldo, Alberto, Kike, Jose Y Luz
 * @version 1.0
 */

public class Excepciones {
	
	/**
	 * Metodo para tratar las SQLException lanzadas por por el programa java
	 * @param ex es la variable creada para el metodo de las SQLException.
	 */
	
	public static void printSQLException(SQLException ex) {

		ex.printStackTrace(System.err);
		System.err.println("SQLState: " + ex.getSQLState()); // getSQLState()
		System.err.println("Error Code: " + ex.getErrorCode()); // getErrorCode()
		System.err.println("Message: " + ex.getMessage()); // getMessage()

		Throwable t = ex.getCause(); // getCause() - Leemos la primera causa

		while (t != null) {
			System.out.println("Cause: " + t); // Imprimimos una causa
			t = t.getCause(); // Leemos otra causa
		}

	}

}
