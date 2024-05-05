package back;

import java.util.ArrayList;
import java.util.List;

import grafo.Arista;
import grafo.Nodo;

public class PRutas implements Comparable<PRutas>  {
	public Arista primero;
	public Reloj reloj;
	public List <Arista> camino;
	public Grafo demora;
	public Nodo inicial;
	public int	tiempoVmax=0;
	public int tiempoCmax=0;
	public int gastoCmax=0;
	public int gastoFmax=0;
	public int distanciaTotal=0;
	public float rapidezAuto=0;
	public float rapidezAutoD=0;
	public float rapidezCaminando=0;
	
	public int ordenamiento = 0;
	
	
	public PRutas(Nodo inicial, Arista primero, Grafo demora, Reloj reloj) {
		this.inicial = inicial;
		this.demora = demora;
		this.primero = primero;
		this.reloj = reloj;
		crearR();
		calcularValoresI();
		calcularRapidez();
	}
	
	public void crearR() {
		if (primero != null) {
			Arista tmp = primero;
			while(tmp != null) {
				if(camino == null) {
					camino = new ArrayList<>();
				}
				camino.add(tmp);
				tmp = tmp.siguiente;
			}
		}
	}

	public void calcularValoresI() {
		for (Arista arista : camino) {
			tiempoVmax += arista.distancia[0];
			tiempoCmax += arista.distancia[1];
			gastoCmax += arista.distancia[2];
			gastoFmax += arista.distancia[3];
			distanciaTotal += arista.distancia[4];
		}
	}
	
	private void calcularRapidez() {
		// TODO Auto-generated method stub
		this.rapidezCaminando = (float)distanciaTotal/tiempoCmax;
		this.rapidezAuto = (float)distanciaTotal/tiempoVmax;
		Nodo origenT = inicial;
		for (Arista arista : camino) {
			Arista A = null; 
			if(demora !=null) {
				A= demora.bucarDemora(origenT, arista.getDestino());
			}
			if(A !=null) {
				if (reloj.getHora()>A.demora[0] && reloj.getHora()<A.demora[1]) {
					
					rapidezAutoD += arista.distancia[4]/(arista.distancia[0]*(1+(100/A.demora[2])));
				}
			}
			origenT = new Nodo(arista.getDestino());
		}
	}
	
	public void setdemora(Grafo demora) {
		this.demora = demora;
	}

	public int compareTo(PRutas otraRuta) {
		// TODO Auto-generated method stub
		if(ordenamiento == 0 ) {

			if (this.rapidezAuto > otraRuta.distanciaTotal) {
				return-1;
			}
			if (this.rapidezAuto <otraRuta.distanciaTotal) {
				return 1;
			}
		}
		if(ordenamiento == 1) {
			if (this.rapidezAuto > otraRuta.gastoCmax) {
				return-1;
			}
			if (this.rapidezAuto <otraRuta.gastoCmax) {
				return 1;
			}
		}
		if(ordenamiento == 2) {
			if (this.rapidezAuto > otraRuta.gastoFmax) {
				return-1;
			}
			if (this.rapidezAuto <otraRuta.gastoFmax) {
				return 1;
			}
		}
		if(ordenamiento == 3) {
			if ((this.gastoCmax+this.distanciaTotal) > (otraRuta.distanciaTotal + otraRuta.gastoCmax)) {
				return-1;
			}
			if ((this.gastoCmax+this.distanciaTotal) <(otraRuta.distanciaTotal + otraRuta.gastoCmax)) {
				return 1;
			}
		}
		if(ordenamiento == 4) {
			if ((this.gastoFmax+this.distanciaTotal) > (otraRuta.distanciaTotal + otraRuta.gastoFmax)) {
				return-1;
			}
			if ((this.gastoFmax+this.distanciaTotal) <(otraRuta.distanciaTotal + otraRuta.gastoFmax)) {
				return 1;
			}
		}
		return 0;
	}
}
