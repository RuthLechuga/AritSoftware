package arbol;

import Entorno.TablaDeSimbolos;
import Utilidades.Mensaje;
import java.util.LinkedList;

public interface Instruccion {

    public Object ejecutar(TablaDeSimbolos ts, LinkedList<Mensaje> mensajes);
    
    public String getArbol(TablaDeSimbolos ts);
}
