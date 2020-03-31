package arbol;

import Entorno.TablaDeSimbolos;
import Utilidades.Mensaje;
import arbol.Operacion.tipo_operacion;
import java.util.LinkedList;

public class Case implements Instruccion{
    
    private Instruccion expresion;
    private LinkedList<Instruccion> bloque_instrucciones;
    private Instruccion expresion_switch;
    private int linea;
    private int columna;
    private Boolean isDefault;
    private Boolean isEquals;
    
    public Case(Instruccion expresion, LinkedList<Instruccion> instrucciones, int linea, int columna){
        this.expresion = expresion;
        this.bloque_instrucciones = instrucciones;
        this.linea = linea;
        this.columna = columna;
        this.isDefault = false;
        this.isEquals = false;
    }

    public Case(LinkedList<Instruccion> instrucciones, int linea, int columna){
        this.bloque_instrucciones = instrucciones;
        this.linea = linea;
        this.columna = columna;
        this.isDefault = true;
        this.isEquals = false;
    }
    
    @Override
    public Object ejecutar(TablaDeSimbolos ts, LinkedList<Mensaje> mensajes) {
        
        if(!this.isDefault){
            Operacion operar = new Operacion(tipo_operacion.IGUAL_QUE,expresion,expresion_switch,linea,columna);
            this.isEquals = (Boolean)operar.ejecutar(ts, mensajes);
        }
        
        if(isEquals || isDefault){
            Object result;
            
            for(Instruccion ins: bloque_instrucciones){
                result = ins.ejecutar(ts, mensajes);
                
                if(result != null)
                    return result;
            }
        }
        
        return null;
    }

    @Override
    public String getArbol(TablaDeSimbolos ts) {
        String temporal = "";
   
        if(!isDefault){
            temporal+= "   \""+this.toString()+"\" [label=\"ins_case\"] ;\n"+
                "   \""+this.toString()+"et"+"\" [label=\"case\"] ;\n" +
                "   \""+this.toString()+"\" -> \""+this.toString()+"et"+"\"\n";
        
            temporal+= expresion.getArbol(ts);
            temporal+= "   \""+this.toString()+"\" -> \""+expresion.toString()+"\"\n";

            temporal += "\""+bloque_instrucciones.toString()+"\" [label=\"instrucciones\"] ;\n";
            temporal+= "\""+this.toString()+"\" -> \""+bloque_instrucciones.toString()+"\"\n";

            for(Instruccion ins:bloque_instrucciones){
                temporal += "\""+ins.toString()+"\" [label=\"instruccion\"] ;\n";
                temporal+= "\""+bloque_instrucciones.toString()+"\" -> \""+ins.toString()+"\"\n";

                temporal += ins.getArbol(ts);
            }
        }
        else{
            temporal+= "   \""+this.toString()+"\" [label=\"ins_case\"] ;\n"+
                "   \""+this.toString()+"et"+"\" [label=\"default\"] ;\n" +
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

    public Instruccion getExpresion() {
        return expresion;
    }
    public void setExpresion(Instruccion expresion) {
        this.expresion = expresion;
    }
    public LinkedList<Instruccion> getInstrucciones() {
        return bloque_instrucciones;
    }
    public void setInstrucciones(LinkedList<Instruccion> instrucciones) {
        this.bloque_instrucciones = instrucciones;
    }
    public Instruccion getExpresion_switch() {
        return expresion_switch;
    }
    public void setExpresion_switch(Instruccion expresion_switch) {
        this.expresion_switch = expresion_switch;
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
    public Boolean getIsDefault() {
        return isDefault;
    }
    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }
    public Boolean getIsEquals() {
        return isEquals;
    }
    public void setIsEquals(Boolean isEquals) {
        this.isEquals = isEquals;
    }    
}
