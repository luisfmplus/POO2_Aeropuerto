package manejoSocks;
import vuelos.Avion;

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
	InetAddress ip;

    //mas datos necesarios
	

	public ClienteManejoSockets(int puerto) {
		
		this.puerto = puerto;
		try {
			ip = InetAddress.getLocalHost();
		} catch (Exception e) {
			//TODO: handle exception
		}
		
		

	}

	public void escribirObjeto(Avion obj) throws NullPointerException, IOException {

		if (obj == null) {
			throw new NullPointerException("objeto vacio");
		}

		//datos = new String(obj.getCodigo() + obj.getClasificacion() + obj.getTamano() + obj.getRetraso() + obj.getNombre() + obj.getAerolinea() + obj.getpuerta());
		//o
		//datos = new String(obj.getCodigo() + obj.getClasificacion() + obj.getTamano() + obj.getRetraso() + obj.getNombre() + obj.getAerolinea());

		datos = obj.toString();
		
		try {
			// creamos el  socket cliente predefinido en caso de no existir uno antes
			cliente = new Socket(ip.getHostAddress(), puerto);
			System.out.println("1");
			Data_salida = new DataOutputStream(cliente.getOutputStream());
			System.out.println("2");
			Data_salida.writeUTF(datos);
			System.out.println("3");
			Data_salida.close();
			System.out.println("4");
			return;
			
			
		} catch (UnknownHostException e) {
			System.out.println("\nerror encontrado: no se encotro servidor\n");
		} catch (IOException e) {
			System.out.println("\nerror encontrado: no se pudo escribir\n");
		}

		throw new IOException("no se pudo escribir\n");

	}
	
	public void setClienteConexion(int temp) throws IOException {
			
		// crea un socket cliente con el puerto "temp"
		// sobreescribe el socket cliente en caso de que este ya este en uso

		try {
			cliente = new Socket(ip.getHostAddress(), temp);
			return;
			
		} catch (UnknownHostException e) {
			System.out.println("\nerror encontrado: no se encotro servidor\n");
		} catch (IOException e) {
			System.out.println("\nerror encontrado: no se pudo escribir\n");
		}

		throw new IOException("no se pudo conectar\n");
	}

	public void setClienteConexion() throws IOException {
			
		// crea un socket cliente con el puerto "temp"
		// sobreescribe el socket cliente en caso de que este ya este en uso

		try {
			cliente = new Socket(ip.getHostAddress(), puerto);
			return;
			
		} catch (UnknownHostException e) {
			System.out.println("\nerror encontrado: no se encotro servidor\n");
		} catch (IOException e) {
			System.out.println("\nerror encontrado: no se pudo escribir\n");
		}

		throw new IOException("no se pudo conectar\n");
	}

	public void setServerConexion(int temp) throws IOException {
			
		// crea un socket servidor con el puerto "temp"
		// sobreescribe el socket servidor en caso de que este ya este en uso

		try {
			servidor = new ServerSocket(temp);
			return;
			
		} catch (UnknownHostException e) {
			System.out.println("\nerror encontrado: no se encotro servidor\n");
		} catch (IOException e) {
			System.out.println("\nerror encontrado: no se pudo escribir\n");
		}

		throw new IOException("no se pudo conectar\n");
	}

	public void setServerConexion() throws IOException {
			
		// crea un socket servidor con el puerto "predefinido"
		// sobreescribe el socket servidor en caso de que este ya este en uso

		try {
			servidor = new ServerSocket(puerto);
			return;
			
		} catch (UnknownHostException e) {
			System.out.println("\nerror encontrado: no se encotro servidor\n");
		} catch (IOException e) {
			System.out.println("\nerror encontrado: no se pudo escribir\n");
		}

		throw new IOException("no se pudo conectar\n");
	}

	public Avion recibirObjeto() throws NullPointerException{
	
		//Recibe atravez de un puerto previamente definido entre las aplicaciones la informacion, esta siempre entrara atravez del socket servidor

		//datos = new String(obj.getCodigo() + obj.getClasificacion() + obj.getTamano() + obj.getRetraso() + obj.getNombre() + obj.getAerolinea());
		// puede tener el dato de puerta si este lo contiene
			
		try {
			if (!(servidor != null)) {
				// creamos el  socket servidor predefinido en caso de no existir uno antes
				servidor = new ServerSocket(puerto);
			}
			
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
			System.out.println("\nerror: no se encotro servidor\n");
			throw new NullPointerException();
		} catch (IOException e) {
			System.out.println("\nerror: no se pudo escribir\n");
			throw new NullPointerException();
		}

	}


}

