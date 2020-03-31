package arbol;

import Entorno.TablaDeSimbolos;
import Estructuras.Vector;
import Utilidades.Mensaje;
import static Utilidades.Mensaje.tipo_mensaje.SEMANTICO;
import java.util.LinkedList;

public class Elseif implements Instruccion {
    
    private Boolean valor_condicion;
    private Boolean isElse;    
    private Instruccion condicion;
    private LinkedList<Instruccion> bloque_instrucciones;
    private int linea;
    private int columna;
    
    public Elseif(Instruccion condicion, LinkedList<Instruccion> bloque_instrucciones, int linea, int columna){
        this.condicion = condicion;
        this.bloque_instrucciones = bloque_instrucciones;
        this.linea = linea;
        this.columna = columna;
        this.isElse = false;
    }
    
    public Elseif(LinkedList<Instruccion> bloque_instrucciones, int linea, int columna){
        this.bloque_instrucciones = bloque_instrucciones;
        this.linea = linea;
        this.columna = columna;
        this.isElse = true;
    }
    
    @Override
    public Object ejecutar(TablaDeSimbolos ts, LinkedList<Mensaje> mensajes) {
        try{
            if(condicion == null)
                valor_condicion = false;
            else{
                Object t = condicion.ejecutar(ts, mensajes);
                
                if(t instanceof Boolean)
                    valor_condicion = ((Boolean)t);
                
                else if(t instanceof Vector){
                    valor_condicion=true;
                    for(Object obj: ((Vector)t).getVector()){
                        if(!((Boolean)obj)){
                            valor_condicion = false;
                            break;
                        }   
                    }
                }
            }
            
            //valor_condicion = (condicion == null)? false: (Boolean)condicion.ejecutar(ts, mensajes);
        }catch(Exception e){
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Expresión inválida."));
            return null;
        }
        
        if(valor_condicion || isElse){
            Object result = null;

            for(Instruccion ins: bloque_instrucciones){
                result = ins.ejecutar(ts, mensajes);
                
                if(result != null && (ins instanceof Return || ins instanceof Break || ins instanceof If || ins instanceof While || ins instanceof For || ins instanceof DoWhile || ins instanceof Switch))
                {
                    valor_condicion = true;
                    return result;
                }
            }
        }
        
        return null;
    }

    @Override
    public String getArbol(TablaDeSimbolos ts) {
        String temporal = "";
        
        if(!isElse){
            
            temporal += 
                "   \""+this.toString()+"\" [label=\"ins_if\"] ;\n" +
                "   \""+this.toString()+"et"+"\" [label=\"if\"] ;\n" +
                "   \""+this.toString()+"\" -> \""+this.toString()+"et"+"\"\n";
            temporal += 
                "   \""+condicion.toString()+"\" [label=\"expresion\"] ;\n" +
                "   \""+this.toString()+"\" -> \""+condicion.toString()+"\"\n"; 
            
            temporal += "\""+bloque_instrucciones.toString()+"\" [label=\"instrucciones\"] ;\n";
            temporal+= "\""+this.toString()+"\" -> \""+bloque_instrucciones.toString()+"\"\n";

            for(Instruccion ins:bloque_instrucciones){
                temporal += "\""+ins.toString()+"\" [label=\"instruccion\"] ;\n";
                temporal+= "\""+bloque_instrucciones.toString()+"\" -> \""+ins.toString()+"\"\n";

                temporal += ins.getArbol(ts);
            }
        }
        else{
            temporal += 
                "   \""+this.toString()+"\" [label=\"ins_else\"] ;\n" +
                "   \""+this.toString()+"et"+"\" [label=\"else\"] ;\n" +
                "   \""+this.toString()+"\" -> \""+this.toString()+"et"+"\"\n";
            
            temporal += "\""+bloque_instrucciones.toString()+"\" [label=\"instrucciones\"] ;\n";
            temporal+= "\""+this.toString()+"\" -> \""+bloque_instrucciones.toString()+"\"\n";

            for(Instruccion ins:bloque_instrucciones){
                temporal += "\""+ins.toString()+"\" [label=\"instruccion\"] ;\n";
                temporal+= "\""+bloque_instrucciones.toString()+"\" -> \""+ins.toString()+"\"\n";

                temporal += ins.getArbol(ts);
            }
        }
        
        return temporal;
    
    }
    
    public Boolean getValor_condicion() {
        return valor_condicion;
    }
    public void setValor_condicion(Boolean valor_condicion) {
        this.valor_condicion = valor_condicion;
    }
    public Boolean getIsElse() {
        return isElse;
    }
    public void setIsElse(Boolean isElse) {
        this.isElse = isElse;
    }
    public Instruccion getCondicion() {
        return condicion;
    }
    public void setCondicion(Instruccion condicion) {
        this.condicion = condicion;
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
