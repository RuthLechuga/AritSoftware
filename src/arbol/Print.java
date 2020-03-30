package arbol;

import Entorno.TablaDeSimbolos;
import Utilidades.Mensaje;
import static Utilidades.Mensaje.tipo_mensaje.*;
import java.util.LinkedList;

public class Print implements Instruccion {
   
    private Instruccion mensaje;
    private int linea;
    private int columna;
    
    public Print(Instruccion mensaje, int linea, int columna){
        this.mensaje = mensaje;
        this.linea = linea;
        this.columna = columna;
    }

    @Override
    public Object ejecutar(TablaDeSimbolos ts, LinkedList<Mensaje> mensajes) {
        Object resultado = mensaje.ejecutar(ts, mensajes);
        
        /*if(resultado.toString().compareTo("debuguear")==0)
        { 
            if(true) System.out.println(":D");
        }*/
        
        if(resultado instanceof Error || resultado == null)
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Ha sucedido un error procesando el mensaje en el print."));
        else
            mensajes.add(new Mensaje(linea,columna,MENSAJE,resultado.toString()));
        
        return null;
    }

    @Override
    public String getArbol(TablaDeSimbolos ts) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Instruccion getMensaje() {
        return mensaje;
    }
    public void setMensaje(Instruccion mensaje) {
        this.mensaje = mensaje;
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
