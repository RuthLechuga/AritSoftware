package arbol;

import Entorno.Simbolo;
import Entorno.TablaDeSimbolos;
import Estructuras.Lista;
import Estructuras.Vector;
import Utilidades.Mensaje;
import static Utilidades.Mensaje.tipo_mensaje.SEMANTICO;
import java.util.LinkedList;

public class Acceso implements Instruccion {

    private String nombre_estructura;
    private LinkedList<Instruccion> accesos;
    private int linea;
    private int columna;
    
    public Acceso(String nombre_estructura, LinkedList<Instruccion> accesos, int linea, int columna){
        this.nombre_estructura = nombre_estructura;
        this.accesos = accesos;
        this.linea = linea;
        this.columna = columna;
    }

    @Override
    public Object ejecutar(TablaDeSimbolos ts, LinkedList<Mensaje> mensajes) {
    
        Simbolo s = ts.getSymbol(nombre_estructura);
        
        if(s != null){
            
            Object temporal = s.getValor();
            
            for(Instruccion ins: accesos){
                int posicion = 1;
                try{
                   posicion = (int)ins.ejecutar(ts, mensajes);
                }
                catch(Exception e){
                    mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Acceso incorrecto a la estructura"));
                    return null;
                }
                
                posicion -=1;
                
                if(temporal instanceof Lista)
                    temporal = ((Lista)temporal).getLista();
                
                else if(temporal instanceof Vector)
                    temporal = ((Vector)temporal).getVector();
                
                if(temporal instanceof LinkedList){
                    
                    if(posicion >=0 && posicion<((LinkedList)temporal).size()){                    
                        temporal = ((LinkedList)temporal).get(posicion);   
                    }
                    else{
                        mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Acceso incorrecto a la estructura"));
                        return null;
                    }
                }
                else if(posicion != 0){
                    mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Acceso incorrecto a la estructura"));
                    return null;
                }
            }
            
            return temporal;
            
        }
        else
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"La estructura:"+nombre_estructura+" no existe."));
        
        return null;
    }

    @Override
    public String getArbol(TablaDeSimbolos ts) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getNombre_estructura() {
        return nombre_estructura;
    }
    public void setNombre_estructura(String nombre_estructura) {
        this.nombre_estructura = nombre_estructura;
    }
    public LinkedList<Instruccion> getAccesos() {
        return accesos;
    }
    public void setAccesos(LinkedList<Instruccion> accesos) {
        this.accesos = accesos;
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
