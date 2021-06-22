import java.awt.EventQueue;
import javax.swing.JFrame;

import manejoSocks.*;
import hilos.*;


public class Ejecutar {
    

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

        VControlador frame = new VControlador();;

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

        boolean [] condicion = new boolean [10];
        Avion[] aviones = new Avion [15];
        Avion temp = null;
        int ubicacionActualizacion = -1;
        int contador_aviones = 0;
        EscuchaVcontrol Control = new EscuchaVcontrol();
        Thread entradaCont = new Thread(Control);

        entradaCont.start();

       while (true) {
           
            //revisamos si llego nueva informacion de "Control"
            condicion[0] = false;
            try {
                temp = Control.getAvion();

                //revisamos si tenemos este avion dentro del arreglo
                for (int i = 0; i < aviones.length; i++) {
                    
                    if (aviones[i] == null) {
                        continue;
                    }
                    if (aviones[i].getCodigo() == temp.getCodigo()) {
                        ubicacionActualizacion = i;
                        condicion[0] = true;
                        break;
                    }

                }

                // condicion[0] = true implica que debemos realizar una actualizacion de datos, en vez de crear una nueva entrada
                if (!condicion[0]) {
                    aviones[contador_aviones] = temp;
                    condicion[1] = true; // tenemos objeto valido llegado de "Control"
                }
                
            } catch (Exception e) {
                //no recibimos nada; pues no hay nada

                condicion[1] = false;// tenemos objeto valido llegado de "Control"
            }
    

            //actualizacion de datos ya existentes
            // condicion[0] == !condicion[1]
            if (condicion[0]) {

                //debido a razones de programacion, la actualizacion se realiza aquí
                aviones[ubicacionActualizacion] = temp;
                
                if (aviones[ubicacionActualizacion].getPuerta() == -1 ){ // mandamos el avion a la tabla volando
                    
                    if (aviones[ubicacionActualizacion].getEstado().equals("Quitar")) {
                        frame.removeCodigoTabla(aviones[ubicacionActualizacion].getCodigo(), 1);
                        //System.out.println(" actualizar; -1; quitar");

                    } else {
                        frame.addtoTabla(aviones[ubicacionActualizacion], 1);

                    }
                    

                } else if (aviones[ubicacionActualizacion].getPuerta() == 111) { // madamos a la tabla volando
                    
                    if (aviones[ubicacionActualizacion].getEstado().equals("Quitar")) {
                        frame.removeCodigoTabla(aviones[ubicacionActualizacion].getCodigo(), 1);

                    } else {
                        frame.addtoTabla(aviones[ubicacionActualizacion], 1);

                    }

                } else if (aviones[ubicacionActualizacion].getPuerta() == 222) { // mandamos a la tabla taxi

                    if (aviones[ubicacionActualizacion].getEstado().equals("Quitar")) {
                        frame.removeCodigoTabla(aviones[ubicacionActualizacion].getCodigo(), 2);

                    } else {
                        frame.addtoTabla(aviones[ubicacionActualizacion], 2);

                    }
                    
                } else if (aviones[ubicacionActualizacion].getPuerta() == 333) { // mandamos a la tabla desembarque

                    if (aviones[ubicacionActualizacion].getEstado().equals("Quitar")) {
                        frame.removeCodigoTabla(aviones[ubicacionActualizacion].getCodigo(), 3);

                    } else {
                        frame.addtoTabla(aviones[ubicacionActualizacion], 3);

                    }
                    
                } else {                                                   //mandamos a la tabla desembarque

                    if (aviones[ubicacionActualizacion].getEstado().equals("Quitar")) {
                        frame.removeCodigoTabla(aviones[ubicacionActualizacion].getCodigo(), 3);

                    } else {
                        frame.addtoTabla(aviones[ubicacionActualizacion], 3);

                    }

                }

            } // condicion[0]


            // escritura del nuevo objeto en la GUI. solamente ocurre si tenemos un nuevo objeto
            // condicion[1] == !condicion[0]
            if (condicion[1]) {
                
                if (aviones[contador_aviones].getPuerta() == -1 ){ // mandamos el avion a la tabla volando

                    // no requiere procesado de la puerta adicional
                    frame.addtoTabla(aviones[contador_aviones], 1);

                } else if (aviones[contador_aviones].getPuerta() == 111) { // madamos a la tabla volando
                    
                    frame.addtoTabla(aviones[contador_aviones], 1);

                } else if (aviones[contador_aviones].getPuerta() == 222) { // mandamos a la tabla taxi

                    frame.addtoTabla(aviones[contador_aviones], 2);
                    
                } else if (aviones[contador_aviones].getPuerta() == 333) { // mandamos a la tabla desembarque

                    frame.addtoTabla(aviones[contador_aviones], 3);
                    
                } else {                                                   //mandamos a la tabla desembarque

                    frame.addtoTabla(aviones[contador_aviones], 3);

                }

                contador_aviones++;
                condicion[1] = false;
                
            } // condicion[1]
            
            wait(500);
        } 


    }


}
