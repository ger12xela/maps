package back;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class DibujarArbolB {
	
	public String DibujarInicial(String t) {
		String texto = "digraph Grafo {\r\n"
				+ "    size=\"60\"\r\n"
				+"		node [shape = circle];\r\n"
				+"  	node [shape = record,height=.1];\r\n"
				+ "";
		texto+=t;
		texto+="\n}";
		
		return dibujarGraph(texto);
	}
	
	void escribirArchivo(String direccion, String contenido) {
		File fichero;
		try {
			fichero = new File("src//front//archivo1.dot");
			if (!fichero.exists()) {
				fichero.createNewFile();
				System.out.println("se creo el archibo");
			}
		} catch (Exception e) {
			// TODO: handle exception
				System.out.println("error al crear archivo");
		}
		
		FileWriter archivo = null;
		PrintWriter pw =null;
		
		try {
			archivo = new FileWriter("src//front//archivo1.dot");
			pw = new PrintWriter(archivo);
			pw.write(contenido);
			pw.close();
			archivo.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		} finally {
			if(pw!=null) {
				pw.close();
			}
		}
	}
	
	String dibujarGraph(String t) {
		String ruta="src//front//grafo1.png";
		try {
			escribirArchivo("archivo1.dot",t);
			ProcessBuilder proceso; //ya tiene un proceso java
			proceso = new ProcessBuilder("dot","-Tpng","-o","grafo1.png","src//front//archivo1.dot");
			proceso.redirectErrorStream(true);
			proceso.start();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ruta;
	}
}
