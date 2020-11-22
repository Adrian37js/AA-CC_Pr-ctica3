package Dominio;

import java.util.ArrayList;

import Persistencia.TurismoDao;

public class Turismo extends Vehiculo {
	private int num_puertas;
	private TurismoDao turismodao;

	public Turismo(String matricula, String marca, String modelo, String color, double precio, int num_puertas) {
		super(matricula, marca, modelo, color, precio);
		this.num_puertas = num_puertas;
		this.turismodao= new TurismoDao();
	}

	public Turismo() {

		turismodao = new TurismoDao();
	}

	public int getNum_puertas() {
		return num_puertas;
	}

	public void setNum_puertas(int num_puertas) {
		this.num_puertas = num_puertas;
	}


	@Override
	public String toString() {
		return "Vehiculo [MATRICULA= " + matricula + ", MARCA= " + marca + ", MODELO= " + modelo + ", COLOR= " + color
				+ ", PRECIO= " + precio + ", PUERTAS= " + num_puertas;

	}

	public void insertar() throws ClassNotFoundException {
		turismodao.insertar(this);
	}

	public ArrayList<Vehiculo> leerTodos() throws ClassNotFoundException {
		return turismodao.leerTodos();

	}


	public Vehiculo leerVehiculo(String matricula) throws ClassNotFoundException {
		return turismodao.leer(matricula);

	}

	public void actualizar(String matricula) throws ClassNotFoundException {
		turismodao.actualizar(this, matricula);

	}

	public void eliminar() throws ClassNotFoundException {
		turismodao.eliminar(this);
	}

	public void eliminarTodo() throws ClassNotFoundException {
		turismodao.eliminarTodo();

	}
}