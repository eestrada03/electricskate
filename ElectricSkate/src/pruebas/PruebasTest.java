package pruebas;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import metodos.excepciones.Excepciones;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PruebasTest {
	
	//Variables vacias para almacenar las consultas en la base de datos
	private int edadMetodo = 0;
	
	private String colorMetodo = "";
	
	private String contraseñaMetodo = "";
	
	private int kmRecorridoPatineteMetodo = 0;
	
	private String emailAdminMetodo = "";
	
	
	@Before
	public void setUp() throws Exception {
				
		try {
			//Antes de ejecutar los tests nos conectamos a la base de datos
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/electricskate", "root", "");
			
			//Almacenamos las consultas en la variables creadas previamente
			 edadMetodo = Pruebas.edadCliente(connection, "electricskate", "X87623790");
			 
			 colorMetodo = Pruebas.colorPatinete(connection, "electricskate", 5678);
			 
			 contraseñaMetodo = Pruebas.contraseñaAdmin(connection, "electricskate", "jesucristo");
			 
			 kmRecorridoPatineteMetodo = Pruebas.kmRecorridoPatinete(connection, "electricskate", 6556);
			 
			 emailAdminMetodo = Pruebas.emailAdmin(connection, "electricskate", "X66655544");
			
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
	public void testColorPatinete() {
		
		Assert.assertEquals("Negro", colorMetodo);
		Assert.assertNotEquals("Azul", colorMetodo);
			
	}
	
	@Test
	public void testContraseñaAdmin() {
			
		Assert.assertEquals("password", contraseñaMetodo);	
		Assert.assertNotEquals("123456", contraseñaMetodo);
				
	}
	
	@Test
	public void testKmRecorridoPatinete() {
			
		Assert.assertEquals(700, kmRecorridoPatineteMetodo);	
		Assert.assertNotEquals(256, kmRecorridoPatineteMetodo);
				
	}
	
	@Test
	public void testEmailAdmin() {
			
		Assert.assertEquals("reginaldo@estudiante.edib.es", emailAdminMetodo);	
		Assert.assertNotEquals("kike@estudiante.edib.es", emailAdminMetodo);		
	}
	
}
