package arbol;

import Entorno.Simbolo;
import Entorno.TablaDeSimbolos;
import Estructuras.Arreglo;
import Estructuras.Lista;
import Estructuras.Matriz;
import Estructuras.Vector;
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
        Object exp = expresion.ejecutar(ts, mensajes);
        LinkedList<Object> lista_iteracion = new LinkedList<Object>();
        
        if(exp instanceof Arreglo)
            lista_iteracion = ((Arreglo)exp).getArreglo();
        
        else if(exp instanceof Lista)
            lista_iteracion = ((Lista)exp).getLista();
        
        else if(exp instanceof Matriz)
            lista_iteracion = ((Matriz)exp).getMatriz();
        
        else if(exp instanceof Vector)
            lista_iteracion = ((Vector)exp).getVector();
        
        else
            lista_iteracion.add(exp);
        
        //TablaDeSimbolos local = new TablaDeSimbolos(ts);
        
        for(Object obj: lista_iteracion){
            ts.addSymbol(new Simbolo(variable,obj));
            
            for(Instruccion ins: bloque_instrucciones){
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
                "   \""+this.toString()+"\" [label=\"ins_for\"] ;\n" +
                "   \""+this.toString()+"for"+"\" [label=\"for\"] ;\n" +
                "   \""+this.toString()+"\" -> \""+this.toString()+"for"+"\"\n";
        
        temporal+= "   \""+this.toString()+"id\" [label=\"IDENTIFICADOR\"] ;\n";
        temporal+= "   \""+this.toString()+"\" -> \""+this.toString()+"id"+"\"\n";        
        
        temporal+= "   \""+this.toString()+"in\" [label=\"IN\"] ;\n";
        temporal+= "   \""+this.toString()+"\" -> \""+this.toString()+"in"+"\"\n";        
        
        temporal+= expresion.getArbol(ts);
        temporal+= "   \""+this.toString()+"\" -> \""+expresion.toString()+"\"\n";        
        
        temporal += "\""+bloque_instrucciones.toString()+"\" [label=\"instrucciones\"] ;\n";
        temporal+= "\""+this.toString()+"\" -> \""+bloque_instrucciones.toString()+"\"\n";
            
        for(Instruccion ins:bloque_instrucciones){
            temporal += "\""+ins.toString()+"\" [label=\"instruccion\"] ;\n";
            temporal+= "\""+bloque_instrucciones.toString()+"\" -> \""+ins.toString()+"\"\n";
        
            temporal += ins.getArbol(ts);
        }
        
        return temporal;
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
