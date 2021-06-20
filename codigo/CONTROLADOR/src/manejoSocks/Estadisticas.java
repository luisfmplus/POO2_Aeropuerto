package manejoSocks;

public class Estadisticas {
    
    private int codigo;
    private int clasificacionAvion;
    private int tamano;
    private String nombre;
    private String aerolinea;

    public Estadisticas(int codigo, int clasificacion, int tamano, String nombre, String aerolinea){

        this.codigo = codigo;
        this.clasificacionAvion = clasificacion;
        this.tamano = tamano;
        this.nombre = nombre;
        this.aerolinea = aerolinea;
    }


public String getAerolinea() {
    return aerolinea;
}
public int getClasificacionAvion() {
    return clasificacionAvion;
}
public int getCodigo() {
    return codigo;
}
public String getNombre() {
    return nombre;
}
public int getTamano() {
    return tamano;
}
public void setAerolinea(String aerolinea) {
    this.aerolinea = aerolinea;
}
public void setClasificacionAvion(int clasificacionAvion) {
    this.clasificacionAvion = clasificacionAvion;
}
public void setCodigo(int codigo) {
    this.codigo = codigo;
}
public void setNombre(String nombre) {
    this.nombre = nombre;
}
public void setTamano(int tamano) {
    this.tamano = tamano;
}






}
