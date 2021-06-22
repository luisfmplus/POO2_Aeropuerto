

import manejoSocks.*;
import hilos.*;


//importante:
// puertos: vuelos = 8010|| enviar Vcontrol = 8025 || escuchar Vcontrol = 8020 || Vinfo = 8030

// estados Aviones especiales: "Quitar" (remueve el avion de la interfaz grafica)

// puertas especiales entrando: 1,2 y 3 implican que el avion esta aterrizando

// puertas especiales saliendo: 111 asigna el avion a la tabla de taxiando
// || 222 asigna el avion a la tabla de desembarque || 333 indica a la tabla desembarque || -1 la manda a tabla vuelos

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
        
        boolean condicion [] = new boolean [10];

        Avion[] volando = new Avion [15];
        int contador_volando = 0;
        Avion[] taxiando = new Avion [15];
        int contador_taxiando = 0;
        Avion[] aterrizando = new Avion [15];
        int contador_aterrizando = 0;
        Avion temp = null;

        int tiempo_aterrizando [] = new int [15];
        int tiempo_taxiando [] = new int [15];
        int tiempo_desembarque [] = new int [15];

        EscuchaVuelos Evuelos = new EscuchaVuelos();
        EscuchaVcontrol EVControl = new EscuchaVcontrol();
        MandarVinfo salidaVinfo;
        MandarVcontrol salidaVcont;

        Thread entradaVuelo = new Thread(Evuelos);
        Thread entradaVCont = new Thread(EVControl);
                
        entradaVuelo.start(); // hilo encargado de recibir los aviones de "Vuelos"; es infinito
        entradaVCont.start(); // hilo encargado de recibir los aviones de "Vcontrol"; es infinito

        while (true) {


            //revisamos si llego nueva informacion de "Vuelos"
            try {
                volando[contador_volando] = Evuelos.getAvion();
                condicion[0] = true; // tenemos objeto valido llegado de "vuelos"
            } catch (Exception e) {
                //no recibimos nada; pues no hay nada

                condicion[0] = false;// tenemos objeto valido llegado de "vuelos"
            }
            


            // escritura del nuevo objeto. solamente ocurre si tenemos un nuevo objeto
            if (condicion[0]) {
                
                try {

                    //Manejo con hilos del envio de informacion

                    salidaVcont = new MandarVcontrol(volando[contador_volando]);
                    salidaVinfo = new MandarVinfo(volando[contador_volando]);

                    Thread mandarInfo = new Thread(salidaVinfo);
                    Thread mandarVcont = new Thread(salidaVcont);

                    mandarInfo.start();
                    mandarVcont.start();

                    contador_volando++; //aumentamos el contador del contador pues ya se mandaron los aviones a 
                } catch (Exception e) {
                    System.out.println("error en la matrix\n");
                }

                condicion[0] = false;
                
            }


            // revisamos si llego nueva informacion de "Vcontrol"
            try {
                temp = EVControl.getAvion();
                condicion[1] = true; // tenemos objeto valido llegado de "Vcontrol"
            } catch (Exception e) {
                //no recibimos nada; pues no hay nada

                temp = null;
                condicion[1] = false;// tenemos objeto valido llegado de "Vcontrol"
            }

            // pista/puerta asignada en el objeto {temp}
            // escritura del nuevo objeto a Vinfo. Informaremos a Vcontrol cuando se acabe el contador de aterrizando/taxiando

            if (condicion[1]) {
                
                //importante: si puerta e [1,3] puerta tiene el valor de la pista de aterrizaje
                //importnte: si puerta != [1,3] puerta tiene el valor de la puerta

                if ((temp.getPuerta() <= 3) && (temp.getPuerta() > 0) ) {
                    // el objeto recibido debe ser asignado a aterrizando
                    temp.setEstado("Aterrizando");
                    aterrizando[contador_aterrizando] = temp;
                    condicion[2] = true; // esta el objeto aterrizando
                    
                } else {

                    temp.setEstado("Taxiando");
                    taxiando[contador_taxiando] = temp;
                    condicion[2] = false; // esta el objeto taxiando

                }

                // le mandamos la informacion a "Vinfo"
                // esta informacion es independiente del contador
                // lo cual permite realizar esta operacion antes de la asignacion del contador

                try {

                    //Manejo con hilos del envio de informacion

                    temp.setPuerta(-1); // decimos que puerta es -1 pues la pista no es la puerta
                    salidaVinfo = new MandarVinfo(temp);

                    Thread mandarInfo = new Thread(salidaVinfo);

                    mandarInfo.start();

                    contador_volando++; //aumentamos el contador del contador pues ya se mandaron los aviones a 
                } catch (Exception e) {
                    System.out.println("error en la matrix\n");
                }

                // en estos momentos se debe iniciar el contador del objeto
                // este contador solamente puede ser de tipo "aterrizando" o "taxiando"
                // el contador desembarque es automatico

                if (condicion[2]) {
                    tiempo_aterrizando[contador_aterrizando] = 6; // como el limite del contador es 1 ponemos 6
                    // si usaramos 0 como limite, nos confundiriamos con los valores no inicializados
                    //aumentamos el contador respectivo
                    contador_aterrizando++;
                } else {
                    tiempo_taxiando[contador_taxiando] = 6;// como el limite del contador es 1 ponemos 6
                    // si usaramos 0 como limite, nos confundiriamos con los valores no inicializados
                    //aumentamos el contador respectivo
                    contador_taxiando++;
                }

                condicion[1] = false; // tenemos objeto valido llegado de "Vcontrol"
            }


            //revisamos el contador de los aviones aterrizando
                //de completarlo le avisamos a Vcontrol(lo movemos de tabla) y Vinfo

            int recorriendo = 0;
            while (recorriendo < tiempo_aterrizando.length) {
    
               if (tiempo_aterrizando[recorriendo] == 0) {
                   recorriendo++;
                   continue;
               }

               if (tiempo_aterrizando[recorriendo] == 1) {
                // el tiempo del contador se cumplio
                //por lo tanto hay que informa a Vcontrol y Vinfo
                    try {

                        temp = aterrizando[recorriendo];

                        temp.setEstado("Quitar");
                        temp.setPuerta(111);//numero para indicar la tabla donde debe ser borrado
                        salidaVcont = new MandarVcontrol(temp);
                        Thread mandarCont = new Thread(salidaVcont);
                        mandarCont.start();
                        //mandamos a borrar la entrada de la tabla volando en Vcontrolador

                        temp.setEstado("Aterrizado");
                        temp.setPuerta(222);//numero para indicar la tabla donde debe ser annadido
                        salidaVcont = new MandarVcontrol(temp);
                        mandarCont = new Thread(salidaVcont);
                        mandarCont.start();
                        //mandamos a annadir la entrada en la tabla taxi en Vcontrolador
                        

                        temp.setPuerta(-1);
                        salidaVinfo = new MandarVinfo(temp);
                        Thread mandarInfo = new Thread(salidaVinfo);
                        mandarInfo.start();
                        //mandamos a actualizar la entrada en Vinfo
                        


                    } catch (Exception e) {

                    }

               }

               recorriendo++;

            }
    
        
            


            //revisamos el contador de los aviones taxiando
                //de completarlo le avisamos a Vcontrol(desaparece) y Vinfo.

                recorriendo = 0;

                while (recorriendo < tiempo_taxiando.length) {
        
                   if (tiempo_taxiando[recorriendo] == 0) {
                       recorriendo++;
                       continue;
                   }
    
                   if (tiempo_taxiando[recorriendo] == 1) {
                    // el tiempo del contador se cumplio
                    //por lo tanto hay que informa a Vcontrol y Vinfo
                        try {
    
                            temp = taxiando[recorriendo];
    
                            temp.setEstado("Quitar");
                            temp.setPuerta(222);//numero para indicar la tabla donde debe ser borrado
                            salidaVcont = new MandarVcontrol(temp);
                            Thread mandarCont = new Thread(salidaVcont);
                            mandarCont.start();
                            //mandamos a borrar la entrada de la tabla taxi en Vcontrolador

                            temp.setEstado("Desembarque");
                            if (taxiando[recorriendo].getPuerta() == 111){
                                temp.setPuerta(1110);
                            
                            } else if (taxiando[recorriendo].getPuerta() == 222){
                                temp.setPuerta(2220);

                            } else if (taxiando[recorriendo].getPuerta() == 333){
                                temp.setPuerta(3330);

                            } else {
                                temp.setPuerta(taxiando[recorriendo].getPuerta());

                            }
                            
                            salidaVcont = new MandarVcontrol(temp);
                            mandarCont = new Thread(salidaVcont);
                            mandarCont.start();
                            //mandamos a annadir la entrada en la tabla desembarque en Vcontrolador

                            salidaVinfo = new MandarVinfo(temp);
                            Thread mandarInfo = new Thread(salidaVinfo);
                            mandarInfo.start();
                            //mandamos a actualizar la entrada de la tabla en Vinfo
                            
    
                            tiempo_desembarque[recorriendo] = 4;
    
                        } catch (Exception e) {
    
                        }
    
                   }
    
                   recorriendo++;
    
                }

            //revisamos el contador de los aviones desembarcando
                // de completarlo lo eliminamo de Vcontrol y Vinfo

                recorriendo = 0;

                while (recorriendo < tiempo_desembarque.length) {
        
                   if (tiempo_desembarque[recorriendo] == 0) {
                       recorriendo++;
                       continue;
                   }
    
                   if (tiempo_desembarque[recorriendo] == 1) {
                    // el tiempo del contador se cumplio
                    //por lo tanto hay que informa a Vcontrol y Vinfo
                        try {
    
                            temp = taxiando[recorriendo]; // usamos a los aviones en taxiando pues estan en la misma posicion
    
                            temp.setEstado("Quitar");
                            temp.setPuerta(333);//numero para indicar la tabla donde debe ser borrado
    
                            salidaVcont = new MandarVcontrol(temp);
                            salidaVinfo = new MandarVinfo(temp);
    
                            Thread mandarInfo = new Thread(salidaVinfo);
                            Thread mandarCont = new Thread(salidaVcont);
    
                            mandarInfo.start();
                            mandarCont.start();
    
    
                        } catch (Exception e) {
    
                        }
    
                   }
    
                   recorriendo++;
    
                }



            wait(1000);

            //disminuimos los contadores de los aviones en todos los estados

            for (int i = 0; i < tiempo_aterrizando.length; i++) {
                
                if (tiempo_aterrizando[i] == 0) {
                    continue;
                }
                tiempo_aterrizando[i]--;

            }

            for (int i = 0; i < tiempo_taxiando.length; i++) {
                
                if (tiempo_taxiando[i] == 0) {
                    continue;
                }
                tiempo_taxiando[i]--;
            }

            for (int i = 0; i < tiempo_desembarque.length; i++) {
                
                if (tiempo_desembarque[i] == 0) {
                    continue;
                }
                tiempo_desembarque[i]--;
            }

            

        } // while

    } //main


}
