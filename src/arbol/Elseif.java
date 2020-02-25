package arbol;

import Entorno.TablaDeSimbolos;
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
            valor_condicion = (condicion == null)? false: (Boolean)condicion.ejecutar(ts, mensajes);
        }catch(Exception e){
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Expresión inválida."));
            return null;
        }
        
        if(valor_condicion || isElse){
            Object result = null;
            for(Instruccion ins: bloque_instrucciones){
                result = ins.ejecutar(ts, mensajes);
                
                if(ins instanceof Return)
                    return result;
            }
        }
        
        return null;
    }

    @Override
    public String getArbol(TablaDeSimbolos ts) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
