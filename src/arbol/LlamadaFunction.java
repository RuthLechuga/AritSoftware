package arbol;

import Entorno.Funciones;
import Entorno.Simbolo;
import Entorno.TablaDeSimbolos;
import Utilidades.Mensaje;
import static Utilidades.Mensaje.tipo_mensaje.SEMANTICO;
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
                id_simbolo = f.getParametros().get(i).getIdentificador(); 
                valor = lista_valores.get(i).ejecutar(ts, mensajes);
                local.addSymbol(new Simbolo(id_simbolo,valor));
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
