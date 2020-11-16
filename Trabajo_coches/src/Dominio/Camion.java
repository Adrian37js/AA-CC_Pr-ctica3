package Dominio;

import java.util.ArrayList;


import Persistencia.CamionDao;

public class Camion extends Vehiculo{

	
	private int Carga;
	private CamionDao camiondao;
	
	public Camion(String matricula, String marca, String modelo, String color, double precio, int capacidad_carga) {
		super(matricula, marca, modelo, color, precio);
		this.Carga = capacidad_carga;
		camiondao= new CamionDao();
	}

	public Camion() {
		camiondao=new CamionDao();
	}
	public int getCapacidad_carga() {
		return Carga;
	}

	public void setCapacidad_carga(int capacidad_carga) {
		this.Carga = capacidad_carga;
	}

	@Override
	public String toString() {
		return "Vehiculo [ MATRICULA= " + matricula + ", MARCA= " + marca + ", MODELO= " + modelo + ", COLOR= " + color
				+ ", PRECIO= " + precio + ", CARGA= " + Carga + "]";
	}
	
	public void insertar() throws ClassNotFoundException {
		camiondao.insertar(this);
	}

	public ArrayList<Vehiculo> leerTodos() throws ClassNotFoundException {
		return camiondao.leerTodos();

	}


	public void actualizar(String matricula) throws ClassNotFoundException {
		camiondao.actualizar(this, matricula);

	}

	public void eliminar() throws ClassNotFoundException {
		camiondao.eliminar(this);
	}

	public void eliminarTodo() throws ClassNotFoundException {
		camiondao.eliminarTodo();

	}

	@Override
	public Vehiculo leerVehiculo(String matricula) throws ClassNotFoundException {
		return camiondao.leer(matricula);
	}


	
}