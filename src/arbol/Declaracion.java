package arbol;

import Entorno.Simbolo;
import Entorno.TablaDeSimbolos;
import Utilidades.Mensaje;
import java.util.LinkedList;

public class Declaracion implements Instruccion {
    
    String identificador;
    Instruccion expresion;
    int linea;
    int columna;
    
    public Declaracion(String identificador, Instruccion expresion, int linea, int columna){
        this.identificador = identificador;
        this.expresion = expresion;
        this.linea = linea;
        this.columna = columna;
    }

    @Override
    public Object ejecutar(TablaDeSimbolos ts, LinkedList<Mensaje> mensajes) {
        Simbolo nuevo = new Simbolo(identificador,expresion.ejecutar(ts, mensajes));
        ts.addSymbol(nuevo);
        return null;
    }

    @Override
    public String getArbol(TablaDeSimbolos ts) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
