package back;

import java.util.Calendar;

import javax.swing.JLabel;

public class Reloj extends Thread  {
	
	Calendar calendario ;	
	int hora = 0;
	int minutos = 0;
	int segundos = 0;
	
	boolean avanzar = true;
	
	JLabel texto;
	
	public Reloj (JLabel muestra) {
		texto = muestra;
		
	}
	
	public void comenzar() {
		this.start();
	}
	public void run() {
		while (avanzar) {
			calendario = Calendar.getInstance();
			hora = calendario.get(Calendar.HOUR_OF_DAY);
			minutos = calendario.get(Calendar.MINUTE);
			segundos = calendario.get(Calendar.SECOND);
			texto.setText(hora+":"+minutos+":"+segundos);
		}
	}
	
	public int getHora() {
		return hora;
	}
	
	public void setHora(int hora) {
		this.hora = hora;
	}
	
	public void detener(boolean avanzar) {
		this.avanzar = avanzar;
	}
	

}
