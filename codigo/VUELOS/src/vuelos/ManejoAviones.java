package vuelos;

import java.util.ArrayList;
import java.util.Random;

public class ManejoAviones {
    
private ArrayList<Avion> listaAviones;
private Random random = new Random();
private int codigo;


public ManejoAviones(){

    codigo = 0;

    listaAviones = new ArrayList<Avion>();

    generarArreglo(listaAviones, 12); // genera 12 aviones

}

private void generarArreglo(ArrayList<Avion> listaAviones, int tamano){

    int i = 0;

    while (i < tamano) {// crea una lista de Aviones de tamanno "tamano"
            
    listaAviones.add(generarAvion());
    i++;

    }

}

private Avion generarAvion(){

    codigo++;
    Jets avionJet;
    Comercial avionComercial;
    Carga avionCarga;
    int i = random.nextInt(3);


    if (i == 2) { // generamos un jet
        
        avionJet = new Jets(codigo, setClasificacion(random.nextInt(5)), setTamano(random.nextInt(6)),setEstado(random.nextInt(20)),
        setNombre(random.nextInt(5)), setAerolinea(random.nextInt(5))); // set tamano tiene como limite 6 para que cada tamano tenga un 33% de probabilidad de salir
        return avionJet;

    } else if (i == 1){ // generamos un commercial

        avionComercial = new Comercial(codigo, setClasificacion(random.nextInt(5)), setTamano(random.nextInt(6)),setEstado(random.nextInt(20)),
        setNombre(random.nextInt(5)), setAerolinea(random.nextInt(5))); // set tamano tiene como limite 6 para que cada tamano tenga un 33% de probabilidad de salir
        return avionComercial;

    } else { // generamos un carga

        avionCarga = new Carga(codigo, setClasificacion(random.nextInt(5)), setTamano(random.nextInt(6)),setEstado(random.nextInt(20)),
        setNombre(random.nextInt(5)), setAerolinea(random.nextInt(5))); // set tamano tiene como limite 6 para que cada tamano tenga un 33% de probabilidad de salir
        return avionCarga;

    }


}

private int setClasificacion(int opcion){

    switch (opcion) {

        case 0:
            return 100;
        case 1:
            return 200;
        case 2:
            return 300;
        case 3:
            return 400;
        case 4:
            return 500;
        default:
            return 100;
    }
}

private int setTamano(int opcion){

    switch (opcion) {

        case 0:
            return 1;
        case 1:
            return 1;
        case 2:
            return 2;
        case 3:
            return 2;
        case 4:
            return 3;
        default:
            return 3;
    }

}

private String setNombre(int opcion){

    String nombre0 = "RBK";
    String nombre1 = "RGB";
    String nombre2 = "AK7";
    String nombre3 = "LOL";
    String nombre4 = "UWU";

    switch (opcion) {

        case 0:
            return nombre0;
        case 1:
            return nombre1;
        case 2:
            return nombre2;
        case 3:
            return nombre3;
        case 4:
            return nombre4;
        default:
            return nombre0;
    }

}

private String setAerolinea(int opcion){

    String nombre0 = "United Airlines";
    String nombre1 = "Spirit";
    String nombre2 = "JetBlue";
    String nombre3 = "Lufthansa";
    String nombre4 = "Avianca";

    switch (opcion) {

        case 0:
            return nombre0;
        case 1:
            return nombre1;
        case 2:
            return nombre2;
        case 3:
            return nombre3;
        case 4:
            return nombre4;
        default:
            return nombre0;
    }

}

private String setEstado(int opcion){

    // el rango de entrada determinara la posibilidad de que el avion este retrasado.
    // en el programa la entrada sera 20, generando 25% de que el avion este retrasado

    switch (opcion) {

        case 0:
            return "A tiempo";
        case 1:
            return "Retrasado 10min";
        case 2:
            return "Retrasado 20min";
        case 3:
            return "Retrasado 30min";
        case 4:
            return "Retrasado 40min";
        case 5:
            return "Retrasado 50min";
        default:
            return "A tiempo";
    }

}

public Avion getAvion(int posicion){

    try {
        Avion temp = listaAviones.get(posicion);
        return temp;
    } catch (Exception e) {
        if (posicion < 0) {
            return listaAviones.get(0);
        }

        return listaAviones.get((listaAviones.size())-1);
    }

}

public void eliminarAvion(int posicion){
    
    try {
        listaAviones.set(posicion, null);

    } catch (Exception e) {
        return;
    }

}
    
public boolean isVacio(){

    for (int i = 0; i < listaAviones.size(); i++) {
        
        try {
            
            if (listaAviones.get(i) == null){
                continue;

            } 
            return false;


        } catch (Exception e) {
        
        }
    
    }

    return true;
}


}