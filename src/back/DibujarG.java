package back;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class DibujarG {
	
	public String DibujarInicial(String t) {
		String texto = "digraph Grafo {\r\n"
				+ "    size=\"60\"\r\n"
				+"		node [shape = circle];\r\n"
				+ "";
		texto+=t;
		texto+="\n}";
		
		return dibujarGraph(texto);
	}
	
	void escribirArchivo(String direccion, String contenido) {
		File fichero;
		try {
			fichero = new File("src//front//archivo.dot");
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
			archivo = new FileWriter("src//front//archivo.dot");
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
		String ruta="src//front//grafo.png";
		try {
			escribirArchivo("archivo.dot",t);
			ProcessBuilder proceso; //ya tiene un proceso java
			proceso = new ProcessBuilder("dot","-Tpng","-o","grafo.png","src//front//archivo.dot");
			proceso.redirectErrorStream(true);
			proceso.start();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ruta;
	}

}
