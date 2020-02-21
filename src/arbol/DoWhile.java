package arbol;

import Entorno.TablaDeSimbolos;
import Utilidades.Mensaje;
import java.util.LinkedList;

public class DoWhile implements Instruccion {
    
    private Instruccion condicion;
    private LinkedList<Instruccion> bloque_sentencias;
    private int linea;
    private int columna;
    
    public DoWhile(Instruccion condicion, LinkedList<Instruccion> bloque_sentencias, int linea, int columna){
        this.condicion = condicion;
        this.bloque_sentencias = bloque_sentencias;
        this.linea = linea;
        this.columna = columna;
    }

    @Override
    public Object ejecutar(TablaDeSimbolos ts, LinkedList<Mensaje> mensajes) {
        do
        {
            for(Instruccion ins: bloque_sentencias){
                Object r = ins.ejecutar(ts, mensajes);
                
                if(r instanceof Break)
                    return null;
                
                if(r instanceof Continue)
                    break;
            }
        }
        while((Boolean)condicion.ejecutar(ts, mensajes));
        
        return null;
    }

    @Override
    public String getArbol(TablaDeSimbolos ts) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Instruccion getCondicion() {
        return condicion;
    }
    public void setCondicion(Instruccion condicion) {
        this.condicion = condicion;
    }
    public LinkedList<Instruccion> getBloque_sentencias() {
        return bloque_sentencias;
    }
    public void setBloque_sentencias(LinkedList<Instruccion> bloque_sentencias) {
        this.bloque_sentencias = bloque_sentencias;
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
