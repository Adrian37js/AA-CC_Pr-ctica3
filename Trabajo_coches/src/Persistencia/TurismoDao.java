package Persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Dominio.Extra;
import Dominio.Turismo;
import Dominio.Vehiculo;

public class TurismoDao extends VehiculoDao {
	public TurismoDao() {

	}
	
	
	public boolean insertar(Vehiculo turismo) throws ClassNotFoundException {
		boolean registrar = false;

		Statement stm = null;
		Connection con = null;

		String sql = "INSERT INTO Vehiculo values ('" + turismo.getMatricula() + "','" + turismo.getModelo() + "','"
				+ turismo.getMarca() + "','" + turismo.getColor()+"'," + turismo.getPrecio()+")";
		String sql2 = "INSERT INTO Turismo values ('" + turismo.getMatricula() + "'," + ((Turismo) turismo).getNum_puertas()+","+ ((Turismo) turismo).getExtras().getid() + ")";

		try {
			con = Conexion.conectar();
			stm = con.createStatement();
			
			stm.execute(sql);
			stm.execute(sql2);
			
			registrar = true;
			
			stm.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase PersonaDaoImple, método registrar");
			e.printStackTrace();
		}

		
		return registrar;
	}

	@Override
	public ArrayList<Vehiculo> leerTodos() throws ClassNotFoundException {
		Connection co = null;
		Statement stm = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM Turismo ORDER BY matricula";

		ArrayList<Vehiculo> listaVehiculo = new ArrayList<Vehiculo>();
		Extra extra = new Extra();
		ArrayList<Integer> auxExtra=new ArrayList<Integer>();
		try {
			co = Conexion.conectar();
			stm = co.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				auxExtra.add(rs.getInt(3));	
				listaVehiculo.add(new Turismo(rs.getString(1), "", "","", 0, rs.getInt(2), extra));

				}
			stm.close();
			rs.close();
			co.close();
			for (int i=0;i<auxExtra.size();i++) {
				extra= extra.leerExtras(auxExtra.get(i));
				((Turismo)listaVehiculo.get(i)).setExtras(extra);

			}

			for (int i = 0; i < listaVehiculo.size(); i++) {
				co = Conexion.conectar();
				stm = co.createStatement();
				sql = "SELECT * FROM Vehiculo WHERE matricula='" + listaVehiculo.get(i).getMatricula()+ "'";
				rs = stm.executeQuery(sql);
				rs.next();
				listaVehiculo.get(i).setMarca(rs.getString(2));
				listaVehiculo.get(i).setModelo(rs.getString(3));
				listaVehiculo.get(i).setColor(rs.getString(4));
				listaVehiculo.get(i).setPrecio(rs.getDouble(5));


				stm.close();
				rs.close();
				co.close();
			}
		} catch (SQLException e) {
			System.out.println("Error: Clase TurismoDaoImple, método obtener");
			e.printStackTrace();
		}

		return listaVehiculo;
	}

	//Leer Vehiculo
	public Vehiculo leer(String matricula) throws ClassNotFoundException {
		Connection co = null;
		Statement stm = null;
		ResultSet rs = null;

		Vehiculo leerVehiculo = null;
		String sql = "SELECT * FROM Turismo WHERE matricula='" + matricula + "'";
		Extra extra = new Extra();
		int auxExtra=0;
	    boolean encontrado=false;
		try {
			co = Conexion.conectar();
			stm = co.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				auxExtra=rs.getInt(2);
			encontrado=true;
				leerVehiculo = new Turismo(rs.getString(1), "", "","", 0, rs.getInt(2), extra);
			}
			stm.close();
			rs.close();
			co.close();
			if(encontrado) {
			extra=extra.leerExtras(auxExtra);			
			((Turismo)leerVehiculo).setExtras(extra);
			}
			if (leerVehiculo != null) {
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
			System.out.println("Error:  método eliminar");
			e.printStackTrace();
		}
		return leerVehiculo;	
	}

	//Actualizar
	public boolean actualizar(Vehiculo turismo, String matricula) throws ClassNotFoundException {
		Connection connect = null;
		Statement stm = null;

		boolean actualizar = false;
		
		if(turismo.getMatricula().equals(matricula)) {		
			
		String sql="UPDATE Vehiculo SET matricula='"+turismo.getMatricula()+"', marca='"+turismo.getMarca()+
				"', modelo='"+turismo.getModelo()+"', color= '"+turismo.getColor()+"', precio="
				+turismo.getPrecio()+" WHERE matricula='"+turismo.getMatricula()+"'";	
		
		String sql2="UPDATE Turismo SET matricula='"+turismo.getMatricula()+"', extra="
				+((Turismo)turismo).getExtras().getid()+" WHERE matricula='"+turismo.getMatricula()+"'";
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
		String sql="INSERT INTO Vehiculo values ('"+turismo.getMatricula()+"','"+turismo.getMarca()+"','"+
		turismo.getModelo()+"',"+turismo.getColor()+"',"+turismo.getPrecio()+")";
			
		String sql2="INSERT INTO Turismo values ('"+turismo.getMatricula()+"',"+((Turismo)turismo).getExtras().getid()+")";
		
		String sql3="DELETE FROM Turismo WHERE matricula='"+matricula+"'";
		
		String sql4="DELETE FROM Vehiculo WHERE matricula='"+matricula+"'";
		
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
			System.out.println("Error: método actualizar");
			e.printStackTrace();	
		}	
		}
		return actualizar;
	}
	public boolean eliminar(Vehiculo turismo) throws ClassNotFoundException {
		Connection connect = null;
		Statement stm = null;

		boolean eliminar = false;

		String sql = "DELETE FROM Turismo WHERE matricula='" + turismo.getMatricula()+ "'";
		String sql2 = "DELETE FROM Vehiculo WHERE matricula='" + turismo.getMatricula()+ "'";

		try {
			connect = Conexion.conectar();
			stm = connect.createStatement();
			stm.execute(sql);
			stm.execute(sql2);
			eliminar = true;
		} catch (SQLException e) {
			System.out.println("Error:  método eliminar");
			e.printStackTrace();
		}
		
		return eliminar;
	}



	public boolean eliminarTodo() throws ClassNotFoundException {
		Connection connect = null;
		Statement stm = null;

		boolean eliminar = false;

		String sql = "DELETE FROM Turismo";
		try {
			connect = Conexion.conectar();
			stm = connect.createStatement();
			stm.execute(sql);
			sql = "DELETE FROM Turismo";
			connect = Conexion.conectar();
			stm = connect.createStatement();
			stm.execute(sql);
			eliminar = true;
		} catch (SQLException e) {
			System.out.println("Error:  método eliminar");
			e.printStackTrace();
		}
		return eliminar;
	}



}