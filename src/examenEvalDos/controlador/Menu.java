package examenEvalDos.controlador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import examenEvalDos.examen.modelo.Libro;
import metodos.Metodos;



/**
 * Clase de menus.
 */
public class Menu {
	private static final String PATH_FICHERO = "Archivos";
	private static final String NOMBRE_FICHERO = "examen.txt";
	public static final String URL = "jdbc:mysql://localhost:3306/exameneval2";
	public static final String USER = "root";
	public static final String PASS = "";
	
	


	private Scanner teclado = null;

	public Menu() {
		teclado = new Scanner(System.in);
	}

	// --------------------------
	// No tocar - Menus
	// --------------------------

	public void iniciar() {
		int opcion = 0;
		do {
			opcion = opcionMenuInicial();
			if (opcion != 0) {
				ejecutarOpcionMenuInicial(opcion);
			}
		} while (opcion != 0);
	}

	private int opcionMenuInicial() {
		int ret = 0;
		do {
			try {
				escribirMenuInicial();
				System.out.print("Elija una opcion: ");
				ret = teclado.nextInt();
				teclado.nextLine();
			} catch (Exception e) {
				teclado.nextLine();
				ret = -1;
			}
		} while ((ret < 0) && (ret > 8));
		return ret;
	}

	private void escribirMenuInicial() {
		System.out.println(" ");
		System.out.println("---- MENU ----");
		System.out.println("---- 0 - SALIR ");
		System.out.println("---- 1 - Buscar todos los libros ");
		System.out.println("---- 2 - Buscar libro por ID ");
		System.out.println("---- 3 - Buscar todos los libros de un autor ");
		System.out.println("---- 4 - Buscar todos los libros publicados antes de 2020-01-01 ");
		System.out.println("---- 5 - Añadir libro ");
		System.out.println("---- 6 - Guardar todos los libros en un fichero ");
		System.out.println("---- 7 - Mostrar todos los libros del fichero ");
		System.out.println("---- 8 - Insertar todos los libros del fichero en BBDD");
		System.out.println("--------------");
	}

	// --------------------------
	// A modificar por el alumno
	// --------------------------
	Libro[]libros=new Libro[0];
	
	private void ejecutarOpcionMenuInicial(int opcion) {
		System.out.println(" ");
		switch (opcion) {
		case 1: libros=Metodos.cogerLibros(URL, USER, PASS);
		mostrarLibros(libros);
			break;
		case 2:
			System.out.println("Inserte ID");
			int id=teclado.nextInt()-1;
			mostrarLibro(libros[id]);
			break;
		case 3: libros=Metodos.cogerLibros(URL, USER, PASS);
		System.out.println("Inserte Autor");
		String autor=teclado.nextLine();
		for(int i=0;i<libros.length;i++) {
			if(libros[i].getAutor().equals(autor)) {
				mostrarLibro(libros[i]);
			}
		}
			break;
		case 4: //libros=Metodos.cogerLibros(URL, USER, PASS);
		//String fecha="2020-01-01";
		//for(int i=0;i<libros.length;i++) {
			//if(libros[i].getFechaPublicacion()<stringToDate(fecha)) {
				//mostrarLibro(libros[i]);
			//}
	//	}
			break;
		case 5: sqlLibro();
			break;
		case 6: escribirLibros(libros);
			break;
		case 7: txtLibros();
			break;
		case 8: // TODO A responder por el alumno
			break;
		default:
			System.out.println("Esta opcion no deberia salir...");
		}
	}

	// --------------------------
	// No tocar - Pinta datos
	// --------------------------

	/**
	 * Muestra los datos del alumno pasado por parametro
	 */
	private void mostrarLibros(Libro [] libros) {
		if (null != libros) {
			for (Libro libro : libros)
				mostrarLibro(libro);
		} else {
			System.out.println("No hay libros que mostrar");
		}
	}
	
	

	/**
	 * Muestra los datos del alumno pasado por parametro
	 */
	private void mostrarLibro(Libro libro) {
		if (null != libro) {
			System.out.println("-------------------------------------");
			System.out.println("Id - " + libro.getId());
			System.out.println("Titulo - " + libro.getTitulo());
			System.out.println("Autor - " + libro.getAutor());
			System.out.println("Editorial - " + libro.getEditorial());
			System.out.println("Fecha Publicacion - " + dateToString(libro.getFechaPublicacion()));
			System.out.println("-------------------------------------");
		} else {
			System.out.println("No hay informacion que mostrar");
		}
	}
	

