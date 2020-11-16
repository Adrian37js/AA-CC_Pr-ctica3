package Persistencia;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	public static Connection conectar() throws ClassNotFoundException, SQLException {
		Connection conexion = null;
		
		String password = "1234";
		String usuario = "SYSTEM";
		String url ="jdbc:oracle:thin:@localhost:1521:XE";

		try {
			DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
			 Class.forName("oracle.jdbc.driver.OracleDriver");
			    conexion = DriverManager.getConnection(url,usuario,password);
			
		} catch (SQLException e) {
			System.out.println("No se pudo conectar a la base de datos");
			e.printStackTrace();
		}
		return conexion;
	}
}