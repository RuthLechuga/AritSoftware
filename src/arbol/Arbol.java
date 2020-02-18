package arbol;

import Entorno.TablaDeSimbolos;
import Utilidades.Mensaje;
import java.util.LinkedList;

public class Arbol{
    
    LinkedList<Instruccion> instrucciones;
    LinkedList<Mensaje> mensajes;
    TablaDeSimbolos tsglobal;
    
    public Arbol(LinkedList<Instruccion> instrucciones){
        this.instrucciones = instrucciones;
        this.tsglobal = new TablaDeSimbolos(null);
        this.mensajes = new LinkedList<>();
    }

    public void ejecutar(){
        for(Instruccion ins:instrucciones){
            ins.ejecutar(tsglobal, mensajes);
        }
        
        for(Mensaje mensaje: mensajes)
            System.out.println(mensaje.getDescripcion());
    }
    
    public void reporteAST(){
        String temporal = "";
        
        for(Instruccion ins:instrucciones){
            temporal += ins.getArbol(tsglobal);
        }
    }
}
