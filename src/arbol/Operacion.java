package arbol;

import Entorno.TablaDeSimbolos;
import Estructuras.Matriz;
import Estructuras.Vector;
import Utilidades.Mensaje;
import static Utilidades.Mensaje.tipo_mensaje.*;
import static arbol.Operacion.tipo_operacion.*;
import java.util.LinkedList;
import java.util.Objects;
import Utilidades.Error;

public class Operacion implements Instruccion {

    private tipo_operacion tipo;
    private Instruccion operador_izquierdo;
    private Instruccion operador_derecho;
    private Object valor;
    private int linea;
    private int columna;
    private Boolean acceso_arreglo;
    
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
    
    public Operacion(Object valor, int linea, int columna){
        this.linea = linea;
        this.columna = columna;
        this.valor = valor;
        
        if(valor instanceof String)
            this.tipo = CADENA;
        else if(valor instanceof Integer)
            this.tipo = ENTERO;
        else if(valor instanceof Double)
            this.tipo = DECIMAL;
        else if(valor instanceof Boolean)
            this.tipo = BOOLEAN;
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
    
    //Constructor para acceso al arreglo, saber si se devuelve una posicion o un arreglo
    //true para retornar un tipo 1 y false para retornar un tipo 2
    public Operacion(tipo_operacion tipo, Instruccion operador_izquierdo, Boolean acceso_arreglo, int linea, int columna){
        this.tipo = tipo;
        this.operador_izquierdo = operador_izquierdo;
        this.acceso_arreglo = acceso_arreglo;
        this.linea = linea;
        this.columna = columna;
    }
    
    @Override
    public Object ejecutar(TablaDeSimbolos ts, LinkedList<Mensaje> mensajes) {
        Object a = (operador_izquierdo == null) ? null : operador_izquierdo.ejecutar(ts, mensajes);
        Object b = (operador_derecho == null) ? null : operador_derecho.ejecutar(ts, mensajes);
        
        if(a instanceof Error || b instanceof Error)
            return new Error();
        
        /*if(a instanceof Null)
            return a;*/
        
        if(tipo == ENTERO || tipo == DECIMAL || tipo == CADENA || tipo == BOOLEAN)
            return valor;
        
        if(tipo == IDENTIFICADOR){
            
            Object t = ts.getSymbol(valor.toString());
            
            if(t == null){
                mensajes.add(new Mensaje(linea,columna,SEMANTICO,"El identificador no ha sido encontrado en el contexto."));
                return new Error();   
            }
            
            return ts.getSymbol(valor.toString()).getValor();
        }
        
        if(tipo == ACCESO_ARREGLO)
            return a;
        
        if(tipo == SUMA){
            if(a instanceof Integer && b instanceof Integer)
                return (int)a + (int)b;
            
            if(a instanceof Integer && b instanceof Double || a instanceof Double && b instanceof Integer
                    || a instanceof Double && b instanceof Double)
                return new Double(a.toString()) + new Double(b.toString());
            
            if(a instanceof Vector && b instanceof Double){
                LinkedList<Object> t = suma_vector(((Vector)a).getVector(),new Double(b.toString())); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);   
            }
            
            if(a instanceof Matriz && b instanceof Double){
                LinkedList<Object> t = suma_vector(((Matriz)a).getMatriz(),new Double(b.toString())); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)a).getDimension_x(),((Matriz)a).getDimension_y());   
            }
            
            if(b instanceof Vector && a instanceof Double){
                LinkedList<Object> t = suma_vector(((Vector)b).getVector(),new Double(a.toString())); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);   
            }
            
            if(b instanceof Matriz && a instanceof Double){
                LinkedList<Object> t = suma_vector(((Matriz)b).getMatriz(),new Double(a.toString())); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)b).getDimension_x(),((Matriz)b).getDimension_y()); 
            }
            
            if(a instanceof Vector && b instanceof Integer){
                LinkedList<Object> t = suma_vector(((Vector)a).getVector(),new Integer(b.toString())); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);   
            }
            
            if(a instanceof Matriz && b instanceof Integer){
                LinkedList<Object> t = suma_vector(((Matriz)a).getMatriz(),new Integer(b.toString())); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)a).getDimension_x(),((Matriz)a).getDimension_y());   
            }
            
            if(b instanceof Vector && a instanceof Integer){
                LinkedList<Object> t = suma_vector(((Vector)b).getVector(),new Integer(a.toString())); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);  
            }
            
            if(b instanceof Matriz && a instanceof Integer){
                LinkedList<Object> t = suma_vector(((Matriz)b).getMatriz(),new Integer(a.toString())); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)b).getDimension_x(),((Matriz)b).getDimension_y());   
            }
            
            if(a instanceof Vector && b instanceof String){
                LinkedList<Object> t = suma_vector(((Vector)a).getVector(),b.toString()); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);  
            }
            
            if(a instanceof Matriz && b instanceof String){
                LinkedList<Object> t = suma_vector(((Matriz)a).getMatriz(),b.toString()); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)a).getDimension_x(),((Matriz)a).getDimension_y());   
            }
            
            if(b instanceof Vector && a instanceof String){
                LinkedList<Object> t = suma_vector(a.toString(),((Vector)b).getVector()); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t); 
            }
            
            if(b instanceof Matriz && a instanceof String){
                LinkedList<Object> t = suma_vector(a.toString(),((Matriz)b).getMatriz()); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)b).getDimension_x(),((Matriz)b).getDimension_y());   
            }
            
            if(a instanceof Vector && b instanceof Vector){
                LinkedList<Object> t = suma_vector(((Vector)a).getVector(),((Vector)b).getVector(),ts,mensajes); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);     
            }
            
            if(a instanceof Matriz && b instanceof Matriz){
                LinkedList<Object> t = suma_vector(((Matriz)a).getMatriz(),((Matriz)b).getMatriz(),ts,mensajes); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)a).getDimension_x(),((Matriz)a).getDimension_y());   
            }
            
            if(a instanceof String || b instanceof String)
                return a.toString()+b.toString();
           
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Operación inválida, imposible castear  en la suma."));
            
            return new Error();
        }
        
        if(tipo == RESTA){
            if(a instanceof Integer && b instanceof Integer)
                return (int)a - (int)b;
            
            if(a instanceof Integer && b instanceof Double || a instanceof Double && b instanceof Integer
                    || a instanceof Double && b instanceof Double)
                return new Double(a.toString()) - new Double(b.toString());
            
            if(a instanceof Vector && b instanceof Double){
                LinkedList<Object> t = resta_vector(((Vector)a).getVector(),new Double(b.toString())); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);     
            
            }
            
            if(a instanceof Matriz && b instanceof Double){
                LinkedList<Object> t = resta_vector(((Matriz)a).getMatriz(),new Double(b.toString())); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)a).getDimension_x(),((Matriz)a).getDimension_y());     
            }
            
            if(b instanceof Vector && a instanceof Double){
                LinkedList<Object> t = resta_vector(new Double(a.toString()),((Vector)b).getVector()); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);     
            }
            
            if(b instanceof Matriz && a instanceof Double){
                LinkedList<Object> t = resta_vector(new Double(a.toString()),((Matriz)b).getMatriz()); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)b).getDimension_x(),((Matriz)b).getDimension_y());
            }
            
            if(a instanceof Vector && b instanceof Integer){
                LinkedList<Object> t = resta_vector(((Vector)a).getVector(),new Integer(b.toString())); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);         
            }
            
            if(a instanceof Matriz && b instanceof Integer){
                LinkedList<Object> t = resta_vector(((Matriz)a).getMatriz(),new Integer(b.toString())); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)a).getDimension_x(),((Matriz)a).getDimension_y());     
            }
            
            if(b instanceof Vector && a instanceof Integer){
                LinkedList<Object> t = resta_vector(new Integer(a.toString()),((Vector)b).getVector()); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);         
            }
            
            if(b instanceof Matriz && a instanceof Integer){
                LinkedList<Object> t = resta_vector(new Integer(a.toString()),((Matriz)b).getMatriz()); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)b).getDimension_x(),((Matriz)b).getDimension_y());
            }
            
            if(a instanceof Vector && b instanceof Vector){
                LinkedList<Object> t = resta_vector(((Vector)a).getVector(),((Vector)b).getVector(),ts,mensajes); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);         
            }
            
            if(a instanceof Matriz && b instanceof Matriz){
                LinkedList<Object> t = resta_vector(((Matriz)a).getMatriz(),((Matriz)b).getMatriz(),ts,mensajes); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)b).getDimension_x(),((Matriz)b).getDimension_y());
            }
            
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Operación inválida, imposible castear  en la resta."));
            
            return new Error();
        }
        
        if(tipo == MULTIPLICACION){
            if(a instanceof Integer && b instanceof Integer)
                return (int)a * (int)b;
            
            if(a instanceof Integer && b instanceof Double || a instanceof Double && b instanceof Integer
                    || a instanceof Double && b instanceof Double)
                return new Double(a.toString()) * new Double(b.toString());
            
            if(a instanceof Vector && b instanceof Double){
                LinkedList<Object> t = multiplicacion_vector(((Vector)a).getVector(),new Double(b.toString()));
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);
            }
            
            if(a instanceof Matriz && b instanceof Double){
                LinkedList<Object> t = multiplicacion_vector(((Matriz)a).getMatriz(),new Double(b.toString()));
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)a).getDimension_x(),((Matriz)a).getDimension_y());
            }
            
            if(b instanceof Vector && a instanceof Double){
                LinkedList<Object> t = multiplicacion_vector(((Vector)b).getVector(),new Double(a.toString()));
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);
            }
            
            if(b instanceof Matriz && a instanceof Double){
                LinkedList<Object> t = multiplicacion_vector(((Matriz)b).getMatriz(),new Double(a.toString()));
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)b).getDimension_x(),((Matriz)b).getDimension_y());
            }
            
            if(a instanceof Vector && b instanceof Integer){
                LinkedList<Object> t = multiplicacion_vector(((Vector)a).getVector(),new Integer(b.toString()));
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);
            }
            
            if(a instanceof Matriz && b instanceof Integer){
                LinkedList<Object> t = multiplicacion_vector(((Matriz)a).getMatriz(),new Integer(b.toString()));
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)a).getDimension_x(),((Matriz)a).getDimension_y());
            }
            
            if(b instanceof Vector && a instanceof Integer){
                LinkedList<Object> t = multiplicacion_vector(((Vector)b).getVector(),new Integer(a.toString()));
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);
            }
            
            if(b instanceof Matriz && a instanceof Integer){
                LinkedList<Object> t = multiplicacion_vector(((Matriz)b).getMatriz(),new Integer(a.toString()));
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)b).getDimension_x(),((Matriz)b).getDimension_y());
            }
            
            if(a instanceof Vector && b instanceof Vector){
                LinkedList<Object> t = multiplicacion_vector(((Vector)a).getVector(),((Vector)b).getVector(),ts,mensajes);
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);
            }
            
            if(a instanceof Matriz && b instanceof Matriz){
                LinkedList<Object> t = multiplicacion_vector(((Matriz)a).getMatriz(),((Matriz)b).getMatriz(),ts,mensajes);
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)b).getDimension_x(),((Matriz)b).getDimension_y());
            }
            
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Operación inválida, imposible castear  en la multiplicación."));
            
            return new Error();
        }
        
        if(tipo == DIVISION){
            
            if(a instanceof Integer && b instanceof Integer){
                
                if(Double.parseDouble(b.toString()) == 0){
                    mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Operación inválida, la división entre cero no está definida."));
                    return new Error();
                }
    
                return (int)a / (int)b;
            }
                
            if(a instanceof Integer && b instanceof Double || a instanceof Double && b instanceof Integer
                    || a instanceof Double && b instanceof Double){
                
                if(Double.parseDouble(b.toString()) == 0){
                    mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Operación inválida, la división entre cero no está definida."));
                    return new Error();
                }
                
                return new Double(a.toString()) / new Double(b.toString());
            }
            
            if(a instanceof Vector && b instanceof Double){
                
                LinkedList t = division_vector(((Vector)a).getVector(),new Double(b.toString()));
                
                if(t == null){
                    mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Operación inválida, la división entre cero no está definida."));
                    return null;
                }
                
                return new Vector(t);
            }
            
            if(a instanceof Matriz && b instanceof Double){
                
                LinkedList t = division_vector(((Matriz)a).getMatriz(),new Double(b.toString()));
                
                if(t == null){
                    mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Operación inválida, la división entre cero no está definida."));
                    return null;
                }
                
                return new Matriz(t,((Matriz)a).getDimension_x(),((Matriz)a).getDimension_y());
            }
            
            if(b instanceof Vector && a instanceof Double){
                
                LinkedList t = division_vector(new Double(a.toString()),((Vector)b).getVector());
                
                if(t == null){
                    mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Operación inválida, la división entre cero no está definida."));
                    return null;
                }
                
                return new Vector(t);
            }
            
            if(b instanceof Matriz && a instanceof Double){
                
                LinkedList t = division_vector(new Double(a.toString()),((Matriz)b).getMatriz());
                
                if(t == null){
                    mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Operación inválida, la división entre cero no está definida."));
                    return null;
                }
                
                return new Matriz(t,((Matriz)b).getDimension_x(),((Matriz)b).getDimension_y());
            }
            
            if(a instanceof Vector && b instanceof Integer){
                
                LinkedList t = division_vector(((Vector)a).getVector(),new Integer(b.toString()));
                
                if(t == null){
                    mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Operación inválida, la división entre cero no está definida."));
                    return null;
                }
                
                return new Vector(t);
            }
            
            if(a instanceof Matriz && b instanceof Integer){
                
                LinkedList t = division_vector(((Matriz)a).getMatriz(),new Integer(b.toString()));
                
                if(t == null){
                    mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Operación inválida, la división entre cero no está definida."));
                    return null;
                }
                
                return new Matriz(t,((Matriz)a).getDimension_x(),((Matriz)a).getDimension_y());
            }
            
            if(b instanceof Vector && a instanceof Integer){
            
                LinkedList t = division_vector(new Integer(a.toString()),((Vector)b).getVector());    
                
                if(t == null){
                    mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Operación inválida, la división entre cero no está definida."));
                    return null;
                }
                
                return new Vector(t);
            }
            
            if(b instanceof Matriz && a instanceof Integer){
            
                LinkedList t = division_vector(new Integer(a.toString()),((Matriz)b).getMatriz());    
                
                if(t == null){
                    mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Operación inválida, la división entre cero no está definida."));
                    return null;
                }
                
                return new Matriz(t,((Matriz)b).getDimension_x(),((Matriz)b).getDimension_y());
            }
            
            if(a instanceof Vector && b instanceof Vector){
                LinkedList<Object> t = division_vector(((Vector)a).getVector(),((Vector)b).getVector(),ts,mensajes);
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);    
            }
            
            if(a instanceof Matriz && b instanceof Matriz){
                LinkedList<Object> t = division_vector(((Matriz)a).getMatriz(),((Matriz)b).getMatriz(),ts,mensajes);
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)b).getDimension_x(),((Matriz)b).getDimension_y());
            }
                    
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Operación inválida, imposible castear en la division."));
            
            return new Error();
        }
        
        if(tipo == POTENCIA){
            if(a instanceof Integer && b instanceof Integer || a instanceof Integer && b instanceof Double 
                    || a instanceof Double && b instanceof Integer || a instanceof Double && b instanceof Double)
                return Math.pow(new Double(a.toString()), new Double(b.toString()));
            
            if(a instanceof Vector && (b instanceof Double|| b instanceof Integer)){
                LinkedList<Object> t = potencia_vector(((Vector)a).getVector(),new Double(b.toString()));
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);
            }
            
            if(a instanceof Matriz && (b instanceof Double|| b instanceof Integer)){
                LinkedList<Object> t = potencia_vector(((Matriz)a).getMatriz(),new Double(b.toString()));
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)a).getDimension_x(),((Matriz)a).getDimension_y());
            }
            
            if(b instanceof Vector && (a instanceof Double|| a instanceof Integer)){
                LinkedList<Object> t = potencia_vector(new Double(a.toString()),((Vector)b).getVector());
                if(t == null)
                    return new Error();
                
                return new Vector(t);
            }
            
            if(b instanceof Matriz && (a instanceof Double|| a instanceof Integer)){
                LinkedList<Object> t = potencia_vector(new Double(a.toString()),((Matriz)b).getMatriz());
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)b).getDimension_x(),((Matriz)b).getDimension_y());
            }
            
            if(a instanceof Vector && b instanceof Vector){
                LinkedList<Object> t = potencia_vector(((Vector)a).getVector(),((Vector)b).getVector(),ts,mensajes);
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);
            }
            
            if(a instanceof Matriz && b instanceof Matriz){
                LinkedList<Object> t = potencia_vector(((Matriz)a).getMatriz(),((Matriz)b).getMatriz(),ts,mensajes);
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)b).getDimension_x(),((Matriz)b).getDimension_y());
            }
            
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Operación inválida, imposible castear en la potencia."));
            
            return new Error();                      
        }
        
        if(tipo == MODULO){
            if(a instanceof Integer && b instanceof Integer)
                return (int)a % (int)b;
            
            if(a instanceof Integer && b instanceof Double || a instanceof Double && b instanceof Integer
                    || a instanceof Double && b instanceof Double)
                return new Double(a.toString()) % new Double(b.toString());
            
            if(a instanceof Vector && b instanceof Double){
                LinkedList<Object> t = modulo_vector(((Vector)a).getVector(),new Double(b.toString()));
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);    
            }
            
            if(a instanceof Matriz && b instanceof Double){
                LinkedList<Object> t = modulo_vector(((Matriz)a).getMatriz(),new Double(b.toString()));
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)a).getDimension_x(),((Matriz)a).getDimension_y());
            }
            
            if(b instanceof Vector && a instanceof Double){
                LinkedList<Object> t = modulo_vector(new Double(a.toString()),((Vector)b).getVector());
                
                if(t == null)
                    return new Error();
                
                return new Vector(t); 
            }
            
            if(b instanceof Matriz && a instanceof Double){
                LinkedList<Object> t = modulo_vector(new Double(a.toString()),((Matriz)b).getMatriz());
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)b).getDimension_x(),((Matriz)b).getDimension_y());
            }
            
            if(a instanceof Vector && b instanceof Integer){
                LinkedList<Object> t = modulo_vector(((Vector)a).getVector(),new Integer(b.toString()));
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);    
            }
            
            if(a instanceof Matriz && b instanceof Integer){
                LinkedList<Object> t = modulo_vector(((Matriz)a).getMatriz(),new Integer(b.toString()));
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)a).getDimension_x(),((Matriz)a).getDimension_y());
            }
            
            if(b instanceof Vector && a instanceof Integer){
                LinkedList<Object> t = modulo_vector(new Integer(a.toString()),((Vector)b).getVector());
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);    
            }
            
            if(b instanceof Matriz && a instanceof Integer){
                LinkedList<Object> t = modulo_vector(new Integer(a.toString()),((Matriz)b).getMatriz());
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)b).getDimension_x(),((Matriz)b).getDimension_y());
            }
            
            if(a instanceof Vector && b instanceof Vector){
                LinkedList<Object> t = modulo_vector(((Vector)a).getVector(),((Vector)b).getVector(),ts,mensajes);
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);    
            }
            
            if(a instanceof Matriz && b instanceof Matriz){
                LinkedList<Object> t = modulo_vector(((Matriz)a).getMatriz(),((Matriz)b).getMatriz(),ts,mensajes);
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)b).getDimension_x(),((Matriz)b).getDimension_y());
            }
            
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Operación inválida, imposible castear  en el modulo."));
            
            return new Error();
        }
        
        if(tipo == MENOS_UNARIO){
            if(a instanceof Integer)
                return (-1)*(int)a;
            
            if(a instanceof Double)
                return (-1)*(new Double(a.toString()));
            
            if(a instanceof Vector)
                return new Vector(resta_unaria_vector(((Vector)a).getVector()));
            
            if(a instanceof Matriz)
                return new Matriz(resta_unaria_vector(((Matriz)a).getMatriz()),((Matriz)a).getDimension_x(),((Matriz)a).getDimension_y());
            
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Operación inválida, imposible castear  en la resta unitaria."));
            
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
            
            if(a instanceof Null && b instanceof Null)
                return true;
            
            if(a instanceof Vector && b instanceof Double){
                LinkedList<Object> t = igual_que_vector(((Vector)a).getVector(),new Double(b.toString())); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);   
            }
            
            if(a instanceof Matriz && b instanceof Double){
                LinkedList<Object> t = igual_que_vector(((Matriz)a).getMatriz(),new Double(b.toString())); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)a).getDimension_x(),((Matriz)a).getDimension_y());   
            }
            
            if(b instanceof Vector && a instanceof Double){
                LinkedList<Object> t = igual_que_vector(((Vector)b).getVector(),new Double(a.toString())); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);   
            }
            
            if(b instanceof Matriz && a instanceof Double){
                LinkedList<Object> t = igual_que_vector(((Matriz)b).getMatriz(),new Double(a.toString())); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)b).getDimension_x(),((Matriz)b).getDimension_y());   
            }
            
            if(a instanceof Vector && b instanceof Integer){
                LinkedList<Object> t = igual_que_vector(((Vector)a).getVector(),new Integer(b.toString())); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);   
            }
            
            if(a instanceof Matriz && b instanceof Integer){
                LinkedList<Object> t = igual_que_vector(((Matriz)a).getMatriz(),new Integer(b.toString())); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)a).getDimension_x(),((Matriz)a).getDimension_y());   
            }
            
            if(b instanceof Vector && a instanceof Integer){
                LinkedList<Object> t = igual_que_vector(((Vector)b).getVector(),new Integer(a.toString())); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);  
            }
            
            if(b instanceof Matriz && a instanceof Integer){
                LinkedList<Object> t = igual_que_vector(((Matriz)b).getMatriz(),new Integer(a.toString())); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)b).getDimension_x(),((Matriz)b).getDimension_y());   
            }
            
            if(a instanceof Vector && b instanceof String){
                LinkedList<Object> t = igual_que_vector(((Vector)a).getVector(),b.toString()); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);  
            }
            
            if(a instanceof Matriz && b instanceof String){
                LinkedList<Object> t = igual_que_vector(((Matriz)a).getMatriz(),b.toString()); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)a).getDimension_x(),((Matriz)a).getDimension_y());   
            }
            
            if(b instanceof Vector && a instanceof String){
                LinkedList<Object> t = igual_que_vector(((Vector)b).getVector(),a.toString()); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t); 
            }
            
            if(b instanceof Matriz && a instanceof String){
                LinkedList<Object> t = igual_que_vector(((Matriz)b).getMatriz(),a.toString()); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)b).getDimension_x(),((Matriz)b).getDimension_y());   
            }
            
            if(a instanceof Vector && b instanceof Vector){
                LinkedList<Object> t = igual_que_vector(((Vector)a).getVector(),((Vector)b).getVector(),ts,mensajes); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);     
            }
            
            if(a instanceof Matriz && b instanceof Matriz){
                LinkedList<Object> t = igual_que_vector(((Matriz)a).getMatriz(),((Matriz)b).getMatriz(),ts,mensajes); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)b).getDimension_x(),((Matriz)b).getDimension_y());   
            }
            
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
            
            if(a instanceof Null && b instanceof Null)
                return false;
            
            if(a instanceof Vector && b instanceof Double){
                LinkedList<Object> t = distinto_que_vector(((Vector)a).getVector(),new Double(b.toString())); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);   
            }
            
            if(a instanceof Matriz && b instanceof Double){
                LinkedList<Object> t = distinto_que_vector(((Matriz)a).getMatriz(),new Double(b.toString())); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)a).getDimension_x(),((Matriz)a).getDimension_y());   
            }
            
            if(b instanceof Vector && a instanceof Double){
                LinkedList<Object> t = distinto_que_vector(((Vector)b).getVector(),new Double(a.toString())); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);   
            }
            
            if(b instanceof Matriz && a instanceof Double){
                LinkedList<Object> t = distinto_que_vector(((Matriz)b).getMatriz(),new Double(a.toString())); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)b).getDimension_x(),((Matriz)b).getDimension_y());   
            }
            
            if(a instanceof Vector && b instanceof Integer){
                LinkedList<Object> t = distinto_que_vector(((Vector)a).getVector(),new Integer(b.toString())); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);   
            }
            
            if(a instanceof Matriz && b instanceof Integer){
                LinkedList<Object> t = distinto_que_vector(((Matriz)a).getMatriz(),new Integer(b.toString())); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)a).getDimension_x(),((Matriz)a).getDimension_y());   
            }
            
            if(b instanceof Vector && a instanceof Integer){
                LinkedList<Object> t = distinto_que_vector(((Vector)b).getVector(),new Integer(a.toString())); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);  
            }
            
            if(b instanceof Matriz && a instanceof Integer){
                LinkedList<Object> t = distinto_que_vector(((Matriz)b).getMatriz(),new Integer(a.toString())); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)b).getDimension_x(),((Matriz)b).getDimension_y());   
            }
            
            if(a instanceof Vector && b instanceof String){
                LinkedList<Object> t = distinto_que_vector(((Vector)a).getVector(),b.toString()); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);  
            }
            
            if(a instanceof Matriz && b instanceof String){
                LinkedList<Object> t = distinto_que_vector(((Matriz)a).getMatriz(),b.toString()); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)a).getDimension_x(),((Matriz)a).getDimension_y());   
            }
            
            if(b instanceof Vector && a instanceof String){
                LinkedList<Object> t = distinto_que_vector(((Vector)b).getVector(),a.toString()); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t); 
            }
            
            if(b instanceof Matriz && a instanceof String){
                LinkedList<Object> t = distinto_que_vector(((Matriz)b).getMatriz(),a.toString()); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)b).getDimension_x(),((Matriz)b).getDimension_y());   
            }
            
            if(a instanceof Vector && b instanceof Vector){
                LinkedList<Object> t = distinto_que_vector(((Vector)a).getVector(),((Vector)b).getVector(),ts,mensajes); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);     
            }
            
            if(a instanceof Matriz && b instanceof Matriz){
                LinkedList<Object> t = distinto_que_vector(((Matriz)a).getMatriz(),((Matriz)b).getMatriz(),ts,mensajes); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)b).getDimension_x(),((Matriz)b).getDimension_y());   
            }
            
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Operación inválida, no pueden compararse estos tipos de datos."));
                   
            return new Error();
        }
        
        if(tipo == MAYOR_QUE){
            
            if(a instanceof Integer && b instanceof Integer || a instanceof Double && b instanceof Double
                    || a instanceof Integer && b instanceof Double || a instanceof Double && b instanceof Integer)
                return new Double(a.toString()) > new Double(b.toString());
            
            if(a instanceof String && b instanceof String)
                return (a.toString().compareTo(b.toString())<0);
            
            if(a instanceof Vector && (b instanceof Integer || b instanceof Double)){
                LinkedList<Object> t = mayor_que_vector(((Vector)a).getVector(),Double.parseDouble(b.toString())); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);   
            }

            if(a instanceof Matriz && (b instanceof Integer || b instanceof Double)){
                LinkedList<Object> t = mayor_que_vector(((Matriz)a).getMatriz(),Double.parseDouble(b.toString())); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)a).getDimension_x(),((Matriz)a).getDimension_y());   
            }
            
            if(b instanceof Vector && (a instanceof Integer || a instanceof Double)){
                LinkedList<Object> t = menor_que_vector(((Vector)b).getVector(),Double.parseDouble(a.toString())); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);  
            }
            
            if(b instanceof Matriz && (a instanceof Integer || a instanceof Double)){
                LinkedList<Object> t = menor_que_vector(((Matriz)b).getMatriz(),Double.parseDouble(a.toString())); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)b).getDimension_x(),((Matriz)b).getDimension_y());   
            }
            
            if(a instanceof Vector && b instanceof String){
                LinkedList<Object> t = mayor_que_vector(((Vector)a).getVector(),b.toString()); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);  
            }
            
            if(a instanceof Matriz && b instanceof String){
                LinkedList<Object> t = mayor_que_vector(((Matriz)a).getMatriz(),b.toString()); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)a).getDimension_x(),((Matriz)a).getDimension_y());   
            }
            
            if(b instanceof Vector && a instanceof String){
                LinkedList<Object> t = menor_que_vector(((Vector)b).getVector(),a.toString()); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t); 
            }
            
            if(b instanceof Matriz && a instanceof String){
                LinkedList<Object> t = menor_que_vector(((Matriz)b).getMatriz(),a.toString()); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)b).getDimension_x(),((Matriz)b).getDimension_y());   
            }
            
            if(a instanceof Vector && b instanceof Vector){
                LinkedList<Object> t = mayor_que_vector(((Vector)a).getVector(),((Vector)b).getVector(),ts,mensajes); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);     
            }
            
            if(a instanceof Matriz && b instanceof Matriz){
                LinkedList<Object> t = mayor_que_vector(((Matriz)a).getMatriz(),((Matriz)b).getMatriz(),ts,mensajes); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)a).getDimension_x(),((Matriz)a).getDimension_y());   
            }
           
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Operación inválida, no pueden compararse estos tipos de datos."));
                      
            return new Error();
        }
        
        if(tipo == MENOR_QUE){
            
            if(a instanceof Integer && b instanceof Integer || a instanceof Double && b instanceof Double
                    || a instanceof Integer && b instanceof Double || a instanceof Double && b instanceof Integer)
                return new Double(a.toString()) < new Double(b.toString());
            
            if(a instanceof String && b instanceof String)
                return (a.toString().compareTo(b.toString())>0);
            
            if(a instanceof Vector && (b instanceof Integer || b instanceof Double)){
                LinkedList<Object> t = menor_que_vector(((Vector)a).getVector(),Double.parseDouble(b.toString())); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);   
            }
            
            if(a instanceof Matriz && (b instanceof Integer || b instanceof Double)){
                LinkedList<Object> t = menor_que_vector(((Matriz)a).getMatriz(),Double.parseDouble(b.toString())); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)a).getDimension_x(),((Matriz)a).getDimension_y());   
            }
            
            if(b instanceof Vector && (a instanceof Integer || a instanceof Double)){
                LinkedList<Object> t = mayor_que_vector(((Vector)b).getVector(),Double.parseDouble(a.toString())); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);  
            }
            
            if(b instanceof Matriz && (a instanceof Integer || a instanceof Double)){
                LinkedList<Object> t = mayor_que_vector(((Matriz)b).getMatriz(),Double.parseDouble(a.toString())); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)b).getDimension_x(),((Matriz)b).getDimension_y());   
            }
            
            if(a instanceof Vector && b instanceof String){
                LinkedList<Object> t = menor_que_vector(((Vector)a).getVector(),b.toString()); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);  
            }
            
            if(a instanceof Matriz && b instanceof String){
                LinkedList<Object> t = menor_que_vector(((Matriz)a).getMatriz(),b.toString()); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)a).getDimension_x(),((Matriz)a).getDimension_y());   
            }
            
            if(b instanceof Vector && a instanceof String){
                LinkedList<Object> t = mayor_que_vector(((Vector)b).getVector(),a.toString()); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t); 
            }
            
            if(b instanceof Matriz && a instanceof String){
                LinkedList<Object> t = mayor_que_vector(((Matriz)b).getMatriz(),a.toString()); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)b).getDimension_x(),((Matriz)b).getDimension_y());   
            }
            
            if(a instanceof Vector && b instanceof Vector){
                LinkedList<Object> t = menor_que_vector(((Vector)a).getVector(),((Vector)b).getVector(),ts,mensajes); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);     
            }
            
            if(a instanceof Matriz && b instanceof Matriz){
                LinkedList<Object> t = menor_que_vector(((Matriz)a).getMatriz(),((Matriz)b).getMatriz(),ts,mensajes); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)b).getDimension_x(),((Matriz)b).getDimension_y());   
            }
            
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Operación inválida, no pueden compararse estos tipos de datos."));
            
            return new Error();
        }
        
        if(tipo == MAYOR_IGUAL_QUE){
            
            if(a instanceof Integer && b instanceof Integer || a instanceof Double && b instanceof Double
                    || a instanceof Integer && b instanceof Double || a instanceof Double && b instanceof Integer)
                return new Double(a.toString()) >= new Double(b.toString());
            
            if(a instanceof String && b instanceof String)
                return (a.toString().compareTo(b.toString())<=0);
            
            if(a instanceof Vector && (b instanceof Integer || b instanceof Double)){
                LinkedList<Object> t = mayor_igual_que_vector(((Vector)a).getVector(),Double.parseDouble(b.toString())); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);   
            }
            
            if(a instanceof Matriz && (b instanceof Integer || b instanceof Double)){
                LinkedList<Object> t = mayor_igual_que_vector(((Matriz)a).getMatriz(),Double.parseDouble(b.toString())); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)a).getDimension_x(),((Matriz)a).getDimension_y());   
            }
            
            if(b instanceof Vector && (a instanceof Integer || a instanceof Double)){
                LinkedList<Object> t = menor_igual_que_vector(((Vector)b).getVector(),Double.parseDouble(a.toString())); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);  
            }
            
            if(b instanceof Matriz && (a instanceof Integer || a instanceof Double)){
                LinkedList<Object> t = menor_igual_que_vector(((Matriz)b).getMatriz(),Double.parseDouble(a.toString())); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)b).getDimension_x(),((Matriz)b).getDimension_y());   
            }
            
            if(a instanceof Vector && b instanceof String){
                LinkedList<Object> t = mayor_igual_que_vector(((Vector)a).getVector(),b.toString()); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);  
            }
            
            if(a instanceof Matriz && b instanceof String){
                LinkedList<Object> t = mayor_igual_que_vector(((Matriz)a).getMatriz(),b.toString()); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)a).getDimension_x(),((Matriz)a).getDimension_y());   
            }
            
            if(b instanceof Vector && a instanceof String){
                LinkedList<Object> t = menor_igual_que_vector(((Vector)b).getVector(),a.toString()); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t); 
            }
            
            if(b instanceof Matriz && a instanceof String){
                LinkedList<Object> t = menor_igual_que_vector(((Matriz)b).getMatriz(),a.toString()); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)b).getDimension_x(),((Matriz)b).getDimension_y());   
            }
            
            if(a instanceof Vector && b instanceof Vector){
                LinkedList<Object> t = mayor_igual_que_vector(((Vector)a).getVector(),((Vector)b).getVector(),ts,mensajes); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);     
            }
           
            if(a instanceof Matriz && b instanceof Matriz){
                LinkedList<Object> t = mayor_igual_que_vector(((Matriz)a).getMatriz(),((Matriz)b).getMatriz(),ts,mensajes); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)b).getDimension_x(),((Matriz)b).getDimension_y());   
            }
            
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Operación inválida, no pueden compararse estos tipos de datos."));
            
            return new Error();
        }
        
        if(tipo == MENOR_IGUAL_QUE){
            
            if(a instanceof Integer && b instanceof Integer || a instanceof Double && b instanceof Double
                    || a instanceof Integer && b instanceof Double || a instanceof Double && b instanceof Integer)
                return new Double(a.toString()) <= new Double(b.toString());
            
            if(a instanceof String && b instanceof String)
                return (a.toString().compareTo(b.toString())>=0);
            
            if(a instanceof Vector && (b instanceof Integer || b instanceof Double)){
                LinkedList<Object> t = menor_igual_que_vector(((Vector)a).getVector(),Double.parseDouble(b.toString())); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);   
            }
            
            if(a instanceof Matriz && (b instanceof Integer || b instanceof Double)){
                LinkedList<Object> t = menor_igual_que_vector(((Matriz)a).getMatriz(),Double.parseDouble(b.toString())); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)a).getDimension_x(),((Matriz)a).getDimension_y());   
            }
            
            if(b instanceof Vector && (a instanceof Integer || a instanceof Double)){
                LinkedList<Object> t = mayor_igual_que_vector(((Vector)b).getVector(),Double.parseDouble(a.toString())); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);  
            }
            
            if(b instanceof Matriz && (a instanceof Integer || a instanceof Double)){
                LinkedList<Object> t = mayor_igual_que_vector(((Matriz)b).getMatriz(),Double.parseDouble(a.toString())); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)b).getDimension_x(),((Matriz)b).getDimension_y());   
            }
            
            if(a instanceof Vector && b instanceof String){
                LinkedList<Object> t = menor_igual_que_vector(((Vector)a).getVector(),b.toString()); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);  
            }
            
            if(a instanceof Matriz && b instanceof String){
                LinkedList<Object> t = menor_igual_que_vector(((Matriz)a).getMatriz(),b.toString()); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)a).getDimension_x(),((Matriz)a).getDimension_y());   
            }
            
            if(b instanceof Vector && a instanceof String){
                LinkedList<Object> t = mayor_igual_que_vector(((Vector)b).getVector(),a.toString()); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t); 
            }
            
            if(b instanceof Matriz && a instanceof String){
                LinkedList<Object> t = mayor_igual_que_vector(((Matriz)b).getMatriz(),a.toString()); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)b).getDimension_x(),((Matriz)b).getDimension_y());   
            }
            
            if(a instanceof Vector && b instanceof Vector){
                LinkedList<Object> t = menor_igual_que_vector(((Vector)a).getVector(),((Vector)b).getVector(),ts,mensajes); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);     
            }
            
            if(a instanceof Matriz && b instanceof Matriz){
                LinkedList<Object> t = menor_igual_que_vector(((Matriz)a).getMatriz(),((Matriz)b).getMatriz(),ts,mensajes); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)b).getDimension_x(),((Matriz)b).getDimension_y());   
            }
            
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Operación inválida, no pueden compararse estos tipos de datos."));
            
            return new Error();
        }
        
        if(tipo == AND){
            
            if(a instanceof Boolean && b instanceof Boolean)
                return (Boolean)a && (Boolean)b;
            
            if(a instanceof Vector && b instanceof Boolean){
                LinkedList<Object> t = and_vector(((Vector)a).getVector(),((Boolean)b)); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);   
            }
            
            if(a instanceof Matriz && b instanceof Boolean){
                LinkedList<Object> t = and_vector(((Matriz)a).getMatriz(),((Boolean)b)); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)a).getDimension_x(),((Matriz)a).getDimension_y());   
            }
            
            if(b instanceof Vector && a instanceof Boolean){
                LinkedList<Object> t = and_vector(((Vector)b).getVector(),((Boolean)a)); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);   
            }
            
            if(b instanceof Matriz && a instanceof Boolean){
                LinkedList<Object> t = and_vector(((Matriz)b).getMatriz(),((Boolean)a)); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)b).getDimension_x(),((Matriz)b).getDimension_y());   
            }
            
            if(a instanceof Vector && b instanceof Vector){
                LinkedList<Object> t = and_vector(((Vector)a).getVector(),((Vector)b).getVector(),ts,mensajes); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);   
            }
            
            if(a instanceof Matriz && b instanceof Matriz){
                LinkedList<Object> t = and_vector(((Matriz)a).getMatriz(),((Matriz)b).getMatriz(),ts,mensajes); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)b).getDimension_x(),((Matriz)b).getDimension_y());   
            }
            
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Operación inválida, el operador lógico AND solo puede aplicarse sobre valores booleanos."));
            
            return new Error();
        }

        if(tipo == OR){
            
            if(a instanceof Boolean && b instanceof Boolean)
                return (Boolean)a || (Boolean)b;
            
            if(a instanceof Vector && b instanceof Boolean){
                LinkedList<Object> t = or_vector(((Vector)a).getVector(),((Boolean)b)); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);   
            }
            
            if(a instanceof Matriz && b instanceof Boolean){
                LinkedList<Object> t = or_vector(((Matriz)a).getMatriz(),((Boolean)b)); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)a).getDimension_x(),((Matriz)a).getDimension_y());   
            }
            
            if(b instanceof Vector && a instanceof Boolean){
                LinkedList<Object> t = or_vector(((Vector)b).getVector(),((Boolean)a)); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);   
            }
            
            if(b instanceof Matriz && a instanceof Boolean){
                LinkedList<Object> t = or_vector(((Matriz)b).getMatriz(),((Boolean)a)); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)b).getDimension_x(),((Matriz)b).getDimension_y());   
            }
            
            if(a instanceof Vector && b instanceof Vector){
                LinkedList<Object> t = or_vector(((Vector)a).getVector(),((Vector)b).getVector(),ts,mensajes); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);   
            }
            
            if(a instanceof Matriz && b instanceof Matriz){
                LinkedList<Object> t = or_vector(((Matriz)a).getMatriz(),((Matriz)b).getMatriz(),ts,mensajes); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)b).getDimension_x(),((Matriz)b).getDimension_y());   
            }
            
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Operación inválida, el operador lógico OR solo puede aplicarse sobre valores booleanos."));
            
            return new Error();
        }
        
        if(tipo == NOT){
            if(a instanceof Boolean)
                return !((Boolean)a);
            
            if(a instanceof Vector){
                LinkedList<Object> t = not_vector(((Vector)a).getVector()); 
                
                if(t == null)
                    return new Error();
                
                return new Vector(t);   
            }
            
            if(a instanceof Matriz){
                LinkedList<Object> t = not_vector(((Matriz)a).getMatriz()); 
                
                if(t == null)
                    return new Error();
                
                return new Matriz(t,((Matriz)a).getDimension_x(),((Matriz)a).getDimension_y());   
            }
            
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Operación inválida, el operador lógico NOT solo puede aplicarse sobre valores booleanos."));
            
            return new Error();
        }
        
        if(a instanceof Null)
            return a;
        
        return new Error();
    }
    
    public LinkedList<Object> suma_vector(LinkedList<Object> listap, Double valor){
        
        Double t1;
        LinkedList lista = new LinkedList<Object>();
        lista.addAll(listap);
        
        for(int i=0;i<lista.size();i++){
            
            if(lista.get(i) instanceof Integer || lista.get(i) instanceof Double){
                
                t1 = new Double(lista.get(i).toString());
                lista.set(i, (t1+valor));    
            }
            
        }
        
        return lista;
    }
    
    public LinkedList<Object> suma_vector(LinkedList<Object> listap, int valor){
        
        Double t1;
        int t2;
        LinkedList lista = new LinkedList<Object>();
        lista.addAll(listap);
        
        for(int i=0;i<lista.size();i++){
            
            if(lista.get(i) instanceof Integer){             
                t2 = new Integer(lista.get(i).toString());
                lista.set(i, (t2+valor));    
            }
            
            else if(lista.get(i) instanceof Double){             
                t1 = new Double(lista.get(i).toString());
                lista.set(i, (t1+valor));    
            }
            
        }
        
        return lista;
    }
    
    public LinkedList<Object> suma_vector(LinkedList<Object> listap, String valor){
        LinkedList lista = new LinkedList<Object>();
        lista.addAll(listap);
        
        for(int i=0;i<lista.size();i++){
            lista.set(i,lista.get(i).toString()+valor);
        }      
        return lista;
    }
    
    public LinkedList<Object> suma_vector(String valor,LinkedList<Object> listap){
        LinkedList lista = new LinkedList<Object>();
        lista.addAll(listap);
        
        for(int i=0;i<lista.size();i++){
            lista.set(i,valor+lista.get(i).toString());
        }      
        return lista;
    }
    
    public LinkedList<Object> suma_vector(LinkedList<Object> v1, LinkedList<Object> v2, TablaDeSimbolos ts, LinkedList<Mensaje> mensajes){
        
        LinkedList lista_temporal = new LinkedList<Object>();
        Operacion ope_temporal;
        Instruccion auxiliar;
        LinkedList<Instruccion> lista_auxiliar;
        
        if(v1.size() == v2.size()){
            
            for(int i=0;i<v1.size();i++){
                ope_temporal = new Operacion(SUMA,new Operacion(v1.get(i),linea,columna),new Operacion(v2.get(i),linea,columna),linea,columna);
                lista_temporal.add(ope_temporal.ejecutar(ts, mensajes));
            }
            
            return lista_temporal;            
        }
        
        else if(v1.size() == 1){
            lista_auxiliar = new LinkedList<Instruccion>();
            
            for(Object obj: v2)
                lista_auxiliar.add(new Operacion(obj,linea,columna));
            
            auxiliar = new LlamadaFunction("c",lista_auxiliar,linea,columna);
            
            ope_temporal = new Operacion(SUMA,new Operacion(v1.get(0),linea,columna),auxiliar,linea,columna);
            return (LinkedList)((Vector)ope_temporal.ejecutar(ts, mensajes)).getVector();
        }
        
        else if(v2.size() == 1){
            
            lista_auxiliar = new LinkedList<Instruccion>();
            
            for(Object obj: v1)
                lista_auxiliar.add(new Operacion(obj,linea,columna));
            
            auxiliar = new LlamadaFunction("c",lista_auxiliar,linea,columna);
            
            ope_temporal = new Operacion(SUMA,new Operacion(v2.get(0),linea,columna),auxiliar,linea,columna);
            return (LinkedList)((Vector)ope_temporal.ejecutar(ts, mensajes)).getVector();
        }
          
        return null;
    }
    
    public LinkedList<Object> resta_vector(LinkedList<Object> listap, Double valor){
        
        Double t1;
        LinkedList lista = new LinkedList<Object>();
        lista.addAll(listap);
        
        for(int i=0;i<lista.size();i++){
            
            if(lista.get(i) instanceof Integer || lista.get(i) instanceof Double){
                
                t1 = new Double(lista.get(i).toString());
                lista.set(i, (t1-valor));    
            }
            
        }
        
        return lista;
    }
    
    public LinkedList<Object> resta_vector(Double valor,LinkedList<Object> listap){
        
        Double t1;
        LinkedList lista = new LinkedList<Object>();
        lista.addAll(listap);
        
        for(int i=0;i<lista.size();i++){
            
            if(lista.get(i) instanceof Integer || lista.get(i) instanceof Double){
                
                t1 = new Double(lista.get(i).toString());
                lista.set(i, (valor)-t1);    
            }
            
        }
        
        return lista;
    }
    
    public LinkedList<Object> resta_vector(LinkedList<Object> listap, int valor){
        
        Double t1;
        int t2;
        LinkedList lista = new LinkedList<Object>();
        lista.addAll(listap);
        
        for(int i=0;i<lista.size();i++){
            
            if(lista.get(i) instanceof Integer){             
                t2 = new Integer(lista.get(i).toString());
                lista.set(i, (t2-valor));    
            }
            
            else if(lista.get(i) instanceof Double){             
                t1 = new Double(lista.get(i).toString());
                lista.set(i, (t1-valor));    
            }
            
        }
        
        return lista;
    }
    
    public LinkedList<Object> resta_vector(int valor,LinkedList<Object> listap){
        
        Double t1;
        int t2;
        LinkedList lista = new LinkedList<Object>();
        lista.addAll(listap);
        
        for(int i=0;i<lista.size();i++){
            
            if(lista.get(i) instanceof Integer){             
                t2 = new Integer(lista.get(i).toString());
                lista.set(i, (valor)-t2);    
            }
            
            else if(lista.get(i) instanceof Double){             
                t1 = new Double(lista.get(i).toString());
                lista.set(i, (valor)-t1);    
            }
            
        }
        
        return lista;
    }
    
    public LinkedList<Object> resta_vector(LinkedList<Object> v1, LinkedList<Object> v2, TablaDeSimbolos ts, LinkedList<Mensaje> mensajes){
        
        LinkedList lista_temporal = new LinkedList<Object>();
        Operacion ope_temporal;
        Instruccion auxiliar;
        LinkedList<Instruccion> lista_auxiliar;
        
        if(v1.size() == v2.size()){
            
            for(int i=0;i<v1.size();i++){
                ope_temporal = new Operacion(RESTA,new Operacion(v1.get(i),linea,columna),new Operacion(v2.get(i),linea,columna),linea,columna);
                lista_temporal.add(ope_temporal.ejecutar(ts, mensajes));
            }
            
            return lista_temporal;            
        }
        
        else if(v1.size() == 1){
            lista_auxiliar = new LinkedList<Instruccion>();
            
            for(Object obj: v2)
                lista_auxiliar.add(new Operacion(obj,linea,columna));
            
            auxiliar = new LlamadaFunction("c",lista_auxiliar,linea,columna);
            
            ope_temporal = new Operacion(RESTA,new Operacion(v1.get(0),linea,columna),auxiliar,linea,columna);
            return (LinkedList)((Vector)ope_temporal.ejecutar(ts, mensajes)).getVector();
        }
        
        else if(v2.size() == 1){
            
            lista_auxiliar = new LinkedList<Instruccion>();
            
            for(Object obj: v1)
                lista_auxiliar.add(new Operacion(obj,linea,columna));
            
            auxiliar = new LlamadaFunction("c",lista_auxiliar,linea,columna);
            
            ope_temporal = new Operacion(RESTA,auxiliar,new Operacion(v2.get(0),linea,columna),linea,columna);
            return (LinkedList)((Vector)ope_temporal.ejecutar(ts, mensajes)).getVector();
        }
          
        return null;
    }
    
    public LinkedList<Object> multiplicacion_vector(LinkedList<Object> listap,Double valor){
        
        Double t1;
        LinkedList lista = new LinkedList<Object>();
        lista.addAll(listap);
        
        for(int i=0;i<lista.size();i++){
            
            if(lista.get(i) instanceof Integer || lista.get(i) instanceof Double){
                
                t1 = new Double(lista.get(i).toString());
                lista.set(i, (valor)*t1);    
            }
            
        }
        
        return lista;
    }
    
    public LinkedList<Object> multiplicacion_vector(LinkedList<Object> listap,int valor){
        
        Double t1;
        int t2;
        LinkedList lista = new LinkedList<Object>();
        lista.addAll(listap);
        
        for(int i=0;i<lista.size();i++){
            
            if(lista.get(i) instanceof Integer){             
                t2 = new Integer(lista.get(i).toString());
                lista.set(i, (valor)*t2);    
            }
            
            else if(lista.get(i) instanceof Double){             
                t1 = new Double(lista.get(i).toString());
                lista.set(i, (valor)*t1);    
            }
            else{
                return null;
            }
            
        }
        
        return lista;
    }
    
    public LinkedList<Object> multiplicacion_vector(LinkedList<Object> v1, LinkedList<Object> v2, TablaDeSimbolos ts, LinkedList<Mensaje> mensajes){
        
        LinkedList lista_temporal = new LinkedList<Object>();
        Operacion ope_temporal;
        Instruccion auxiliar;
        LinkedList<Instruccion> lista_auxiliar;
        
        if(v1.size() == v2.size()){
            
            for(int i=0;i<v1.size();i++){
                ope_temporal = new Operacion(MULTIPLICACION,new Operacion(v1.get(i),linea,columna),new Operacion(v2.get(i),linea,columna),linea,columna);
                lista_temporal.add(ope_temporal.ejecutar(ts, mensajes));
            }
            
            return lista_temporal;            
        }
        
        else if(v1.size() == 1){
            lista_auxiliar = new LinkedList<Instruccion>();
            
            for(Object obj: v2)
                lista_auxiliar.add(new Operacion(obj,linea,columna));
            
            auxiliar = new LlamadaFunction("c",lista_auxiliar,linea,columna);
            
            ope_temporal = new Operacion(MULTIPLICACION,new Operacion(v1.get(0),linea,columna),auxiliar,linea,columna);
            return (LinkedList)((Vector)ope_temporal.ejecutar(ts, mensajes)).getVector();
        }
        
        else if(v2.size() == 1){
            
            lista_auxiliar = new LinkedList<Instruccion>();
            
            for(Object obj: v1)
                lista_auxiliar.add(new Operacion(obj,linea,columna));
            
            auxiliar = new LlamadaFunction("c",lista_auxiliar,linea,columna);
            
            ope_temporal = new Operacion(MULTIPLICACION,auxiliar,new Operacion(v2.get(0),linea,columna),linea,columna);
            return (LinkedList)((Vector)ope_temporal.ejecutar(ts, mensajes)).getVector();
        }
          
        return null;
    }
    
    public LinkedList<Object> division_vector(LinkedList<Object> listap, Double valor){
        
        Double t1;
        LinkedList lista = new LinkedList<Object>();
        lista.addAll(listap);
        
        if(valor == 0)
            return null;
        
        for(int i=0;i<lista.size();i++){
            
            if(lista.get(i) instanceof Integer || lista.get(i) instanceof Double){
                
                t1 = new Double(lista.get(i).toString());
                lista.set(i, (t1/valor));    
            }
            
        }
        
        return lista;
    }
    
    public LinkedList<Object> division_vector(Double valor,LinkedList<Object> listap){
        
        Double t1;
        LinkedList lista_temporal = new LinkedList<Object>();
        LinkedList lista = new LinkedList<Object>();
        lista.addAll(listap);
        
        for(int i=0;i<lista.size();i++){
            
            if(lista.get(i) instanceof Integer || lista.get(i) instanceof Double){
                
                t1 = new Double(lista.get(i).toString());
                
                if(t1 == 0)
                    return null;
                
                lista_temporal.add(valor/t1);    
            }
            
        }
        
        return lista_temporal;
    }
    
    public LinkedList<Object> division_vector(LinkedList<Object> listap, int valor){
        
        Double t1;
        int t2;
        LinkedList lista = new LinkedList<Object>();
        lista.addAll(listap);
        
        if(valor == 0)
            return null;
        
        for(int i=0;i<lista.size();i++){
            
            if(lista.get(i) instanceof Integer){             
                t2 = new Integer(lista.get(i).toString());
                lista.set(i, (t2/valor));    
            }
            
            else if(lista.get(i) instanceof Double){             
                t1 = new Double(lista.get(i).toString());
                lista.set(i, (t1/valor));    
            }
            
        }
        
        return lista;
    }
    
    public LinkedList<Object> division_vector(int valor,LinkedList<Object> lista){
        
        Double t1;
        int t2;
        LinkedList lista_temporal = new LinkedList<Object>();
        
        for(int i=0;i<lista.size();i++){
            
            if(lista.get(i) instanceof Integer){             
                t2 = new Integer(lista.get(i).toString());
                
                if(t2 == 0)
                    return null;
                
                lista_temporal.add(valor/t2);    
            }
            
            else if(lista.get(i) instanceof Double){             
                t1 = new Double(lista.get(i).toString());
                
                if(t1 == 0)
                    return null;
                
                lista_temporal.add(valor/t1);    
            }
            
        }
        
        return lista_temporal;
    }

    public LinkedList<Object> division_vector(LinkedList<Object> v1, LinkedList<Object> v2, TablaDeSimbolos ts, LinkedList<Mensaje> mensajes){

        LinkedList lista_temporal = new LinkedList<Object>();
        Operacion ope_temporal;
        Instruccion auxiliar;
        LinkedList<Instruccion> lista_auxiliar;

        if(v1.size() == v2.size()){

            for(int i=0;i<v1.size();i++){
                ope_temporal = new Operacion(DIVISION,new Operacion(v1.get(i),linea,columna),new Operacion(v2.get(i),linea,columna),linea,columna);
                lista_temporal.add(ope_temporal.ejecutar(ts, mensajes));
            }

            return lista_temporal;            
        }

        else if(v1.size() == 1){
            lista_auxiliar = new LinkedList<Instruccion>();

            for(Object obj: v2)
                lista_auxiliar.add(new Operacion(obj,linea,columna));

            auxiliar = new LlamadaFunction("c",lista_auxiliar,linea,columna);

            ope_temporal = new Operacion(DIVISION,new Operacion(v1.get(0),linea,columna),auxiliar,linea,columna);
            return (LinkedList)((Vector)ope_temporal.ejecutar(ts, mensajes)).getVector();
        }

        else if(v2.size() == 1){
            
            try{
                Double temporal = Double.parseDouble(v2.get(0).toString());
                if(temporal == 0)
                {
                    mensajes.add(new Mensaje(linea,columna,SEMANTICO,"La división entre cero no está definida."));
                    return null;
                }
            }
            catch(Exception e){
                return null;
            }

            lista_auxiliar = new LinkedList<Instruccion>();

            for(Object obj: v1)
                lista_auxiliar.add(new Operacion(obj,linea,columna));

            auxiliar = new LlamadaFunction("c",lista_auxiliar,linea,columna);

            ope_temporal = new Operacion(DIVISION,auxiliar,new Operacion(v2.get(0),linea,columna),linea,columna);
            return (LinkedList)((Vector)ope_temporal.ejecutar(ts, mensajes)).getVector();
        }

        return null;
    }
    
    public LinkedList<Object> potencia_vector(LinkedList<Object> listap, Double valor){
        Double t1;
        LinkedList lista = new LinkedList<Object>();
        lista.addAll(listap);
        
        for(int i=0;i<lista.size();i++){
            
            if(lista.get(i) instanceof Integer || lista.get(i) instanceof Double){
                t1 = new Double(lista.get(i).toString());
                lista.set(i, Math.pow(t1, valor));    
            }
            
        }
        
        return lista;
    }
    
    public LinkedList<Object> potencia_vector(Double valor,LinkedList<Object> listap){
        Double t1;
        LinkedList lista = new LinkedList<Object>();
        lista.addAll(listap);
        
        for(int i=0;i<lista.size();i++){
            
            if(lista.get(i) instanceof Integer || lista.get(i) instanceof Double){
                t1 = new Double(lista.get(i).toString());
                lista.set(i, Math.pow(valor, t1));    
            }
            
        }
        
        return lista;
    }
    
    public LinkedList<Object> potencia_vector(LinkedList<Object> v1, LinkedList<Object> v2, TablaDeSimbolos ts, LinkedList<Mensaje> mensajes){
        
        LinkedList lista_temporal = new LinkedList<Object>();
        Operacion ope_temporal;
        Instruccion auxiliar;
        LinkedList<Instruccion> lista_auxiliar;
        
        if(v1.size() == v2.size()){
            
            for(int i=0;i<v1.size();i++){
                ope_temporal = new Operacion(POTENCIA,new Operacion(v1.get(i),linea,columna),new Operacion(v2.get(i),linea,columna),linea,columna);
                lista_temporal.add(ope_temporal.ejecutar(ts, mensajes));
            }
            
            return lista_temporal;            
        }
        
        else if(v1.size() == 1){
            lista_auxiliar = new LinkedList<Instruccion>();
            
            for(Object obj: v2)
                lista_auxiliar.add(new Operacion(obj,linea,columna));
            
            auxiliar = new LlamadaFunction("c",lista_auxiliar,linea,columna);
            
            ope_temporal = new Operacion(POTENCIA,new Operacion(v1.get(0),linea,columna),auxiliar,linea,columna);
            return (LinkedList)((Vector)ope_temporal.ejecutar(ts, mensajes)).getVector();
        }
        
        else if(v2.size() == 1){
            
            lista_auxiliar = new LinkedList<Instruccion>();
            
            for(Object obj: v1)
                lista_auxiliar.add(new Operacion(obj,linea,columna));
            
            auxiliar = new LlamadaFunction("c",lista_auxiliar,linea,columna);
            
            ope_temporal = new Operacion(POTENCIA,auxiliar,new Operacion(v2.get(0),linea,columna),linea,columna);
            return (LinkedList)((Vector)ope_temporal.ejecutar(ts, mensajes)).getVector();
        }
          
        return null;
    }
    
    public LinkedList<Object> modulo_vector(LinkedList<Object> listap, Double valor){
        
        Double t1;
        LinkedList lista = new LinkedList<Object>();
        lista.addAll(listap);
        
        for(int i=0;i<lista.size();i++){
            
            if(lista.get(i) instanceof Integer || lista.get(i) instanceof Double){
                
                t1 = new Double(lista.get(i).toString());
                lista.set(i, (t1%valor));    
            }
            
        }
        
        return lista;
    }
    
    public LinkedList<Object> modulo_vector(Double valor,LinkedList<Object> listap){
        
        Double t1;
        LinkedList lista = new LinkedList<Object>();
        lista.addAll(listap);
        
        for(int i=0;i<lista.size();i++){
            
            if(lista.get(i) instanceof Integer || lista.get(i) instanceof Double){
                
                t1 = new Double(lista.get(i).toString());
                lista.set(i, (valor)%t1);    
            }
            
        }
        
        return lista;
    }
    
    public LinkedList<Object> modulo_vector(LinkedList<Object> listap, int valor){
        
        Double t1;
        int t2;
        LinkedList lista = new LinkedList<Object>();
        lista.addAll(listap);
        
        for(int i=0;i<lista.size();i++){
            
            if(lista.get(i) instanceof Integer){             
                t2 = new Integer(lista.get(i).toString());
                lista.set(i, (t2-valor));    
            }
            
            else if(lista.get(i) instanceof Double){             
                t1 = new Double(lista.get(i).toString());
                lista.set(i, (t1%valor));    
            }
            
        }
        
        return lista;
    }
    
    public LinkedList<Object> modulo_vector(int valor,LinkedList<Object> listap){
        
        Double t1;
        int t2;
        LinkedList lista = new LinkedList<Object>();
        lista.addAll(listap);
        
        for(int i=0;i<lista.size();i++){
            
            if(lista.get(i) instanceof Integer){             
                t2 = new Integer(lista.get(i).toString());
                lista.set(i, (valor)%t2);    
            }
            
            else if(lista.get(i) instanceof Double){             
                t1 = new Double(lista.get(i).toString());
                lista.set(i, (valor)%t1);    
            }
            
        }
        
        return lista;
    }
    
    public LinkedList<Object> modulo_vector(LinkedList<Object> v1, LinkedList<Object> v2, TablaDeSimbolos ts, LinkedList<Mensaje> mensajes){
        
        LinkedList lista_temporal = new LinkedList<Object>();
        Operacion ope_temporal;
        Instruccion auxiliar;
        LinkedList<Instruccion> lista_auxiliar;
        
        if(v1.size() == v2.size()){
            
            for(int i=0;i<v1.size();i++){
                ope_temporal = new Operacion(MODULO,new Operacion(v1.get(i),linea,columna),new Operacion(v2.get(i),linea,columna),linea,columna);
                lista_temporal.add(ope_temporal.ejecutar(ts, mensajes));
            }
            
            return lista_temporal;            
        }
        
        else if(v1.size() == 1){
            lista_auxiliar = new LinkedList<Instruccion>();
            
            for(Object obj: v2)
                lista_auxiliar.add(new Operacion(obj,linea,columna));
            
            auxiliar = new LlamadaFunction("c",lista_auxiliar,linea,columna);
            
            ope_temporal = new Operacion(MODULO,new Operacion(v1.get(0),linea,columna),auxiliar,linea,columna);
            return (LinkedList)((Vector)ope_temporal.ejecutar(ts, mensajes)).getVector();
        }
        
        else if(v2.size() == 1){
            
            lista_auxiliar = new LinkedList<Instruccion>();
            
            for(Object obj: v1)
                lista_auxiliar.add(new Operacion(obj,linea,columna));
            
            auxiliar = new LlamadaFunction("c",lista_auxiliar,linea,columna);
            
            ope_temporal = new Operacion(MODULO,auxiliar,new Operacion(v2.get(0),linea,columna),linea,columna);
            return (LinkedList)((Vector)ope_temporal.ejecutar(ts, mensajes)).getVector();
        }
          
        return null;
    }
    
    public LinkedList<Object> resta_unaria_vector(LinkedList<Object> listap){
        Double t1;
        LinkedList lista = new LinkedList<Object>();
        lista.addAll(listap);
        
        for(int i=0;i<lista.size();i++){
            
            if(lista.get(i) instanceof Integer || lista.get(i) instanceof Double){            
                t1 = new Double(lista.get(i).toString());
                lista.set(i, (t1*-1));    
            }
        }
        
        return lista;
    }
    
    public LinkedList<Object> igual_que_vector(LinkedList<Object> listap, Double valor){
        
        Double t1;
        LinkedList lista = new LinkedList<Object>();
        lista.addAll(listap);
        
        for(int i=0;i<lista.size();i++){
            
            if(lista.get(i) instanceof Integer || lista.get(i) instanceof Double){
                
                t1 = new Double(lista.get(i).toString());
                lista.set(i, (t1==valor));    
            }
            
        }
        
        return lista;
    }
    
    public LinkedList<Object> igual_que_vector(LinkedList<Object> listap, int valor){
        
        Double t1;
        int t2;
        LinkedList lista = new LinkedList<Object>();
        lista.addAll(listap);
        
        for(int i=0;i<lista.size();i++){
            
            if(lista.get(i) instanceof Integer){             
                t2 = new Integer(lista.get(i).toString());
                lista.set(i, (t2==valor));    
            }
            
            else if(lista.get(i) instanceof Double){             
                t1 = new Double(lista.get(i).toString());
                lista.set(i, (t1==valor));    
            }
            
        }
        
        return lista;
    }
    
    public LinkedList<Object> igual_que_vector(LinkedList<Object> listap, String valor){
        LinkedList lista = new LinkedList<Object>();
        lista.addAll(listap);
        
        for(int i=0;i<lista.size();i++){
            lista.set(i,lista.get(i).toString().compareTo(valor)==0);
        }      
        return lista;
    }
        
    public LinkedList<Object> igual_que_vector(LinkedList<Object> v1, LinkedList<Object> v2, TablaDeSimbolos ts, LinkedList<Mensaje> mensajes){
        
        LinkedList lista_temporal = new LinkedList<Object>();
        Operacion ope_temporal;
        Instruccion auxiliar;
        LinkedList<Instruccion> lista_auxiliar;
        
        if(v1.size() == v2.size()){
            
            for(int i=0;i<v1.size();i++){
                ope_temporal = new Operacion(IGUAL_QUE,new Operacion(v1.get(i),linea,columna),new Operacion(v2.get(i),linea,columna),linea,columna);
                lista_temporal.add(ope_temporal.ejecutar(ts, mensajes));
            }
            
            return lista_temporal;            
        }
        
        else if(v1.size() == 1){
            lista_auxiliar = new LinkedList<Instruccion>();
            
            for(Object obj: v2)
                lista_auxiliar.add(new Operacion(obj,linea,columna));
            
            auxiliar = new LlamadaFunction("c",lista_auxiliar,linea,columna);
            
            ope_temporal = new Operacion(IGUAL_QUE,new Operacion(v1.get(0),linea,columna),auxiliar,linea,columna);
            return (LinkedList)((Vector)ope_temporal.ejecutar(ts, mensajes)).getVector();
        }
        
        else if(v2.size() == 1){
            
            lista_auxiliar = new LinkedList<Instruccion>();
            
            for(Object obj: v1)
                lista_auxiliar.add(new Operacion(obj,linea,columna));
            
            auxiliar = new LlamadaFunction("c",lista_auxiliar,linea,columna);
            
            ope_temporal = new Operacion(IGUAL_QUE,new Operacion(v2.get(0),linea,columna),auxiliar,linea,columna);
            return (LinkedList)((Vector)ope_temporal.ejecutar(ts, mensajes)).getVector();
        }
          
        return null;
    }

    public LinkedList<Object> distinto_que_vector(LinkedList<Object> listap, Double valor){
        
        Double t1;
        LinkedList lista = new LinkedList<Object>();
        lista.addAll(listap);
        
        for(int i=0;i<lista.size();i++){
            
            if(lista.get(i) instanceof Integer || lista.get(i) instanceof Double){
                
                t1 = new Double(lista.get(i).toString());
                lista.set(i, (t1!=valor));    
            }
            
        }
        
        return lista;
    }
    
    public LinkedList<Object> distinto_que_vector(LinkedList<Object> listap, int valor){
        
        Double t1;
        int t2;
        LinkedList lista = new LinkedList<Object>();
        lista.addAll(listap);
        
        for(int i=0;i<lista.size();i++){
            
            if(lista.get(i) instanceof Integer){             
                t2 = new Integer(lista.get(i).toString());
                lista.set(i, (t2!=valor));    
            }
            
            else if(lista.get(i) instanceof Double){             
                t1 = new Double(lista.get(i).toString());
                lista.set(i, (t1!=valor));    
            }
            
        }
        
        return lista;
    }
    
    public LinkedList<Object> distinto_que_vector(LinkedList<Object> listap, String valor){
        LinkedList lista = new LinkedList<Object>();
        lista.addAll(listap);
        
        for(int i=0;i<lista.size();i++){
            lista.set(i,lista.get(i).toString().compareTo(valor)!=0);
        }      
        return lista;
    }
        
    public LinkedList<Object> distinto_que_vector(LinkedList<Object> v1, LinkedList<Object> v2, TablaDeSimbolos ts, LinkedList<Mensaje> mensajes){
        
        LinkedList lista_temporal = new LinkedList<Object>();
        Operacion ope_temporal;
        Instruccion auxiliar;
        LinkedList<Instruccion> lista_auxiliar;
        
        if(v1.size() == v2.size()){
            
            for(int i=0;i<v1.size();i++){
                ope_temporal = new Operacion(DISTINTO_QUE,new Operacion(v1.get(i),linea,columna),new Operacion(v2.get(i),linea,columna),linea,columna);
                lista_temporal.add(ope_temporal.ejecutar(ts, mensajes));
            }
            
            return lista_temporal;            
        }
        
        else if(v1.size() == 1){
            lista_auxiliar = new LinkedList<Instruccion>();
            
            for(Object obj: v2)
                lista_auxiliar.add(new Operacion(obj,linea,columna));
            
            auxiliar = new LlamadaFunction("c",lista_auxiliar,linea,columna);
            
            ope_temporal = new Operacion(DISTINTO_QUE,new Operacion(v1.get(0),linea,columna),auxiliar,linea,columna);
            return (LinkedList)((Vector)ope_temporal.ejecutar(ts, mensajes)).getVector();
        }
        
        else if(v2.size() == 1){
            
            lista_auxiliar = new LinkedList<Instruccion>();
            
            for(Object obj: v1)
                lista_auxiliar.add(new Operacion(obj,linea,columna));
            
            auxiliar = new LlamadaFunction("c",lista_auxiliar,linea,columna);
            
            ope_temporal = new Operacion(DISTINTO_QUE,new Operacion(v2.get(0),linea,columna),auxiliar,linea,columna);
            return (LinkedList)((Vector)ope_temporal.ejecutar(ts, mensajes)).getVector();
        }
          
        return null;
    }

    public LinkedList<Object> mayor_que_vector(LinkedList<Object> listap, Double valor){
        
        Double t1;
        LinkedList lista = new LinkedList<Object>();
        lista.addAll(listap);
        
        for(int i=0;i<lista.size();i++){
            
            if(lista.get(i) instanceof Integer || lista.get(i) instanceof Double){
                
                t1 = new Double(lista.get(i).toString());
                lista.set(i, (t1>valor));    
            }
            
        }
        
        return lista;
    }
    
    public LinkedList<Object> mayor_que_vector(LinkedList<Object> listap, String valor){
        
        LinkedList lista = new LinkedList<Object>();
        lista.addAll(listap);
        
        for(int i=0;i<lista.size();i++){
            lista.set(i, (lista.get(i).toString().compareTo(valor)>0));    
        }
        
        return lista;
    }
    
    public LinkedList<Object> mayor_que_vector(LinkedList<Object> v1, LinkedList<Object> v2, TablaDeSimbolos ts, LinkedList<Mensaje> mensajes){
        
        LinkedList lista_temporal = new LinkedList<Object>();
        Operacion ope_temporal;
        Instruccion auxiliar;
        LinkedList<Instruccion> lista_auxiliar;
        
        if(v1.size() == v2.size()){
            
            for(int i=0;i<v1.size();i++){
                ope_temporal = new Operacion(MAYOR_QUE,new Operacion(v1.get(i),linea,columna),new Operacion(v2.get(i),linea,columna),linea,columna);
                Object r = ope_temporal.ejecutar(ts, mensajes);
                lista_temporal.add(ope_temporal.ejecutar(ts, mensajes));
            }
            
            return lista_temporal;            
        }
        
        else if(v1.size() == 1){
            lista_auxiliar = new LinkedList<Instruccion>();
            
            for(Object obj: v2)
                lista_auxiliar.add(new Operacion(obj,linea,columna));
            
            auxiliar = new LlamadaFunction("c",lista_auxiliar,linea,columna);
            
            ope_temporal = new Operacion(MAYOR_QUE,new Operacion(v1.get(0),linea,columna),auxiliar,linea,columna);
            return (LinkedList)((Vector)ope_temporal.ejecutar(ts, mensajes)).getVector();
        }
        
        else if(v2.size() == 1){
            
            lista_auxiliar = new LinkedList<Instruccion>();
            
            for(Object obj: v1)
                lista_auxiliar.add(new Operacion(obj,linea,columna));
            
            auxiliar = new LlamadaFunction("c",lista_auxiliar,linea,columna);
            
            ope_temporal = new Operacion(MAYOR_QUE,new Operacion(v2.get(0),linea,columna),auxiliar,linea,columna);
            return (LinkedList)((Vector)ope_temporal.ejecutar(ts, mensajes)).getVector();
        }
          
        return null;
    }
    
    public LinkedList<Object> menor_que_vector(LinkedList<Object> listap, Double valor){
        
        Double t1;
        LinkedList lista = new LinkedList<Object>();
        lista.addAll(listap);
        
        for(int i=0;i<lista.size();i++){
            
            if(lista.get(i) instanceof Integer || lista.get(i) instanceof Double){
                
                t1 = new Double(lista.get(i).toString());
                lista.set(i, (t1<valor));    
            }
            
        }
        
        return lista;
    }
    
    public LinkedList<Object> menor_que_vector(LinkedList<Object> listap, String valor){
        
        LinkedList lista = new LinkedList<Object>();
        lista.addAll(listap);
        
        for(int i=0;i<lista.size();i++){
            lista.set(i, (lista.get(i).toString().compareTo(valor)<0));    
        }
        
        return lista;
    }
    
    public LinkedList<Object> menor_que_vector(LinkedList<Object> v1, LinkedList<Object> v2, TablaDeSimbolos ts, LinkedList<Mensaje> mensajes){
        
        LinkedList lista_temporal = new LinkedList<Object>();
        Operacion ope_temporal;
        Instruccion auxiliar;
        LinkedList<Instruccion> lista_auxiliar;
        
        if(v1.size() == v2.size()){
            
            for(int i=0;i<v1.size();i++){
                ope_temporal = new Operacion(MENOR_QUE,new Operacion(v1.get(i),linea,columna),new Operacion(v2.get(i),linea,columna),linea,columna);
                Object r = ope_temporal.ejecutar(ts, mensajes);
                lista_temporal.add(ope_temporal.ejecutar(ts, mensajes));
            }
            
            return lista_temporal;            
        }
        
        else if(v1.size() == 1){
            lista_auxiliar = new LinkedList<Instruccion>();
            
            for(Object obj: v2)
                lista_auxiliar.add(new Operacion(obj,linea,columna));
            
            auxiliar = new LlamadaFunction("c",lista_auxiliar,linea,columna);
            
            ope_temporal = new Operacion(MENOR_QUE,new Operacion(v1.get(0),linea,columna),auxiliar,linea,columna);
            return (LinkedList)((Vector)ope_temporal.ejecutar(ts, mensajes)).getVector();
        }
        
        else if(v2.size() == 1){
            
            lista_auxiliar = new LinkedList<Instruccion>();
            
            for(Object obj: v1)
                lista_auxiliar.add(new Operacion(obj,linea,columna));
            
            auxiliar = new LlamadaFunction("c",lista_auxiliar,linea,columna);
            
            ope_temporal = new Operacion(MENOR_QUE,new Operacion(v2.get(0),linea,columna),auxiliar,linea,columna);
            return (LinkedList)((Vector)ope_temporal.ejecutar(ts, mensajes)).getVector();
        }
          
        return null;
    }

    public LinkedList<Object> mayor_igual_que_vector(LinkedList<Object> listap, Double valor){
        
        Double t1;
        LinkedList lista = new LinkedList<Object>();
        lista.addAll(listap);
        
        for(int i=0;i<lista.size();i++){
            
            if(lista.get(i) instanceof Integer || lista.get(i) instanceof Double){
                
                t1 = new Double(lista.get(i).toString());
                lista.set(i, (t1>=valor));    
            }
            
        }
        
        return lista;
    }
    
    public LinkedList<Object> mayor_igual_que_vector(LinkedList<Object> listap, String valor){
        
        LinkedList lista = new LinkedList<Object>();
        lista.addAll(listap);
        
        for(int i=0;i<lista.size();i++){
            lista.set(i, (lista.get(i).toString().compareTo(valor)>=0));    
        }
        
        return lista;
    }
    
    public LinkedList<Object> mayor_igual_que_vector(LinkedList<Object> v1, LinkedList<Object> v2, TablaDeSimbolos ts, LinkedList<Mensaje> mensajes){
        
        LinkedList lista_temporal = new LinkedList<Object>();
        Operacion ope_temporal;
        Instruccion auxiliar;
        LinkedList<Instruccion> lista_auxiliar;
        
        if(v1.size() == v2.size()){
            
            for(int i=0;i<v1.size();i++){
                ope_temporal = new Operacion(MAYOR_IGUAL_QUE,new Operacion(v1.get(i),linea,columna),new Operacion(v2.get(i),linea,columna),linea,columna);
                Object r = ope_temporal.ejecutar(ts, mensajes);
                lista_temporal.add(ope_temporal.ejecutar(ts, mensajes));
            }
            
            return lista_temporal;            
        }
        
        else if(v1.size() == 1){
            lista_auxiliar = new LinkedList<Instruccion>();
            
            for(Object obj: v2)
                lista_auxiliar.add(new Operacion(obj,linea,columna));
            
            auxiliar = new LlamadaFunction("c",lista_auxiliar,linea,columna);
            
            ope_temporal = new Operacion(MAYOR_IGUAL_QUE,new Operacion(v1.get(0),linea,columna),auxiliar,linea,columna);
            return (LinkedList)((Vector)ope_temporal.ejecutar(ts, mensajes)).getVector();
        }
        
        else if(v2.size() == 1){
            
            lista_auxiliar = new LinkedList<Instruccion>();
            
            for(Object obj: v1)
                lista_auxiliar.add(new Operacion(obj,linea,columna));
            
            auxiliar = new LlamadaFunction("c",lista_auxiliar,linea,columna);
            
            ope_temporal = new Operacion(MAYOR_IGUAL_QUE,new Operacion(v2.get(0),linea,columna),auxiliar,linea,columna);
            return (LinkedList)((Vector)ope_temporal.ejecutar(ts, mensajes)).getVector();
        }
          
        return null;
    }

    public LinkedList<Object> menor_igual_que_vector(LinkedList<Object> listap, Double valor){
        
        Double t1;
        LinkedList lista = new LinkedList<Object>();
        lista.addAll(listap);
        
        for(int i=0;i<lista.size();i++){
            
            if(lista.get(i) instanceof Integer || lista.get(i) instanceof Double){
                
                t1 = new Double(lista.get(i).toString());
                lista.set(i, (t1<=valor));    
            }
            
        }
        
        return lista;
    }
    
    public LinkedList<Object> menor_igual_que_vector(LinkedList<Object> listap, String valor){
        
        LinkedList lista = new LinkedList<Object>();
        lista.addAll(listap);
        
        for(int i=0;i<lista.size();i++){
            lista.set(i, (lista.get(i).toString().compareTo(valor)<=0));    
        }
        
        return lista;
    }
    
    public LinkedList<Object> menor_igual_que_vector(LinkedList<Object> v1, LinkedList<Object> v2, TablaDeSimbolos ts, LinkedList<Mensaje> mensajes){
        
        LinkedList lista_temporal = new LinkedList<Object>();
        Operacion ope_temporal;
        Instruccion auxiliar;
        LinkedList<Instruccion> lista_auxiliar;
        
        if(v1.size() == v2.size()){
            
            for(int i=0;i<v1.size();i++){
                ope_temporal = new Operacion(MENOR_IGUAL_QUE,new Operacion(v1.get(i),linea,columna),new Operacion(v2.get(i),linea,columna),linea,columna);
                Object r = ope_temporal.ejecutar(ts, mensajes);
                lista_temporal.add(ope_temporal.ejecutar(ts, mensajes));
            }
            
            return lista_temporal;            
        }
        
        else if(v1.size() == 1){
            lista_auxiliar = new LinkedList<Instruccion>();
            
            for(Object obj: v2)
                lista_auxiliar.add(new Operacion(obj,linea,columna));
            
            auxiliar = new LlamadaFunction("c",lista_auxiliar,linea,columna);
            
            ope_temporal = new Operacion(MENOR_IGUAL_QUE,new Operacion(v1.get(0),linea,columna),auxiliar,linea,columna);
            return (LinkedList)((Vector)ope_temporal.ejecutar(ts, mensajes)).getVector();
        }
        
        else if(v2.size() == 1){
            
            lista_auxiliar = new LinkedList<Instruccion>();
            
            for(Object obj: v1)
                lista_auxiliar.add(new Operacion(obj,linea,columna));
            
            auxiliar = new LlamadaFunction("c",lista_auxiliar,linea,columna);
            
            ope_temporal = new Operacion(MENOR_IGUAL_QUE,new Operacion(v2.get(0),linea,columna),auxiliar,linea,columna);
            return (LinkedList)((Vector)ope_temporal.ejecutar(ts, mensajes)).getVector();
        }
          
        return null;
    }
    
    public LinkedList<Object> and_vector(LinkedList<Object> listap, Boolean valor){
        
        LinkedList<Object> lista = new LinkedList<Object>();
        lista.addAll(listap);
        
        for(int i=0;i<lista.size();i++){
            lista.set(i,((Boolean)lista.get(i))&&valor);
        }
        
        return lista;
    }
  
    public LinkedList<Object> and_vector(LinkedList<Object> v1, LinkedList<Object> v2, TablaDeSimbolos ts, LinkedList<Mensaje> mensajes){
        
        LinkedList lista_temporal = new LinkedList<Object>();
        Operacion ope_temporal;
        Instruccion auxiliar;
        LinkedList<Instruccion> lista_auxiliar;
        
        if(v1.size() == v2.size()){
            
            for(int i=0;i<v1.size();i++){
                ope_temporal = new Operacion(AND,new Operacion(v1.get(i),linea,columna),new Operacion(v2.get(i),linea,columna),linea,columna);
                Object r = ope_temporal.ejecutar(ts, mensajes);
                lista_temporal.add(ope_temporal.ejecutar(ts, mensajes));
            }
            
            return lista_temporal;            
        }
        
        else if(v1.size() == 1){
            lista_auxiliar = new LinkedList<Instruccion>();
            
            for(Object obj: v2)
                lista_auxiliar.add(new Operacion(obj,linea,columna));
            
            auxiliar = new LlamadaFunction("c",lista_auxiliar,linea,columna);
            
            ope_temporal = new Operacion(AND,new Operacion(v1.get(0),linea,columna),auxiliar,linea,columna);
            return (LinkedList)((Vector)ope_temporal.ejecutar(ts, mensajes)).getVector();
        }
        
        else if(v2.size() == 1){
            
            lista_auxiliar = new LinkedList<Instruccion>();
            
            for(Object obj: v1)
                lista_auxiliar.add(new Operacion(obj,linea,columna));
            
            auxiliar = new LlamadaFunction("c",lista_auxiliar,linea,columna);
            
            ope_temporal = new Operacion(AND,new Operacion(v2.get(0),linea,columna),auxiliar,linea,columna);
            return (LinkedList)((Vector)ope_temporal.ejecutar(ts, mensajes)).getVector();
        }
          
        return null;
    }

    public LinkedList<Object> or_vector(LinkedList<Object> listap, Boolean valor){
        
        LinkedList<Object> lista = new LinkedList<Object>();
        lista.addAll(listap);
        
        for(int i=0;i<lista.size();i++){
            lista.set(i,((Boolean)lista.get(i))||valor);
        }
        
        return lista;
    }
  
    public LinkedList<Object> or_vector(LinkedList<Object> v1, LinkedList<Object> v2, TablaDeSimbolos ts, LinkedList<Mensaje> mensajes){
        
        LinkedList lista_temporal = new LinkedList<Object>();
        Operacion ope_temporal;
        Instruccion auxiliar;
        LinkedList<Instruccion> lista_auxiliar;
        
        if(v1.size() == v2.size()){
            
            for(int i=0;i<v1.size();i++){
                ope_temporal = new Operacion(OR,new Operacion(v1.get(i),linea,columna),new Operacion(v2.get(i),linea,columna),linea,columna);
                Object r = ope_temporal.ejecutar(ts, mensajes);
                lista_temporal.add(ope_temporal.ejecutar(ts, mensajes));
            }
            
            return lista_temporal;            
        }
        
        else if(v1.size() == 1){
            lista_auxiliar = new LinkedList<Instruccion>();
            
            for(Object obj: v2)
                lista_auxiliar.add(new Operacion(obj,linea,columna));
            
            auxiliar = new LlamadaFunction("c",lista_auxiliar,linea,columna);
            
            ope_temporal = new Operacion(OR,new Operacion(v1.get(0),linea,columna),auxiliar,linea,columna);
            return (LinkedList)((Vector)ope_temporal.ejecutar(ts, mensajes)).getVector();
        }
        
        else if(v2.size() == 1){
            
            lista_auxiliar = new LinkedList<Instruccion>();
            
            for(Object obj: v1)
                lista_auxiliar.add(new Operacion(obj,linea,columna));
            
            auxiliar = new LlamadaFunction("c",lista_auxiliar,linea,columna);
            
            ope_temporal = new Operacion(OR,new Operacion(v2.get(0),linea,columna),auxiliar,linea,columna);
            return (LinkedList)((Vector)ope_temporal.ejecutar(ts, mensajes)).getVector();
        }
          
        return null;
    }

    public LinkedList<Object> not_vector(LinkedList<Object> listap){
        
        LinkedList<Object> lista = new LinkedList<Object>();
        lista.addAll(listap);
        
        for(int i=0;i<lista.size();i++){
            lista.set(i,!((Boolean)lista.get(i)));
        }
        
        return lista;
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
        TERNARIO,       //si
        ACCESO_ARREGLO  
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
    public Boolean getAcceso_arreglo() {
        return acceso_arreglo;
    }
    public void setAcceso_arreglo(Boolean acceso_arreglo) {
        this.acceso_arreglo = acceso_arreglo;
    }
    
}