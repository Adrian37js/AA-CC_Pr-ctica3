package Persistencia;

import java.util.ArrayList;

import Dominio.Vehiculo;

abstract public class VehiculoDao {
	public VehiculoDao() {
		
	}
	//Abstractas porque no se llamaran desde vehiculo
	abstract public boolean insertar(Vehiculo vehiculo) throws ClassNotFoundException ;
	abstract public ArrayList<Vehiculo> leerTodos() throws ClassNotFoundException;
	abstract public Vehiculo leer(String matricula) throws ClassNotFoundException ;
	abstract public boolean actualizar(Vehiculo vehiculo, String matricula) throws ClassNotFoundException;
	abstract public boolean eliminar(Vehiculo vehiculo) throws ClassNotFoundException ;
	abstract public boolean eliminarTodo() throws ClassNotFoundException;
}