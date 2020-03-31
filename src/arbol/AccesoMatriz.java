package arbol;

import Entorno.Simbolo;
import Entorno.TablaDeSimbolos;
import Estructuras.Matriz;
import Estructuras.Vector;
import Utilidades.Mensaje;
import static Utilidades.Mensaje.tipo_mensaje.SEMANTICO;
import java.util.LinkedList;

public class AccesoMatriz implements Instruccion{
    
    private String identificador;
    private int linea;
    private int columna;
    private Instruccion x;
    private Instruccion y;
    
    public AccesoMatriz(String identificador, Instruccion x, Instruccion y, int linea, int columna){
        this.identificador = identificador;
        this.x = x;
        this.y = y;
        this.linea = linea;
        this.columna = columna;
    } 

    @Override
    public Object ejecutar(TablaDeSimbolos ts, LinkedList<Mensaje> mensajes) {
    
        try{
            
            Simbolo s = ts.getSymbol(identificador);
            
            if(s != null){
                
                Object temporal = s.getValor();
                LinkedList<Object> lt = new LinkedList<Object>();
                
                if(temporal instanceof Matriz){
                    
                    //Acceso tipo 1
                    if(x != null && y != null){
                        int posx = ((int)x.ejecutar(ts, mensajes));
                        int posy = ((int)y.ejecutar(ts, mensajes));
                        lt.add(((Matriz)temporal).getValor(posy,posx));
                    }
                    
                    //Acceso tipo 2
                    else if(x != null && y == null){
                        int posx = ((int)x.ejecutar(ts, mensajes));
                        lt.addAll(((Matriz)temporal).getFila(posx));
                    }
                    
                    //Acceso tipo 3
                    else{
                        int posy = ((int)y.ejecutar(ts, mensajes));
                        lt.addAll(((Matriz)temporal).getColumna(posy));
                    }
                    
                    return new Vector(lt);
                }
                else{
                    mensajes.add(new Mensaje(linea,columna,SEMANTICO,"El tipo de acceso solo puede realizarse sobre matrices."));
                }
                
            }
            else{
                mensajes.add(new Mensaje(linea,columna,SEMANTICO,"El arreglo:"+identificador+" no se ha encontrado."));
            }
            
            return null;
        }
        catch(Exception e){
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"No se ha podido realizar el acceso al arreglo."));
            return null;
        }
    
    }

    @Override
    public String getArbol(TablaDeSimbolos ts) {
        String temporal = 
                "   \""+this.toString()+"\" [label=\"acceso\"] ;\n" +
                "   \""+this.toString()+"et"+"\" [label=\""+identificador+"\"] ;\n" +
                "   \""+this.toString()+"\" -> \""+this.toString()+"et"+"\"\n";
        
        if(x!=null){
            temporal+= x.getArbol(ts);
            temporal+= "   \""+this.toString()+"\" -> \""+x.toString()+"\"\n";
        }
        else{
            temporal+= "   \""+this.toString()+"x"+"\" [label=\"null\"] ;\n";
            temporal+= "   \""+this.toString()+"\" -> \""+this.toString()+"x"+"\"\n";
        }
        
        temporal+= "   \""+this.toString()+"coma"+"\" [label=\",\"] ;\n";
        temporal+= "   \""+this.toString()+"\" -> \""+this.toString()+"coma"+"\"\n";
        
        if(y!=null){
            temporal+= y.getArbol(ts);
            temporal+= "   \""+this.toString()+"\" -> \""+y.toString()+"\"\n";
        }
        else{
            temporal+= "   \""+this.toString()+"y"+"\" [label=\"null\"] ;\n";
            temporal+= "   \""+this.toString()+"\" -> \""+this.toString()+"y"+"\"\n";
        }
        
        return temporal;
    }

    public String getIdentificador() {
        return identificador;
    }
    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
    public int getLinea() {
        return linea;
    }
    public void setLinea(int linea) {
        this.linea = linea;
    }
    public int getColumna() {
        return columna;
    }
    public void setColumna(int columna) {
        this.columna = columna;
    }    
    public Instruccion getX() {
        return x;
    }
    public void setX(Instruccion x) {
        this.x = x;
    }
    public Instruccion getY() {
        return y;
    }
    public void setY(Instruccion y) {
        this.y = y;
    }

}
