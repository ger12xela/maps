package front;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;

public class ArbolBPresentacion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblArbolB;


	public ArbolBPresentacion() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 999, 525);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		lblArbolB = new JLabel("");
		contentPane.add(lblArbolB, BorderLayout.CENTER);
	}
	
	public void pintar() {
		Image mImage = new ImageIcon("grafo1.png").getImage();
		ImageIcon mIcon = new ImageIcon(mImage.getScaledInstance(contentPane.getWidth(),contentPane.getHeight(),Image.SCALE_SMOOTH));
		lblArbolB.setIcon(mIcon);
	}

}
