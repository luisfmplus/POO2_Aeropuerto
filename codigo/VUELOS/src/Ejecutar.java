
import java.awt.EventQueue;
import javax.swing.JFrame;
import vuelos.*;
import manejoSocks.*;


public class Ejecutar {
    

    VentControlador frame = new VentControlador();


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
        

        ManejoAviones manejo = new ManejoAviones();
        ClienteManejoSockets MaSocks = new ClienteManejoSockets();
        VentControlador frame = new VentControlador();
        Avion obj = null;

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

        // 1s == 1Tick

        int contador = 0;
        int contador_error = 0;
        boolean error = false;

        while (!manejo.isVacio()) {
            

            if (contador_error == 1000) {
                
                System.out.println("\nerror al eviar el objeto mas de 1000 veces\n");
                break;
            }

            if (!error) {
                //solamente entramos si ocupamos enviar el siguiente avion
                wait(5000);//esperamos 10 segundos
                obj = manejo.getAvion(contador);
                manejo.eliminarAvion(contador);
            }
            
            

            try {

                MaSocks.escribirObjeto(obj); // intentamos pasar el objeto por sockets
            } catch (Exception e) {

                //objeto nulo o no pudo mandar la info
                error = true;
                contador_error++;
                continue;

                //TODO: handle exception
            }

            
            contador++; // cuando llega el objeto fue mandado con exito y nos movemos al siguiente objeto
            error = false;
        
        } // while
        frame.dispose();
        System.out.println("termine");
        

    } //main

    

}
