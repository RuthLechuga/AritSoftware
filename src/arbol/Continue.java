package arbol;

import Entorno.TablaDeSimbolos;
import Utilidades.Mensaje;
import java.util.LinkedList;

public class Continue implements Instruccion {

    @Override
    public Object ejecutar(TablaDeSimbolos ts, LinkedList<Mensaje> mensajes) {
        return this;
    }

    @Override
    public String getArbol(TablaDeSimbolos ts) {
        String temporal = 
                "   \""+this.toString()+"\" [label=\"ins_continue\"] ;\n" +
                "   \""+this.toString()+"c\" [label=\"continue\"] ;\n" +
                "   \""+this.toString()+"\" -> \""+this.toString()+"c\"\n"
        ;
        return temporal;
    }
    
}
