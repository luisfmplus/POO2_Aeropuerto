package hilos;

import manejoSocks.*;

public class MandarVinfo implements Runnable{
    
    private Avion naves;
    private ClienteManejoSockets socks;


    public MandarVinfo(Avion obj){
        
        this.socks = new ClienteManejoSockets(8030);// para "Vinfo"
        this.naves = obj;
    }


    @Override
    public void run() {

        while (true) {

            try {
    
                socks.escribirObjeto(naves);
            
            } catch (Exception e) {
                System.out.println("para info");
                continue;
            }

            break;
        }
    }

}