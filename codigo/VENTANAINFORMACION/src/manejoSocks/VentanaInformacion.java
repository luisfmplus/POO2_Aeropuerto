package manejoSocks;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.w3c.dom.ranges.RangeException;

import javax.swing.ListSelectionModel;

public class VentanaInformacion extends JFrame {
	private JTable table;
	private int x = 15; // tamaño de la tabla
	private Avion [] AV_array;
	private int [] AV_puerta;
	
	public VentanaInformacion() {
		setFont(new Font("Arial", Font.BOLD, 14));
		setBackground(SystemColor.menu);
		setTitle("Aviones");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(204, 255, 255));
		getContentPane().setLayout(null);
		
		AV_puerta = new int [x+1];
		AV_array = new Avion [(x+1)];
		table = new JTable((x+1),5);
		table.setFont(new Font("Arial Black", Font.BOLD, 10));
		table.setBackground(new Color(255, 255, 204));
		table.setValueAt("Aerolinea", 0, 0);
		table.setValueAt("Codigo", 0, 1);
		table.setValueAt("Nombre", 0, 2);
		table.setValueAt("Estado", 0, 3);
		table.setValueAt("Puerta", 0, 4);

		
		table.setBounds(10, 20, 565, 274);
		getContentPane().add(table);
		
		
		Toolkit mipantalla = Toolkit.getDefaultToolkit();
		Dimension tamanoPantalla = mipantalla.getScreenSize();
		int largoPantalla = tamanoPantalla.height;
		int anchoPantalla = tamanoPantalla.width;
		 
		setSize(4*anchoPantalla/10, 5*largoPantalla/10);
		setLocation(anchoPantalla/64, 64*largoPantalla/128);
	}
	
	
	public void addtoTabla(Avion obj, int puerta) throws RangeException {
		int i;
		int j;
		boolean igualdad = false;
		//revisamos arreglo 

		for (i = 1; i < AV_array.length; i++) {
			// asuminos que todos los espacios estan llenos hasta i
			if (AV_array[i] == null) {
				break;
			}
		}

		if (i == AV_array.length) {
			throw new RangeException((short)100,"Maximo tamaño alcanzado en la tabla");
		}

		// i = el valor donde guardar el siguiente avion
		

		for (j = 1; j < AV_array.length; j++) {
			// asuminos que todos los espacios estan llenos hasta i
			
			if (AV_array[j] == null) {
				break;
			}

			if (AV_array[j].getCodigo() == obj.getCodigo()) {
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

		AV_array[i] = obj; // guardamos el nuevo avion
		AV_puerta[i] = puerta; // guardamos la nueva puerta

		// actualizamos la fila

		table.setValueAt(AV_array[i].getAerolinea(), i, 0);
		table.setValueAt(AV_array[i].getCodigo(), i, 1);
		table.setValueAt(AV_array[i].getNombre(), i, 2);
		table.setValueAt(AV_array[i].getEstado(), i, 3);
		if (AV_array[i].getPuerta() != -1) {
			table.setValueAt(AV_array[i].getPuerta(), i, 4);
			return;
		}
		table.setValueAt(null, i, 4);
		
	}

	public void removePosicionTabla(int posicion) throws RangeException {
		
		// movemos todos los valores no nulos y a la derecha de la posicion indicada
		// recordemos que el arreglo de Avion esta vinculado con la tabla
		Avion temp;
		int i = posicion;


		//revisamos arreglo 

		if ((posicion <= 0) || (posicion == AV_array.length)) {
			throw new RangeException((short)100,"Maximo tamaño alcanzado en la tabla");
		}

		// borramos la fila
		table.setValueAt(null, i, 0);
		table.setValueAt(null, i, 1);
		table.setValueAt(null, i, 2);
		table.setValueAt(null, i, 3);
		table.setValueAt(null, i, 4);
		AV_array[i] = null;

		if ((AV_array.length -1) == i) {
			// borramos al ultimo elemento de la tabla y arreglo
			// por lo tanto no requerimos de corrimiento
			return;
		}

			// vamos de "posicion" hasta "AV_array.length -1"
		for (i = posicion ; (i+1) < AV_array.length; i++) {
			// arreglamos la posicion de todos los objetos en el arreglo y la tabla
			
			
			try {
				
                temp = AV_array[(i+1)];

				if (temp == null) {
					throw new Exception();
				}

			} catch (Exception e) {
				table.setValueAt(null, i, 0);
				table.setValueAt(null, i, 1);
				table.setValueAt(null, i, 2);
				table.setValueAt(null, i, 3);
				table.setValueAt(null, i, 4);
				AV_array[i] = null;
				return;
			}
			
			AV_array[i] = temp;
			table.setValueAt(AV_array[i].getAerolinea(), i, 0);
			table.setValueAt(AV_array[i].getCodigo(), i, 1);
			table.setValueAt(AV_array[i].getNombre(), i, 2);
			table.setValueAt(AV_array[i].getEstado(), i, 3);
			table.setValueAt(AV_puerta[i], i, 4);

		}
	
}

	public void removeCodigoTabla(int codigo) throws NullPointerException {

		// movemos todos los valores no nulos y a la derecha de la posicion indicada
		// recordemos que el arreglo de Avion esta vinculado con la tabla

		for (int j = 1; j < AV_array.length; j++) {
			// revisamos por la existencia del codigo dentro del arreglo
			
			if (AV_array[j] == null) {
				throw new NullPointerException("no encontramos el codigo solicitado"); // chocamos con un elemento en nulo y por lo tanto el codigo no existe
			}

			if (AV_array[j].getCodigo() == codigo) {
				removePosicionTabla(j); // lo encontramos
				return;
			}

		}

		//solamente ocurre si la tabla esta llena y no encontramos el codigo
		throw new NullPointerException("no encontramos el codigo solicitado");

	}



}