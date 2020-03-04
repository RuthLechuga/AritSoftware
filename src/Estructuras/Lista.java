package Estructuras;

import java.util.LinkedList;

public class Lista {
    
    private LinkedList<Object> lista;
    
    public Lista(LinkedList<Object> lista){
        this.lista = lista;
    }
    
    @Override
    public String toString(){
        return lista.toString();
    }
    
    public int size(){
        return lista.size();
    }
    
    public LinkedList<Object> getLista() {
        return lista;
    }
    public void setLista(LinkedList<Object> lista) {
        this.lista = lista;
    }   
    
}
