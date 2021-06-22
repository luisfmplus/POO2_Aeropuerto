package hilos;

import manejoSocks.*;

public class EscuchaVcontrol implements Runnable{
    
    private Avion [] naves;
    private ClienteManejoSockets socks;
    private int contador;
    private int yaContado;


    public EscuchaVcontrol(){
        
        this.socks = new ClienteManejoSockets(8025);// para escuchar "control"
        this.naves = new Avion [15];
        this.contador = 0;
        this.yaContado = 0;
    
    }


    @Override
    public void run() {

        while (true) {
            try {
    
                naves[contador] = socks.recibirObjeto();
            
            } catch (NullPointerException e) {
                System.out.println("error; no se recibio informacion");
                continue;
            }

            contador++;
        }
    }


    public Avion getAvion() throws NullPointerException {
        
        int temporal = yaContado;

        if (yaContado < contador){ // esto implica que ya existe un objeto de por medio entre contadores 

            yaContado++;
            return naves[temporal];
            
        }
        throw new NullPointerException();
        
    }

}
