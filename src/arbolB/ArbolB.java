package arbolB;
import back.*;

public class ArbolB {
	NodoArbolB raiz;
	int t;// Grado minimo para este proyecto es de 5
	
	public ArbolB(int t){
		this.t = t;
		this.raiz = new NodoArbolB(t);
	}
	
	//insercion absolute
	public void insertar(NodoBInternal I) {
		NodoArbolB r = raiz;
		if(r.n == ((2*t)-1)) {
			NodoArbolB s = new NodoArbolB(t);
			raiz = s;
			s.hoja = false;
			s.n = 0;
			s.hijos[0] = r;
			split(s, 0, r);
			sinDivInsercion(s, I);
		}else {
			sinDivInsercion(r, I);
		}
	}
	
	//insercion cuando hay que buscar entre las hojas
	public void sinDivInsercion(NodoArbolB x, NodoBInternal clave) {
		if (x.hoja) {
			int i  = x.n; // n representa la cantidad de valores guardados
			
			//*********************************************** ingresa en el lugar correcto
			while (i >= 1 && clave.clave < x.clave[i-1].clave) {
				x.clave[i] = x.clave[i-1];
				i--;
			}
			x.clave[i] = clave;
			x.n++;
		}else {
			int j=0;
			while (j < x.n && clave.clave > x.clave[j].clave) {
				j++;
			}
			if(x.hijos[j].n == (2*t-1)) {
				split(x, j, x.hijos[j]);
				if(clave.clave > x.clave[j].clave) {
					j++;
				}
			}
			sinDivInsercion(x.hijos[j], clave);
		}
	}
	
	//dividir nodo+++++++++++++++++++++++++++++++++++++++++++++
	
	private void split(NodoArbolB x, int i, NodoArbolB y) {
		NodoArbolB z = new NodoArbolB(t);
		z.hoja =y.hoja;
		z.n=(t-1);
		for (int j = 0; j < (t-1); j++) {
			z.clave[j] = y.clave[j+t];
		}
		
		if(!y.hoja) {// no es hoja se reasignan los nodos hijos 
			for (int k = 0; k < t; k++) {
				z.hijos[k]= y.hijos[k+t];
				
			}
		}
		
		y.n = (t-1); //como se divide********* los valores son la mitada
		
		for (int j = x.n; j > i ; j--) {//mueve los hijos de x para dar espacio a z
			x.hijos[j+1]= x.hijos[j];
		}
		
		x.hijos[i+1] = z;//reasigna el hijo |i+1| de x
		
		for (int j = x.n; j > i; j--) {//mueve las calves de x
			x.clave[j+1] = x.clave[j];
		}
		
		x.clave[i]= y.clave[t-1];//agrega la clave de la mitad
		x.n++;
	}
	public String verArbol() {
		DibujarArbolB DJB = new DibujarArbolB();
		DJB.DibujarInicial(print(raiz,0));
		return print(raiz, 0);
	}
	
	public String print(NodoArbolB b, int suma) {
		String a = "";
		int c = 1+suma;
		a += "node"+c+ b.imprimir();
		if(!b.hoja) {
			for (int j = 0; j <= b.n; j++) {
				if(b.hijos[j]!=null) {
					int tmp = (c*10)+j;
					int tmp1 = tmp+1;
					a += print(b.hijos[j],tmp);
					a += "node"+c+" -> "+"node"+tmp1+";\n";
				}
				
			}
		}
		return a;
	}
}
