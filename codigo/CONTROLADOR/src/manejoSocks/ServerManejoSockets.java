package manejoSocks;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.*;

public class ServerManejoSockets {
    
	int puerto;
	Socket cliente;
    ObjectOutputStream OBJ_salida;
	Estadisticas EST_salida;
    //mas datos necesarios
	

	public ServerManejoSockets() {
		
		puerto = 8000;
		
	}

	public void escribirObjeto(Estadisticas obj){

		int temp = puerto;

		for (int i = 0; i < 5; i++) {
			
			try {
				cliente = new Socket("192.168.1.3", temp);
				OBJ_salida = new ObjectOutputStream(cliente.getOutputStream());
				OBJ_salida.writeObject(obj);
				OBJ_salida.close();
				return;
				
				
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}

			temp++;
		}

	}
	






}

