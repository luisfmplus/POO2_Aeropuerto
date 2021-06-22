package hilos;

import manejoSocks.*;

public class MandarVcontrol implements Runnable{
    
    private Avion naves;
    private ClienteManejoSockets socks;


    public MandarVcontrol(Avion obj){
        
        this.socks = new ClienteManejoSockets(8020);// para "control"
        this.naves = obj;
    }


    @Override
    public void run() {

        while (true) {

            try {
    
                socks.escribirObjeto(naves);
            
            } catch (Exception e) {
                System.out.println("para control");
                continue;
            }

            break;
        }
    }

}
