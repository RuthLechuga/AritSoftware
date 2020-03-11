package Estructuras;

import Utilidades.Mensaje;
import static Utilidades.Mensaje.tipo_mensaje.SEMANTICO;
import java.util.LinkedList;

public class Matriz {

    private LinkedList<Object> matriz;
    private int dimension_x;
    private int dimension_y;
    
    public Matriz(LinkedList<Object> matriz, int dimension_x, int dimension_y){
        this.matriz = matriz;
        this.dimension_x = dimension_x;
        this.dimension_y = dimension_y;
    }
    
    public LinkedList<Object> getMatriz() {
        return matriz;
    }
    public void setMatriz(LinkedList<Object> matriz) {
        this.matriz = matriz;
    } 
    public int getDimension_x() {
        return dimension_x;
    }
    public void setDimension_x(int dimension_x) {
        this.dimension_x = dimension_x;
    }
    public int getDimension_y() {
        return dimension_y;
    }
    public void setDimension_y(int dimension_y) {
        this.dimension_y = dimension_y;
    }

    public void setValor(int p_x, int p_y, Object valor){
        matriz.set(((p_x-1)*dimension_y+p_y)-1,valor);
    }
    
    public void setFila(int fila, Object vector, LinkedList<Mensaje> mensajes, int linea, int columna){
        
        try{
           if(vector instanceof Vector){
               
               LinkedList<Object> temporal = (((Vector) vector).getVector());
               
               if(temporal.size() != 1){
                    
                   if(temporal.size() == dimension_x){
                       
                       int cont = 0;
                       
                        for(int i=fila-1;i<matriz.size();i=i+dimension_y){
                            matriz.set(i,temporal.get(cont));
                            cont++;
                        }    
                   }
                   else
                       mensajes.add(new Mensaje(linea,columna,SEMANTICO,"No se puede modificar la matriz, ya que el arreglo a insertar no cumple con las dimensiones."));
                   
                              
                    return;
               }   
               else
                   vector = temporal.get(0);
           }
           
           for(int i=fila-1;i<matriz.size();i=i+dimension_y){
                    matriz.set(i,vector);
           }   
        }
        catch(Exception e){
            
        }   
    }
    
    public void setColumna(int col, Object vector, LinkedList<Mensaje> mensajes, int linea, int columna){
        
        try{
            int c=0; 
            
            if(vector instanceof Vector){
               
               LinkedList<Object> temporal = (((Vector) vector).getVector());
               
               if(temporal.size() != 1){
                    
                   if(temporal.size() == dimension_y){
                       
                        c=1;
                        
                        for(int i=(col-1)*dimension_y;c<=dimension_y;i++){
                            matriz.set(i, temporal.get(c-1));
                            c++;
                        }    
                   }
                   else
                       mensajes.add(new Mensaje(linea,columna,SEMANTICO,"No se puede modificar la matriz, ya que el arreglo a insertar no cumple con las dimensiones."));
                   
                              
                    return;
               }   
               else
                   vector = temporal.get(0);
           }
           
            c=1;
            for(int i=(col-1)*dimension_y;c<=dimension_y;i++){
                matriz.set(i, vector);
                c++;
            }   
        }
        catch(Exception e){
            
        }   
    }
    
    public Object getValor(int p_x, int p_y){
        return matriz.get(((p_x-1)*dimension_y+p_y)-1);
    }
    
    public LinkedList<Object> getFila(int fila){
        
        LinkedList<Object> temporal = new LinkedList<Object>();
        
        for(int i=fila-1;i<matriz.size();i=i+dimension_y){
            temporal.add(matriz.get(i));
        }
        
        return temporal;
    }
    
    public LinkedList<Object> getColumna(int columna){
        
        LinkedList<Object> temporal = new LinkedList<Object>();
        
        int c = 1;
        
        for(int i=(columna-1)*dimension_y;c<=dimension_y;i++){
            temporal.add(matriz.get(i));
            c++;
        }
        
        return temporal;
    }
    
    @Override
    public String toString(){
        String temporal = "";
        
        
        for(int fila=1;fila<=dimension_y;fila++){
            for(int columna=1;columna<=dimension_x;columna++){
                temporal += matriz.get(((columna-1)*dimension_y+fila)-1)+ "  ";
                
            }
            temporal += "\n";
        }
        
        
        return temporal;
    }
    
}
