package arbol;

import Entorno.TablaDeSimbolos;
import Utilidades.Mensaje;
import java.util.LinkedList;

public class Switch implements Instruccion {

    private Instruccion expresion;
    private LinkedList<Case> cases;
    private int linea;
    private int columna;
    
    public Switch(Instruccion expresion, LinkedList<Case> cases, int linea, int columna){
        this.expresion = expresion;
        this.cases = cases;
        this.linea = linea;
        this.columna = columna;
    }
    
    @Override
    public Object ejecutar(TablaDeSimbolos ts, LinkedList<Mensaje> mensajes) {
        Object result = null;
        
        for(Case ins: cases){
            ins.setExpresion_switch(expresion);
            result = ins.ejecutar(ts, mensajes);
            
            if(ins.getIsEquals() || ins.getIsDefault()){
                if(result != null){
                    if(result instanceof Break)
                        return null;
                    
                    return result;
                }
            }
        }
        
        return null;
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
    public LinkedList<Case> getCases() {
        return cases;
    }
    public void setCases(LinkedList<Case> cases) {
        this.cases = cases;
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
