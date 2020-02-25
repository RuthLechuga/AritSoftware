package arbol;

import Entorno.TablaDeSimbolos;
import Utilidades.Mensaje;
import static Utilidades.Mensaje.tipo_mensaje.*;
import static arbol.Operacion.tipo_operacion.*;
import java.util.LinkedList;
import java.util.Objects;

public class Operacion implements Instruccion {
    
    private tipo_operacion tipo;
    private Instruccion operador_izquierdo;
    private Instruccion operador_derecho;
    private Object valor;
    private int linea;
    private int columna;
    
    public Operacion(tipo_operacion tipo, Instruccion operador_izquierdo, Instruccion operador_derecho, int linea, int columna){
        this.tipo = tipo;
        this.operador_izquierdo = operador_izquierdo;
        this.operador_derecho = operador_derecho;
        this.linea = linea;
        this.columna = columna;
    }
    
    public Operacion(tipo_operacion tipo, Instruccion operador_izquierdo, int linea, int columna){
        this.tipo = tipo;
        this.operador_izquierdo = operador_izquierdo;
        this.linea = linea;
        this.columna = columna;
    }
    
    public Operacion(int valor, int linea, int columna){
        this.tipo = ENTERO;
        this.valor = valor;
        this.linea = linea;
        this.columna = columna;
    }
    
    public Operacion(double valor, int linea, int columna){
        this.tipo = DECIMAL;
        this.valor = valor;
        this.linea = linea;
        this.columna = columna;
    }
    
    public Operacion(String valor, int linea, int columna){
        this.tipo = CADENA;
        this.valor = valor;
        this.linea = linea;
        this.columna = columna;
    }
    
    public Operacion(Boolean valor, int linea, int columna){
        this.tipo = BOOLEAN;
        this.valor = valor;
        this.linea = linea;
        this.columna = columna;
    }
    
    public Operacion(tipo_operacion tipo, String identificador, int linea, int columna){
        this.tipo = tipo;
        this.valor = identificador;
        this.linea = linea;
        this.columna = columna;
    }
    
    //Constructor para operador ternario
    public Operacion(Instruccion resultado_true, Instruccion resultado_false, Instruccion condicion, int linea, int columna){
        this.tipo = TERNARIO;
        this.valor = condicion;
        this.operador_izquierdo = resultado_true;
        this.operador_derecho = resultado_false;
        this.linea = linea;
        this.columna = columna;
    }
    
