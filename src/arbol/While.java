package arbol;

import Entorno.TablaDeSimbolos;
import Utilidades.Mensaje;
import java.util.LinkedList;

public class While implements Instruccion {

    private Instruccion condicion;
    private LinkedList<Instruccion> bloque_sentencias;
    private int linea;
    private int columna;
    
    public While(Instruccion condicion, LinkedList<Instruccion> bloque_sentencias, int linea, int columna){
        this.condicion = condicion;
        this.bloque_sentencias = bloque_sentencias;
        this.linea = linea;
        this.columna = columna;
    } 

    @Override
    public Object ejecutar(TablaDeSimbolos ts, LinkedList<Mensaje> mensajes) {
        
        while((Boolean)condicion.ejecutar(ts, mensajes))
        {
            for(Instruccion ins: bloque_sentencias){
                Object r = ins.ejecutar(ts, mensajes);
                
                if(r instanceof Break)
                    return null;
                
                if(r instanceof Continue)
                    break;
            }
        }
        
        return null;
    }

    @Override
    public String getArbol(TablaDeSimbolos ts) {
        String temporal = 
                "   \""+this.toString()+"\" [label=\"ins_while\"] ;\n" +
                "   \""+this.toString()+"et"+"\" [label=\"while\"] ;\n" +
                "   \""+this.toString()+"\" -> \""+this.toString()+"et"+"\"\n";
        
        temporal+= condicion.getArbol(ts);
        temporal+= "   \""+this.toString()+"\" -> \""+condicion.toString()+"\"\n";
        
        temporal += "\""+bloque_sentencias.toString()+"\" [label=\"instrucciones\"] ;\n";
        temporal+= "\""+this.toString()+"\" -> \""+bloque_sentencias.toString()+"\"\n";
            
        for(Instruccion ins:bloque_sentencias){
            temporal += "\""+ins.toString()+"\" [label=\"instruccion\"] ;\n";
            temporal+= "\""+bloque_sentencias.toString()+"\" -> \""+ins.toString()+"\"\n";
        
            temporal += ins.getArbol(ts);
        }
        
        return temporal;
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
