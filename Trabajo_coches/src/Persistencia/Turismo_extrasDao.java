package Persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Dominio.Extra;
import Dominio.Turismo;
import Dominio.Turismo_extras;

public class Turismo_extrasDao {
	
	//--------------------------------------------------------------------------------
	public boolean insertar(Turismo_extras turismo_extra) throws ClassNotFoundException {
		boolean registrar = false;
		
		Statement stm= null;
		Connection con=null;
		

		String sql="INSERT INTO Turismo_extras values ('" + turismo_extra.getMatricula() + "', " + ((Turismo_extras) turismo_extra).getExtra().getid() + ")";
		
		try {			
			con=Conexion.conectar();
			stm= con.createStatement();
			stm.execute(sql);
			registrar=true;
			stm.close();
			con.close();
		} catch (SQLException e) {
			System.err.println("Error: Turismo_extrasDao");
			e.printStackTrace();
		}
		return registrar;
	}
	//-------------------------------------------------------------------------------------------
	
	//--------------------------------------------------------------------------
	public ArrayList<Turismo_extras> leerTodos() throws ClassNotFoundException {
		Connection co =null;
		Statement stm= null;
		ResultSet rs=null;
		
		String sql="SELECT * FROM Turismo_extras ORDER BY Turismo";
		
		ArrayList<Turismo_extras> listaTurismo_extras= new ArrayList<Turismo_extras>();
		
		Extra extra = new Extra();
		
		ArrayList<Integer> auxExtra=new ArrayList<Integer>();
		
		try {			
			co= Conexion.conectar();
			stm=co.createStatement();
			rs=stm.executeQuery(sql);
			while (rs.next()) {
				auxExtra.add(rs.getInt(2));	
				
				listaTurismo_extras.add(new Turismo_extras(rs.getString(1), extra));
			}
			stm.close();
			rs.close();
			co.close();
			
			for (int i=0;i<auxExtra.size();i++) {
				extra= extra.leerExtras(auxExtra.get(i));
				listaTurismo_extras.get(i).setExtra(extra);

			}
		} catch (SQLException e) {
			System.err.println("Error: Turismo_extras");
			e.printStackTrace();
		}
		
		return listaTurismo_extras;
	}
	//------------------------------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------------------------
	public Turismo_extras leer(int id) throws ClassNotFoundException {
		Connection co =null;
		Statement stm= null;
		ResultSet rs=null;
		Turismo_extras turismo_extra = null;
		String sql="SELECT * FROM Turismo_extras WHERE Turismo = '" + turismo_extra.getMatricula() +"'";
		
		Extra extra = new Extra();
		int auxExtra=0;
	    boolean encontrado=false;

		try {
			co= Conexion.conectar();
			stm=co.createStatement();
			rs=stm.executeQuery(sql);
			while (rs.next()) {
				auxExtra=rs.getInt(2);
			encontrado=true;
			
				turismo_extra = new Turismo_extras(rs.getString(1), extra);
			}
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.err.println("Error: Turismo_extras");
			e.printStackTrace();
		}		
		return turismo_extra;
	}
	//-----------------------------------------------------------------------------------------------------------------
	
/*public boolean actualizar(Turismo_extras turismo_extras, int id) throws ClassNotFoundException {
		Connection connect= null;
		Statement stm= null;
		
		boolean actualizar=false;
		String sql="";
		 sql="UPDATE Turismo_extras SET Turismo ="+turismo_extras.getMatricula() + ", Extra='"+turismo_extras.getDescripcion()+"' WHERE id="+id+"";
		try {
			connect=Conexion.conectar();
			stm=connect.createStatement();
			stm.execute(sql);
			actualizar=true;
		} catch (SQLException e) {
			System.err.println("Error: Turismo_extras");
			e.printStackTrace();
		}
		return actualizar;

	}*/
		
		public boolean eliminarTodo() throws ClassNotFoundException {
			Connection connect= null;
			Statement stm= null;
			
			boolean eliminar=false;
			
			String sql="DELETE FROM Turismo_extra WHERE Extra <> 0";
			try {
				connect=Conexion.conectar();
				stm=connect.createStatement();
				stm.execute(sql);
				eliminar=true;
				stm.close();
				connect.close();
			} catch (SQLException e) {
				System.err.println("Error: Turismo_extras");
				e.printStackTrace();	
			}		
			return eliminar;			
		}
	
		
		public boolean eliminar(Extra extra) throws ClassNotFoundException {
			Connection connect= null;	
			Statement stm= null;		
			
			boolean actualizar=false;
			boolean eliminar=false;
			
			String sql="UPDATE Turismo SET extra=0 WHERE extra=" + extra.getid()+"";
			String sql2="DELETE FROM Turismo_extras WHERE id = "+extra.getid()+ "";
			try {
				connect=Conexion.conectar();	
				stm=connect.createStatement();		
				stm.execute(sql);
				stm.execute(sql2);
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
