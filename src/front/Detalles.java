package front;

import back.*;
import grafo.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import arbolB.ArbolB;
import arbolB.NodoBInternal;

import javax.swing.JScrollPane;
import javax.swing.JLabel;

public class Detalles extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	public static final String DIBUJAR = "DIBUJAR";
	public static final String CERRAR = "CERRAR";
	
	private JPanel contentPane;
	List<PRutas> caminos;
	int index;
	
	private JButton btnCerrar;
	private JButton btnDibujar;
	private JTable tableLugares;
	private JTable tableValores;
	private JLabel lblOrigen;
	private JScrollPane scrollPane_1;
	
	public Detalles(List <PRutas> caminos, int index) {
		this();
		this.caminos = caminos;
		this.index = index;
		rellenarTablaL();
	}
	/**
	 * Create the frame.
	 */
	public Detalles() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnCerrar = new JButton("Cerrar");
		btnCerrar.setBounds(10, 11, 89, 23);
		btnCerrar.setActionCommand(CERRAR);
		btnCerrar.addActionListener(this);
		contentPane.add(btnCerrar);
		
		btnDibujar = new JButton("Dibujar");
		btnDibujar.setBounds(635, 11, 89, 23);
		btnDibujar.setActionCommand(DIBUJAR);
		btnDibujar.addActionListener(this);
		contentPane.add(btnDibujar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 90, 236, 290);
		contentPane.add(scrollPane);
		
		tableLugares = new JTable();
		scrollPane.setViewportView(tableLugares);
		tableLugares.setModel(new DefaultTableModel(
			new Object[][] {
				{null},
			},
			new String[] {
				"Lugares por visitar"
			}
		));
		
		lblOrigen = new JLabel("Origen");
		lblOrigen.setBounds(10, 65, 236, 14);
		contentPane.add(lblOrigen);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(278, 90, 236, 290);
		contentPane.add(scrollPane_1);
		
		tableValores = new JTable();
		scrollPane_1.setViewportView(tableValores);
		tableValores.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
			},
			new String[] {
				"Descripcion", "valor"
			}
		));
		tableLugares.getColumnModel().getColumn(0).setPreferredWidth(90);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(CERRAR)) {
			this.dispose();
		}else if(e.getActionCommand().equals(DIBUJAR)) {
			crearArbolB();
			ArbolBPresentacion ABP = new ArbolBPresentacion();
			ABP.setVisible(true);
			ABP.pintar();
		}
		
	}
	
	private void crearArbolB() {
		// TODO Auto-generated method stub
		ArbolB AB = new ArbolB(3);
		for (Arista nodos : caminos.get(index).camino) {
			NodoBInternal tmp = new NodoBInternal(nodos.getClave(), nodos.getDestino().toString());
			AB.insertar(tmp);
		}		
		AB.verArbol();
	}
	public void rellenarTablaL() {
		
		lblOrigen.setText("Origen: "+ caminos.get(index).inicial.dato.toString());
		DefaultTableModel modeloDefault = new DefaultTableModel(new String[] {"lugares recorrido"},
				caminos.get(index).camino.size());
		
		tableLugares.setModel(modeloDefault);
		TableModel modeloLugares = tableLugares.getModel();
		
		// llena tabla detalles*****************************************************
		for (int i = 0; i < caminos.get(index).camino.size() ; i++) {
			Arista ps = caminos.get(index).camino.get(i);
			modeloLugares.setValueAt(ps.getDestino(), i, 0);
			
		}
		// llena tabla valores ***************************************
		DefaultTableModel modeloDefault2 = new DefaultTableModel(new String[] {"Detalles","Valores"},8);
		
		tableValores.setModel(modeloDefault2);
		TableModel modeloValores = tableValores.getModel();

		modeloValores.setValueAt("Distancia Total", 0, 0);
		modeloValores.setValueAt("Tiempo Total Veiculo", 1, 0);
		modeloValores.setValueAt("Tiempo Total caminando", 2, 0);
		modeloValores.setValueAt("Gasto Total Combustible", 3, 0);
		modeloValores.setValueAt("Desgaste Total fisico", 4, 0);
		modeloValores.setValueAt("Rapidez Caminando", 5, 0);
		modeloValores.setValueAt("Rapidez en Veiculo", 6, 0);
		modeloValores.setValueAt("Rapidez en Veiculo con demora", 7, 0);
		modeloValores.setValueAt(caminos.get(index).distanciaTotal, 0, 1);
		modeloValores.setValueAt(caminos.get(index).tiempoVmax, 1, 1);
		modeloValores.setValueAt(caminos.get(index).tiempoCmax, 2, 1);
		modeloValores.setValueAt(caminos.get(index).gastoCmax, 3, 1);
		modeloValores.setValueAt(caminos.get(index).gastoFmax, 4, 1);
		modeloValores.setValueAt(caminos.get(index).rapidezCaminando, 5, 1);
		modeloValores.setValueAt(caminos.get(index).rapidezAuto, 6, 1);
		modeloValores.setValueAt(caminos.get(index).rapidezAutoD, 7, 1);
	}
}
