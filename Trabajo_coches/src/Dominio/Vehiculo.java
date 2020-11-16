package Dominio;

import java.util.ArrayList;

import Persistencia.VehiculoDao;

abstract public class Vehiculo {
	
	protected String matricula;
	protected String marca;
	protected String modelo;
	protected String color;
	protected double precio;
	protected VehiculoDao vehiculodao;
	
	public Vehiculo(String matricula, String marca, String modelo, String color, double precio) {
			
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.color = color;
		this.precio = precio;
		
	}

	public Vehiculo() {
		
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Vehiculo [ MATRICULA= " + matricula + ", MARCA= " + marca + ", MODELO= " + modelo + ", COLOR= " + color
				+ ", PRECIO =" + precio + " ]";
	}
	
	abstract public void insertar() throws ClassNotFoundException ;

	abstract public ArrayList<Vehiculo> leerTodos() throws ClassNotFoundException ;
	
	abstract public Vehiculo leerVehiculo(String matricula) throws ClassNotFoundException;
	

	abstract public void actualizar(String matricula) throws ClassNotFoundException ;


	abstract public void eliminar() throws ClassNotFoundException;

abstract public void eliminarTodo() throws ClassNotFoundException;

}
//	