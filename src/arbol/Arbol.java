package arbol;

import Entorno.Funciones;
import Entorno.TablaDeSimbolos;
import Utilidades.Graficas;
import Utilidades.Mensaje;
import java.util.LinkedList;

public class Arbol{
    
    LinkedList<Instruccion> instrucciones;
    private LinkedList<Mensaje> mensajes;
    private TablaDeSimbolos tsglobal;
    
    public Arbol(LinkedList<Instruccion> instrucciones){
        this.instrucciones = instrucciones;
        this.tsglobal = new TablaDeSimbolos(null);
        this.mensajes = new LinkedList<>();
        Funciones instancia = Funciones.getSingletonInstance();
    }

    public void ejecutar(){  
        Funciones instancia = Funciones.getSingletonInstance();
        instancia.reiniciar();
        
        Graficas i_graficas = Graficas.getSingletonInstance();
        i_graficas.reiniciar();
        
        LinkedList<Instruccion> new_instrucciones = new LinkedList<Instruccion>();
        
        //PRIMERA PASADA PARA FUNCIONES
        for(Instruccion ins:instrucciones){
            if(ins instanceof Function){
                ins.ejecutar(getTsglobal(), getMensajes()); 
            }
            else{
                new_instrucciones.add(ins);
            }
        }
         
        //SEGUNDA PASADA PARA EL RESTO
        for(Instruccion ins:new_instrucciones){
            ins.ejecutar(getTsglobal(), getMensajes());
        }

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
    public LinkedList<Mensaje> getMensajes() {
        return mensajes;
    }
    public void setMensajes(LinkedList<Mensaje> mensajes) {
        if(mensajes.size() > 0)
            this.mensajes.addAll(mensajes);
    }

}
