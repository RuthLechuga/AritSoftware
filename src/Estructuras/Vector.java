package Estructuras;

import Entorno.Tipo;
import java.util.LinkedList;

public class Vector {
    
    private LinkedList<Object> vector;
    private Tipo tipo_dato;
    
    public Vector(LinkedList<Object> vector){
        this.vector = vector;
    }
    
    public Vector(LinkedList<Object> vector, Tipo tipo_dato){
        this.vector = vector;
        this.tipo_dato = tipo_dato;
    }
    
    @Override
    public String toString(){
        return vector.toString();
    }
    
    public int size(){
        return vector.size();
    }
    
    public void castear(){
        
    }
    
    public LinkedList<Object> getVector() {
        return vector;
    }
    public void setVector(LinkedList<Object> vector) {
        this.vector = vector;
    }   
    public Tipo getTipo_dato() {
        return tipo_dato;
    }
    public void setTipo_dato(Tipo tipo_dato) {
        this.tipo_dato = tipo_dato;
    }
  
}
