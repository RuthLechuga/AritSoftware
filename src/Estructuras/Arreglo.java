package Estructuras;

import Entorno.TablaDeSimbolos;
import Utilidades.Mensaje;
import arbol.Instruccion;
import java.util.LinkedList;

public class Arreglo implements Instruccion {

    private LinkedList<Object> arreglo;
    private LinkedList dimensiones;
    Boolean isList = false;
    
    public Arreglo(LinkedList<Object> arreglo, LinkedList dimensiones){
        this.arreglo = arreglo;
        this.dimensiones = dimensiones;
    }
    
    public Arreglo(LinkedList<Object> arreglo, LinkedList dimensiones, Boolean isList){
        this.arreglo = arreglo;
        this.dimensiones = dimensiones;
        this.isList = isList;
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
        
        if(dimensiones.size()==1){
            return arreglo.toString();
        }
        
        if(dimensiones.size()==2){
            return toString(0,Integer.parseInt(dimensiones.get(0).toString()),Integer.parseInt(dimensiones.get(1).toString()));
        }
        
        int dimension_y = this.getDimensionX();
        int dimension_x = this.getDimensionY();
        
        if(dimensiones.size()==3){
            int tercera_dimension=Integer.parseInt(dimensiones.get(2).toString());
            String temporal="";
            
            for(int i=0;i<tercera_dimension;i++){
                temporal+= "<p>, , "+(i+1)+"</p>";
                temporal+= this.toString(i*dimension_x*dimension_y,dimension_x,dimension_y);
            }
            
            return temporal;
        }
        
        if(dimensiones.size()==4){
            int tercera_dimension=Integer.parseInt(dimensiones.get(2).toString());
            int cuarta_dimension=Integer.parseInt(dimensiones.get(3).toString());
            
            String temporal="";
            
            for(int i=0;i<cuarta_dimension;i++){
                for(int j=0;j<tercera_dimension;j++){
                    temporal+= "<p>, , "+(j+1)+", "+(i+1)+"</p>";
                    if(i==2 && j==3)
                        System.out.println("pruebita");    
                    temporal+= this.toString(i*tercera_dimension*dimension_x*dimension_y+j*dimension_x*dimension_y,dimension_x,dimension_y);   
                }
            }
            
            return temporal;
        }
        
        if(dimensiones.size()==5){
            int tercera_dimension=Integer.parseInt(dimensiones.get(2).toString());
            int cuarta_dimension=Integer.parseInt(dimensiones.get(3).toString());
            int quinta_dimension=Integer.parseInt(dimensiones.get(4).toString());
            
            String temporal="";
            
            for(int i=0;i<quinta_dimension;i++){
                for(int j=0;j<cuarta_dimension;j++){
                    for(int k=0;k<tercera_dimension;k++){
                        temporal+= "<p>, , "+(k+1)+", "+(j+1)+", "+(i+1)+"</p>";
                        temporal+= this.toString(i*j*k*dimension_x*dimension_y,dimension_x,dimension_y); 
                    }
                }
            }
            
            return temporal;
        }
        
        if(dimensiones.size()==6){
            int tercera_dimension=Integer.parseInt(dimensiones.get(2).toString());
            int cuarta_dimension=Integer.parseInt(dimensiones.get(3).toString());
            int quinta_dimension=Integer.parseInt(dimensiones.get(4).toString());
            int sexta_dimension=Integer.parseInt(dimensiones.get(5).toString());
            
            String temporal="";
            
            for(int i=0;i<sexta_dimension;i++){
                for(int j=0;j<quinta_dimension;j++){
                    for(int k=0;k<cuarta_dimension;k++){
                        for(int l=0;l<tercera_dimension;l++){
                            temporal+= "<p>, , "+(l+1)+", "+(k+1)+", "+(j+1)+", "+(i+1)+"</p>";
                            temporal+= this.toString(i*j*k*l*dimension_x*dimension_y,dimension_x,dimension_y);
                        } 
                    }
                }
            }
            
            return temporal;
        }
        
        return "";
    }
    
    public String toString(int indicei, int dimension_x, int dimension_y){
        String temporal = "";
        temporal+="<table><tr>";
        temporal+= "<th></th>";
        for(int columna=1;columna<=dimension_x;columna++){
            temporal+= "<th>Columna "+columna+"</th>";
            
        }
        temporal+="</tr>";
        
        for(int fila=1;fila<=dimension_y;fila++){
            temporal+="<tr>";
            temporal+="<td>Fila "+fila+"</td>";
            
            for(int columna=1;columna<=dimension_x;columna++){
                temporal += "<td>"+arreglo.get(indicei+((columna-1)*dimension_y+fila)-1)+ "</td>";
            }
            temporal+="</tr>";
        }
        temporal+="</table>";
        
        return temporal;
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

    public void setValor(TablaDeSimbolos ts, LinkedList accesos_ins, Object valor){
        
        if(!isList && valor instanceof Lista)
            convertToList();
        
        else if(isList && !(valor instanceof Lista)){
            Object t = valor;
            valor = new Lista(new LinkedList<>());  
            ((Lista)valor).getLista().add(t);
        }
            
        
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
        
        arreglo.set(pos-1, valor);
        
    }

    private void convertToList() {
        for(int i=0;i<arreglo.size();i++){
            Lista list = new Lista(new LinkedList<>());
            list.getLista().add(arreglo.get(i));
            arreglo.set(i, list);
        }
        isList = true;
    }

}
