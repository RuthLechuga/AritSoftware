package Entorno;

import static Entorno.Tipo.tipo_primitivo.CADENA;
import static Entorno.Tipo.tipo_primitivo.DECIMAL;
import static Entorno.Tipo.tipo_primitivo.ENTERO;
import static Entorno.Tipo.tipo_primitivo.LISTA;
import static Entorno.Tipo.tipo_primitivo.VECTOR;
import Estructuras.Lista;
import Estructuras.Vector;
import Utilidades.Mensaje;
import static Utilidades.Mensaje.tipo_mensaje.SEMANTICO;
import arbol.Function;
import arbol.Instruccion;
import arbol.Operacion;
import arbol.Operacion.tipo_operacion;
import java.util.LinkedList;

public class Funciones {

    private LinkedList<Function> lista_funciones;    
    private static Funciones stFunciones;
    
    private Funciones(){
        this.lista_funciones = new LinkedList<>();
    }
    
    public static Funciones getSingletonInstance() {

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
            if(f.getNombre().toLowerCase().compareTo(nombre.toLowerCase())==0){
                if(f.getParametros().size() == num_parametros)
                    return f;
            }
        }
        
        return null;
    }
    
    public Object funcionesNativas(String nombre, LinkedList<Instruccion> parametros, TablaDeSimbolos ts, LinkedList<Mensaje> mensajes, int linea, int columna){
        
        nombre = nombre.toLowerCase();
        
        if(nombre.compareTo("list")==0)
            return functionList(parametros,ts,mensajes,linea,columna);
        
        if(nombre.compareTo("c")==0)
            return functionC(parametros,ts,mensajes,linea,columna);
        
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
        
        if(nombre.compareTo("typeof")==0 && parametros.size()==1)
            return typeof(parametros.get(0),ts,mensajes,linea,columna);
        
        mensajes.add(new Mensaje(linea,columna,SEMANTICO,"La función:"+nombre+" no ha sido declarada"));
        
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
    
    public String typeof(Instruccion expresion, TablaDeSimbolos ts, LinkedList<Mensaje> mensajes, int linea, int columna){
        try{
            
            if(expresion instanceof Operacion && ((Operacion)expresion).getTipo()==tipo_operacion.IDENTIFICADOR){
                String identificador = ((Operacion)expresion).getValor().toString();
                
                Simbolo s = ts.getSymbol(identificador);
                return s.getTipo().getTipo_primitivo().name();
                
            }
            else{
                
                Object result = expresion.ejecutar(ts, mensajes);
                
                if(result instanceof Integer)
                    return "ENTERO";
                
                if(result instanceof Double)
                    return "DECIMAL";
                
                if(result instanceof String)
                    return "CADENA";
                
                if(result instanceof Boolean)
                    return "BOOLEAN";
                
                if(result instanceof LinkedList)
                    return "VECTOR";              
            }
            
            return "";
        }
        catch(Exception e){
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"No se ha podido aplicar la función typeof sobre la operación."));
            return "";
        }
    }
  
    public Object functionList(LinkedList<Instruccion> expresiones, TablaDeSimbolos ts, LinkedList<Mensaje> mensajes, int linea, int columna){
        
        try{
            LinkedList<Object> temporal = new LinkedList<>();
                    
            for(Instruccion exp: expresiones){
                Object objt = exp.ejecutar(ts, mensajes);
                temporal.add(objt);
            }

            return (new Lista(temporal));
        }
        catch(Exception e){
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"No se ha podido crear la lista."));           
            return null;
        }
        
    }
    
    public Object functionC(LinkedList<Instruccion> expresiones, TablaDeSimbolos ts, LinkedList<Mensaje> mensajes, int linea, int columna){
        try{
            LinkedList<Object> temporal = new LinkedList<>();
            
            int prioridad_casteo = 1;
        
            for(Instruccion exp: expresiones){
                
                Object objt = exp.ejecutar(ts, mensajes);
                
                if(objt instanceof Lista){
                    prioridad_casteo = 4;
                    temporal.addAll(((Lista) objt).getLista());
                }
                
                else if(objt instanceof Vector){
                    temporal.addAll(((Vector) objt).getVector());
                }
                
                else{
                    temporal.add(objt);
                                       
                if(objt instanceof String)
                    prioridad_casteo = 3;
                
                else if(objt instanceof Double && prioridad_casteo == 1)
                    prioridad_casteo = 2; 
                }                            
            }
            
            if(prioridad_casteo == 4){
                return new Lista(temporal);   
            }else{
                
                if(prioridad_casteo == 3){
                    return new Vector(temporal,new Tipo(CADENA));
                }
                
                else if(prioridad_casteo == 2){
                    return new Vector(temporal,new Tipo(DECIMAL));
                }
                
                return new Vector(temporal,new Tipo(ENTERO));
            }
            
        }
        catch(Exception e){
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"No se ha podido crear el vector."));           
            return null;
        }
        
    }
    
    public LinkedList<Function> getLista_funciones() {
        return lista_funciones;
    }
    public void setLista_funciones(LinkedList<Function> lista_funciones) {
        this.lista_funciones = lista_funciones;
    }       
}
