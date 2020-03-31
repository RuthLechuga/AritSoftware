package arbol;

import Entorno.Funciones;
import Entorno.Simbolo;
import Entorno.TablaDeSimbolos;
import Utilidades.Mensaje;
import java.util.LinkedList;

public class LlamadaFunction implements Instruccion {
    
    private String identificador;
    private LinkedList<Instruccion> lista_valores;
    private int linea;
    private int columna;
    
    public LlamadaFunction(String identificador, LinkedList<Instruccion> lista_valores, int linea, int columna){
        this.identificador = identificador;
        this.lista_valores = lista_valores;
        this.linea = linea;
        this.columna = columna;
    }

    @Override
    public Object ejecutar(TablaDeSimbolos ts, LinkedList<Mensaje> mensajes) {
        
        TablaDeSimbolos local = new TablaDeSimbolos(ts.getGlobal());
        Funciones instancia = Funciones.getSingletonInstance();
        
        Function f = instancia.getFunction(identificador, lista_valores.size());
        
        if(f != null){
            
            String id_simbolo;
            Object valor;
            for(int i=0;i<lista_valores.size();i++){
                valor = lista_valores.get(i).ejecutar(ts, mensajes);
                
                if(valor instanceof Default){
                    f.getParametros().get(i).ejecutar(local, mensajes);
                }
                else{
                    id_simbolo = f.getParametros().get(i).getIdentificador(); 
                    local.addSymbol(new Simbolo(id_simbolo,valor));
                }
            }
            
            Object result = null;
            for(Instruccion ins: f.getBloque_Instrucciones()){
                result = ins.ejecutar(local, mensajes);
                
                if(result != null && (ins instanceof Return || ins instanceof If || ins instanceof While || ins instanceof For || ins instanceof DoWhile || ins instanceof Switch))
                    return result;
            }
            
        }
        else{
            
            Object result = instancia.funcionesNativas(identificador, lista_valores, ts, mensajes, linea, columna);
            return result;        
        }
        
        return null;
    }

    @Override
    public String getArbol(TablaDeSimbolos ts) {
    String temporal = 
                "   \""+this.toString()+"\" [label=\"llamada_funcion\"] ;\n" +
                "   \""+this.toString()+"id"+"\" [label=\""+identificador+"\"] ;\n" +
                "   \""+this.toString()+"\" -> \""+this.toString()+"id"+"\"\n";
        
        temporal += "\""+lista_valores.toString()+"\" [label=\"lista_valores\"] ;\n";
        temporal+= "\""+this.toString()+"\" -> \""+lista_valores.toString()+"\"\n";
            
        for(Instruccion ins:lista_valores){
            temporal += "\""+ins.toString()+"\" [label=\"valor\"] ;\n";
            temporal+= "\""+lista_valores.toString()+"\" -> \""+ins.toString()+"\"\n";
        
            temporal += ins.getArbol(ts);
        }
        
        return temporal;
    }
 
    public String getIdentificador() {
        return identificador;
    }
    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
    public LinkedList<Instruccion> getLista_valores() {
        return lista_valores;
    }
    public void setLista_valores(LinkedList<Instruccion> lista_valores) {
        this.lista_valores = lista_valores;
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

}
