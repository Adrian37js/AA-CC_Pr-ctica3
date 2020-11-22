package Presentacion;

import java.io.IOException;
import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Dominio.*;


class Main {

	public static void main(String[] args) throws ClassNotFoundException, IOException, SQLDataException {
		
		
		Scanner sc = new Scanner(System.in);


		int opcion = 0;
		boolean registro = false;
		String usuario;
		String contraseña;
		//Registro
		do{
		TodoEmpleados();
		System.out.println("Bienvenido al programa de gestion de vehículos");				//solictamos lel usuario y contraseña
		System.out.println("Introduzca su usuario y contraseña");
		usuario = sc.next();
		contraseña = sc.next();
		
		registro = login(usuario, contraseña);												//comprobamos si está en la bbdd
		}while(registro == false);
	
		//Vehiculo tu_e = new Turismo();
		//ArrayList<Vehiculo> t_e = tu_e.leerTodos();	

		System.out.println("____Bienvenido____");
		System.out.println("-------------------------------");
		

																									//Menú 
		do {
			try {
				System.out.println("\nElija que opción desea realizar.");
				System.out.println("Menú");
				System.out.println("1. Mostrar todos los vehículos");
				System.out.println("2. Buscar una vehiculo");
				System.out.println("3. Añadir un vehículo.");
				System.out.println("4. Modificar un vehículo.");
				System.out.println("5. Eliminar un vehiculo");
				System.out.println("6. Eliminar todos los vehículos");
				System.out.println("7. Mostrar todos los extras");
				System.out.println("8. Buscar un extra");
				System.out.println("9. Añadir un extra");
				System.out.println("10. Modificar un extra");
				System.out.println("11. Eliminar un extra");
				System.out.println("12. Eliminar todos los extras");
				System.out.println("13. Log out");
				opcion = sc.nextInt();

				switch (opcion) {
				case 1:
					mostrarTodos();
					break;
				case 2:
					mostrarTodos();
					buscarVehiculo();
					break;
				case 3:
					insertarVehiculo();
					break;
				case 4:
					mostrarTodos();
					modificarVehiculo();
					break;
				case 5:
					mostrarTodos();
					eliminarVehiculo();
					break;
				case 6:
					eliminarTodoslosVehiculos();
					break;
				case 7:
					mostrarTodosLosExtras();
					break;
				case 8:
					mostrarTodosLosExtras();
					buscarExtra();
					break;
				case 9:
					
					insertarExtra();
					break;
				case 10:
					mostrarTodosLosExtras();
					modificarExtra();
					
					break;
				case 11:
					mostrarTodosLosExtras();
					eliminarExtra();
					
					break;
				case 12:
					eliminarTodosExtras();
					
					break;
				case 13:
					System.out.println("Ciao");
					break;
				default:
					System.out.println("Introduce un número de 1 a 13");
				}

			} catch (InputMismatchException e) {
				System.err.println("Introduce un número");
				sc.nextLine();
			}
		} while (opcion != 13);
	}

	//Mostramos todos
	public static void mostrarTodos() throws ClassNotFoundException {							//Mostramos todos los vehiculos
		boolean NoVehiculos = true;
		
		Vehiculo Vehiculo1 = new Turismo();
		ArrayList<Vehiculo> vehiculos = Vehiculo1.leerTodos();	
		
		Turismo_extras t_e = new Turismo_extras();
		ArrayList<Turismo_extras> t_es = t_e.leerTodos();	
		
		//Lo guardamos en Arraylist
		for (int i = 0; i < vehiculos.size(); i++) {			
			//Comprobamos los Turismos
			NoVehiculos = false;			
			System.out.println(vehiculos.get(i).toString());//Comprobamos los Turismos

			
			for (int a = 0; a < t_es.size(); a++) {		
			
				if(vehiculos.get(i).getMatricula().equals(t_es.get(a).getMatricula()))
				{
					System.out.println(t_es.get(a).toString());
					
				}
				
			}
			System.out.println("\n");

		}
		

		
		Vehiculo Vehiculo2 = new Camion();
		vehiculos = Vehiculo2.leerTodos();
		
		for (int i = 0; i < vehiculos.size(); i++) {											//Comprobamos los camiones
			System.out.println(vehiculos.get(i).toString());
			NoVehiculos = false;
		}
		if (NoVehiculos) {
			System.out.println("No hay vehiculos en la Base de Datos");

		}
	}

