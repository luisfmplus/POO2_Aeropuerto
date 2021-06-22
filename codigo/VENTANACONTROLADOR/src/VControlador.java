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
import javax.swing.JTable;

import manejoSocks.*;
import hilos.*;
import javax.swing.JLabel;
import org.w3c.dom.ranges.RangeException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VControlador extends JFrame {

	private Avion AV_arrayVolando [];
	private Avion AV_arrayTaxi [];
	private Avion AV_arrayDesembarque [];
	private int x = 15;
	private JPanel contentPane;
	private JTextField textEntradaCodigo1;
	private JTextField textEntradaPista1;
	private JTextField textEntradaPuerta1;
	private JTextField textEntradaCodigo2;
	private JTable volando;
	private JTable taxi;
	private JTable desembarque;
	private JTextField textField;

	/**
	 * Create the frame.
	 */
	public VControlador() {
		setFont(new Font("Arial", Font.BOLD, 14));
		setBackground(SystemColor.menu);
		setTitle("VentanaControlador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(153, 153, 204));
		getContentPane().setLayout(null);
		Toolkit mipantalla = Toolkit.getDefaultToolkit();
		Dimension tamanoPantalla = mipantalla.getScreenSize();
		int largoPantalla = tamanoPantalla.height;
		int anchoPantalla = tamanoPantalla.width;
		 
		setSize(85*anchoPantalla/100, 85*largoPantalla/100);
		setLocation(100, 50);
		
		AV_arrayVolando = new Avion [(x+1)];
		AV_arrayTaxi = new Avion [(x+1)];
		AV_arrayDesembarque = new Avion [(x+1)];
		
		
		textEntradaCodigo1 = new JTextField();
		textEntradaCodigo1.setBounds(1143, 42, 126, 29);
		getContentPane().add(textEntradaCodigo1);
		textEntradaCodigo1.setColumns(10);
		
		textEntradaPista1 = new JTextField();
		textEntradaPista1.setBounds(1143, 81, 126, 29);
		getContentPane().add(textEntradaPista1);
		textEntradaPista1.setColumns(10);
		
		textEntradaPuerta1 = new JTextField();
		textEntradaPuerta1.setBounds(1143, 247, 126, 29);
		textEntradaPuerta1.setColumns(10);
		getContentPane().add(textEntradaPuerta1);
		
		textEntradaCodigo2 = new JTextField();
		textEntradaCodigo2.setBounds(1143, 208, 126, 29);
		textEntradaCodigo2.setColumns(10);
		getContentPane().add(textEntradaCodigo2);
		
		
		volando = new JTable((x+1),5);
		volando.setBounds(10, 49, 280, 256);
		volando.setFont(new Font("Arial Black", Font.BOLD, 10));
		volando.setBackground(new Color(255, 255, 204));
		volando.setValueAt("Aerolinea", 0, 0);
		volando.setValueAt("Codigo", 0, 1);
		volando.setValueAt("Nombre", 0, 2);
		volando.setValueAt("Estado", 0, 3);
		volando.setValueAt("Puerta", 0, 4);
		volando.setVisible(true);
		getContentPane().add(volando);
		
		JLabel etiqVolando = new JLabel("Volando");
		etiqVolando.setFont(new Font("Arial Black", Font.BOLD, 18));
		etiqVolando.setBounds(15, 10, 96, 29);
		getContentPane().add(etiqVolando);
		
		
		taxi = new JTable((x+1),5);
		taxi.setBounds(350, 49, 280, 256);
		taxi.setFont(new Font("Arial Black", Font.BOLD, 10));
		taxi.setBackground(new Color(255, 255, 204));
		taxi.setValueAt("Aerolinea", 0, 0);
		taxi.setValueAt("Codigo", 0, 1);
		taxi.setValueAt("Nombre", 0, 2);
		taxi.setValueAt("Estado", 0, 3);
		taxi.setValueAt("Puerta", 0, 4);
		taxi.setVisible(true);
		getContentPane().add(taxi);
		
		JLabel etiqTaxi = new JLabel("Taxi");
		etiqTaxi.setFont(new Font("Arial Black", Font.BOLD, 18));
		etiqTaxi.setBounds(350, 12, 96, 24);
		getContentPane().add(etiqTaxi);
		
		
		desembarque = new JTable((x+1),5);
		desembarque.setBounds(677, 49, 280, 256);
		desembarque.setFont(new Font("Arial Black", Font.BOLD, 10));
		desembarque.setBackground(new Color(255, 255, 204));
		desembarque.setValueAt("Aerolinea", 0, 0);
		desembarque.setValueAt("Codigo", 0, 1);
		desembarque.setValueAt("Nombre", 0, 2);
		desembarque.setValueAt("Estado", 0, 3);
		desembarque.setValueAt("Puerta", 0, 4);
		desembarque.setVisible(true);
		getContentPane().add(desembarque);
		
		JLabel etiqDesembarque = new JLabel("Desembarque");
		etiqDesembarque.setFont(new Font("Arial Black", Font.BOLD, 18));
		etiqDesembarque.setBounds(677, 12, 153, 24);
		getContentPane().add(etiqDesembarque);		
		
		textField = new JTextField();
		textField.setBackground(new Color(102, 255, 204));
		textField.setBounds(977, 0, 40, 697);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel etiqCodigoAvion1 = new JLabel("Codigo Avion");
		etiqCodigoAvion1.setFont(new Font("Arial", Font.BOLD, 15));
		etiqCodigoAvion1.setBounds(1027, 42, 104, 29);
		getContentPane().add(etiqCodigoAvion1);
		
		JLabel etiqCodigoPista1 = new JLabel("Codigo Pista");
		etiqCodigoPista1.setFont(new Font("Arial", Font.BOLD, 15));
		etiqCodigoPista1.setBounds(1029, 81, 104, 29);
		getContentPane().add(etiqCodigoPista1);
		
		JLabel etiqCodigoAvion2 = new JLabel("Codigo Avion");
		etiqCodigoAvion2.setFont(new Font("Arial", Font.BOLD, 15));
		etiqCodigoAvion2.setBounds(1027, 208, 104, 29);
		getContentPane().add(etiqCodigoAvion2);
		
		JLabel etiqCodigoPuerta = new JLabel("Codigo Puerta");
		etiqCodigoPuerta.setFont(new Font("Arial", Font.BOLD, 15));
		etiqCodigoPuerta.setBounds(1027, 246, 116, 29);
		getContentPane().add(etiqCodigoPuerta);

		
		JButton btnIngresarEntrada1 = new JButton("Ingresar");
		btnIngresarEntrada1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			//accion a realizar cuando se preciona el boton1

			String codigo1;
			String pista;
			int numeroPista;
			int codigoAvion = -1;
			int ubicacionAvion = -1;
			Avion temp;
			boolean error = false;
			MandarVcontrol salidacontrol;
			Thread mandarControl;
			

			try {
				codigo1 = textEntradaCodigo1.getText();
				pista = textEntradaPista1.getText();

				numeroPista = Integer.parseInt(pista);
				codigoAvion = Integer.parseInt(codigo1);

				for (int i = 0; i < AV_arrayVolando.length; i++) {
					
					if (AV_arrayVolando[i] == null) {
						continue;
					}
					if (AV_arrayVolando[i].getCodigo() == codigoAvion) {
						ubicacionAvion = i;
						break;
					}

				}

				if ((numeroPista > 3) || (numeroPista <= 0) ) {
					throw new Exception();
				}

				// validaremos la pista obtenida mas alla de su rango aabbccdd


			} catch (Exception hey) {
				
				error = true;
			}
			
			if (!error) {
				//entraremos solamente cuando no halla error
				temp = AV_arrayVolando[ubicacionAvion];

				try {

                    //Manejo con hilos del envio de informacion

                    salidacontrol = new MandarVcontrol(temp);
                    mandarControl = new Thread(salidacontrol);
                    mandarControl.start();

					//dejamos las entradas en blanco
					textEntradaCodigo1.setText("");
					textEntradaPista1.setText("");


                } catch (Exception hay) {
                    System.out.println("error en la matrix\n");
                }

			} else {
				//existencia de error

				//dejamos las entradas en blanco
				textEntradaCodigo1.setText("");
				textEntradaPista1.setText("");
				
				//informaremoss al usuario aabbccdd

			}
			

			}
		});
		btnIngresarEntrada1.setFont(new Font("Arial", Font.BOLD, 14));
		btnIngresarEntrada1.setBounds(1143, 120, 126, 29);
		getContentPane().add(btnIngresarEntrada1);
		
		JButton btnIngresarEntrada2 = new JButton("Ingresar");
		btnIngresarEntrada2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				//accion a realizar cuando se preciona el boton2
	
				String codigo2;
				String puerta;
				int numeroPuerta;
				int codigoAvion = -1;
				int ubicacionAvion = -1;
				Avion temp;
				boolean error = false;
				MandarVcontrol salidacontrol;
				Thread mandarControl;
				
	
				try {
					codigo2 = textEntradaCodigo2.getText();
					puerta = textEntradaPuerta1.getText();
	
					numeroPuerta = Integer.parseInt(puerta);
					codigoAvion = Integer.parseInt(codigo2);
	
					for (int i = 0; i < AV_arrayTaxi.length; i++) {
						
						if (AV_arrayTaxi[i] == null) {
							continue;
						}
						if (AV_arrayTaxi[i].getCodigo() == codigoAvion) {
							ubicacionAvion = i;
							break;
						}
	
					}
	
					if ((numeroPuerta <= 3) || (numeroPuerta > 0)) {
						throw new Exception();
					}
	
					// validaremos la pista obtenida mas alla de su rango aabbccdd
	
	
				} catch (Exception hey) {
					
					error = true;
				}
				
				if (!error) {
					//entraremos solamente cuando no halla error
					temp = AV_arrayTaxi[ubicacionAvion];
	
					try {
	
						//Manejo con hilos del envio de informacion
	
						salidacontrol = new MandarVcontrol(temp);
						mandarControl = new Thread(salidacontrol);
						mandarControl.start();
						//dejamos las entradas en blanco
						textEntradaCodigo1.setText("");
						textEntradaPista1.setText("");
	
					} catch (Exception hay) {
						System.out.println("error en la matrix\n");
					}
	
				} else {
					//existencia de error
	
					//dejamos las entradas en blanco
					textEntradaCodigo1.setText("");
					textEntradaPista1.setText("");
					
					//informaremoss al usuario aabbccdd
	
				}
				
	
				}
			});
		btnIngresarEntrada2.setFont(new Font("Arial", Font.BOLD, 14));
		btnIngresarEntrada2.setBounds(1143, 286, 126, 29);
		getContentPane().add(btnIngresarEntrada2);
		
	}
	
	public void addtoTabla(Avion obj, int numeroTabla) throws RangeException {
		int i;
		int j;
		boolean igualdad = false;
		
		if (numeroTabla == 1) { // osea la tabla de volando
			
			//revisamos arreglo 

			for (i = 1; i < AV_arrayVolando.length; i++) {
				// asuminos que todos los espacios estan llenos hasta i
				if (AV_arrayVolando[i] == null) {
					break;
				}
			}

			if (i == AV_arrayVolando.length) {
				throw new RangeException((short)100,"Maximo tama�o alcanzado en la tabla");
			}

			// i = el valor donde guardar el siguiente avion
			

			for (j = 1; j < AV_arrayVolando.length; j++) {
				// asuminos que todos los espacios estan llenos hasta i
				
				if (AV_arrayVolando[j] == null) {
					break;
				}

				if (AV_arrayVolando[j].getCodigo() == obj.getCodigo()) {
					igualdad = true;
					break;
				}
			}

			if (igualdad) {
				
				// si entramos significa que el objeto mandado ya existe en la tabla
				// por lo tanto podemos de decir
				i = j;
				// y eso actualizara la informacion automatica
			}

			AV_arrayVolando[i] = obj; // guardamos el nuevo avion

			// actualizamos la fila

			volando.setValueAt(AV_arrayVolando[i].getAerolinea(), i, 0);
			volando.setValueAt(AV_arrayVolando[i].getCodigo(), i, 1);
			volando.setValueAt(AV_arrayVolando[i].getNombre(), i, 2);
			volando.setValueAt(AV_arrayVolando[i].getEstado(), i, 3);
			if (AV_arrayVolando[i].getPuerta() != -1) {
				volando.setValueAt(AV_arrayVolando[i].getPuerta(), i, 4);
				return;
			}
			volando.setValueAt(null, i, 4);
			
			
			
		} else if(numeroTabla == 2) { // osea la tabla de taxi
			
			//revisamos arreglo 

			for (i = 1; i < AV_arrayTaxi.length; i++) {
				// asuminos que todos los espacios estan llenos hasta i
				if (AV_arrayTaxi[i] == null) {
					break;
				}
			}

			if (i == AV_arrayTaxi.length) {
				throw new RangeException((short)100,"Maximo tama�o alcanzado en la tabla");
			}

			// i = el valor donde guardar el siguiente avion
			

			for (j = 1; j < AV_arrayTaxi.length; j++) {
				// asuminos que todos los espacios estan llenos hasta i
				
				if (AV_arrayTaxi[j] == null) {
					break;
				}

				if (AV_arrayTaxi[j].getCodigo() == obj.getCodigo()) {
					igualdad = true;
					break;
				}
			}

			if (igualdad) {
				
				// si entramos significa que el objeto mandado ya existe en la tabla
				// por lo tanto podemos de decir
				i = j;
				// y eso actualizara la informacion automatica
			}

			AV_arrayTaxi[i] = obj; // guardamos el nuevo avion

			// actualizamos la fila

			taxi.setValueAt(AV_arrayTaxi[i].getAerolinea(), i, 0);
			taxi.setValueAt(AV_arrayTaxi[i].getCodigo(), i, 1);
			taxi.setValueAt(AV_arrayTaxi[i].getNombre(), i, 2);
			taxi.setValueAt(AV_arrayTaxi[i].getEstado(), i, 3);
			if (AV_arrayTaxi[i].getPuerta() != -1) {
				taxi.setValueAt(AV_arrayTaxi[i].getPuerta(), i, 4);
				return;
			}
			taxi.setValueAt(null, i, 4);
			
			
			
		} else { // osea la tabla de desembarque
			
			//revisamos arreglo 

			for (i = 1; i < AV_arrayDesembarque.length; i++) {
				// asuminos que todos los espacios estan llenos hasta i
				if (AV_arrayDesembarque[i] == null) {
					break;
				}
			}

			if (i == AV_arrayDesembarque.length) {
				throw new RangeException((short)100,"Maximo tama�o alcanzado en la tabla");
			}

			// i = el valor donde guardar el siguiente avion
			

			for (j = 1; j < AV_arrayDesembarque.length; j++) {
				// asuminos que todos los espacios estan llenos hasta i
				
				if (AV_arrayDesembarque[j] == null) {
					break;
				}

				if (AV_arrayDesembarque[j].getCodigo() == obj.getCodigo()) {
					igualdad = true;
					break;
				}
			}

			if (igualdad) {
				
				// si entramos significa que el objeto mandado ya existe en la tabla
				// por lo tanto podemos de decir
				i = j;
				// y eso actualizara la informacion automatica
			}

			AV_arrayDesembarque[i] = obj; // guardamos el nuevo avion

			// actualizamos la fila

			desembarque.setValueAt(AV_arrayDesembarque[i].getAerolinea(), i, 0);
			desembarque.setValueAt(AV_arrayDesembarque[i].getCodigo(), i, 1);
			desembarque.setValueAt(AV_arrayDesembarque[i].getNombre(), i, 2);
			desembarque.setValueAt(AV_arrayDesembarque[i].getEstado(), i, 3);
			if (AV_arrayDesembarque[i].getPuerta() != -1) {
				desembarque.setValueAt(AV_arrayDesembarque[i].getPuerta(), i, 4);
				return;
			}
			desembarque.setValueAt(null, i, 4);
			
		}
		
	}

	public void removePosicionTabla(int posicion, int numeroTabla) throws RangeException {
		
		// movemos todos los valores no nulos y a la derecha de la posicion indicada
		// recordemos que el arreglo de Avion esta vinculado con la tabla
		Avion temp;
		int i = posicion;


		if (numeroTabla == 1){// osea la tabla de volando
			
			//revisamos arreglo 

			if ((posicion <= 0) || (posicion == AV_arrayVolando.length)) {
				throw new RangeException((short)100,"Maximo tama�o alcanzado en la tabla");
			}

			// borramos la fila
			volando.setValueAt(null, i, 0);
			volando.setValueAt(null, i, 1);
			volando.setValueAt(null, i, 2);
			volando.setValueAt(null, i, 3);
			volando.setValueAt(null, i, 4);
			AV_arrayVolando[i] = null;

			if ((AV_arrayVolando.length -1) == i) {
				// borramos al ultimo elemento de la tabla y arreglo
				// por lo tanto no requerimos de corrimiento
				return;
			}

				// vamos de "posicion" hasta "AV_array.length -1"
			for (i = posicion ; (i+1) < AV_arrayVolando.length; i++) {
				// arreglamos la posicion de todos los objetos en el arreglo y la tabla
				
				
				try {
					
	                temp = AV_arrayVolando[(i+1)];

					if (temp == null) {
						throw new Exception();
					}

				} catch (Exception e) {
					volando.setValueAt(null, i, 0);
					volando.setValueAt(null, i, 1);
					volando.setValueAt(null, i, 2);
					volando.setValueAt(null, i, 3);
					volando.setValueAt(null, i, 4);
					AV_arrayVolando[i] = null;
					return;
				}
				
				AV_arrayVolando[i] = temp;
				volando.setValueAt(AV_arrayVolando[i].getAerolinea(), i, 0);
				volando.setValueAt(AV_arrayVolando[i].getCodigo(), i, 1);
				volando.setValueAt(AV_arrayVolando[i].getNombre(), i, 2);
				volando.setValueAt(AV_arrayVolando[i].getEstado(), i, 3);
				if (AV_arrayVolando[i].getPuerta() != -1) {
					volando.setValueAt(AV_arrayVolando[i].getPuerta(), i, 4);
					continue;
				}
				volando.setValueAt(null, i, 4);

			}
			
			
			
		} else if (numeroTabla == 2) {// osea la tabla de taxi
			
			//revisamos arreglo 

			if ((posicion <= 0) || (posicion == AV_arrayTaxi.length)) {
				throw new RangeException((short)100,"Maximo tama�o alcanzado en la tabla");
			}

			// borramos la fila
			taxi.setValueAt(null, i, 0);
			taxi.setValueAt(null, i, 1);
			taxi.setValueAt(null, i, 2);
			taxi.setValueAt(null, i, 3);
			taxi.setValueAt(null, i, 4);
			AV_arrayTaxi[i] = null;

			if ((AV_arrayTaxi.length -1) == i) {
				// borramos al ultimo elemento de la tabla y arreglo
				// por lo tanto no requerimos de corrimiento
				return;
			}

				// vamos de "posicion" hasta "AV_array.length -1"
			for (i = posicion ; (i+1) < AV_arrayTaxi.length; i++) {
				// arreglamos la posicion de todos los objetos en el arreglo y la tabla
				
				
				try {
					
	                temp = AV_arrayTaxi[(i+1)];

					if (temp == null) {
						throw new Exception();
					}

				} catch (Exception e) {
					taxi.setValueAt(null, i, 0);
					taxi.setValueAt(null, i, 1);
					taxi.setValueAt(null, i, 2);
					taxi.setValueAt(null, i, 3);
					taxi.setValueAt(null, i, 4);
					AV_arrayTaxi[i] = null;
					return;
				}
				
				AV_arrayTaxi[i] = temp;
				taxi.setValueAt(AV_arrayTaxi[i].getAerolinea(), i, 0);
				taxi.setValueAt(AV_arrayTaxi[i].getCodigo(), i, 1);
				taxi.setValueAt(AV_arrayTaxi[i].getNombre(), i, 2);
				taxi.setValueAt(AV_arrayTaxi[i].getEstado(), i, 3);
				if (AV_arrayTaxi[i].getPuerta() != -1) {
					taxi.setValueAt(AV_arrayTaxi[i].getPuerta(), i, 4);
					continue;
				}
				taxi.setValueAt(null, i, 4);

			}
			
			
		} else {// osea la tabla de desembarque
			
			//revisamos arreglo 

			if ((posicion <= 0) || (posicion == AV_arrayDesembarque.length)) {
				throw new RangeException((short)100,"Maximo tama�o alcanzado en la tabla");
			}

			// borramos la fila
			desembarque.setValueAt(null, i, 0);
			desembarque.setValueAt(null, i, 1);
			desembarque.setValueAt(null, i, 2);
			desembarque.setValueAt(null, i, 3);
			desembarque.setValueAt(null, i, 4);
			AV_arrayDesembarque[i] = null;

			if ((AV_arrayDesembarque.length -1) == i) {
				// borramos al ultimo elemento de la tabla y arreglo
				// por lo tanto no requerimos de corrimiento
				return;
			}

				// vamos de "posicion" hasta "AV_array.length -1"
			for (i = posicion ; (i+1) < AV_arrayDesembarque.length; i++) {
				// arreglamos la posicion de todos los objetos en el arreglo y la tabla
				
				
				try {
					
	                temp = AV_arrayDesembarque[(i+1)];

					if (temp == null) {
						throw new Exception();
					}

				} catch (Exception e) {
					desembarque.setValueAt(null, i, 0);
					desembarque.setValueAt(null, i, 1);
					desembarque.setValueAt(null, i, 2);
					desembarque.setValueAt(null, i, 3);
					desembarque.setValueAt(null, i, 4);
					AV_arrayDesembarque[i] = null;
					return;
				}
				
				AV_arrayDesembarque[i] = temp;
				desembarque.setValueAt(AV_arrayDesembarque[i].getAerolinea(), i, 0);
				desembarque.setValueAt(AV_arrayDesembarque[i].getCodigo(), i, 1);
				desembarque.setValueAt(AV_arrayDesembarque[i].getNombre(), i, 2);
				desembarque.setValueAt(AV_arrayDesembarque[i].getEstado(), i, 3);
				if (AV_arrayDesembarque[i].getPuerta() != -1) {
					desembarque.setValueAt(AV_arrayDesembarque[i].getPuerta(), i, 4);
					continue;
				}
				desembarque.setValueAt(null, i, 4);

			}
			
		}

	}

	public void removeCodigoTabla(int codigo, int numeroTabla) throws NullPointerException {

		// movemos todos los valores no nulos y a la derecha de la posicion indicada
		// recordemos que el arreglo de Avion esta vinculado con la tabla

		
		if (numeroTabla == 1) {
			
			for (int j = 1; j < AV_arrayVolando.length; j++) {
				// revisamos por la existencia del codigo dentro del arreglo
				
				if (AV_arrayVolando[j] == null) {
					throw new NullPointerException("no encontramos el codigo solicitado"); // chocamos con un elemento en nulo y por lo tanto el codigo no existe
				}

				if (AV_arrayVolando[j].getCodigo() == codigo) {
					removePosicionTabla(j, numeroTabla); // lo encontramos
					return;
				}

			}

			//solamente ocurre si la tabla esta llena y no encontramos el codigo
			throw new NullPointerException("no encontramos el codigo solicitado");
			
			
			
		} else if (numeroTabla == 2) {
			
			for (int j = 1; j < AV_arrayTaxi.length; j++) {
				// revisamos por la existencia del codigo dentro del arreglo
				
				if (AV_arrayTaxi[j] == null) {
					throw new NullPointerException("no encontramos el codigo solicitado"); // chocamos con un elemento en nulo y por lo tanto el codigo no existe
				}

				if (AV_arrayTaxi[j].getCodigo() == codigo) {
					removePosicionTabla(j, numeroTabla); // lo encontramos
					return;
				}

			}

			//solamente ocurre si la tabla esta llena y no encontramos el codigo
			throw new NullPointerException("no encontramos el codigo solicitado");
			
			
			
		} else {
			
			for (int j = 1; j < AV_arrayDesembarque.length; j++) {
				// revisamos por la existencia del codigo dentro del arreglo
				
				if (AV_arrayDesembarque[j] == null) {
					throw new NullPointerException("no encontramos el codigo solicitado"); // chocamos con un elemento en nulo y por lo tanto el codigo no existe
				}

				if (AV_arrayDesembarque[j].getCodigo() == codigo) {
					removePosicionTabla(j, numeroTabla); // lo encontramos
					return;
				}

			}

			//solamente ocurre si la tabla esta llena y no encontramos el codigo
			throw new NullPointerException("no encontramos el codigo solicitado");
			
			
		}

	}
	
	
	
	
}
