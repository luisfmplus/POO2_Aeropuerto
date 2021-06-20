import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;

public class VentControlador extends JFrame {

	private JPanel contentPane;
	private JTextField textHora;
	private JTextField textEntradaCodigo;
	private JTextField textEntradaPista;
	private JTextField textEntradaPuerta;
	private JTextField textEntradaCodigo2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentControlador frame = new VentControlador();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentControlador() {
		setFont(new Font("Arial", Font.BOLD, 14));
		setBackground(SystemColor.menu);
		setTitle("Proyecto 1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(192, 192, 192));
		getContentPane().setLayout(null);
		
		JTextArea txtrVuelosEntrantes = new JTextArea();
		txtrVuelosEntrantes.setText("Vuelos entrantes");
		txtrVuelosEntrantes.setBounds(10, 83, 123, 647);
		getContentPane().add(txtrVuelosEntrantes);
		
		textHora = new JTextField();
		textHora.setText("display hora");
		textHora.setBounds(10, 10, 123, 50);
		getContentPane().add(textHora);
		textHora.setColumns(10);
		
		JTextArea txtrPistasDispolibles = new JTextArea();
		txtrPistasDispolibles.setText("Pistas disponibles");
		txtrPistasDispolibles.setBounds(194, 83, 123, 647);
		getContentPane().add(txtrPistasDispolibles);
		
		JTextArea txtrPuertasDisponibles = new JTextArea();
		txtrPuertasDisponibles.setText("Puertas disponibles");
		txtrPuertasDisponibles.setBounds(373, 83, 123, 647);
		getContentPane().add(txtrPuertasDisponibles);
		
		textEntradaCodigo = new JTextField();
		textEntradaCodigo.setText("Codigo Avion");
		textEntradaCodigo.setBounds(572, 86, 96, 19);
		getContentPane().add(textEntradaCodigo);
		textEntradaCodigo.setColumns(10);
		
		textEntradaPista = new JTextField();
		textEntradaPista.setText("Numero Pista");
		textEntradaPista.setBounds(572, 125, 96, 19);
		getContentPane().add(textEntradaPista);
		textEntradaPista.setColumns(10);
		
		textEntradaPuerta = new JTextField();
		textEntradaPuerta.setText("Numero Puerta");
		textEntradaPuerta.setColumns(10);
		textEntradaPuerta.setBounds(572, 290, 96, 19);
		getContentPane().add(textEntradaPuerta);
		
		textEntradaCodigo2 = new JTextField();
		textEntradaCodigo2.setText("Codigo Avion");
		textEntradaCodigo2.setColumns(10);
		textEntradaCodigo2.setBounds(572, 251, 96, 19);
		getContentPane().add(textEntradaCodigo2);
		
		JButton btnIngresarEntrada = new JButton("New button");
		btnIngresarEntrada.setBounds(572, 171, 85, 21);
		getContentPane().add(btnIngresarEntrada);
		
		JButton btnIngresarEntrada2 = new JButton("New button");
		btnIngresarEntrada2.setBounds(572, 332, 85, 21);
		getContentPane().add(btnIngresarEntrada2);
		Toolkit mipantalla = Toolkit.getDefaultToolkit();
		Dimension tamanoPantalla = mipantalla.getScreenSize();
		int largoPantalla = tamanoPantalla.height;
		int anchoPantalla = tamanoPantalla.width;
		 
		setSize(9*anchoPantalla/10, 9*largoPantalla/10);
		setLocation(anchoPantalla/20, largoPantalla/20);
	}
}