    @Override
    public Object ejecutar(TablaDeSimbolos ts, LinkedList<Mensaje> mensajes) {
        Object a = (operador_izquierdo == null) ? null : operador_izquierdo.ejecutar(ts, mensajes);
        Object b = (operador_derecho == null) ? null : operador_derecho.ejecutar(ts, mensajes);
        
        if(a instanceof Error || b instanceof Error)
            return new Error();
        
        if(tipo == ENTERO || tipo == DECIMAL || tipo == CADENA || tipo == BOOLEAN)
            return valor;
        
        if(tipo == IDENTIFICADOR)
            return ts.getSymbol(valor.toString()).getValor();
        
        if(tipo == SUMA){
            if(a instanceof Integer && b instanceof Integer)
                return (int)a + (int)b;
            
            if(a instanceof Integer && b instanceof Double || a instanceof Double && b instanceof Integer
                    || a instanceof Double && b instanceof Double)
                return new Double(a.toString()) + new Double(b.toString());
            
            if(a instanceof String || b instanceof String)
                return a.toString()+b.toString();
            
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Operación inválida, imposible castear."));
            
            return new Error();
        }
        
        if(tipo == RESTA){
            if(a instanceof Integer && b instanceof Integer)
                return (int)a - (int)b;
            
            if(a instanceof Integer && b instanceof Double || a instanceof Double && b instanceof Integer
                    || a instanceof Double && b instanceof Double)
                return new Double(a.toString()) - new Double(b.toString());
            
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Operación inválida, imposible castear."));
            
            return new Error();
        }
        
        if(tipo == MULTIPLICACION){
            if(a instanceof Integer && b instanceof Integer)
                return (int)a * (int)b;
            
            if(a instanceof Integer && b instanceof Double || a instanceof Double && b instanceof Integer
                    || a instanceof Double && b instanceof Double)
                return new Double(a.toString()) * new Double(b.toString());
            
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Operación inválida, imposible castear."));
            
            return new Error();
        }
        
        if(tipo == DIVISION){
            
            if(Double.parseDouble(b.toString()) == 0){
                mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Operación inválida, la división entre cero no está definida."));
                return new Error();
            }
            
            if(a instanceof Integer && b instanceof Integer)
                return (int)a / (int)b;
            
            if(a instanceof Integer && b instanceof Double || a instanceof Double && b instanceof Integer
                    || a instanceof Double && b instanceof Double)
                return new Double(a.toString()) / new Double(b.toString());
            
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Operación inválida, imposible castear."));
            
            return new Error();
        }
        
        if(tipo == POTENCIA){
            if(a instanceof Integer && b instanceof Integer || a instanceof Integer && b instanceof Double 
                    || a instanceof Double && b instanceof Integer || a instanceof Double && b instanceof Double)
                return Math.pow(new Double(a.toString()), new Double(b.toString()));
            
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Operación inválida, imposible castear."));
            
            return new Error();                      
        }
        
        if(tipo == MODULO){
            if(a instanceof Integer && b instanceof Integer)
                return (int)a % (int)b;
            
            if(a instanceof Integer && b instanceof Double || a instanceof Double && b instanceof Integer
                    || a instanceof Double && b instanceof Double)
                return new Double(a.toString()) % new Double(b.toString());
            
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Operación inválida, imposible castear."));
            
            return new Error();
        }
        
        if(tipo == MENOS_UNARIO){
            if(a instanceof Integer)
                return (-1)*(int)a;
            
            if(a instanceof Double)
                return (-1)*(new Double(a.toString()));
            
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Operación inválida, imposible castear."));
            
            return new Error();
        }
        
        if(tipo == IGUAL_QUE){
            
            if(a instanceof Integer && b instanceof Integer || a instanceof Double && b instanceof Double
                    || a instanceof Integer && b instanceof Double || a instanceof Double && b instanceof Integer)
                return (Objects.equals(new Double(a.toString()), new Double(b.toString())));
            
            if(a instanceof String && b instanceof String)
                return (a.toString().compareTo(b.toString())==0);
            
            if(a instanceof Boolean && b instanceof Boolean)
                return !((Boolean)a^(Boolean)b);
            
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Operación inválida, no pueden compararse estos tipos de datos."));
          
            return new Error();
        }
        
        if(tipo == DISTINTO_QUE){
            
            if(a instanceof Integer && b instanceof Integer || a instanceof Double && b instanceof Double
                    || a instanceof Integer && b instanceof Double || a instanceof Double && b instanceof Integer)
                return !(Objects.equals(new Double(a.toString()), new Double(b.toString())));
            
            if(a instanceof String && b instanceof String)
                return (a.toString().compareTo(b.toString())!=0);
            
            if(a instanceof Boolean && b instanceof Boolean)
                return ((Boolean)a^(Boolean)b);
            
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Operación inválida, no pueden compararse estos tipos de datos."));
                   
            return new Error();
        }
        
        if(tipo == MAYOR_QUE){
            
            if(a instanceof Integer && b instanceof Integer || a instanceof Double && b instanceof Double
                    || a instanceof Integer && b instanceof Double || a instanceof Double && b instanceof Integer)
                return new Double(a.toString()) > new Double(b.toString());
            
            if(a instanceof String && b instanceof String)
                return (a.toString().compareTo(b.toString())<0);
            
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Operación inválida, no pueden compararse estos tipos de datos."));
                      
            return new Error();
        }
        
        if(tipo == MENOR_QUE){
            
            if(a instanceof Integer && b instanceof Integer || a instanceof Double && b instanceof Double
                    || a instanceof Integer && b instanceof Double || a instanceof Double && b instanceof Integer)
                return new Double(a.toString()) < new Double(b.toString());
            
            if(a instanceof String && b instanceof String)
                return (a.toString().compareTo(b.toString())>0);
            
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Operación inválida, no pueden compararse estos tipos de datos."));
            
            return new Error();
        }
        
        if(tipo == MAYOR_IGUAL_QUE){
            
            if(a instanceof Integer && b instanceof Integer || a instanceof Double && b instanceof Double
                    || a instanceof Integer && b instanceof Double || a instanceof Double && b instanceof Integer)
                return new Double(a.toString()) >= new Double(b.toString());
            
            if(a instanceof String && b instanceof String)
                return (a.toString().compareTo(b.toString())<=0);
            
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Operación inválida, no pueden compararse estos tipos de datos."));
            
            return new Error();
        }
        
        if(tipo == MENOR_IGUAL_QUE){
            
            if(a instanceof Integer && b instanceof Integer || a instanceof Double && b instanceof Double
                    || a instanceof Integer && b instanceof Double || a instanceof Double && b instanceof Integer)
                return new Double(a.toString()) <= new Double(b.toString());
            
            if(a instanceof String && b instanceof String)
                return (a.toString().compareTo(b.toString())>=0);
            
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Operación inválida, no pueden compararse estos tipos de datos."));
            
            return new Error();
        }
        
        if(tipo == AND){
            
            if(a instanceof Boolean && b instanceof Boolean)
                return (Boolean)a && (Boolean)b;
            
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Operación inválida, el operador lógico AND solo puede aplicarse sobre valores booleanos."));
            
            return new Error();
        }

        if(tipo == OR){
            
            if(a instanceof Boolean && b instanceof Boolean)
                return (Boolean)a || (Boolean)b;
            
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Operación inválida, el operador lógico OR solo puede aplicarse sobre valores booleanos."));
            
            return new Error();
        }
        
        if(tipo == NOT){
            if(a instanceof Boolean)
                return !((Boolean)a);
            
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Operación inválida, el operador lógico OR solo puede aplicarse sobre valores booleanos."));
            
            return new Error();
        }
        
        return new Error();
    }

