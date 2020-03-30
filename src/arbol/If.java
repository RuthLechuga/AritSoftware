package arbol;

import Entorno.TablaDeSimbolos;
import Utilidades.Mensaje;
import java.util.LinkedList;

public class If implements Instruccion {

    private LinkedList<Elseif> lista_elseif;
    
    public If(LinkedList<Elseif> lista_elseif){
        this.lista_elseif = lista_elseif;
    }

    @Override
    public Object ejecutar(TablaDeSimbolos ts, LinkedList<Mensaje> mensajes) {
        
        try{
            Object result;
            for(Elseif i: lista_elseif){
            result = i.ejecutar(ts, mensajes);
            
                if(i.getIsElse() || i.getValor_condicion())
                    return result;
            }
        }
        catch(Exception e){
        }

        return null;
    }

    @Override
    public String getArbol(TablaDeSimbolos ts) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public LinkedList<Elseif> getLista_elseif() {
        return lista_elseif;
    }
    public void setLista_elseif(LinkedList<Elseif> lista_elseif) {
        this.lista_elseif = lista_elseif;
    }
    
}
