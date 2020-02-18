package Entorno;

import Utilidades.Mensaje;
import java.util.LinkedList;

public class TablaDeSimbolos{
   
    private LinkedList<Simbolo> local;
    private TablaDeSimbolos padre;

    public TablaDeSimbolos(TablaDeSimbolos padre) {
        this.local = new LinkedList<>();
        this.padre = padre;
    }
    
    public Simbolo getSymbol(String identificador){
        TablaDeSimbolos temporal = this;
        
        while(temporal != null){
            for(Simbolo s: temporal.local){
                if(s.getIdentificador().compareTo(identificador) == 0)
                    return s;
            }
            
            temporal = temporal.padre;
        }
        
        return null;
    }
    
    public TablaDeSimbolos getGlobal(){
        TablaDeSimbolos global = this;
        
        while(global.padre != null)
            global = global.padre;
        
        return global;
    }
    
    public boolean existLocalSymbol(String identificador){
        
        for(Simbolo s: this.local)
            if(s.getIdentificador().compareTo(identificador) == 0)
                return true;
        
        return false;
    }
    
    public void addSymbol(Simbolo nuevo, LinkedList<Mensaje> mensajes){
        
        if(!existLocalSymbol(nuevo.getIdentificador()))
            this.local.add(nuevo);
        else{
            
            for(Simbolo s: this.local)
                if(s.getIdentificador().compareTo(nuevo.getIdentificador()) == 0)
                {
                    this.local.remove(s);
                    this.local.add(s);
                }    
        }
    }
    
    public LinkedList<Simbolo> getLocal() {
        return local;
    }
    public void setLocal(LinkedList<Simbolo> local) {
        this.local = local;
    }
    public TablaDeSimbolos getPadre() {
        return padre;
    }
    public void setPadre(TablaDeSimbolos padre) {
        this.padre = padre;
    }    
   
}