    @Override
    public String getArbol(TablaDeSimbolos ts) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public enum tipo_operacion{
        ENTERO,         //si
        DECIMAL,        //si
        CADENA,         //si
        BOOLEAN,        //si
        SUMA,           //si
        RESTA,          //si
        MULTIPLICACION, //si
        DIVISION,       //si
        POTENCIA,       //si
        MODULO,         //si
        MENOS_UNARIO,   //si
        IGUAL_QUE,      //si
        DISTINTO_QUE,   //si
        MAYOR_QUE,      //si
        MENOR_QUE,      //si
        MAYOR_IGUAL_QUE,//si
        MENOR_IGUAL_QUE,//si
        AND,            //si
        OR,             //si
        NOT,            //si
        IDENTIFICADOR,  //si
        TERNARIO        //Por implementar y definir si es mejor como metodo o clase
    }
    
    public tipo_operacion getTipo() {
        return tipo;
    }
    public void setTipo(tipo_operacion tipo) {
        this.tipo = tipo;
    }
    public Instruccion getOperador_izquierdo() {
        return operador_izquierdo;
    }
    public void setOperador_izquierdo(Instruccion operador_izquierdo) {
        this.operador_izquierdo = operador_izquierdo;
    }
    public Instruccion getOperador_derecho() {
        return operador_derecho;
    }
    public void setOperador_derecho(Instruccion operador_derecho) {
        this.operador_derecho = operador_derecho;
    }
    public Object getValor() {
        return valor;
    }
    public void setValor(Object valor) {
        this.valor = valor;
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
