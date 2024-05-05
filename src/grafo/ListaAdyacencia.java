package grafo;

public class ListaAdyacencia {
	Arista primero, ultimo;
	
	public ListaAdyacencia() {
		primero = null;
		ultimo = null;
	}
	
	public boolean listaVacia() {
		return primero ==null;
	}
	
	public void nuevaAdyacencia(Object destino,int tiempoV, int tiempoC, int gastoC, int gastoF, int distancia) {
		if (!adyacente(destino)) {
			Arista nodo = new Arista(destino, tiempoV, tiempoC, gastoC, gastoF, distancia);
			insertar(nodo, destino);
			
		}
	}
	public void nuevaAdyacencia(Object destino, int horaI, int horaF, int por) {
		if (!adyacente(destino)) {
			Arista nodo = new Arista(destino, horaI, horaF, por);
			insertar(nodo, destino);
		}
		
	}

	public void insertar(Arista nodo, Object destino) {
		if(listaVacia()) {
			primero = nodo;
			ultimo = nodo;
		}else {
			if(destino.toString().compareTo(primero.destino.toString())<=0) {
				nodo.siguiente = primero;
				primero = nodo;
			}else {
				if(destino.toString().compareTo(ultimo.destino.toString())>=0) {
					ultimo.siguiente = nodo;
					ultimo = nodo;
				}else {
					Arista posicion = primero;
					//aqui esta el problema S
					while (destino.toString().compareTo(posicion.destino.toString())<=0) {
						posicion = posicion.siguiente;
					}
					nodo.siguiente = posicion.siguiente;
					posicion.siguiente = nodo;
				}
			}
		}
		
	}

	public boolean adyacente(Object dato) {
		Arista actual;
		boolean encontrado;
		encontrado = false;
		actual = primero;
		
		while (actual!= null && !dato.toString().equals(actual.destino.toString())) {
			actual = actual.siguiente;
		}
		if(actual != null) {
			encontrado = true;
		}
		return encontrado;
	}
	
	
	public Arista getPrimero() {
		return primero;
	}

	public String toString() {
		String cadena = "";
		Arista tmp = primero;
		while (tmp != null) {
			if(cadena =="") {			
				cadena = cadena+tmp.destino.toString();
			}else {
				cadena = cadena+" ,"+tmp.destino.toString();				
			}
			tmp = tmp.siguiente;
		}
		return cadena;
		
	}

}
