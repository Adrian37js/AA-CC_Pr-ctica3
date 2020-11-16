package Persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Dominio.Empleado;




public class EmpleadoDao {
	
	
	
	public EmpleadoDao() {
		
	}
	
	public ArrayList<Empleado> leerTodos() throws ClassNotFoundException {
		Connection co = null;
		Statement stm = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM Empleados ORDER BY usuario";

		ArrayList<Empleado> listaEmpleado = new ArrayList<Empleado>();

		try {
			co = Conexion.conectar();
			stm = co.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				listaEmpleado.add(new Empleado(rs.getString(1), rs.getString(2)));

				}
			stm.close();
			rs.close();
			co.close();
		

			for (int i = 0; i < listaEmpleado.size(); i++) {
				co = Conexion.conectar();
				stm = co.createStatement();
				sql = "SELECT * FROM Empleados WHERE usuario='" + listaEmpleado.get(i).getUsuario()+ "'";
				rs = stm.executeQuery(sql);
				rs.next();
				listaEmpleado.get(i).setPassword(rs.getString(2));
			


				stm.close();
				rs.close();
				co.close();
			}
		} catch (SQLException e) {
			System.err.println("Error: EmpleadoDao");
			e.printStackTrace();
		}

		return listaEmpleado;
	}

	public static boolean leerLogin(String usuario, String password) throws ClassNotFoundException, SQLDataException {
		Connection co =null;
		Statement stm= null;
		ResultSet rs=null;
		Empleado leerLogin = null;
		boolean seguir = false;
		String sql="SELECT * FROM Empleados WHERE usuario= " + usuario +" AND password= '" + password + "'";
		try {
			co= Conexion.conectar();
			stm=co.createStatement();
			rs=stm.executeQuery(sql);
			stm.close();
			rs.close();
			co.close();
			seguir = true;
		} catch (SQLException e) {
			System.err.println("Error: EmpleadoDao");
			e.printStackTrace();
			seguir = false;
		}
		return seguir;
	}

	
	


	

}