	//Buscmaos vehiculos
	public static void buscarVehiculo() throws ClassNotFoundException {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("\nIndique la matricula:");
		String matricula = sc.next();
																						//Leemos los Turismos
		Vehiculo leerTurismo = new Turismo();
		leerTurismo = leerTurismo.leerVehiculo(matricula);
																						//Leemos los camiones 
		Vehiculo leerCamion = new Camion();
		leerCamion = leerCamion.leerVehiculo(matricula);
		
		Turismo_extras t_e = new Turismo_extras();
		ArrayList<Turismo_extras> t_es = t_e.leerTodos();	
		

																						//Lo buscamos
		if (leerTurismo != null)
		{
			System.out.println(leerTurismo.toString());
			for (int a = 0; a < t_es.size(); a++) {		
				if(leerTurismo.getMatricula().equals(t_es.get(a).getMatricula()))
				{
					System.out.println(t_es.get(a).toString());
				}
			}		
			
			} else if (leerCamion != null) 
		{
			System.out.println(leerCamion.toString());
		} else 
		{
			System.out.printf("No existe ningun vehiculo con la matricula %s\n", matricula);
		}
	}
	
	
	//Insertamos vehiculo
	public static void insertarVehiculo() throws IOException, ClassNotFoundException {
		Scanner sc = new Scanner(System.in);
		boolean seguir = false;
		
		String matricula = "";
		
		Extra newExtra = new Extra();
		
		do {
			seguir = false;
			System.out.print("Introduzca la matricula: ");								//Solicitamos la matricula
			matricula = sc.next();
			Vehiculo Turismo1 = new Turismo();
			Turismo1 = Turismo1.leerVehiculo(matricula);								//Lo buscamos si ya existe algún vehiculo
			
			Vehiculo Camion1 = new Camion();
			Camion1 = Camion1.leerVehiculo(matricula);
			if (Turismo1 != null) {

				if (Turismo1.getMatricula().equals(matricula)) {
					System.err.println("Esta matricula conincide con otro Turismo");
					seguir = true;
				}
			}

			if (Camion1 != null) {

				if (Camion1.getMatricula().equals(matricula)) {
					System.err.println("Esta matricula conincide con otro Camión");
					seguir = true;
				}
			}
		} while (seguir);																//Solicitamos datos
		System.out.println("Introduzca la Marca: ");
		sc.nextLine();
		
		String marca = sc.nextLine();
		System.out.println("Introduzca el Modelo: ");
		String modelo = sc.nextLine();
		
		System.out.println("Introduzca el color: ");
		String color = sc.nextLine();
		double precio = 0;
		do {
			seguir = false;
			try {
				
				System.out.println("Introduzca el precio: ");
				precio = sc.nextDouble();
			} catch (InputMismatchException e) {
				
				System.err.println("Introduzca solo números. Recuerde utilice COMA no PUNTO");
				seguir = true;
				sc.nextLine();
			}
		} while (seguir);
		int opcion = 0;
		do {
			try {
				System.out.println("¿Es un Turismo o un Camión?\n1. Turismo\n2. Camion");			//Aqui preguntamos si va a ser un camion o vehiculo
				opcion = sc.nextInt();
				
			} catch (InputMismatchException e) {
				
				System.err.println("Introduzca una de las dos opciones");
				sc.nextLine();
			}
		} while (opcion != 1 && opcion != 2);
		int extra = 0;
		if (opcion == 1) {																			//Aqui depende lo que haya seleeccionado...

			int num_puertas = 0;
			
			do {
				seguir = false;
				try {
					
					System.out.println("Introduzca el número de puertas: ");
					num_puertas = sc.nextInt();
				} catch (InputMismatchException e) {
					
					System.err.println("Introduzca solo números");
					seguir = true;
					sc.nextLine();
				}
			} while (seguir);

			do {
				seguir = false;
				try {
					int id_extra = 0;

					Vehiculo newVehiculo = new Turismo(matricula, marca, modelo, color, precio, num_puertas);
					newVehiculo.insertar();
					
					mostrarTodosLosExtras();
					System.out.println("En los Turismos es obligatorio un extra como mínimo.");
					
					System.out.println("Cuantos extras quieres añadir a tu Turismo?");
					int cantidad = sc.nextInt();
					for(int i = 0; i <cantidad; i++) {
						
						System.out.println("Introduzca un extra: ");
						id_extra = sc.nextInt();
						
						newExtra = new Extra();
						
						newExtra = newExtra.leerExtras(id_extra);
						
						if (newExtra == null) {
							seguir = true;
							System.err.println("No disponemos de ese extra");

						}
						
						Turismo_extras turismo_extras=new Turismo_extras(matricula, newExtra);
	                    turismo_extras.insertar();
						
					}
					

					
				} catch (InputMismatchException e) {
					System.err.println("Introduzca solo números");
					seguir = true;
					sc.nextLine();
					
				}
			} while (seguir);
			
			//Vehiculo newVehiculo = new Turismo(matricula, marca, modelo, color, precio, num_puertas, newExtra);
			//newVehiculo.insertar();

		}
		int capacidad_carga = 0;
		
		if (opcion == 2) {														//Camion

			do {
				seguir = false;
				try {
					System.out.println("Introduzca la capacidad de carga: ");
					capacidad_carga = sc.nextInt();
					
				} catch (InputMismatchException e) {
					System.err.println("Introduzca solo números: ");
					seguir = true;
					sc.nextLine();
				}
			} while (seguir);
			Vehiculo newVehiculo = new Camion(matricula, marca, modelo, color, precio, capacidad_carga);	//Se crea el camión
			newVehiculo.insertar();
		}

	}
	
