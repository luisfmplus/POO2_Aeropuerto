import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class VentControlador extends JFrame {

	public VentControlador() {
		setFont(new Font("Arial", Font.BOLD, 14));
		setBackground(SystemColor.menu);
		setTitle("Aviones");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(192, 192, 192));
		getContentPane().setLayout(null);
		
		
		Toolkit mipantalla = Toolkit.getDefaultToolkit();
		Dimension tamanoPantalla = mipantalla.getScreenSize();
		int largoPantalla = tamanoPantalla.height;
		int anchoPantalla = tamanoPantalla.width;
		 
		setSize(2*anchoPantalla/10, 2*largoPantalla/10);
		setLocation(anchoPantalla/64, largoPantalla/128);
	}
}
