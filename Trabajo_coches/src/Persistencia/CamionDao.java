package Persistencia;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Dominio.Camion;
import Dominio.Vehiculo;

public class CamionDao {
	
	public CamionDao() {
		
	}
	public boolean insertar(Camion camion) throws ClassNotFoundException {
		boolean registrar = false;
		
		Statement stm= null;
		Connection con=null;
		
		String sql="INSERT INTO Vehiculo values ('"+camion.getMatricula()+"','"+
		camion.getMarca()+"','"+camion.getModelo()+"','"+camion.getColor()+"',"+camion.getPrecio()+")";
		
		String sql2="INSERT INTO Camion values ('"+camion.getMatricula()+"',"+((Camion)camion).getCapacidad_carga()+")";

		try {			
			con=Conexion.conectar();
			stm= con.createStatement();
			stm.execute(sql);
			stm.execute(sql2);
			registrar=true;
			stm.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase ProfesorDaoImple, método registrar");
			e.printStackTrace();
		}
		
		
		return registrar;
	}
	
	
	public ArrayList<Vehiculo> leerTodos() throws ClassNotFoundException {
		Connection co = null;
		Statement stm = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM Camion ORDER BY matricula";

		ArrayList<Vehiculo> listaCamion = new ArrayList<Vehiculo>();

		try {
			co = Conexion.conectar();
			stm = co.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				listaCamion.add(new Camion(rs.getString(1),"","","",0,rs.getInt(2)));
			}
			stm.close();
			rs.close();
			co.close();
			for (int i = 0; i < listaCamion.size(); i++) {
				co = Conexion.conectar();
				stm = co.createStatement();
				sql = "SELECT * FROM Vehiculo WHERE matricula='" + listaCamion.get(i).getMatricula() + "'";
				rs = stm.executeQuery(sql);
				rs.next();
				listaCamion.get(i).setMarca(rs.getString(2));
				listaCamion.get(i).setModelo(rs.getString(3));
				listaCamion.get(i).setColor(rs.getString(4));
				listaCamion.get(i).setPrecio(rs.getDouble(5));


				stm.close();
				rs.close();
				co.close();
			}
		} catch (SQLException e) {
			System.out.println("Error: Clase CamionDao");
			e.printStackTrace();
		}

		return listaCamion;
	}
 
	
	
	

	public Vehiculo leer(String matricula) throws ClassNotFoundException {
		Connection co = null;
		Statement stm = null;
		ResultSet rs = null;		

		Vehiculo leerVehiculo = null;
		String sql = "SELECT * FROM Camion WHERE matricula='" + matricula + "'";	
		try {
			co = Conexion.conectar();
			stm = co.createStatement();
			rs = stm.executeQuery(sql);
			
			while (rs.next()) {
				leerVehiculo = new Camion(rs.getString(1),"","","", 0, rs.getInt(2));
			}
			stm.close();	
			rs.close();	
			co.close();
			if(leerVehiculo!=null) {
				co = Conexion.conectar();
				stm = co.createStatement();
				sql = "SELECT * FROM Vehiculo WHERE matricula='" + leerVehiculo.getMatricula() + "'";
				rs = stm.executeQuery(sql);
				rs.next();
				leerVehiculo.setMarca(rs.getString(2));
				leerVehiculo.setModelo(rs.getString(3));
				leerVehiculo.setColor(rs.getString(4));
				leerVehiculo.setPrecio(rs.getDouble(5));


				}

		} catch (SQLException e) {
			System.out.println("Error: CamionDao");
			e.printStackTrace();	
		}
		return leerVehiculo;
	}
		
	
	public boolean eliminarTodo() throws ClassNotFoundException {
		Connection connect= null;	
		Statement stm= null;	
			
		boolean eliminar=false;	
				
		String sql="DELETE FROM Camion";
			
		try {
			connect=Conexion.conectar();	
			
			stm=connect.createStatement();	
			stm.execute(sql);
			eliminar=true;	
			
		} catch (SQLException e) {
			System.err.println("Error: CamionDao");
			e.printStackTrace();	
		}		
		return eliminar;		
	}
	
	public boolean actualizar(Vehiculo camion, String matricula) throws ClassNotFoundException {
		Connection connect= null;
		Statement stm= null;
		
		boolean actualizar=false;
		if(camion.getMatricula().equals(matricula)) {	

		String sql="UPDATE Camion SET Matricula='"+camion.getMatricula()+"','"+camion.getMarca()+"','"+
		camion.getModelo()+"',"+camion.getColor()+"','"+camion.getPrecio()+"WHERE DNI='"+camion.getMatricula()+"'";
		
		String sql2="UPDATE Camion SET Matricula='"+camion.getMatricula()+"', sueldo="+((Camion)camion).getCapacidad_carga()
				+" WHERE DNI='"+camion.getMatricula()+"'";
		try {
			
			connect=Conexion.conectar();
			stm=connect.createStatement();
			stm.execute(sql);	
			stm.execute(sql2);
			actualizar=true;	
			stm.close();	
			connect.close();
			
		} catch (SQLException e) {	
			System.out.println("Error: método actualizar");
			e.printStackTrace();
		}		
		}else {	
			
		String sql="INSERT INTO Vehiculo values ('"+camion.getMatricula()+"','"
		+camion.getMarca()+"','"+camion.getModelo()+"',"+camion.getColor()+"',"+camion.getPrecio()+")";
		
		String sql2="INSERT INTO Camion values ('"+camion.getMatricula()+"',"+((Camion)camion).getCapacidad_carga()+")";
		
		String sql3="DELETE FROM Camion WHERE Matricula='"+matricula+"'";
		
		String sql4="DELETE FROM Vehiculo WHERE Matricula='"+matricula+"'";
		
		try {	
			connect=Conexion.conectar();
			stm=connect.createStatement();
			
			stm.execute(sql);
			stm.execute(sql2);
			stm.execute(sql3);
			stm.execute(sql4);	
			actualizar=true;
			stm.close();
			connect.close();
			
		} catch (SQLException e) {
			System.out.println("Error: Camion Dao");
			e.printStackTrace();
		}	
		}
			
		
		return actualizar;
	}
	
	

	public boolean eliminar(Vehiculo camion) throws ClassNotFoundException {
		Connection connect= null;
		Statement stm= null;
					
		boolean eliminar=false;
		
		String sql="DELETE FROM Camion WHERE matricula='"+camion.getMatricula()+"'";	
		String sql2="DELETE FROM Vehiculo WHERE matricula='"+camion.getMatricula()+"'";

		try {
			connect=Conexion.conectar();
			stm=connect.createStatement();
			stm.execute(sql);
			stm.execute(sql2);
			eliminar=true;	
			
		} catch (SQLException e) {
			
			System.err.println("Error:  CamionDao");
			e.printStackTrace();
		}				
			
		return eliminar;	
	}
	
	
	
	
	
	
	
	}