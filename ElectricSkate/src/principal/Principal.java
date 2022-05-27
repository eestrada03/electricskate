package principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Principal {

	public static void main(String[] args) {

		try {
			
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/electricskate", "root", "");
			
			System.out.println("¡Conexión establecida correctamente!");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}

}
