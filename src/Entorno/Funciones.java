package Entorno;

import arbol.Function;
import java.util.LinkedList;

public class Funciones {

    private LinkedList<Function> lista_funciones;
    private static Funciones stFunciones;
    
    private Funciones(){
        this.lista_funciones = new LinkedList<>();
    }
    
    public  static Funciones getSingletonInstance() {

        if (stFunciones == null)
            stFunciones = new Funciones();
        
        return stFunciones ;
    }
    
    public void reiniciar(){
        this.lista_funciones = new LinkedList<>();
    }
    
    public Boolean addFunction(Function nueva){
        
        for(Function f: lista_funciones){           
            //se compara que no exista una funcion con el mismo nombre
            if(f.getNombre().compareTo(nueva.getNombre())==0){
                if(f.getParametros().size() == nueva.getParametros().size())
                    return false;
            }
        }
        
        lista_funciones.add(nueva);
        return true;
    }
    
    public Function getFunction(String nombre, int num_parametros){
        
        for(Function f: lista_funciones){           
            //se compara que no exista una funcion con el mismo nombre
            if(f.getNombre().compareTo(nombre)==0){
                if(f.getParametros().size() == num_parametros)
                    return f;
            }
        }
        
        return null;
    }

    public LinkedList<Function> getLista_funciones() {
        return lista_funciones;
    }
    public void setLista_funciones(LinkedList<Function> lista_funciones) {
        this.lista_funciones = lista_funciones;
    }       
}
