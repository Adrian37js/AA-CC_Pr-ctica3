package Dominio;

import java.util.ArrayList;



import Persistencia.Turismo_extrasDao;

public class Turismo_extras {

    private String matricula;
    private Extra extra;
    private Turismo_extrasDao turismo_extrasDao;



    @Override
	public String toString() {
		return "Turismo_extras [matricula=" + matricula + " "+ extra;
	}

	public Turismo_extras( String matricula, Extra extra) {
        this.matricula = matricula;
        this.extra = extra;
        this.turismo_extrasDao = new Turismo_extrasDao();
    }

    public Turismo_extras() {

        turismo_extrasDao = new Turismo_extrasDao();
    }


    public String getMatricula() {
        return matricula;
    }


    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }


    public Extra getExtra() {
        return extra;
    }


    public void setExtra(Extra extra) {
        this.extra = extra;
    }


  public void insertar() throws ClassNotFoundException {
        turismo_extrasDao.insertar(this);
    }

    public ArrayList<Turismo_extras> leerTodos() throws ClassNotFoundException {
        return turismo_extrasDao.leerTodos();

    }
    public Turismo_extras leerExtras(int id) throws ClassNotFoundException {
        return turismo_extrasDao.leer(id);

    }

   /* public void actualizar(int id) throws ClassNotFoundException {
        turismo_extrasDao.actualizar(this, id);

    }*/

    public void eliminar() throws ClassNotFoundException {
        turismo_extrasDao.eliminar(extra);
    }

    public void eliminarTodo() throws ClassNotFoundException {
        turismo_extrasDao.eliminarTodo();

    }




}