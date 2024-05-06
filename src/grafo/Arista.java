package grafo;

public class Arista {
	Object destino;
	public int[] distancia = new int[5];
	public Arista siguiente;
	public int[] demora = new int [3]; 

	

	public Arista(Object destino, int tiempoV, int tiempoC, int gastoC, int gastoF, int distancia) {
		this.destino = destino;
		this.distancia[0] = tiempoV;
		this.distancia[1] = tiempoC;
		this.distancia[2] = gastoC;
		this.distancia[3] = gastoF;
		this.distancia[4] = distancia;
		siguiente = null;
	}
	
	public Arista(Object destino, int horaI, int horaF, int por) {
		this.destino = destino;
		this.demora[0] = horaI;
		this.demora[1] = horaF;
		this.demora[2] = por;
		siguiente = null;
	}
	
	public Object getDestino() {
		return destino;
	}
	public int getClave() {
		char l[] = destino.toString().toCharArray();
		int clave=0;
		for (char c : l) {
			clave += c;
		}
		return clave;
	}
}
