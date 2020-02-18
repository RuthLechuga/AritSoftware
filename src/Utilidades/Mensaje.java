package Utilidades;

public class Mensaje {

    private int linea;
    private int columna;
    private tipo_mensaje tipo;
    private String descripcion;
    
    public Mensaje(int linea, int columna, tipo_mensaje tipo, String descripcion){
        this.linea = linea;
        this.columna = columna;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public int getLinea() {
        return linea;
    }
    public void setLinea(int linea) {
        this.linea = linea;
    }
    public int getColumna() {
        return columna;
    }
    public void setColumna(int columna) {
        this.columna = columna;
    }
    public tipo_mensaje getTipo() {
        return tipo;
    }
    public void setTipo(tipo_mensaje tipo) {
        this.tipo = tipo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
      
    public enum tipo_mensaje {
        LEXICO, SINTACTICO, SEMANTICO, MENSAJE
    }
}
