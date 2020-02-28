package Entorno;
import static Entorno.Tipo.tipo_primitivo.*;
import java.util.LinkedList;

public class Simbolo {

    private Tipo tipo;
    private String identificador;
    private Object valor;
    private int dimensionX;
    private int dimensionY;
    private int linea;
    private int columna;
    
    public Simbolo(Tipo tipo, String identificador){
        this.tipo = tipo;
        this.identificador = identificador;
        this.dimensionX = 1;
        this.dimensionY = 1;
        
        switch(this.tipo.getTipo_primitivo()){
            case ENTERO: 
                this.valor = 0;
                break;
            case DECIMAL: 
                this.valor = 0.0;
                break;
            case BOOLEAN: 
                this.valor = false;
                break;
            case CADENA: 
            case LISTA: 
            case VECTOR: 
            case ARREGLO: 
            case MATRIZ: 
                this.valor = null;
                break;
        }
    }

    public Simbolo(String identificador, Object valor){
        this.identificador = identificador;
        this.valor = valor;
        this.dimensionX = 1;
        this.dimensionY = 1;
        
        if(valor instanceof Integer)
            this.tipo = new Tipo(ENTERO);
        
        else if(valor instanceof Double)
            this.tipo = new Tipo(DECIMAL);
        
        else if(valor instanceof String)
            this.tipo = new Tipo(CADENA);
        
        else if(valor instanceof Boolean)
            this.tipo = new Tipo(BOOLEAN);
        
        else if(valor instanceof LinkedList){
            
            this.tipo = (Tipo)(((LinkedList)valor).get(0));
            ((LinkedList) this.valor).remove(0);
            
            if(this.tipo.getTipo_primitivo().compareTo(LISTA)==0){
                this.dimensionX = ((LinkedList) this.valor).size();
            }
        }
    }
    
    public Simbolo(String identificador){
        this.identificador = identificador;
        this.valor = null;
    }
    
    public Tipo getTipo() {
        return tipo;
    }
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
    public String getIdentificador() {
        return identificador;
    }
    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
    public void setValor(Object valor) {
        this.valor = valor;
    }   
    public int getDimensionX() {
        return dimensionX;
    }
    public void setDimensionX(int dimensionX) {
        this.dimensionX = dimensionX;
    }
    public int getDimensionY() {
        return dimensionY;
    }
    public void setDimensionY(int dimensionY) {
        this.dimensionY = dimensionY;
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
    public Object getValor() {
        return valor;
    }
    
    public String getValorCadena(){
        return valor.toString();
    }
   
}
