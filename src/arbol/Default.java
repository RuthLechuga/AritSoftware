package arbol;

import Entorno.TablaDeSimbolos;
import Utilidades.Mensaje;
import java.util.LinkedList;

public class Default implements Instruccion{
    
    int linea;
    int columna;
    
    public Default(int linea, int columna){
        this.linea = linea;
        this.columna = columna;
    }

    @Override
    public Object ejecutar(TablaDeSimbolos ts, LinkedList<Mensaje> mensajes) {
        return this;
    }

    @Override
    public String getArbol(TablaDeSimbolos ts) {
        String temporal = 
                "   \""+this.toString()+"\" [label=\"default\"] ;\n"
        ;
        return temporal;
    }
    
}
