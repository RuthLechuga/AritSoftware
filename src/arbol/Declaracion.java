package arbol;

import Entorno.Simbolo;
import Entorno.TablaDeSimbolos;
import Utilidades.Mensaje;
import java.util.LinkedList;

public class Declaracion implements Instruccion {
    
    private String identificador;
    private Instruccion expresion;
    private int linea;
    private int columna;
    
    public Declaracion(String identificador, Instruccion expresion, int linea, int columna){
        this.identificador = identificador;
        this.expresion = expresion;
        this.linea = linea;
        this.columna = columna;
    }

    public Declaracion(String identificador, int linea, int columna){
        this.identificador = identificador;
        this.linea = linea;
        this.columna = columna;
    }
    
    @Override
    public Object ejecutar(TablaDeSimbolos ts, LinkedList<Mensaje> mensajes) {
        Object t = getExpresion().ejecutar(ts, mensajes);
        Simbolo nuevo = new Simbolo(getIdentificador(), getExpresion().ejecutar(ts, mensajes));
        ts.addSymbol(nuevo);
        return null;
    }

    @Override
    public String getArbol(TablaDeSimbolos ts) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getIdentificador() {
        return identificador;
    }
    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
    public Instruccion getExpresion() {
        return expresion;
    }
    public void setExpresion(Instruccion expresion) {
        this.expresion = expresion;
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
