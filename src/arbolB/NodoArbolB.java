package arbolB;

public class NodoArbolB {
	int n;// las claves en pagina n-1
	boolean hoja; // si es hoja ?
	NodoBInternal clave[]; //claves
	NodoArbolB hijos[]; //hijos del nodo 
	
	public NodoArbolB(int t) {
		this.n = 0;
		this.hoja = true;
		this.clave = new NodoBInternal [(2*t)-1];
		this.hijos = new NodoArbolB[2*t];
	}
	
    public String imprimir() {
        String r = "[label =  \"";
        for (int i = 0; i < n; i++) {
            if (i < n - 1) {
                r += clave[i].nombre + " | ";
            } else {
                r += clave[i].nombre;
            }
        }
        r += " \"];\n";
        return r;
    }
}