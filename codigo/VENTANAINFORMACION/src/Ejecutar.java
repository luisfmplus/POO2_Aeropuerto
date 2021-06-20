
import java.awt.EventQueue;
import javax.swing.JFrame;
import manejoSocks.*;

public class Ejecutar {
    

    VentanaInformacion frame = new VentanaInformacion();


    public static void wait(int ms)
    {
        try
        {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        

        ClienteManejoSockets MaSocks = new ClienteManejoSockets();
        VentanaInformacion frame = new VentanaInformacion();
        Avion obj = null;
        int puerta;

        EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});


        while (true) {
            
            wait(3000); // esperamos 3 segundos

            try {
	    	
                obj = MaSocks.recibirObjeto(puerta);

        	if (obj != null) {
        		//llego un objeto y actualizamos la interfaz grafica
        		
                    if (obj.getEstado() == "Quitar") {
                        frame.removeCodigoTabla(obj.getCodigo());
                        continue;
                    }

                    frame.addtoTabla(obj, puerta);
                }
	    	
		    } catch (NullPointerException e) {
	    	// TODO Auto-generated catch block
	    	System.out.println("error; no se recibio informacion");
		    }

        }



    } //main


}
