package grafo;

public class Nodo {
		public Object dato;
		public ListaAdyacencia lista;
		public Nodo siguiente;
		
		public Nodo(Object x) {
			this.dato = x;
			lista = new ListaAdyacencia();
			siguiente = null;
		}

}
