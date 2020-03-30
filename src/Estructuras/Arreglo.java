package Estructuras;

import Entorno.TablaDeSimbolos;
import Utilidades.Mensaje;
import arbol.Instruccion;
import java.util.LinkedList;

public class Arreglo implements Instruccion {

    private LinkedList<Object> arreglo;
    private LinkedList dimensiones;
    
    public Arreglo(LinkedList<Object> arreglo, LinkedList dimensiones){
        this.arreglo = arreglo;
        this.dimensiones = dimensiones;
    }

    @Override
    public Object ejecutar(TablaDeSimbolos ts, LinkedList<Mensaje> mensajes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getArbol(TablaDeSimbolos ts) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public LinkedList<Object> getArreglo() {
        return arreglo;
    }
    public void setArreglo(LinkedList<Object> arreglo) {
        this.arreglo = arreglo;
    }
    public LinkedList getDimensiones() {
        return dimensiones;
    }
    public void setDimensiones(LinkedList dimensiones) {
        this.dimensiones = dimensiones;
    }
        
    public int getDimensionX(){
        try{
            return ((int)dimensiones.get(0));   
        }
        catch(Exception e){
            return 0;
        }
    }
    
    public int getDimensionY(){
        try{
            return ((int)dimensiones.get(1));   
        }
        catch(Exception e){
            return 0;
        }
    }

    public int getDimension(){
        
        int total=1;
        
        for(int i=0;i<dimensiones.size();i++)
            total*= ((int)dimensiones.get(i));
        
        return total;
    }
    
    @Override
    public String toString(){
        
        LinkedList contadores = new LinkedList<>(); 
        for(int i=0;i<dimensiones.size()-2;i++)
            contadores.add(0);
        
        for(int dimension_actual=0;dimension_actual<dimensiones.size();dimension_actual++){
            
            
            
        }
        
        return "";
    }
    
    public Object getValor(TablaDeSimbolos ts, LinkedList accesos_ins){
        
        LinkedList accesos = new LinkedList<>();
        
        for(int i=0;i<accesos_ins.size();i++)
            accesos.add(((Instruccion)accesos_ins.get(i)).ejecutar(ts, dimensiones));
        
        int pos = 0;
        int tamdim;
        
        for(int i=accesos.size()-1;i>0;i--){
            tamdim = 1;
            
            for(int j=i-1;j>=0;j--)
                tamdim *= ((int)dimensiones.get(j));
            
            pos += tamdim*(((int)accesos.get(i))-1);
        }
        
        pos += ((int)accesos.get(0));
        
        return arreglo.get(pos-1);
    }
}