	//Modificamos un vehiculo
	public static void modificarVehiculo() throws IOException, ClassNotFoundException {
		Scanner sc = new Scanner(System.in);
		Vehiculo modTurismo = new Turismo();
		Vehiculo modCamion = new Camion();

		System.out.println("Indica la matricula");
		String matricula = sc.next();
																											//Buscamos la matricula en mabos tipos de coches
		modTurismo = modTurismo.leerVehiculo(matricula);
		modCamion = modCamion.leerVehiculo(matricula);

		if (modTurismo != null || modCamion != null) {
			System.out.println("¿Qué desea modificar?");
			System.out.println("1. Matricula");
			System.out.println("2. Marca");
			System.out.println("3. Modelo");
			System.out.println("4. Color");
			System.out.println("5. Precio");

			if (modTurismo != null) {
				System.out.println("6. Extra");
			}
			else if (modCamion != null) {
				System.out.println("6. Capacidad de carga");
			}
			
			int opcion = sc.nextInt();
			switch (opcion) {
			case 1:
				boolean repetido = false;
				do {
					repetido = false;
					System.out.println("Introduzca la nueva Matricula: ");								//Comprobamos matricula
					String newmatricula = sc.next();
					
					Vehiculo existeTurismo = new Turismo();
					existeTurismo = existeTurismo.leerVehiculo(newmatricula);
					
					Vehiculo existeCamion = new Camion();
					existeCamion = existeCamion.leerVehiculo(matricula);
					
					if (existeTurismo != null) {
						if (existeTurismo.getMatricula().equals(matricula)) {
							System.err.println("Matricula repetida");
							repetido = true;
						}
					} else if (existeCamion != null) {
						if (existeCamion.getMatricula().equals(matricula)) {
							System.err.println("Matricula repetida");
							repetido = true;
						}
					} else {

						if (modTurismo != null) {
							modTurismo.setMatricula(matricula);
						}
						else if (modCamion != null) {
							modCamion.setMatricula(matricula);
						}
					}
				} while (repetido);
				break;
			case 2:
				System.out.println("Introduzca la nueva marca: ");										//Pedimos marca
				sc.nextLine();
				String marca = sc.nextLine();
				if (modTurismo != null) {
					modTurismo.setMarca(marca);
				}
				if (modCamion != null) {
					modCamion.setMarca(marca);
				}
				break;
			case 3:
				System.out.println("Introduzca el nuevo modelo: ");										//Pedimos modelo
				sc.nextLine();
				String modelo = sc.nextLine();
				if (modTurismo != null) {
					modTurismo.setModelo(modelo);
				}
				else if (modCamion != null) {
					modCamion.setModelo(modelo);
				}
				break;
			case 4:
				System.out.println("Introduzca el nuevo color: ");										//Pedimos color
				sc.nextLine();
				String color = sc.nextLine();
				if (modTurismo != null) {
					modTurismo.setColor(color);
				}
				else if (modCamion != null) {
					modCamion.setColor(color);
				}
				break;
			case 5:
				boolean seguir = false;
				do {
					seguir = false;
					try {
						System.out.println("Introduzca el nuevo precio: ");								//Pedimos el precio
						int precio = sc.nextInt();
						if (modTurismo != null) {
							modTurismo.setPrecio(precio);
						}
						if (modCamion != null) {
							modCamion.setPrecio(precio);
						}

					} catch (InputMismatchException e) {
						System.err.println("Introduzca solo números");
						sc.nextLine();
						seguir = true;
					}
				} while (seguir);
				break;
			case 6:
				if (modTurismo != null) {
					seguir = false;
					do {
						seguir = false;
						try {
							mostrarTodosLosExtras();
							System.out.println("En los Turismos es obligatorio un extra.");
							System.out.println("Introduce el nuevo extra: ");							//Extras
							int extra = sc.nextInt();
							Extra modExtra = new Extra();
							modExtra = modExtra.leerExtras(extra);
							if (modExtra == null) {
								seguir = true;
								System.out.println("No disponemos de ese extra");

							} else {
								//((Turismo) modTurismo).setExtras(modExtra);
							}
						} catch (InputMismatchException e) {
							System.err.println("Introduzca solo números");							
							sc.nextLine();			
							seguir = true;
						}
					} while (seguir);
				}					
				if (modCamion != null) {
					seguir = false;					
					do {				
						seguir = false;	
						try {
							System.out.println("Introduzca la nueva capacidad de carga");
							int capacidad_carga = sc.nextInt();
							((Camion) modCamion).setCapacidad_carga(capacidad_carga);
								
							} catch (InputMismatchException e) {
							System.err.println("Introduzce solo números");
							sc.nextLine();
							seguir = true;
						}	
					} while (seguir);	
				}
				break;									

			}
			if (modTurismo != null) {
				modTurismo.actualizar(matricula);

			} else if (modCamion != null) {
				modCamion.actualizar(matricula);

			}

		} else {
			System.out.printf("No tenemos un vehiculo con esa matricula %s\n", matricula);						//Si la matricula está repetida

		}

	}
	
