package arbol;

import Entorno.TablaDeSimbolos;
import Utilidades.Mensaje;
import java.util.LinkedList;

public class Return implements Instruccion{

    private Instruccion expresion;
    private int linea;
    private int columna;
    
    public Return(Instruccion expresion, int linea, int columna){
        this.expresion = expresion;
        this.linea = linea;
        this.columna = columna;
    }
    
    public Return(int linea, int columna){
        this.expresion = null;
        this.linea = linea;
        this.columna = columna;
    }

    @Override
    public Object ejecutar(TablaDeSimbolos ts, LinkedList<Mensaje> mensajes) {
        if(this.expresion== null)
            return this;
        System.out.print(" ");
        return this.expresion.ejecutar(ts,mensajes);
    }

    @Override
    public String getArbol(TablaDeSimbolos ts) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
