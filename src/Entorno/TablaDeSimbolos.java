package Entorno;

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
                if(s.getIdentificador().toLowerCase().compareTo(identificador.toLowerCase()) == 0)
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
    
//    public void addSymbol(Simbolo nuevo){
//        for(Simbolo s: this.local){
//         if(s.getIdentificador().compareTo(nuevo.getIdentificador()) == 0)
//            {
//                this.local.remove(s);
//                this.local.add(nuevo);
//                return;
//            }    
//        }
//        
//        this.local.add(nuevo);
//    }
    
    public void addSymbol(Simbolo nuevo){
        TablaDeSimbolos local = this;
        
        while(local!=null){
            for(Simbolo s: local.getLocal()){
                if(s.getIdentificador().compareTo(nuevo.getIdentificador()) == 0)
                {
                    local.getLocal().remove(s);
                    local.getLocal().add(nuevo);
                    return;
                }    
            }
            local = local.getPadre();
        }
        
        
        this.local.add(nuevo);
    }
    
    public Simbolo removeSymbol(String identificador){
        TablaDeSimbolos temporal = this;
        
        while(temporal != null){
            for(Simbolo s: temporal.local){
                if(s.getIdentificador().toLowerCase().compareTo(identificador.toLowerCase()) == 0)
                    temporal.local.remove(s);
            }
            
            temporal = temporal.padre;
        }
        
        return null;
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