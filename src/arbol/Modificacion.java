package arbol;

import Entorno.Simbolo;
import Entorno.TablaDeSimbolos;
import static Entorno.Tipo.tipo_primitivo.VECTOR;
import Estructuras.Lista;
import Estructuras.Vector;
import Utilidades.Mensaje;
import static Utilidades.Mensaje.tipo_mensaje.SEMANTICO;
import java.util.LinkedList;

public class Modificacion implements Instruccion{

    private String identificador;
    private LinkedList<Instruccion> accesos;
    private Instruccion expresion;
    private int linea;
    private int columna;
    
    public Modificacion(String identificador, LinkedList<Instruccion> accesos, Instruccion expresion, int linea, int columna){
        this.identificador = identificador;
        this.accesos = accesos;
        this.expresion = expresion;
        this.linea = linea;
        this.columna = columna;
    }

    @Override
    public Object ejecutar(TablaDeSimbolos ts, LinkedList<Mensaje> mensajes) {
        
        Simbolo s = ts.getSymbol(identificador);
        
        Object temporal = null;
        
        if(s.getValor() instanceof Lista)
            temporal = ((Lista)s.getValor()).getLista();
        else if(s.getValor() instanceof Vector)
            temporal = ((Vector)s.getValor()).getVector();
        else
        {
            temporal = new LinkedList<Object>();
            ((LinkedList)temporal).add(s.getValor());
        }
        
        int posicion = -1;
            
        for(int i=0;i<accesos.size()-1;i++){
            posicion = obtenerPosicion(accesos.get(i),ts,mensajes);
            
            if(posicion == -1)
                return null;
            
            //hacer procedimiento para listas/arreglos/matrices que tienen dentro otra estructura
        }
        
        posicion = obtenerPosicion(accesos.get(accesos.size()-1),ts,mensajes);
           
        if(posicion>=0){
            
            if(posicion>((LinkedList)temporal).size()){
                for(int i=((LinkedList)temporal).size();i<=posicion;i++)
                    ((LinkedList)temporal).add(new Null());
            }
            
            ((LinkedList)temporal).set(posicion, expresion.ejecutar(ts, mensajes));
            
            if(s.getValor() instanceof Lista){
                ts.addSymbol(new Simbolo(s.getIdentificador(),new Lista((LinkedList<Object>) temporal)));
            }
            else if(s.getValor() instanceof Vector){
                ts.addSymbol(new Simbolo(s.getIdentificador(),new Vector((LinkedList<Object>) temporal)));
            }
            else{
                ts.addSymbol(new Simbolo(s.getIdentificador(),new Vector((LinkedList<Object>) temporal)));
            }
            
        }        
        else{
            return null;
        }
        
            
        return null;
    }
    
    public int obtenerPosicion(Instruccion acceso, TablaDeSimbolos ts, LinkedList<Mensaje> mensajes){
         try{
            return (int)acceso.ejecutar(ts, mensajes)-1;
         }
         catch(Exception e){
             mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Acceso incorrecto a la estructura"));
             return -1;
         }
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
    public LinkedList<Instruccion> getAccesos() {
        return accesos;
    }
    public void setAccesos(LinkedList<Instruccion> accesos) {
        this.accesos = accesos;
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
