package manejoSocks;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class ClienteManejoSockets {
    
	int puerto;
	Socket cliente;
	ServerSocket servidor;
	DataInputStream Data_entrada;
	DataOutputStream Data_salida;
	String datos;
    //mas datos necesarios
	

	public ClienteManejoSockets() {
		
		this.puerto = 8010;
		try {
			establecerServerConexion();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void escribirObjeto(Avion obj) throws NullPointerException, IOException {

		int temp = puerto;

		if (obj == null) {
			throw new NullPointerException("objeto vacio");
		}

		//datos = new String(obj.getCodigo() + obj.getClasificacion() + obj.getTamano() + obj.getRetraso() + obj.getNombre() + obj.getAerolinea());
		datos = obj.toString();
		
		try {
			cliente = new Socket("192.168.1.3", temp);
			System.out.println("1");
			Data_salida = new DataOutputStream(cliente.getOutputStream());
			System.out.println("2");
			Data_salida.writeUTF(datos);
			System.out.println("3");
			Data_salida.close();
			System.out.println("4");
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
	
	public void establecerClienteConexion() throws IOException {
		
		int temp = puerto;
			
		try {
			cliente = new Socket("192.168.1.3", temp);
			return;
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			System.out.println("\nerror encontrado: no se encotro servidor\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("\nerror encontrado: no se pudo escribir\n");
		}

		throw new IOException("no se pudo conectar\n");
	}

	public void establecerServerConexion() throws IOException {
		
		int temp = puerto;
			
		try {
			servidor = new ServerSocket(temp);
			return;
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			System.out.println("\nerror encontrado: no se encotro servidor\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("\nerror encontrado: no se pudo escribir\n");
		}

		throw new IOException("no se pudo conectar\n");
	}

	public Avion recibirObjeto() throws NullPointerException{
	
		//datos = new String(obj.getCodigo() + obj.getClasificacion() + obj.getTamano() + obj.getRetraso() + obj.getNombre() + obj.getAerolinea());
		// puede tener el dato de puerta si este lo contiene
			
		try {
			cliente = servidor.accept();
			Data_entrada = new DataInputStream(cliente.getInputStream());
			datos = Data_entrada.readUTF();
			Data_entrada.close();
			String [] alreglo = datos.split(";");

			Avion temp;
			
			try {

				temp = new Avion(Integer.parseInt(alreglo[0]), Integer.parseInt(alreglo[1]), Integer.parseInt(alreglo[2]), alreglo[3], alreglo[4], alreglo[5], Integer.parseInt(alreglo[6]));

			} catch (Exception e) {
				temp = new Avion(Integer.parseInt(alreglo[0]), Integer.parseInt(alreglo[1]), Integer.parseInt(alreglo[2]), alreglo[3], alreglo[4], alreglo[5]);
			}
			return temp;
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			System.out.println("\nerror: no se encotro servidor\n");
			throw new NullPointerException();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("\nerror: no se pudo escribir\n");
			throw new NullPointerException();
		}

	}


}

