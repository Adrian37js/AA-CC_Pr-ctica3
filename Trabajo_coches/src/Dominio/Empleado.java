package Dominio;

import java.sql.SQLDataException;
import java.util.ArrayList;

import Persistencia.EmpleadoDao;

public class Empleado {
	
	private String usuario;
	private String password;
	private EmpleadoDao empleadodao;

	
	public Empleado(String usuario, String password) {
		this.usuario = usuario;
		this.password = password;
		this.empleadodao= new EmpleadoDao();
	}
	
	public Empleado() {
		this.empleadodao = new EmpleadoDao();

	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Empleado [usuario=" + usuario + ", password=" + password + "]";
	}
	

	public boolean leerEmpleados(String usuario, String password) throws ClassNotFoundException, SQLDataException {
		
		return empleadodao.leerLogin(usuario, password);

	}
	
	
	public  ArrayList<Empleado>  leerTodos() throws ClassNotFoundException {
		return empleadodao.leerTodos();

	}

}
