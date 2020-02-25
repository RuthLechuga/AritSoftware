package Entorno;

import Utilidades.Mensaje;
import static Utilidades.Mensaje.tipo_mensaje.SEMANTICO;
import arbol.Function;
import arbol.Instruccion;
import java.util.LinkedList;

public class Funciones {

    private LinkedList<Function> lista_funciones;    
    private static Funciones stFunciones;
    
    private Funciones(){
        this.lista_funciones = new LinkedList<>();
    }
    
    public  static Funciones getSingletonInstance() {

        if (stFunciones == null)
            stFunciones = new Funciones();
        
        return stFunciones ;
    }
    
    public void reiniciar(){
        this.lista_funciones = new LinkedList<>();
    }
    
    public Boolean addFunction(Function nueva){
        
        for(Function f: lista_funciones){           
            //se compara que no exista una funcion con el mismo nombre
            if(f.getNombre().compareTo(nueva.getNombre())==0){
                if(f.getParametros().size() == nueva.getParametros().size())
                    return false;
            }
        }
        
        lista_funciones.add(nueva);
        return true;
    }
    
    public Function getFunction(String nombre, int num_parametros){
        
        for(Function f: lista_funciones){           
            //se compara que no exista una funcion con el mismo nombre
            if(f.getNombre().compareTo(nombre)==0){
                if(f.getParametros().size() == num_parametros)
                    return f;
            }
        }
        
        return null;
    }
    
    public Object funcionesNativas(String nombre, LinkedList<Instruccion> parametros, TablaDeSimbolos ts, LinkedList<Mensaje> mensajes, int linea, int columna){
        
        nombre = nombre.toLowerCase();
        
        if(nombre.compareTo("stringlength")==0 && parametros.size()==1)
            return stringLength(parametros.get(0),ts,mensajes,linea,columna);
        
        if(nombre.compareTo("remove")==0 && parametros.size()==2)
            return remove(parametros.get(0),parametros.get(1),ts,mensajes,linea,columna);
        
        if(nombre.compareTo("tolowercase")==0 && parametros.size()==1)
            return lowerCase(parametros.get(0),ts,mensajes,linea,columna);
        
        if(nombre.compareTo("touppercase")==0 && parametros.size()==1)
            return upperCase(parametros.get(0),ts,mensajes,linea,columna);
        
        if(nombre.compareTo("trunk")==0 && parametros.size()==1)
            return trunk(parametros.get(0),ts,mensajes,linea,columna);
        
        if(nombre.compareTo("round")==0 && parametros.size()==1)
            return round(parametros.get(0),ts,mensajes,linea,columna);
        
        return null;
    }
    
    public int stringLength(Instruccion expresion, TablaDeSimbolos ts, LinkedList<Mensaje> mensajes, int linea, int columna){
        try{
            String cadena = expresion.ejecutar(ts, mensajes).toString();
            return cadena.length();
        }
        catch(Exception e){
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"No se ha podido aplicar la función stringLength sobre la operación."));
            return 0;
        }
    }
    
    public String remove(Instruccion expresion1, Instruccion expresion2, TablaDeSimbolos ts, LinkedList<Mensaje> mensajes, int linea, int columna){
        try{
            String cadena1 = expresion1.ejecutar(ts, mensajes).toString();
            String cadena2 = expresion2.ejecutar(ts, mensajes).toString();
            
            return cadena1.replaceAll(cadena2, "");
        }
        catch(Exception e){
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"No se ha podido aplicar la función remove sobre la operación."));
            return "";
        }
    }
    
    public String lowerCase(Instruccion expresion, TablaDeSimbolos ts, LinkedList<Mensaje> mensajes, int linea, int columna){
        try{
            String cadena = expresion.ejecutar(ts, mensajes).toString();
            return cadena.toLowerCase();
        }
        catch(Exception e){
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"No se ha podido aplicar la función toLowerCase sobre la operación."));
            return "";
        }
    }
    
    public String upperCase(Instruccion expresion, TablaDeSimbolos ts, LinkedList<Mensaje> mensajes, int linea, int columna){
        try{
            String cadena = expresion.ejecutar(ts, mensajes).toString();
            return cadena.toUpperCase();
        }
        catch(Exception e){
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"No se ha podido aplicar la función toUpperCase sobre la operación."));
            return "";
        }
    }
    
    public int trunk(Instruccion expresion, TablaDeSimbolos ts, LinkedList<Mensaje> mensajes, int linea, int columna){
        try{
            Double decimal = Double.parseDouble(expresion.ejecutar(ts, mensajes).toString());
            return (int) Math.floor(decimal);
        }
        catch(Exception e){
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"No se ha podido aplicar la función trunk sobre la operación."));
            return 0;
        }
    }
    
    public int round(Instruccion expresion, TablaDeSimbolos ts, LinkedList<Mensaje> mensajes, int linea, int columna){
        try{
            Double decimal = Double.parseDouble(expresion.ejecutar(ts, mensajes).toString());
            return (int) Math.round(decimal);
        }
        catch(Exception e){
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"No se ha podido aplicar la función round sobre la operación."));
            return 0;
        }
    }
    
    public LinkedList<Function> getLista_funciones() {
        return lista_funciones;
    }
    public void setLista_funciones(LinkedList<Function> lista_funciones) {
        this.lista_funciones = lista_funciones;
    }       
}
