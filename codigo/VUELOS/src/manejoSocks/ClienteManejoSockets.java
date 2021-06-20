package manejoSocks;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

import vuelos.Avion;

public class ClienteManejoSockets {
    
	int puerto;
	Socket cliente;
    DataOutputStream OBJ_salida;
	String datos;
    //mas datos necesarios
	

	public ClienteManejoSockets() {
		
		puerto = 8010;
		
	}

	public void escribirObjeto(Avion obj) throws NullPointerException, IOException {

		int temp = puerto;

		if (obj == null) {
			throw new NullPointerException("objeto vacio");
		}

		//datos = new String(obj.getCodigo() +";"+ obj.getClasificacion() +";"+ obj.getTamano() +";"+ obj.getEstado() +";"+ obj.getNombre() +";"+ obj.getAerolinea());
		datos = obj.toString();
		

		try {
			cliente = new Socket("192.168.1.3", temp);
			OBJ_salida = new DataOutputStream(cliente.getOutputStream());
			OBJ_salida.writeUTF(datos);
			OBJ_salida.close();
			return;
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			System.out.println("\nerror encontrado: no se encotro servidor\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("\nerror encontrado: no se pudo escribir\n");
		}

		throw new IOException("no se pudo escribir\n");

	}
	






}

