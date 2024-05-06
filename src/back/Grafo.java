package back;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import grafo.Arista;
import grafo.Nodo;
import front.Principal;

public class Grafo {
	
	List<PRutas> posiblesRutas;// guarda las rutas encontradas***************************************
	Nodo origenI;
	
	Nodo primero;
	Nodo ultimo;
	Grafo demoras;
	Principal prn; 
	
	public Grafo(Principal prn) {
		this.prn =prn;
		primero = null;
		ultimo = null;
		demoras = null;
	}
	public void agregarPrincipal(Principal prn) {
		this.prn = prn;
	}
	
	public void agregarDemora(Grafo demora) {
		this.demoras = demora;
	}
	public boolean grafoVacio() {
		return primero == null;
	}
	
	public boolean existeVertice(Object dato) {
		boolean existe = false;
		if (!grafoVacio()) {
			Nodo temporal = primero;
			while (temporal !=null && !existe) {
				if (temporal.dato.toString().equals(dato.toString())) {
					existe = true;
				}
				temporal = temporal.siguiente;
			}
		}
		return existe;
	}
	
	public void NuevaArista (Object origen, Object destino, int tiempoV, int tiempoC, 
			int gastoC, int gastoF, int distanci) {
		
		if (existeVertice(origen) && existeVertice(destino)) {
			Nodo posicion = primero;
			while(!posicion.dato.equals(origen.toString())) {
				posicion = posicion.siguiente;
			}
			posicion.lista.nuevaAdyacencia(destino, tiempoV, tiempoC, gastoC, gastoF, distanci);
		}
		
	}
	
	public void NuevaArista (Object origen, Object destino, int horaI, int horaF, int por) {
		if (existeVertice(origen) && existeVertice(destino)) {
			Nodo posicion = primero;
			while(!posicion.dato.equals(origen.toString())) {
				posicion = posicion.siguiente;
			}
			posicion.lista.nuevaAdyacencia(destino, horaI, horaF, por);
		}
	}
	
	public void nuevoNodo (Object dato) {
		if(!existeVertice(dato)) {
			Nodo nuevoNodo = new Nodo(dato);
			if (grafoVacio()) {
				primero = nuevoNodo;
				ultimo =nuevoNodo;
			}else {
				if(dato.toString().compareTo(primero.dato.toString())<=0) {
					nuevoNodo.siguiente = primero;
					primero = nuevoNodo;
				}else {
					if (dato.toString().compareTo(ultimo.dato.toString())>=0) {
						ultimo.siguiente = nuevoNodo;
						ultimo = nuevoNodo;
					}else {
						Nodo tmp = primero;
						while (dato.toString().compareTo(tmp.dato.toString())<=0) {
							tmp = tmp.siguiente;
						}
						nuevoNodo.siguiente = tmp.siguiente;
						tmp.siguiente = nuevoNodo;
					}
				}
			}
			
		}
	}
	
	public List<PRutas> getCaminos(){
		return posiblesRutas;
	}
	public void setCaminos(List<PRutas> posiblesRutas) {
		this.posiblesRutas = posiblesRutas;
	}

	public void encontarRutas(Arista llevar, Object origen, Object destino, List<Nodo> listR) {
		if(existeVertice(origen) && existeVertice(destino)){
			if (listR == null) {
				listR = new ArrayList<Nodo>();
				listR.add(new Nodo(origen));
				this.origenI = new Nodo(origen);
				this.posiblesRutas = null;
				
			}else {
				listR.add(new Nodo(origen));
			}
			
			//busca en todos los nodos el origen
			Nodo posicion = primero;
			while (!posicion.dato.equals(origen.toString())) {
				posicion = posicion.siguiente;
			}
			
			if(posicion.dato.equals(destino.toString())) {
				//llego al final ********************************************************
				listR.remove(listR.size()-1);
//				if(llevar!=null) {
//					Arista tpm = llevar;
//					while (tpm !=null) {
//						System.out.print(tpm.getDestino()+"-->");
//						tpm= tpm.siguiente;
//					}
//					System.out.println("");
//				}
				if(posiblesRutas == null) {
					if (llevar!=null) {
						posiblesRutas = new ArrayList<>();
					}
				}
				if(llevar != null) {
					
				posiblesRutas.add(new PRutas(origenI, llevar, demoras, prn.getReloj()));
				}
				
			}else {
				Arista tmppos = posicion.lista.getPrimero();
				while(tmppos!= null) {
					boolean check = true; //vandera para verificar que no a pasado por ese camino.
					
					for (Nodo nodo : listR) {
						if(tmppos.getDestino().toString().equals(nodo.dato.toString())) {
							check = false;
						}
					}
					Arista camino = llevar;
					if(check) {

						if (llevar==null) {
							llevar =  new Arista(tmppos.getDestino(),tmppos.distancia[0],tmppos.distancia[1],tmppos.distancia[2],
									tmppos.distancia[3],tmppos.distancia[4]);
						}else {
							camino = llevar;
							while (camino.siguiente !=null) {
								camino= camino.siguiente;
							}
							camino.siguiente = new Arista(tmppos.getDestino(),tmppos.distancia[0],tmppos.distancia[1],tmppos.distancia[2],
									tmppos.distancia[3],tmppos.distancia[4]);
							
						}
						encontarRutas(llevar, tmppos.getDestino(), destino, listR);
						if(camino!=null) {
							camino.siguiente=null;					
						}
					}
					
					tmppos = tmppos.siguiente; 
				}				
				if(listR != null) {
					if(listR.size() > 0) {
						listR.remove(listR.size() -1);
					}				
				}
			}
		}
		
		if(posiblesRutas !=null) {
			Collections.sort(posiblesRutas);
		}
	}
	
	public Arista bucarDemora(Object origen, Object destino) {
		Arista a = null;
		if(existeVertice(origen) && existeVertice(destino)) {
			Nodo tmp = primero;
			while (!tmp.dato.equals(origen.toString())) {
				tmp = tmp.siguiente;
			}
			Arista tmpA = tmp.lista.getPrimero();
			while (!tmpA.getDestino().equals(destino.toString())) {
				tmpA = tmpA.siguiente;
			}
			if(tmp.dato.equals(origen.toString())&&tmpA.getDestino().equals(destino.toString())) {
				a = tmpA;
			}
		}
		
		return a;
	}
	
	public List<String> listaNodos(){
		List<String> lista = new ArrayList<>();
		if(primero!=null) {
			Nodo tmp = primero;
			while(tmp != null) {
				lista.add(tmp.dato.toString());
				tmp = tmp.siguiente;
			}
		}
		return lista;
	}
	
	public String toString() {
		
		String cadena= "";
		Nodo tmp = primero;
		while (tmp !=null) {
			if(tmp.lista.toString()=="") {
				cadena = cadena + tmp.dato.toString()+";\n";
			}else {
				cadena = cadena +tmp.dato.toString()+"->"+tmp.lista.toString()+";\n";
			}
			tmp = tmp.siguiente;
		}
		return cadena;
	}
	
	public String mejorR(int index) {
		String nuevo = "";
		if(index < 0) {
			index = 0;
		}
		if(posiblesRutas != null) {
			nuevo = posiblesRutas.get(index).inicial.dato.toString();

			for (Arista p : posiblesRutas.get(index).camino) {
				nuevo += ","+p.getDestino().toString();
			}
			nuevo+="[color=red]";
		}
		return nuevo;
	}
}
