package Entorno;

import static Entorno.Tipo.tipo_primitivo.CADENA;
import static Entorno.Tipo.tipo_primitivo.DECIMAL;
import static Entorno.Tipo.tipo_primitivo.ENTERO;
import Estructuras.Lista;
import Estructuras.Vector;
import Utilidades.Graficas;
import Utilidades.Mensaje;
import static Utilidades.Mensaje.tipo_mensaje.SEMANTICO;
import arbol.Function;
import arbol.Instruccion;
import arbol.Operacion;
import arbol.Operacion.tipo_operacion;
import java.util.LinkedList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

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
        
        if(nombre.compareTo("mean")==0 && parametros.size() == 1)
            return f_mean(parametros.get(0),ts,mensajes,linea,columna);
        
        if(nombre.compareTo("mean")==0 && parametros.size() == 2)
            return f_mean(parametros.get(0),parametros.get(1),ts,mensajes,linea,columna);
        
        if(nombre.compareTo("median")==0 && parametros.size() == 1)
            return f_median(parametros.get(0),ts,mensajes,linea,columna);
        
        if(nombre.compareTo("median")==0 && parametros.size() == 2)
            return f_median(parametros.get(0),parametros.get(1),ts,mensajes,linea,columna);
        
        if(nombre.compareTo("mode")==0 && parametros.size() == 1)
            return f_mode(parametros.get(0),ts,mensajes,linea,columna);
        
        if(nombre.compareTo("mode")==0 && parametros.size() == 2)
            return f_mode(parametros.get(0),parametros.get(1),ts,mensajes,linea,columna);
        
        if(nombre.compareTo("pie")==0 && parametros.size()==3){
            Graficas instancia = Graficas.getSingletonInstance();
            instancia.add_grafica(pieChart(parametros.get(0),parametros.get(1),parametros.get(2),ts,mensajes,linea,columna));
            return null;
        }
        
        if(nombre.compareTo("barplot")==0 && parametros.size()==5){
            Graficas instancia = Graficas.getSingletonInstance();
            instancia.add_grafica(barplot(parametros.get(0),parametros.get(1),parametros.get(2),parametros.get(3),parametros.get(4),ts,mensajes,linea,columna));
            return null;
        }
        
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
  
    public double f_mean(Instruccion vector, TablaDeSimbolos ts, LinkedList<Mensaje> mensajes, int linea, int columna){
        
        try{
            Vector v = (Vector) vector.ejecutar(ts, mensajes);
            double sumatoria = 0;
            double t = 0;
            
            for(Object obj: v.getVector()){
                t = Double.parseDouble(obj.toString());
                sumatoria += t;
            }
            
            return sumatoria/v.size();
            
        }catch(Exception e){
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"No se ha podido procesar la funcion mean."));
        }
        
        return 0;
    }
    
    public double f_mean(Instruccion vector, Instruccion trim, TablaDeSimbolos ts, LinkedList<Mensaje> mensajes, int linea, int columna){
        
        try{
            Vector v = (Vector) vector.ejecutar(ts, mensajes);
            double t_trim = Double.parseDouble(trim.ejecutar(ts, mensajes).toString());
            double sumatoria = 0;
            double t = 0;
            int datos_totales = 0;
            
            for(Object obj: v.getVector()){
                t = Double.parseDouble(obj.toString());
                
                if(t >= t_trim){
                    sumatoria += t;
                    datos_totales += 1;
                }
            }
            
            return sumatoria/datos_totales;
            
        }catch(Exception e){
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"No se ha podido procesar la funcion mean."));
        }
        
        return 0;
    }
    
    public double f_median(Instruccion vector, TablaDeSimbolos ts, LinkedList<Mensaje> mensajes, int linea, int columna){
        
        try{
            Vector v = (Vector) vector.ejecutar(ts, mensajes);
            LinkedList temporal = ordenar_valores(v.getVector());
            
            if(temporal.size()%2==1)
                return Double.parseDouble(temporal.get(((temporal.size()+1)/2)-1).toString());
            else{
                double n1 = Double.parseDouble(temporal.get(temporal.size()/2).toString());
                double n2 = Double.parseDouble(temporal.get(temporal.size()/2-1).toString());
                return (n1+n2)/2;
            }
            
        }catch(Exception e){
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"No se ha podido procesar la funcion mean."));
        }
        
        return 0;    
    }
    
    public double f_median(Instruccion vector, Instruccion trim, TablaDeSimbolos ts, LinkedList<Mensaje> mensajes, int linea, int columna){
        
        try{
            Vector v = (Vector) vector.ejecutar(ts, mensajes);
            LinkedList temporal_valores = ordenar_valores(v.getVector());
            double t_trim = Double.parseDouble(trim.ejecutar(ts, mensajes).toString());
            double t_valor = 0;
            LinkedList temporal = new LinkedList<Object>();
            
            for(Object obj: temporal_valores){
                t_valor = Double.parseDouble(obj.toString());
                
                if(t_valor >= t_trim)
                    temporal.add(obj);               
            }
            
            if(temporal.size()%2==1)
                return Double.parseDouble(temporal.get(((temporal.size()+1)/2)-1).toString());
            else{
                double n1 = Double.parseDouble(temporal.get(temporal.size()/2).toString());
                double n2 = Double.parseDouble(temporal.get(temporal.size()/2-1).toString());
                return (n1+n2)/2;
            }
            
        }catch(Exception e){
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"No se ha podido procesar la funcion mean."));
        }
        
        return 0;    
    }
    
    public double f_mode(Instruccion vector, TablaDeSimbolos ts, LinkedList<Mensaje> mensajes, int linea, int columna){
        
        try{
            Vector v = (Vector) vector.ejecutar(ts, mensajes);
            LinkedList<Object> temporal = ordenar_valores(v.getVector());
            
            double cont_moda = 0;
            double cont_moda_actual = 1;
            double valor_moda = 0;
            double valor_moda_actual = Double.parseDouble(temporal.get(0).toString());
            double v_temporal = 0;
            
            for(int i=1;i<temporal.size();i++){
                v_temporal = Double.parseDouble(temporal.get(i).toString());
                
                if(v_temporal == valor_moda_actual){
                    cont_moda_actual++;
                }
                else{
                    if(cont_moda<cont_moda_actual){
                        cont_moda = cont_moda_actual;
                        valor_moda = valor_moda_actual;
                    }
                    
                    valor_moda_actual = v_temporal;
                    cont_moda_actual = 1;
                }
                
            }
            
            return valor_moda;
            
        }
        catch(Exception e){
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"No se ha podido procesar la funcion mean."));
        }
        
        return 0;
    }
    
    public double f_mode(Instruccion vector, Instruccion trim, TablaDeSimbolos ts, LinkedList<Mensaje> mensajes, int linea, int columna){
        
        try{
            Vector v = (Vector) vector.ejecutar(ts, mensajes);
            LinkedList<Object> temporal = ordenar_valores(v.getVector());
            double t_trim = Double.parseDouble(trim.ejecutar(ts, mensajes).toString());
            int pos = 0;
            double v_temporal = 0;
            
            for(int i=0;i<temporal.size();i++){
                v_temporal = Double.parseDouble(temporal.get(i).toString());
                
                if(v_temporal >= t_trim){
                    pos = i;
                    break;
                }
            }
            
            double cont_moda = 0;
            double cont_moda_actual = 1;
            double valor_moda = 0;
            double valor_moda_actual = Double.parseDouble(temporal.get(pos).toString());
            
            for(int i=pos+1;i<temporal.size();i++){
                v_temporal = Double.parseDouble(temporal.get(i).toString());
                
                if(v_temporal == valor_moda_actual){
                    cont_moda_actual++;
                }
                else{
                    if(cont_moda<cont_moda_actual){
                        cont_moda = cont_moda_actual;
                        valor_moda = valor_moda_actual;
                    }
                    
                    valor_moda_actual = v_temporal;
                    cont_moda_actual = 1;
                }
                
            }
            
            return valor_moda;
            
        }
        catch(Exception e){
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"No se ha podido procesar la funcion mean."));
        }
        
        return 0;
    }
    
    public LinkedList<Object> ordenar_valores(LinkedList<Object> lista){
        
        try{
            
            double n1;
            double n2;
            Object temporal;
            
            for(int i=0;i<lista.size()-1;i++){
                for(int j=i;j<lista.size();j++){
                    n1 = Double.parseDouble(lista.get(i).toString());
                    n2 = Double.parseDouble(lista.get(j).toString());
                    
                    if(n2<n1){
                        temporal = lista.get(i);
                        lista.set(i,lista.get(j));
                        lista.set(j, temporal);
                    }                  
                }
            }
                      
            return lista;
        }
        catch(Exception e){        
            return null;    
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
    
    
    //---------------------------------------GRAFICAS-------------------------------------------------------//
    public JFreeChart pieChart(Instruccion valores, Instruccion labels, Instruccion titulos, TablaDeSimbolos ts, LinkedList<Mensaje> mensajes, int linea, int columna){
        
        try{
            LinkedList valores_grafica = ((Vector)valores.ejecutar(ts, mensajes)).getVector();
            LinkedList labels_grafica = ((Vector)labels.ejecutar(ts, mensajes)).getVector();
            String titulo = titulos.ejecutar(ts, mensajes).toString();
            
            DefaultPieDataset dataset = new DefaultPieDataset( );
            
            if(valores_grafica.size() != labels_grafica.size())
                mensajes.add(new Mensaje(linea,columna,SEMANTICO,"El número de labels debe ser igual a la cantidad de valores de la gráfica."));
            
            int cont_desc = 1;
            for(int i=labels_grafica.size();i<valores_grafica.size();i++){
                labels_grafica.add("Desconocido#"+cont_desc);
                cont_desc++;
            }
            
            for(int i=0;i<valores_grafica.size();i++)
                dataset.setValue(labels_grafica.get(i).toString(), Double.parseDouble(valores_grafica.get(i).toString()));
            
            JFreeChart chart = ChartFactory.createPieChart(titulo,dataset,true,true,false);
            
            return chart;
        }
        catch(Exception e){
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Es imposible crear el gráfico de pie."));
            return null;
        }
        
    }
    
    public JFreeChart barplot(Instruccion H, Instruccion Xlab, Instruccion Ylab, Instruccion main, Instruccion names, TablaDeSimbolos ts, LinkedList<Mensaje> mensajes, int linea, int columna){
        
        try{
            
            LinkedList<Object> valores_grafica = ((Vector)H.ejecutar(ts, mensajes)).getVector();
            String et_x = Xlab.ejecutar(ts, mensajes).toString();
            String et_y = Ylab.ejecutar(ts, mensajes).toString();
            String titulo = main.ejecutar(ts, mensajes).toString();
            LinkedList<Object> labels_grafica = ((Vector)names.ejecutar(ts, mensajes)).getVector();
            
            DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
            if(valores_grafica.size() != labels_grafica.size())
                mensajes.add(new Mensaje(linea,columna,SEMANTICO,"El número de labels debe ser igual a la cantidad de valores de la gráfica."));
            
            int cont_desc = 1;
            for(int i=labels_grafica.size();i<valores_grafica.size();i++){
                labels_grafica.add("Desconocido#"+cont_desc);
                cont_desc++;
            }
            
            for(int i=0;i<valores_grafica.size();i++)
                dataset.addValue(Double.parseDouble(valores_grafica.get(i).toString()),et_x,labels_grafica.get(i).toString());
            
            JFreeChart chart = ChartFactory.createBarChart(titulo,et_x, et_y, dataset,PlotOrientation.VERTICAL, true, true, false);
                    
            return chart;
            
        }
        catch(Exception e){
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"Es imposible crear el gráfico barplot."));
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
