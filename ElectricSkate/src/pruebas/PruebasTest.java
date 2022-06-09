package pruebas;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import metodos.excepciones.Excepciones;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PruebasTest {
	
	private int edadMetodo = 0;
	
	private String colorMetodo = "";
	
	private String contraseñaMetodo = "";
	
	@Before
	public void setUp() throws Exception {
				
		try {

			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/electricskate", "root", "");

			 edadMetodo = Pruebas.edadCliente(connection, "electricskate", "X87623790");
			 
			 colorMetodo = Pruebas.colorPatinete(connection, "electricskate", 5678);
			 
			 contraseñaMetodo = Pruebas.contraseñaAdmin(connection, "electricskate", "jesucristo");
			
		} catch (SQLException e) {
			Excepciones.printSQLException(e);			
		}
				
	}

	@Test
	public void testEdadCliente() {
		
		Assert.assertEquals(25, edadMetodo);
		Assert.assertNotEquals(28, edadMetodo);
	}

	
	@Test
	public void testcolorPatinete() {
		
		Assert.assertEquals("Negro", colorMetodo);
		Assert.assertNotEquals("Azul", colorMetodo);
			
	}
	
	@Test
	public void testcontraseñaAdmin() {
			
		Assert.assertEquals("password", contraseñaMetodo);	
		Assert.assertNotEquals("123456", contraseñaMetodo);
				
	}
	
	
	
	
	
	
}
