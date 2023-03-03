package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import examenEvalDos.controlador.Menu;
import examenEvalDos.examen.modelo.Libro;
import metodos.Metodos;

class TodosLibrosTest {
	
@Test
	
	void test() {
	Libro[]libros=Metodos.cogerLibros(Menu.URL, Menu.USER, Menu.PASS);
	assertEquals(libros[0].getAutor(),"Juan Torres");
	
	
	
}
	
}
