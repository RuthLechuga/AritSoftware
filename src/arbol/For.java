package arbol;

import Entorno.TablaDeSimbolos;
import Utilidades.Mensaje;
import java.util.LinkedList;

public class For implements Instruccion {
    
    private String variable;
    private Instruccion expresion;
    private LinkedList<Instruccion> bloque_instrucciones;
    private int linea;
    private int columna;
    
    public For(String variable, Instruccion expresion, LinkedList<Instruccion> bloque_instrucciones, int linea, int columna){
        this.variable = variable;
        this.expresion = expresion;
        this.bloque_instrucciones = bloque_instrucciones;
        this.linea = linea;
        this.columna = columna;
    }

    @Override
    public Object ejecutar(TablaDeSimbolos ts, LinkedList<Mensaje> mensajes) {
        System.out.println("For :D");
        
        return null;
    }

    @Override
    public String getArbol(TablaDeSimbolos ts) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getVariable() {
        return variable;
    }
    public void setVariable(String variable) {
        this.variable = variable;
    }
    public Instruccion getExpresion() {
        return expresion;
    }
    public void setExpresion(Instruccion expresion) {
        this.expresion = expresion;
    }
    public LinkedList<Instruccion> getBloque_instrucciones() {
        return bloque_instrucciones;
    }
    public void setBloque_instrucciones(LinkedList<Instruccion> bloque_instrucciones) {
        this.bloque_instrucciones = bloque_instrucciones;
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
}
