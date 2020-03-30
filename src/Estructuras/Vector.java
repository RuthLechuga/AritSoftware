package Estructuras;

import Entorno.Tipo;
import arbol.Null;
import java.util.LinkedList;

public class Vector {
    
    private LinkedList<Object> vector;
    private Tipo tipo_dato;
    
    public Vector(LinkedList<Object> vector){
        this.vector = vector;
        castear();
    }
    
    public Vector(LinkedList<Object> vector, Tipo tipo_dato){
        this.vector = vector;
        this.tipo_dato = tipo_dato;
        castear();
        
    }
    
    @Override
    public String toString(){
        return vector.toString();
    }
    
    public int size(){
        return vector.size();
    }
    
    public int castear(){
        int prioridad = establecer_prioridad();
        LinkedList temporal = new LinkedList<>();
        
        switch (prioridad) {
            case 1:
                vector.forEach((obj) -> {
                    
                    if(obj instanceof Boolean)
                        temporal.add(((Boolean)obj)? 1:0);
                    else
                        temporal.add(((int)obj));
                });
                break;
            case 2:
                vector.forEach((obj) -> {
                    if(obj instanceof Boolean)
                        temporal.add(((Boolean)obj)? 1:0);
                    else
                        temporal.add(((double)obj));
                });
                break;
            case 3:
                vector.forEach((obj) -> {
                    if(!(obj instanceof Null))
                        temporal.add(obj.toString());
                    else
                        temporal.add(obj);
                });
                break;
            default:
                return prioridad;
        }
        
        vector = temporal;
        return prioridad;
    }

    public int establecer_prioridad(){
        int prioridad=0;
        
        for(Object obj: vector){
            if(obj instanceof Integer && prioridad<1)
            {
                prioridad=1;
            }
            else if(obj instanceof Double && prioridad<2)
            {
                prioridad=2;
            }
            else if(obj instanceof String && prioridad<3){
                prioridad=3;
                break;
            }
        }
        return prioridad;
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
