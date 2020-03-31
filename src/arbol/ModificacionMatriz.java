package arbol;

import Entorno.Simbolo;
import Entorno.TablaDeSimbolos;
import Estructuras.Matriz;
import Utilidades.Mensaje;
import static Utilidades.Mensaje.tipo_mensaje.SEMANTICO;
import java.util.LinkedList;

public class ModificacionMatriz implements Instruccion{
  
    private String identificador;
    private Instruccion expresion;
    private Instruccion x;
    private Instruccion y;
    private int linea;
    private int columna;
    
    public ModificacionMatriz(String identificador, Instruccion x, Instruccion y, Instruccion expresion, int linea, int columna){
        this.identificador = identificador;
        this.x = x;
        this.y = y;
        this.expresion = expresion;
        this.linea = linea;
        this.columna = columna;
    }

    @Override
    public Object ejecutar(TablaDeSimbolos ts, LinkedList<Mensaje> mensajes) {
        
        try{
            Simbolo s = ts.getSymbol(identificador);
            
            if(s != null){
                
                Object temporal = s.getValor();
                Object valor = expresion.ejecutar(ts, mensajes);
                
                if(temporal instanceof Matriz){
                    
                    if(x != null && y != null){
                        int posx = ((int)x.ejecutar(ts, mensajes));
                        int posy = ((int)y.ejecutar(ts, mensajes));
                        ((Matriz)temporal).setValor(posy, posx, valor);
                    }
                    
                    else if(x != null && y == null){
                        int posx = ((int)x.ejecutar(ts, mensajes));
                        ((Matriz)temporal).setFila(posx,valor,mensajes,linea,columna);
                    }
                    
                    else {
                        int posy = ((int)y.ejecutar(ts, mensajes));
                        ((Matriz)temporal).setColumna(posy,valor,mensajes,linea,columna);                        
                    }
                    
                }else{
                    mensajes.add(new Mensaje(linea,columna,SEMANTICO,"No se encontro la matriz:"+identificador+" en el contexto."));
                }
                
            }
            else{
                mensajes.add(new Mensaje(linea,columna,SEMANTICO,"No se encontro la matriz:"+identificador+" en el contexto."));
            }
            
        }
        catch(Exception e){
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"No se pudo realizar la modificación a la matriz."));
        }
        return null;
    }

    @Override
    public String getArbol(TablaDeSimbolos ts) {
        String temporal = 
                "   \""+this.toString()+"\" [label=\"ins_modificacion\"] ;\n" +
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
        
        temporal+= "   \""+this.toString()+"igual"+"\" [label=\"=\"] ;\n";
        temporal+= "   \""+this.toString()+"\" -> \""+this.toString()+"igual"+"\"\n";
        
        temporal+= expresion.getArbol(ts);
        temporal+= "   \""+this.toString()+"\" -> \""+expresion.toString()+"\"\n";        

        return temporal;
    }
    
    public String getIdentificador() {
        return identificador;
    }
    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
    public Instruccion getExpresion() {
        return expresion;
    }
    public void setExpresion(Instruccion expresion) {
        this.expresion = expresion;
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
