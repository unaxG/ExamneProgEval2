package metodos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import examenEvalDos.controlador.Menu;
import examenEvalDos.examen.modelo.Libro;

public class Metodos {

	
	
	public boolean idVal(int id) {
		
		boolean resultado=false;
		
		if(id>=1&&id<=4) {
			resultado=true;
		}
		
		return resultado;
	}
		
	public static Libro[] cogerLibros(String URL, String USER, String PASS) {
		
		int i=0;
		Libro [] guardaLibros =new Libro[0];
		
		
		try {
			
		Connection  conexion = DriverManager.getConnection(URL, USER, PASS);
		Statement sacaLibros = conexion.createStatement();
		String sql = "select * from t_libros";
		ResultSet cargaLibros = sacaLibros.executeQuery(sql);
		
		
		while (cargaLibros.next()) {
			Libro [] librosA= new Libro[i+1];
			int cont=0;
			while(cont<i){
				
				librosA[cont]=guardaLibros[cont];
				
				cont++;
			}
			
			Libro libro=new Libro();
			
			libro.setId(cargaLibros.getInt(1));
			libro.setTitulo(cargaLibros.getString(2));
			libro.setAutor(cargaLibros.getString(3));
			libro.setEditorial(cargaLibros.getString(4));
			libro.setFechaPublicacion(cargaLibros.getDate(5));
			
			
			
			librosA [i]=libro;
			
			i++;
			
			guardaLibros=librosA;
			
		}
		
		
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return guardaLibros;
		
			
		
	}
	

	
	
	
}
