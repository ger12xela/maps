package back;

public class instruccion {
	public String origen;
	public String destino;
	public int tiempoV;
	public int tiempoC;
	public int gastoC;
	public int gastoF;
	public int distancia;
	
	//para las demoras
	public int horaI;
	public int horaF;
	public int por;
	public instruccion(String origen, String destino, int tiempoV, int tiempoC, 
						int gastoC, int gastoF, int distancia) {
		super();
		this.origen = origen;
		this.destino = destino;
		this.tiempoV = tiempoV;
		this.tiempoC = tiempoC;
		this.gastoC = gastoC;
		this.gastoF = gastoF;
		this.distancia = distancia;
	}
	
	public instruccion(String origen, String destino, int horaI, int horaF, int por ) {
		this.origen = origen;
		this.destino = destino;
		this.horaI = horaI;
		this.horaF = horaF;
		this.por = por;
	}
	public String toString() {
		String intruccion = origen+
				destino+
				tiempoV+
				tiempoC+
				gastoC+
				gastoF+
				distancia;
		return intruccion;
	}
}
