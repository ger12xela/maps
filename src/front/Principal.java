package front;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import back.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

public class Principal extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	//agregando botones 
	public static final String CARGAR_RUTAS = "CARGAR_RUTAS";
	public static final String CARGAR_DEMORAS = "DEMORAS";
	public static final String INCIAR = "INCIAR";
	public static final String PINTAR = "PINTAR";
	public static final String VER_DETALLES = "VER_DETALLES";
	public static final String ORDENAR = "ORDENAR";
	public static final String RUTAS = "RUTAS";
	public static final String DETENER_R = "DETENER_R";
	public static final String CAMBIAR_H = "CAMBIAR_H";
	public static final String CONTINUAR_H = "CONTINUAR_H";
	
	Detalles Detalles;
	Grafo rutas = new Grafo(this);
	Grafo demora = new Grafo(this);
	DibujarG DG = new DibujarG();
	List<PRutas> pr ;
	Reloj reloj;
	
	private JPanel contentPane;
	private JMenuItem mntmCargarRutas;
	private JMenuItem mntmCargarDemoras;
	private JButton btnIniciar;
	private JButton btnPintar;
	private JPanel panelDibujo;
	private JLabel lblImagen;
	private JComboBox comboBoxDestino;
	private JComboBox comboBoxOrigen;
	private JPanel panel;
	private JLabel lblReloj;
	private JComboBox comboBoxRutas;
	private JButton btnVerDetalles;
	private JComboBox comboBoxSegun;
	private JButton btnDetenR;
	private JButton btnCambiarH;
	private JButton btnContinuarH;
	private JTextField textFieldHora;
	
	
	public Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 800, 800);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Archivo");
		menuBar.add(mnNewMenu);
		
		mntmCargarRutas = new JMenuItem("Cargar Rutas");
		mnNewMenu.add(mntmCargarRutas);
		mntmCargarRutas.setActionCommand(CARGAR_RUTAS);
		mntmCargarRutas.addActionListener(this);
		
		mntmCargarDemoras = new JMenuItem("Cargar Demoras");
		mnNewMenu.add(mntmCargarDemoras);
		mntmCargarDemoras.setActionCommand(CARGAR_DEMORAS);
		mntmCargarDemoras.addActionListener(this);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelOpciones = new JPanel();
		panelOpciones.setPreferredSize(new Dimension(10, 200));
		panelOpciones.setMinimumSize(new Dimension(10, 300));
		contentPane.add(panelOpciones, BorderLayout.NORTH);
		panelOpciones.setLayout(null);
		
		comboBoxDestino = new JComboBox();
		comboBoxDestino.setBounds(66, 44, 190, 22);
		panelOpciones.add(comboBoxDestino);
		
		comboBoxOrigen = new JComboBox();
		comboBoxOrigen.setBounds(66, 11, 190, 22);
		panelOpciones.add(comboBoxOrigen);
		
		JLabel lblNewLabel = new JLabel("Origen");
		lblNewLabel.setBounds(10, 15, 46, 14);
		panelOpciones.add(lblNewLabel);
		
		JLabel lblDestino = new JLabel("Destino");
		lblDestino.setBounds(10, 48, 46, 14);
		panelOpciones.add(lblDestino);
		
		btnIniciar = new JButton("INICIAR");
		btnIniciar.setBounds(10, 77, 89, 23);
		btnIniciar.setActionCommand(INCIAR);
		btnIniciar.addActionListener(this);
		
		panelOpciones.add(btnIniciar);
		
		btnPintar = new JButton("PINTAR");
		btnPintar.setBounds(10, 111, 89, 23);
		btnPintar.setActionCommand(PINTAR);
		btnPintar.addActionListener(this);
		panelOpciones.add(btnPintar);
		
		btnVerDetalles = new JButton("Ver Detalles");
		btnVerDetalles.setBounds(302, 109, 115, 26);
		btnVerDetalles.setActionCommand(VER_DETALLES);
		btnVerDetalles.addActionListener(this);
		panelOpciones.add(btnVerDetalles);
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(534, 11, 190, 54);
		panelOpciones.add(panel);
		panel.setPreferredSize(new Dimension(10, 60));
		panel.setLayout(new BorderLayout(0, 0));
		
		lblReloj = new JLabel("");
		panel.add(lblReloj, BorderLayout.CENTER);
		lblReloj.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblReloj.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("Rutas Encontradas Segun:");
		lblNewLabel_1.setBounds(302, 14, 172, 16);
		panelOpciones.add(lblNewLabel_1);
		
		comboBoxRutas = new JComboBox();
		comboBoxRutas.setBounds(303, 76, 171, 25);
		comboBoxRutas.setActionCommand(RUTAS);
		comboBoxRutas.addActionListener(this);
		panelOpciones.add(comboBoxRutas);
		
		comboBoxSegun = new JComboBox();
		comboBoxSegun.setModel(new DefaultComboBoxModel(new String[] 
				{"Distancia", "Gasto Gasolina", "Desgaste Fisico", "Gasolina Distancia", "Desgaste Fisico Distancia "}));
		comboBoxSegun.setBounds(301, 43, 173, 25);
		comboBoxSegun.setActionCommand(ORDENAR);
		comboBoxSegun.addActionListener(this);
		panelOpciones.add(comboBoxSegun);
		
		btnDetenR = new JButton("Detener Reloj");
		btnDetenR.setBounds(534, 75, 142, 26);
		btnDetenR.setActionCommand(DETENER_R);
		btnDetenR.addActionListener(this);
		panelOpciones.add(btnDetenR);
		
		btnCambiarH = new JButton("Cambiar Hora");
		btnCambiarH.setBounds(534, 109, 142, 26);
		btnCambiarH.setActionCommand(CAMBIAR_H);
		btnCambiarH.addActionListener(this);
		panelOpciones.add(btnCambiarH);
		
		btnContinuarH = new JButton("Continuar Hora");
		btnContinuarH.setBounds(534, 148, 142, 26);
		btnContinuarH.setActionCommand(CONTINUAR_H);
		btnContinuarH.addActionListener(this);
		panelOpciones.add(btnContinuarH);
		
		textFieldHora = new JTextField();
		textFieldHora.setBounds(688, 112, 36, 20);
		panelOpciones.add(textFieldHora);
		textFieldHora.setColumns(10);
		
		panelDibujo = new JPanel();
		contentPane.add(panelDibujo, BorderLayout.CENTER);
		panelDibujo.setLayout(new BorderLayout(0, 0));
		
		lblImagen = new JLabel("");
		panelDibujo.add(lblImagen);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals(CARGAR_RUTAS)) {// Boton Cargar Rutas*********************
			List<instruccion> instruc = new ArrayList<>();
			JFileChooser jf = new JFileChooser();
			jf.showOpenDialog(this);
			File archivo = jf.getSelectedFile();
			
			if (archivo != null) {
				instruc = LeerArchivoRutas(archivo.getAbsolutePath());

				for (instruccion i : instruc) {
					rutas.nuevoNodo(i.origen);
					rutas.nuevoNodo(i.destino);
					rutas.NuevaArista(i.origen, i.destino, i.tiempoV, i.tiempoC, i.gastoC, i.gastoF, i.distancia);	
				}
			}
			
		DG.DibujarInicial(rutas.toString());// escribe el archivo de graphviz***********************
			
		}else if(e.getActionCommand().equals(CARGAR_DEMORAS)) {// Boton Cargar demoras*********************
			List<instruccion> instruc = new ArrayList<>();
			JFileChooser jf = new JFileChooser();
			jf.showOpenDialog(this);
			File archivo = jf.getSelectedFile();
			if(archivo != null) {
				instruc = LeerArchivoDemoras(archivo.getAbsolutePath());
				for(instruccion i: instruc) {
					demora.nuevoNodo(i.origen);
					demora.nuevoNodo(i.destino);
					demora.NuevaArista(i.origen, i.destino, i.horaI, i.horaF, i.por);
				}
			}
		}else if(e.getActionCommand().endsWith(VER_DETALLES)){//boton verDEtalles***********************************
			verDetalles();
						
		}else if(e.getActionCommand().equals(INCIAR)) {// Boton iniciar*********************
			if(comboBoxOrigen.getSelectedIndex()!=-1) {
				encontrarRutas(comboBoxOrigen.getSelectedItem(),comboBoxDestino.getSelectedItem());
			}
			
		}else if(e.getActionCommand().equals(PINTAR)) {// Boton pintar****************************
			pintar();
		}
		else if(e.getActionCommand().equals(ORDENAR)) {
			ajustarComboBoxRutas();
			
		}else if(e.getActionCommand().equals(RUTAS)) {
			DG.DibujarInicial(rutas.toString()+rutas.mejorR(comboBoxRutas.getSelectedIndex()));
			
		}else if(e.getActionCommand().equals(DETENER_R)) {
			reloj.detener(false);
		}else if(e.getActionCommand().equals(CAMBIAR_H)) {
			
			if((Integer.parseInt(textFieldHora.getText())>=0) && Integer.parseInt(textFieldHora.getText())<=24 ) {
				
				reloj.setHora(Integer.parseInt(textFieldHora.getText()));
			}
			
		}else if(e.getActionCommand().equals(CONTINUAR_H)) {
			reloj.detener(false);
			reloj.detener(true);
			reloj = new Reloj(lblReloj);
			reloj.comenzar();
		}
	}
	
	public void encontrarRutas( Object origen, Object destino) {
		rutas.setCaminos(null);
		if(rutas != null) {
			rutas.encontarRutas(null, origen , destino , null);
		}
		DG.DibujarInicial(rutas.toString()+rutas.mejorR(comboBoxRutas.getSelectedIndex()));
		ajustarComboBoxRutas();
	}
	
	
	private void verDetalles() {
		if(pr !=null) {
			Detalles = new Detalles(pr, comboBoxRutas.getSelectedIndex());
			Detalles.setVisible(true);
		}
	}

	public void pintar() {
		Image mImage = new ImageIcon("grafo.png").getImage();
		ImageIcon mIcon = new ImageIcon(mImage.getScaledInstance(panelDibujo.getWidth(),panelDibujo.getHeight(),Image.SCALE_SMOOTH));
		lblImagen.setIcon(mIcon);
		ajustarComboBox();
	}

	//coloca los nodos en la lista de los combo box----------------------------------------------------
	public void ajustarComboBox() {
		List<String> nodos = rutas.listaNodos();
		for (String s : nodos) {
			comboBoxOrigen.addItem(s);
			comboBoxDestino.addItem(s);	
		}	
	}
	
	public void ajustarComboBoxRutas() {
		comboBoxRutas.removeAllItems();
		if(rutas.getCaminos()!=null) {

			pr = rutas.getCaminos();
			for (PRutas pRutas : pr) {
				pRutas.ordenamiento = comboBoxSegun.getSelectedIndex();
			}
			Collections.sort(pr);
			for (PRutas pri : pr ) {// Corregir presentacion **********************************
				comboBoxRutas.addItem(pri.distanciaTotal);
			}
		}

	}
	
	
	public List<instruccion> LeerArchivoRutas(String ruta){
		List<instruccion> instruccion = new ArrayList<>();
		
		try {
			BufferedReader lector = new BufferedReader(new FileReader(ruta));
			String linea = "";
			while ((linea=lector.readLine())!=null) {
				String[] bloques = linea.split("\\|");
				if(bloques.length == 7) {
					String origen = bloques[0];
					String destino = bloques[1];
					int tiempoV = Integer.parseInt(bloques[2]);
					int tiempoC = Integer.parseInt(bloques[3]);
					int gastoC = Integer.parseInt(bloques[4]);
					int gastoF = Integer.parseInt(bloques[5]);
					int distancia = Integer.parseInt(bloques[6]);
					instruccion.add(new instruccion(origen, destino, tiempoV, tiempoC, gastoC, gastoF, distancia));
					
				}
			}
		lector.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error al abrir el archivo"+e.getMessage());
		}
		return instruccion;
	}
	
	public List<instruccion> LeerArchivoDemoras(String ruta){
		List<instruccion> instruccion = new ArrayList<>();
		
		try {
			BufferedReader lector = new BufferedReader(new FileReader(ruta));
			String linea = "";
			while ((linea=lector.readLine())!=null) {
				String[] bloques = linea.split("\\|");
				if(bloques.length == 5) {
					String origen = bloques[0];
					String destino = bloques[1];
					int horaI = Integer.parseInt(bloques[2]);
					int horaF = Integer.parseInt(bloques[3]);
					int por = Integer.parseInt(bloques[4]);

					instruccion.add(new instruccion(origen, destino, horaI, horaF, por));
					
				}
			}
		lector.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error al abrir el archivo"+e.getMessage());
		}
		return instruccion;
	}
	
	public void iniciarR() {
		reloj = new Reloj(lblReloj);
		reloj.comenzar();
	}
	
	public Reloj getReloj() {
		return reloj;
		
	}
}
