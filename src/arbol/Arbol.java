package arbol;

import Entorno.Funciones;
import Entorno.TablaDeSimbolos;
import Utilidades.Mensaje;
import java.util.LinkedList;

public class Arbol{
    
    LinkedList<Instruccion> instrucciones;
    LinkedList<Mensaje> mensajes;
    private TablaDeSimbolos tsglobal;
    
    public Arbol(LinkedList<Instruccion> instrucciones){
        this.instrucciones = instrucciones;
        this.tsglobal = new TablaDeSimbolos(null);
        this.mensajes = new LinkedList<>();
        Funciones instancia = Funciones.getSingletonInstance();
    }

    public void ejecutar(){
        
        for(Instruccion ins:instrucciones){
            ins.ejecutar(getTsglobal(), mensajes);
        }
            
        for(Mensaje mensaje: mensajes)
            System.out.println(mensaje.getDescripcion());
    }
    
    public void reporteAST(){
        String temporal = "";
        
        for(Instruccion ins:instrucciones){
            temporal += ins.getArbol(getTsglobal());
        }
    }


    public TablaDeSimbolos getTsglobal() {
        return tsglobal;
    }
    public void setTsglobal(TablaDeSimbolos tsglobal) {
        this.tsglobal = tsglobal;
    }

}
