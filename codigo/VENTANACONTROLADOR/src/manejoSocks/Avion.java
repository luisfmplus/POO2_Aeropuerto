package manejoSocks;

public class Avion {


    protected int codigo;
    protected int clasificacionAvion;
    protected int tamano;
    protected int puerta;
    protected String estado;
    protected String nombre;
    protected String aerolinea;



    public Avion(int codigo, int clasificacionAvion, int tamano, String estado, String nombre, String aerolinea){

        this.codigo = codigo;
        this.clasificacionAvion = clasificacionAvion;
        this.tamano = tamano;
        this.nombre = nombre;
        this.aerolinea = aerolinea;
        this.estado =estado;
        this.puerta = -1;

    }

    public Avion(int codigo, int clasificacionAvion, int tamano, String estado, String nombre, String aerolinea, int puerta){

        this.codigo = codigo;
        this.clasificacionAvion = clasificacionAvion;
        this.tamano = tamano;
        this.nombre = nombre;
        this.aerolinea = aerolinea;
        this.estado =estado;
        this.puerta = puerta;

    }

    

    public int getCodigo(){
        return this.codigo;
    }
    public int getClasificacion(){
        return this.clasificacionAvion;
    }
    public int getTamano() {
        return tamano;
    }
    public int getPuerta() {
        return puerta;
    }
    public String getEstado() {
        return estado;
    }
    public String getNombre(){
        return this.nombre;
    }
    public String getAerolinea(){
        return this.aerolinea;
    }
    public void setCodigo(int codigo){
        this.codigo = codigo;
    }
    public void setTamano(int tamano) {
        this.tamano = tamano;
    }
    public void setClasificacionAvion(int clasificacion){
        this.clasificacionAvion = clasificacion;
    }
    public void setPuerta(int puerta) {
        this.puerta = puerta;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setAerolinea(String aerolinea){
        this.aerolinea = aerolinea;
    }


    public String toString(){

        if (puerta == -1) {
            
            return new String(this.codigo +";"+ this.clasificacionAvion +";"+ this.tamano +";"+ this.estado +";"+ this.nombre +";"+ this.aerolinea);

        }

        return new String(this.codigo +";"+ this.clasificacionAvion +";"+ this.tamano +";"+ this.estado +";"+ this.nombre +";"+ this.aerolinea + ";" + this.puerta);

        
    }


}