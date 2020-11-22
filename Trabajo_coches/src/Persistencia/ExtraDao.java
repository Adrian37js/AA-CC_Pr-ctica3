package Persistencia;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import Dominio.Turismo;
import Dominio.Extra;

public class ExtraDao {
	public ExtraDao() {
		
	}
	public boolean insertar(Extra extra) throws ClassNotFoundException {
		boolean registrar = false;
		
		Statement stm= null;
		Connection con=null;
		
		String sql="INSERT INTO Extras values ("+extra.getid()+", '"+extra.getDescripcion()+"')";
		
		try {			
			con=Conexion.conectar();
			stm= con.createStatement();
			stm.execute(sql);
			registrar=true;
			stm.close();
			con.close();
		} catch (SQLException e) {
			System.err.println("Error: ExtraDao");
			e.printStackTrace();
		}
		return registrar;
	}
 
	
	public ArrayList<Extra> leerTodos() throws ClassNotFoundException {
		Connection co =null;
		Statement stm= null;
		ResultSet rs=null;
		
		String sql="SELECT * FROM Extras ORDER BY id";
		
		ArrayList<Extra> listaExtras= new ArrayList<Extra>();
		
		try {			
			co= Conexion.conectar();
			stm=co.createStatement();
			rs=stm.executeQuery(sql);
			while (rs.next()) {
				listaExtras.add(new Extra(rs.getInt(1),rs.getString(2)));
			}
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.err.println("Error: ExtraDao");
			e.printStackTrace();
		}
		
		return listaExtras;
	}
 

	
	public Extra leer(int id) throws ClassNotFoundException {
		Connection co =null;
		Statement stm= null;
		ResultSet rs=null;
		Extra leerExtras = null;
		String sql="SELECT * FROM Extras WHERE id="+id+"";
		try {
			co= Conexion.conectar();
			stm=co.createStatement();
			rs=stm.executeQuery(sql);
			while (rs.next()) {
				leerExtras = new Extra(rs.getInt(1),rs.getString(2));
			}
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.err.println("Error: ExtraDao");
			e.printStackTrace();
		}		
		return leerExtras;
	}
	public boolean actualizar(Extra extra, int id) throws ClassNotFoundException {
		Connection connect= null;
		Statement stm= null;
		
		boolean actualizar=false;
		
		String sql="UPDATE Extras SET id="+extra.getid()+", descripcion='"+extra.getDescripcion()+"' WHERE id="+id+"";
		try {
			connect=Conexion.conectar();
			stm=connect.createStatement();
			stm.execute(sql);
			actualizar=true;
		} catch (SQLException e) {
			System.err.println("Error: ExtraDao");
			e.printStackTrace();
		}
		
			
		return actualizar;
	}
 
	
	public boolean eliminarTodo() throws ClassNotFoundException {
		Connection connect= null;
		Statement stm= null;
		
		boolean eliminar=false;
		boolean actualizar=false;
		String sql="UPDATE Turismo SET extra=0";
		try {
			connect=Conexion.conectar();
			stm=connect.createStatement();
			stm.execute(sql);
			actualizar=true;
			stm.close();
			connect.close();
		} catch (SQLException e) {
			System.err.println("Error: ExtraDao");
			e.printStackTrace();	
		}		
		sql="DELETE FROM Extras WHERE id <> 1";	
		try {
			connect=Conexion.conectar();
			stm=connect.createStatement();
			stm.execute(sql);	
			eliminar=true;	
			
		} catch (SQLException e) {	
			System.out.println("Error: ExtraDao");
			e.printStackTrace();	
		}		
		return eliminar;			
	}
	
	public boolean eliminar(Extra extra) throws ClassNotFoundException {
		Connection connect= null;	
		Statement stm= null;		
		
		boolean actualizar=false;
		boolean eliminar=false;
		
		String sql="UPDATE Turismo SET extra=1 WHERE extra="+extra.getid()+"";
		try {
			connect=Conexion.conectar();	
			stm=connect.createStatement();		
			stm.execute(sql);
			actualizar=true;
			
			stm.close();
			connect.close();
			
		} catch (SQLException e) {
			System.err.println("Error: ExtraDao")
			;
			e.printStackTrace();
		}	
		sql="DELETE FROM Extras WHERE id="+extra.getid()+"";
		try {
			connect=Conexion.conectar();
			stm=connect.createStatement();
			stm.execute(sql);
			
			eliminar=true;
		} catch (SQLException e) {
			
			System.err.println("Error: ExtraDao");
			e.printStackTrace();
		}		
		return eliminar;
	}


}