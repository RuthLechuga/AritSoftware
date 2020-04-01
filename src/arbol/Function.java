package arbol;

import Entorno.Funciones;
import Entorno.TablaDeSimbolos;
import Utilidades.Mensaje;
import static Utilidades.Mensaje.tipo_mensaje.SEMANTICO;
import java.util.LinkedList;

public class Function implements Instruccion {
    
    private String nombre;
    private LinkedList<Declaracion> parametros;
    private LinkedList<Instruccion> bloque_Instrucciones;
    private int linea;
    private int columna;
    
    public Function(String nombre, LinkedList<Declaracion> parametros, LinkedList<Instruccion> bloque_instrucciones, int linea, int columna){
        this.nombre = nombre;
        this.parametros = parametros;
        this.bloque_Instrucciones = bloque_instrucciones;
        this.linea = linea;
        this.columna = columna;
    }

    public Function(String nombre, LinkedList<Instruccion> bloque_instrucciones, int linea, int columna){
        this.nombre = nombre;
        this.parametros = new LinkedList<Declaracion>();
        this.bloque_Instrucciones = bloque_instrucciones;
        this.linea = linea;
        this.columna = columna;
    }

    @Override
    public Object ejecutar(TablaDeSimbolos ts, LinkedList<Mensaje> mensajes) {
        Funciones instancia = Funciones.getSingletonInstance();
        
        if(!instancia.addFunction(this))
            mensajes.add(new Mensaje(linea,columna,SEMANTICO,"La función: "+nombre+" ya ha sido creada anteriormente."));
        
        return null;
    }

    @Override
    public String getArbol(TablaDeSimbolos ts) {
    String temporal = 
                "   \""+this.toString()+"\" [label=\"decl_function\"] ;\n" +
                "   \""+this.toString()+nombre+"\" [label=\"IDENTIFICADOR\"] ;\n" +
                "   \""+this.toString()+"\" -> \""+this.toString()+nombre+"\"\n";
    
        temporal += "\""+parametros.toString()+"\" [label=\"parametros\"] ;\n";
        temporal+= "\""+this.toString()+"\" -> \""+parametros.toString()+"\"\n";
            
        for(Declaracion dec:parametros){
            temporal += "\""+dec.toString()+"\" [label=\"parametro\"] ;\n";
            temporal+= "\""+parametros.toString()+"\" -> \""+dec.toString()+"\"\n";
        
            temporal += dec.getArbol(ts);
        }
    
        temporal += "\""+bloque_Instrucciones.toString()+"\" [label=\"instrucciones\"] ;\n";
        temporal+= "\""+this.toString()+"\" -> \""+bloque_Instrucciones.toString()+"\"\n";
            
        for(Instruccion ins:bloque_Instrucciones){
            temporal += "\""+ins.toString()+"\" [label=\"instruccion\"] ;\n";
            temporal+= "\""+bloque_Instrucciones.toString()+"\" -> \""+ins.toString()+"\"\n";
        
            temporal += ins.getArbol(ts);
        }
        
        return temporal;}

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public LinkedList<Declaracion> getParametros() {
        return parametros;
    }
    public void setParametros(LinkedList<Declaracion> parametros) {
        this.parametros = parametros;
    }
    public LinkedList<Instruccion> getBloque_Instrucciones() {
        return bloque_Instrucciones;
    }
    public void setBloque_Instrucciones(LinkedList<Instruccion> bloque_Instrucciones) {
        this.bloque_Instrucciones = bloque_Instrucciones;
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