	//Eliminamos un vehiculo
	public static void eliminarVehiculo() throws IOException, ClassNotFoundException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Indica la Matricula");		//Solicitamos l matricula
		String matricula = sc.next();
		
		Vehiculo delTurismo = new Turismo();
		delTurismo = delTurismo.leerVehiculo(matricula);
																						//comprobamos y eliminamos
		Vehiculo delCamion = new Camion();
		delCamion = delCamion.leerVehiculo(matricula);
		
		if (delTurismo != null) {
			delTurismo.eliminar();
		} else if (delCamion != null) {
			delCamion.eliminar();
		} else {
			System.out.printf("No existe el vehiculo con la matricula %s\n", matricula);
		}
	}
	
	//Eliminamos todos los vehicculos de la base de datos
	public static void eliminarTodoslosVehiculos() throws ClassNotFoundException {
		Vehiculo eliminarTodosVehiculos = new Turismo();
		eliminarTodosVehiculos.eliminarTodo();
		eliminarTodosVehiculos = new Camion();
		eliminarTodosVehiculos.eliminarTodo();
	}
	
	//Mostramos todos los extras
	public static void mostrarTodosLosExtras() throws ClassNotFoundException {
		boolean sinExtras = true;
		Extra buscarExtras = new Extra();
		ArrayList<Extra> extra = buscarExtras.leerTodos();
		for (int i = 0; i < extra.size(); i++) {
			System.out.println(extra.get(i).toString());
			sinExtras = false;
		}

		if (sinExtras) {
			System.out.println("No hay extras");

		}
	}
	
	//Buscamos si hay una extra
	public static void buscarExtra() throws ClassNotFoundException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca el id: ");
		int id = sc.nextInt();
		
		Extra leerExtra = new Extra();
		leerExtra = leerExtra.leerExtras(id);

		if (leerExtra != null) {
			System.out.println(leerExtra.toString());
		} else {
			System.out.printf("No existe el extra con el id %d\n", id);
		}
	}
	
	//Insertamos un extra
	public static void insertarExtra() throws ClassNotFoundException {
		Scanner sc = new Scanner(System.in);

		boolean seguir = false;
		int id = 0;
		do {
			seguir = false;
			System.out.println("Introduzca el id");
			id = sc.nextInt();
			
			Extra existeExtra = new Extra();
			existeExtra = existeExtra.leerExtras(id);

			if (existeExtra != null) {

				if (existeExtra.getid() == id) {
					System.out.println("Id repetido");
					seguir = true;
				}
			}

		} while (seguir);

		System.out.println("Introduzca la descripcion");
		sc.nextLine();
		String descripcion = sc.nextLine();

		Extra newExtra = new Extra(id, descripcion);
		newExtra.insertar();
	}
	
	//modificamos un extra
	public static void modificarExtra() throws ClassNotFoundException {
		Scanner sc = new Scanner(System.in);

		Extra modExtra = new Extra();

		System.out.println("Indica el Id");
		int identificador = sc.nextInt();
		modExtra = modExtra.leerExtras(identificador);

		if (modExtra != null) {
			System.out.println("¿Qué deseas modificar?");
			System.out.println("1. Id");
			System.out.println("2. Descripción");
			int opcion = sc.nextInt();
			switch (opcion) {
			case 1:
				boolean repetido = false;
				do {
					repetido = false;
					System.out.println("Introduce el nuevo Id");
					int id = sc.nextInt();
					Extra existeExtra = new Extra();
					existeExtra = existeExtra.leerExtras(id);

					if (existeExtra != null) {
						if (existeExtra.getid() == id) {
							System.out.println("Id repetido");
							repetido = true;
						}
					} else {

						modExtra.setid(id);
					}
				} while (repetido);
				break;
			case 2:
				System.out.println("Introduce la descripcion");
				sc.nextLine();
				String descripcion = sc.nextLine();
				modExtra.setDescripcion(descripcion);

				break;

			}
			modExtra.actualizar(identificador);
		} else {
			System.out.printf("No existe el extra con el Id %d\n", identificador);

		}

	}

	//Eliminamos un extra
	public static void eliminarExtra() throws ClassNotFoundException {
		Scanner sc = new Scanner(System.in);

		System.out.println("Indica el Id");
		int id = sc.nextInt();
		Extra delExtras = new Extra();
		delExtras = delExtras.leerExtras(id);

		if (delExtras != null) {
			delExtras.eliminar();

		} else {
			System.out.printf("No existe el Extra con el Id %d\n", id);
		}

	}

	//Elminamos todos los extras
	private static void eliminarTodosExtras() throws ClassNotFoundException {
		Extra eliminarTodosExtras = new Extra();
		eliminarTodosExtras.eliminarTodo();
	}
	
	//Login
	private static boolean  login(String usuario, String contraseña) throws ClassNotFoundException, SQLDataException {
		
		Empleado empleado1 = new Empleado();

		return empleado1.leerEmpleados(usuario, contraseña);

	}
	
	public static void TodoEmpleados() throws ClassNotFoundException {
		boolean emp = true;
		Empleado busca_emp = new Empleado();
		ArrayList<Empleado> empleados = busca_emp.leerTodos();
		for (int i = 0; i < empleados.size(); i++) {
			System.out.println(empleados.get(i).toString());
			emp = false;
		}
		if (emp) {
			System.out.println("no hay empleados xdd");

		}
	}








}