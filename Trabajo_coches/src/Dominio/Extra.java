package Dominio;

import java.util.ArrayList;

import Persistencia.ExtraDao;

public class Extra {
	
	private int id;
	private String descripcion;
	private ExtraDao extradao;
	
	public Extra(int id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.extradao = new ExtraDao();
	}
	
	public Extra() {
		this.extradao = new ExtraDao();

	}

	public int getid() {
		return id;
	}

	public void setid(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Extras [id=" + id + ", descripcion=" + descripcion + "]";
	}
	
	public void insertar() throws ClassNotFoundException {
		extradao.insertar(this);
	}

	public ArrayList<Extra> leerTodos() throws ClassNotFoundException {
		return extradao.leerTodos();

	}
	public Extra leerExtras(int id) throws ClassNotFoundException {
		return extradao.leer(id);

	}

	public void actualizar( int id) throws ClassNotFoundException {
		extradao.actualizar(this, id);

	}

	public void eliminar() throws ClassNotFoundException {
		extradao.eliminar(this);
	}

	public void eliminarTodo() throws ClassNotFoundException {
		extradao.eliminarTodo();

	}
	
	
	
	

}