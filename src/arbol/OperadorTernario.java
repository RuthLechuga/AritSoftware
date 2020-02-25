package arbol;

import Entorno.TablaDeSimbolos;
import Utilidades.Mensaje;
import static Utilidades.Mensaje.tipo_mensaje.SEMANTICO;
import java.util.LinkedList;

public class OperadorTernario implements Instruccion {
    
    private Instruccion expresion;
    private Instruccion resultado_true;
    private Instruccion resultado_false;
    private int linea;
    private int columna;
    
    public OperadorTernario(Instruccion expresion, Instruccion resultado_true, Instruccion resultado_false, int linea, int columna){
        this.expresion = expresion;
        this.resultado_true = resultado_true;
        this.resultado_false = resultado_false;
        this.linea = linea;
        this.columna = columna;
    }

    @Override
    public Object ejecutar(TablaDeSimbolos ts, LinkedList<Mensaje> mensajes) {
        try{
            return ((Boolean)expresion.ejecutar(ts, mensajes)? resultado_true.ejecutar(ts, mensajes) : resultado_false.ejecutar(ts, mensajes));
        }catch(Exception e){
            
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"No se ha podido ejecutar la operación ternaria."));
            return null;
        }
        
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
    public Instruccion getResultado_true() {
        return resultado_true;
    }
    public void setResultado_true(Instruccion resultado_true) {
        this.resultado_true = resultado_true;
    }
    public Instruccion getResultado_false() {
        return resultado_false;
    }
    public void setResultado_false(Instruccion resultado_false) {
        this.resultado_false = resultado_false;
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