	/**
	 * Convierte la fecha en String en fecha Date. Las fechas estan en formato
	 * MM/dd/yyyy
	 * 
	 * @param fecha
	 * @return la fecha en String
	 */
	private String dateToString(Date fecha) {
		String ret = null;
		String pattern = "yyyy/MM/dd";
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		ret = dateFormat.format(fecha);
		return ret;
	}

	// --------------------------
	// No tocar - Pide datos
	// --------------------------

	/**
	 * Solicita por teclado un libro
	 * 
	 * @return el libro
	 */
	private Libro pedirLibro() {
		Libro ret = new Libro();
		System.out.print("Titulo: ");
		ret.setTitulo(teclado.nextLine().trim());
		ret.setAutor(pedirAutor());
		System.out.print("Editorial: ");
		ret.setEditorial(teclado.nextLine().trim());
		System.out.print("Fecha Publicacion: ");
		ret.setFechaPublicacion(stringToDate(teclado.nextLine().trim()));
		return ret;
	}

	/**
	 * Solicita por teclado el id de un libro
	 * 
	 * @return el id
	 */
	private int pedirId() {
		// TODO A responder por el alumno
		return 0;
	}

	/**
	 * Solicita por teclado el autor de un libro
	 * 
	 * @return el autor
	 */
	private String pedirAutor() {
		System.out.print("Autor: ");
		return teclado.nextLine().trim();
	}

	/**
	 * Convierte la fecha en String en fecha Date. Si hay cualquier problema, en
	 * lugar de eso, devuelve la fecha del sistema. Las fechas estan en formato
	 * MM/dd/yyyy
	 * 
	 * @param fecha
	 * @return Date
	 */
	private Date stringToDate(String fecha) {
		Date ret = null;
		try {
			String pattern = "yyyy/MM/dd";
			SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.ENGLISH);
			ret = formatter.parse(fecha);
		} catch (Exception e) {
			// Algo ha ido mal, devolvemos la fecha del sistema
			ret = new Date();
		}
		return ret;
	}
	
	
public void sqlLibro() {
		
		
		Libro libro=pedirLibro();
		
		try {
			
		Connection  conexion = DriverManager.getConnection(URL, USER, PASS);
		Statement sacaLibros = conexion.createStatement();
		String sql = "insert into t_libros VALUES ('"+libro.getTitulo()+"','"+libro.getAutor()+"','"+libro.getEditorial()+"','"+libro.getFechaPublicacion()+"')";
		
		
			} catch(SQLException e) {
			e.printStackTrace();
			}
}
		
		public void sqlAllLibro() {
			
			int cont=0;
			while(cont<libros.length) {
			libros[cont]=pedirLibro();
			
			try {
				
			Connection  conexion = DriverManager.getConnection(URL, USER, PASS);
			Statement sacaLibros = conexion.createStatement();
			String sql = "insert into t_libros VALUES ('"+libros[cont].getTitulo()+"','"+libros[cont].getAutor()+"','"+libros[cont].getEditorial()+"','"+libros[cont].getFechaPublicacion()+"')";
			sacaLibros.executeQuery(sql);
			
				} catch(SQLException e) {
				e.printStackTrace();
	}
			}
}
		
		public void escribirLibros(Libro[]libros) {
			
			
			File file = new File("txt.txt");
			
			BufferedWriter fichero;
			
				try {
					fichero = new BufferedWriter(new FileWriter(file));
				for(int i =0;i<libros.length;i++)
				{
					fichero.write(libros[i].toString());
					fichero.write("----------------------------------");
				}
				fichero.close();
				
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
		}
		
		public void txtLibros(){
			
			
			
				
				
				File file = new File("txt.txt");
				BufferedReader fichero;
				try {
					fichero = new BufferedReader(new FileReader(file));
				
				String linea;
				
				while((linea = fichero.readLine())!=null)
				{
					System.out.println(linea);
						
				}
				
				fichero.close();
				
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
			
			
		}

