package arbol;

import Entorno.TablaDeSimbolos;
import Utilidades.Mensaje;
import java.util.LinkedList;

public class Null implements Instruccion{

    @Override
    public Object ejecutar(TablaDeSimbolos ts, LinkedList<Mensaje> mensajes) {
        return this;
    }

    @Override
    public String getArbol(TablaDeSimbolos ts) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String toString(){
        return "NULL";
    }
}
